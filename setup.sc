
~midiSetup = {
	var inPorts = 1;
	var outPorts = 4;

	MIDIClient.disposeClient;
	MIDIClient.init(inPorts,outPorts); // explicitly intialize the client
	inPorts.do({ arg i;
		MIDIIn.connect(i, MIDIClient.sources.at(i));
	});
};

~midiSetup.value;

~synth1 = MIDIOut(0);
~synth1.latency = 0;

~synth2 = MIDIOut(1);
~synth2.latency = 0;

~synth3 = MIDIOut(2);
~synth3.latency = 0;

~synth4 = MIDIOut(3);
~synth4.latency = 0;

// Timer code

~startTimer = {arg num;

	t = TempoClock.default.tempo = num / 60;

	~onbeat = 4;
	~rp = {};

	a = {
		
		arg beat;
		var beat4 = (beat%4);
		beat4.postln;

		
		if(beat % ~onbeat == 0, {
			Routine.run({
				s.sync;
				~rp.value;
				~rp={};
				~onbeat = 4;
			});
		});
	};

	t.schedAbs(
		0.00, // evaluate this immediately
		{
			arg ...args;
			a.value(args[0]); // pass the beat number to our function
			1.0               // do it all again on the next beat
		}
	);
};


~channel0 = {arg num,vel;^nil;};
~channel1 = {arg num,vel;^nil;};
~channel2 = {arg num,vel;^nil;};
~channel3 = {arg num,vel;^nil;};
~channel4 = {arg num,vel;^nil;};
~channel5 = {arg num,vel;^nil;};
~channel6 = {arg num,vel;^nil;};
~channel7 = {arg num,vel;^nil;};
~channel8 = {arg num,vel;^nil;};
~channel9 = {arg num,vel;^nil;};
~channel10 = {arg num,vel;^nil;};
~channel11 = {arg num,vel;^nil;};
~channel12 = {arg num,vel;^nil;};
~channel13 = {arg num,vel;^nil;};
~channel14 = {arg num,vel;^nil;};


~channel0off = {arg num,vel;^nil;};
~channel1off = {arg num,vel;^nil;};
~channel2off = {arg num,vel;^nil;};
~channel3off = {arg num,vel;^nil;};
~channel4off = {arg num,vel;^nil;};
~channel5off = {arg num,vel;^nil;};
~channel6off = {arg num,vel;^nil;};
~channel7off = {arg num,vel;^nil;};
~channel8off = {arg num,vel;^nil;};
~channel9off = {arg num,vel;^nil;};
~channel10off = {arg num,vel;^nil;};
~channel11off = {arg num,vel;^nil;};
~channel12off = {arg num,vel;^nil;};
~channel13off = {arg num,vel;^nil;};
~channel14off = {arg num,vel;^nil;};


~bend = Array.newClear(16);
~start = {^nil;};

~zz=Array.newClear(128);

for(0,~zz.size - 1,

	{ arg i;
		var za;
		za = Array.newClear(16);
		~zz.put(i,za);
	}
);

MIDIIn.noteOn = {arg src, chan, num, vel;
	var x,a;

	//src.postln;
	vel = vel.linlin(0,127,0.01,1);

	if((chan == 0), {
		x = ~channel0.value(num,vel);

	});

	if((chan == 1), {
		x = ~channel1.value(num,vel);

	});
	if((chan == 2), {
		x = ~channel2.value(num,vel);

	});

	if((chan == 3), {
		x = ~channel3.value(num,vel);

	});

	if((chan == 4), {
		x = ~channel4.value(num,vel);

	});

	if((chan == 5), {
		x = ~channel5.value(num,vel);

	});

	if((chan == 6), {
		x = ~channel6.value(num,vel);

	});

	if((chan == 7), {
		x = ~channel7.value(num,vel);

	});

	if((chan == 8), {
		x = ~channel8.value(num,vel);

	});
	if((chan == 9), {
		x = ~channel9.value(num,vel);

	});

	if((chan == 10), {
		x = ~channel10.value(num,vel);

	});

	if((chan == 11), {
		x = ~channel11.value(num,vel);

	});

	if((chan == 12), {
		x = ~channel12.value(num,vel);

	});

	if((chan == 13), {
		x = ~channel13.value(num,vel);

	});

	if((chan == 14), {
		x = ~channel14.value(num,vel);

	});

	if((chan == 15), {

		~start.value;
		x = 15;

	});

	a = ~zz.at(num);
	a.put(chan,x);
	~zz.put(num,a);
};


MIDIIn.bend = { arg src,chan,val;
	var x;

	~bend[chan] = val.linlin(0,16383,-5,5);
	~zz.do{arg note;
		x = note[chan];
		x.set(\bend,~bend[chan]);
		note[chan] = x;
	}

};


MIDIIn.noteOff = { arg src,chan,num,vel;
	var a,b,x;
	a = ~zz.at(num);
	b = a.at(chan);

	if(b != nil,{ b.set(\gate, 0);});

	if((chan == 0), {
		x = ~channel0off.value(num,vel);

	});

	if((chan == 1), {
		x = ~channel1off.value(num,vel);

	});
	if((chan == 2), {
		x = ~channel2off.value(num,vel);

	});

	if((chan == 3), {
		x = ~channel3off.value(num,vel);

	});

	if((chan == 4), {
		x = ~channel4off.value(num,vel);

	});

	if((chan == 5), {
		x = ~channel5off.value(num,vel);

	});

	if((chan == 6), {
		x = ~channel6off.value(num,vel);

	});

	if((chan == 7), {
		x = ~channel7off.value(num,vel);

	});

	if((chan == 8), {
		x = ~channel8off.value(num,vel);

	});
	if((chan == 9), {
		x = ~channel9off.value(num,vel);

	});

	if((chan == 10), {
		x = ~channel10off.value(num,vel);

	});

	if((chan == 11), {
		x = ~channel11off.value(num,vel);

	});

	if((chan == 12), {
		x = ~channel12off.value(num,vel);

	});

	if((chan == 13), {
		x = ~channel13off.value(num,vel);

	});

	if((chan == 14), {
		x = ~channel14off.value(num,vel);

	});

	if((chan == 15), {

	});


};

/*
MIDIIn.polytouch = { arg src, chan, num, vel;


};

MIDIIn.control = { arg src, chan, num, val;

};

MIDIIn.program = { arg src, chan, prog;


};
*/

~displayCC.free;
~displayCC = MIDIdef.cc(\displayCC, {arg ...args; args.postln}); // display CC