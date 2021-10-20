// =====================================================================
// Dynamic Oscillator
// =====================================================================

~adsr = {
	arg gate, attack = 0.5, decay = 0.5, sustain = 0.7, release = 0.5 , voc = 1;

	var env = Env.adsr(attack,decay,sustain,release);

	env = EnvGen.ar(env, gate: gate, doneAction:2);

	env = voc*(env - 1) + 1;

	env;
};

~fadsr = {
	arg gate = 0, fattack = 0.5, fdecay = 0.5, fsustain = 0.7, frelease = 0.5, aoc = 1;

	var env = ~adsr.value(gate, fattack, fdecay, fsustain, frelease, aoc);

	env;
};

~evenVCO = {
	arg gate = 0, freq = 220, lagtime = 0, bend = 0;

	var sig;

	freq = Lag.kr(freq,lagtime);

	freq = 1.5*SinOsc.kr(8) + freq;

	freq = {freq * bend.midiratio * LFNoise2.ar(2.5,0.01,1)}!8;

	sig = (0.35*SinOsc.ar(freq,0) + 0.5*Saw.ar(2*freq));

	sig;
};

~moogFilter = {
	arg sig, env, cutoff = 12000, gain = 0.25 ;

	sig = MoogFF.ar(sig,cutoff*env, gain);

	sig;
};


~dfm1Filter = {
	arg sig, env, cutoff = 12000, gain = 0.25 ;

	sig = DFM1.ar(sig,cutoff*env, gain);

	sig;
};

~dynOsc = {
	arg name = "nbasicSynth", adsr = ~adsr, fdsr = ~fadsr, osc = ~evenVCO, filter = ~moogFilter;

	SynthDef(name, {
		arg	out = 0, amp = 1.0, spread = 1, balance = 0, gate = 0, hpf = 128;

		var sig, env, fenv;

		env = SynthDef.wrap(adsr,nil,prependArgs: [gate]);
		sig = SynthDef.wrap(osc,nil,prependArgs: [gate])*env;

		fenv = SynthDef.wrap(fdsr,nil,prependArgs: [gate]);
		sig = SynthDef.wrap(filter,nil, prependArgs: [sig,fenv]);

		sig = HPF.ar(sig,hpf);

		sig = LeakDC.ar(sig);

		sig = Splay.ar(sig,spread,center:balance);

		Out.ar(out,sig * amp);

	}).add;
};

~vca = MyADSR.new(0.5,0.50,0.7,0.5,"VCA");
// ~vca.gui;
~vcf = MyADSR.new(0.5,0.50,0.7,0.6,"VCF");

~playDyno = {arg num, vel = 1,src,out = 0, synth = "nbasicSynth", vca = ~vca, vcf = ~vcf;
	var ret;

	ret = Synth(synth);
	vel = (vel/127);
	ret.set(\amp,0.75*vel);
	ret.set(\out,out);

	vca.setADSR(ret);
	ret.set(\freq,num.midicps);
	ret.set(\midinum,num);

	ret.set(\voc,1.0);
	ret.set(\lagtime,0);
	ret.set(\bend,0);

	vcf.setfADSR(ret);
	ret.set(\cutoff,10000);
	ret.set(\gain,1.2);
	ret.set(\aoc,1.0);

	ret.set(\hpf,32.5);
	ret.set(\gate,1);

	ret;
};

/*

~dynOsc.value;

~ret = Synth("nbasicSynth");
~ret.set(\gate,1);
~ret.set(\gate,0);

~mytrack = Track.new(~out0,0);
~mytrack.noteON = ~playDyno;

*/
