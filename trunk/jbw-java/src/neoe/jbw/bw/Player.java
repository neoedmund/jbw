package neoe.jbw.bw;

import neoe.jbw.BW;
import neoe.jbw.data.Offset;

public class Player extends Struct {

	private int id;

	public Player(int id) {
		super(id);
		this.id = id;
	}

	private final static int SIZEOFS32 = 4;
	private final static int ResLen = Offset.PLAYER_COUNT * SIZEOFS32;
	private final static int PlayerInfoLen = 36;// 8+3+25

	public int minerals() {
		return BW.u32(Offset.BWDATA_PlayerResources + SIZEOFS32 * id);
	}

	public int gas() {
		return BW.u32(Offset.BWDATA_PlayerResources + SIZEOFS32 * id + ResLen);
	}

	public int cumulativeGas() {
		return BW.u32(Offset.BWDATA_PlayerResources + SIZEOFS32 * id + ResLen * 2);
	}

	public int cumulativeMinerals() {
		return BW.u32(Offset.BWDATA_PlayerResources + SIZEOFS32 * id + ResLen * 3);
	}

	/** u32 */
	public int xid() {
		return BW.u32(Offset.BWDATA_Players + PlayerInfoLen * id + 0);
	}

	/** u8 */
	public int type() {
		return BW.u8(Offset.BWDATA_Players + PlayerInfoLen * id + 8);
	}

	/** u8 */
	public int race() {
		return BW.u8(Offset.BWDATA_Players + PlayerInfoLen * id + 9);
	}

	/** u8 */
	public int force() {
		return BW.u8(Offset.BWDATA_Players + PlayerInfoLen * id + 10);
	}

	/** char name[25] */
	public String name() {
		return BW.getStr(Offset.BWDATA_Players + PlayerInfoLen * id + 11);
	}
	public String toStr(){
		return String.format("[%s,%s,%s,%s,%s]",name(),xid(),force(),race(),type());
	}
}
