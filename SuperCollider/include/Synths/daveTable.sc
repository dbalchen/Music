
w = WavetablePrep(
	"/home/dbalchen/Documents/Surge XT/Wavetables/Exported/Analyse_osc1_sceneA.wav".standardizePath,
	wtSize: 128,
	numMaps: 64
).
read(action: { "done".postln }).
write(
	"/home/dbalchen/Desktop/growler-wt.wav"
);

w.tables.size  // 256 wavetable positions

w.gui(nil, Rect(800, 200, 500, 400)).front;  // have a look

b = Buffer.read(s, "/home/dbalchen/Desktop/growler-wt.wav".standardizePath);

(
a = {
    var sig = MultiWtOsc.ar(
        MouseX.kr(50, 800, 1).poll,
        MouseY.kr(0,62.999, 0).poll,
        bufnum: b,
		wtSize: 128,
		numTables: 64
    );
    (sig * 0.1).dup
}.play;
)

(
a = { |freq = 71, wtPos = 40.4827|
	(MultiWtOsc.ar(freq, wtPos, bufnum: b, wtSize: 128,numTables: 64) * 0.1).dup
}.play;
)

/*

MultiWtOsc.ar(freq: 440, wtPos: 0, squeeze: 0, wtOffset: 0, bufnum: 0, wtSize: 2048, numTables: 8, ratio: 2, numOscs: 1, detune: 1, interpolation: 2, hardSync: 0, phaseMod: 0)


*/



// simple oscillator
.

SynthDef("daveTable", { arg ss, freq, out = 0, amp = 1.0, gate = 0,
	wtPos = 0, wtSize = 1024, numTables = 8,
	attack = 0.5, decay = 2.5, sustain = 0.4, release = 0.75,
	fattack = 0.5, fdecay = 2.5,fsustain = 0.4, frelease = 0.85,
	voc = 1, aoc = 0.6, gain = 0.25,cutoff = 12000.00,
	spread = 1, balance = 0, hpf = 128;

	var sig, env, fenv;

	env = Env.adsr(attack,decay,sustain,release);
	env = EnvGen.kr(env, gate: gate, doneAction:da);
	env = voc*(env - 1) + 1;


	fenv = Env.adsr(fattack,fdecay,fsustain,frelease);
	fenv = EnvGen.kr(fenv, gate,doneAction:da);
	fenv = aoc*(fenv - 1) + 1;

	sig = MultiWtOsc.ar(freq, wtPos, bufnum: ss, wtSize: wtSize,numTables: numTables)

	sig = env * MoogFF.ar
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
