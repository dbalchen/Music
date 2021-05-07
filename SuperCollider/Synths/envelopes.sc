SynthDef(\zeroRate,{arg out = 0;
	Out.ar(out, 0);}).add;

SynthDef(\iRate,{arg out = 0;
	Out.kr(out, 1);}).add;

SynthDef(\iRate0,{arg out = 0;
	Out.kr(out, 0);}).add;

SynthDef(\env0, {arg out=0,r1 = 0.5,r2 = 1, r3 = 0.5, r4 = 0, r5 = 0,gate = 0;
	var sig;
	sig = EnvGen.ar(
		Env.new([r1, r2, r3, r4, r5], [0.005, 0.06, 0.26, 0.1], [-4, -2, -4, -1],4)
		,gate,doneAction:2);

	Out.ar(out, sig);}).add;

SynthDef(\env1, {arg out=0,gate = 0;
	var sig;
	sig = EnvGen.ar(
		Env.new([110, 59, 29], [0.005, 0.1], [-4, -5])
		,gate,doneAction:2);

	sig = sig.midicps;
	Out.ar(out,sig);}).add;

SynthDef(\myASR,{arg out = 0, attack = 8, sustain = 1, release = 0.2, gate = 0,cutoff = 1,aoc = 1, da = 2;
	var sig;

	sig = Env.asr(attack,sustain,release);
	sig = EnvGen.ar(sig,gate,doneAction:da);
	sig = aoc*(sig - 1) + 1;
	Out.ar(out,sig*cutoff)}).add;


SynthDef(\myADSR,{arg out = 0, attack = 0, decay = 0.0, sustain = 0, release = 0.0, gate = 0,cutoff = 1, aoc = 1,da = 2;
	var sig;

	sig = Env.adsr(attack,decay,sustain,release);
	sig = EnvGen.ar(sig,gate,doneAction:da);
	sig = aoc*(sig - 1) + 1;
	Out.ar(out,sig*cutoff)}).add;



SynthDef(\two2one, {arg out=0,in0 = 0, in1 = 0,bal = 0, bmod = 999;
	var amp1 = 1, amp2 = 1, sig;

	bal = bal + 1 + In.kr(bmod);
	amp1 = bal*0.5;
	amp2 = 1 - amp1;
	sig = (In.ar(in0)*amp1) + (In.ar(in1)*amp2);
	Out.ar(out,sig);
}).add;

SynthDef(\two2two, {arg out=0, out1 = 0, in0 = 0, in1 = 0, bal = 0, bmod = 999;
	var amp1 = 1, amp2 = 1, sig0, sig1;

	bal = bal + 1 + In.kr(bmod);
	amp1 = bal*0.5;
	amp2 = 1 - amp1;
	sig0 = (In.ar(in0,2)*amp1);
	sig1 = (In.ar(in1,2)*amp2);
	Out.ar(out,sig0);
	Out.ar(out1,sig1);
}).add;


SynthDef(\myASRk,{arg out = 0, attack = 8, sustain = 1, release = 0.2, gate = 0, da = 2;
	var sig;

	sig = Env.asr(attack,sustain,release);
	sig = EnvGen.kr(sig,gate,doneAction:da);
	Out.kr(out,sig)}).add;


SynthDef(\myADSRk,{arg out = 0, attack = 0, decay = 0.0, sustain = 1, release = 0.0, gate = 0,da = 2;
	var sig;

	sig = Env.adsr(attack,decay,sustain,release);
	sig = EnvGen.kr(sig,gate,doneAction:da);
	Out.kr(out,sig)}).add;


SynthDef(\env0, {arg out=0,gate = 0;
	var sig;
	sig = EnvGen.ar(
		Env.new([0.5, 1, 0.5, 0], [0.005, 0.06, 0.26], [-4, -2, -4])
		,gate,doneAction:2);

	Out.ar(out, sig);}).add;

SynthDef(\env1, {arg out=0,gate = 0;
	var sig;
	sig = EnvGen.ar(
		Env.new([4698.6362866785, 246.9416506281, 43.6535289291], [0.005, 0.1], [-4, -5])
		,gate,doneAction:2);

	Out.ar(out,sig);}).add;


SynthDef(\env2, {arg out=0,gate = 0;
	var sig;
	sig = EnvGen.ar(
		Env.new([0,7200,4500,0], [2,1,0.9],releaseNode: 2)
		,gate,doneAction:2);

	Out.ar(out,sig);}).add;