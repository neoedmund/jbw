package neoe.jbw.bw;

import neoe.jbw.BW;

public class Struct {

	public final int base;

	public Struct(int offset) {
		this.base = offset;
	}

	protected int u32(int i) {
		return BW.u32(base + i);
	}

	protected int s32(int i) {
		return BW.u32(base + i);
	}

	protected int u16(int i) {
		return BW.u16(base + i);
	}

	protected IntArr u16array(int i, int size) {
		return new IntArr(BW.getBytes(i, size), 2);
	}

	protected byte[] u8array(int i, int size) {
		return BW.getBytes(i, size);
	}

	protected int u8(int i) {
		return BW.u8(base + i);
	}

	protected int BitMasku8(int i) {
		return BW.u8(base + i);
	}

	protected int BitMasku32(int i) {
		return BW.u32(base + i);
	}

	protected Unit getUnit(int offset) {
		return new Unit(BW.u32(offset + base));
	}

	protected CSprite getCSprite(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	protected Order getOrder(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	protected UnitType getUnitType(int i) {
		return new UnitType(BW.u16(i + base));
	}

	protected Position getPosition(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
