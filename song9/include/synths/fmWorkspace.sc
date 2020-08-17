// =====================================================================
// FM Workspace
// =====================================================================

SynthDef("frenchHorn",
	  {
	    arg out = 0, freq = 110, gate = 0, amp = 0.250, da = 2,hpf = 20,
	        attack = 0.2, decay = 1.5, sustain = 0.6, release = 0.3,
	        fattack = 0.2,fdecay = 1.5, fsustain = 0.8,frelease = 0.3,
	        aoc = 0.9,gain = 0.7, cutoff = 10200.00,
	        bend = 0, spread = 0, balance = 0;
	
	    var sig, fb1, op1, op2, op3, env, fenv,tmp;

	    env  = Env.adsr(attack,decay,sustain,release,curve: 'welch');
	    env = EnvGen.kr(env, gate: gate,doneAction:da);

	    fenv = Env.adsr(fattack,fdecay,fsustain,frelease,1,'sine');
	    fenv = EnvGen.kr(fenv, gate);
	    fenv = aoc*(fenv - 1) + 1;
	    
		op3 = SinOsc.ar(3*freq);
	    op2 = SinOsc.ar(1*freq);

		  //		tmp = MouseX.kr(0,2).poll;
	    fb1 = FbNode(1);
	    op1 = SinOsc.ar(freq,(op2*1.35) + fb1 + (0.0637501*op3));	  
	    fb1.write(0.3* op1);	  	 
	    sig = env*op1;

	    sig = RLPF.ar
	      (
	       sig,
	       cutoff*fenv,
	       gain
	       );
			  
	    sig = HPF.ar(sig,hpf);
		  
		  //	    sig = FreeVerb.ar(sig,0.33); // fan out...
		  
	    sig = LeakDC.ar(sig);
		  
	    sig = Splay.ar(sig,spread,center:balance);

	    Out.ar(out,amp*sig);
		  
	  }).send(s);


~frenchHorn = {arg num, vel = 1;
	     var ret;
	     num.postln;
	     ret = Synth("frenchHorn");
	     ret.set(\freq,num.midicps);
	     ret.set(\gate,1);
	     ret;
};






///////     Basic Synth Def   ///////


SynthDef("fmBasic", { arg freq = 55, out = 0, amp = 0.75, da = 2, gate = 0,
	attack = 1.5, decay = 1.5, sustain = 0.7, release = 0.5,fbr = 0.9,
	spread = 0.33, balance = 0, hpf = 128;

	var sig, env, fb;

	env = Env.adsr(attack,decay,sustain,release);
	env = EnvGen.kr(env, gate: gate, doneAction:da);

	fb = FbNode(1);
 	 
	sig = SinOsc.ar(freq,fb*fbr,mul:env);

	fb.write(sig);

	sig = HPF.ar(sig,hpf);

	sig = LeakDC.ar(sig);

	sig = Splay.ar(sig,spread,center:balance);

	Out.ar(out,sig * amp);

}).send(s);


~fmBasic = {arg num, vel = 1;
	var ret;
	num.postln;
	ret = Synth("fmBasic");
	ret.set(\freq,num.midicps);
	ret.set(\gate,1);
	ret;
};
