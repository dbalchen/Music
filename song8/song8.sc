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
)~mynotes.init;

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

~t0 = [0,2,4,6,7,9,11];
~ti = [0,11,9,7,6,4,2];


~midiFactory = MidiFactory.new("/home/dbalchen/Desktop/song8.mid");

~mytrack = Track.new(~out0,0);

~mynotes = (~midiFactory.getTrack(3,5)).remove0waits;
~mynotes.vels = nil;
~mynotes = ~mynotes.init;

~mytrack.notes = ~mynotes.init;

~mytrack.notes.freqs

~mytrack.transport.play;

~mytrack.transport.mute;

~mytrack.transport.unmute;

~mytrack.transport.stop;

/*
* Transport
*/

~startTimer.value(140);

~rp = {

	~mytrack.transport.play;
	
	//	~mytrack10.transport.play;~mytrack2.transport.play;

	//	~mytrack.transport.stop;~mytrack10.transport.stop;~mytrack2.transport.stop;

};


~get1 = (~midiFactory.getTrack(9,1)).remove0waits;
~get1 = ~get1.getMeasure(1,2);

~get1.freqs = ~get1.freqs * 0;
~get1.waits = ~get1.waits*2;
~get1.durations = nil;
~get1.vels = nil;
~get1 = ~get1.init;

~mytrack.notes = ~get1.init;

~myAdd = ~invAdd.value(~get1,~mynotes,~t0);
~myAdd = ~myAdd.init;

~myAdd.freqs;
~mytrack.notes = ~myAdd.init;