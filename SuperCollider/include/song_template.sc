// Song Template

Server.default.makeGui;

"/home/dbalchen/Music/SuperCollider/include/setup.sc".load;

// Let's do it.....
"/home/dbalchen/Music/SuperCollider/include/functions/PitchClass.sc".load;
"/home/dbalchen/Music/SuperCollider/include/functions/getMeasures.sc".load;


~mytrack = Track.new(~out0,0);

/* Set up synth

"/home/dbalchen/Music/SuperCollider/include/Synths/dynOsc.sc".load;

~dynOsc.value;

~mytrack.noteON = ~playDyno;

~vca.gui;
~vcf.gui;



*/

t = TempoClock.default.tempo = 90/60;

// Read Midi
~midiFactory = MidiFactory.new("/home/dbalchen/Desktop/song9.mid");

// Get track 1
~mynotes = ~midiFactory.getTrack(0,1);

~mynotes = ~mynotes.remove0waits

// ~mynotes.freqs


// Play with Pitches

~ff0 = ~mynotes.freqs.deepCopy;

~f0 = ~ff0.deepCopy;

~t0 = [0,2,3,5,7,9,10];
~t1 = [0,10,9,7,5,3,2];

~t1 = ~pitchClassTI.value(~t0 + 12,5);

~f0 = ~pitchMap.value(~f0,~t0,~t1);

~f0 = [ 72, 67, 70, 62, 72, 67, 70, 62 ];

~mynotes.freqs = ~f0;


// Play with Rhythm


// Load and play

~mytrack.notes = ~mynotes.init;

// Transport commands

~mytrack.transport.play;

~mytrack.transport.mute;

~mytrack.transport.unmute;
