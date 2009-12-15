package neoe.jbw.cmd;

import java.util.List;

import neoe.jbw.Pos;
import neoe.jbw.bw.Unit;
import neoe.jbw.bytes.U16;
import neoe.jbw.bytes.U32;

public class AttackMove extends Command {

	public AttackMove(List<Unit> units, Pos pos) {
		this.units = units;
		this.pos = pos;
	}

	public byte[] getBytes() {
		return new BA().add(0x15)
		// BW::PositionUnitID target;
				// {BW::Position position; u16 x, u16 y
				// BW::UnitID unitId;u16 id}
				.add(new U16(pos.x)).add(new U16(pos.y)).add(new U16(0))
				// u16 always0xe4;
				.add(new U16(0xe4))
				// BW::OrderID order; int
				.add(new U32(Order.AttackMove))
				// u8 type;
				.add(0x00).toBA();
	}
}
