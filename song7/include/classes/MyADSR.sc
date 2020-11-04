MyADSR {

	var <>name, <>attack, <>release, <>decay, <>sustain,
	<>attacks, //<>releases,
	<>decays,
	<>att,<>rel,<>dec;

	*new {arg at = 0.5, dc = 0.0, ss = 1.0, re = 0.5, nme = "My Adsr";
		^super.new.init(at, dc, ss, re, nme);
	}

	init {arg at, dc, ss, re, nme;

		this.attack = at;
		this.decay =  dc;
		this.sustain = ss;
		this.release = re;
		this.name = nme;

		if(attacks == nil,
			{attacks = [1.0];});

		if(releases == nil,
			{releases = [1.0];});

		if(decays == nil,
			{decays = [1.0];});

		this.calcAttack.value;
		this.calcRelease.value;
		this.calcDecay.value;
	}


	setADSR
	{
		arg env;

		"set Attack".postln;
		env.set(\attack,attack*att.next);

		"set Decay".postln;
		env.set(\decay,decay*dec.next);
		"set Sustain".postln;
		env.set(\sustain,sustain);

		"set Release".postln;
		env.set(\release,release);
	}

	setfADSR
	{
		arg env;
		env.set(\fattack,attack*att.next);
		env.set(\fdecay,decay*dec.next);
		env.set(\fsustain,sustain);
		env.set(\frelease,release);
	}


	calcAttack {
		var lazy;

		lazy = Plazy({
			Pseq(attacks,1);
		});

		att = Pn(lazy,inf).asStream;
	}

	// calcRelease {
	// 	var lazy;
	//
	// 	lazy = Plazy({
	// 		Pseq(releases,1);
	// 	});
	//
	// 	rel = Pn(lazy,inf).asStream;
	// }
	//

	calcDecay {
		var lazy;

		lazy = Plazy({
			Pseq(decays,1);
		});

		dec = Pn(lazy,inf).asStream;
	}

	gui {

		var envWin, nb1, sl1, nb2, sl2, nb3, sl3, nb4, sl4, nb5, sl5, nb6, sl6;

		envWin = Window.new(name,Rect(200,200,305,155)).front;
		StaticText( envWin, Rect(4, 4, 50, 25 )).align_( \left ).string_( "Atk:" );

		nb1 = NumberBox(envWin, Rect(240, 4, 53, 25)).value_(attack);
		sl1 = Slider(envWin, Rect(35, 9, 200, 15))
		.value_(nb1.value/10)
		.action_({
			nb1.value_(sl1.value * 10);
			attack = nb1.value;
		});

		nb1.action_({
			sl1.value_(nb1.value/10);
			attack = sl1.value;
		});


		StaticText( envWin, Rect(4, 32, 50, 25 )).align_( \left ).string_( "Dcy:" );
		nb2 = NumberBox(envWin, Rect(240, 30, 53, 25)).value_(decay);

		sl2 = Slider(envWin, Rect(35, 37, 200, 15))
		.value_(nb2.value/10)
		.action_({
			nb2.value_(sl2.value * 10);
			decay = nb2.value;
		});

		nb2.action_({

			sl2.value_(nb2.value/10);
			decay = sl2.value;
		});


		StaticText( envWin, Rect(4, 58, 50, 25 )).align_( \left ).string_( "Sus:" );
		nb3 = NumberBox(envWin, Rect(240, 58, 53, 25)).value_(sustain);

		sl3 = Slider(envWin, Rect(35, 63, 200, 15))
		.value_(1)
		.action_({
			nb3.value_(sl3.value);
			sustain = nb3.value;
		});

		nb3.action_({
			sl3.value_(nb3.value);
			sustain = nb3.value;
		});

		StaticText( envWin, Rect(4, 84, 50, 25 )).align_( \left ).string_( "Rel:" );
		nb4 = NumberBox(envWin, Rect(240, 84, 53, 25)).value_(release);

		sl4 = Slider(envWin, Rect(35, 89, 200, 15))
		.value_(nb4.value/10)
		.action_({
			nb4.value_(sl4.value * 10);
			release = nb4.value;
		});

		nb4.action_({
			sl4.value_(nb4.value/10);
			release = nb4.value;
		});

	}

}