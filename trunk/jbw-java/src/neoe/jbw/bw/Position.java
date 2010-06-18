package neoe.jbw.bw;

import neoe.jbw.BW;
import neoe.jbw.PosUnit;

public class Position extends Struct {

	@Override
	public String toString() {
		return "("+x()+","+y()+")";
	}
	public int x(){return BW.u16(base);};
	public int y(){return BW.u16(base+2);};

	public Position(int offset) {
		super(offset);
	}
	public PosUnit toPos(Unit u) {
		return new PosUnit(x(),y(),u==null?0:u.bwId());
	}

	
}
