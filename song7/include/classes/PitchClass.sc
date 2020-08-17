/*
* Scale and transformations
*/

~createScale = {arg tonerow, key = 0;

    var fullscale = [];

	tonerow = tonerow + key;

    for(-2,12,
        { arg i;
            tonerow.do({arg item, ii;

                var point;

                point = item + (i*12);

                if((point >=0) && (point <= 120),{

                    fullscale = fullscale.add(point);

				};)});
			
    });
    fullscale.sort;
};


~melodicCurves = {arg noteSeq, tonerow,gap = 2;

    var point = 0, mapEnv,mapfreqs = [], mapwaits = [];

    for(0,noteSeq.freqs.size - 1,
        { arg i;
            if(noteSeq.freqs.at(i)
                >= 1,{

                    mapfreqs =	mapfreqs.add(noteSeq.freqs.at(i));
                    mapwaits =	mapwaits.add(noteSeq.durations.at(i));

            });
    });

    mapfreqs =	mapfreqs.add(noteSeq.freqs.at(0));


    mapEnv = Env.new(mapfreqs,mapwaits,\sine);
    mapfreqs.postln;
    mapwaits.postln;


    mapEnv.plot;

    noteSeq.freqs.do({ arg item, i;
        var subtone;

        if( item == 0,
            {

                subtone = tonerow.copyRange(tonerow.indexOfGreaterThan( mapEnv.at(point))
                    - gap,tonerow.indexOfGreaterThan( mapEnv.at(point))
                    + (gap - 1));

                noteSeq.freqs.put(i,subtone.choose);


        });
        point = point + noteSeq.waits.at(i);
    });
    noteSeq;
};

/*
~tt = ~melCurves.value(~verse.notes,~tonerow);
~tt.freqs;
*/

~pcset = {arg tonerow, key = 0;

    tonerow = tonerow.reject({ arg item, index; item == 'rest'; });
    tonerow = tonerow.reject({ arg item, index; item == 0; });
	
    tonerow = (tonerow - key) % 12;
    tonerow = tonerow.as(Set).as(Array).sort;
    tonerow;
};


/*

* Pitch Map

*/

~pitchMap = {arg pitch,t0,t1,key = 0;

    var pitches = [];

    pitch.do {arg itemp, a;

        var search;

        if((itemp == 'rest') || (itemp == 0),
            {
				"I be here".postln;
				
				itemp = 'rest';
                pitches = pitches.add(itemp);

            },
            {
                search = (itemp - key)%12;

                t0.do{arg item,b;

                    var diff;

                    if(item == search,
                        {
                            diff = t1[b] - item;
                            pitches = pitches.add(itemp + diff)
                    });
                };
        });

    };

    pitches;
};


/*
* Transpose
*/

~pitchClassT = {arg pitch,transpose,key = 0;

    var pitches,pitches0;

    pitches = ~pcset.value(pitch,key);
	pitches0 = pitches.deepCopy;

    if(transpose >= 0,
        {
            pitches = (pitches - transpose)%12;

        },
        {
            pitches = (abs(transpose) -  pitches)%12;
    });

	pitches = ~pitchMap.value(pitch,pitches0,pitches,key);

	pitches;

};

~pitchClassTI = {arg pitch,transpose,key = 0;

    var pitches,pitches0;

    pitches = ~pcset.value(pitch,key);
	pitches0 = pitches.deepCopy;

    if(transpose >= 0,
        {
            pitches = (pitches + transpose)%12;

        },
        {
            pitches = (abs(transpose) +  pitches)%12;
    });

	pitches = ~pitchMap.value(pitch,pitches0,pitches,key);

	pitches;

};


~harmony = {arg pitch,transpose,key = 0;
	
	var pitches,pitches0,harmony;

    pitches = ~pcset.value(pitch,key);

	harmony = ~pitchClassT.value(pitch,transpose,key);

	pitches0 = ~pcset.value(harmony,key);

	harmony =  ~pitchMap.value(harmony,pitches0,pitches,key);

};

