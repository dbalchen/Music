// =====================================================================
// SuperCollider Workspace song 9 b
// =====================================================================

~midiFactory = MidiFactory.new("/home/dbalchen/Desktop/song9b.mid");

~mynotes = (~midiFactory.getTrack(1,4)).remove0waits;
~mynotes.vels = nil;
~mynotes = ~mynotes.init;

~mynotesB = (~midiFactory.getTrack(0,3)).remove0waits;
~mynotesB.vels = nil;
~mynotesB = ~mynotesB.init;

~mynotes2 = (~midiFactory.getTrack(2,1)).remove0waits;
~mynotes2.vels = nil;
~mynotes2 = ~mynotes2.init;

~mynotes10 = (~midiFactory.getTrack(9,2)).remove0waits;
~mynotes10.vels = nil;
~mynotes10 = ~mynotes10.init;

~h0 = ~mynotes.merge(~mynotes);
~mytrack.notes.replace(~h0);

~r0 = ~mynotes.deepCopy.init;
~r0.waits = ~r0.waits * 2;

~h1 = ~invAdd.value(~h0,~r0,~t0);

~mytrack.notes.replace(~h1);


~mytrack.notes.freqs = [ 52, 55, 57, 59, 60, 57, 62, 60, 57, 57, 59, 60, 57, 64, 65, 69, 64, 65, 60, 69, 62, 67, 64, 64, 65, 71, 67, 62 ]