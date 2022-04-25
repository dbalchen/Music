// =====================================================================
// Notes Class
// =====================================================================


Notes {

	var <>freqs = nil,    <>freq = nil,
	<>probs = nil,        <>numerator = 4,
	<>waits = nil,        <>wait = nil,
	<>lag = 0.0,          <>lags = nil,
	<>vel = 1,            <>vels = nil,
	<>durations = nil,    <>duration = nil,
	<>realtime = nil;

	init {

		if(freqs == nil,
			{freqs = [60]; });

		if(probs == nil,
			{probs = freqs.deepCopy.collect{|x| if(x == 0,{x = 0;},{x = 1;})}});

		if(waits == nil,
			{waits = Array.newClear(freqs.size).fill(1); });

		if(durations == nil,
			{durations = waits.deepCopy;});

		if(vels == nil,
			{vels = Array.newClear(freqs.size).fill(127); });

		if(lags == nil,
			{lags = Array.newClear(freqs.size).fill(0); });

		realtime = [];

		waits.do({
			arg item, i;

			if(i == 0,
				{
					realtime = realtime.add(item);
				},
				{
					realtime = realtime.add( realtime.at(i-1) + item);

			};);

		});

		realtime = realtime.addFirst(0.0);

		this.calcFreq.value;
		this.calcDur.value;
		this.calcWait.value;
		this.calcLag.value;
		this.calcVel.value;

	}


	calcFreq {

		var lazy;

		lazy = Plazy({
			Pseq(freqs,1);
		});

		freq = Pn(lazy,inf).asStream;
	}


	calcDur {

		var lazy;

		lazy = Plazy({
			Pseq(durations,1);
		});

		duration = Pn(lazy,inf).asStream;

	}


	calcWait {
		var lazy;

		lazy = Plazy({
			Pseq(waits,1);
		});

		wait =Pn(lazy,inf).asStream;

	}


	calcLag {
		var lazy;

		lazy = Plazy({
			Pseq(lags,1);
		});

		lag =Pn(lazy,inf).asStream;

	}


	calcVel {
		var lazy;

		lazy = Plazy({
			Pseq(vels,1);
		});

		vel =Pn(lazy,inf).asStream;

	}


	rotate {arg how_many = 0;

		var myNotes = Notes.new;

		myNotes.freqs = (freqs.deepCopy).rotate(how_many);

		myNotes.probs = (probs.deepCopy).rotate(how_many);

		myNotes.waits = (waits.deepCopy).rotate(how_many);

		myNotes.durations = (durations.deepCopy).rotate(how_many);

		myNotes.lags = (lags.deepCopy).rotate(how_many);

		myNotes.vels = (vels.deepCopy).rotate(how_many);

		myNotes.numerator = numerator;

		myNotes = myNotes.init;

		^myNotes;

	}


	remove0waits {

		var myNotes = Notes.new;

		waits.do {

			arg item,i;

			if((item != 0.00),{

				myNotes.freqs = myNotes.freqs.add(freqs.at(i));
				myNotes.probs = myNotes.probs.add(probs.at(i));
				myNotes.waits = myNotes.waits.add(waits.at(i));
				myNotes.durations = myNotes.durations.add(durations.at(i));
				myNotes.lags = myNotes.lags.add(lags.at(i));
				myNotes.vels = myNotes.vels.add(vels.at(i));

			});

		};

		myNotes.numerator = numerator;

		myNotes = myNotes.init;

		^myNotes;
	}

	merge {arg addToo;

		var myNotes = Notes.new;

		addToo = addToo.deepCopy;


		myNotes.freqs = freqs ++ addToo.freqs;
		myNotes.probs = probs ++ addToo.probs;
		myNotes.waits = waits ++ addToo.waits;
		myNotes.durations = durations ++ addToo.durations;
		myNotes.lags = lags ++ addToo.lags;
		myNotes.vels = vels ++ addToo.vels;

		myNotes.numerator = numerator;

		myNotes = myNotes.init;

		^myNotes;

	}

	replace {arg replacewith;

		replacewith = replacewith.deepCopy;

		freqs = replacewith.freqs;
		probs = replacewith.probs;
		waits = replacewith.waits;
		durations = replacewith.durations;
		lags = replacewith.lags;
		vels = replacewith.vels;

		numerator = replacewith.numerator;

	}


	getMeasure {arg start, end;

		var a = nil,b = nil, mynotes, diff;

		start = (start-1)*this.numerator;

		end = end*this.numerator;

		this.realtime.do({arg item,idx; if(item > start && a == nil,{ a = idx -1;});});
		this.realtime.do({arg item,idx; if(item >= end && b == nil,{ b = idx - 1});});

		mynotes = Notes.new;

		mynotes.freqs = this.freqs.copyRange(a,b);
		mynotes.waits = this.waits.copyRange(a,b);
		mynotes.durations = this.durations.copyRange(a,b);
		mynotes.vels = this.vels.copyRange(a,b);

		diff = this.realtime.at(a).round(0.03125) - start;

		mynotes.freqs = ['rest'] ++ mynotes.freqs;
		mynotes.waits = mynotes.waits.addFirst(diff);
		mynotes.durations = mynotes.durations.addFirst(diff);
		mynotes.vels = [127] ++ mynotes.vels;

		diff = end - this.realtime.at(b).round(0.03125);

		mynotes.waits = mynotes.waits.put(mynotes.waits.size - 1, diff);

		mynotes.numerator = this.numerator;

		mynotes = (mynotes.init).remove0waits;

		^mynotes;
	}


}

