// =====================================================================
// MyNotes Class
// =====================================================================


Notes {
	var <>freqs = nil,    <>freq = nil,
	<>waits = nil,        <>wait = nil,
	<>lag = 0.0,          <>lags = nil,
	<>vel = 1,            <>vels = nil,
	<>durations = nil,    <>duration = nil;

	init {

		if(freqs == nil,
			{freqs = [32]; });

		if((waits == nil) || (waits.size < freqs.size),
			{waits = Array.newClear(freqs.size).fill(1); });

		if((durations == nil) || (durations.size < freqs.size),
			{
				durations = waits.deepCopy;
		});

		if(vels == nil,
			{vels = Array.newClear(freqs.size).fill(1); });

		if(lags == nil,
			{lags = Array.newClear(freqs.size).fill(0); });

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


}
