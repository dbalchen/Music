/*
* SC Setup
*/
Server.default.makeGui;

"/home/dbalchen/Music/SuperCollider/include/setup.sc".load;

// Pitch Libraries

"/home/dbalchen/Music/SuperCollider/include/functions/PitchClass.sc".load;
"/home/dbalchen/Music/SuperCollider/include/functions/getMeasures.sc".load;

/*
* Instrument Setup

//  Set up synth

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

~mynotes = (~midiFactory.getTrack(0,2)).remove0waits;

~mynotes = ~mynotes.init;

~ff0 = ~mynotes.freqs.deepCopy;

/*
**  Play with Pitches
*/

// Pitch Classes

~t0 =  [0,2,4,4,5,7,9,11];
// ~t1 =  [ 0.0, 1.0, 2.0, 4.0, 5.0, 7.0, 9.0, 10.0, 11.0 ];
~t1 = [ 0.0, 1.0, 3.0, 4.0, 5.0, 7.0, 9.0, 11.0 ]

~t0 =  [0,2,2,4,5,7,9,9,11];
~t1 = [ 0.0, 2.0, 3.0, 5.0, 7.0, 8.0, 9.0, 10.0, 11.0 ];

//~t1 = [2,4,5,7,9,11,0];




~f0 = ~ff0.deepCopy;

~t1 = ~pitchClassT.value(~t0 + 12,2);

~f0 = ~pitchMap.value(~f0,~t1,~t0);


~f0 = ~mynotesT.freqs.deepCopy;

~pcset.value(~f0);

~mynotesT.freqs = ~f0 - 12;


/*
** Play with Rhythm
*/

/*
** Load and play
*/


~mytrack.notes = ~mynotes.init;

~mytrack.notes = ~mynotesT.init;

~mytrack10.notes = ~mynotes10.init;

~mynotesT = ~invAdd.value(~mynotes,~mynotes2,9);



~mytrack.transport.play;

~mytrack.transport.mute;

~mytrack.transport.unmute;


/*
* Track 2
*/

~mytrack2 = Track.new(~out0,1);

~mynotes2 = ~midiFactory.getTrack(1,3).remove0waits;

~mynotes2 = ~mynotes2.init;

~mynotes2.freqs;


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

~rp = {~mytrack.transport.play;~mytrac10.transport.play;~mytrack2.transport.play;};

~mytrack.transport.play;~mytrack10.transport.play;~mytrack2.transport.play;
~mytrack.transport.stop;~mytrack10.transport.stop;~mytrack2.transport.stop;

