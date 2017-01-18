Help.gui
Quarks.gui
GUI.qt

s.boot;
s.plotTree;
s.meter;
s.quit;
FreqScope.new(400, 200, 0, server: s);
Server.default.makeGui

(
o = Server.local.options;
o.numOutputBusChannels = 24; // The next time it boots, this will take effect
o.memSize = 2097152;
)

"/home/dbalchen/Music/setup.sc".load;

(
~startup = {

	(
		"/home/dbalchen/Music/JamOn6/include/Synths/bdSynth.sc".load;
		"/home/dbalchen/Music/JamOn6/include/Synths/envelopes.sc".load;
		"/home/dbalchen/Music/JamOn6/include/Synths/oscillator.sc".load;
		"/home/dbalchen/Music/JamOn6/include/Synths/bell.sc".load;
		"/home/dbalchen/Music/JamOn6/include/Events/beats.sc".load;
		"/home/dbalchen/Music/JamOn6/include/Events/celloBeats.sc".load;
		"/home/dbalchen/Music/JamOn6/include/Events/violaBeats.sc".load;
		"/home/dbalchen/Music/JamOn6/include/Events/violinBeats.sc".load;
		"/home/dbalchen/Music/JamOn6/include/Events/bellBeats.sc".load;
		"/home/dbalchen/Music/JamOn6/include/Synths/eStrings.sc".load;
		"/home/dbalchen/Music/JamOn6/include/Synths/strings.sc".load;
		"/home/dbalchen/Music/JamOn6/include/Patches/initPatch.sc".load;
		"/home/dbalchen/Music/JamOn6/include/Patches/patchFunctions.sc".load;
		"/home/dbalchen/Music/JamOn6/include/Patches/midiDefs.sc".load;
		/*
		~noise.set(\out,2);
		~vca1.set(\out,6);
		~pulse.set(\out,4);
		~string2_firmus.out = 8;
		~string2_firmusB.out = 8;
		~string3_firmus.out = 10;
		~string3_firmusB.out = 10;
		*/
	)
	//
};
)

~startup.value;
~startTimer.value(120);

~rp = {~cello0.value();~midistring1_firmus.value();};
~cello0.value();

~rp = {~cello2.value();~midistring1_firmus.value();};
~cello2.value();

~rp = {~cello3.value();~midistring1_firmus.value();};
~cello3.value();

~rp = {~cello4.value();~midistring1_firmus.value();};
~cello4.value();


~rp = {~viola0.value();~midistring2_firmus.value();};
~viola0.value();

~rp = {~viola1.value();~midistring2_firmus.value();};
~viola1.value();

~rp = {~viola2.value();~midistring2_firmus.value();};
~viola2.value();~cf_clock2.value();

~rp = {~viola3.value();~midistring2_firmus.value();};
~viola3.value();

~rp = {~viola4.value();~midistring2_firmus.value();};
~viola4.value();


~rp = {~violin1.value();~midistring3_firmus.value();};
~violin1.value();

~rp = {~violin2.value();~midistring3_firmus.value();};
~violin2.value();

~rp = {~violin3.value();~midistring3_firmus.value();};
~violin3.value();

~rp = {~violin4.value();~midistring3_firmus.value();};
~violin4.value();


~rp = {~cello1.value;~midistring1_firmus.value();~viola1.value();~midistring2_firmus.value();~violin1.value();~midistring3_firmus.value();};

~rp = {~cello2.value;~midistring1_firmus.value();~viola2.value();~midistring2_firmus.value();~violin2.value();~midistring3_firmus.value();};

~rp = {~cello3.value;~midistring1_firmus.value();~viola3.value();~midistring2_firmus.value();~violin3.value();~midistring3_firmus.value();};

~rp = {~cello4.value;~midistring1_firmus.value();~viola4.value();~midistring2_firmus.value();~violin4.value();~midistring3_firmus.value();};


~cello0.value();~viola0.value();~cf_clock0.value();~string3_firmus.probs= ~string3_firmus.probs * 0;
~cello0.value();~viola1.value();~violin1.value();~cf_clock3.value();
~cello2.value();~violin2.value();~viola2.value();~cf_clock2.value();
~cello3.value();~violin3.value();~viola3.value();~cf_clock3.value();
~cello4.value();~violin4.value();~viola4.value();~cf_clock4.value();

~noiseSweep.value(1,-0.25,((8*4)/2), ((4*4)/2), 0.25);
~noiseSweep2.value(1,-1,((8*4)/2),((2*4)/2), 0.75);

~noiseSweep.value(-0.25,1,((16*4)./2), ((4*4)/2), 0.25);
~noiseSweep2.value(-1,1,((16*4)/2),((4*4)/2), 0.75);

~pulseSweep.value(0.85,-1,((16*4)/2),((4*4)/2), 1.0);
~pulseSweep.value(-1,0.85,((16*4)/2),((2*4)/2), 0.95);


TempoClock.default.tempo = 120 / 60;

~rp = {~start.value();};
~startTimer.value(120);
(
~start = {

	var num = 120,timeNow;
	t = TempoClock.default.tempo = num / 60;
	Routine.run({
		s.sync;
		timeNow = TempoClock.default.beats;

		~mixer1.set(\bal,1);
		~mixer2.set(\bal,1);
		~circleExt4.set(\gate,0);
		~pulse1.set(\bamp,998);
		~pulse1.set(\amp,1.2);
		~circleExt5.set(\gate,0);
		~vca1.set(\bamp,998);
		~vca1.set(\amp,0.4);
		~mixer3.set(\bal,0.85);
		~mixer4.set(\bal,1);
		~cello0.value();
		~viola0.value();
		~violin1.value();

		t.schedAbs(timeNow + ((8*4) - 0.1),{ // 00 = Time in beats
			(

				~midistring1_firmus.value();

		);};); // End of t.schedAbs

		t.schedAbs(timeNow + ((8*4)),{ // 00 = Time in beats
			(

				~midistring2_firmus.value();
		);};); // End of t.schedAbs

		t.schedAbs(timeNow + ((8*4) + 0.1),{ // 00 = Time in beats
			(
				~midicf_clock.value();
				~midiClock.value();

		);};); // End of t.schedAbs

		t.schedAbs(timeNow + (16*4),{ // 00 = Time in beats
			(

				~viola1.value();
				~noiseSweep.value(1,-0.25,((8*4)/2), ((4*4)/2), 0.25);
				~noiseSweep2.value(1,-1,((8*4)/2),((2*4)/2), 0.75);

		);};); // End of t.schedAbs

		t.schedAbs(timeNow + (24*4),{ // 00 = Time in beats
			(
				~midistring3_firmus.value();

		);};); // End of t.schedAbs

		t.schedAbs(timeNow + ((40*4)-1),{ // 00 = Time in beats
			(
				~cello2.value();~viola2.value();~violin2.value();~cf_clock2.value();

		);};); // End of t.schedAbs


		t.schedAbs(timeNow + ((56*4)-1),{ // 00 = Time in beats
			(
				~cello3.value();~viola3.value();~violin3.value();~cf_clock3.value();

		);};); // End of t.schedAbs

		t.schedAbs(timeNow + ((56*4)),{ // 00 = Time in beats
			(
				//	~pulseSweep.value(0.85,-1,((16*4)/2),((2*4)/2), 0.95);

				t.schedAbs(timeNow + ((72*4)-1),{ // 00 = Time in beats
					(
						~cello4.value();~viola4.value();~violin4.value();~cf_clock4.value();

				);};); // End of t.schedAbs

		);};); // End of t.schedAbs

	}); // End of Routine

}; //End of Start

)
