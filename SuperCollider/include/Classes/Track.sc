// =====================================================================
// MyTrack Class
// =====================================================================


Track {
	var <>notes = nil, <>midiout = nil, <>out = 0,
	<>channel = 0,  <>balance = 0, <>spread = 0,
	<>amp = 1.0, <>transport = nil,
	synthNotes, on, off,<>noteON,<>noteOFF,
	ampCC, balCC;


	*new {arg chanOut,chn = 0, nts = nil;
		^super.new.init(chanOut,chn);
	}

	init {arg chanOut,chn;

		channel = chn;

		notes = Notes.new;
		notes = notes.init;

		midiout = chanOut;

		synthNotes = Array.newClear(128);

		ampCC.free;
		ampCC = MIDIFunc.cc({arg ...args;
			amp = args[0]/127;
			"Channel ".post;
			channel.post;
			" Amp = ".post;
			amp.postln;
		}, 7, channel);

		balCC.free;
		balCC = MIDIFunc.cc({arg ...args;
			balance = (args[0]*(2/127))-1;
			
			"Channel ".post;
			channel.post;
			" Balance = ".post;
			balance.postln;
			
		}, 10, channel);

		noteON = {arg num,vel,chan,src,out,amp,balance;

			var ret;

			num.post;chn.post;vel.post;src.postln;

			ret = Synth("basicSynth");
			ret.set(\freq,num.midicps);
			ret.set(\amp,amp);
			ret.set(\gate,1);
			ret.set(\out,out);

			ret;
		};

		noteOFF =  {arg num,synth;
			synth.set(\gate,0);
		};

		on = MIDIFunc.noteOn({ |veloc, num, chan, src|

			if((chan == channel), {
				synthNotes[num] = noteON.value(num,veloc,chan,src,out,amp,balance);
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
			\amp, Pfunc.new({notes.vels.next}),
			\chan, channel,
			\sustain, Pfunc.new({notes.duration.next}),
			\dur, Pfunc.new({notes.wait.next})
		).play;

		transport.stop;
	}

}
