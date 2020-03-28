/* =====================================================================
*  Song 7 Workspace
// =====================================================================
*/

Server.default.makeGui;

"/home/dbalchen/Music/song7/include/setup.sc".load;

t = TempoClock.default.tempo = 120/60;

/*
* Instrument Setup
*/

"/home/dbalchen/Music/song7/include/synths/evenVCO.sc".load;

~vca = MyADSR.new(1.25,3.0,0.5,0.6,"VCA");

// ~vca.gui;

~vcf = MyADSR.new(0.5,3.0,0.5,0.7,"VCF");

// ~vcf.gui;

~evenVCOpoly = {arg num, vel = 1,src,out = 0;
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
    ret.set(\hpf,425);
    vel = (vel/127)*0.60;
    ret.set(\amp,vel);
    ret;
};


/*
* Track Setup
*/

/*
** Track 1
*/
~mytrack = Track.new(~out0,0);

~mytrack.noteON = ~evenVCOpoly;

/*

~mytrack.noteON = ~evenVCOmono;

~mytrack.noteON = ~channel1;

*/


/*
** Track 2
*/
~mytrack2 = Track.new(~out0,1);

~mytrack2.noteON = ~evenVCOpoly;

/*

~mytrack2.noteON = ~evenVCOmono;

~mytrack2.noteON = ~channel1;

*/

/*
** Track 3
*/
~mytrack3= Track.new(~out0,2);

~mytrack3.noteON = ~evenVCOpoly;

/*
~mytrack3.noteON = ~evenVCOmono;

~mytrack3.noteON = ~channel1;
*/

/*
* Midi Load
*/

"/home/dbalchen/Music/song7/include/classes/getMeasures.sc".load;
"/home/dbalchen/Music/song7/include/classes/PitchClass.sc".load;

~midiFactory = MidiFactory.new("/home/dbalchen/Desktop/MandD.mid");

~mynotesAll = ~midiFactory.getTrack(0);

~pcset.value(~mynotesAll.freqs);

~key = 9;

~pcset.value(~mynotesAll.freqs,~key);

/*
** Track 1
*/

~mynotes = ~getMeasure.value(17,20,~mynotesAll);
~mytrack.notes = ~mynotes.init;

~pcset.value(~mynotes.freqs,~key);

~f0 = ~mytrack.notes.freqs.deepCopy;

~mynotes.freqs = ~pitchClassT.value(~f0,-12,~key);

~mynotes.freqs = ~f0;

/*
** Track 2
*/

~mynotes2 = ~mynotes.deepCopy;

~mynotes2.freqs = ~pitchClassT.value(~f0,-5,~key) -12;

~mytrack2.notes = ~mynotes2.init;

~f1 = ~mytrack2.notes.freqs.deepCopy;


/*
** Track 3
*/

~mynotes3 = ~mynotes.deepCopy;

~mynotes3.freqs = ~pitchClassT.value(~f0,-10,~key) -12;

~mytrack3.notes = ~mynotes3.init;

~f2 = ~mytrack3.notes.freqs.deepCopy;


/*
* Playback
*/

~mytrack.transport.play;
~mytrack.transport.mute;
~mytrack.transport.unmute;

~mytrack2.transport.play;
~mytrack2.transport.mute;
~mytrack2.transport.unmute;

~mytrack3.transport.play;
~mytrack3.transport.mute;
~mytrack3.transport.unmute;

~startTimer.value(4,120);

~rp ={~mytrack2.transport.play;~mytrack.transport.play;~mytrack3.transport.play;}


/*
// VCA Envelope
~vca.attacks = (~mynotes.durations.deepCopy) * 0.45;
~vca.decays = (~mynotes.durations.deepCopy) * 0.450;
~vca.sustain = 0.30;
~vca.init;

// VCF Envelope
~vcf.attacks = (~mynotes.durations.deepCopy) * 0.25;
~vcf.decays = (~mynotes.durations.deepCopy) * 0.5;
~vcf.sustain = 0.20;
~vcf.init;
*/

