SynthDef(\stringLow, {arg num = 60,gate = 1;
	var env = Env.asr(0,1,0);
	var trig = EnvGen.kr(env, gate,doneAction:2);
	SendReply.kr(trig, '/stringLow', num);
}).add;

OSCdef(\stringLow, { |m|

	var num = m[3].asInteger;

	~string_low_synth.set(\freq,num.midicps);
	~string_low_synth.set(\lagtime,	~lowStrings.notes.lag);
	~string_low_synth.set(\amp,	~lowStrings.amp);
	~string_low_synth.set(\balance,	~lowStrings.balance);
	~string_low_synth.set(\release,~string_low_vca_envelope.release);
	~string_low_synth.set(\out,	~lowStrings.out);


	~stringLow_fenv = Synth("myADSRk",addAction: \addToHead);
	~stringLow_fenv.set(\out,~string_low_vcf_control_in);
	~string_low_vcf_envelope.setADSR(~stringLow_fenv);

	~stringLow_env  = Synth("myADSRk",addAction: \addToHead);
	~string_low_vca_envelope.setADSR(~stringLow_env);
	~stringLow_env.set(\out,~string_low_vca_control_in);

	~stringLow_env.set(\gate,1);
	~stringLow_fenv.set(\gate,1);
	~string_low_synth.set(\gate,1);


}, '/stringLow');



~channel0 = {arg num, vel = 1;
	var ret;
	num.postln;

	ret = Synth("stringLow");
	ret.set(\num,num);
	ret.set(\gate,1);

	ret;
};

~channel0off = {arg num, vel = 1;
	var ret = nil;

	~stringLow_env.set(\gate,0);
	~stringLow_fenv.set(\gate,0);
	~string_low_synth.set(\gate,0);
	ret;
};


~channel1 = {arg num, vel = 1;
	var ret;
	num.postln;

	ret = Synth("Esampler");
	ret.set(\gate,0);

	~celloTemplate.value(ret,num,~cellosounds);
	ret.set(\amp,~cello.amp);
	ret.set(\balance,~cello.balance);
	ret.set(\out,~cello.out);
	~string_cello_vcf_envelope.setfADSR(ret);
	~string_cello_vca_envelope.setADSR(ret);
	ret.set(\gate,1);
	ret;
};
/*
~channel2 = {arg num, vel = 1;
var ret;
num.postln;

ret = Synth("Esampler");
ret.set(\gate,0);

~violaTemplate.value(ret,num,~violasounds);
ret.set(\amp,~viola.amp);
ret.set(\balance,~viola.balance);
ret.set(\out,~viola.out);
~string_viola_vcf_envelope.setfADSR(ret);
~string_viola_vca_envelope.setADSR(ret);
ret.set(\gate,1);
ret;
};
*/


~channel2 = {arg num, vel = 1;
	var ret;
	num.postln;

	ret = Synth("eViola");
	ret.set(\num,num);
	ret.set(\gate,1);

	ret;
};

~channel2off = {arg num, vel = 1;
	var ret = nil;

	~string_viola_env.set(\gate,0);
	~string_viola_fenv.set(\gate,0);
	~string_viola.set(\gate,0);
	ret;
};




/*
~channel3 = {arg num, vel = 1;
var ret;
num.postln;
ret = Synth("Esampler");
ret.set(\gate,0);

~violinTemplate.value(ret,num,~violinsounds);
ret.set(\amp,~violin.amp);
ret.set(\out,~violin.out);
ret.set(\balance,~violin.balance);
ret.set(\cutoff,4950);
~string_violin_vcf_envelope.setfADSR(ret);
~string_violin_vca_envelope.setADSR(ret);

ret.set(\gate,1);
ret;
};
*/


~channel3 = {arg num, vel = 1;
	var ret;
	num.postln;

	ret = Synth("eViolin");
	ret.set(\num,num);
	ret.set(\gate,1);
	~string_violin.set(\gate,0);
	ret;
};

~channel3off = {arg num, vel = 1;
	var ret = nil;

	~string_violin_env.set(\gate,0);
	~string_violin_fenv.set(\gate,0);
	~string_violin.set(\gate,0);
	ret;
};



~channel4 = {arg num, vel = 1;
	var ret;
	num.postln;
	ret = Synth("eStrings");
	ret.set(\gate,0);
	ret.set(\freq,num.midicps);
	ret.set(\amp,~viola2.amp);
	ret.set(\out,~viola2.out);
	ret.set(\balance,~viola2.balance);
	~string_viola_vcf_envelope.setfADSR(ret);
	~string_viola_vca_envelope.setADSR(ret);
	ret.set(\gate,1);
	ret;
};


~channel5 = {arg num, vel = 1;
	var ret;
	num.postln;

	ret = Synth("eStrings16");
	ret.set(\gate,0);
	ret.set(\freq,num.midicps);
	ret.set(\amp,~violin2.amp);
	ret.set(\out,~violin2.out);
	ret.set(\balance,~violin2.balance);
	ret.set(\cutoff,15000);
	ret.set(\hpf,328);
	~string_violin_vcf_envelope.setfADSR(ret);
	~string_violin_vca_envelope.setADSR(ret);

	ret.set(\gate,1);
	ret;
};

SynthDef(\kickClock, {arg num = 60,gate = 0;
	var env = Env.asr(0,1,0);
	var trig = EnvGen.kr(env, gate,doneAction:2);
	SendReply.kr(trig, '/kickClock', num);
}).add;

OSCdef(\kickClock, { |m|
	var aa,aa1;

	aa  = Synth("env0",addAction: \addToHead);
	aa.set(\out,~envout0);
	aa.set(\gate,1);

	aa1 = Synth("env1",addAction: \addToHead);
	aa1.set(\out,~envout1);
	aa1.set(\gate,1);

}, '/kickClock');


~channel9 = {arg num, vel = 1;
	var ret;

	ret = Synth(\kickClock);
	ret.set(\gate,1);

	ret;

};


~channel10 = {arg num, vel = 1;
	var ret;

	num = num.midicps;
	~pulseSaw.set(\freq, num);
	~pulseSaw.set(\lagLev,0.5);
	~pulseSaw2.set(\freq, num);
	~noise.set(\freq,num/2);
	~sine.set(\freq, num/2);

	~ret0 = Synth("env2",addAction: \addToHead);
	~ret0.set(\out,~envout2);
	~ret0.set(\gate,1);

	ret  = Synth("myADSR",addAction: \addToHead);
	ret.set(\out,~adsrout);
	~myadsr.setADSR(ret);
	ret.set(\gate,1);
	ret;
};

~channel10off = {arg num, vel = 1;
	var ret = nil;
	~ret0.set(\gate,0);
};


~turndown1 = {
	arg vol,cc,chan,src;
	vol = vol/127;
	vol.postln;

	if((chan == 0), {

		~lowStrings.amp = vol;
	});

	if((chan == 1), {
		~cello.amp = vol;
	});

	if((chan == 2), {

		~viola.amp = vol;

	});

	if((chan == 3), {

		~violin.amp = vol;
	});

	if((chan == 4), {


	});

	if((chan == 5), {


	});

	if((chan == 6), {


	});

	if((chan == 7), {


	});

	if((chan == 8), {


	});
	if((chan == 9), {


	});

	if((chan == 10), {

	});

	if((chan == 11), {


	});

	if((chan == 12), {

	});

	if((chan == 13), {


	});

	if((chan == 14), {


	});

	if((chan == 15), {


	});


};


~volCC.free;
~volCC = MIDIdef.cc(\volume, ~turndown1, 7);

~pan1 = {
	arg vol,cc,chan,src;
	vol = (2*(vol/127))-1;

	vol.postln;

	if((chan == 0), {

		~lowStrings.balance = vol;

	});

	if((chan == 1), {

		~cello.balance = vol;

	});

	if((chan == 2), {

		~viola.balance = vol;

	});

	if((chan == 3), {

		~violin.balance = vol;

	});

	if((chan == 4), {

	});

	if((chan == 5), {


	});

	if((chan == 6), {


	});

	if((chan == 7), {


	});

	if((chan == 8), {


	});
	if((chan == 9), {


	});

	if((chan == 10), {
	});

	if((chan == 11), {


	});

	if((chan == 12), {

	});

	if((chan == 13), {


	});

	if((chan == 14), {


	});

	if((chan == 15), {


	});


};

~ballCC.free;
~ballCC = MIDIdef.cc(\pan, ~pan1,10); // match cc 1


