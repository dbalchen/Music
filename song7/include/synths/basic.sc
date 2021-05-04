SynthDef("basicSynth", { arg freq = 55, out = 0, amp = 0.75, da = 2, gate = 0,
	attack = 1.5, decay = 2.5, sustain = 0.4, release = 0.5,
	spread = 1, balance = 0, hpf = 128;


	var sig, env;

	env = Env.adsr(attack,decay,sustain,release);
	env = EnvGen.kr(env, gate: gate, doneAction:da);

	sig = ((0.35*SinOsc.ar(freq,0)) + (0.5*Saw.ar(freq)));

	sig = HPF.ar(sig,hpf);

	sig = LeakDC.ar(sig);

	sig = Splay.ar(sig,spread,center:balance);

	Out.ar(out,sig * (env*amp));

}).store;


~channel0 = {arg num, vel = 1;
	var ret;
	num.postln;
	ret = Synth("basicSynth");
	ret.set(\freq,num.midicps);
	ret.set(\gate,1);
	ret;
};


SynthDef("basicSynthW", { arg ss = 0, freq = 55, out = 0, amp = 0.75, da = 2, gate = 0,
	attack = 1.5, decay = 2.5, sustain = 0.4, release = 0.5,
	spread = 1, balance = 0, hpf = 128;


	var sig, env;

	env = Env.adsr(attack,decay,sustain,release);
	env = EnvGen.kr(env, gate: gate, doneAction:da);

	sig = Osc.ar(ss,freq,0,mul:env*amp);

	sig = HPF.ar(sig,hpf);

	sig = LeakDC.ar(sig);

	sig = Splay.ar(sig,spread,center:balance);

	Out.ar(out,sig * (env*amp));

}).store;


"/home/dbalchen/workspace/SuperCollider/makeWaveTable.sc".load;

~wavebuff =  ~makeWav.value("/home/dbalchen/Music/song9/include/samples/MoogWaves/02-pulse.wav");

~channel0w = {arg num, vel = 1;
	var ret;
	num.postln;
	ret = Synth("basicSynthW");
	ret.set(\ss,~wavebuff);
	ret.set(\freq,num.midicps);
	ret.set(\gate,1);
	ret;
};




SynthDef("basicOsc", { arg freq = 55, out = 0, bend = 0, lagtime = 0.25;
	var sig;

	sig =  ((0.35*SinOsc.ar(Lag.kr(freq,lagtime),0)) + (0.5*Saw.ar(Lag.kr(freq,lagtime))));
	
    sig = Pan2.ar(sig);
	
	Out.ar(out,sig);

}).store;


SynthDef("monoPolySynth", { arg sigIn = 0, out = 0, amp = 1.2, da = 2, gate = 0,
	attack = 1.5, decay = 2.5, sustain = 0.4, release = 0.75,
	fattack = 1.5, fdecay = 2.5,fsustain = 0.4, frelease = 0.75,
	voc = 1, aoc = 0.6, gain = 0.25,cutoff = 12000.00,
	spread = 1, balance = 0, hpf = 128;

	var sig, env, fenv;

	env = Env.adsr(attack,decay,sustain,release);
	env = EnvGen.kr(env, gate: gate, doneAction:da);
	env = voc*(env - 1) + 1;

	
	fenv = Env.adsr(fattack,fdecay,fsustain,frelease);
	fenv = EnvGen.kr(fenv, gate,doneAction:da);
	fenv = aoc*(fenv - 1) + 1;

	sig = In.ar(sigIn,2)*env;

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

}).store;


~bo = Synth("basicOsc",addAction: \addToTail);
~boOut = Bus.audio(s, 2);
~bo.set(\out,~boOut);

~channel1 = {arg num, vel = 1;
	var ret;
	num.postln;
	~bo.set(\freq,num.midicps);
	ret = Synth("monoPolySynth",addAction: \addToTail);
	ret.set(\sigIn,~boOut);
	ret.set(\gate,1);
	ret;
};
