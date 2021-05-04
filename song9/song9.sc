Server.default.makeGui;

"/home/dbalchen/Music/song7/include/setup.sc".load;

"/home/dbalchen/Music/song7/include/classes/PitchClass.sc".load;
"/home/dbalchen/Music/song7/include/classes/getMeasures.sc".load;

/*
* Instrument Setup
*/

"/home/dbalchen/Music/song7/include/synths/evenVCO.sc".load;


~mytrack = Track.new(~out0,0);
~mytrack.noteON =~evenVCOpoly;

~mytrack2 = Track.new(~out0,1);
~mytrack2.noteON = ~evenVCOpoly2;

~mytrack3 = Track.new(~out0,2);
~mytrack3.noteON = ~evenVCOpoly2;

~mytrack4 = Track.new(~out0,3);
~mytrack4.noteON = ~channel0;//~frenchHorn; //~evenVCOpoly;

~mytrack10 = Track.new(~out1,9);

//
~vca.gui;
~vcf.gui;
//


// I see shit here
~bassbuff = Buffer.read(s, "/home/dbalchen/Music/song9/include/audio/bass.wav");
~loop = {arg audio,amp = 0.25;amp*PlayBuf.ar(2,~bassbuff,loop:1)};

~loop.free;
~bassbuff.free;

/*
** The Song
*/

t = TempoClock.default.tempo = 90/60;

~midiFactory = MidiFactory.new("/home/dbalchen/Desktop/song9.mid");

// Track 1

~t0 = [0,2,4,5,7,9,10];

~mynotes = ~midiFactory.getTrack(0,5);

~ff0 = ~mynotes.freqs.deepCopy;

~f0 = ~ff0.deepCopy;

~f0 = ~pitchClassTI.value(~f0,5) + 60;

~t1 = ~pcset.value(~pitchClassTI.value(~t0 + 12,5));

~f0 = ~pitchMap.value(~f0,~t1,~t0);

~mynotes.freqs = ~f0;

~mytrack.notes = ~mynotes.init;

~mytrack.transport.play;

~mytrack.transport.mute;

~mytrack.transport.unmute;



// Track 2

~mynotes2 = ~midiFactory.getTrack(1,2);

~f2 = ~mynotes2.freqs.deepCopy;

// -----------------
~mynotes2 = ~mynotes.deepCopy;

~f2  = ~f0.copy;

~f2 = ~pitchClassT.value(~f2,7) + 60;

~t2 = ~pcset.value(~pitchClassT.value(~t0 + 12,7));

~f2 = ~pitchMap.value(~f2,~t2,~t0);

~mynotes2.freqs = ~f2;

~mytrack2.notes = ~mynotes2.init;

~mytrack2.transport.mute;

~mytrack2.transport.unmute;



// Track 3

~mynotes3 = ~midiFactory.getTrack(2,5);
~f3 = ~mynotes3.freqs.deepCopy;


~mynotes3 = ~mynotes.deepCopy;

~f3  = ~f0.copy;

~f3 = ~pitchClassT.value(~f3,5) + 48;

~t3 = ~pcset.value(~pitchClassT.value(~t0 + 12,5));

~f3 = ~pitchMap.value(~f3,~t3,~t0);


~mynotes3.freqs = ~f3;

~mytrack3.notes = ~mynotes3.init;

~mytrack3.transport.mute;

~mytrack3.transport.unmute;

// Track 4

~mynotes4 = ~midiFactory.getTrack(3,3);
~f4 = ~mynotes4.freqs.deepCopy;


~mynotes4 = ~mynotes.deepCopy;

~f4  = ~f0.copy;

~f4 = ~pitchClassTI.value(~f4,2) + 72;

~t4 = ~pcset.value(~pitchClassTI.value(~t0 + 12,2));

~f4 = ~pitchMap.value(~f4,~t4,~t0);


~mynotes4.freqs = ~f4;

~mytrack4.notes = ~mynotes4.init;

~mytrack4.transport.mute;

~mytrack4.transport.unmute;


// Track 10

~mynotes10 = ~midiFactory.getTrack(9,4);

~mytrack10.notes = ~mynotes10.init;

~mytrack10.transport.mute;

~mytrack10.transport.unmute;




~startTimer.value(90);

~rp = {~mytrack.transport.play;};
~rp = {~mytrack.transport.play;~mytrack2.transport.play;~mytrack3.transport.play;~mytrack4.transport.play;~loop = ~loop.play;~mytrack10.transport.play;};

~rp = {~mytrack2.transport.play;};
~rp = {~mytrack3.transport.play;};

~rp = {~mytrack4.transport.play;};


//////////////////////////////////////////////////////////////////////////////////////////////
~mytrack.transport.play;~mytrack2.transport.play;~mytrack3.transport.play;~mytrack4.transport.play;~loop = ~loop.play;~mytrack10.transport.play;

~mytrack.transport.mute;~mytrack2.transport.mute;~mytrack3.transport.mute;~mytrack4.transport.mute;~mytrack10.transport.mute;

~mytrack.transport.unmute;~mytrack2.transport.unmute;~mytrack3.transport.unmute;~mytrack10.transport.unmute;

~mytrack.transport.stop;~mytrack2.transport.stop;~mytrack3.transport.stop;~mytrack10.transport.stop;

