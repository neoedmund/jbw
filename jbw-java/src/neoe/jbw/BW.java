package neoe.jbw;

import java.util.HashMap;
import java.util.Map;

public class BW {
	// (Players*)
	public static final int BWDATA_Players = 0x0057EEE0; // 1.16.1
	// (Supplies*)
	public static final int BWDATA_Supplies = 0x00582144;
	// (PlayerResources*)
	public static final int BWDATA_PlayerResources = 0x0057F0F0;
	public static final int BW_UnitTypeIDs_None = 0xE4;
	// static char*
	public final static int BWDATA_CurrentMapFileName = 0x0057FD3C;
	// static char*
	public final static int BWDATA_CurrentMapName = 0x0057FE40;
	// static char*
	public final static int BWDATA_CurrentPlayerName = 0x0057EE9C;

	public final static int BWDATA_StringTableOff = 0x006D1238;
	// Unit*
	public final static int BWDATA_CurrentPlayerSelectionGroup = 0x00597208;
	public static final int DAMAGE_TYPE_COUNT = 5;

	public static final int EXPLOSION_TYPE_COUNT = 25;

	public static final int FLINGY_TYPE_COUNT = 209;
	public static final int PLAYABLE_PLAYER_COUNT = 8;
	public static final int PLAYER_COUNT = 12;
	public static final int RACE_COUNT = 3;
	public static final int TECH_TYPE_COUNT = 44;
	public static final int TILE_SIZE = 32;
	public static final int UNIT_TYPE_COUNT = 228;
	public static final int UPGRADE_TYPE_COUNT = 66;
	public static final int WEAPON_TYPE_COUNT = 130;

	private static native byte[] getBytes(int offset, int size);

	public static byte[] getBytes1(int offset, int size) {
		if (cacheFrame != Main.frame) {
			cacheFrame = Main.frame;
			cache.clear();
		}
		String key = offset + ":" + size;
		byte[] bs = cache.get(key);
		if (bs == null) {
			bs = getBytes(offset, size);
			cache.put(key, bs);
		}
		return bs;
	}

	static Map<String, byte[]> cache = new HashMap<String, byte[]>();
	static int cacheFrame = -1;

	public static native String getStr(int offset);

	public static native void print(int id, String s);

	public static native void command(byte[] combytes, int byteslen);

	public static int u16(int offset) {
		return Bytes.u16(getBytes1(offset, 2));
	}

	// static Unit**
	// Haven't found the right offset yet. Should point to the first unit of the
	// first player (player 1).
	public static final int BWDATA_UnitNodeChain_PlayerFirstUnit = 0x006283F8;

	// static Unit**
	public static final int BWDATA_UnitNodeChain_VisibleUnit_First = 0x00628430; // 1.16.1
	// static Unit**
	public static final int BWDATA_UnitNodeChain_VisibleUnit_Last = 0x0059CC9C; // 1.16.1
	// static u32*
	public static final int BWDATA_UnitNodeTable_UsedNodeCount = 0x006283F0; // 1.16.1

	public static final int UNIT_SIZE_IN_BYTES = 336;
	// static UnitArray*
	public static final int BWDATA_UnitNodeTable = 0x0059CCA8; // 1.16.1
	// static Unit**
	public static final int BWDATA_UnitNodeTable_FirstElement = 0x00628430;

	public static int u32(int offset) {
		int v = Bytes.u32(getBytes1(offset, 4));
		return v;
	}

	public static int u8(int offset) {
		return Bytes.u8(getBytes1(offset, 1)[0]);
	}

}
