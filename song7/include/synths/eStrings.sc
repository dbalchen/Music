
// =====================================================================
// SuperCollider Workspace
// =====================================================================

SynthDef("eStrings", {
	arg ss, freq = 55, out = 0, amp = 1.0, lagtime = 0, da = 2, gate = 0,
	idx = 0.2,hpf = 200,bend = 0,sinamp = 0.35, sawamp = 0.65,
	attack = 1.5, decay = 2.5, sustain = 0.4, release = 0.75,
	fattack = 1.5, fdecay = 2.5,fsustain = 0.4, frelease = 0.75,
	aoc = 0.6, gain = 0.15,cutoff = 12000.00, spread = 1, balance = 0;

	var sig, env, fenv;

	env = Env.adsr(attack,decay,sustain,release);
	env = EnvGen.kr(env, gate: gate, doneAction:da);

	fenv = Env.adsr(fattack,fdecay,fsustain,frelease);
	fenv = EnvGen.kr(fenv, gate,doneAction:da);
	fenv = aoc*(fenv - 1) + 1;

	freq = Lag.kr(freq,lagtime);
	freq = {freq * bend.midiratio * LFNoise2.kr(2.5,0.01,1)}!16;

	sig = (sinamp * SinOsc.ar(freq,mul:env*amp)) + (sawamp * (SawDPW.ar(freq,mul:env*amp)));


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



~channel0 = {arg num, vel = 1,src,out=0;
	var ret;
	num.postln;
	ret = Synth("eStrings");
	ret.set(\freq,num.midicps);
	ret.set(\gate,1);
	ret.set(\out,out);
	ret;
};

SynthDef("eStringsOsc", { arg ss, freq = 55, out = 0, bend = 0,
	sinamp = 0.35, sawamp = 0.65, lagtime = 0.15, idx = 0;

	var sig;

	freq = Lag.kr(freq,lagtime);

	freq = {freq * bend.midiratio * LFNoise2.kr(2.5,0.01,1)}!16;

	sig = (sinamp * SinOsc.ar(freq)) + (sawamp * (SawDPW.ar(freq)));

	sig = Splay.ar(sig);

	Out.ar(out,sig);

}).send(s);

~estrings = Synth("eStringsOsc",addAction: \addToTail);
~estringsOut = Bus.audio(s, 2);
~estrings.set(\out,~estringsOut);

~channel1 = {arg num, vel = 1,src,out=0;
	var ret;
	num.postln;

	~estrings.set(\freq,num.midicps);
	~estrings.set(\lagtime,0.05);
	ret = Synth("monoPolySynth",addAction: \addToTail);

	ret.set(\gate,1);
	ret.set(\hpf,120);
	ret.set(\sigIn,~estringsOut);
	ret.set(\out,0);
	ret;
};

