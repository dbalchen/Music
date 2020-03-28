~buildDic =
{arg pcset1, pcset2;
    var ldic;

    ldic = Dictionary.new;

    pcset1.do({ arg item, i;

        ldic.put(item,pcset2[i]);

    });
    ldic;
};

~useDic =
{arg num, dic,key;

    var base, prime;

    prime = (num - key)%12;

    base = (num / 12).asInteger;

    prime = dic.at(prime) + key + (12*base);

    prime;

};


~fullDic = {arg notes, dic, key;

    var notesback = Array.new(notes.size);

    notes.do({arg item, i;
        if( item == 'rest',
            {notesback.insert(i,'rest');},
            {notesback.insert(i,~useDic.value(item,dic,key))});
    });

    notesback;
};

~createScale = {arg tonerow;

    var fullscale = [];

    for(0,12,
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
    tonerow = (tonerow - key) % 12;
    tonerow = tonerow.as(Set).as(Array).sort;
    tonerow;
};

~pitchClassT = {arg pitch,transpose,key = 0;

    var pitches;

    pitches = pitch.deepCopy;

    pitches = (pitches - key)%12;

    if(transpose >= 0,
        {
            pitches = (pitches - transpose)%12;

        },
        {
            pitches = (abs(transpose) - pitches)%12;
    });


    pitches = pitches + key;

    pitches = pitches + 60;

};

~toneMap = {arg pitch,t0,t1,key = 0;

    var pitches;

    pitches = [];

    pitch.do {arg itemp, a;
        var search;

        search = (itemp -key)%12;

        t0.do{arg item,b;

            var diff;

            if(item == search,
                {
                    diff = item - t1[i];
                    pitches = pitches.add(itemp + diff)
                }
            );
        };
    };
    pitches;
};