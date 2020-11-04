Help.gui
Quarks.gui

s.boot;
s.plotTree;
s.meter;
s.quit;

Stethoscope.new(s);
FreqScope.new(800, 400, 0, server: s);
Server.default.makeGui


(
o = Server.local.options;
o.numOutputBusChannels = 24; // The next time it boots, this will take effect
o.memSize = 2097152;
s.latency = 0.04
)

"/home/dbalchen/Music/setup.sc".load;


(
~allTimesWaits = [1.0,1.0,0.5,1.0,0.5,1.0,0.75,0.75,1.0,0.5,1.0,1.0,0.5,1.0,0.5,1.0,0.75,0.75,1.0,0.5];

~probs1 = [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1];

~verse = MyTrack.new(~synth1,0);
//~verse.notes.freqs = [69,0,0,71,0,68,0,0,64,0,62,0,0,64,0,65,0,0,64,0];
//~verse.notes.freqs = [69,[68,69].choose,[66,68].choose,71,[68,69].choose,68,66,62,64,61,62,64,62,64,[68,69].choose,65,[68,71].choose,64,64,68];

~verse.notes.freqs = [ 69, 69, 66, 71, 68, 68, 66, 62, 64, 61, 62, 64, 62, 64, 69, 65, 71, 64, 64, 68 ];

~verse.notes.waits = ~allTimesWaits.deepCopy * 1;
//~verse.notes.probs = [1,0,0,1,0,1,0,0,1,0,1,0,0,1,0,1,0,0,1,0];
~verse.notes.fixdurs = 1;
// ~verse.notes.durMult = 0.95;
~newNotes =  ~verse.notes;
~verse.notes.init;
)

~startTimer.value(100);
~verse.notes.freqs;
~verse.notes.waits;
~verse.notes.probs;
~verse.notes.durations;

~verse.transport.play;
~verse.transport.stop;
~verse.transport.mute;
~verse.transport.unmute;


~tonerow = ~pcset.value(Scale.major.degrees.collect({ arg item, i; item; item}) + 57);
~tonerow = ~createScale.value(~tonerow);

~newNotes = ~melCurves.value(~verse.notes,~tonerow);
~newNotes.waits = ~allTimesWaits.deepCopy * 1;
~newNotes.probs =  ~probs1;

~newNotes.probs = ((5 * [1,0,0,1,0,1,0,0,1,0,1,0,0,1,0,1,0,0,1,0]) + ~probs1) * 0.33;
~newNotes.waits = ~allTimesWaits.deepCopy * 1;

~newNotes.freqs = ~newNotes.freqs * ~newNotes.probs;

~newNotes.freqs;
~newNotes.probs;

~newNotes.freqs =[ 69, 68, 73, 71, 69, 68, 66, 62, 64, 62, 62, 62, 62, 64, 64, 65, 62, 64, 64, 64 ]

~newNotes.freqs =[ 69, 68, 66, 71, 68, 64, 66, 62, 64, 61, 62, 71, 73, 64, 69, 65, 71, 69, 64, 71 ]

~newNotes.freqs =[ 69, 69, 71, 71, 68, 68, 66, 66, 64, 66, 62, 64, 62, 64, 68, 65, 68, 64, 64, 68 ]

~newNotes.freqs =[ 69, 68, 66, 71, 68, 64, 66, 62, 64, 61, 62, 71, 73, 64, 69, 65, 71, 69, 64, 68]

~newNotes.freqs = [ 69, 69, 68, 71, 69, 68, 66, 62, 64, 61, 62, 64, 62, 64, 66, 65, 68, 64, 64, 68 ]


~newNotes.freqs = [69, 69, 68, 71, 69, 68, 69, 66, 64, 61, 62, 64, 62, 64, 68, 65, 64, 64, 64, 68 ];


///////  futher refined
~newNotes.freqs = [ 69, 69, 66, 71, 68, 68, 66, 62, 64, 61, 62, 64, 62, 64, 69, 65, 71, 64, 64, 68 ]

~newNotes.freqs = [ 69, 69, 66, 71, 68, 68, 66, 62, 64, 61, 62, 64, 62, 64, 69, 65, 68, 64, 64, 68 ]

// Probs
[ 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 ]
[ 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1 ]
[ 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 ]
[ 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1 ]

~newNotes.probs = [ 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1 ]

[ 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0 ]
[ 1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0 ]
[ 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1 ]
[ 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0 ]

~newNotes.probs = [ 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0 ];

~newNotes.probs = [ 1, 0, 0, 1, 0, 1, 1, [0,1].choose, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0 ]
~newNotes.probs = [ 1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0 ]


~rp = {~verse.transport.play;}; // Example

(
~start = {

	var num = 100,timeNow;
	t = TempoClock.default.tempo = num / 60;

	Routine.run({
		s.sync;
		timeNow = TempoClock.default.beats;

		t.schedAbs(timeNow + 00,{ // 00 = Time in beats
			(
				// If yes put stuff Here
				~verse.transport.play;
			);

			(
				// If No put stuff here otherwise nil
				nil
			);
		};	 // End of if statement

		); // End of t.schedAbs


		//Add more

	}); // End of Routine

}; //End of Start

)


~rp = {~start.value;};
