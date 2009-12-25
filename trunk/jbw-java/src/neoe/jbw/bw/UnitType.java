package neoe.jbw.bw;

import neoe.jbw.BW;
import neoe.jbw.data.UnitID;
import neoe.jbw.data.UnitPrototypeFlags;

public class UnitType extends Struct {

	public int id;
	static final int DatLoadSize = 12;

	public UnitType(int id) {
		super(id);
		this.id = id;
	}
	public int maxShieldPoints(){
		return v16(BWDATA_MaxShieldPoints);
	}
	public boolean isShieldEnabled(){
		return v8(BWDATA_ShieldsEnabled)!=0;
	}
	
	public String getName() {
		if (id == BW.BW_UnitTypeIDs_None)
			return "None";
		else if (id < BW.UNIT_TYPE_COUNT) {
			// (char*)(*((u16*)(*(u32*)BW::BWDATA_StringTableOff +
			// this->getID() * 2 + 2)) + *((u32*)BW::BWDATA_StringTableOff));
			int pv = BW.u32(BW.BWDATA_StringTableOff);
			return BW.getStr(BW.u16(pv + id * 2 + 2) + pv);
		} else
			return "Invalid";
	}

	private int v16(int field) {
		return BW.u16(BW.u32(BW.unitsDat + DatLoadSize * field) + id * 2);
	}

	private int v8(int field) {
		return BW.u8(BW.u32(BW.unitsDat + DatLoadSize * field) + id);
	}

	private int v32(int field) {
		return BW.u32(BW.u32(BW.unitsDat + DatLoadSize * field) + id * 4);
	}

	private int getFlag() {
		return v32(BWDATA_UnitPrototypeFlags);
	}
	public boolean flag(int flag){
		return (getFlag()&UnitPrototypeFlags.Flyer)!=0;
	}
	
	public WeaponType groundWeapon() {
		// u8 w = BW::BWDATA_UnitGroundWeapon->unitType[this->getID()];
		// if (w == BW::WeaponID::None &&
		// BW::BWDATA_SubUnit1->unitType[this->getID()] !=
		// BW::UnitTypeIDs::None)
		// {
		// w =
		// BW::BWDATA_UnitGroundWeapon->unitType[BW::BWDATA_SubUnit1->unitType[this->getID()]];
		// }
		int DatLoadSize = 12;
		int Ground = 17;
		int w = BW.u8(BW.u32(BW.unitsDat + DatLoadSize * Ground) + id);
		if (w == 0x82/* None */) {
			int su1 = BW.u16(BW.u32(BW.unitsDat + DatLoadSize * 1) + id * 2);
			if (su1 != UnitID.None) {
				w = BW.u8(BW.u32(BW.unitsDat + DatLoadSize * Ground) + su1);
			}
		}
		return new WeaponType(w);
	}

	public WeaponType airWeapon() {
		// u8 w = BW::BWDATA_UnitAirWeapon->unitType[this->getID()];
		// if (w == BW::WeaponID::None &&
		// BW::BWDATA_SubUnit1->unitType[this->getID()] !=
		// BW::UnitTypeIDs::None)
		// {
		// w =
		// BW::BWDATA_UnitAirWeapon->unitType[BW::BWDATA_SubUnit1->unitType[this->getID()]];
		// }
		int DatLoadSize = 12;
		int Air = 19;
		int SubUnit1 = 1;
		int w = BW.u8(BW.u32(BW.unitsDat + DatLoadSize * Air) + id);
		if (w == 0x82/* None */) {
			int su1 = BW.u16(BW.u32(BW.unitsDat + DatLoadSize * SubUnit1) + id
					* 2);
			if (su1 != UnitID.None) {
				w = BW.u8(BW.u32(BW.unitsDat + DatLoadSize * Air) + su1);
			}
		}
		return new WeaponType(w);
	}

	// static unitsDat_u8_type*
	private static final int BWDATA_UnitGraphics = 0;

	// static unitsDat_u16_type*
	private static final int BWDATA_SubUnit1 = 1;

	// static unitsDat_u16_type*
	private static final int BWDATA_SubUnit2 = 2;

	// static unitsDat_u32_type*
	private static final int BWDATA_ConstructionGraphics = 4;

	// static unitsDat_u8_type*
	private static final int BWDATA_Direction = 5;

	// static unitsDat_u8_type*
	private static final int BWDATA_ShieldsEnabled = 6;

	// static unitsDat_u16_type*
	private static final int BWDATA_MaxShieldPoints = 7;

	// /** Direct mapping of unit unit type (Max Health Points)/(Not
	// Attackable)/(Requirable) specification. */
	// struct MaxHealthPoints_NotAttackable_Repairable_type
	// {
	// /** mapping of the Max Health Points)/(Not Attackable)/(Requirable) for
	// single unit type. */
	// struct MaxHealthPoints_NotAttackable_Repairable_Internal_type
	// {
	// u16 maxHealthPoints;
	// u8 notAttackable;
	// u8 repairable;
	// };
	// MaxHealthPoints_NotAttackable_Repairable_Internal_type
	// raw[UNIT_TYPE_COUNT];
	// };
	// static MaxHealthPoints_NotAttackable_Repairable_type*
	private static final int BWDATA_MaxHealthPoints_NotAttackable_Repairable = 8;

	// static unitsDat_u8_type*
	private static final int BWDATA_Elevation = 9;

	// static unitsDat_u8_type*
	private static final int BWDATA_UnitSubLabel = 11;

	// static unitsDat_u8_type*
	private static final int BWDATA_UnitGroundWeapon = 17;

	// static unitsDat_u8_type*
	private static final int BWDATA_MaxGroundHits = 18;

	// static unitsDat_u8_type*
	private static final int BWDATA_UnitAirWeapon = 19;

	// static unitsDat_u8_type*
	private static final int BWDATA_MaxAirHits = 20;

	// static unitsDat_u8_type*
	private static final int BWDATA_AIInterval = 21;

	// struct PrototypeFlags_type
	// {
	// Util::BitMask<u32> unit[UNIT_TYPE_COUNT];
	// };
	// static PrototypeFlags_type*
	private static final int BWDATA_UnitPrototypeFlags = 22;

	// static unitsDat_u8_type*
	private static final int BWDATA_UnitSeekRange = 23;

	// static unitsDat_u8_type*
	private static final int BWDATA_UnitSightRange = 24;

	// static unitsDat_u8_type*
	private static final int BWDATA_UnitUpgrade = 25;

	// static unitsDat_u8_type*
	private static final int BWDATA_UnitSize = 26;

	// static unitsDat_u8_type*
	private static final int BWDATA_Armor = 27;

	// static unitsDat_u8_type*
	private static final int BWDATA_RightClickAction = 28;

	// static unitsDat_u16_type*
	private static final int BWDATA_ReadySound = 29;

	// static unitsDat_u16_type*
	private static final int BWDATA_FirstWhatSound = 30;

	// static unitsDat_u16_type*
	private static final int BWDATA_LastWhatSound = 31;

	// static unitsDat_u16_type*
	private static final int BWDATA_FirstAnnoyedSound = 32;

	// static unitsDat_u16_type*
	private static final int BWDATA_LastAnnoyedSound = 33;

	// static unitsDat_u16_type*
	private static final int BWDATA_FirstYesSound = 34;

	// static unitsDat_u16_type*
	private static final int BWDATA_LastYesSound = 35;

	// struct UnitPlacement_type
	// {
	// struct Placement_Internal_type
	// {
	// u16 height;
	// u16 width;
	// };
	// Placement_Internal_type unitType[UNIT_TYPE_COUNT];
	// };
	// static UnitPlacement_type*
	private static final int BWDATA_UnitPlacement = 36;

	// struct UnitsDimensions_type
	// {
	/**
	 * Dimensions of unit, it's the distance from the 'center' of unit to each
	 * border
	 */
	// struct UnitDimensions
	// {
	// u16 left;
	// u16 up;
	// u16 right;
	// u16 down;
	// };
	// UnitDimensions units[UNIT_TYPE_COUNT];
	// };
	// static UnitsDimensions_type*
	private static final int BWDATA_UnitDimensions = 38;

	// static unitsDat_u16_type*
	private static final int BWDATA_IdlePortrait = 39;

	// static unitsDat_u16_type*
	private static final int BWDATA_MineralPrices = 40;

	// static unitsDat_u16_type*
	private static final int BWDATA_GasPrices = 41;

	// static unitsDat_u16_type*
	private static final int BWDATA_BuildTime = 42;

	// struct PrototypeGroupFlags_type
	// {
	// Util::BitMask<u8> unit[UNIT_TYPE_COUNT];
	// };
	// static PrototypeGroupFlags_type*
	private static final int BWDATA_PrototypeGroupFlags = 44;

	// static unitsDat_u8_type*
	private static final int BWDATA_SupplyProvided = 45;

	// static unitsDat_u8_type*
	private static final int BWDATA_SupplyRequired = 46;

	// static unitsDat_u8_type*
	private static final int BWDATA_SpaceRequired = 47;

	// static unitsDat_u8_type*
	private static final int BWDATA_SpaceProvided = 48;

	// static unitsDat_u16_type*
	private static final int BWDATA_BuildScore = 49;

	// static unitsDat_u16_type*
	private static final int BWDATA_DestroyScore = 50;

	// static unitsDat_u16_type*
	private static final int BWDATA_BroodwarOnly = 52;

	// static unitsDat_u16_type*
	private static final int BWDATA_AvailabilityFlags = 53;
}
