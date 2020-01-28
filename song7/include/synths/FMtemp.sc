// =====================================================================
// SuperCollider rawFM
// =====================================================================

(
SynthDef(\rawFM, { |out, amp=1,
	attack=0.25, decay=0.5, sustain=1,  release=2,
	balance = 0,spread = 1, accelerate=0, hpf = 128,gate = 0, da = 2,
	freq=440, carP=1, modP= 0.7777,moduP =0.33, index=3, mul=0.5,
	detune=0.0, modAttack=0.4, modDecay=2, modSustain=1, modRelease=0.5|

	var env = EnvGen.ar(Env.adsr(attack, decay, sustain, release), gate: gate, doneAction:da);
	var envm =  EnvGen.ar(Env.adsr(attack, decay, sustain, release), gate: gate, doneAction:da); //EnvGen.kr(Env.adsr(modAttack, modDecay,modSustain,modRelease,2,-4,0));

	var mod =  SinOsc.ar(freq * modP);

	var modu =0;// SinOsc.ar(freq * moduP * envm,0 , freq * index);

	var sig = (SinOsc.ar((freq * carP) + [2*mod + modu], 0, mul)) * env;

	sig = HPF.ar(sig,hpf);

	sig = LeakDC.ar(sig);

	sig = Splay.ar(sig);

	sig = Splay.ar(sig,spread,center:balance);

	OffsetOut.ar(out, sig * amp);

}).add;
)


~channel4 = {arg num, vel = 1;
	var ret;
	num.postln;
	ret = Synth("rawFM");
	ret.set(\freq,num.midicps);
	ret.set(\gate,1);
	ret;
};




