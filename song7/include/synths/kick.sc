"/home/dbalchen/workspace/SuperCollider/makeWaveTable.sc".load;

~wavetables.free;
~wavetables = ~fileList.value("/home/dbalchen/Music/song7/include/samples/eDrums");
~windex = ~wavetables.size;
~wavebuff = ~loadWaveTables.value(~wavetables);




(

 SynthDef(\kick, { arg ss, out=0, amp= 1, da = 2,clip = 1.0, windex = 1,idx = 0.25,
       gain = 0.25,pulseAmp = 1.0, noiseAmp = 0.5, sineAmp = 0.5,gate = 0,
       cutoff = 5200.00,dist = 0.5,hpf = 55,spread = 0, balance = 0;

     var env0, env1, env1m, sig;

     env0 =  EnvGen.ar(Env.new([0.5, 1, 0.5, 0], [0.005, 0.06, 0.26], [-4, -2, -4]),gate: gate, doneAction:da);
     env1 = EnvGen.ar(Env.new([110, 59, 29], [0.005, 0.29], [-4, -5]),gate: gate);
     env1m = env1.midicps;

     windex = idx*(windex-1);

     sig = pulseAmp*VOsc.ar(ss + windex,env1m,0,mul:env0);

     sig = sig + (noiseAmp*WhiteNoise.ar(1));

     sig = LPF.ar(sig, env1m*1.5, env0);
     sig = sig + (sineAmp*SinOsc.ar(env1m, 0.5, env0));

     sig = sig * clip;
     sig = sig.clip2(1);

     sig = RLPFD.ar
       (
	sig,
	cutoff,
	gain,
	dist
	);

     sig = HPF.ar(sig,hpf);
     sig = LeakDC.ar(sig);
     sig = Splay.ar(sig,spread,center:balance);

     Out.ar(out, 1.7 * sig * amp);
   }).add;

 )


///////////////////////////////////// Mono Kick ///////////////////////////////////////////////////////////////////



(

 SynthDef(\noisekick, { arg out=0, amp= 0.5, clip = 0.5,
       gain = 0.25, e0In = 0, e1In = 0,lagLev = 0.1, freq = 110,rq = 0.30,
       cutoff = 5200.00,hpf = 22,spread = 0, balance = 0;

     var env0, env1, env1m, sig;

     env0 =  In.ar(e0In);
     env1 =  In.ar(e1In);

     env1m = env1.midicps;

     freq = Lag.kr(freq, lagLev);

     sig = WhiteNoise.ar(1);

     freq = freq + env1m;

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


 SynthDef(\sawPulsekick, { arg ss, out=0, amp= 1, clip = 1.0, windex = 1,idx = 0.25,freq = 55,
       gain = 0.25, e0In = 0, e1In = 0, cutoff = 5200.00,dist = 0.5,hpf = 55,lagLev = 0.1, spread = 0, balance = 0;

     var env0, env1, env1m, sig;

     env0 =  In.ar(e0In);
     env1 =  In.ar(e1In);
     env1m =  env1.midicps;

     freq = Lag.kr(freq, lagLev);
     freq = freq + (0.5 * env1m);

     windex = idx*(windex-1);

     sig = VOsc.ar(ss + windex,freq,0,0,mul:env0);

     sig = RLPFD.ar
       (
	sig,
	env1m*1.5,
	gain,
	dist,
	mul:env0
	);

     sig = sig * clip;
     sig = sig.clip2(1);

     sig = HPF.ar(sig,hpf);
     sig = LeakDC.ar(sig);
     sig = Splay.ar(sig,spread,center:balance);

     Out.ar(out, 1.7 * sig * amp);
   }).add;


 SynthDef(\sinekick, { arg out=0, amp= 1, clip = 1.3, e0In = 0, e1In = 0,freq = 27.5,
       lagLev = 0.1, hpf = 27.5,spread = 0, balance = 0;

     var env0, env1, env1m, sig;

     env0 =  In.ar(e0In);
     env1 =  In.ar(e1In);

     freq = Lag.kr(freq, lagLev);
     freq = freq + env1.midicps;

     sig = SinOsc.ar(freq, 0.375, env0);

     sig = sig * clip;
     sig = sig.clip2(1);

     sig = HPF.ar(sig,hpf);
     sig = LeakDC.ar(sig);
     sig = Splay.ar(sig,spread,center:balance);

     Out.ar(out, sig * amp);
   }).add;

 )



/*

// Test  Kick

~aa = Synth("kick");
~aa.set(\ss,~wavebuff);
~aa.set(\windex, ~windex);
~aa.set(\gate,1);


// Test Mono Kick

SynthDef(\env0, {arg out=0,gate = 0;
var sig;
sig = EnvGen.ar(
Env.new([0.5, 1, 0.5, 0], [0.005, 0.06, 0.26], [-4, -2, -4])
,gate,doneAction:2);

Out.ar(out, sig);}).add;

SynthDef(\env1, {arg out=0,gate = 0;
var sig;
sig = EnvGen.ar(
Env.new([110, 59, 29], [0.005, 0.1], [-4, -5])
,gate,doneAction:2);

Out.ar(out,sig);}).add;

~envout0 = Bus.audio(s,1);
~envout1 = Bus.audio(s,1);

~noise =  Synth("noisekick",addAction: \addToTail);
~noise.set(\e0In,~envout0);
~noise.set(\e1In,~envout1);

~pulseSaw =  Synth("sawPulsekick",addAction: \addToTail);
~pulseSaw.set(\e0In,~envout0);
~pulseSaw.set(\e1In,~envout1);
~pulseSaw.set(\ss,~wavebuff);
~pulseSaw.set(\windex, ~windex);

~sine =  Synth("sinekick",addAction: \addToTail);
~sine.set(\e0In,~envout0);
~sine.set(\e1In,~envout1);

SynthDef(\kickClock, {arg num = 60,gate = 1;
var env = Env.asr(0,1,0);
var trig = EnvGen.kr(env, gate,doneAction:2);
SendReply.kr(trig, '/kickClock', num);
}).add;

OSCdef(\kickClock, { |m|
var aa,aa1;

aa  = Synth("env0",addAction: \addToHead);
aa.set(\out,~envout0);
aa.set(\gate,1);

aa1 = Synth("env1",addAction: \addToHead);
aa1.set(\out,~envout1);
aa1.set(\gate,1);

}, '/kickClock');


Pdef(\kick, Pbind(\instrument, \kickClock, \dur, 0.5,	\amp, 1)).play;

*/
