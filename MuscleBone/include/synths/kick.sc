"/home/dbalchen/workspace/SuperCollider/makeWaveTable.sc".load;

~wavetables.free;
~wavetables = ~fileList.value("/home/dbalchen/workspace/SuperCollider/eDrums/samples/");

~windex = ~wavetables.size;

~wavebuff = ~loadWaveTables.value(~wavetables);


///////////////////////////////////// Mono Kick ///////////////////////////////////////////////////////////////////





SynthDef(\noisekick, { arg out=0, amp= 0.5, clip = 0.5,
	gain = 0.25, e0In = 0, e1In = 0,lagLev = 0.0, freq = 110,rq = 0.30,
	cutoff = 5200.00,hpf = 22,spread = 0, balance = 0;

	var env0, env1, env1m, sig;

	env0 =  In.ar(e0In);
	env1 =  In.ar(e1In);

	//env1m = env1.midicps;

	freq = Lag.kr(freq, lagLev);

	sig = WhiteNoise.ar(1);

	freq = freq + env1;

	sig = BPF.ar(sig,freq,rq,mul:1/rq);

	sig = sig * clip;
	sig = sig.clip2(1);

	sig = MoogFF.ar
	(
		sig,
		freq * 1.5,
		gain,
		mul:env0
	);

	sig = HPF.ar(sig,hpf);
	sig = LeakDC.ar(sig);
	sig = Splay.ar(sig,spread,center:balance);

	Out.ar(out, sig * amp);
}).add;


SynthDef(\sawPulsekick, { arg ss, out=0, amp= 1, clip = 1.0, windex = 1,idx = 0.25,freq = 55.00,fmod = 0.0,
	gain = 0.25, e0In = 0, e1In = 0, cutoff = 5200.00,dist = 0.5,hpf = 55,lagLev = 0.0, spread = 0, balance = 0;

	var env0, env1, sig;

	env0 =  In.ar(e0In);
	env1 =  In.ar(e1In);


	freq = Lag.kr(freq, lagLev);
	freq = freq + (fmod * env1);

	windex = idx*(windex-1);

	sig = VOsc.ar(ss + windex,freq,mul:env0);

	sig = RLPFD.ar
	(
		sig,
		env1*1.5,
		gain,
		dist,
		mul:env0
	);

	sig = sig * clip;
	sig = sig.clip2(1);

	sig = HPF.ar(sig,hpf);
	sig = LeakDC.ar(sig);
	sig = Splay.ar(sig,spread,center:balance);

	Out.ar(out, sig * amp);
}).add;


SynthDef(\sinekick, { arg out=0, amp= 0.5, clip = 1.3, e0In = 0, e1In = 0,freq = 27.5,
	lagLev = 0.0, hpf = 27.5,spread = 0, balance = 0;

	var env0, env1, env1m, sig;

	env0 =  In.ar(e0In);
	env1 =  In.ar(e1In);

	freq = Lag.kr(freq, lagLev);
	freq = freq + env1;

	sig = SinOsc.ar(freq, 0.375, env0);

	sig = sig * clip;
	sig = sig.clip2(1);

	sig = HPF.ar(sig,hpf);
	sig = LeakDC.ar(sig);
	sig = Splay.ar(sig,spread,center:balance);

	Out.ar(out, sig * amp);
}).add;
