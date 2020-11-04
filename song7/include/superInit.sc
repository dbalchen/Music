Help.gui
Quarks.gui

s.boot;
s.plotTree;
s.meter;
s.quit;

Stethoscope.new(s);
FreqScope.new(800, 400, 0, server: s);
Server.default.makeGui;


(
o = Server.local.options;
o.numOutputBusChannels = 32; // The next time it boots, this will take effect
o.memSize = 2097152;
s.latency = 0.00
)

"/home/dbalchen/Music/song7/include/setup.sc".load;


// "/home/dbalchen/Music/setup.sc".load;