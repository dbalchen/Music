~channel2 = {arg num, vel = 1;
	var ret;
	ret = ~midiStrings.value(~string3_firmus,num,2);
	ret;
};

~channel3 = {arg num, vel = 1;
	var ret;
	ret = ~midiStrings.value(~string2_firmus,num,3);
	ret;
};

~channel4 = {arg num, vel = 1;
	var ret;

	ret  = Synth("myADSR",addAction: \addToHead);
	ret.set(\out,~adsrOut);
	~myadsr.setADSR(ret);
	ret.set(\gate,1);
	ret;

};

~channel5 = {arg num, vel = 1;
	var ret;
	ret = Synth("Sine",addAction: \addToHead);
	ret.set(\amp,~sinedrum.amp);
	ret.set(\out,~sine1Out);
	ret.set(\gate,1);
	ret;

};

~channel6 = {arg num, vel = 1;
	var ret;
	ret = ~midiStrings.value(~string1_firmus,num-24,6);
	//			ret = ~midiStrings.value(~string1_firmus,num,6);
	ret;
};

~channel7 = {arg num, vel = 1;
	var ret;

	ret = Synth("tbell",addAction: \addToHead);
	ret.set(\freq,~pitch);
	ret.set(\decayscale,~dcs);
	ret.set(\fscale,~fscale);
	ret.set(\release,~release);
	ret.set(\attack,~attack);
	ret.set(\amp,~amp);
	ret.set(\out,~bellOut);
	ret.set(\gate,1);
	ret;
};

~channel8 = {arg num, vel = 1;
	var ret;

	~noise1.set(\freq,num.midicps);
	~pulse1.set(\freq,(num-36).midicps);

	~pitch = (num - 36).midicps;

	ret  = Synth("myASR",addAction: \addToHead);
	ret.set(\out,~asrOut);
	ret.set(\release,0.02);
	ret.set(\attack,16);
	ret.set(\cutoff,10000);
	ret.set(\aoc,1.0);
	ret.set(\gate,1);
	ret;
};

~channel9 = {arg num, vel = 1;
	var ret;

	ret  = Synth("env0",target: ~nGroup,addAction: \addToHead);
	ret.set(\out,~envout);
	ret  = Synth("env1",target: ~nGroup,addAction: \addToHead);
	ret.set(\out,~envout1);

	~nGroup.set(\gate,1);
	~nGroup;

};