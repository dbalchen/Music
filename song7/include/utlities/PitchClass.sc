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

    prime = num%12;

    prime = prime - key;

    base = (num / 12).asInteger;

    if (prime < 0,{prime = prime + 12;base = base -1});

    prime = dic.at(prime) + key + (12*base);

    prime;

};


~fullDic = {arg notes, dic, key;

    var notesback = Array.new(notes.size);

    notes.do({arg item, i;

        notesback.insert(i,~useDic.value(item,dic,key));

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

~melCurves = {arg noteSeq, tonerow,gap = 2;

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

                subtone = tonerow.copyRange(tonerow.indexOfGreaterThan( mapEnv.at(point)) - gap,tonerow.indexOfGreaterThan( mapEnv.at(point)) + (gap - 1));

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
~pcset = {arg tonerow;

    tonerow = tonerow.takeThese({ arg item, index; item == 0; });
    tonerow = tonerow % 12;
    tonerow = tonerow.as(Set).as(Array).sort;
    tonerow;
};


//	      ~pcset.value(~verse.notes.freqs);

~fixDurations = { arg notes;

    notes.freqs.do({arg item, i;

        var dur = 0, count = i+1;

        if (item != 0,{
            dur = notes.waits[i];
            while({(notes.freqs[count] == 0) && (count < notes.freqs.size)},{
                dur = dur + notes.waits[count];
                count = count + 1;
            });

            notes.durations[i] = dur;
        },
        {
            notes.durations[i] = 0;
        }
        );


    });

    notes;
};
