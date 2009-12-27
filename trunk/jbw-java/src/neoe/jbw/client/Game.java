package neoe.jbw.client;

import neoe.jbw.BW;
import neoe.jbw.Log;
import neoe.jbw.Main;
import neoe.jbw.Pos;
import neoe.jbw.SelectedLog;
import neoe.jbw.Utils;
import neoe.jbw.bw.Player;
import neoe.jbw.bw.Unit;
import neoe.jbw.cmd.Command;
import neoe.jbw.cmd.Name;
import neoe.jbw.data.Order;

public class Game {
	
	private Player me;
	private int st;
	boolean buildMode = true;
	BuildHelp buildHelp;
	SelectedLog selectedLog;
	private boolean logSelect=true;
	public void onFrame() {
		if (logSelect)
		selectedLog.run();
//		if (st == 0) {
//			if ("campaign\\protoss\\protoss01".equals(BW
//					.getStr(BW.BWDATA_CurrentMapFileName))) {
//				letAllGotoFenix();
//			}
//			st = 1;
//		}
		gather.run();
		if (buildMode ) {
			buildHelp.run();
		}
	}

	

	private void letAllGotoFenix() {
		// Fenix(1271,1853)
		Log.log("letAllGotoFenix");
		Command.add(new Command(Name.Attack, new Pos(1271, 1853,0), 0, Order.AttackMove), Utils
				.getMyUnits());
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
		selectedLog=new SelectedLog();
		gather=new Gather();
	}

	Gather gather;
	public void onText(String text) {
		Log.log("[TEXT]" + text);
		if ("build".equals(text)||"b".equals(text)) {
			buildMode = !buildMode;
			Utils.print("build helper " + (buildMode ? "enabled" : "disabled"));
		}else if ("stat".equals(text)) {
			new Stat().run();
		}else if ("log".equals(text)) {
			logSelect=!logSelect;
			Utils.print("select log " + (logSelect ? "enabled" : "disabled"));
		}else if ("m".equals(text)) {
			java.util.List<Unit>ss=SelectedLog.getSelected();
			if(ss.size()>0){
				gather.set(Order.Move,ss.get(0).position());
				Utils.print("gather move " + gather.pos);
			}
		}else if ("a".equals(text)) {
			java.util.List<Unit>ss=SelectedLog.getSelected();
			if(ss.size()>0){
				gather.set(Order.AttackMove,ss.get(0).position());
				Utils.print("gather attack " + gather.pos);
			}
		}else if ("s".equals(text)) {
			java.util.List<Unit>ss=SelectedLog.getSelected();
			if(ss.size()>0){
				gather.clear();
				Utils.print("gather disabled");
			}
		}
	}

	public void onUnitDeath(Unit unit) {
		Log.log("[DEAD]" + unit.toStr1());
	}

}
