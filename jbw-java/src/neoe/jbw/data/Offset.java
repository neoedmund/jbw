package neoe.jbw.data;

import java.util.HashMap;
import java.util.Map;

public class Offset {

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
	public final static int BASE = 0x400000;
	public static Map<String, byte[]> cache = new HashMap<String, byte[]>();
	public static int cacheFrame = -1;
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

	public static final int GameSpeedModifiers=0x005124D8;

}
