// =====================================================================
// Dynamic Oscillator
// =====================================================================

~adsr = {
    arg gate = 0, attack = 0.10, decay = 0.10, sustain = 0.9, release = 0.1 , voc = 1;

    var env = Env.adsr(attack,decay,sustain,release);

    env = EnvGen.ar(env, gate: gate, doneAction:2);

    env = voc*(env - 1) + 1;

    env;
};

~fadsr = {
    arg gate = 0, fattack = 0.10, fdecay = 0.10, fsustain = 0.9, frelease = 0.1, aoc = 1;

    var env = ~adsr.value(gate, fattack, fdecay, fsustain, frelease, aoc);

    env;
};


~evenVCO = {
    arg gate = 0, freq = 220, lagtime = 0, bend = 0;

    var sig;

    freq = Lag.kr(freq,lagtime);

    freq = 1.5*SinOsc.kr(8) + freq;

    freq = {freq * bend.midiratio * LFNoise2.ar(2.5,0.01,1)}!8;

    sig = (0.35*SinOsc.ar(freq,0) + 0.5*Saw.ar(2*freq));

    sig;
};



~eSample = {
    arg gate = 0,bufnum = 0, midinum = 60, rate = 1.0, basef = 60, looper = 1;

    var sig,i;

    i = midinum.midicps/basef.midicps;

    sig = PlayBuf.ar(2, bufnum, BufRateScale.kr(bufnum)*rate*i,gate,0,loop:looper, doneAction:2);

    sig;
};



~moogFilter = {
    arg sig, env, cutoff = 12000, gain = 0.25 ;

    sig = MoogFF.ar(sig,cutoff*env, gain);

    sig;
};


~dfm1Filter = {
    arg sig, env, cutoff = 12000, gain = 0.25 ;

    sig = DFM1.ar(sig,cutoff*env, gain);

    sig;
};

~dynOsc = {
    arg name = "nbasicSynth", adsr = ~adsr, fdsr = ~fadsr, osc = ~evenVCO, filter = ~moogFilter;

    SynthDef(name, {
        arg	out = 0, amp = 1.0, spread = 1, balance = 0, gate = 0, hpf = 128;

        var sig, env, fenv;

        env = SynthDef.wrap(adsr,nil,prependArgs: [gate]);
        sig = SynthDef.wrap(osc,nil,prependArgs: [gate])*env;

        fenv = SynthDef.wrap(fdsr,nil,prependArgs: [gate]);
        sig = SynthDef.wrap(filter,nil, prependArgs: [sig,fenv]);

        sig = HPF.ar(sig,hpf);

        sig = LeakDC.ar(sig);

        sig = Splay.ar(sig,spread,center:balance);

        Out.ar(out,sig * amp);

    }).add;

    name;
};

~vca = MyADSR.new(0.5,0.50,0.7,0.5,"VCA");
// ~vca.gui;
~vcf = MyADSR.new(0.5,0.50,0.7,0.6,"VCF");


~playDyno = {arg num, vel = 127, chan, src, out = 0, amp = 1, balance = 0, spread = 1, synth = "nbasicSynth", vca = ~vca, vcf = ~vcf, buffer = 0, rate = 1.0, basef = 60, looper = 1;
    var ret;

    ret = Synth(synth);
    vel = (vel/127);
    ret.set(\amp,amp*vel);

    ret.set(\out,out);

    ret.set(\balance,balance);
    ret.set(\spread,spread);

    vca.setADSR(ret);

    ret.set(\freq,num.midicps);
    ret.set(\midinum,num);

    ret.set(\bufnum,buffer);
    ret.set(\rate,rate);
    ret.set(\basef,basef);
    ret.set(\looper,looper);

    ret.set(\voc,1.0);
    ret.set(\lagtime,0);
    ret.set(\bend,0);

    vcf.setfADSR(ret);
    ret.set(\cutoff,8000);
    ret.set(\gain,0.92);
    ret.set(\aoc,0.75);

    ret.set(\hpf,32.5);
    ret.set(\gate,1);

    ret;
};

/*

~dynOsc.value;

~ret = Synth("nbasicSynth");
~ret.set(\gate,1);
~ret.set(\gate,0);

~mytrack = Track.new(~out0,0);
~mytrack.noteON = ~playDyno;

*/
