MidiFactory {

    var <>midifile,<>timeSig, <>bpm, <>numerator, <>midipattern;

    *new {arg fileRead;625

        ^super.new.init(fileRead);
    }

    init {arg filer;

        midifile = SimpleMIDIFile.read(filer);

        bpm = midifile.tempo;

        timeSig = ((midifile.timeSignatures).last).at(1);

        numerator = (timeSig.split($/)).first.asFloat;

        midipattern = (midifile.p).iter.all(());

    }

    getTrack{arg channel = 0,trackNumber = 1;

        var trk, mynotes,endif, offset, hold;

        trk = midipattern.select({arg item,i;item.at('chan') == channel});


        mynotes = Notes.new;

        (midifile.noteOnEvents (channel,trackNumber)).do(
            {
                arg item,i;

                mynotes.vels = mynotes.vels.add(item.at(5))
        });

        trk.do(
            {
                arg item,i;

                mynotes.freqs  = mynotes.freqs.add(item.at('midinote'))
        });

        trk.do(
            {
                arg item,i;

                mynotes.waits = mynotes.waits.add(0.00 + ((bpm/60) *item.at('dur')))
        });


        trk.do(
            {
                arg item,i;

                mynotes.durations = mynotes.durations.add(0.00 + ((bpm/60) *item.at('sustain')))

			});

		endif = ((midifile.metaEvents.select({arg item,i;(item.at(0) == trackNumber) && item.at(2) == 'endOfTrack' }))[0])[1];

		endif = endif - (midifile.noteOnEvents(channel, trackNumber).last)[1];

		endif = endif/midifile.division;

		mynotes.waits = mynotes.waits.put(	mynotes.waits.size - 1 ,endif);

        mynotes.numerator = numerator;

        mynotes = mynotes.init;

        ^mynotes;

    }
}
