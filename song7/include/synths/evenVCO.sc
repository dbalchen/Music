/*
*** evenVCO
*/

"/home/dbalchen/Music/MuscleBone/include/synths/envelopes.sc".load;
"/home/dbalchen/workspace/SuperCollider/makeWaveTable.sc".load;



SynthDef("evenVCO", {
	arg ss, freq = 55, out = 0, amp = 1, lagtime = 0, da = 2, gate = 0,
	idx = 0.2,hpf = 120, bend = 0,
	attack = 1.5, decay = 2.5, sustain = 0.4, release = 0.75,
	fattack = 1.5, fdecay = 2.5,fsustain = 0.4, frelease = 0.75,
	aoc = 0.6, gain = 0.25,cutoff = 12000.00, spread = 1, balance = 0;

	var sig, env, fenv;

	env = Env.adsr(attack,decay,sustain,release);
	env = EnvGen.kr(env, gate: gate, doneAction:da);

	fenv = Env.adsr(fattack,fdecay,fsustain,frelease);
	fenv = EnvGen.kr(fenv, gate,doneAction:da);
	fenv = aoc*(fenv - 1) + 1;

	freq = Lag.kr(freq,lagtime);
	freq = {freq * bend.midiratio * LFNoise2.kr(2.5,0.01,1)}!16;

	sig = VOsc.ar(ss+idx,freq,0,mul:env*amp);

	sig = MoogFF.ar
	(
		sig,
		cutoff*fenv,
		gain
	);

	sig = HPF.ar(sig,hpf);

	sig = LeakDC.ar(sig);

	sig = Splay.ar(sig,spread,center:balance);

	Out.ar(out,sig * amp);

}).send(s);

// ****  Setup midi channel vosc
~wavetables.free;
~wavetables = ~fileList.value("/home/dbalchen/Desktop/eVCO");
~windex = ~wavetables.size;

~wavebuff = ~loadWaveTables.value(~wavetables);

/*

~evenVCOpoly = {arg num, vel = 1;
	var ret,tidx;
	num.postln;
	tidx = (~wavetables.size/120)* num;
	ret = Synth("evenVCO");
	ret.set(\ss,~wavebuff);
	ret.set(\freq,num.midicps);
	ret.set(\idx,tidx);
	ret.set(\gate,1);
	ret.set(\hpf,120);

	ret;
};

*/

SynthDef("evoOsc", { arg ss, freq = 55, out = 0, bend = 0, lagtime = 0.15, idx = 0;
	var sig;

	freq = Lag.kr(freq,lagtime);

	freq = {freq * bend.midiratio * LFNoise2.kr(2.5,0.01,1)}!16;

	sig = VOsc.ar(ss+idx,freq,0);

	sig = Splay.ar(sig);

	Out.ar(out,sig);

}).send(s);



~evo = Synth("evoOsc",addAction: \addToTail);
~evoOut = Bus.audio(s, 2);
~evo.set(\out,~evoOut);

/*

~evenVCOmono = {arg num,chan, vel = 1,out;
	var ret,tidx;
	num.postln;
	tidx = (~wavetables.size/120)* num;

	~evo.set(\freq,num.midicps);
	~evo.set(\idx,tidx);
	~evo.set(\ss,~wavebuff);
	~evo.set(\lagtime,0.1);

	ret = Synth("monoPolySynth",addAction: \addToTail);

	ret.set(\gate,1);
	ret.set(\hpf,120);
	ret.set(\attack,1.5);
	ret.set(\sigIn,~evoOut);
	ret.set(\out,out);
	ret;
};

*/