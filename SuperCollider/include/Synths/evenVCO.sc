/*
* evenVCO
*/

"./envelopes.sc".load;


SynthDef("evenVCO", {
    arg ss, freq = 220, out = 0, amp = 1, lagtime = 0, da = 2, gate = 0,
    idx = 0.2,hpf = 120, bend = 0,
    attack = 1.5, decay = 2.5, sustain = 0.4, release = 0.75,
    fattack = 1.5, fdecay = 2.5,fsustain = 0.4, frelease = 0.75,
    aoc = 0.6, gain = 0.25,cutoff = 12000.00, spread = 1, balance = 0;

    var sig, env, fenv;

    env = Env.adsr(attack,decay,sustain,release);
    env = EnvGen.ar(env, gate: gate, doneAction:da);

    fenv = Env.adsr(fattack,fdecay,fsustain,frelease);
    fenv = EnvGen.ar(fenv, gate,doneAction:da);
    fenv = aoc*(fenv - 1) + 1;

    freq = Lag.kr(freq,lagtime);
    freq = {freq * bend.midiratio * LFNoise2.ar(2.5,0.01,1)}!16;

	 sig = VOsc.ar(ss+idx,freq,0,mul:env*amp);

	//	sig = SinOsc.ar(freq,0,mul:env*amp) + 0.5*(Saw.ar(2*freq,mul:env*amp));

    sig = MoogFF.ar
    (
        sig,
        cutoff*fenv,
        gain
    );

    sig = HPF.ar(sig,hpf);

	//	sig = Splay.ar(sig,spread,center:balance);
	sig = FreeVerb.ar(sig);
	sig = LeakDC.ar(sig) * amp;
    Out.ar(out,sig.dup);

}).send(s);




SynthDef("evenVCO2", {
    arg freq = 220, out = 0, amp = 0.5, lagtime = 0, da = 2, gate = 0,
    idx = 0.2,hpf = 45, bend = 0,
    attack = 1.5, decay = 2.5, sustain = 0.4, release = 0.75,
    fattack = 1.5, fdecay = 2.5,fsustain = 0.4, frelease = 0.75,
    aoc = 1, gain = 0.25,cutoff = 12000.00, spread = 1, balance = 0;

    var sig, env, fenv, freq2;

    env = Env.adsr(attack,decay,sustain,release);
    env = EnvGen.ar(env, gate: gate, doneAction:da);

    fenv = Env.adsr(fattack,fdecay,fsustain,frelease);
    fenv = EnvGen.ar(fenv, gate,doneAction:da);
    fenv = aoc*(fenv - 1) + 1;

    freq = Lag.kr(freq,lagtime);

	freq = 1.5*SinOsc.kr(8) + freq;
	
    freq = {freq * bend.midiratio * LFNoise2.ar(2.5,0.01,1)}!16;

	sig = (0.35*SinOsc.ar(freq,0) + 0.5*Saw.ar(freq));
	
    sig = MoogFF.ar(
    
        sig,
        cutoff*fenv,
        gain
    );
	
    sig = HPF.ar(sig,hpf);

    sig = LeakDC.ar(sig);
	//	sig = Pan2.ar(sig,balance,amp);
	sig = Splay.ar(sig,spread,center:balance);
    sig = FreeVerb.ar(sig);
    Out.ar(out,sig * (env*amp));

}).send(s);





/*
**  Setup the wavetable
*/

"/home/dbalchen/workspace/SuperCollider/makeWaveTable.sc".load;

~wavetables.free;

~wavetables = ~fileList.value("../samples/eVCO/");

~windex = ~wavetables.size;

~wavebuff = ~loadWaveTables.value(~wavetables);


~vca = MyADSR.new(1.25,3.0,0.5,0.6,"VCA");

// ~vca.gui;

~vcf = MyADSR.new(0.5,3.0,0.5,0.7,"VCF");

// ~vcf.gui;

~evenVCOpoly = {arg num, vel = 1,chan,src,out = 0;
    var ret,tidx;
    tidx = ((~windex-1)/120)* num;

    ret = Synth("evenVCO");
    ret.set(\ss,~wavebuff);
    ret.set(\freq,num.midicps);
    ret.set(\idx,tidx);

    ~vca.setADSR(ret);
    ~vcf.setfADSR(ret);

    ret.set(\out,out);
    ret.set(\gate,1);
    ret.set(\cutoff,9000);
    ret.set(\gain,0.85);
    ret.set(\aoc,0.70);
    ret.set(\hpf,125);
    vel = (vel/127)*0.4;
    ret.set(\amp,vel);
    ret;
};



~evenVCOpoly2 = {arg num, vel = 1,chan, src,out = 0;
    var ret,tidx;

    ret = Synth("evenVCO2");
    ret.set(\freq,num.midicps);
 
    ~vca.setADSR(ret);
    ~vcf.setfADSR(ret);

    ret.set(\out,out);
    ret.set(\gate,1);
    ret.set(\cutoff,7000);
    ret.set(\gain,0.85);
    ret.set(\aoc,0.7);
    ret.set(\hpf,25);
    vel = (vel/127)*0.40;
    ret.set(\amp,1.0);
    ret;
};


