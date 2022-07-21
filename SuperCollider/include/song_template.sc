// Song Template

Server.default.makeGui;
Help.gui
Quarks.gui

Server.supernova
Server.scsynth

s.boot;
s.plotTree;
s.meter;
s.quit;

Stethoscope.new(s);
FreqScope.new(800, 400, 0, server: s);
Server.default.makeGui;


(
o = Server.local.options;
o.numOutputBusChannels = 32; // The next time it boots, this will take effect
o.memSize = 2097152;
s.latency = 0.00
)

"/home/dbalchen/Music/SuperCollider/include/setup.sc".load;
"/home/dbalchen/Music/SuperCollider/include/functions/PitchClass.sc".load;
"/home/dbalchen/Music/SuperCollider/include/Synths/dynOsc.sc".load;



/* Set up synth

~dynOsc.value;
~mytrack.noteON = ~playDyno;
~vca.gui;
~vcf.gui;

*/

// Song Setup

t = TempoClock.default.tempo = 140/60;

~scale = Scale.majorPentatonic.degrees.collect({ arg item, i; item; item}) + 68; // G Major

~mytrack = Track.new(~out0,0);
~mytrack.notes.freqs = Bjorklund(4, 4) * 35;
~mytrack.notes = ~mytrack.notes.init;

// ~mytrack.notes.freqs = ~mytrack.notes.probs.collect({ arg item, i; item; item*~scale.choose});


/*
* Transport
*/

~startTimer.value(90);

~rp = {

	~mytrack.transport.play;~mytrack10.transport.play;~mytrack2.transport.play;

	~mytrack.transport.stop;~mytrack10.transport.stop;~mytrack2.transport.stop;

};
