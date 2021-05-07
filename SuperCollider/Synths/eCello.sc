/*
*** vosc
*/

"/home/dbalchen/Music/MuscleBone/include/synths/envelopes.sc".load;
"/home/dbalchen/workspace/SuperCollider/makeWaveTable.sc".load;



SynthDef("cello", {
	arg ss, freq = 55, out = 0, amp = 1, lagtime = 0, da = 2, gate = 0,
	idx = 0.2,hpf = 400,bend = 0,iq = 3.5,
	attack = 1.5, decay = 2.5, sustain = 0.4, release = 0.50,
	fattack = 1.5, fdecay = 2.5,fsustain = 0.4, frelease = 0.5,
	aoc = 0.1, gain = 0.5,cutoff = 8000.00, spread = 1, balance = 0;

	var sig, env, fenv, freq2;

	env = Env.adsr(attack,decay,sustain,release);
	env = EnvGen.kr(env, gate: gate, doneAction:da);

	fenv = Env.adsr(fattack,fdecay,fsustain,frelease);
	fenv = EnvGen.kr(fenv, gate,doneAction:da);
	fenv = aoc*(fenv - 1) + 1;

	freq = Lag.kr(freq,lagtime);
	freq = {freq * LFNoise2.kr(2.5,0.01,1)}!5;

	sig = VOsc.ar(ss+idx,freq,0,mul:env*amp) + (0.15*SawDPW.ar(freq,0.20,mul:env*amp));


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

/*

****  Setup midi channel vosc
*/
~cellowavetables.free;
~cellowavetables =  ~fileList.value("/home/dbalchen/Music/song7/include/samples/cello/");
~cellowindex = ~wavetables.size;

~cellowavebuff = ~loadWaveTables.value(~cellowavetables);

/*
~channel2 = {arg num, vel = 1;
	var ret,tidx;
	num.postln;
	tidx = (~cellowavetables.size/120)* num;
	ret = Synth("cello");
	ret.set(\ss,~cellowavebuff);
	ret.set(\freq,num.midicps);
	ret.set(\windex, ~cellowindex);
	ret.set(\idx,tidx);
	ret.set(\gate,1);
	ret.set(\hpf,120);
	ret.set(\out,);
	ret;
};

~channel3 = {arg num, vel = 1;
	var ret,tidx;
	num.postln;
	tidx = (~cellowavetables.size/120)* num;

	ret = Synth("cello");


	ret.set(\ss,~cellowavebuff);
	ret.set(\freq,num.midicps);
	ret.set(\windex, ~cellowindex);
	ret.set(\idx,tidx);
	ret.set(\gate,1);
	ret.set(\hpf,120);
	ret.set(\out,0);
	ret;
};
*/

SynthDef("celOsc", { arg ss, freq = 55, out = 0, bend = 0, lagtime = 0.10, idx = 0;
	var sig;

	freq = Lag.kr(freq,lagtime);

	freq = {freq * bend.midiratio * LFNoise2.kr(2.5,0.01,1)}!5;

	sig = VOsc.ar(ss+idx,freq,0);

	sig = Splay.ar(sig);

	Out.ar(out,sig);

}).send(s);


~cel = Synth("celOsc",addAction: \addToTail);
~celOut = Bus.audio(s, 2);
~cel.set(\out,~celOut);

/*
~channel3 = {arg num, vel = 1;
	var ret,tidx;
	num.postln;
	tidx = (~cellowavetables.size/120)* num;

	~cel.set(\freq,num.midicps);
	~cel.set(\idx,tidx);
	~cel.set(\ss,~cellowavebuff);

	ret = Synth("monoPolySynth",addAction: \addToTail);

	ret.set(\gate,1);
	ret.set(\hpf,120);
	ret.set(\sigIn,~celOut);
	ret.set(\out,0);
	ret;
};

*/