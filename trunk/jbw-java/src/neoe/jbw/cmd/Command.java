package neoe.jbw.cmd;

import java.util.List;

import neoe.jbw.BW;
import neoe.jbw.Pos;
import neoe.jbw.bw.Unit;
import neoe.jbw.bytes.U16;

public abstract class Command {
	public static List<Command> cmdList;
	protected Pos pos;
	public List<Unit> units;

	public abstract byte[] getBytes();

	public static byte[] select(List<Unit> units2) {
		BA ba = new BA().add(0x09).add(units2.size());
		for (Unit u : units2) {
			int fromIndex = (u.base - BW.BWDATA_UnitNodeTable)
					/ BW.UNIT_SIZE_IN_BYTES + 1;
			ba.add(new U16(fromIndex + (1 << 11)));
		}
		return ba.toBA();
	}

}
