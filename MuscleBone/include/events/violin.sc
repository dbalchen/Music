
~violinInit = {

	~violin.notes.waits = [38.0,1.0,1.0];
	~violin.notes.freqs = [0,77,80];
	~violin.notes.probs = [0,1,1];
	~violin.notes.durations = [1.0,1.0,1.0];

	~violin2.notes.waits = [38.0,1.0,1.0];
	~violin2.notes.freqs = [0,77,80];
	~violin2.notes.probs = [0,1,1];
	~violin2.notes.durations = [1.0,1.0,1.0];


};

~violinInit.value;
~violin.notes.init;
~violin2.notes.init;
~violin.notes.lag = 0.012;



// Part 4
~violin4 = {

	~violin.notes.waits = [6.0,1.0,1.0,6.0,1.0,1.0,6.0,1.0,1.0,4.0,2.0,2.0];
	~violin.notes.freqs = [82,80,75,77,80,79,77,80,73,75,79,77];
	~violin.notes.probs = [1,1,1,1,1,1,1,1,1,1,1,1];
	~violin.notes.durations = [6.0,1.0,1.0,6.0,1.0,1.0,6.0,1.0,1.0,4.0,2.0,2.0];

	~violin2.notes.waits = [6.0,1.0,1.0,6.0,1.0,1.0,6.0,1.0,1.0,4.0,2.0,2.0];
	~violin2.notes.freqs = [82,80,75,77,80,79,77,80,73,75,79,77];
	~violin2.notes.probs = [1,1,1,1,1,1,1,1,1,1,1,1];
	~violin2.notes.durations = [6.0,1.0,1.0,6.0,1.0,1.0,6.0,1.0,1.0,4.0,2.0,2.0];
	~violin.notes.lag = 0.012;


};

// Part 5
~violin5 = {

	~violin.notes.waits = [6.0,1.0,1.0,6.0,1.0,1.0,6.0,1.0,1.0,6.0,1.0,1.0];
	~violin.notes.freqs = [82,80,75,77,80,75,74,72,68,70,74,77];
	~violin.notes.probs = [1,1,1,1,1,1,1,1,1,1,1,1];
	~violin.notes.durations = [6.0,1.0,1.0,6.0,1.0,1.0,6.0,1.0,1.0,6.0,1.0,1.0];

	~violin2.notes.waits = [6.0,1.0,1.0,6.0,1.0,1.0,6.0,1.0,1.0,6.0,1.0,1.0];
	~violin2.notes.freqs = [82,80,75,77,80,75,74,72,68,70,74,77];
	~violin2.notes.probs = [1,1,1,1,1,1,1,1,1,1,1,1];
	~violin2.notes.durations = [6.0,1.0,1.0,6.0,1.0,1.0,6.0,1.0,1.0,6.0,1.0,1.0];
	~violin.notes.lag = 0.012;
};

~violin6 = {

	~violin.notes.waits = [2.0,2.0,2.0,2.0,8.0,4.0,2.0,2.0,8.0];
	~violin.notes.freqs = [79,75,72,75,74,75,77,79,72];
	~violin.notes.probs = [1,1,1,1,1,1,1,1,1];
	~violin.notes.durations = [2.0,2.0,2.0,2.0,8.0,4.0,2.0,2.0,8.0];

	~violin2.notes.waits = [2.0,2.0,2.0,2.0,8.0,4.0,2.0,2.0,8.0];
	~violin2.notes.freqs = [79,75,72,75,74,75,77,79,72];
	~violin2.notes.probs = [1,1,1,1,1,1,1,1,1];
	~violin2.notes.durations = [2.0,2.0,2.0,2.0,8.0,4.0,2.0,2.0,8.0];
	~violin.notes.lag = 0.14;
};




~violin7 = {
	~violin.notes.waits = [4.0,2.0,2.0,8.0,4.0,4.0,4.0,4.0];
	~violin.notes.freqs = [80,82,77,79,77,80,74,77];
	~violin.notes.probs = [1,1,1,1,1,1,1,1];
	~violin.notes.durations = [4.0,2.0,2.0,8.0,4.0,4.0,4.0,4.0];

	~violin2.notes.waits = [4.0,2.0,2.0,8.0,4.0,4.0,4.0,4.0];
	~violin2.notes.freqs = [80,82,77,79,77,80,74,77];
	~violin2.notes.probs = [1,1,1,1,1,1,1,1];
	~violin2.notes.durations = [4.0,2.0,2.0,8.0,4.0,4.0,4.0,4.0];
	~violin.notes.lag = 0.14;
};

~violin8 = {
	~violin.notes.waits = [6.0,2.0,6.0,2.0,4.0,2.0,2.0,8.0];
	~violin.notes.freqs = [77,78,77,80,82,79,75,77];
	~violin.notes.durations = [6.0,2.0,6.0,2.0,4.0,2.0,2.0,8.0];

	~violin2.notes.waits = [6.0,2.0,6.0,2.0,4.0,2.0,2.0,8.0];
	~violin2.notes.freqs = [77,78,77,80,82,79,75,77];
	~violin2.notes.durations = [6.0,2.0,6.0,2.0,4.0,2.0,2.0,8.0];
	~violin.notes.lag = 0.09;

};

~violin9 = {

	~violin.notes.waits = [6.0,2.0,6.0,2.0,4.0,2.0,2.0,8.0];
	~violin.notes.freqs = [77,78,77,80,82,84,80,82];
	~violin.notes.probs = [1,1,1,1,1,1,1,1];
	~violin.notes.durations = [6.0,2.0,6.0,2.0,4.0,2.0,2.0,8.0];

	~violin2.notes.waits = [6.0,2.0,6.0,2.0,4.0,2.0,2.0,8.0];
	~violin2.notes.freqs = [77,78,77,80,82,84,80,82];
	~violin2.notes.probs = [1,1,1,1,1,1,1,1];
	~violin2.notes.durations = [6.0,2.0,6.0,2.0,4.0,2.0,2.0,8.0];
	~violin.notes.lag = 0.140;
};

~violin10 = {

	~violin.notes.waits = [2.0,2.0,2.0,2.0,8.0,4.0,2.0,2.0,6.0,2.0];
	~violin.notes.freqs = [79,75,72,75,74,75,77,79,72,77];
	~violin.notes.probs = [1,1,1,1,1,1,1,1,1,1];
	~violin.notes.durations = [2.0,2.0,2.0,2.0,8.0,4.0,1.99,2.0,6.0,2.0];

	~violin2.notes.waits = [2.0,2.0,2.0,2.0,8.0,4.0,2.0,2.0,6.0,2.0];
	~violin2.notes.freqs = [79,75,72,75,74,75,77,79,72,77];
	~violin2.notes.probs = [1,1,1,1,1,1,1,1,1,1];
	~violin2.notes.durations = [2.0,2.0,2.0,2.0,8.0,4.0,1.99,2.0,6.0,2.0];

	~violin.notes.lag = 0.04;
};

~violin11 = {

	~violin.notes.waits = [6.0,1.0,1.0,6.0,1.0,1.0,2.0,2.0,2.0,2.0,6.0,2.0];
	~violin.notes.freqs = [82,80,75,77,80,82,84,80,77,80,82,83];
	~violin.notes.probs = [1,1,1,1,1,1,1,1,1,1,1,1];
	~violin.notes.durations = [6.0,1.0,1.0,6.0,1.0,1.0,1.99,2.0,2.0,2.0,6.0,2.0];

	~violin2.notes.waits = [6.0,1.0,1.0,6.0,1.0,1.0,2.0,2.0,2.0,2.0,6.0,2.0];
	~violin2.notes.freqs = [82,80,75,77,80,82,84,80,77,80,82,83];
	~violin2.notes.probs = [1,1,1,1,1,1,1,1,1,1,1,1];
	~violin2.notes.durations = [6.0,1.0,1.0,6.0,1.0,1.0,1.99,2.0,2.0,2.0,6.0,2.0];
	~violin.notes.lag = 0.03;
};


~violin12 = {

	~violin.notes.waits = [6.0,2.0,6.0,2.0,6.0,2.0,6.0,2.0,6.0,2.0,4.0,4.0,27.0,1.0];
	~violin.notes.freqs = [82,77,82,83,82,77,82,83,82,77,82,80,82,0];
	~violin.notes.probs = [1,1,1,1,1,1,1,1,1,1,1,1,1,1];
	~violin.notes.durations = [6.0,2.0,6.0,2.0,6.0,2.0,6.0,2.0,6.0,2.0,4.0,4.0,12.0,0.92];


	~violin2.notes.waits = [6.0,2.0,6.0,2.0,6.0,2.0,6.0,2.0,6.0,2.0,4.0,4.0,27.0,1.0];
	~violin2.notes.freqs = [82,77,82,83,82,77,82,83,82,77,82,80,82,0];
	~violin2.notes.probs = [1,1,1,1,1,1,1,1,1,1,1,1,1,1];
	~violin2.notes.durations = [6.0,2.0,6.0,2.0,6.0,2.0,6.0,2.0,6.0,2.0,4.0,4.0,12.0,0.92];

	~violin.notes.lag = 0.2;
};
