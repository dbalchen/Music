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
	ret.set(\hpf,125);
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

~f0 = ~mynotes.freqs.deepCopy;
~s0 = ~pcset.value(~f0,~key);

~f2 = ~pitchClassT.value(~f0,-2,~key);
~s2 = ~pcset.value(~f2,~key);
~f2 = ~pitchMap.value(~f2,~s2,~s0,~key);

~f4i = ~pitchClassT.value(~f0,-4,~key);
~s4i = ~pcset.value(~f4i,~key);
~f4i = ~pitchMap.value(~f4i,~s4i,~s0,~key);

~f4 = ~pitchClassT.value(~f0,4,~key);
~s4 = ~pcset.value(~f4,~key);
~f4 = ~pitchMap.value(~f4,~s4,~s0,~key);


~f7 = ~pitchClassT.value(~f0,7,~key);
~s7 = ~pcset.value(~f7,~key);
~f7 = ~pitchMap.value(~f7,~s7,~s0,~key);

~f7 = [ 74, 76, 78, 73, 69, 71, 73, 74, 69 ];

/*
** Track 2
*/

~mynotes2 = ~mynotes.deepCopy;
~mynotes2.freqs = ~f7;
~mytrack2.notes = ~mynotes2.init;


/*
** Track 3
*/

~mynotes3 = ~mynotes.deepCopy;
~mynotes3.freqs = ~f2;
~mytrack3.notes = ~mynotes3.init;


/*
* Playback
*/

~startTimer.value(120);

~rp = {~mytrack.transport.play;};
~rp = {~mytrack2.transport.play;};
~rp = {~mytrack3.transport.play;};

~rp = {~mytrack.transport.play;~mytrack2.transport.play;~mytrack3.transport.play;};
~rp = {~mytrack.transport.stop;~mytrack2.transport.stop;~mytrack3.transport.stop;};


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

