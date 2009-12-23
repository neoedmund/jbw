package neoe.jbw.bw;

import neoe.jbw.BW;
import neoe.jbw.Pos;

public class Position extends Struct {

	public int x(){return BW.u16(base);};
	public int y(){return BW.u16(base+2);};

	public Position(int offset) {
		super(offset);
	}
	public Pos toPos(Unit u) {
		return new Pos(x(),y(),u==null?0:u.bwId());
	}

	
}
