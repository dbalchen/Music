"/home/dbalchen/workspace/SuperCollider/loadSamples.sc".load;

/*
~cellosounds.free;
~cellosounds = ~loadSamples.value("/home/dbalchen/Music/Samples/strings/Celli");

~celloTemplate = {
arg x,num,sounds;
x.set(\midi,num);
if(num < 36, {x.set(\imp,0.5)},{x.set(\imp,2)});

if(num < 39,{x.set(\bufnum, sounds.at(0));x.set(\basef,36);});
if((num >= 39) && (num < 42) ,{x.set(\bufnum, sounds.at(1));x.set(\basef,39);});
if((num >= 42) && (num < 45) ,{x.set(\bufnum, sounds.at(2));x.set(\basef,42);});
if((num >= 45) && (num < 48) ,{x.set(\bufnum, sounds.at(3));x.set(\basef,45);});
if((num >= 48) && (num < 51) ,{x.set(\bufnum, sounds.at(4));x.set(\basef,48);});
if((num >= 51) && (num < 54) ,{x.set(\bufnum, sounds.at(5));x.set(\basef,51);});
if((num >= 54) && (num < 57) ,{x.set(\bufnum, sounds.at(6));x.set(\basef,54);});
if((num >= 57) && (num < 60) ,{x.set(\bufnum, sounds.at(7));x.set(\basef,57);});
if((num >= 60) && (num < 63) ,{x.set(\bufnum, sounds.at(8));x.set(\basef,60);});
if((num >= 63) && (num < 66) ,{x.set(\bufnum, sounds.at(9));x.set(\basef,63);});
if((num >= 66) && (num < 69) ,{x.set(\bufnum, sounds.at(10));x.set(\basef,66);});
if((num >= 69) && (num < 72) ,{x.set(\bufnum, sounds.at(11));x.set(\basef,69);});
if((num >= 72),{x.set(\bufnum, sounds.at(12));x.set(\basef,72);});
};

// ret = ~eSampler.value(~cello,~cellosounds,~celloTemplate,num);

*/


~cellosounds.free;
~cellosounds = ~loadSamples.value("/home/dbalchen/Music/Samples/string2/cellos");


~celloTemplate = {
	arg x,num,sounds;
	x.set(\midi,num);

	if(num < 36, {x.set(\imp,0.5)},{x.set(\imp,2)});

	if(num < 38,{x.set(\bufnum, sounds.at(0));x.set(\basef,36);});
	if((num >= 38) && (num < 41) ,{x.set(\bufnum, sounds.at(1));x.set(\basef,40);});
	if((num >= 41) && (num < 45) ,{x.set(\bufnum, sounds.at(2));x.set(\basef,43);});
	if((num >= 45) && (num < 48) ,{x.set(\bufnum, sounds.at(3));x.set(\basef,47);});
	if((num >= 48) && (num < 51) ,{x.set(\bufnum, sounds.at(4));x.set(\basef,50);});
	if((num >= 51) && (num < 55) ,{x.set(\bufnum, sounds.at(5));x.set(\basef,53);});
	if((num >= 55) && (num < 58) ,{x.set(\bufnum, sounds.at(6));x.set(\basef,57);});
	if((num >= 58) && (num < 62) ,{x.set(\bufnum, sounds.at(7));x.set(\basef,60);});
	if((num >= 62) && (num < 66) ,{x.set(\bufnum, sounds.at(8));x.set(\basef,64);});
	if((num >= 65) && (num < 69) ,{x.set(\bufnum, sounds.at(9));x.set(\basef,67);});
	if((num >= 69) && (num < 72) ,{x.set(\bufnum, sounds.at(10));x.set(\basef,71);});
	if((num >= 72) && (num < 75) ,{x.set(\bufnum, sounds.at(11));x.set(\basef,74);});
	if((num >= 75),{x.set(\bufnum, sounds.at(12));x.set(\basef,77);});
};





~violinsounds.free;
~violinsounds = ~loadSamples.value("/home/dbalchen/Music/Samples/strings/1stViolins");


~violinTemplate = {
	arg x,num,sounds;
	x.set(\midi,num);
	if(num < 55, {x.set(\imp,0.5)},{x.set(\imp,3)});

	if(num < 58,{x.set(\bufnum, sounds.at(0));x.set(\basef,55);});
	if((num >= 58) && (num < 61) ,{x.set(\bufnum,  sounds.at(1));x.set(\basef,58);});
	if((num >= 61) && (num < 64) ,{x.set(\bufnum,  sounds.at(2));x.set(\basef,61);});
	if((num >= 64) && (num < 67) ,{x.set(\bufnum,  sounds.at(3));x.set(\basef,64);});
	if((num >= 67) && (num < 70) ,{x.set(\bufnum,  sounds.at(4));x.set(\basef,67);});
	if((num >= 70) && (num < 73) ,{x.set(\bufnum,  sounds.at(5));x.set(\basef,70);});
	if((num >= 73) && (num < 76) ,{x.set(\bufnum,  sounds.at(6));x.set(\basef,73);});
	if((num >= 76) && (num < 79) ,{x.set(\bufnum,  sounds.at(7));x.set(\basef,76);});
	if((num >= 79) && (num < 82) ,{x.set(\bufnum,  sounds.at(8));x.set(\basef,79);});
	if((num >= 82) && (num < 85) ,{x.set(\bufnum,  sounds.at(9));x.set(\basef,82);});
	if((num >= 85) && (num < 88) ,{x.set(\bufnum,  sounds.at(10));x.set(\basef,85);});
	if((num >= 88) && (num < 91) ,{x.set(\bufnum,  sounds.at(11));x.set(\basef,88);});
	if((num >= 91) && (num < 94) ,{x.set(\bufnum,  sounds.at(12));x.set(\basef,91);});
	if((num >= 94),{x.set(\bufnum, sounds.at(13));x.set(\basef,94);});
};


//ret = ~eSampler.value(~violin,~violinsounds,~violinTemplate,num);


~violasounds.free;
~violasounds = ~loadSamples.value("/home/dbalchen/Music/Samples/strings/Violas");


~violaTemplate = {
	arg x,num,sounds;

	x.set(\midi,num);
	if(num < 48, {x.set(\imp,0.5)},{x.set(\imp,2)});

	if(num < 51,{x.set(\bufnum, sounds.at(0));x.set(\basef,48);});
	if((num >= 51) && (num < 54) ,{x.set(\bufnum, sounds.at(1));x.set(\basef,51);});
	if((num >= 54) && (num < 57) ,{x.set(\bufnum, sounds.at(2));x.set(\basef,54);});
	if((num >= 57) && (num < 60) ,{x.set(\bufnum, sounds.at(3));x.set(\basef,57);});
	if((num >= 60) && (num < 63) ,{x.set(\bufnum, sounds.at(4));x.set(\basef,60);});
	if((num >= 63) && (num < 66) ,{x.set(\bufnum, sounds.at(5));x.set(\basef,63);});
	if((num >= 66) && (num < 69) ,{x.set(\bufnum, sounds.at(6));x.set(\basef,66);});
	if((num >= 69) && (num < 72) ,{x.set(\bufnum, sounds.at(7));x.set(\basef,69);});
	if((num >= 72) && (num < 75) ,{x.set(\bufnum, sounds.at(8));x.set(\basef,72);});
	if((num >= 75) && (num < 78) ,{x.set(\bufnum, sounds.at(9));x.set(\basef,75);});
	if((num >= 78) && (num < 81) ,{x.set(\bufnum, sounds.at(10));x.set(\basef,78);});
	if((num >= 81) && (num < 84) ,{x.set(\bufnum, sounds.at(11));x.set(\basef,81);});
	if((num >= 84),{x.set(\bufnum, sounds.at(12));x.set(\basef,84);});

};



//ret = ~eSampler.value(~viola,~violasounds,~violaTemplate,num);

/*
~basssounds.free;
~basssounds = ~loadSamples.value("/home/dbalchen/Music/Samples/strings/BassStac");


~bassTemplate = {
arg x,num,sounds;

x.set(\midi,num);
if(num < 24, {x.set(\imp,0.5)},{x.set(\imp,2)});

if(num < 24,{x.set(\bufnum, sounds.at(0));x.set(\basef,24);});
if((num >= 27) && (num < 30) ,{x.set(\bufnum, sounds.at(1));x.set(\basef,27);});
if((num >= 30) && (num < 33) ,{x.set(\bufnum, sounds.at(2));x.set(\basef,30);});
if((num >= 33) && (num < 36) ,{x.set(\bufnum, sounds.at(3));x.set(\basef,33);});
if((num >= 36) && (num < 39) ,{x.set(\bufnum, sounds.at(4));x.set(\basef,36);});
if((num >= 39) && (num < 42) ,{x.set(\bufnum, sounds.at(5));x.set(\basef,39);});
if((num >= 42) && (num < 45) ,{x.set(\bufnum, sounds.at(6));x.set(\basef,42);});
if((num >= 45) && (num < 48) ,{x.set(\bufnum, sounds.at(7));x.set(\basef,45);});
if((num >= 48) && (num < 51) ,{x.set(\bufnum, sounds.at(8));x.set(\basef,48);});
if((num >= 51) && (num < 54) ,{x.set(\bufnum, sounds.at(9));x.set(\basef,51);});
if((num >= 54) && (num < 57) ,{x.set(\bufnum, sounds.at(10));x.set(\basef,54);});
if((num >= 57) && (num < 60) ,{x.set(\bufnum, sounds.at(11));x.set(\basef,57);});
if((num >= 60),{x.set(\bufnum, sounds.at(12));x.set(\basef,60);});

};



//ret = ~eSampler.value(~viola,~violasounds,~violaTemplate,num);

*/
