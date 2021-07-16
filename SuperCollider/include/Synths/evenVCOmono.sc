// =====================================================================
// Mono EVO
// =====================================================================

SynthDef("evoOsc", { arg ss, freq = 55, out = 0, bend = 0, lagtime = 0.15, idx = 0;
    var sig;

    freq = Lag.kr(freq,lagtime);

    freq = {freq * bend.midiratio * LFNoise2.kr(2.5,0.01,1)}!16;

    sig = VOsc.ar(ss+idx,freq,0);

    sig = Splay.ar(sig);

    Out.ar(out,sig);

  }).send(s);


~evo = Synth("evoOsc",addAction: \addToTail);
~evoOut = Bus.audio(s, 2);
~evo.set(\out,~evoOut);

/*
**  Setup the wavetable
*/

"/home/dbalchen/workspace/SuperCollider/makeWaveTable.sc".load;

~wavetables.free;

~wavetables = ~fileList.value("/home/dbalchen/Music/song7/include/samples/eVCO");

~windex = ~wavetables.size;

~wavebuff = ~loadWaveTables.value(~wavetables);
