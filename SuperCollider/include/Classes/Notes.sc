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

        waits.do({
            arg item, i;

            if(i == 0,
                {realtime = realtime.add(item);},
                {realtime = realtime.add( realtime.at(i-1) + item);};
            );

        });


        this.calcFreq.value;
        this.calcDur.value;
        this.calcWait.value;
        this.calcLag.value;
        this.calcVel.value;

    }


    calcFreq {

        var lazy;

        lazy = Plazy({

            var ary,flip,fre;

            if(freqs.size >= probs.size,
                {ary = Array.newClear(freqs.size);},
                {ary = Array.newClear(probs.size);}
            );


            ary.do({ arg item, i;

                flip =  rrand(0.0, 1.0);

                if(probs.at(i%probs.size) >= flip,
                    {ary.put(i,1);},
                    {ary.put(i,0);}
                );

            });


            fre = freqs*ary;

            fre.do({ arg item, i;

                if(item.isKindOf(Array),
                    {
                        if(item.at(0) == 0,{fre.put(i,\rest)};);
                });

                if(item == 0,{fre.put(i,\rest);});

            });

            Pseq(fre,1);

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
}





