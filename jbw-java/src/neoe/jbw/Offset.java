package neoe.jbw;
public class Offset {
	// static char*
	static int BWDATA_CurrentPlayerName = 0x0057EE9C;
	// static char*
	static int BWDATA_CurrentMapFileName = 0x0057FD3C;
	// static char*
	static int BWDATA_CurrentMapName = 0x0057FE40;
	
	static native String getStr(int offset);
}
