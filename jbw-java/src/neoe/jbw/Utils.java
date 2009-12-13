package neoe.jbw;

import java.util.ArrayList;
import java.util.List;

import neoe.jbw.bw.Unit;

public class Utils {

	public static List<Unit> getVisibleUnits() {// not work
		// int addr = BW.u32(BW.BWDATA_UnitNodeChain_VisibleUnit_First);
		int pv = BW.u32(BW.BWDATA_UnitNodeChain_VisibleUnit_First);
		Unit u = new Unit(pv);
		Log.log("getVisibleUnits " + Integer.toHexString(u.base));
		List<Unit> all = new ArrayList<Unit>();
		while (u.base != 0) {
			all.add(u);
			u = u.nextUnit();
		}
		Log.log("getVisibleUnits count=" + all.size());
		return all;
	}

	public static List<Unit> getAllUnits() {
		// has problem
		int cnt = BW.u32(BW.BWDATA_UnitNodeTable_UsedNodeCount);
		Log.log("unit cnt " + cnt);
		List<Unit> all = new ArrayList<Unit>();
		int p = BW.BWDATA_UnitNodeTable;
		// int pv=BW.u32(p);
		Unit u = new Unit(p);
		for (int i = 0; i < cnt; i++) {
			Log.log(u.healthPoints());
			all.add(u);
			p += BW.UNIT_SIZE_IN_BYTES;
			u = new Unit(p);
		}
		Log.log("getAllUnits count=" + all.size());
		return all;
	}

	public static boolean sameList(List<Unit> a, List<Unit> b) {
		if (a.size()!=b.size())return false;
		for(int i=0;i<a.size();i++){
			if (!a.get(i).equals(b.get(i)))return false;
		}
		return true;
	}

}
