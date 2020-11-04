/*
~string_low_vca_envelope.gui;
~string_low_vcf_envelope.gui;
*/


~string_low_synth = Synth("mono_eStrings",addAction: \addToTail);
~string_low_synth.set(\cutoff,15950);

~string_low_vca_control_in = Bus.control(s, 1);
~string_low_vcf_control_in = Bus.control(s, 1);
~string_low_synth.set(\cutoff,9950);

~string_low_synth.set(\fenvIn,~string_low_vcf_control_in);
~string_low_synth.set(\vcaIn,~string_low_vca_control_in);


~string_low_vca_envelope = MyADSR.new;
~string_low_vcf_envelope = MyADSR.new;
~string_low_vca_envelope.init;
~string_low_vcf_envelope.init;

~lowStringsEnvInit = {
	~string_low_vca_envelope.attack = 1.5;
	~string_low_vca_envelope.decay = 1.5;
	~string_low_vca_envelope.sustain = 0.1;
	~string_low_vca_envelope.release = 1.0;

	~string_low_vcf_envelope.attack = 1.8;
	~string_low_vcf_envelope.decay = 2.8;
	~string_low_vcf_envelope.sustain = 0.03;
	~string_low_vcf_envelope.release = 1.4;
};

~lowStringsEnvInit.value();

~lowStringsEnvRest = {
	~string_low_vca_envelope.attack = 1.0;
	~string_low_vca_envelope.decay = 3.0;
	~string_low_vca_envelope.sustain = 0.4;
	~string_low_vca_envelope.release = 1;

	~string_low_vcf_envelope.attack = 1.5;
	~string_low_vcf_envelope.decay = 2.5;
	~string_low_vcf_envelope.sustain = 0.2;
	~string_low_vcf_envelope.release = 1;
};




