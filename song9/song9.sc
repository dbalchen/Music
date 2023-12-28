/*
* Song 9
*/
/*
** SC Setup
*/
Server.default.makeGui;

"/home/dbalchen/Music/SuperCollider/include/setup.sc".load;


// Pitch Libraries

"/home/dbalchen/Music/SuperCollider/include/functions/PitchClass.sc".load;

/*
** Set up a synth

"/home/dbalchen/Music/SuperCollider/include/Synths/dynOsc.sc".load;
;; This buffer is for text that is not saved, and for Lisp evaluation.
;; To create a file, visit it with C-x C-f and enter text in its buffer.


~dynOsc.value;

~vca.gui;
~vcf.gui;

*/

/*
** The Song
*/

"/home/dbalchen/Music/song9/song9b.notes.sc".load;

/*
** Track 1
Lead Track

*** Define Track and the notes to play
*/

~mytrack = Track.new(~out0,0);

~mytrack.notes = ~mel8.deepCopy.init;

/*
*** Set up track to play on the synth
~mytrack.noteON = ~playDyno;

*/

/*
*** Play Track
*/

~mytrack.transport.play;

~mytrack.transport.mute;

~mytrack.transport.unmute;

/*
** Track 2
Bass Track

*** Define Track and the notes to play

*/

~mytrack2 = Track.new(~out0,1);

~mytrack2.notes = ~bass.deepCopy.init;


/*
*** Play Track
*/

~mytrack2.transport.play;

~mytrack2.transport.mute;

~mytrack2.transport.unmute;

/*
** Track 10
The drum track

*** Define Track and the notes to play
*/

~mytrack10 = Track.new(~out0,9);

~mytrack10.notes = ~drumbasic.deepCopy.init;

/*
*** Play Track
*/

~mytrack10.transport.play;

~mytrack10.transport.mute;

~mytrack10.transport.unmute;

/*
** Audio Bass 1
Use this as a template to read in a audio buffer
*/

~bass1.free;~bass2.free;

~bass1 = Buffer.read(s, "/home/dbalchen/Music/song9/include/audio/Loop1_Bass Bus.wav"); // remember to free the buffer later.
~bass2 = Buffer.read(s, "/home/dbalchen/Music/song9/include/audio/Loop2_Bass Bus.wav"); // remember to free the buffer later.

var synth = ~dynOsc.value("bsampler",osc: ~eSample);
~bs = Synth(synth);
~bs.set(\bufnum,~bass1);
~bs.set(\amp,0.5);

~bs.set(\gate,1);

~bs.set(\gate,0);

/*
** Transport
*/
~rp = {};

~startTimer.value(90);

~rp = {

	//	~mytrack.transport.play;
	//	~mytrack10.transport.play;
	//	~mytrack2.transport.play;
	//  ~bs.set(\gate,1);

	//	~mytrackb.transport.play;
	//	~mytrackc.transport.play;
	//  ~mytrackd.transport.play;

	//	~mytrack.transport.stop;
	//	~mytrack10.transport.stop;
	//	~mytrack2.transport.stop;
	//  ~bs.set(\gate,0);

	// ~mytrackb.transport.stop;
	// ~mytrackc.transport.stop;
	// ~mytrackd.transport.stop;

};


/*
** Freq Manipulation
*/

~f0 = ~mel8.freqs.deepCopy;

// ~f0 = ~f1

/*
*** Pitch classes
- t0 Current pitch class
- ti Inverse Pitch class
*/

~t0 = [0,2,4,5,7,9,11];
~ti = [0,11,9,7,5,4,2];

/*
*** Inverse
*/

~f1 = ~pitchMap.value(~f0,~t0,~ti);

/*
*** Retrograde
*/

~f1 = ~pitchMap.value(~f0,~t0,~t0.reverse);

/*
*** Retrograde Inversion
*/

~f1 = ~pitchMap.value(~f0,~t0,~ti.reverse);

/*
*** Pitch reverse
*/
~f1 =  ~f0.reverse;

/*
*** 3rd
*/

~f1 = ~pitchMap.value(~f0,~t0,~t0.rotate(-2));

/*
*** 5th
*/
~f1 = ~pitchMap.value(~f0,~t0,~t0.rotate(3));

/*
*** 6th
*/

~f1 = ~pitchMap.value(~f0,~t0,~t0.rotate(-5));

/*
*** Markov
*/

~f1 = ~marco.value(~test%12,~f0.at(0)%12,~f0.size);

~f1 = ~pitchMap.value(~f1,~t0,~t0,low:64,high:72);

/*
*** load frequencies new and default
*/

~mytrack.notes.freqs = ~f1.deepCopy;

~mytrack.notes.freqs = ~f0.deepCopy;

/*
*** Run Transpose
*/

~roo = 1;
(
t = Task({
	loop {
		s.makeBundle(s.latency, {

			~fn = ~pitchMap.value(~f0,~t0,~t0.rotate(~roo));
			~roo = ~roo + 1;
			~mytrack.notes.freqs = ~fn;

		});
		32.wait;
	}
}).play;
)

t.stop;

/*
*** Create Harmony Tracks
*/

~mytrackb = Track.new(~out2,0);

~mytrackb.notes = ~main_Theme.deepCopy.init;

~mytrackb.notes.freqs = ~f1.deepCopy;
~mytrackb.notes.freqs = ~f0.deepCopy;

~mytrackc = Track.new(~out2,0);

~mytrackc.notes = ~main_Theme.deepCopy.init;

~mytrackc.notes.freqs = ~f1.deepCopy;
~mytrackc.notes.freqs = ~f0.deepCopy;

/*
** Synth settings
*/

/*
*** define VCA and VCF envelopes
*/

~dsvca = MyADSR.new(1.05,2.3,0.2,0.6,"VCA");
~dsvca.gui;
~dsvcf = MyADSR.new(2.35,1.35,0.04,0.7,"VCF");

/*
*** first synth function
*/
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

/*
*** second synth function
*/

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


/*
*** Initilize the synth and set up the track
*/

~dynOsc.value("dStrings",osc: ~dstrings);

~playDstrings = {arg num, vel = 1, chan, src, out = 0, amp = 1, balance = 0, synth = "dStrings", vca = ~dsvca, vcf = ~dsvcf;
	~playDyno.value(num, vel, chan, src, out, amp, balance,synth, vca, vcf,0, 1.0, 60,1);
};

~mytrack = Track.new(~out0,0);
~mytrack.noteON = ~playDstrings;






