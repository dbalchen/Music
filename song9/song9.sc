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

"/home/dbalchen/Music/song9/song9.notes.sc".load;

// Tempo
t = TempoClock.default.tempo = 90/60;


* Track 1

*/

~mytrack = Track.new(~out1,0);

/*

~mytrack.noteON = ~playDyno;


*/

// Get track 1


~mytrack.notes = ~main_theme.deepCopy.init;

~mytrack.transport.play;

~mytrack.transport.mute;

~mytrack.transport.unmute;

/*
* Track 2
*/
~mytrack2 = Track.new(~out0,2);

~mytrack2.notes = ~bass.deepCopy.init;

/*
** Load and play
*/

~mytrack2.transport.play;

~mytrack2.transport.mute;

~mytrack2.transport.unmute;




~mytrack10 = Track.new(~out0,9); // Notice ~out1 not ~out0

~mytrack10.notes = ~drumbasic.deepCopy.init;

~mytrack10.transport.play;

~mytrack10.transport.mute;

~mytrack10.transport.unmute;

/*
* Audio Bass 1
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
* Transport
*/
~rp = {"Tits UP".postln;}
~startTimer.value(90);


~rp = {

	 ~mytrack.transport.play;
	~mytrack10.transport.play;
	~mytrack2.transport.play;
	
	//	~mytrackb.transport.play;
	//~mytrackc.transport.play;
	//~mytrackd.transport.play;
	// ~bs.set(\gate,1);


	//
	//
	//	~mytrack.transport.stop;
	// ~mytrackb.transport.stop;
	// ~mytrackc.transport.stop;
	// ~mytrackd.transport.stop;
	//	~mytrack10.transport.stop;
	//	~mytrack2.transport.stop;
	// ~bs.set(\gate,0);

};


/*
* Freq Manipulation
*/



~f0 = ~main_theme.freqs.deepCopy;

// ~f0 = ~f1

~t0 = [0,2,4,5,7,9,11];
~ti = [0,11,9,7,5,4,2];

// Inverse
~f1 = ~pitchMap.value(~f0,~t0,~ti);

// Retrograde
~f1 = ~pitchMap.value(~f0,~t0,~t0.reverse);

~// Retrograde Inversionp
~f1 = ~pitchMap.value(~f0,~t0,~ti.reverse);

// Pitch reverse
~f1 =  ~f0.reverse;

// 3rd
~f1 = ~pitchMap.value(~f0,~t0,~t0.rotate();

// 5th
~f1 = ~pitchMap.value(~f0,~t0,~t0.rotate(3));

	
// Run Transpose
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

~t0.rotate(3)

~f1 = ~pitchMap.value(~f0,~t0,~t0.rotate(2));

~mytrackb.notes.freqs = ~f1;

~mytrackc.notes.freqs = ~f1;

~mytrackd.notes.freqs = ~f0 - 12;
*/

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

`
//

~h0 =  ~mynotes.merge(~mynotes);
~mytrack.notes.replace(~h0);


~h1 =  ~mynotes.deepCopy.init;
~h1.waits = ~h1.waits*2;
~h1.durations = nil;
~h1 = ~h1.init;

~h1.freqs;
~h1.waits;
~h1.durations;

~ht = ~invAdd.value(~h0,~h1,~t0);


~mytrack.notes.replace(~ht);

~mytrack.notes.replace(~mynotes.deepCopy.init);


~f0 = ~ht.freqs.deepCopy;

~f1 = [ 67, 71, 64, 65, 69, 65, 60, 62, 69, 65, 67, 62, 71, 65, 67, 69, 60, 62, 69, 65, 60, 67, 64, 60, 62, 71, 60, 69, 60, 65, 67, 64, 60, 67, 60, 69, 65, 67, 62, 71, 65, 60, 64, 67, 69, 64, 60, 65, 65, 62, 71, 67, 67, 88 ];

/*
* Synth settings
*/

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
	~playDyno.value(num, vel, chan, src, out, amp, balance,synth, vca, vcf,0, 1.0, 60,1);
};

~mytrack = Track.new(~out0,0);
~mytrack.noteON = ~playDstrings;






