package neoe.jbw;

import java.util.ArrayList;
import java.util.List;

import neoe.jbw.bw.Player;
import neoe.jbw.bw.Position;
import neoe.jbw.bw.Unit;
import neoe.jbw.data.UnitID;

public class Utils {

	public static List<Unit> getVisibleUnits() {
		int pv = BW.u32(BW.BWDATA_UnitNodeChain_VisibleUnit_First);
		Unit u = new Unit(pv);
		List<Unit> all = new ArrayList<Unit>();
		while (u.base != 0) {
			all.add(u);
			u = u.nextUnit();
		}
		Log.log("getVisibleUnits count=" + all.size());
		return all;
	}

	public static List<Unit> getMyUnits() {
		Log.log("my playerid="+Main.playerId);
		List<Unit> all = new ArrayList<Unit>();
		for (Unit u : getVisibleUnits()){
			if (u.playerID()==Main.playerId){
				all.add(u);
			}
		}
		Log.log("getMyUnits count=" + all.size());
		return all;
	}

	/** has problem
	 * @deprecated use getVisibleUnits */
	public static List<Unit> getAllUnits() {
		//
		int cnt = BW.u32(BW.BWDATA_UnitNodeTable_UsedNodeCount);
		Log.log("unit cnt " + cnt);
		List<Unit> all = new ArrayList<Unit>();
		int p = BW.BWDATA_UnitNodeTable;
		// int pv=BW.u32(p);
		Unit u = new Unit(p);
		for (int i = 0; i < cnt; i++) {
			//Log.log(u.healthPoints());
			all.add(u);
			p += BW.UNIT_SIZE_IN_BYTES;
			u = new Unit(p);
		}
		Log.log("getAllUnits count=" + all.size());
		return all;
	}

	public static boolean sameList(List<Unit> a, List<Unit> b) {
		if (a.size() != b.size())
			return false;
		for (int i = 0; i < a.size(); i++) {
			if (!a.get(i).equals(b.get(i)))
				return false;
		}
		return true;
	}

	public static int getPlayId() {
		String playName = BW.getStr(BW.BWDATA_CurrentPlayerName);
		Log.log(playName);
		int playId = -1;
		for (int i = 0; i < BW.PLAYABLE_PLAYER_COUNT; i++) {
			String s;
			String t = "Player" + i + ":" + (s = Main.players[i].name());
			if (s.equals(playName)) {
				t = t + "(me)";
				playId = i;
			}
			Log.log(t);
		}
		return playId;
	}

	public static boolean isBase(Unit u) {
		int id = u.unitID().id;
		if (id == UnitID.T_CommandCenter || id == UnitID.Z_Hatchery
				|| id == UnitID.P_Nexus) {
			return true;
		}
		return false;

	}

	public static void print(String s) {
		Log.log("[M]" + s);
		BW.print(Main.playerId, s);
	}

	public static double getDistance(Unit u1, Unit u2) {
		return getDistance(u1.position(), u2.position());
	}

	private static double getDistance(Position p1, Position p2) {
		int x1 = p1.x();
		int y1 = p1.y();
		int x2 = p2.x();
		int y2 = p2.y();
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}

	public static List<Unit> toList(Unit u) {
		List<Unit> us = new ArrayList<Unit>();
		us.add(u);
		return us;
	}

	public static Player me() {
		return Main.players[Main.playerId];
	}
}