// =====================================================================
// FM Workspace
// =====================================================================

///////     Basic Synth Def   ///////


SynthDef("fmBasic", {
	arg ss = 0, freq = 55, out = 0, amp = 0.25, da = 2, gate = 0,
	    attack = 1.5, decay = 1.5, sustain = 0.7, release = 0.5,
	    hpf = 55, fbr = 0.0, midx = 0.0;

	var sig, env, fb;

	env = Env.adsr(attack,decay,sustain,release);

	env = EnvGen.ar(env, gate: gate, doneAction:da);

	fb = FbNode(1);

	sig = SinOsc.ar(freq,((fb*fbr) + (midx*In.ar(ss))),mul:env);

	fb.write(sig);

	sig = HPF.ar(sig,hpf);

	sig = LeakDC.ar(sig);

	Out.ar(out,sig * amp);

}).send(s);


~fmBasic = {arg num, vel = 1;
	var ret;
	num.postln;
	ret = Synth("fmBasic");
	ret.set(\freq,num.midicps);
	ret.set(\gate,1);
	ret;
};
