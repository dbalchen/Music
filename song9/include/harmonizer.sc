Server.default.makeGui;

"/home/dbalchen/Music/song7/include/setup.sc".load;

"/home/dbalchen/Music/song7/include/classes/PitchClass.sc".load;
"/home/dbalchen/Music/song7/include/classes/getMeasures.sc".load;
"/home/dbalchen/Music/song7/include/synths/evenVCO.sc".load;

/*
** The Song
*/

t = TempoClock.default.tempo = 120/60;

~midiFactory = MidiFactory.new("/home/dbalchen/Desktop/m&b.mid");

~mynotesAll = ~midiFactory.getTrack(1,4);
//~mynotesAll = ~getMeasure.value(9,16,~mynotesAll);

~mynotesAll = ~getMeasure.value(26,34,~mynotesAll);

// Track 0

~mytrack = Track.new(~out0,0);
~mytrack.noteON = ~evenVCOpoly2; 
~mynotes = ~mynotesAll.deepCopy;

~f0 = ~mynotesAll.freqs;
/*
 ~f0 = [ 60, 'rest', 'rest', 69, 62, 'rest', 65, 'rest', 'rest', 70, 'rest', 'rest', 65, 'rest', 'rest', 'rest' ];
 ~f0 = [ 64, 61, 64, 71, 68, 69, 61, 64, 61, 64, 68, 69, 71, 68 ];

*/

/*

~t0 = ~pcset.value(~f0);
~f0 = ~pitchClassT.value(~f0,-5);
~t1 = ~pcset.value(~f0);

~t1 = ~pcset.value(~f0).reverse;

~f0 = ~pitchMap.value(~f0,~t1,~t0);

*/


~mynotes.freqs = ~f0;

~mytrack.notes = ~mynotes.init;

/*

~mytrack.transport.mute;

~mytrack.transport.unmute;

*/

// Track2

~mytrack2 = Track.new(~out0,1);
~mytrack2.noteON = ~evenVCOpoly;
~mynotes2 = ~mynotes.deepCopy;

~f2  = ~f0.copy;
// ~f2  = [ 64, 68, 64, 69, 61, 71, 68, 64, 68, 64, 61, 71, 69, 71 ];

~t0 = ~pcset.value(~f2);
~f2 = ~pitchClassT.value(~f2,4);
~t1 = ~pcset.value(~f2);
//~t1 = ~pcset.value(~f2);
//~t1 =[ 7, 5, 2, 0, 10, 9 ];
~f2 = ~pitchMap.value(~f2,~t1,~t0);
~mynotes2.freqs = ~f2;

~mytrack2.notes = ~mynotes2.init;

/*

~mytrack2.transport.mute;

~mytrack2.transport.unmute;

*/

// Track3

~mytrack3 = Track.new(~out0,2);
~mytrack3.noteON = ~evenVCOpoly; 
~mynotes3 = ~mynotes.deepCopy;


~f3 =  ~f0.deepCopy;

/*

~t0 = ~pcset.value(~f3);
~f3 = ~pitchClassTI.value(~f3, 7);
~t1 = ~pcset.value(~f3);

~f3 = ~pitchMap.value(~f3,~t1,~t0);
*/

~mynotes3.freqs = ~f3 - 12;   

~mytrack3.notes = ~mynotes3.init;

/*
~mytrack3.transport.mute;

~mytrack3.transport.unmute;

*/

~mytrack.transport.play;~mytrack2.transport.play;~mytrack3.transport.play;

~mytrack.transport.mute;~mytrack2.transport.mute;~mytrack3.transport.mute;

~mytrack.transport.unmute;~mytrack2.transport.unmute;~mytrack3.transport.unmute;

~mytrack.transport.stop;~mytrack2.transport.stop;~mytrack3.transport.stop;

