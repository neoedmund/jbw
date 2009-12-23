package neoe.jbw;

import java.util.ArrayList;
import java.util.List;

import neoe.jbw.bw.Unit;

public class SelectedLog {
	List<Unit> lastSelect = new ArrayList<Unit>();

	public void run() {
		logSelected();

	}

	private void logSelected() {

		List<Unit> selected = new ArrayList<Unit>();
		int p = BW.BWDATA_CurrentPlayerSelectionGroup;
		int pv = BW.u32(p);
		while (pv != 0) {
			Unit u = new Unit(pv);
			selected.add(u);
			p += 4;
			pv = BW.u32(p);
		}
		if (!Utils.sameList(selected, lastSelect)) {
			for (Unit u : selected) {
				Log.log("select " + u.toStr1());
			}

			lastSelect = selected;
		}
		if (selected.size() > 0) {
			String s = "selected " + selected.get(0).toStr2();
			if (!lastSelectStr.equals(s)) {
				BW.print(8, selected.get(0).toStr1());
				lastSelectStr = s;
			}
		}

	}

	String lastSelectStr = "";
}
