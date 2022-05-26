/*
* SC Setup
*/
Server.default.makeGui;

"/home/dbalchen/Music/SuperCollider/include/setup.sc".load;

// Pitch Libraries

"/home/dbalchen/Music/SuperCollider/include/functions/PitchClass.sc".load;
// "/home/dbalchen/Music/SuperCollider/include/functions/getMeasures.sc".load;

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

~mytrack.notes = ~tmp.init;

~mytrack.transport.play;

~mytrack.transport.mute;

~mytrack.transport.unmute;

/*
* Track 2
*/
~mytrack2 = Track.new(~out0,1);

// ~mynotes2 = ~mynotes.deepCopy;
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

~f0 = ~tmp.freqs;

~t0 = [0,2,4,5,7,9,11];
~ti = [0,11,9,7,5,4,2];

~f1 = ~pitchMap.value(~f0,~t0,~ti);

~f1 = ~pitchMap.value(~f0,~t0,~ti.rotate(3));

~f1 = ~pitchMap.value(~f0,~t0,~t0.rotate(3).reverse);

~f1 = ~pitchMap.value(~f0,~t0,~ti.rotate(3).reverse);

~f1 =  ~f0.reverse;

~f1 = ~pitchMap.value(~f0,~t0,(~t0.rotate(5)));

~f1 = ~pitchMap.value(~f0,~t0,(~t0.rotate(7)));

~f1 = ~pitchMap.value(~f0,~t0,~ti.rotate(5));

~f1 = ~pitchMap.value(~f0,~t0,(~ti.rotate(7)));




~tmp.freqs = ~f1;

~tmp2 = ~tmp.deepCopy;

~tmp2 = ~tmp2.merge(~tmp);

