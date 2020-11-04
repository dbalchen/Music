
~turndown1 = {
	arg vol,cc,chan,src;
	vol = vol/127;
	vol.postln;

	if((chan == 0), {


	});

	if((chan == 1), {

	});

	if((chan == 2), {



	});

	if((chan == 3), {


	});

	if((chan == 4), {


	});

	if((chan == 5), {


	});

	if((chan == 6), {


	});

	if((chan == 7), {


	});

	if((chan == 8), {


	});
	if((chan == 9), {


	});

	if((chan == 10), {

	});

	if((chan == 11), {


	});

	if((chan == 12), {

	});

	if((chan == 13), {


	});

	if((chan == 14), {


	});

	if((chan == 15), {


	});


};

~volCC.free;
~volCC = MIDIdef.cc(\volume, ~turndown1, 7);

~pan1 = {
	arg vol,cc,chan,src;
	vol = (2*(vol/127))-1;

	vol.postln;

	if((chan == 0), {


	});

	if((chan == 1), {

	});

	if((chan == 2), {


	});

	if((chan == 3), {

	});

	if((chan == 4), {

	});

	if((chan == 5), {


	});

	if((chan == 6), {


	});

	if((chan == 7), {


	});

	if((chan == 8), {


	});
	if((chan == 9), {


	});

	if((chan == 10), {
	});

	if((chan == 11), {


	});

	if((chan == 12), {

	});

	if((chan == 13), {


	});

	if((chan == 14), {


	});

	if((chan == 15), {


	});

};

~ballCC.free;
~balCC = MIDIdef.cc(\pan, ~pan1,10); // match cc 1


