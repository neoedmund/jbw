package neoe.jbw.bw;

import neoe.jbw.BW;

public class Position extends Struct {

	public int x(){return BW.u16(base);};
	public int y(){return BW.u16(base+2);};

	public Position(int offset) {
		super(offset);
	}

	
}
