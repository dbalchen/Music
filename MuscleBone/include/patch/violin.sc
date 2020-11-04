~violin = MyTrack.new(~synth1,3);

~violin2 = MyTrack.new(~synth1,5);
~violin.amp = 0.1;
~violin2.amp = 0.15

~string_violin = Synth("mono_eStrings",addAction: \addToTail);
~string_violin.set(\cutoff,12000);
~string_violin.set(\hpf,128);
~string_violin.set(\aoc,0.4);
~string_violin_vca_control_in = Bus.control(s, 1);
~string_violin_vcf_control_in = Bus.control(s, 1);


~string_violin.set(\fenvIn,~string_violin_vcf_control_in);
~string_violin.set(\vcaIn,~string_violin_vca_control_in);


~string_violin_vca_envelope = MyADSR.new;
~string_violin_vca_envelope.init;
~string_violin_vca_envelope.attack = 0.50;
~string_violin_vca_envelope.decay = 1.5;
~string_violin_vca_envelope.sustain = 0.35;
~string_violin_vca_envelope.release = 0.125;


~string_violin_vcf_envelope = MyADSR.new;
~string_violin_vcf_envelope.init;
~string_violin_vcf_envelope.attack = 0.5;
~string_violin_vcf_envelope.decay = 2.5;
~string_violin_vcf_envelope.sustain = 0.7;
~string_violin_vcf_envelope.release = 0.35;


SynthDef(\eViolin, {arg num = 60,gate = 1;
	var env = Env.asr(0,1,0);
	var trig = EnvGen.kr(env, gate,doneAction:2);
	SendReply.kr(trig, '/eViolin', num);
}).add;

OSCdef(\eViolin, { |m|

	var num = m[3].asInteger;
	~string_violin.set(\freq,num.midicps);
	~string_violin.set(\lagtime,	~violin.notes.lag);
	~string_violin.set(\amp,	~violin.amp);
	~string_violin.set(\balance,	~violin.balance);
	~string_violin.set(\release,~string_violin_vca_envelope.release);
	~string_violin.set(\out,	~violin.out);


	~string_violin_fenv = Synth("myADSRk",addAction: \addToHead);
	~string_violin_fenv.set(\out,~string_violin_vcf_control_in);
	~string_violin_vcf_envelope.setADSR(~string_violin_fenv);

	~string_violin_env  = Synth("myADSRk",addAction: \addToHead);
	~string_violin_vca_envelope.setADSR(~string_violin_env);
	~string_violin_env.set(\out,~string_violin_vca_control_in);

	~string_violin_env.set(\gate,1);
	~string_violin_fenv.set(\gate,1);
	~string_violin.set(\gate,1);


}, '/eViolin');