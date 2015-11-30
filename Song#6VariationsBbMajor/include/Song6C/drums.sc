"/home/dbalchen/workspace/SuperCollider/drumSampler.sc".loadPath;

~snare = MyEvents.new;
~snare.amp = 0.80;
~snareend = [0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,1.00,0.00,0.00,0.00];
~snare.probs = [0.00,0.00,0.00,1.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,1.00,0.00,0.00,0.00] * 1;
~snaremain = ~snare.probs.deepCopy;
~snare.freqs = [0.00,0.00,0.00,38.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,38.00,0.00,0.00,0.00];
~snare.waits = [1.00,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50];
~snare.durations = [1.00,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50] * 2;
~snare.init;
~snare.filter.attack = 0.0;
~snare.filter.release = 0.50;
~snare.filter.cutoff = 10000;
~snare.filter.gain = 0.0;
~snare.filter.sustain = 1.0;
~snare.filter.aoc = 1;
~snare.envelope.attack = 0.0;
~snare.envelope.release = 0.50;
~snare.envelope.decay = 1.5;
~snare.envelope.sustain = 0.0;

~bassd =  MyEvents.new;
~bassd.amp = 1.0;
~bassd.probs = [1.00,0.00,0.00,0.00,0.00,1.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00] * 1.0;

~bassdmain = [1.00,0.00,0.00,0.00,0.00,1.00,0.00,1.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00];
~bassd.probs = [1.00,0.00,0.29,0.00,0.00,1.00,0.00,1.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00] * 1.0;
~bassd.probs = [1.00,0.00,0.0,0.00,0.00,1.00,0.00,1.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00] * 1.0;
~bassd.freqs = [36.00,0.00,36.00,0.00,0.00,36.00,0.00,36.00,0.00,0.00,0.00,36.00,0.00,36.00,0.00];
~bassd.waits = [1.00,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50] * 1.0;
~bassd.durations = [1.00,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50] * 2;
~bassd.init;
~bassd.filter.attack = 0.0;
~bassd.filter.release = 4.0;
~bassd.filter.cutoff = 8000;
~bassd.filter.gain = 0.2;
~bassd.filter.sustain = 1.0;
~bassd.filter.aoc = 1;
~bassd.envelope.attack = 0.0;
~bassd.envelope.release = 0.250;
~bassd.envelope.decay = 0.50;
~bassd.envelope.sustain = 0.0;


~fadein =  (Array.fill(15, { arg i; (((i*i)/15)/13);}));
~fadeout =  Array.fill(15, { arg i; i = i-15;(((i*i)/225 )) ;} );

~tom1 = MyEvents.new;
~tom1.amp = 1.0;
~tom1.probs = [0.00,1.00,0.00,1.00,0.00,0.00,0.00,0.00,1.00,0.00,1.00,0.00,0.00,1.00,0.00] * 1;
~tom1.probs = [0.00,1.00,0.00,1.00,1.00,0.00,1.00,0.00,1.00,1.00,1.00,0.00,1.00,1.00,1.00] * 1;
~tom1end = [0.00,1.00,0.00,1.00,1.00,0.00,1.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00];
~tom1all = [0.00,1.00,0.00,1.00,1.00,0.00,1.00,0.00,1.00,1.00,1.00,0.00,1.00,1.00,1.00];
~tom1probs =      [0.00,0.80,0.00,1.00,0.40,0.00,0.60,0.00,1.00,0.60,1.00,0.00,0.80,0.80,0.40];
~tom1probsPrime = [0.00,0.00, 0.00,1.00, 0.00, 0.00,0.00, 0.00,1.00, 0.00, 1.00, 0.00,0.00, 1.00,0.00];
~tom1roll =  [0.00,1.00,0.00,0.00,1.00,0.00,1.00,0.00,1.00,1.00,1.00,0.00,1.00,1.00,1.00] * 1;
~tom1.freqs = [0.00,41.00,0.00,47.00,43.00,0.00,41.00,0.00,47.00,41.00,43.00,0.00,41.00,47.00,43.00];
~tom1.waits = [1.00,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50];
~tom1.durations = [1.00,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50] * 5;
~tom1.init;
~tom1.filter.attack = 0.0;
~tom1.filter.release = 4.0;
~tom1.filter.cutoff = 8000;
~tom1.filter.gain = 0.0;
~tom1.filter.sustain = 1.0;
~tom1.filter.aoc = 1;
~tom1.envelope.attack = 0.0;
~tom1.envelope.release = 8.0;
~tom1.envelope.decay = 0.0;
~tom1.envelope.sustain = 1.0;



~tom2 = MyEvents.new;
~tom2.amp = 1.0;
~tom2.init;
~tom2.filter.attack = 0.0;
~tom2.filter.release = 4.0;
~tom2.filter.cutoff = 8000;
~tom2.filter.gain = 0.0;
~tom2.filter.sustain = 1.0;
~tom2.filter.aoc = 1;
~tom2.envelope.attack = 0.0;
~tom2.envelope.release = 8.0;
~tom2.envelope.decay = 0.0;
~tom2.envelope.sustain = 1.0;


~tom3 = MyEvents.new;
~tom3.amp = 1.0;
~tom3.init;
~tom3.filter.attack = 0.0;
~tom3.filter.release = 4.0;
~tom3.filter.cutoff = 8000;
~tom3.filter.gain = 0.0;
~tom3.filter.sustain = 1.0;
~tom3.filter.aoc = 1;
~tom3.envelope.attack = 0.0;
~tom3.envelope.release = 8.0;
~tom3.envelope.decay = 0.0;
~tom3.envelope.sustain = 1.0;


~ride = MyEvents.new;
~ride.amp = 6.0;
~ride.probs = [1.00,0.00,1.00,0.00,0.00,1.00,0.00,1.00,0.00,0.00,0.00,1.00,0.00,0.00,0.00];
~ride.probs = [1.00,1.00,0.00,0.00,0.00,1.00,0.00,1.00,0.00,1.00,0.00,0.00,0.00,1.00,0.00];
~ride.probs = [1.00,1.00,1.00,0.00,0.00,1.00,0.00,1.00,0.00,1.00,0.00,1.00,0.00,1.00,0.00];
~ride.probs = [1.00,0.67,0.67,0.00,0.00,1.00,0.00,1.00,0.00,0.67,0.00,0.67,0.00,0.67,0.00] * 4.0;

~ride.freqs = [59.00,59.00,59.00,0.00,0.00,59.00,0.00,59.00,0.00,59.00,0.00,59.00,0.00,59.00,0.00];
~ride.waits = [1.00,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50] * 2.00;
~ride.durations = [1.00,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50] * 10;
~ride.init;
~ride.filter.attack = 0.0;
~ride.filter.release = 4.0;
~ride.filter.cutoff = 8000;
~ride.filter.gain = 3.0;
~ride.filter.sustain = 1.0;
~ride.filter.aoc = 1;
~ride.envelope.attack = 0.0;
~ride.envelope.release = 8.0;
~ride.envelope.decay = 0.0;
~ride.envelope.sustain = 1.0;


~midiBassDrum = {Pbind(\type, \midi,
		       \midiout, ~synth1,
		       \midicmd, \noteOn,
		       \note,  Pfunc.new({~bassd.freq.next}- 60),
		       \amp, ~bassd.amp,
		       \chan, 9,
		       \sustain, Pfunc.new({~bassd.duration.next}),
		       \dur, Pfunc.new({~bassd.wait.next})
		       ).play};

~midiSnare = {Pbind(\type, \midi,
		    \midiout, ~synth1,
		    \midicmd, \noteOn,
		    \note,  Pfunc.new({~snare.freq.next}- 60),
		    \amp, ~snare.amp,
		    \chan, 9,
		    \sustain, Pfunc.new({~snare.duration.next}),
		    \dur, Pfunc.new({~snare.wait.next})
		    ).play};

~midiToms = {Pbind(\type, \midi,
		   \midiout, ~synth1,
		   \midicmd, \noteOn,
		   \note,  Pfunc.new({~tom1.freq.next}- 60),
		   \amp, ~tom1.amp,
		   \chan, 9,
		   \sustain, Pfunc.new({~tom1.duration.next}),
		   \dur, Pfunc.new({~tom1.wait.next})
		   ).play};

~midiCyms = {Pbind(\type, \midi,
		   \midiout, ~synth1,
		   \midicmd, \noteOn,
		   \note,  Pfunc.new({~ride.freq.next}- 60),
		   \amp, ~ride.amp,
		   \chan, 10,
		   \sustain, Pfunc.new({~ride.duration.next}),
		   \dur, Pfunc.new({~ride.wait.next})
		   ).play};

~channel9 = {arg num, vel = 1;
	     var ret, amp = 2, drum;

	     num.postln;

	     drum = ~snare;
	     /*
	       if((num == 36),{drum = ~bassd;});
	       if((num == 38),{drum = ~snare;});
	       if((num == 41),{drum = ~tom1;});
	       if((num == 43),{drum = ~tom2;});
	       if((num == 47),{drum = ~tom3;});
	       if((num == 59),{drum = ~ride;});
	     */
	     amp = drum.amp;
	     drum.amp = amp * vel;
	     ret = ~midiDrum.value(drum,~drumSound,num);
	     drum.amp = amp;
   
	     ret;
};


~channel10 = {arg num, vel = 1;
	      var ret, amp = 2, drum;

	      num.postln;

	      drum = ~ride;
	      /*
		if((num == 36),{drum = ~bassd;});
		if((num == 38),{drum = ~snare;});
		if((num == 41),{drum = ~tom1;});
		if((num == 43),{drum = ~tom2;});
		if((num == 47),{drum = ~tom3;});
		if((num == 59),{drum = ~ride;});
	      */
	      amp = drum.amp;
	      drum.amp = amp * vel;
	      ret = ~midiDrum.value(drum,~drumSound,num);
	      drum.amp = amp;
   
	      ret;
};

~bassdrummiddle = {~bassd.durations = [2.50,0.50,2.50,0.50,2.50,0.50,3.50,2.50,0.50,3.50,2.50,0.50,3.50];
		   ~bassd.waits = [3.00,1.00,3.00,1.00,3.00,1.00,4.00,3.00,1.00,4.00,3.00,1.00,0.00];
		   ~bassd.freqs = [36,36,36,36,36,36,36,36,36,36,36,36,36];
		   ~bassd.probs = [1,1,1,1,1,1,1,1,1,1,1,1,1]; };

~tomBegin = {//~bassd.probs = [1.00,0.00,0.00,0.00,0.00,1.00,0.00,1.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00] ;~
  ~tom1.probs = (~tom1probs * 1.4 * ~fadein) + (~tom1probsPrime);~ride.amp = 0;~tom1.amp = 1;~tom2.amp = 1;~tom3.amp = 1;~snare.amp = 0;};

~tomMid = {//~bassd.probs = [1.00,0.00,0.00,0.00,0.00,1.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00] ;
  ~tom1.probs = (~tom1probs * 2.4 * ~fadein) + (~tom1probsPrime);~ride.amp = 0;~tom1.amp = 1;~tom2.amp = 1;~tom3.amp = 1;~snare.amp = 0;
};

~tomend1 = {//~bassd.probs = ~bassdmain;
  ~tom1.probs = (~tom1probs) + (~tom1probsPrime);~ride.amp = 0;~tom1.amp = 1;~tom2.amp = 1;~tom3.amp = 1;~snare.amp = 0;};

~tomend2 ={~tom1.probs = ~tom1end;
	   //~bassd.probs = ~bassdmain;
	   ~snare.probs =~snareend;~snare.amp = 1;~tom1.amp = 1;~tom2.amp = 1;~tom3.amp = 1;~ride.amp = 0};

~mainDrum = {~bassd.probs = [1.00,0.00,0.0,0.00,0.00,1.00,0.00,1.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00] * 1.0;
	     ~bassd.freqs = [36.00,0.00,36.00,0.00,0.00,36.00,0.00,36.00,0.00,0.00,0.00,36.00,0.00,36.00,0.00];
	     ~bassd.waits = [1.00,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50] * 1.0;
	     ~bassd.durations = [1.00,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50,0.50] * 2;
	     ~snare.probs = ~snaremain;~tom1.amp = 0;~tom2.amp = 0;~tom3.amp = 0;~ride.amp = 0;~snare.amp = 0;~tom1.probs = ~tom1probs};

~roleOn  = {~tom1.amp = 1;~tom2.amp = 1;~tom3.amp = 1};
~roleOff = {~tom1.amp = 0;~tom2.amp = 0;~tom3.amp = 0};
