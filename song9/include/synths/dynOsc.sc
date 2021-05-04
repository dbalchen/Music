// =====================================================================
// SuperCollider Workspace
// =====================================================================

Server.default.makeGui;


~dynOsc = {arg name = "nbasicSynth",disp = 1;

	SynthDef(name, {
		arg
		freq = 120,  out = 0,        amp = 1.0,
		spread = 1,    balance = 0, oscSel = 0,     fltSel = 0,
		attack = 1.5,  decay = 2.5, sustain = 0.4,  release = 0.75,
		fattack = 1.5, fdecay = 2.5,fsustain = 0.4, frelease = 0.75,
		voc = 1,       aoc = 1,     gain = 0.1,     cutoff = 12000.00,
		hpf = 128,     pw = 0.5,    bend = 0,       idx = 0,
		gate = 0,      da = 2;

		var sig, env, fenv, oscArray, fltArray;

		env = Env.adsr(attack,decay,sustain,release);
		env = EnvGen.ar(env, gate: gate, doneAction:da);
		env = voc*(env - 1) + 1;


		fenv = Env.adsr(fattack,fdecay,fsustain,frelease);
		fenv = EnvGen.ar(fenv, gate,doneAction:da);
		fenv = aoc*(fenv - 1) + 1;

		freq = {freq * bend.midiratio * LFNoise2.ar(2.5,0.01,1)}!disp;

		oscArray = [
			((0.35*SinOsc.ar(freq,20) + (0.5*SawDPW.ar(2*freq)))),
			DPW3Tri.ar(freq),
			SawDPW.ar(freq),
			PulseDPW.ar(freq,pw),
		];

		sig = Select.ar(oscSel,oscArray)*env;

		fltArray = [MoogFF.ar(sig,cutoff*fenv,gain),
			DFM1.ar(sig,cutoff*fenv,gain)
		];

		sig = Select.ar(fltSel,fltArray);

		sig = HPF.ar(sig,hpf);

		sig = LeakDC.ar(sig);

		sig = Splay.ar(sig,spread,center:balance);

		Out.ar(out,sig * amp);

	}).add;
};


~dynOsc.value("zbasicSynth",12);

~playFunc = {arg num, vel = 1,src,out = 0;
	var ret,tidx;

	ret = Synth("zbasicSynth");
	ret.set(\freq,num.midicps);
	ret.set(\out,out);
	ret.set(\gate,1);
	ret.set(\cutoff,10000);
	ret.set(\gain,0.7);
	ret.set(\aoc,1.0);
	ret.set(\hpf,0);
	vel = (vel/127)*0.40;
	ret.set(\amp,0.75);
	ret.set(\oscSel,0);
	ret.set(\pw,0.5);
	ret.set(\fltSel,1);
	ret;
};


"/home/dbalchen/Music/song7/include/setup.sc".load;

~mytrack = Track.new(~out0,0);
~mytrack.noteON = ~playFunc;
