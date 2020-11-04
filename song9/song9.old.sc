Server.default.makeGui;

"/home/dbalchen/Music/song7/include/setup.sc".load;
"/home/dbalchen/Music/song7/include/classes/PitchClass.sc".load;
"/home/dbalchen/Music/song7/include/classes/getMeasures.sc".load;

/*
* Instrument Setup
*/

"/home/dbalchen/Music/song7/include/synths/evenVCO.sc".load;


/*
** The Song
*/

t = TempoClock.default.tempo = 90/60;

~midiFactory = MidiFactory.new("/home/dbalchen/Desktop/song9.mid");
~mynotesAll = ~midiFactory.getTrack(0,2);
//~mynotesAll = ~getMeasure.value(9,16,~mynotesAll);

// Define a track and notes for that track

~mytrack = Track.new(~out1,9);
~mynotes = Notes.new;

// Define a 4 over 8 rhythm
~r0 = [ 1, 0, 1, 0, 1, 0, 1, 0 ] ++ [ 1, 0, 1, 0, 1, 0, 1, 0 ];

~f0 = ~r0*35;

//~drumNotes = ~midiFactory.getTrack(9);

//~mynotes = ~drumNotes.deepCopy;

~mynotes.freqs = ~f0;

~mytrack.notes = ~mynotes.init;




//~mytrack.transport.play;

//~mytrack.transport.stop;


// Track 2
~mytrack2 = Track.new(~out1,9);
~mynotes2 = Notes.new;

~r2 = [ 0, 1, 0, 1, 0, 1, 0, 1 ] ++ [ 0, 1, 0, 1, 0, 1, 0, 1 ];

~f2 = ~r2*38;

~mynotes2.freqs = ~f2;

~mytrack2.notes = ~mynotes2.init;

//~mytrack2.transport.play;

//~mytrack2.transport.stop;


// Track 3

~mytrack3 = Track.new(~out0,0);
~mytrack3.notes = ~mytrack3.notes.init;
~mytrack3.noteON = ~evenVCOpoly; 

~mynotesAll = ~midiFactory.getTrack(0,1);
//~mynotesAll = ~getMeasure.value(1,4,~mynotesAll);

~mynotes3 = ~mynotesAll.deepCopy;

~f3 = [ 60, 'rest', 'rest', 69, 62, 'rest', 65, 'rest', 'rest', 70, 'rest', 'rest', 65, 'rest', 'rest', 'rest' ];

~f3 = ~mynotesAll.freqs;

~t0 = ~pcset.value(~f3);
~f3 = ~pitchClassT.value(~f3, -7);
~t1 = ~pcset.value(~f3);

// ~t1 = ~pcset.value(~f3).reverse;

~f3 = ~pitchMap.value(~f3,~t1,~t0);
// ~f3 = ~pitchMap.value(~f3,~t0,~t0);

~mynotes3.freqs = ~f3;


~mytrack3.notes = ~mynotes3.init;




// Track4

~mytrack4 = Track.new(~out0,1);
~mytrack4.notes = ~mytrack4.notes.init;
~mytrack4.noteON = ~evenVCOpoly2;

~mynotes4 = ~mynotes3.deepCopy;

~f3_HI4 = ~f3.copy; 

~t0 = ~pcset.value(~f3_HI4);
~f3_HI4 = ~pitchClassT.value(~f3_HI4,-4);
~t1 = ~pcset.value(~f3_HI4);
//  ~t1 = ~pcset.value(~f3_HI4).reverse;

~f3_HI4 = ~pitchMap.value(~f3_HI4,~t1,~t0);
//  ~f3_HI4 = ~pitchMap.value(~f3_HI4,~t0,~t0);

~mynotes4.freqs = ~f3_HI4 + 12;

~mytrack4.notes = ~mynotes4.init;


// Track5

~mytrack5 = Track.new(~out0,2);
~mytrack5.noteON = ~evenVCOpoly; 

~mynotes5 = ~mynotes3.deepCopy;

~f3_HI5 =  ~f3.deepCopy;

~t0 = ~pcset.value(~f3_HI5);
~f3_HI5 = ~pitchClassTI.value(~f3_HI5, 12 );
~t1 = ~pcset.value(~f3_HI5);

//~t1 = ~pcset.value(~f3_HI5).reverse;

~f3_HI5 = ~pitchMap.value(~f3_HI5,~t1,~t0);

~mynotes5.freqs = ~f3_HI5 - 12;   

~mytrack5.notes = ~mynotes5.init;



~mytrack.transport.play;~mytrack2.transport.play;~mytrack3.transport.play;~mytrack4.transport.play;~mytrack5.transport.play;
~mytrack.transport.mute;~mytrack2.transport.mute;~mytrack3.transport.mute;~mytrack4.transport.mute;~mytrack5.transport.mute;
~mytrack.transport.unmute;~mytrack2.transport.unmute;~mytrack3.transport.unmute;~mytrack4.transport.unmute;~mytrack5.transport.unmute;
~mytrack.transport.stop;~mytrack2.transport.stop;~mytrack3.transport.stop;~mytrack4.transport.stop;~mytrack5.transport.stop;

