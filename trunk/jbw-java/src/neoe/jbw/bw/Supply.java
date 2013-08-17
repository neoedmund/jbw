package neoe.jbw.bw;

import neoe.jbw.BW;
import neoe.jbw.data.Offset;

public class Supply extends Struct {

	private int id;
	private int race;

	public Supply(int id, int race) {
		super(id);
		this.id = id;
		this.race = race;
	}

	private final static int SIZEOFS32 = 4;
	private final static int ResLen = Offset.PLAYER_COUNT * SIZEOFS32;
	private final static int RaceLen = ResLen * 3;

	public int available() {
		return BW.u32(Offset.BWDATA_Supplies + SIZEOFS32 * id + RaceLen * race);
	}

	public int used() {
		return BW.u32(Offset.BWDATA_Supplies + SIZEOFS32 * id + ResLen + RaceLen
				* race);
	}

	public int max() {
		return BW.u32(Offset.BWDATA_Supplies + SIZEOFS32 * id + ResLen * 2
				+ RaceLen * race);
	}
}
