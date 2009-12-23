package neoe.jbw.bw;

import neoe.jbw.BW;

public class UnitType extends Struct {

	public int id;

	public UnitType(int id) {
		super(id);
		this.id = id;
	}

	public String getName() {
		if (id == BW.BW_UnitTypeIDs_None)
			return "None";
		else if (id < BW.UNIT_TYPE_COUNT) {
			// (char*)(*((u16*)(*(u32*)BW::BWDATA_StringTableOff +
			// this->getID() * 2 + 2)) + *((u32*)BW::BWDATA_StringTableOff));
			int pv = BW.u32(BW.BWDATA_StringTableOff);
			return BW
					.getStr(BW.u16(pv + id * 2 + 2) + pv);
		} else
			return "Invalid";
	}
}
