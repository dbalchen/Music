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


~pitchMap = {
	arg fn , p0, pI, high = 120, low = 0, center = 60;
	var newP = [];

	fn.do(
		{arg item, i;
			var noteIdx,note;

			//			[i, item].postln;

			note = item%12;

			noteIdx = p0.detectIndex({arg item0, idx;
				item0 == note;
			});

			//		[note,noteIdx].postln;

			if(noteIdx != nil,
				{
					note = pI.at(noteIdx);
					//					newP = newP.add(pI.at(noteIdx));
				},
				{
					note = note;
					//					newP = newP.add(note);
				}
			);

			note = note + center;
		
			if(note > high,
				{
					note = note - 12;
				}

			);

			if(note < low,
				{
					note = note + 12;
				}

			);

			newP = newP.add(note);
			
		}
	);

	newP;
};

/*
* Interval Addition
*/

~invAdd = {arg notes1,notes2, p0 = nil; // key = 0;

	var mynotes, map1, map2,rt;

	mynotes = Notes.new;

	//	rt = (notes1.realtime ++ notes2.realtime).round(0.03125);

	rt = (notes1.realtime).round(0.03125);
	rt = (rt.as(Set).as(Array).sort);

	map1 = Env.new(notes1.freqs,notes1.waits,\hold);
	map2 = Env.new(notes2.freqs,notes2.waits,\hold);

	for ( 0, rt.size - 2 , {arg i;

		var note,f0,f1,i0 = nil ,i1 = nil, wait;

		rt.at(i).post;
		"   ".post;

		f0 = map1.at(rt.at(i))%12;// .post;
		f0.post;
		"   ".post;

		i0 = p0.detectIndex({arg item0, idx;
			item0 == f0;
		});
		i0.post;
		"   ".post;

		f1 = map2.at(rt.at(i))%12;//.post;
		f1.post;
		"   ".post;

		i1 = p0.detectIndex({arg item0, idx;
			item0 == f1;
		});
		i1.post;
		"   ".post;


		if((i0 != nil && i1 != nil),
			{
				i0 = (i1 + i0)%(p0.size);
				note = p0.at(i0);
			},
			{
				note = (f1 + f0)%12;
			}
		);

		//		note = ((map1.at(rt.at(i)) - key) + (map2.at(rt.at(i)) - key))%12;

		note.post;
		"   ".post;

		wait = (rt.at(i+1) - rt.at(i));

		wait.postln;

		mynotes.freqs = mynotes.freqs.add(note);
		mynotes.waits = mynotes.waits.add(wait);

	});

	mynotes.freqs = mynotes.freqs + 60; //(60 + key);

	mynotes = mynotes.init;

	mynotes;
};


