// =====================================================================
*  SuperCollider Song Template
// =====================================================================

//*

** The basics

//*/

Server.default.makeGui;

"/home/dbalchen/Music/song7/include/setup.sc".load;
"/home/dbalchen/Music/song7/include/classes/PitchClass.sc".load;

/*
** The Song
*/

// Set Tempo
t = TempoClock.default.tempo = 120/60;

// Define a track and notes for that track
~mytrack = Track.new(~out0,0);

~mynotes = Notes.new;

// Define the Tonerow and create a table of all possible pitches.
~tonerow = [0,4,7,9,11];

~pitchR0 = ~createScale.value(~tonerow);

// Take a subset of the possible pitches
~p0 = ~pitchR0.select({ arg item, i; (item >= 48) && (item < 77) });

// Define a 5 over 8 rhythm
~r0 = Bjorklund(5, 8);

// Fill that rhythm
~f0 = ~r0*60;

~f0 = ~r0.collect({ arg item, i; item; item*~p0.choose});

~pc0 = ~pcset.value(~f0);

~mynotes.freqs = ~f0;

~f0I = ~pitchClassT.value(~f0,-12);
~mynotes.freqs = ~f0I;

~pc0I = ~pcset(~f0I);

~f0R =  ~pitchMap.value(~f0,~pc0,~pc0.reverse);
~mynotes.freqs = ~f0R;

~f0IR =  ~pitchMap.value(~f0I,~pc0I,~pc0I.reverse);
~mynotes.freqs = ~f0IR;

~mytrack.notes = ~mynotes.init;

/*
~mytrack.noteON = ~fmBasic;

~mytrack.noteON = ~channel0;

*/

/*
** Play Track
*/

~startTimer.value(120);

~rp = {~mytrack.transport.play;};

~mytrack.transport.mute;

~mytrack.transport.unmute;

/*

* Scratch Area

*/


/*

* Song routine Example

*/


~rp = {}; // Example

(
~start = {

	var num = 60,timeNow;
	t = TempoClock.default.tempo = num / 60;

	Routine.run({
		s.sync;
		timeNow = TempoClock.default.beats;

		t.schedAbs(timeNow + 00,{ // 00 = Time in beats
			(
				// If yes put stuff Here
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


~rp = {{" Hello ".post;}};
