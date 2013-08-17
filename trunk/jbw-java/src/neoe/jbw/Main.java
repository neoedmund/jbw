package neoe.jbw;

import java.util.HashSet;
import java.util.Set;

import neoe.jbw.ai.AiZerg01;
import neoe.jbw.bw.Player;
import neoe.jbw.bw.Unit;
import neoe.jbw.client.Game;
import neoe.jbw.cmd.Command;
import neoe.jbw.data.Offset;
import neoe.jbw.data.UnitID;

public class Main {

	// private static final String UTF8 = "utf8";
	public static int frame;

	public static void initJVM() {
		Log.log("initJVM");
		// load jbwnative.dll
		System.loadLibrary("jbw");
		// cprint1();
	}

	public static void onMatchEnd() {
		Log.log("onMatchEnd");
		game.onEnd();
		frame = 0;
	}

	public static void onText(String text) {
		game.onText(text);
	}

	public static void onUnitDeath(int unitp) {
		game.onUnitDeath(new Unit(unitp));
	}

	static IGame game;
	public static int playerId = -1;
	public static Player[] players;
	public static int waitUntil;

	public static void onMatchFrame() {
		try {
			if (frame == 0) {
				Log.log("onMatchFrame");

				Log.log(BW.getStr(Offset.BWDATA_CurrentMapFileName));
				Log.log(BW.getStr(Offset.BWDATA_CurrentMapName));

				initGame();
				welcomeMessage();
			}
			frame++;

			checkNuclear();

			if (Command.hasCommand()) {
				Command.issueFrameCommand();
				return;
			}
			// else {
			game.onFrame();
			// }
		} catch (Throwable e) {
			Log.log(e);
		}
	}

	private static Set<Unit> nukes = new HashSet<Unit>();
	static int lastOrder = -1;
	public static boolean isReplay;

	private static void checkNuclear() {
		for (Unit u : Utils.getVisibleUnits())
			if (u.unitID().id == UnitID.T_NuclearMissile) {
				if (!nukes.contains(u)) {
					nukes.add(u);
					game.onNuclearLaunchDetected(u.orderTargetPos());
				}
				if (u.orderID() != lastOrder) {
					Log.log("[nuke]" + u.toStr1());
					lastOrder = u.orderID();
				}
			}
	}

	private static void welcomeMessage() {
		new Thread() {
			public void run() {
				try {
					BW.print1(-1, "enjoy");
					Thread.sleep(3500);
					BW.print1(-1, "=== jbw ===");
					Thread.sleep(3500);
					BW.print1(-1, "by neoedmund 2009");
				} catch (Exception e) {
					Log.log(e);
				}
			}
		}.start();

	}

	private static void initGame() {
		players = new Player[Offset.PLAYER_COUNT];
		for (int i = 0; i < Offset.PLAYER_COUNT; i++) {
			players[i] = new Player(i);
		}
		isReplay = BW.u32(Offset.BWDATA_InReplay) != 0;
		playerId = Utils.getPlayId();
		Command.initQueue();
		String mapName = BW.getStr(Offset.BWDATA_CurrentMapFileName);
		if (mapName.equals("campaign\\zerg\\zerg01")) {
			game = new AiZerg01();
			game.start();
		} else {
			game = new Game();
			game.start();
		}
	}

	public static void main(String[] args) {
		initJVM();
		onMatchFrame();
		onMatchEnd();
	}

	public static native void cprint1();
}
