package neoe.jbw.bw;

import java.lang.reflect.Field;
import java.util.HashMap;

import neoe.jbw.Utils;
import neoe.jbw.data.AttackTypeID;
import neoe.jbw.data.Offset;
import neoe.jbw.data.Order;

public class AttackType extends Struct {

	public AttackType(int offset) {
		super(offset);
		if (offset == 0)
			throw new RuntimeException("0 address");
	}

	public AttackType next() {
		if (base == Offset.BWDATA_AttackNodeTable_LastElement)
			return null;
		int addr = u32(0);
		if (addr == 0)
			return null;
		return new AttackType(addr);
	}

	public AttackType previous() {
		if (base == Offset.BWDATA_AttackNodeTable_FirstElement)
			return null;
		int addr = u32(4);
		if (addr == 0)
			return null;
		return new AttackType(addr);
	}

	// int __unknown00[7];
	// unsigned short type; // if this is 0x9D, this is a psi storm
	public int type() {
		return u16(36);
	}

	// unsigned short __unknown01;
	// unsigned short pos_x;
	public int pos_x() {
		return u16(40);
	}

	// unsigned short pos_y;
	public int pos_y() {
		return u16(42);
	}

	// int pos4_x; // (pos4_x >> 4) == pos_x
	// int pos4_y;
	// int __unknown02[11];
	// int time_left; // use time_left>>8 for frames
	public int frameLeft() {
		return u32(96)>>8;
	}
	
	public String toStr(){
		return String.format("attack[%s,%s,%s:%s]", getName(type()), frameLeft(), pos_x(),pos_y());
	}
	private static HashMap<Integer, String> m;

	public static String getName(int v) {
		if (m == null) {
			try {
				m = new HashMap<Integer, String>();
				Field[] fs = AttackTypeID.class.getDeclaredFields();
				for (Field f : fs) {
					if (f.getType()!=int.class)continue;
					int w = f.getInt(null);
					m.put(w, f.getName());
				}
			} catch (Exception e) {
				Utils.printException(e);
			}
		}
		return m.get(v);
	}
}
