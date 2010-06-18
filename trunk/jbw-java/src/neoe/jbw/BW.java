package neoe.jbw;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

public class BW {
	//(u32*)
	public static final int   BWDATA_InReplay   =   0x006D0F14;
	// (PlayerAlliance*)
	public static final int BWDATA_Alliance = 0x0058D634; // 1.16.1
	// (AttackType**)
	public static final int BWDATA_AttackNodeTable_FirstElement = 0x64DEAC;
	// (AttackType**)
	public static final int BWDATA_AttackNodeTable_LastElement = 0x64DEC4;
	// (u8*)
	public static final int BWDATA_gameType = 0x00596820; // 1.16.1

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

	private static native ByteBuffer getBB();

	public static final ByteBuffer BB = getBB().order(ByteOrder.LITTLE_ENDIAN);
	private final static int BASE = 0x400000;

	/**
	 * @deprecated
	 */
	public static byte[] getBytes2(int offset, int size) {

		if (cacheFrame != Main.frame) {
			cacheFrame = Main.frame;
			cache.clear();
		}
		String key = offset + ":" + size;
		byte[] bs = cache.get(key);
		if (bs == null) {
			Log.log(key);
			bs = getBytes(offset, size);
			cache.put(key, bs);
		}
		return bs;
	}

	static Map<String, byte[]> cache = new HashMap<String, byte[]>();
	static int cacheFrame = -1;

	public static native String getStr(int offset);

	private static native void print(int id, String s);

	public static void print1(int id, String s) {
		Log.log("[" + id + "]" + s);
		print(id, s);
	}

	public static native void command(byte[] combytes, int byteslen);

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

	// (DatLoad*)
	public static final int weaponsDat = 0x00513868;
	// (DatLoad*)
	public static final int unitsDat = 0x00513C30;

	// struct DatLoad
	// {
	// u32 address;
	// u32 length;
	// u32 entries;
	// };

	public static synchronized int u32(int offset) {
		position(offset);
		byte bs0 = BB.get();
		byte bs1 = BB.get();
		byte bs2 = BB.get();
		byte bs3 = BB.get();
		return Bytes.u8(bs3) << 24 | Bytes.u8(bs2) << 16 | 
		Bytes.u8(bs1) << 8 | Bytes.u8(bs0);
		//return BB.getInt();
		// int v = Bytes.u32(getBytes1(offset, 4));
		// return v;
	}

	public static synchronized int u8(int offset) {
		position(offset);
		return Bytes.u8(BB.get());
		// return Bytes.u8(getBytes1(offset, 1)[0]);
	}

	public static synchronized int u16(int offset) {
		position(offset);
		byte b1 = BB.get();
		byte b2 = BB.get();
		return Bytes.u8(b1) | Bytes.u8(b2) << 8;
		// return BB.getChar();
		// return Bytes.u16(getBytes1(offset, 2));
	}

	private static void position(int offset) {
		try {
			BB.position(offset - BASE);
		} catch (Exception e) {
			Log.log("bad position " + Integer.toHexString(offset) + " E:" + e);
			print(-1,"bad position " + Integer.toHexString(offset));
		}
	}

}
