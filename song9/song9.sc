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


/*
* Track 1
*/

~mytrack = Track.new(~out0,0);

/*

~mytrack.noteON = ~playDyno;


*/

// Get track 1

~mynotes = (~midiFactory.getTrack(0,5)).remove0waits;

~mytrack.notes = ~mynotes.init;

~mytrack.transport.play;

~mytrack.transport.mute;

~mytrack.transport.unmute;

/*
* Track 2
*/
~mytrack2 = Track.new(~out0,1);

// ~mynotes2 = ~mynotes.deepCopy;~tmp = ~mynotes.deepCopy;

~mytrack.notes = ~tmp.init;


~f0 = ~tmp.freqs;

~t0 = [0,2,4,5,7,9,11];
~ti = [0,11,9,7,5,4,2];
~mynotes2 = ~midiFactory.getTrack(1,2).remove0waits;

/*
** Load and play
*/

~mytrack2.notes = ~mynotes2.init;

~mytrack2.transport.play;

~mytrack2.transport.mute;

~mytrack2.transport.unmute;





/*
* Track 10
*/
~mytrack10 = Track.new(~out0,9);

~mynotes10 = ~midiFactory.getTrack(9,1);
~mynotes10 = ~mynotes10.remove0waits;

/*
** Load and play
*/

~mytrack10.notes = ~mynotes10.init;

~mytrack10.transport.play;

~mytrack10.transport.mute;

~mytrack10.transport.unmute;



/*
* Transport
*/

~startTimer.value(90);

~rp = {

~mytrack.transport.play;~mytrack10.transport.play;~mytrack2.transport.play;

~mytrack.transport.stop;~mytrack10.transport.stop;~mytrack2.transport.stop;

};



/*
* Freq Manipulation
*/

~tmp = ~mynotes.deepCopy;

~mytrack.notes = ~tmp.init;


~f0 = ~tmp.freqs;

~t0 = [0,2,4,5,7,9,11];
~ti = [0,11,9,7,5,4,2];

~f1 = ~pitchMap.value(~f0,~t0,~ti);

~f1 = ~pitchMap.value(~f0,~t0,~ti.rotate(3));

~f1 = ~pitchMap.value(~f0,~t0,~t0.reverse);

~f1 = ~pitchMap.value(~f0,~t0,~ti.reverse);

~f1 =  ~f0.reverse;

~f1 = ~pitchMap.value(~f0,~t0,(~t0.rotate(5)));

~f1 = ~pitchMap.value(~f0,~t0,~t0.rotate(-4));

~f1 = ~pitchMap.value(~f0,~t0,~ti.rotate(5));

~f1 = ~pitchMap.value(~f0,~t0,(~ti.rotate(7)));


~tmp.freqs = ~f1;

~tmp2 = ~tmp.deepCopy;

~tmp2 = ~tmp2.merge(~tmp);



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

