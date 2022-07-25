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


(
o = Server.local.options;
o.numOutputBusChannels = 32; // The next time it boots, this will take effect
o.memSize = 2097152;
s.latency = 0.00
);



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

~mynotes = (~midiFactory.getTrack(4,6)).remove0waits;
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


~f0 = ~mynotes.freqs;


~fn = ~pitchMap.value(~f0,~t0,(~t0+1)%12);//.rotate(rrand(0, 16)));

~fn = ~pitchMap.value(~f0,~t0,~t0.rotate(rrand(0, 16)));

~mytrack.notes.freqs = ~fn;

~mytrack.notes.freqs = ~f0;

~roo = 0;
(
t = Task({
    loop {
        s.makeBundle(s.latency, {

			~fn = ~pitchMap.value(~f0,~t0,~t0.rotate(~roo));
			~roo = ~roo + 1;
			~mytrack.notes.freqs = ~fn;

        });
        32.wait;
    }
}).play;
)

t.stop;