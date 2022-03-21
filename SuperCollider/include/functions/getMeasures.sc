/* =====================================================================
*  getMeasure function
// =====================================================================
*/


~getMeasure = {arg start, end, notes;

	var a = nil,b = nil, mynotes, diff;

	start = (start-1)*notes.numerator;

	end = end*notes.numerator;

	notes.realtime.do({arg item,idx; if(item > start && a == nil,{ a = idx -1;});});
	notes.realtime.do({arg item,idx; if(item >= end && b == nil,{ b = idx - 1});});

	mynotes = Notes.new;

	mynotes.freqs = notes.freqs.copyRange(a,b);
	mynotes.waits = notes.waits.copyRange(a,b);
	mynotes.durations = notes.durations.copyRange(a,b);
	mynotes.vels = notes.vels.copyRange(a,b);

	diff = notes.realtime.at(a).round(0.03125) - start;

	mynotes.freqs = ['rest'] ++ mynotes.freqs;
	mynotes.waits = mynotes.waits.addFirst(diff);
	mynotes.durations = mynotes.durations.addFirst(diff);
	mynotes.vels = [127] ++ mynotes.vels;

	diff = end - notes.realtime.at(b).round(0.03125);

	mynotes.waits = mynotes.waits.put(mynotes.waits.size - 1, diff);

	mynotes.numerator = notes.numerator;

	mynotes = (mynotes.init).remove0waits;
};
