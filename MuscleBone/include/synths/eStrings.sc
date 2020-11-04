// =====================================================================
// eStrings
// =====================================================================

SynthDef("eStrings",
	{
		arg out = 0, freq = 110, gate = 0, amp = 0.5, da = 2,hpf = 120,
		attack = 0.5, decay = 2.0, sustain = 0.6, release = 0.6, fattack = 0.5, fsustain = 0.8,
		frelease = 0.6, aoc = 0.6, cutoff = 5200.00, bend = 0, spread = 1, balance = 0;

		var sig, env, fenv, env2;

		env  = Env.adsr(attack,decay,sustain,release,curve: 'welch');
		//env  = Env.new([0, 1, sustain,0], [attack, decay,release],[1.5,0,-4],2);

		freq = {freq * bend.midiratio * LFNoise2.kr(2.5,0.01,1)}!8;

		fenv = Env.asr(fattack,fsustain,frelease,1,'sine');
		fenv = EnvGen.kr(fenv, gate);
		fenv = aoc*(fenv - 1) + 1;
		sig = (SawDPW.ar(freq));

		sig = sig*EnvGen.kr(env, gate: gate,doneAction:da);

		sig = LPF.ar
		(
			sig,
			cutoff*fenv,
		);

		sig = HPF.ar(sig,hpf);

		sig = LeakDC.ar(sig);

		sig = Splay.ar(sig,spread,center:balance);

		Out.ar(out,amp*sig);

		
}).send(s);


SynthDef("eStrings16",
	{
		arg out = 0, freq = 110, gate = 0, amp = 0.5, da = 2,hpf = 120,
		attack = 0.5, decay = 2.0, sustain = 0.6, release = 0.6, fattack = 0.5, fsustain = 0.8,
		frelease = 0.6, aoc = 0.6, cutoff = 5200.00, bend = 0, spread = 1, balance = 0;

		var sig, env, fenv, env2;

		env  = Env.adsr(attack,decay,sustain,release,curve: 'welch');
		//env  = Env.new([0, 1, sustain,0], [attack, decay,release],[1.5,0,-4],2);

		freq = {freq * bend.midiratio * LFNoise2.kr(2.5,0.01,1)}!15;

		fenv = Env.asr(fattack,fsustain,frelease,1,'sine');
		fenv = EnvGen.kr(fenv, gate);
		fenv = aoc*(fenv - 1) + 1;
		sig = (SawDPW.ar(freq));

		sig = sig*EnvGen.kr(env, gate: gate,doneAction:da);

		sig = LPF.ar
		(
			sig,
			cutoff*fenv,
		);

		sig = HPF.ar(sig,hpf);

		sig = LeakDC.ar(sig);

		sig = Splay.ar(sig,spread,center:balance);
		//sig = Pan2.ar(sig);
		Out.ar(out,amp*sig);

}).send(s);

SynthDef("eStrings32",
	{
		arg out = 0, freq = 110, gate = 0, amp = 0.5, da = 2,hpf = 120,
		attack = 0.5, decay = 2.0, sustain = 0.6, release = 0.6, fattack = 0.5, fsustain = 0.8,
		frelease = 0.6, aoc = 0.6, cutoff = 5200.00, bend = 0, spread = 1, balance = 0;

		var sig, env, fenv, env2;

		env  = Env.adsr(attack,decay,sustain,release,curve: 'welch');
		//env  = Env.new([0, 1, sustain,0], [attack, decay,release],[1.5,0,-4],2);

		freq = {freq * bend.midiratio * LFNoise2.kr(2.5,0.01,1)}!24;

		fenv = Env.asr(fattack,fsustain,frelease,1,'sine');
		fenv = EnvGen.kr(fenv, gate);
		fenv = aoc*(fenv - 1) + 1;
		sig = (SawDPW.ar(freq));

		sig = sig*EnvGen.kr(env, gate: gate,doneAction:da);

		sig = LPF.ar
		(
			sig,
			cutoff*fenv,
		);

		sig = HPF.ar(sig,hpf);

		sig = LeakDC.ar(sig);

		sig = Splay.ar(sig,spread,center:balance);

		Out.ar(out,amp*sig);

}).send(s);



SynthDef(\mono_eStrings, {arg freq = 110, out = 0, amp = 0.5, aoc = 1.0,fenvIn = 999, vcaIn = 999,cutoff = 5200, gain = 0.7,release = 0.8, attack = 0.1,bend =0,hpf = 120, mul = 1,lagtime =0, spread = 1, balance = 0, gate = 0;

	var sig,fenv, env;

	release = release - 0.019;

	env = Env.new([0,0,1,0],[0.000001,0,release],0,2);

	env = EnvGen.kr(env, gate);

	freq = {freq * bend.midiratio * LFNoise2.kr(2.5,0.01,1)}!8;
	sig = (SawDPW.ar(Lag.kr(freq,lagtime)));

	fenv = In.kr(fenvIn);
	fenv = aoc*((fenv - 1) + 1);

	sig = LPF.ar
	(
		sig,
		cutoff*fenv//,
	);

	sig = HPF.ar(sig,hpf);

	sig = sig*((In.kr(vcaIn) - 1) + 1);
	sig = LeakDC.ar(sig);
	sig = Splay.ar(sig,spread,center:balance);
	Out.ar(out,amp*sig*env);

}
).add;



SynthDef(\mono_eStrings16, {arg freq = 110, out = 0, amp = 0.5, aoc = 1.0,fenvIn = 999, vcaIn = 999,cutoff = 5200, gain = 0.7,release = 0.8, attack = 0.1,bend =0,hpf = 120, mul = 1,lagtime =0, spread = 0, balance = 0, gate = 0;

	var sig,fenv, env;

	release = release - 0.019;

	env = Env.new([0,0,1,0],[0.000001,0,release],0,2);

	env = EnvGen.kr(env, gate);

	freq = {freq * bend.midiratio * LFNoise2.kr(2.5,0.01,1)}!16;
	sig = (SawDPW.ar(Lag.kr(freq,lagtime)));

	fenv = In.kr(fenvIn);
	fenv = aoc*((fenv - 1) + 1);

	sig = LPF.ar
	(
		sig,
		cutoff*fenv//,
	);

	sig = HPF.ar(sig,hpf);

	sig = sig*((In.kr(vcaIn) - 1) + 1);
	sig = LeakDC.ar(sig);
	sig = Splay.ar(sig,spread,center:balance);
	Out.ar(out,amp*sig*env);
}
).add;


SynthDef(\mono_eStrings32, {arg freq = 110, out = 0, amp = 0.5, aoc = 1.0,fenvIn = 999, vcaIn = 999,cutoff = 5200, gain = 0.7,release = 0.8, attack = 0.1,bend =0,hpf = 120, mul = 1,lagtime =0, spread = 1, balance = 0, gate = 0;

	var sig,fenv, env;

	release = release - 0.019;

	env = Env.new([0,0,1,0],[0.000001,0,release],0,2);

	env = EnvGen.kr(env, gate);

	freq = {freq * bend.midiratio * LFNoise2.kr(2.5,0.01,1)}!24;
	sig = (SawDPW.ar(Lag.kr(freq,lagtime)));

	fenv = In.kr(fenvIn);
	fenv = aoc*((fenv - 1) + 1);

	sig = LPF.ar
	(
		sig,
		cutoff*fenv//,
	);

	sig = HPF.ar(sig,hpf);

	sig = sig*((In.kr(vcaIn) - 1) + 1);
	sig = LeakDC.ar(sig);
	sig = Splay.ar(sig,spread,center:balance);
	Out.ar(out,amp*sig*env);

}
).add;
/*

// Run eStrings;

~sy = Synth("eStrings",addAction: \addToTail);
~sy.set(\gate,1);
~sy.set(\gate,0);

// Run mono_eStrings

~string_low_synth = Synth("mono_eStrings",addAction: \addToTail);

~string_low_vca_control_in = Bus.control(s, 1);
~string_low_vcf_control_in = Bus.control(s, 1);


~string_low_synth.set(\fenvIn,~string_low_vcf_control_in);
~string_low_synth.set(\vcaIn,~string_low_vca_control_in);


~string_low_vca_envelope = MyADSR.new;
~string_low_vca_envelope.init;
~string_low_vca_envelope.attack = 2.0;
~string_low_vca_envelope.decay = 4.0;
~string_low_vca_envelope.sustain = 0.4;
~string_low_vca_envelope.release = 0.9;

~string_low_vcf_envelope = MyADSR.new;
~string_low_vcf_envelope.init;
~string_low_vcf_envelope.attack = 2.0;
~string_low_vcf_envelope.decay = 4;
~string_low_vcf_envelope.sustain = 0.8;
~string_low_vcf_envelope.release = 0.9;


~stringLow_fenv = Synth("myADSR",addAction: \addToHead);
~stringLow_fenv.set(\out,~string_low_vcf_control_in);
~string_low_vcf_envelope.setADSR(~stringLow_fenv);

~stringLow_env  = Synth("myADSR",addAction: \addToHead);
~string_low_vca_envelope.setADSR(~stringLow_env);
~stringLow_env.set(\out,~string_low_vca_control_in);

~stringLow_env.set(\gate,1);
~stringLow_fenv.set(\gate,1);
~string_low_synth.set(\gate,1);


~stringLow_env.set(\gate,0);
~stringLow_fenv.set(\gate,0);
~string_low_synth.set(\gate,0);

*/