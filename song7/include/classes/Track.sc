// =====================================================================
// MyTrack Class
// =====================================================================


Track {
	var <>notes = nil, <>midiout = nil, <>out = 0,
	<>channel = 0,  <>balance = 0, <>spread = 0,
	<>amp = 1.0, <>transport = nil,
	synthNotes, on, off,<>noteON,<>noteOFF;

	*new {arg chanOut,chn = 0, nts = nil;
		^super.new.init(chanOut,chn);
	}

	init {arg chanOut,chn;

		channel = chn;

		notes = MyNotes.new;
		notes.init;

		midiout = chanOut;

		synthNotes = Array.newClear(128);

		noteON = {arg num,vel,src,out;

			var ret;

			num.post;chn.post;vel.post;src.postln;

			ret = Synth("basicSynth");
			ret.set(\freq,num.midicps);
			ret.set(\gate,1);
			ret.set(\out,out);

			ret;

		};

		noteOFF =  {arg num,synth;
			synth.set(\gate,0);
		};

		on = MIDIFunc.noteOn({ |veloc, num, chan, src|

			if((chan == channel), {
				synthNotes[num] = noteON.value(num,veloc,src,out);
			})
		});

		off = MIDIFunc.noteOff({ |veloc, num, chan, src|

			if((chan == channel), {
				noteOFF.value(num,synthNotes[num]);
				synthNotes[num].release;
			});
		});


		this.setup();
	}


	setup {

		if(transport != nil,
			{transport.stop;transport = nil; });

		transport = Pbind(\type, \midi,
			\midiout, midiout,
			\midicmd, \noteOn,
			\note,  Pfunc.new({notes.freq.next}- 60),
			\amp, amp,
			\chan, channel,
			\sustain, Pfunc.new({notes.duration.next}),
			\dur, Pfunc.new({notes.wait.next})
		).play;

		transport.stop;
	}

}
