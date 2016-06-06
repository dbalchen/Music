SynthDef(\Noise1, {arg out = 0, freq = 550, rq = 0.5, lagLev = 0.0;
	var sig;
	sig = WhiteNoise.ar(1);
	freq = Lag.kr(freq, lagLev);
	sig = BPF.ar(sig,freq,rq,mul:1/rq);
	Out.ar(out, sig);
}).add;


SynthDef(\Pulse, {arg out = 0, freq = 55, width = 0.5, lagLev = 0.0;
	var sig;
	freq = Lag.kr(freq, lagLev);
	sig = LFPulse.ar(freq, 0, 0.5, 1, -0.5);
	Out.ar(out, sig);
}).add;


SynthDef(\Saw2, {arg out = 0, infreq = 0;
	var sig;
	sig = LFSaw.ar(In.ar(infreq),0.0);
	Out.ar(out, sig);
}).add;


SynthDef(\Sine1, {arg out = 0, infreq = 0;
	var sig;
	sig = SinOsc.ar(In.ar(infreq),0.5);
	Out.ar(out, sig);
}).add;
