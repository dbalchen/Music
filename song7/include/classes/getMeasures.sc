/* =====================================================================
*  getMeasure function
// =====================================================================
*/

~getMeasure = {arg start, end, mynotes;

    var noteIndex, subNotes, diff;

    start = (start-1)*mynotes.numerator;

    end = end*mynotes.numerator;

    noteIndex = [];

    mynotes.waits.do({

        arg item, i;

        var tmp = 0;

        tmp = (mynotes.realtime.at(i) - item).round(0.125);

        if(((tmp >= start) && (tmp < end)),{

            noteIndex = noteIndex.add(i);
            noteIndex.postln;

        });

    });

    subNotes = Notes.new;

    subNotes.freqs = mynotes.freqs.copyRange(noteIndex.first,noteIndex.last);
    subNotes.waits = mynotes.waits.copyRange(noteIndex.first,noteIndex.last);
    subNotes.durations = mynotes.durations.copyRange(noteIndex.first,noteIndex.last);
    subNotes.vels = mynotes.vels.copyRange(noteIndex.first,noteIndex.last);

    diff = mynotes.realtime.at(noteIndex.first) - mynotes.waits.at(noteIndex.first);

    diff = diff - start;

    diff.postln;

    if(diff != 0,{

        subNotes.freqs = [0] ++ subNotes.freqs;
        subNotes.waits = subNotes.waits.addFirst(diff);
        subNotes.durations = subNotes.durations.addFirst(diff);
        subNotes.vels = [127] ++ subNotes.vels;

    });

    diff = (mynotes.realtime.at(noteIndex.last)).round(0.125) - end;

    if(diff != 0,{

        subNotes.waits = subNotes.waits.put(subNotes.waits.size - 1,
            subNotes.waits.last + diff);

    });

    subNotes.numerator = mynotes.numerator;

    subNotes.init;
};
