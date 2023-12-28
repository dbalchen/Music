// =====================================================================
// ProtoPiano Workspace
// =====================================================================

SynthDef("protopiano",
	{
		arg freq = 220, out = 0, amp = 0.014, gate = 0,	hpf = 0;

		var sig, sig1, sig2 =0, sig3, sig4, sig5, sig6,
		env1, env2, env3, env4, env5, env6,
		fb1, fb2, fb3, fb4, fb5, fb6, fbr = 0.005, fbr2 = 0.35;

		env1 = Env.adsr(0.00025,3,0.0001,0.2);
		env1 = EnvGen.ar(env1, gate: gate, doneAction:2);

		env2 = Env.adsr(0.00025,4,0.0001,0.45);
		env2 = EnvGen.ar(env2, gate: gate, doneAction:2);

		env3 = Env.adsr(0.00002,3.5,0.0001,0.45);
		env3 = EnvGen.ar(env3, gate: gate, doneAction:2);

		env4 = Env.adsr(0.00025,3,0.0001,0.2);
		env4 = EnvGen.ar(env4, gate: gate, doneAction:2);

		env5 = Env.adsr(0.00025,4,0.0001,0.45);
		env5 = EnvGen.ar(env5, gate: gate, doneAction:2);

		env6 = Env.adsr(0.00002,4.0,0.0001,0.45);
		env6 = EnvGen.ar(env6, gate: gate, doneAction:2);


		freq = 1.1*Pulse.kr(freq: 35, width: 0.5) + freq;

		fb1 = FbNode(1);

		sig3 = 0.7 * SinOsc.ar((5*freq)/(2**(4/1200)),0,mul:env3);

		sig2 = 1.5 * SinOsc.ar(freq/(2**(5/1200)),sig3,mul:env2);

		sig1 = 1.0 * SinOsc.ar(freq/(2**(6/1200)), sig2 + (fbr*fb1),mul:env1);

		fb1.write(sig1);

		fb2 = FbNode(1);

		sig6 = 1.5 * SinOsc.ar((5*freq)/(2**(1/1200)),fbr2*fb2,mul:env6);

		sig5 = 1.5 * SinOsc.ar(freq/(2**(2/1200)),sig6,mul:env5);

		sig4 = 1.0 * SinOsc.ar(freq/(2**(3/1200)), sig5 ,mul:env4);

		fb2.write(sig4);

		sig =  (1.2*sig4) + (1*sig1);

		sig = HPF.ar(sig,hpf);

		sig = LeakDC.ar(sig);

		sig = Splay.ar(sig);

		Out.ar(out,(sig * amp)!2);

}).send(s);


/*
~protopiano = {arg num, vel = 1;
var ret;
num.postln;
ret = Synth("protopiano");
ret.set(\freq,(num).midicps);
ret.set(\gate,1);
ret;
};


~mytrack.noteON = ~protopiano;
*/

~bPiano = {

	arg gate = 0, freq = 55;

	var sig, sig1, sig2, sig3, sig4, sig5, sig6,
	env1, env2, env3, env4, env5, env6, fb1, fb2,
	fbr = 0.001, fbr2 = 0.05;

	env1 = Env.adsr(0.0025,3,0.0001,0.2);
	env1 = EnvGen.ar(env1, gate: gate, doneAction:0);

	env2 = Env.adsr(0.0025,4,0.0001,0.45);
	env2 = EnvGen.ar(env2, gate: gate, doneAction:0);

	env3 = Env.adsr(0.0025,3.5,0.0001,0.45);
	env3 = EnvGen.ar(env3, gate: gate, doneAction:0);

	env4 = Env.adsr(0.0025,3,0.0001,0.2);
	env4 = EnvGen.ar(env4, gate: gate, doneAction:0);

	env5 = Env.adsr(0.0025,4,0.0001,0.45);
	env5 = EnvGen.ar(env5, gate: gate, doneAction:0);

	env6 = Env.adsr(0.0025,4.0,0.0001,0.45);
	env6 = EnvGen.ar(env6, gate: gate, doneAction:0);

	freq = 1.3*Pulse.kr(freq: 35, width: 0.5) + freq;

	fb1 = FbNode(1);

	sig3 = 0.7 * SinOsc.ar((5*freq)/(2**(4/1200)),0,mul:env3);

	sig2 = 1.5 * SinOsc.ar(freq/(2**(5/1200)),sig3,mul:env2);

	sig1 = 1.0 * SinOsc.ar(freq/(2**(6/1200)), sig2 + (fbr*fb1),mul:env1);

	fb1.write(sig1);

	fb2 = FbNode(1);

	sig6 = 1.5 * SinOsc.ar((5*freq)/(2**(1/1200)),fbr2*fb2,mul:env6);

	sig5 = 1.5 * SinOsc.ar(freq/(2**(2/1200)),sig6,mul:env5);

	sig4 = 1.0 * SinOsc.ar(freq/(2**(3/1200)), sig5 ,mul:env4);

	fb2.write(sig4);

	sig =  0.14 *((1.2*sig4) + (1*sig1));

	sig;
};

~dynOsc.value("dPiano",osc: ~bPiano);

~pianoDyno = {
	arg num, vel = 127, chan, src, out = 0, amp = 1, balance = 0, spread = 1, synth = "dPiano",
	vca = ~vca, vcf = ~vcf, voc = 1, aoc = 1, cutoff = 12000, gain = 0.1;

	~playDyno.value(num, vel, chan, src, out, amp, balance, spread, synth, vca, vcf, voc, aoc, cutoff, gain);

}

~vca = MyADSR.new(0.005,16.0,0,0.10,"VCA");

~vcf = MyADSR.new(0.0005,8.0,0.6,0.10,"VCF");

~vca.gui;
~vcf.gui;

~mytrack = Track.new(~out0,0);
~mytrack.noteON = ~pianoDyno;

