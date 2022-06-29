// Standard Setup for all SC projects

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

~out0 = MIDIOut(0);
~out0.latency = 0;

~out1 = MIDIOut(1);
~out1.latency = 0;

~out2 = MIDIOut(2);
~out2.latency = 0;

~out3 = MIDIOut(3);
~out3.latency = 0;

//~displayCC.free;
//~displayCC = MIDIdef.cc(\displayCC, {arg ...args; args.postln}); // display CC


~rp = {};

~startTimer = {arg bpm = 60, num = 4;

    var timeloop,numb;

	numb = num;

    t = TempoClock.default.tempo = bpm / 60;

    "Loop started".postln;

    timeloop = {

        arg beat;

        (((beat-1)%numb) + 1).post;"  ".post;


        if(beat % numb == 0, {

            " -- ".postln;

            Routine.run({

                s.sync;

                ~rp.value;

                ~rp={};

				numb = num;

            });

        });
    };

    t.schedAbs(

        0.00, // evaluate this immediately
        {
            arg ...args;

            timeloop.value(args[0]); // pass the beat number to our function

            1.0               // do it all again on the next beat
    });
};
