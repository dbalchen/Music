// =====================================================================
// SuperCollider Workspace
// =====================================================================

/*
* Midi Load
*/

"/home/dbalchen/Music/song7/include/classes/getMeasures.sc".load;
"/home/dbalchen/Music/song7/include/classes/PitchClass.sc".load;

~midiFactory = MidiFactory.new("/home/dbalchen/Desktop/m&b.mid");

~mynotesAll = ~midiFactory.getTrack(2);

~mynotes = ~getMeasure.value(26,34,~mynotesAll);

~key = 8;

~pcset.value(~mynotes.freqs,~key);

~p0 = ~mynotes.freqs.deepCopy;

~p1 = ~pitchClassT.value(~p0,9,~key);

~pcset.value(~p1,~key);

~s0 = [0, 2, 5, 7, 9, 11];

~s1 = [0, 2, 3, 5, 7, 9];

~s1 =[ 0, 2, 4, 5, 7, 10 ];

~s1 =[1, 3, 5, 7, 8, 10 ];

~s1 = [ 0, 2, 3, 5, 7, 9 ];

~s1 = [ 0, 2, 3, 5, 8, 10 ];


~p1 = ~pitchMap.value(~p1,~s1,~s0,~key);

~p1 =[ 'rest', 70, 75, 77, 75, 68, 70, 75, 73, 70, 75, 67, 68, 73, 70 ];
~p1 =[ 'rest', 73, 77, 79, 77, 70, 73, 77, 75, 73, 77, 68, 70, 75, 73 ];
~p1 =['rest', 68, 73, 75, 73, 67, 68, 73, 70, 68, 73, 65, 67, 70, 68 ]

////

~mytrack3.notes = ~mynotes.init;

~mynotes2 = ~mynotes.deepCopy;

~mytrack2.notes = ~mynotes2.init;

~mytrack2.notes.freqs = ~p1;

~mytrack3.transport.play;~mytrack2.transport.play;
~mytrack3.transport.mute;~mytrack2.transport.mute;
~mytrack3.transport.unmute;~mytrack2.transport.unmute;
