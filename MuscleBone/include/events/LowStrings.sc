~lowStrings = MyTrack.new(~synth1,0);
~lowStrings.amp = 0.15;

~lowStringsInit = {
	~lowStrings.notes.freqs = [0,44];
	~lowStrings.notes.waits = [30.0,2.0];
	~lowStrings.notes.durations = [1.0,2.0];
	~lowStrings.notes.probs = [0,1];
	~lowStrings.notes.lag = 0.9;
};

~lowStringsInit.value();
~lowStrings.notes.init;


~lowStrings2 = {

	~lowStrings.notes.freqs = [46,47,46,44,46,47,46,44];
	~lowStrings.notes.waits = [6.0,2.0,6.0,2.0,6.0,2.0,6.0,2.0];
	~lowStrings.notes.durations = [6.0,2.0,6.0,2.0,
		6.0,2.0,6.0,2.0];
	~lowStrings.notes.probs = [1,1,1,1,1,1,1,1];
	~lowStrings.notes.lag =  1.2;

};

~lowStrings3 = {

	~lowStrings.notes.waits = [6.0,2.0,6.0,2.0,6.0,2.0,16.0];
	~lowStrings.notes.freqs = [46,47,46,44,46,44,46];
	~lowStrings.notes.probs = [1,1,1,1,1,1,1];
	~lowStrings.notes.durations = [6.0,2.0,6.0,2.0,6.0,2.0,12.0];
	~lowStrings.notes.lag = 0.9;
};

~lowStrings4 = {

	~lowStrings.notes.waits = [6.0,2.0,6.0,2.0,6.0,2.0,6.0,2.0];
	~lowStrings.notes.freqs = [46,47,46,44,46,47,46,44];
	~lowStrings.notes.probs = [1,1,1,1,1,1,1,1];
	~lowStrings.notes.durations = [6.0,2.0,6.0,2.0,6.0,2.0,6.0,2.0];
	~lowStrings.notes.lag = 0.9
};

~lowStrings5 = {
	~lowStrings.notes.lag = 0.9;
	~lowStrings.notes.waits = [6.0,2.0,6.0,2.0,6.0,2.0,8.0];
	~lowStrings.notes.freqs = [46,47,46,41,46,44,46];
	~lowStrings.notes.probs = [1,1,1,1,1,1,1];
	~lowStrings.notes.durations = [6.0,2.0,6.0,1.97,6.0,2,8];
	~lowStrings.notes.lag = 0.9;

};


~lowStrings6 = {
	~lowStrings.notes.waits = [6.0,2.0,6.0,2.0,6.0,2.0,8.0];
	~lowStrings.notes.freqs = [48,43,46,41,44,46,41];
	~lowStrings.notes.probs = [1,1,1,1,1,1,1];
	~lowStrings.notes.durations = [6.0,2,6.0,2,6.0,1.98,8];
	~lowStrings.notes.lag = 0.5;
};


~lowStrings7 = {
	~lowStrings.notes.waits = [4.0,4.0,6.0,2.0,4.0,4.0,4.0,4.0];
	~lowStrings.notes.freqs = [47,49,46,39,44,47,41,44];
	~lowStrings.notes.probs = [1,1,1,1,1,1,1,1];
	~lowStrings.notes.durations = [4.0,4.0,6.0,2.0,4.0,4.0,4.0,4.0];
	~lowStrings.notes.probs = [1,1,1,1,1,1,1,1];
	~lowStrings.notes.lag = 0.5;

};

~lowStrings8 = {
	~lowStrings.notes.waits = [6.0,2.0,6.0,2.0,4.0,4.0,6.0,2.0];
	~lowStrings.notes.freqs = [46,47,46,41,46,44,41,44];
	~lowStrings.notes.probs = [1,1,1,1,1,1,1,1];
	~lowStrings.notes.durations = [6.0,2.0,6.0,2.0,4.0,4.0,6.0,2.0];
	~lowStrings.notes.lag = 0.5;

};

~lowStrings9 = {
	~lowStrings.notes.waits = [6.0,2.0,4.0,2.0,2.0,4.0,4.0,4.0,2.0,2.0];
	~lowStrings.notes.freqs = [46,47,46,41,46,48,41,46,41,44];
	~lowStrings.notes.probs = [1,1,1,1,1,1,1,1,1,1];
	~lowStrings.notes.durations = [6.0,2.0,4.0,2.0,2.0,4.0,4.0,4.0,2.0,2.0];
	~lowStrings.notes.lag = 0.5;

};

~lowStrings10 = {
	~lowStrings.notes.waits = [2.0,2.0,2.0,2.0,4.0,2.0,2.0,4.0,2.0,2.0,4.0,2.0,2.0];
	~lowStrings.notes.freqs = [48,44,41,44,46,41,46,44,41,39,41,41,44];
	~lowStrings.notes.probs = [1,1,1,1,1,1,1,1,1,1,1,1,1];
	~lowStrings.notes.durations = [2.0,2.0,2.0,2.0,4,2.0,2.0,4,2,2,4,2,2.0];
	~lowStrings.notes.lag = 0.4;
};

~lowStrings11 = {

	~lowStrings.notes.waits = [6.0,2.0,4.0,2.0,2.0,4.0,4.0,6.0,2.0];
	~lowStrings.notes.freqs = [46,47,46,41,46,48,41,46,47];
	~lowStrings.notes.probs = [1,1,1,1,1,1,1,1,1];
	~lowStrings.notes.durations = [6.0,2.0,4.0,2.0,2.0,4.0,4.0,6.0,2.0];
	~lowStrings.notes.lag = 0.9;

};


~lowStrings12 = {

	~lowStrings.notes.waits = [6.0,2.0,6.0,2.0,6.0,2.0,6.0,2.0,6.0,2.0,4.0,4.0,27.0,1.0];
	~lowStrings.notes.freqs = [46,41,46,47,46,41,46,47,46,41,46,44,46,0];
	~lowStrings.notes.probs = [1,1,1,1,1,1,1,1,1,1,1,1,1,1];
	~lowStrings.notes.durations = [5.99,2.0,5.99,2.0,5.99,2.0,6.0,2.0,6.0,2.0,4.0,4.0,12.0,1.0];
	~lowStrings.notes.lag = 0.6;

};

