package neoe.jbw.bw;

public class Unit extends Struct {
	public Unit(int offset) {
		super(offset);
	}

	// parsed

	public Unit previousUnit() {
		return getUnit(0x000);
	}

	/**
	 * < Pointer to next unit in the unit linked list, we use it to iterate
	 * units.
	 * 
	 * @see BW#BWXFN_UnitNodeTable_FirstElement
	 **/
	public Unit nextUnit() {
		return getUnit(0x004);
	}

	/**
	 * < Health points of unit, note that the displayed value in broodwar is
	 * ceil(healthPoints/256)
	 */
	public int healthPoints() {
		return u32(0x008)/256;
	}

	public CSprite sprite() {
		return getCSprite(0x00C);
	}

	public Position moveToPos() {
		return getPosition(0x010);
	}

	public Unit targetUnit() {
		return getUnit(0x014);
	}

	/**
	 * < The next way point in the path the unit is following to get to its
	 * destination. Equal to moveToPos for air units since they don't need to
	 * navigate around buildings or other units.
	 */
	public Position nextWaypoint() {
		return getPosition(0x018);
	}

	/** < Appears to always be equal to nextWaypoint */
	public Position nextWaypoint2() {
		return getPosition(0x01C);
	}

	/** < Flags specifying movement type - defined in BW#MovementFlags. */
	public int movementFlags() {
		return BitMasku8(0x020);
	}

	/** < The current direction the unit is facing */
	public int currentDirection() {
		return u8(0x021);
	}

	public int flingyTurnRadius() {
		return u8(0x022);
	}

	/**
	 * < This usually only differs from the currentDirection field for units
	 * that can accelerate and travel in a different direction than they are
	 * facing. For example Mutalisks can change the direction they are facing
	 * faster than then can change the direction they are moving.
	 */
	public int velocityDirection() {
		return u8(0x023);
	}

	public int flingyID() {
		return u16(0x024);
	}

	public int flingyMovementType() {
		return u8(0x027);
	}

	/** < Current position of the unit */
	public Position position() {
		return getPosition(0x028);
	}

	/** < @todo Unknown */
	public int xHalt() {
		return u32(0x02C);
	}

	/** < @todo Unknown */
	public int yHalt() {
		return u32(0x030);
	}

	public int flingySpeed() {
		return u32(0x034);
	}

	/** < @todo Unknown */
	public int unknownSpeed_0x038() {
		return u32(0x038);
	}

	/** < @todo Unknown */
	public int unknownSpeed_0x03C() {
		return u32(0x03C);
	}

	public int current_speedX() {
		return s32(0x040);
	}

	public int current_speedY() {
		return s32(0x044);
	}

	public int flingyAcceleration() {
		return u16(0x048);
	}

	/** < Specification of owner of this unit. */
	public int playerID() {
		return u8(0x04C);
	}

	/** < Specification of type of order currently given. */
	public int orderID() {
		return u8(0x04D);
	}

	/**
	 * < Additional order info (mostly unknown, wander property investigated so
	 * far)
	 */
	public int orderFlags() {
		return BitMasku8(0x04E);
	}

	/** < @todo Unknown */
	public int orderSignal() {
		return u8(0x04F);
	}

	/** < @todo Unknown */
	public int mainOrderTimer() {
		return u8(0x054);
	}

	public int groundWeaponCooldown() {
		return u8(0x055);
	}

	public int airWeaponCooldown() {
		return u8(0x056);
	}

	public int spellCooldown() {
		return u8(0x057);
	}

	public Position orderTargetPos() {
		return getPosition(0x058);
	}

	public Unit orderTargetUnit() {
		return getUnit(0x05C);
	}

	/** < Bw shows this value/256 */
	public int shieldPoints() {
		return u32(0x060);
	}

	/** < Specifies the type of unit. */
	public UnitType unitID() {
		return getUnitType(0x064);
	}

	public Unit previousPlayerUnit() {
		return getUnit(0x068);
	}

	public Unit nextPlayerUnit() {
		return getUnit(0x06C);
	}

	public Unit subUnit() {
		return getUnit(0x070);
	}

	public Order orderQueueHead() {
		return getOrder(0x074);
	}

	public Order orderQueueTail() {
		return getOrder(0x078);
	}

	/**
	 * < Addon is connected to building (addon has conntected building, but not
	 * in other direction
	 */
	public Unit connectedUnit() {
		return getUnit(0x080);
	}

	/** < @todo Verify */
	public int orderQueueCount() {
		return u8(0x084);
	}

	/** < @todo Unknown */
	public int unknownOrderTimer_0x085() {
		return u8(0x085);
	}

	public int displayedUnitID() {
		return u16(0x088);
	}

	/** < @todo Unknown */
	public int rankIncrease() {
		return u8(0x08E);
	}

	/** < Killcount */
	public int killCount() {
		return u8(0x08F);
	}

	/** < @todo Unknown */
	public int unknownState_0x93() {
		return u8(0x093);
	}

	public int currentButtonSet() {
		return u16(0x094);
	}

	/**
	 * < Queue of units to build. Note that it doesn't begin with index 0, but
	 * with #buildQueueSlot index.
	 */
	public IntArr buildQueue() {
		return u16array(0x098, 5);
	}

	/** < Energy Points */
	public int energy() {
		return u16(0x0A2);
	}

	/** < Index of active unit in #buildQueue. */
	public int buildQueueSlot() {
		return u8(0x0A4);
	}

	/** < A byte used to determine the target ID for the unit */
	public int targetOrderSpecial() {
		return u8(0x0A5);
	}

	/**
	 * < (Build addon verified) @todo verify (Cloak, Build, ExpandCreep
	 * suggested by EUDDB)
	 */
	public int secondaryOrderID() {
		return u8(0x0A6);
	}

	/** < @todo Verify */
	public int hpGainDuringRepair() {
		return u16(0x0A8);
	}

	/** < Remaining bulding time */
	public int remainingBuildTime() {
		return u16(0x0AC);
	}

	/** Child unit information (structure depends on unitID */
	public IntArr loadedUnitIndex() {
		return u16array(0x0B0, 8);
	}

	// TODO:union ChildInfoUnion_type

	// TODO:union ChildUnitUnion1_type

	// TODO:union ChildUnitUnion2_type

	// TODO:union ChildUnitUnion3_type
	/** < @todo Unknown */
	public int upgradeLevel() {
		return u8(0x0CD);
	}

	/**
	 * < @todo Verify (may be set if going to carry something or targetting
	 * resources.. If 'isgathering' ?
	 */
	public int isCarryingSomething() {
		return u8(0x0CE);
	}

	/** < The amount of resources it is carrying */
	public int resourceCarying() {
		return u8(0x0CF);
	}

	// TODO:union UnitUnion1_type

	public int status() {
		return BitMasku32(0x0DC);
	}

	/** < Resource being held by worker: 1 = gas, 2 = ore */
	public int resourceType() {
		return u8(0x0E0);
	}

	/** < @todo Unknown */
	public int wireframeRandomizer() {
		return u8(0x0E1);
	}

	/** < @todo Unknown */
	public int secondaryOrderState() {
		return u8(0x0E2);
	}

	/** < @todo Unknown */
	public int unknownCounterDown_0x0E3() {
		return u8(0x0E3);
	}

	/** < @todo Unknown */
	public Unit currentBuildUnit() {
		return getUnit(0x0EC);
	}

	// TODO:union RallyPsiProviderUnion_type
	/** < @todo Unknown */
	public int pathUnknown_0x100() {
		return u32(0x100);
	}

	/** < @todo Verify - seems like it isn't working */
	public int isBeingHealed() {
		return u8(0x107);
	}

	/** < @todo Unknown */
	public Position contours1Unknown() {
		return getPosition(0x108);
	}

	/** < @todo Unknown */
	public Position contours2Unknown() {
		return getPosition(0x10C);
	}

	/**
	 * < @todo Verified for Hallucination, unverified for DSwarm, DWeb,
	 * Broodling
	 */
	public int removeTimer() {
		return u16(0x110);
	}

	public int defenseMatrixDamage() {
		return u16(0x112);
	}

	public int defenseMatrixTimer() {
		return u8(0x114);
	}

	public int stimTimer() {
		return u8(0x115);
	}

	public int ensnareTimer() {
		return u8(0x116);
	}

	public int lockdownTimer() {
		return u8(0x117);
	}

	public int irradiateTimer() {
		return u8(0x118);
	}

	public int stasisTimer() {
		return u8(0x119);
	}

	public int plagueTimer() {
		return u8(0x11A);
	}

	public int isUnderStorm() {
		return u8(0x11B);
	}

	/** < @todo Verify */
	public Unit irradiatedBy() {
		return getUnit(0x11C);
	}

	/** < @todo Verify */
	public int irradiatePlayerID() {
		return u8(0x120);
	}

	public int parasiteFlags() {
		return BitMasku8(0x121);
	}

	/** < @todo Verify (runs updates approx 2 times per sec) */
	public int cycleCounter() {
		return u8(0x122);
	}

	public int isBlind() {
		return u8(0x123);
	}

	public int maelstromTimer() {
		return u8(0x124);
	}

	/** < @todo Verify */
	public byte[] acidSporeTime() {
		return u8array(0x126, 9);
	}

	/** < @todo Unknown */
	public int offsetIndex3by3() {
		return u16(0x12F);
	}

	/** < @todo Verify (1 if has no air weapon but has a ground) */
	public int airStrength() {
		return u16(0x137);
	}

	/** < @todo Verify (1 if has no ground weapon but has an air) */
	public int groundStrength() {
		return u16(0x139);
	}

	/** < @todo Unknown */
	public int repulseUnknown1() {
		return u8(0x14B);
	}

	/** < @todo Unknown */
	public int repulseUnknown2() {
		return u8(0x14C);
	}

	/** < @todo Unknown (mapsizex/1.5 max) */
	public int driftPosX() {
		return u8(0x14D);
	}

	/** < @todo Unknown (mapsizex/1.5 max) */
	public int driftPosY() {
		return u8(0x14E);
	}

}
