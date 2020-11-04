/*
~string_viola_vca_envelope.gui;
~string_viola_vcf_envelope.gui;
*/

~viola = MyTrack.new(~synth1,2);
~viola2 = MyTrack.new(~synth1,4);
~viola.amp = 0.10;
~viola2.amp = 0.05;
~viola.notes.lag = 0.09;


~string_viola = Synth("mono_eStrings",addAction: \addToTail);
~string_viola.set(\cutoff,10038);
~string_viola.set(\hpf,64);
~string_viola.set(\aoc,0.4);
~string_viola_vca_control_in = Bus.control(s, 1);
~string_viola_vcf_control_in = Bus.control(s, 1);

~string_viola.set(\fenvIn,~string_viola_vcf_control_in);
~string_viola.set(\vcaIn,~string_viola_vca_control_in);

~string_viola_vca_envelope = MyADSR.new;
~string_viola_vca_envelope.attack = 0.5;
~string_viola_vca_envelope.decay = 1.5;
~string_viola_vca_envelope.sustain = 0.7;
~string_viola_vca_envelope.release = 0.2;

~string_viola_vcf_envelope = MyADSR.new;
~string_viola_vcf_envelope.attack = 0.5;
~string_viola_vcf_envelope.decay = 2.5;
~string_viola_vcf_envelope.sustain = 0.7;
~string_viola_vcf_envelope.release = 0.4;


~string_viola_vca_envelope.init;
~string_viola_vcf_envelope.init;


SynthDef(\eViola, {arg num = 60,gate = 1;
	var env = Env.asr(0,1,0);
	var trig = EnvGen.kr(env, gate,doneAction:2);
	SendReply.kr(trig, '/eViola', num);
}).add;

OSCdef(\eViola, { |m|

	var num = m[3].asInteger;

	"woot".postln;
	~string_viola.set(\freq,num.midicps);
	~string_viola.set(\lagtime,	~viola.notes.lag);
	~string_viola.set(\amp,	~viola.amp);
	~string_viola.set(\balance,	~viola.balance);
	~string_viola.set(\release,~string_viola_vca_envelope.release);
	~string_viola.set(\out,	~viola.out);


	~string_viola_fenv = Synth("myADSRk",addAction: \addToHead);
	~string_viola_fenv.set(\out,~string_viola_vcf_control_in);
	~string_viola_vcf_envelope.setADSR(~string_viola_fenv);

	~string_viola_env  = Synth("myADSRk",addAction: \addToHead);
	~string_viola_vca_envelope.setADSR(~string_viola_env);
	~string_viola_env.set(\out,~string_viola_vca_control_in);

	~string_viola_env.set(\gate,1);
	~string_viola_fenv.set(\gate,1);
	~string_viola.set(\gate,1);


}, '/eViola');
















































~string_viola_vca_envelope.attack = 0.5;
~string_viola_vca_envelope.decay = 2.5;
~string_viola_vca_envelope.sustain = 0.4;
~string_viola_vca_envelope.release = 0.5;

~string_viola_vcf_envelope.attack = 0.5;
~string_viola_vcf_envelope.decay = 2.5;
~string_viola_vcf_envelope.sustain = 0.2;
~string_viola_vcf_envelope.release = 0.5;