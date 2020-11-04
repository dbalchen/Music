
s.boot;
s.plotTree;
s.meter;
s.quit;

"/home/dbalchen/Music/setup.sc".load;

~startTimer.value(100);


(

~allTimes = [0.25,0.5,0.25,0.25,0.25,0.5,0.5,0.06,0.44,0.25,0.5,0.25,0.5,0.5,0.25,0.5,0.25,0.5,1.0,0.25,0.25,0.5,0.25,0.25,0.5,0.5,0.5,0.25,0.25,0.25,0.25,0.5,0.5,0.25,0.25,0.25,0.5,0.25,0.5,0.5,0.25,0.25,0.25,0.25];

~chimeLong = MyTrack.new(~synth1,0);
~chimeLong.notes.waits = ~allTimes.deepCopy;
~chimeLong.notes.freqs = [73,0,0,0,0,0,0,69,0,0,0,0,76,0,0,0,0,0,71,0,0,73,0,0,0,0,0,69,0,0,0,0,76,0,0,0,0,0,0,71,0,0,0,0];
~chimeLong.notes.probs = [1,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0];
~chimeLong.notes.durations = [2.43,0,0,0,0,0,0,1.44,0,0,0,0,2.44,0,0,0,0,0,1.44,0,0,2.44,0,0,0,0,0,1.44,0,0,0,0,2.44,0,0,0,0,0,0,1.44,0,0,0,0] * 0.5;
~chimeLong.notes.init;

~chimeLong = MyTrack.new(~synth1,0);
~chimeLong.notes.waits = [2.5,1.5,2.5,1.5,2.5,1.5,2.5,1.5];

~chimeLong.notes.freqs = [73,69,76,71,73,69,76,71] - 12;
~chimeLong.notes.probs = [1,1,1,1,1,1,1,1];
~chimeLong.notes.durations = [2.44,1.44,2.44,1.44,2.44,1.44,2.44,1.44];
~chimeLong.notes.init;

~chime = MyTrack.new(~synth1,1);
~chime.notes.waits = ~allTimes.deepCopy;
~chime.notes.freqs = [73,73,73,73,73,73,73,69,69,69,69,69,76,76,76,76,76,76,[71,71],71,71,73,73,73,73,73,73,69,69,69,69,69,76,76,76,76,76,76,76,71,71,71,71,71];
~chime.notes.probs = [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1];
~chime.notes.durations = [0.13,0.13,0.12,0.13,0.13,0.13,0.12,0.13,0.13,0.13,0.12,0.12,0.13,0.12,0.12,0.13,0.12,0.13,0.13,0.13,0.13,0.13,0.12,0.13,0.13,0.13,0.13,0.13,0.13,0.12,0.13,0.13,0.13,0.13,0.13,0.13,0.13,0.12,0.13,0.13,0.12,0.13,0.13,0.13] * 0.8;
~chime.notes.init;


~verse = MyTrack.new(~synth1,2);
~verse.notes.waits = ~allTimes.deepCopy;
~verse.notes.probs = [1000,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1] * 0.5;
~verse.notes.freqs = [ 69, 69, 69, 69, 68, 64, 57, 64, 64, 64, 68, 68, 66, 69, 69, 66, 64, 64, 68, 76, 68, 69, 68, 66, 68, 57, 68, 64, 64, 68, 66, 68, 66, 66, 57, 57, 64, 66, 66, 64, 66, 66, 68, 76 ];
~verse.notes.durations = nil;//~allTimes.deepCopy;
~verse.notes.init;
~verse.notes.durMult = 0.9;
~verse.notes.fixdurs = 1;
~verse.notes.persistProbs = 1;


~bassdrum = MyTrack.new(~synth1,9);
~bassdrum.notes.waits = [0.25,0.5,0.25,0.25,0.25,0.5,0.5,0.06,0.44,0.25,0.5,0.25,0.5,0.5,0.25,0.5,0.25,0.5,0.5,0.5,0.25,0.25,0.5,0.25,0.25,0.5,0.5,0.5,0.25,0.25,0.25,0.25,0.5,0.5,0.25,0.25,0.25,0.5,0.25,0.5,0.5,0.25,0.25,0.25,0.25];
~bassdrum.notes.freqs = [36,0,0,36,0,0,36,0,0,36,0,0,36,0,36,0,0,36,0,36,0,0,36,0,0,36,0,36,0,0,36,0,0,36,0,0,36,0,0,36,0,36,0,0,0];
~bassdrum.notes.probs = [1,0,0,1,0,0,1,0,0,1,0,0,1,0,1,0,0,1,0,1,0,0,1,0,0,1,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,1,0,0,0];
~bassdrum.notes.durations = [1.0,0,0,1.0,0,0,1.0,0,0,1.0,0,0,1.0,0,1.0,0,0,1.0,0,1.0,0,0,1.0,0,0,1.0,0,1.0,0,0,1.0,0,0,1.0,0,0,1.0,0,0,1.0,0,1.0,0,0,0] * 0.1;
~bassdrum.notes.init;


~snare = MyTrack.new(~synth2,9);
~snare.notes.waits = [1.75,0.75,0.66,0.59,1.75,0.5,0.25,1.25,1.75,0.75,1.25,1.75,0.75,1.25,1.0];
~snare.notes.freqs = [38,38,38,38,38,38,38,38,38,38,38,38,38,38,38];
~snare.notes.probs = [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1];
~snare.notes.durations = [1.75,0.75,0.91,0.59,1.75,0.5,0.25,1.25,1.75,0.75,1.25,1.75,0.75,1.25,1.0];

~snare.notes.init;

)

~startTimer.value(100);

~rp = {~chime.transport.play;~chimeLong.transport.play;~verse.transport.play;~bassdrum.transport.play;~snare.transport.play;}

~verse.notes.fixdurs = 1;
~verse.notes.persistProbs = 1;

~verse.notes.probs = [ 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1 ];

~verse.notes.freqs = [ 69, 69, 69, 69, 68, 64, 57, 64, 64, 64, 68, 68, 66, 69, 69, 66, 64, 64, 68, 76, 68, 69, 68, 66, 68, 57, 68, 64, 64, 68, 66, 68, 66, 66, 57, 57, 64, 66, 66, 64, 66, 66, 68, 76 ];

~verse.notes.durations;


~chime.transport.mute;
~chimeLong.transport.mute;
~verse.transport.mute;
~bassdrum.transport.mute;

~chime.transport.unmute;
~chimeLong.transport.unmute;
~verse.transport.unmute;
~bassdrum.transport.mute;


~verse.notes.probs;
~verse.notes.durations;
~verse.notes.freqs;
~verse.notes.init;
~verse.notes.waits;


(
~start = {

	var num = 100,timeNow;
	t = TempoClock.default.tempo = num / 60;

	Routine.run({
		s.sync;
		timeNow = TempoClock.default.beats;

		t.schedAbs(timeNow + 00,{ // 00 = Time in beats
			(
				//~chime.transport.play;~chimeLong.transport.play;
				~verse.transport.play;
				//~bassdrum.transport.play;~snare.transport.play;
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