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
		"/home/dbalchen/Music/JamOn6/include/Events/stringBeats.sc".load;
		"/home/dbalchen/Music/JamOn6/include/Events/bellBeats.sc".load;
		"/home/dbalchen/Music/JamOn6/include/Synths/eStrings.sc".load;
		"/home/dbalchen/Music/JamOn6/include/Synths/strings.sc".load;
		"/home/dbalchen/Music/JamOn6/include/Patches/initPatch.sc".load;
		"/home/dbalchen/Music/JamOn6/include/Patches/patchFunctions.sc".load;
		"/home/dbalchen/Music/JamOn6/include/Patches/midiDefs.sc".load;

	)

};
)

~startup.value;
~startTimer.value(120);

~rp = {~midistring1_firmus.value();0.1.wait; ~midistring2_firmus.value();~midistring3_firmus.value();0.1.wait;~midicf_clock.value();~midiClock.value();};

~rp = {~midicf_clock.value(); ~midiClock.value();};




~midistring1_firmus.value();

(
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

~noiseSweep.value(1,-0.25,((16*4)/2), ((4*4)/2), 0.25);
~noiseSweep2.value(1,-1,((16*4)/2),((8*4)/2), 0.75);

~noiseSweep.value(-0.25,1,((16*4)/2), ((4*4)/2), 0.25);
~noiseSweep2.value(-1,1,((16*4)/2),((4*4)/2), 0.75);


~pulseSweep.value(0.85,-1,((16*4)/2),((8*4)/2), 0.55);
~pulseSweep.value(-1,0.85,((16*4)/2),((8*4)/2), 0.55);


TempoClock.default.tempo = 120 / 60;

(
~start = {

	var num = 120,timeNow;
	t = TempoClock.default.tempo = num / 60;
	Routine.run({
		s.sync;
		timeNow = TempoClock.default.beats;




	}); // End of Routine

}; //End of Start

)

