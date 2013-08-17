package neoe.jbw.bw;

import neoe.jbw.BW;
import neoe.jbw.data.Offset;

public class WeaponType {
	public int id;
	static final int DatLoadSize = 12;

	public WeaponType(int w) {
		this.id = w;
	}

	public static final int None = 0x82;

	public String getName() {
		if (id == None)
			return "None";
		else if (id < Offset.WEAPON_TYPE_COUNT) {
			int stringNum = v16(BWDATA_WeaponLabel); 
			int pv = BW.u32(Offset.BWDATA_StringTableOff);
			return BW.getStr(BW.u16(pv + stringNum * 2) + pv);
		} else
			return "Invalid";
	}

	// // u16 WeaponType::
	public int damageAmount() {
		return v16(BWDATA_WeaponDamageAmount);
	}

	private int v16(int field) {
		return BW.u16(BW.u32(Offset.weaponsDat + DatLoadSize * field) + id * 2);
	}

	private int v8(int field) {
		return BW.u8(BW.u32(Offset.weaponsDat + DatLoadSize * field) + id);
	}

	private int v32(int field) {
		return BW.u32(BW.u32(Offset.weaponsDat + DatLoadSize * field) + id * 4);
	}

	// u16 WeaponType::
	public int damageBonus() {
		return v16(BWDATA_WeaponDamageBonus);
	}

	// u8 WeaponType::
	public int damageCooldown() {
		return v8(BWDATA_WeaponDamageCooldown);
	}

	// u8 WeaponType::
	public int damageFactor() {
		return v8(BWDATA_WeaponDamageFactor);
	}

	// BW::UpgradeType WeaponType::upgradeType()
	// {
	// return 0;//
	// BW::UpgradeType(BW::BWDATA_WeaponUpgrade->weaponType[this->getID()]);
	// }

	// u8 WeaponType::
	public int damageType() {
		return v8(BWDATA_WeaponDamageType);
	}

	// u8 WeaponType::
	public int explosionType() {
		return v8(BWDATA_WeaponExplosionType);
	}

	// u32 WeaponType::
	public int minRange() {
		return v32(BWDATA_WeaponMinRange);

	}

	// u32 WeaponType::
	public int maxRange() {
		return v32(BWDATA_WeaponMaxRange);

	}

	// u16 WeaponType::
	public int innerSplashRadius() {
		return v16(BWDATA_WeaponInnerSplashRadius);

	}

	// u16 WeaponType::
	public int medianSplashRadius() {
		return v16(BWDATA_WeaponMedianSplashRadius);

	}

	// u16 WeaponType::
	public int outerSplashRadius() {
		return v16(BWDATA_WeaponOuterSplashRadius);

	}

	// weaponsDat_u16_type*
	private static final int BWDATA_WeaponLabel = 0;

	// weaponsDat_u32_type*
	private static final int BWDATA_WeaponGraphics = 1;

	// weaponsDat_u8_type*
	private static final int BWDATA_WeaponUnusedTechProperty = 2;

	// struct TargetFlags_type
	// {
	// Util::BitMask<u16> weaponType[WEAPON_TYPE_COUNT];
	// };
	// static TargetFlags_type* private static final int
	// BWDATA_WeaponTargetFlags =
	// (TargetFlags_type*) weaponsDat[3].address;

	// weaponsDat_u32_type*
	private static final int BWDATA_WeaponMinRange = 4;

	// weaponsDat_u32_type*
	private static final int BWDATA_WeaponMaxRange = 5;

	// weaponsDat_u8_type*
	private static final int BWDATA_WeaponUpgrade = 6;

	// weaponsDat_u8_type*
	private static final int BWDATA_WeaponDamageType = 7;

	// weaponsDat_u8_type*
	private static final int BWDATA_WeaponGraphicalBehavior = 8;

	// weaponsDat_u8_type*
	private static final int BWDATA_WeaponRemoveAfter = 9;

	// weaponsDat_u8_type*
	private static final int BWDATA_WeaponExplosionType = 10;

	// weaponsDat_u16_type*
	private static final int BWDATA_WeaponInnerSplashRadius = 11;

	// weaponsDat_u16_type*
	private static final int BWDATA_WeaponMedianSplashRadius = 12;

	// weaponsDat_u16_type*
	private static final int BWDATA_WeaponOuterSplashRadius = 13;

	// weaponsDat_u16_type*
	private static final int BWDATA_WeaponDamageAmount = 14;

	// weaponsDat_u16_type*
	private static final int BWDATA_WeaponDamageBonus = 15;

	// weaponsDat_u8_type*
	private static final int BWDATA_WeaponDamageCooldown = 16;

	// weaponsDat_u8_type*
	private static final int BWDATA_WeaponDamageFactor = 17;

	// weaponsDat_u8_type*
	private static final int BWDATA_WeaponAttackDirection = 18;

	// weaponsDat_u8_type*
	private static final int BWDATA_WeaponLaunchSpin = 19;

	// weaponsDat_u8_type*
	private static final int BWDATA_WeaponXOffset = 20;

	// weaponsDat_u8_type*
	private static final int BWDATA_WeaponYOffset = 21;

	public String toStr() {
		return String.format("weapon[%s,%s,%s]", getName(),damageAmount(),maxRange() );
	}

}
