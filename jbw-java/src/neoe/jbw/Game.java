package neoe.jbw;

import java.util.ArrayList;
import java.util.List;

import neoe.jbw.bw.Player;
import neoe.jbw.bw.Unit;
import neoe.jbw.cmd.Command;
import neoe.jbw.cmd.Name;

public class Game {
	List<Unit> lastSelect = new ArrayList<Unit>();
	private Player me;
	private int st;
	boolean buildMode = true;
	BuildHelp buildHelp;

	public void onFrame() {
		logSelected();
		if (st == 0) {
			if ("campaign\\protoss\\protoss01".equals(BW
					.getStr(BW.BWDATA_CurrentMapFileName))) {
				letAllGotoFenix();
			}
			st = 1;
		}
		if (buildMode ) {
			buildHelp.run();
		}
	}

	

	private void letAllGotoFenix() {
		// Fenix(1271,1853)
		Log.log("letAllGotoFenix");
		Command.add(new Command(Name.Attack, new Pos(1271, 1853), 0), Utils
				.getMyUnits());
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
			BW.print(8, "selected " + selected.size());
			lastSelect = selected;
		}

	}

	public void onEnd() {
		// TODO Auto-generated method stub

	}

	public void start() {
		BW.print(8, "JVM created");
		Log.log("game start");
		me = Main.players[Main.playerId];
		Log.log("playid=" + Main.playerId + ":" + me.xid());
		int i = 0;
		for (Unit u : Utils.getVisibleUnits()) {
			Log.log(String.format("[%d]%s", i++, u.toStr1()));
		}
		i = 0;

		for (Unit u : Utils.getMyUnits()) {
			Log.log(String.format("[%d]%s", i++, u.toStr1()));
		}
		buildHelp=new BuildHelp(this);
	}

	public void onText(String text) {
		Log.log("[TEXT]" + text);
		if ("build".equals(text)) {
			buildMode = !buildMode;
			Utils.print("build helper " + (buildMode ? "enabled" : "disabled"));
		}

	}

	public void onUnitDeath(Unit unit) {
		Log.log("[DEAD]" + unit.toStr1());
	}

}
