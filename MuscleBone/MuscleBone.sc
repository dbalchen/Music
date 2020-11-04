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
)


"/home/dbalchen/Music/setup.sc".load;




(

"/home/dbalchen/Music/MuscleBone/include/synths/envelopes.sc".load;
"/home/dbalchen/Music/MuscleBone/include/synths/kick.sc".load;
"/home/dbalchen/Music/MuscleBone/include/synths/eStrings.sc".load;
//"/home/dbalchen/Music/MuscleBone/include/synths/eSampler.sc".load;

"/home/dbalchen/Music/MuscleBone/include/events/bassDrum.sc".load;
~bassDrum.out = 0;
"/home/dbalchen/Music/MuscleBone/include/patch/bassDrum.sc".load;


"/home/dbalchen/Music/MuscleBone/include/events/LowStrings.sc".load;
"/home/dbalchen/Music/MuscleBone/include/patch/LowStrings.scd".load;

"/home/dbalchen/Music/MuscleBone/include/patch/viola.sc".load;
"/home/dbalchen/Music/MuscleBone/include/events/viola.sc".load;
//"/home/dbalchen/Music/MuscleBone/include/patch/strings.sc".load;

"/home/dbalchen/Music/MuscleBone/include/patch/violin.sc".load;
"/home/dbalchen/Music/MuscleBone/include/events/violin.sc".load;

~lowStrings.out = 0;
~viola.out = 0;
~viola2.out = 0;
~violin.out = 0;
~violin2.out = 0;


"/home/dbalchen/Music/MuscleBone/include/midiDefs.sc".load;


)

~startTimer.value(120);

~bassDrum.transport.mute;~bassDrumNotes.transport.mute;


~lowStrings.transport.mute;
~lowStrings.transport.unmute;

~viola.transport.mute;~viola2.transport.mute;
~viola.transport.unmute;~viola2.transport.unmute;

~violin2.transport.mute;~violin.transport.mute;
~violin2.transport.unmute;~violin.transport.unmute;
~bassDrum.transport.mute;
~rp = {~lowStrings.transport.play;};
~rp = {~viola2.transport.play;~viola.transport.play;};
~rp = {~violin.transport.play;~violin2.transport.play;};


~rp = {~violin.transport.play;~viola.transport.play;~lowStrings.transport.play;};



~rp = {~initialBassDrum.value;~bassDrum.transport.play;~bassDrumNotes.transport.play;};



// Example




(
~start = {

	var num = 120,timeNow;
	t = TempoClock.default.tempo = num / 60;

	Routine.run({
		s.sync;
		timeNow = TempoClock.default.beats;

		t.schedAbs(timeNow + 00,{ // 00 = Time in beats
			(
				~initialBassDrum.value;~bassDrum.transport.play;
				~bassDrumNotes.transport.play;~lowStrings.transport.play;
				~bassDrumPulseSaw.value(1);
			);


		};	 // End of if statement

		); // End of t.schedAbs


		t.schedAbs((timeNow + (4*8) - 0.01),{ // 00 = Time in beats
			(

				~bassDrum2.value;
				~lowStrings2.value;
			);

			(
				// If No put stuff here otherwise nil
				nil
			);
		};	 // End of if statement

		); // End of t.schedAbs


		t.schedAbs((timeNow + (4*16) - 0.01),{ // 00 = Time in beats
			(
				~lowStrings3.value();
				~lowStringsEnvRest.value;
				~bassDrum3.value;
				~viola2.transport.play;~viola.transport.play;
				~violin2.transport.play;~violin.transport.play;
			);

			(
				// If No put stuff here otherwise nil
				nil
			);
		};	 // End of if statement


		); // End of t.schedAbs



		t.schedAbs((timeNow + (4*26)) - 0.01,{ // 00 = Time in beats
			(
				~bassDrum4.value;
				~lowStrings4.value();

				~viola4.value;
				~violin4.value;
				//				~violaEnv2.value;
			);

			(
				// If No put stuff here otherwise nil
				nil
			);
		};	 // End of if statement


		); // End of t.schedAbs
		//Add more


		t.schedAbs((timeNow + (4*34)) - 0.01,{ // 00 = Time in beats
			(
				~bassDrum5.value;
				~lowStrings5.value();
				~viola5.value;
				~violin5.value;

			);

			(
				// If No put stuff here otherwise nil
				nil
			);
		};	 // End of if statement
		); // End of t.schedAbs



		t.schedAbs((timeNow + (4*42)) - 0.01,{ // 00 = Time in beats
			(
				~bassDrum6.value;
				~lowStrings6.value();
				~viola6.value;
				~violin6.value;
			);

			(
				// If No put stuff here otherwise nil
				nil
			);
		};	 // End of if statement
		); // End of t.schedAbs


		t.schedAbs((timeNow + (4*50)) - 0.01,{ // 00 = Time in beats
			(
				~bassDrum7.value;
				~lowStrings7.value();
				~viola7.value;
				~violin7.value;

			);

			(
				// If No put stuff here otherwise nil
				nil
			);
		};	 // End of if statement
		); // End of t.schedAbs


		t.schedAbs((timeNow + (4*58)) - 0.01,{ // 00 = Time in beats
			(
				~bassDrum8.value;
				~lowStrings8.value();
				~viola8.value;
				~violin8.value;
			);

			(
				// If No put stuff here otherwise nil
				nil
			);
		};	 // End of if statement
		); // End of t.schedAbs



		t.schedAbs((timeNow + (4*66)) - 0.1,{ // 00 = Time in beats
			(
				~bassDrum9.value;
				~lowStrings9.value();
				~viola9.value;
				~violin9.value;
			);

			(
				// If No put stuff here otherwise nil
				nil
			);
		};	 // End of if statement
		); // End of t.schedAbs


		t.schedAbs((timeNow + (4*74)) - 0.01,{ // 00 = Time in beats
			(
				~bassDrum10.value;
				~lowStrings10.value();
				~viola10.value;
				~violin10.value;
			);

			(
				// If No put stuff here otherwise nil
				nil
			);
		};	 // End of if statement
		); // End of t.schedAbs


		t.schedAbs((timeNow + (4*82)) - 0.01,{ // 00 = Time in beats
			(
				~bassDrum11.value;
				~lowStrings11.value();
				~viola11.value;
				~violin11.value;
			);

			(
				// If No put stuff here otherwise nil
				nil
			);
		};	 // End of if statement
		); // End of t.schedAbs


		t.schedAbs((timeNow + (4*90)) - 0.01,{ // 00 = Time in beats
			(
				~bassDrum12.value;
				~lowStrings12.value();
				~viola12.value;
				~violin12.value;
			);

			(
				// If No put stuff here otherwise nil
				nil
			);
		};	 // End of if statement
		); // End of t.schedAbs



	}); // End of Routine

}; //End of Start

)

~startTimer.value(120);
~rp = {~start.value;};
