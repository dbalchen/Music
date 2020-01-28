// =====================================================================
// Song 7 Workspace
// =====================================================================

"/home/dbalchen/Music/song7/include/utlities/PitchClass.sc".load;


t = TempoClock.default.tempo = 120/60;

~mytrack = Track.new(~out0,0);


// Instrument Setup

//"/home/dbalchen/Music/song7/include/synths/evenVCO.sc".load;

~vca = MyADSR.new(0.5,0.5,0.4,0.75,"VCA");

~vca.gui;

~vcf = MyADSR.new(0.5,0.5,0.4,0.75,"VCF");

~vcf.gui;

~evenVCOpoly = {arg num, vel = 1,src,out = 0;
	var ret,tidx;
	num.postln;
	tidx = ((~windex-1)/120)* num;
	ret = Synth("evenVCO");
	ret.set(\ss,~wavebuff);
	ret.set(\freq,num.midicps);
	ret.set(\idx,tidx);
	~vca.setADSR(ret);
	~vcf.setfADSR(ret);
	ret.set(\out,out);
	ret.set(\gate,1);
	ret.set(\hpf,220);
	ret.set(\amp,0.55);

	ret;
};



~mytrack.noteON = ~evenVCOpoly;

/*
~mytrack.noteON = ~evenVCOmono;

~mytrack.noteON = ~channel1;
*/

//================= Midi Load ======================

~midiFactory = MidiFactory.new("/home/dbalchen/Desktop/MandD.mid");

~mynotes = ~midiFactory.getTrack(0);


~mynotes.freqs;

~mynotes.waits;

~mynotes.durations;

~mynotes.vels;


~mynotes.freqs.last;

~mynotes.waits.last;

~mynotes.durations.last;

~mynotes.vels.last;


~mynotes.freqs = ~mynotes.freqs +12;

~mytrack = Track.new(~out0,0);

~mytrack.notes = ~mynotes2.init;


~mytrack.transport.play;

~mytrack.transport.stop;


///////////

~key = 9;

~tonerow = ~pcset.value(~mynotes.freqs,~key);

~p1 = [ 0, 2, 4, 5, 7, 8, 9, 11 ];

~p2 = [ 7, 9, 11, 0, 2, 3, 4, 6];

~p2 = [ 4, 5, 7, 8, 9, 11, 0, 2];

~p2 = [ 4, 3, 1, 0, 11, 9, 8, 6];

~p2 = [ 4, 6, 8, 9, 11, 0, 1, 3];


~mydic = ~buildDic.value(~p1, ~p2);

~a3rd = ~fullDic.value(~mynotes.freqs.deepCopy,~mydic,~key);

~mynotes2 = ~mynotes.deepCopy;
~mynotes2.freqs = ~a3rd - 12;

~mytrack2 = Track.new(~out0,1);
//~mytrack2.noteON = ~evenVCOpoly;

~mytrack2.notes = ~mynotes2.init;
~mytrack.notes = ~mynotes.init;

~mytrack2.transport.play;~mytrack.transport.play;

~mytrack2.transport.stop;~mytrack.transport.stop;