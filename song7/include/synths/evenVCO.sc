/*
* evenVCO
*/

"/home/dbalchen/Music/MuscleBone/include/synths/envelopes.sc".load;


SynthDef("evenVCO", {
    arg ss, freq = 55, out = 0, amp = 1, lagtime = 0, da = 2, gate = 0,
    idx = 0.2,hpf = 120, bend = 0,
    attack = 1.5, decay = 2.5, sustain = 0.4, release = 0.75,
    fattack = 1.5, fdecay = 2.5,fsustain = 0.4, frelease = 0.75,
    aoc = 0.6, gain = 0.25,cutoff = 12000.00, spread = 1, balance = 0;

    var sig, env, fenv;

    env = Env.adsr(attack,decay,sustain,release);
    env = EnvGen.kr(env, gate: gate, doneAction:da);

    fenv = Env.adsr(fattack,fdecay,fsustain,frelease);
    fenv = EnvGen.kr(fenv, gate,doneAction:da);
    fenv = aoc*(fenv - 1) + 1;

    freq = Lag.kr(freq,lagtime);
    freq = {freq * bend.midiratio * LFNoise2.kr(2.5,0.01,1)}!16;

    sig = VOsc.ar(ss+idx,freq,0,mul:env*amp);

    sig = MoogFF.ar
    (
        sig,
        cutoff*fenv,
        gain
    );

    sig = HPF.ar(sig,hpf);

    sig = LeakDC.ar(sig);

    sig = Splay.ar(sig,spread,center:balance);
    sig = FreeVerb.ar(sig);
    Out.ar(out,sig * amp);

}).send(s);

/*
**  Setup the wavetable
*/

"/home/dbalchen/workspace/SuperCollider/makeWaveTable.sc".load;

~wavetables.free;

~wavetables = ~fileList.value("/home/dbalchen/Music/song7/include/samples/eVCO");

~windex = ~wavetables.size;

~wavebuff = ~loadWaveTables.value(~wavetables);


