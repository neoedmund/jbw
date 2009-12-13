package neoe.jbw;

import java.util.ArrayList;
import java.util.List;

import neoe.jbw.bw.Unit;

public class Game {
	List<Unit> lastSelect = new ArrayList<Unit>();

	public void onFrame() {
		//Log.log("frame " + Main.frame);
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
		if (!selected.equals(lastSelect)) {
			for (Unit u : selected) {
				Log.log(u.healthPoints());
			}
			lastSelect=selected;
		}
		}

	}

	public void onEnd() {
		// TODO Auto-generated method stub

	}

	public void start() {
		Log.log("game start");
		int i = 0;
		for (Unit u : Utils.getVisibleUnits()) {
			Log.log((i++)+"(" +u.healthPoints()+
					"):"+u.unitID().getName());
		}
	}

}
