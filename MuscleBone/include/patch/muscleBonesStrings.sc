// =====================================================================
// Muscle and Bone Workspace
// =====================================================================

Server.default.makeGui;

"/home/dbalchen/Music/song7/include/setup.sc".load;

t = TempoClock.default.tempo = 120/60;

// Instrument Setup

"/home/dbalchen/Music/song7/include/synths/evenVCO.sc".load;
"/home/dbalchen/Music/song7/include/synths/evenVCOmono.sc".load;

~mytrack = Track.new(~out0,0);

~vca = MyADSR.new(1.25,2.6,0.6,0.5,"VCA");

// ~vca.gui;

~vcf = MyADSR.new(1.0,2.0,0.6,0.8,"VCF");

// ~vcf.gui;

~evenVcoMono = {arg num,chan, vel = 1,out;
	var ret,tidx;
	num.postln;

	tidx = (~wavetables.size/120)* num;

	~evo.set(\freq,num.midicps);
	~evo.set(\idx,tidx);
	~evo.set(\ss,~wavebuff);

	~evo.set(\lagtime,0.15);

	ret = Synth("monoPolySynth",addAction: \addToTail);

	~vca.setADSR(ret);
	~vcf.setfADSR(ret);

	ret.set(\gate,1);
	ret.set(\hpf,120);
	ret.set(\sigIn,~evoOut);
	ret.set(\out,out);
	ret.set(\amp,0.75);
	ret;
};


~mytrack.noteON = ~evenVcoMono;

// Track 2

~mytrack2 = Track.new(~out0,1);

~vca2 = MyADSR.new(1.8,3.0,0.7,0.8,"VCA");

//~vca2.gui;

~vcf2 = MyADSR.new(1.0,1.5,0.8,0.9,"VCF");

//~vcf2.gui;

~evenVCOpoly2 = {arg num, vel = 1,src,out = 0;
    var ret,tidx;
    tidx = ((~windex-1)/120)* num;

    ret = Synth("evenVCO");
    ret.set(\ss,~wavebuff);
    ret.set(\freq,num.midicps);
    ret.set(\idx,tidx);

    ~vca2.setADSR(ret);
    ~vcf2.setfADSR(ret);

    ret.set(\out,out);
    ret.set(\gate,1);
    ret.set(\cutoff,9000);
    ret.set(\gain,0.85);
    ret.set(\aoc,0.750);
    ret.set(\hpf,325);
    vel = (vel/127)*0.60;
    ret.set(\amp,1.20);
    ret;
};


~mytrack2.noteON = ~evenVCOpoly2;

// Track 3


~mytrack3 = Track.new(~out0,2);

~vca3 = MyADSR.new(1.0,2.0,0.8,0.8,"VCA");

//~vca3.gui;

~vcf3 = MyADSR.new(0.5,1.5,0.8,0.90,"VCF");

//~vcf3.gui;


~evenVCOpoly3 = {arg num, vel = 1,src,out = 0;
    var ret,tidx;
    tidx = ((~windex-1)/120)* num;

    ret = Synth("evenVCO");
    ret.set(\ss,~wavebuff);
    ret.set(\freq,num.midicps);
    ret.set(\idx,tidx);

    ~vca3.setADSR(ret);
    ~vcf3.setfADSR(ret);

    ret.set(\out,out);
    ret.set(\gate,1);
    ret.set(\cutoff,6500);
    ret.set(\gain,1.85);
    ret.set(\aoc,0.50);
    ret.set(\hpf,225);
    vel = (vel/127)*0.60;
    ret.set(\amp,0.9);
    ret;
};


~mytrack3.noteON = ~evenVCOpoly3;



