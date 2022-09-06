/*
* SC Setup
*/
Server.default.makeGui;

"/home/dbalchen/Music/SuperCollider/include/setup.sc".load;

// Pitch Libraries

"/home/dbalchen/Music/SuperCollider/include/functions/PitchClass.sc".load;

/*
* Instrument Setup
*/

/* Set up synth

"/home/dbalchen/Music/SuperCollider/include/Synths/dynOsc.sc".load;

~dynOsc.value;

~vca.gui;
~vcf.gui;

*/

/*
* The Song
*/

// Tempo
t = TempoClock.default.tempo = 90/60;

// Read Midi
~midiFactory = MidiFactory.new("/home/dbalchen/Desktop/song9.mid");

~mynotes = (~midiFactory.getTrack(0,5)).remove0waits;
~mynotes.vels = nil;
~mynotes = ~mynotes.init;

~mynotes2 = (~midiFactory.getTrack(2,2)).remove0waits;
~mynotes2.vels = nil;
~mynotes2 = ~mynotes2.init;

~mynotes10 = (~midiFactory.getTrack(9,1)).remove0waits;
~mynotes10.vels = nil;
~mynotes10 = ~mynotes10.init;


/*
* Track 1
*/

~mytrack = Track.new(~out0,0);

/*

~mytrack.noteON = ~playDyno;


*/

// Get track 1


~mytrack.notes = ~mynotes.deepCopy.init;

~mytrack.transport.play;

~mytrack.transport.mute;

~mytrack.transport.unmute;

/*
* Track b
*/


~mytrackb = Track.new(~out0,4);

~mytrackb.notes = ~mynotes.deepCopy.init;

~mytrackb.transport.play;

~mytrackb.transport.mute;

~mytrackb.transport.unmute;



/*
* Track c
*/


~mytrackc = Track.new(~out0,5);

~mytrackc.notes = ~mynotes.deepCopy.init;

~mytrackc.transport.play;

~mytrackc.transport.mute;

~mytrackc.transport.unmute;

/*
* Track c
*/

	
~mytrackd = Track.new(~out0,6);

~mytrackd.notes = ~mynotes.deepCopy.init;

~mytrackd.transport.play;

~mytrackd.transport.mute;

~mytrackd.transport.unmute;


/*
* Track 2
*/
~mytrack2 = Track.new(~out0,2);
	
~mytrack2.notes = ~mynotes2.deepCopy.init;

/*
** Load and play
*/

~mytrack2.transport.play;

~mytrack2.transport.mute;

~mytrack2.transport.unmute;

/*
* Track 10
*/


~mytrack10 = Track.new(~out0,9);

~mytrack10.notes = ~mynotes10.deepCopy.init;


/*
** Load and play
*/

~mytrack10.transport.play;

~mytrack10.transport.mute;

~mytrack10.transport.unmute;



/*
* Transport
*/

~startTimer.value(90);

~rp = {

		~mytrack.transport.play;
	//	~mytrackb.transport.play;
	//	~mytrackc.transport.play;
	//	~mytrackd.transport.play;
	//	~mytrack10.transport.play;
	//	~mytrack2.transport.play;

	/*
    ~mytrack.transport.stop;
	~mytrackb.transport.stop;
	~mytrackc.transport.stop;
	~mytrackd.transport.stop;
	~mytrack10.transport.stop;
	~mytrack2.transport.stop;
	*/
};



/*
* Freq Manipulation
*/



~f0 = ~mynotes.freqs.deepCopy;

~f0 = ~f1

~t0 = [0,2,4,5,7,9,11];
~ti = [0,11,9,7,5,4,2];

// Inverse
~f1 = ~pitchMap.value(~f0,~t0,~ti);

// Retrograde
~f1 = ~pitchMap.value(~f0,~t0,~t0.reverse);

// Retrograde Inversion
~f1 = ~pitchMap.value(~f0,~t0,~ti.reverse);

// Pitch reverse
~f1 =  ~f0.reverse;

~f1 = ~pitchMap.value(~f0,~t0,~t0.rotate(3));
	
~mytrackb.notes.freqs = ~f1;

~mytrackc.notes.freqs = ~f1;

~mytrackd.notes.freqs = ~f0 - 12;


~mytrack.notes.freqs = ~f1;

~mytrack.notes.freqs = ~f0;



// 
motif 3,5,6,4 
inverse 3,5,2,6

// Best harmonies
mot 3,5,4
inv 3,2,4

// best H groups
3,5,-2
4,2,-2
	
////// Filter Synth settings

~dsvca = MyADSR.new(1.05,2.3,0.2,0.6,"VCA");
~dsvca.gui;
~dsvcf = MyADSR.new(2.35,1.35,0.04,0.7,"VCF");




~dstrings =
{
	arg gate = 0, freq = 220, lagtime = 0, bend = 0;

	var sig;

	freq = Lag.kr(freq,lagtime);

	freq = 0.5*SinOsc.kr(8) + freq;

	freq = {freq * bend.midiratio * LFNoise2.ar(2.5,0.01,1)}!12;

	sig = (0.35*SinOsc.ar(freq,0) + 0.5*Saw.ar(2*freq));

	sig;
};

~fmstrings =
{
	arg gate = 0, freq = 220, lagtime = 0, bend = 0;

	var sig;

	//	var fbNode = FbNode(1);

	freq = Lag.kr(freq,lagtime);

	freq = 0.5*SinOsc.kr(8) + freq;

	freq = {freq * bend.midiratio * LFNoise2.ar(2.5,0.01,1)}!12;

	sig = (0.05*SinOsc.ar(0.5*freq)) + 0.1*LFSaw.ar(freq) + (0.5*Saw.ar(2*freq));

	sig;
};


~dynOsc.value("dStrings",osc: ~dstrings);

~playDstrings = {arg num, vel = 1, chan, src, out = 0, amp = 1, balance = 0, synth = "dStrings", vca = ~dsvca, vcf = ~dsvcf;
	~playDyno.value(num, vel, chan, src, out, amp, balance,synth, vca, vcf);
};

~mytrack = Track.new(~out0,0);
~mytrack.noteON = ~playDstrings;

