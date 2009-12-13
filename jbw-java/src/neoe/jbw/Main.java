package neoe.jbw;

public class Main {

	private static final String UTF8 = "utf8";
	public static int frame;

	public static void initJVM() {
		Log.log("initJVM");
		//load jbwnative.dll
		System.loadLibrary("jbwnative");
		//cprint1();
	}

	public static void onMatchEnd() {
		Log.log("onMatchEnd");
		game.onEnd();
		frame = 0;
	}

	static Game game;

	public static void onMatchFrame() {
		if (frame == 0) {
			Log.log("onMatchFrame");
			try {
				Log.log(BW.getStr(BW.BWDATA_CurrentPlayerName));
				Log.log(BW.getStr(BW.BWDATA_CurrentMapFileName));
				Log.log(BW.getStr(BW.BWDATA_CurrentMapName));
			} catch (Throwable e) {
				Log.log(e.toString());
			}
			game = new Game();
			game.start();
		}
		frame++;
		game.onFrame();		
	}

	public static void main(String[] args) {
		initJVM();
		onMatchFrame();
		onMatchEnd();
	}

	public static native void cprint1();
}
