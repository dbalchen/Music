~envout0 = Bus.audio(s,1);
~envout1 = Bus.audio(s,1);
~envout2 = Bus.audio(s,1);

// Start
~mixout = Bus.audio(s,1);
~mixout1 = Bus.audio(s,1);
~adsrout = Bus.audio(s,1);

~myadsr = MyADSR.new;
~myadsr.attack = 2.0;
~myadsr.decay = 1.0;
~myadsr.sustain = 0.5;
~myadsr.release = 0.09;

~mixer = Synth("two2one",addAction: \addToTail);
~mixer.set(\in0,~envout0);
~mixer.set(\in1,~adsrout);
~mixer.set(\out,~mixout);
~mixer.set(\bal,0.70);

~mixergui = SimpleMix.new;
~mixergui.mixer = ~mixer;

// End

~noise =  Synth("noisekick",addAction: \addToTail);
~noise.set(\e0In,~envout0);
~noise.set(\e1In,~envout1);
~noise.set(\out,~bassDrum.out);

~pulseSaw =  Synth("sawPulsekick",addAction: \addToTail);
~pulseSaw.set(\e0In,~mixout);
~pulseSaw.set(\e1In,~envout2);
~pulseSaw.set(\ss,~wavebuff);
~pulseSaw.set(\windex, ~windex);
~pulseSaw.set(\dist, 0.0);
~pulseSaw.set(\gain, 0.75);
~pulseSaw.set(\idx, 0.25);
~pulseSaw.set(\amp, 0.5);
//~pulseSaw.set(\out,~bassDrum.out + 2);

~pulseSaw2 =  Synth("sawPulsekick",addAction: \addToTail);
~pulseSaw2.set(\e0In,~envout0);
~pulseSaw2.set(\e1In,~envout1);
~pulseSaw2.set(\ss,~wavebuff);
~pulseSaw2.set(\windex, ~windex);
~pulseSaw2.set(\fmod, 1.0);
~pulseSaw2.set(\dist, 0.3);
~pulseSaw2.set(\gain, 0.1);
~pulseSaw2.set(\idx, 0.35);
~pulseSaw2.set(\amp, 0.15);
//~pulseSaw2.set(\out,~bassDrum.out + 2);

~sine =  Synth("sinekick",addAction: \addToTail);
~sine.set(\e0In,~envout0);
~sine.set(\e1In,~envout1);
~sine.set(\amp,0.25);
//~sine.set(\out,~bassDrum.out + 4);

~bassDrumPulseSaw = {arg bal = 0;

	if(bal == 0, {~pulseSaw.set(\amp, 0);~pulseSaw2.set(\amp, 2.2);},
		{~pulseSaw.set(\amp, 1);~pulseSaw2.set(\amp, 1.2);});

};

~bassDrumPulseSaw.value(0);

