package neoe.jbw;

import java.util.ArrayList;
import java.util.List;

import neoe.jbw.bw.Unit;

public class Game {
	List<Unit> lastSelect = new ArrayList<Unit>();

	public void onFrame() {
		// 
		{
			List<Unit> selected = new ArrayList<Unit>();
			int p = BW.BWDATA_CurrentPlayerSelectionGroup;
			int pv = BW.u32(p);
			while (pv != 0) {
				Unit u = new Unit(pv);
				selected.add(u);
				p += 4;
				pv = BW.u32(p);
			}
			if (!Utils.sameList(selected,lastSelect)) {
				for (Unit u : selected) {
					Log.log("select "+u.toStr1());
				}
				BW.print(8, "selected "+selected.size());
				lastSelect = selected;
			}
		}

	}

	public void onEnd() {
		// TODO Auto-generated method stub

	}

	public void start() {
		BW.print(8, "JVM created");
		BW.print(-1, "enjoy");
		Log.log("game start");
		int i = 0;
		for (Unit u : Utils.getMyUnits()) {
			Log.log(String.format("[%d]%s", i++, u.toStr1()));
		}
	}

	public void onText(String text) {
		Log.log("[TEXT]"+text);
		
	}

	public void onUnitDeath(Unit unit) {
		Log.log("[DEAD]"+unit.toStr1());
	}

}
