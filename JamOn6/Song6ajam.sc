Help.gui
Quarks.gui
GUI.qt

s.boot;
s.plotTree;
s.meter;
s.quit;


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
    "/home/dbalchen/Music/JamOn6/include/Events/beats.sc".load;

    SynthDef(\vca, {arg out = 0, in = 0, amp = 1, spread = 0, center = 0,
	  clip = 1, overd = 1; 
	var sig;
	sig = In.ar(in,1);
	sig = sig*overd;
	sig = sig.clip2(clip);
	sig = Splay.ar(sig,spread,center:center);

	OffsetOut.ar(out, sig*amp);
      }).add;


   
    SynthDef(\Bell, {arg out = 0, freq = 440, gate = 0, sustain = 4, amp = 0.35, da = 2;
	var op1,op2,op3,op4,op5,op6,env,env1,env2,env3,env4,env5,env6;

	env1 = Env.adsr(0.0005,1.25,0,0);
	env2 = Env.adsr(0,sustain,0,0);
	env3 = Env.adsr(0,1.0,0,0);
	env4 = Env.adsr(0,sustain,0,0);
	env5 = Env.adsr(0.0025,0.75,0,0);
	env6 = Env.adsr(0,0.01,0,0);

	op2 = 0.78*SinOsc.ar(freq*3.5*(2**(3/1200)),0,
			     EnvGen.kr(env2, gate: gate,  timeScale: sustain, levelScale:0.75,doneAction:da);); 

	 
	op1 = 0.95*SinOsc.ar(freq*(2**(2/1200)),op2,
			     EnvGen.kr(env1, gate: gate,  timeScale: sustain, levelScale: 0.75,doneAction:da););
	  

	op4 = 0.95*SinOsc.ar((freq*3.5)/(2**(2/1200)),0,
			     EnvGen.kr(env4, gate: gate,  timeScale: sustain, levelScale: 0.50,doneAction:da););

	op3 = SinOsc.ar(freq/(2**(5/1200)),op4,
			EnvGen.kr(env3, gate: gate,  timeScale: sustain, levelScale: 0.50,doneAction:da););


	op6 = 0.85*SinOscFB.ar((freq*2)/(2**(7/1200)),0.70,
			       EnvGen.kr(env6, gate: gate,  timeScale: sustain, levelScale: 0.50,doneAction:da););

	op5 = SinOsc.ar(323.6,op6,
			EnvGen.kr(env5, gate: gate,  timeScale: sustain, levelScale: 0.50,doneAction:da););

	x = amp*((0.12*op3) + (0.10*op1) + (0.10*op5));

	Out.ar(out,x)}).add;
    )


   
     SynthDef(\tbell, { |out, gate = 0, freq = 2434, attack = 0, release = 0.1, fscale = 1, amp = 0.5, decayscale = 0.5, lag = 10, trigIn = 0|

	   var sig, input, first, freqscale, env;


	 env = EnvGen.kr(Env.linen(attack,decayscale,release),gate,doneAction:2);

	 freqscale = freq / (2434/fscale);
	 freqscale = Lag3.kr(freqscale, lag);

	 decayscale = Lag3.kr(decayscale, lag);

	 input = LPF.ar(Trig.ar(gate, SampleDur.ir)!2, 10000 * freqscale);

	 sig = DynKlank.ar(`[

			     [

			      (first = LFNoise1.kr(0.5).range(2424, 2444)) + Line.kr(20, 0, 0.5),

			      first + LFNoise1.kr(0.5).range(1,3),

			      LFNoise1.kr(1.5).range(5435, 5440) - Line.kr(35, 0, 1),

			      LFNoise1.kr(1.5).range(5480, 5485) - Line.kr(10, 0, 0.5)//,
			      /*
				LFNoise1.kr(2).range(8435, 8445) + Line.kr(15, 0, 0.05),

				LFNoise1.kr(2).range(8665, 8670),

				LFNoise1.kr(2).range(8704, 8709),

				LFNoise1.kr(2).range(8807, 8817),

				LFNoise1.kr(2).range(9570, 9607),

				LFNoise1.kr(2).range(10567, 10572) - Line.kr(20, 0, 0.05),

				LFNoise1.kr(2).range(10627, 10636) + Line.kr(35, 0, 0.05),

				LFNoise1.kr(2).range(14689, 14697) - Line.kr(10, 0, 0.05)
			      */

			      ],

			     [

			      LFNoise1.kr(1).range(-10, -5).dbamp,

			      LFNoise1.kr(1).range(-20, -10).dbamp,

			      LFNoise1.kr(1).range(-12, -6).dbamp,

			      LFNoise1.kr(1).range(-12, -6).dbamp//,
			      /*
				-20.dbamp,

				-20.dbamp,

				-20.dbamp,

				-25.dbamp,

				-10.dbamp,

				-20.dbamp,

				-20.dbamp,

				-25.dbamp  */

			      ],

			     [

			      20 * freqscale.pow(0.2),

			      20 * freqscale.pow(0.2),

			      5,

			      5//,
			      /*
				0.6,

				0.5,

				0.3,

				0.25,

				0.4,

				0.5,

				0.4,

				0.6 */

			      ] * freqscale.reciprocal.pow(0.5)

			     ], input,freqscale, 0, decayscale);


	 sig = sig*env;

	 sig = Mix.new(sig);

	 Out.ar(out, sig*amp);

       }).add;


   ~nGroup = Group.new;

   ~envout = Bus.audio(s,1);
   ~envout1 = Bus.audio(s,1);
   ~asrOut = Bus.audio(s,1);
   ~adsrOut = Bus.audio(s,1);

   ~wcut = Bus.audio(s,1);
   ~wmul = Bus.audio(s,1);
   ~wgain = Bus.audio(s,1);

   ~mix1out = Bus.audio(s,1);
   ~mix2out = Bus.audio(s,1);
   ~mix3out = Bus.audio(s,1);
   ~mix4out = Bus.audio(s,1);
   ~circleOut = Bus.audio(s,1);

   ~pulse1Out = Bus.audio(s,1);
   ~noise1Out = Bus.audio(s,1);
   ~sine1Out = Bus.audio(s,1);
   ~bellOut = Bus.audio(s,1);
   ~vcaOut = Bus.audio(s,1);

   ~myadsr = MyADSR.new;
   ~myadsr.init;
   ~myadsr.attack = 0.2;
   ~myadsr.decay = 2.5;
   ~myadsr.sustain = 0.0;
   ~myadsr.release = 0.0;


   ~wind = Synth("windspeed",target: ~nGroup,addAction: \addToHead);
   ~wind.set(\out,~wcut);
   ~wind.set(\out2,~wmul);
   ~wind.set(\out3,~wgain);

   ~circle = Synth("myCircle",target: ~nGroup,addAction: \addToHead);
   ~circle.set(\out,~circleOut);
    

   ~mixer1 = Synth("two2one",target: ~nGroup,addAction: \addToTail);
   ~mixer1.set(\in0,~wcut);
   ~mixer1.set(\in1,~envout1);
   ~mixer1.set(\out,~mix1out);

   ~mixer2 = Synth("two2one",target: ~nGroup,addAction: \addToTail);
   ~mixer2.set(\in0,~wmul);
   ~mixer2.set(\in1,~envout);
   ~mixer2.set(\out,~mix2out);

   ~mixer3 = Synth("two2one",target: ~nGroup,addAction: \addToTail);
   ~mixer3.set(\in0,~envout1);
   ~mixer3.set(\in1,~asrOut);
   ~mixer3.set(\out,~mix3out);

   ~mixer4 = Synth("two2one",addAction: \addToTail);
   ~mixer4.set(\in0,~bellOut);
   ~mixer4.set(\in1,~sine1Out);
   ~mixer4.set(\out,~vcaOut);

   ~vca1 =  Synth("vca",addAction: \addToTail);
   ~vca1.set(\in,~vcaOut);

   ~pulse1 =  Synth("Pulse",target: ~nGroup,addAction: \addToTail);
   ~pulse1.set(\out,~pulse1Out);

   ~pulse =  Synth("bdSound",target: ~nGroup,addAction: \addToTail);
   ~pulse.set(\cutoff,~mix3out);
   ~pulse.set(\mul, ~envout);
   ~pulse.set(\oscIn, ~pulse1Out);
   ~pulse.set(\aocIn, ~adsrOut);

   ~noise1 =  Synth("Noise",target: ~nGroup,addAction: \addToTail);
   ~noise1.set(\freq, 77.midicps);
   ~noise1.set(\out,~noise1Out);

   ~noise =  Synth("bdSound",target: ~nGroup,addAction: \addToTail);
   ~noise.set(\cutoff,~mix1out);
   ~noise.set(\gain,~wgain);
   ~noise.set(\mul, ~mix2out);
   ~noise.set(\oscIn, ~noise1Out);
   ~noise.set(\aocIn, ~circleOut);

   ~dcs = 1.0;
   ~fscale = 2;
   ~release = 0.1;
   ~attack = 0.0;
   ~amp = 0.5;
   ~pitch = 55.00;


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
		
		ret =  Synth("Sine",addAction: \addToHead);
		ret.set(\out,~sine1Out);
		ret.set(\amp,0.5);
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
		ret.set(\attack,8);
		ret.set(\cutoff,15000);
		ret.set(\aoc,0.7);
		ret.set(\gate,1);

		ret;
   };

   ~channel9 = {arg num, vel = 1;
		var ret;

		ret  = Synth("env0",target: ~nGroup,addAction: \addToHead);
		ret.set(\out,~envout);
		ret  = Synth("env1",target: ~nGroup,addAction: \addToHead);
		ret.set(\out,~envout1);

		ret  = Synth("myADSR",target: ~nGroup,addAction: \addToHead);
		ret.set(\out,~adsrOut);
		~myadsr.setADSR(ret);
      
		~nGroup.set(\gate,1);
		~nGroup;

   };
   )

 };
)

~startup.value;
~startTimer.value(120);

~dcs = 4.4;
~fscale = 4.00;
~release = 0.2;
~attack = 2.0;
~amp = 0.025;

~nGroup.free;

~rp = {~midiBassDrum.value;~midiSineDrum.value;~midiCantus_firmus.value;~circle.set(\zgate,1);}; // Exampl;


~noise1.set(\rq,0.15);
~noise1.set(\lagLev,4.00);
~noise.set(\aoc,1.0);
~noise.set(\spread,1);
~noise.set(\amp,3);

~pulse1.set(\lagLev,0.0250);
~pulse1.set(\width,0.5);
~pulse.set(\clip,1);
~pulse.set(\aoc,1.0);
~pulse.set(\cutoff,~mix3out);
~pulse.set(\mgain,2.0);
~pulse.set(\maoc,1.0);
~pulse.set(\amp,0.5);
~pulse.set(\overd,1.2);


~sine.set(\amp,0.7600);

~myadsr.gui;

~mixergui1 = SimpleMix.new;
~mixergui1.mixer = ~mixer1;
~mixergui1.gui;

~mixergui2 = SimpleMix.new;
~mixergui2.mixer = ~mixer2;
~mixergui2.gui;

~mixergui3 = SimpleMix.new;
~mixergui3.mixer = ~mixer3;
~mixergui3.gui;

~mixergui4 = SimpleMix.new;
~mixergui4.mixer = ~mixer4;
~mixergui4.gui;

(
 ~start = {

   var num = 120,timeNow;
   t = TempoClock.default.tempo = num / 60;

   Routine.run({
       s.sync;
       timeNow = TempoClock.default.beats;

       t.schedAbs(timeNow + 00,{ // 00 = Time in beats
	   (
	    // If yes put stuff Here
			);

	   (
	    // If No put stuff here otherwise nil
	    nil
	    );
	 };	 // End of if statement

	 ); // End of t.schedAbs


       //Add more

     }); // End of Routine

 }; //End of Start

 )


~rp = {~start.value;};
