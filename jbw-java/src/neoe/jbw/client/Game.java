package neoe.jbw.client;

import neoe.jbw.BW;
import neoe.jbw.Log;
import neoe.jbw.Main;
import neoe.jbw.PosUnit;
import neoe.jbw.SelectedLog;
import neoe.jbw.Utils;
import neoe.jbw.bw.Player;
import neoe.jbw.bw.Position;
import neoe.jbw.bw.Unit;
import neoe.jbw.cmd.Command;
import neoe.jbw.cmd.Name;
import neoe.jbw.data.Order;

public class Game {

	private Player me;
	private int st;
	boolean buildMode;
	BuildHelp buildHelp;
	SelectedLog selectedLog;
	private boolean logSelect;
	private boolean trainMode;
	private TrainHelp trainHelp;
	private WarStat warStat;

	public void onFrame() {
		if (logSelect)
			selectedLog.run();
		warStat.run();
		if (Main.isReplay) {// replay
			return;
		}
		gather.run();
		if (buildMode) {
			buildHelp.run();
		}
		if (trainMode) {
			trainHelp.run();
		}
	}

	private void letAllGotoFenix() {
		// Fenix(1271,1853)
		Log.log("letAllGotoFenix");
		Command.add(new Command(Name.Attack, new PosUnit(1271, 1853, 0), 0,
				Order.AttackMove), Utils.getMyUnits());
	}

	public void onEnd() {
		// TODO Auto-generated method stub

	}

	public void start() {
		BW.print1(8, "JVM created");
		warStat = new WarStat();
		selectedLog = new SelectedLog();
		if (Main.isReplay) {// replay
			BW.print1(8,"replay start ");
			return;
		} else {
			Log.log("game start ");
			me = Main.players[Main.playerId];
			Log.log("playid=" + Main.playerId + ":" + me.xid());
			// int i = 0;
			// for (Unit u : Utils.getVisibleUnits()) {
			// Log.log(String.format("[%d]%s", i++, u.toStr1()));
			// }
			buildHelp = new BuildHelp(this);
			gather = new Gather();
			trainHelp = new TrainHelp();

			if (BW.u8(BW.BWDATA_gameType) == 10) {// pve game type
				SwingTextArea st = new SwingTextArea();
				st.append(new Stat().run1(Utils.getVisibleUnits()));
				st.show();
				Command.add(new Command(Name.PauseGame, null, 0), null);
			} else {// one of pvp types
				pvpInit();
			}
		}
	}

	private void pvpInit() {
		buildMode = true;
	}

	Gather gather;

	public void onText(String text) {
		Log.log("[TEXT]" + text);
		if (("build".equals(text) || "b".equals(text)) && buildHelp != null) {
			buildMode = !buildMode;
			Utils.print("build helper " + (buildMode ? "enabled" : "disabled"));
		} else if ("stat".equals(text)) {
			new Stat().run();
		} else if ("log".equals(text)) {
			logSelect = !logSelect;
			Utils.print("select log " + (logSelect ? "enabled" : "disabled"));
		} else if ("t".equals(text) && trainHelp != null) {
			trainMode = !trainMode;
			trainHelp.zergtrain = null;
			Utils.print("train helper " + (trainMode ? "enabled" : "disabled"));
		} else if ("m".equals(text) && gather != null) {
			java.util.List<Unit> ss = SelectedLog.getSelected();
			if (ss.size() > 0) {
				gather.set(Order.Move, ss.get(0).position());
				Utils.print("gather move " + gather.pos);
			}
		} else if ("a".equals(text) && gather != null) {
			java.util.List<Unit> ss = SelectedLog.getSelected();
			if (ss.size() > 0) {
				gather.set(Order.AttackMove, ss.get(0).position());
				Utils.print("gather attack " + gather.pos);
			}
		} else if ("s".equals(text) && gather != null) {
			java.util.List<Unit> ss = SelectedLog.getSelected();
			if (ss.size() > 0) {
				gather.clear();
				Utils.print("gather disabled");
			}
		}
	}

	public void onUnitDeath(Unit unit) {
		// Log.log("[DEAD]" + unit.toStr1());
		//if (Main.isReplay) {// replay
			warStat.onUnitDeath(unit);
		//	return;
		//}
	}

	public void onNuclearLaunchDetected(Position targetPos) {
		Utils.print("onNuclearLaunchDetected at " + targetPos.toString());
	}

}
