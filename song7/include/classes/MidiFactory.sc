MidiFactory {

  var <>midifile,<>timeSig, <>bpm, <>numerator, <>midipattern;

  *new {arg fileRead;

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

    endif = (midifile.noteOffEvents(channel, trackNumber).last)[1];

    endif = endif/midifile.division;

    endif = endif%numerator;

    if(endif != 0,{	endif = (numerator - endif);});

    endif =  endif + mynotes.durations.last;

    mynotes.waits = mynotes.waits.put(	mynotes.waits.size - 1 ,endif);


    // Clean up zero waits

    hold = mynotes.waits.deepCopy;

    offset = 0;

    hold.do ({arg item,i;


	if(item == 0,{
		
	    mynotes.waits.removeAt(i-offset);
	    mynotes.vels.removeAt(i-offset);
	    mynotes.freqs.removeAt(i-offset);
	    mynotes.durations.removeAt(i-offset);
	    offset = offset + 1;
		
	  };);

      });

    mynotes = mynotes.init;

    ^mynotes;

  }
}
