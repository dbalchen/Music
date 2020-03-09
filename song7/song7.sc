// =====================================================================
// Song 7 Workspace
// =====================================================================

Server.default.makeGui;

"/home/dbalchen/Music/song7/include/setup.sc".load;

"/home/dbalchen/Music/song7/include/classes/PitchClass.sc".load;


t = TempoClock.default.tempo = 120/60;

~mytrack = Track.new(~out0,0);


// Instrument Setup

"/home/dbalchen/Music/song7/include/synths/evenVCO.sc".load;

~vca = MyADSR.new(1.25,3.0,0.5,0.6,"VCA");

//~vca.gui;>

// ~vca = MyADSR.new();
// ~vca.attacks = (~mynotes.durations.deepCopy * 2) * 0.25;
// ~vca.decays = (~mynotes.durations.deepCopy * 2) * 1.00;
// ~vca.sustain = 0.0;
// ~vca.init(1.0,1.0,0.0,0.4,"VCA");



~vcf = MyADSR.new(0.5,3.0,0.5,0.7,"VCF");

//~vcf.gui;

// ~vcf = MyADSR.new();
// ~vcf.attacks = (~mynotes.durations.deepCopy * 2) * 1.25;
// ~vcf.decays = (~mynotes.durations.deepCopy * 2) * 1.75;
// ~vcf.init(1.0,1.0,0.0,0.6,"VCA");

~evenVCOpoly = {arg num, vel = 1,src,out = 0;
	var ret,tidx;
	num.postln;
	tidx = ((~windex-1)/120)* num;
	ret = Synth("evenVCO");
	ret.set(\ss,~wavebuff);
	ret.set(\freq,num.midicps);
	ret.set(\idx,tidx);
	"set VCA".postln;
	~vca.setADSR(ret);
	"set VCF".postln;
	~vcf.setfADSR(ret);
	ret.set(\out,out);
	ret.set(\gate,1);
	ret.set(\cutoff,14000);
	ret.set(\gain,1.0);
	ret.set(\aoc,1.0);
	ret.set(\hpf,500);
	ret.set(\amp,0.40);
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

~mynotes.init;

~mytrack.notes = ~mynotes;

~mytrack.transport.play;







~mytrack.transport.stop;

~vca.att;
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
~mytrack2.noteON = ~evenVCOpoly;

~mytrack2.notes = ~mynotes2.init;
~mytrack.notes = ~mynotes.init;

~mytrack2.transport.play;~mytrack.transport.play;

~mytrack2.transport.stop;~mytrack.transport.stop;