/*
* PCset
*/

~pcset = {arg tonerow, key = 0;
	tonerow = tonerow.reject({ arg item, index; item == 'rest'; });
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

	var pitches;

	pitches = pitch%12;

	if(transpose >= 0,
		{
			pitches = (pitches + transpose)%12;

		},
		{
			pitches = (abs(transpose) +  pitches)%12;
	});

	pitches;

};

/*
* Transpose Inverse
*/

~pitchClassTI = {arg pitch,transpose,key = 0;

	var pitches;

	pitches = pitch%12;

	if(transpose >= 0,
		{
			pitches = (pitches - transpose)%12;

		},
		{
			pitches = (abs(transpose) - pitches)%12;
	});


	pitches;

};

/*
* Interval Addition
*/

~invAdd = {arg notes1,notes2, key = 0;

	   var mynotes, map1, map2,rt;

	   mynotes = Notes.new;
	
	   rt = (notes1.realtime ++ notes2.realtime).round(0.03125);
	   rt = (rt.as(Set).as(Array).sort).addFirst(0.0);

	   map1 = Env.new(notes1.freqs,notes1.waits,\hold);
	   map2 = Env.new(notes2.freqs,notes2.waits,\hold);

	
	   for ( 0, rt.size - 2 , {arg i;
		
	       var note, wait;

	       rt.at(i).post;
	       "   ".post;

	       map1.at(rt.at(i)).post;
	       "   ".post;
		
	       map2.at(rt.at(i)).post;
	       "   ".post;
		
	       note = ((map1.at(rt.at(i)) - key) + (map2.at(rt.at(i)) - key))%12;
		
	       note.post;
	       "   ".post;
		
	       wait = (rt.at(i+1) - rt.at(i));
		
	       wait.postln;

	       mynotes.freqs = mynotes.freqs.add(note);
	       mynotes.waits = mynotes.waits.add(wait);
	       
	     });

	   mynotes.freqs = mynotes.freqs + (60 + key);

	   mynotes = mynotes.init;

	   mynotes;
};
