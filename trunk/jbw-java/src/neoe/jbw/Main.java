package neoe.jbw;

import neoe.jbw.bw.Player;
import neoe.jbw.bw.Unit;
import neoe.jbw.cmd.Command;

public class Main {

	//private static final String UTF8 = "utf8";
	public static int frame;

	public static void initJVM() {
		Log.log("initJVM");
		// load jbwnative.dll
		System.loadLibrary("jbwnative");
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

	static Game game;
	public static int playId = -1;
	public static Player[] players;

	public static void onMatchFrame() {
		try {
			if (frame == 0) {
				Log.log("onMatchFrame");

				Log.log(BW.getStr(BW.BWDATA_CurrentMapFileName));
				Log.log(BW.getStr(BW.BWDATA_CurrentMapName));

				initGame();
				welcomeMessage();
			}
			frame++;
			if (Command.hasCommand()) {
				Command.issueFrameCommand();

			} else { 
				game.onFrame();
			}
		} catch (Throwable e) {
			Log.log(e.toString());
		}
	}

	private static void welcomeMessage() {
		new Thread() {
			public void run() {
				try {
					BW.print(-1, "enjoy");
					Thread.sleep(3500);
					BW.print(-1, "=== jbw ===");
					Thread.sleep(3500);
					BW.print(-1, "by neoedmund 2009");
				} catch (Exception e) {
					Log.log(e);
				}
			}
		}.start();

	}

	private static void initGame() {
		players = new Player[BW.PLAYER_COUNT];
		for (int i = 0; i < BW.PLAYER_COUNT; i++) {
			players[i] = new Player(i);
		}
		playId = Utils.getPlayId();
		Command.initQueue();
		game = new Game();
		game.start();
	}

	public static void main(String[] args) {
		initJVM();
		onMatchFrame();
		onMatchEnd();
	}

	public static native void cprint1();
}
