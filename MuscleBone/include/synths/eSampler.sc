SynthDef(\Esampler, {arg bufnum = 0, out = 0, amp = 0.5, da = 2, gate = 0, rate = 1.0, midi = 60, basef = 60,hpf = 120,
	attack = 0, decay = 0, sustain = 1, release = 0.9, fattack = 0.0, fdecay = 0,fsustain = 1, frelease = 0.9, aoc = 0, gain = 3, cutoff = 14000.00, balance = 0, spread = 1;

	var sig, env, fenv,i;

	i = midi.midicps/basef.midicps;

	env  = Env.adsr(attack,decay,sustain,release);
	env = EnvGen.kr(env, gate: gate);

	fenv = Env.adsr(fattack,fdecay,fsustain,frelease,1,'sine');
	fenv = EnvGen.kr(fenv, gate,doneAction:da);

	fenv = aoc*(fenv - 1) + 1;

	sig = PlayBuf.ar(2, bufnum, BufRateScale.kr(bufnum)*rate*i,gate,0,0, doneAction:da);

	sig = BLowPass.ar
	(
		sig,
		cutoff*fenv,
		gain
	);

	sig = sig * amp * env;

	sig = HPF.ar(sig,hpf);

	sig = LeakDC.ar(sig);

	sig = Splay.ar(sig,spread,center:balance);

	Out.ar(out,sig);

}).store;
