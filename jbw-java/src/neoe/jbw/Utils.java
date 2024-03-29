package neoe.jbw;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import neoe.jbw.bw.Player;
import neoe.jbw.bw.Position;
import neoe.jbw.bw.Unit;
import neoe.jbw.bw.UnitType;
import neoe.jbw.bw.WeaponType;
import neoe.jbw.data.Offset;
import neoe.jbw.data.UnitID;
import neoe.jbw.data.UnitPrototypeFlags;

public class Utils {

	public static List<Unit> getVisibleUnits() {
		int pv = BW.u32(Offset.BWDATA_UnitNodeChain_VisibleUnit_First);
		Unit u = new Unit(pv);
		List<Unit> all = new ArrayList<Unit>();
		while (u.base != 0) {
			all.add(u);
			u = u.nextUnit();
		}
		// Log.log("getVisibleUnits count=" + all.size());
		return all;
	}

	public static List<Unit> getMyUnits() {
		// Log.log("my playerid="+Main.playerId);
		List<Unit> all = new ArrayList<Unit>();
		for (Unit u : getVisibleUnits()) {
			if (u.playerID() == Main.playerId) {
				all.add(u);
			}
		}
		// Log.log("getMyUnits count=" + all.size());
		return all;
	}

	/**
	 * has problem
	 * 
	 * @deprecated use getVisibleUnits
	 */
	public static List<Unit> getAllUnits() {
		//
		int cnt = BW.u32(Offset.BWDATA_UnitNodeTable_UsedNodeCount);
		Log.log("unit cnt " + cnt);
		List<Unit> all = new ArrayList<Unit>();
		int p = Offset.BWDATA_UnitNodeTable;
		// int pv=BW.u32(p);
		Unit u = new Unit(p);
		for (int i = 0; i < cnt; i++) {
			// Log.log(u.healthPoints());
			all.add(u);
			p += Offset.UNIT_SIZE_IN_BYTES;
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
		String playName = BW.getStr(Offset.BWDATA_CurrentPlayerName);
		Log.log(playName);
		int playId = -1;
		for (int i = 0; i < Offset.PLAYABLE_PLAYER_COUNT; i++) {
			String t = "";
			if (Main.players[i].name().equals(playName)) {
				t = "(me)";
				playId = i;
			}
			Log.log(Main.players[i].toStr() + t);
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < Offset.PLAYABLE_PLAYER_COUNT; i++) {
			for (int j = 0; j < Offset.PLAYABLE_PLAYER_COUNT; j++) {
				sb.append(" " + Utils.alliance(i, j));
			}
			sb.append("\r\n");
		}
		Log.log("\r\n" + sb);
		return playId;
	}

	public static boolean isBase(Unit u) {
		int id = u.unitID().id;
		if (id == UnitID.T_CommandCenter || id == UnitID.Z_Larva || id == UnitID.P_Nexus) {
			return true;
		}
		return false;

	}

	public static void print(String s) {
		Log.log("[M]" + s);
		BW.print1(Main.playerId, s);
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

	public static void printException(Exception e) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		e.printStackTrace(new PrintStream(out, true));
		print(out.toString());
	}

	public static List<Unit> getMyArmy() {
		List<Unit> res = new ArrayList<Unit>();
		for (Unit u : getMyUnits()) {
			UnitType ut = u.unitID();
			if (
			// ut.groupflag(GroupFlag.Men)
			(ut.groundWeapon().id != WeaponType.None || ut.airWeapon().id != WeaponType.None || ut.flag(UnitPrototypeFlags.Spellcaster)) && (ut.id != UnitID.Z_Overlord)
					&& !ut.flag(UnitPrototypeFlags.Worker) && !ut.flag(UnitPrototypeFlags.Hero))
				res.add(u);
		}
		return res;
	}

	public static List<Unit> filterOrder(List<Unit> us, int order) {
		List<Unit> res = new ArrayList<Unit>();
		for (Unit u : us) {
			if (u.orderID() == order)
				res.add(u);
		}
		return res;
	}

	public static int alliance(int pid1, int pid2) {
		return BW.u8(Offset.BWDATA_Alliance + (pid1 * Offset.PLAYER_COUNT) + pid2);
	}

	public static boolean isEnemy(int pid1, int pid2) {
		if (pid1 == pid2)
			return false;
		return alliance(pid1, pid2) == 0;
	}

	public static List<Unit> getVisibleEnemies() {
		int pv = BW.u32(Offset.BWDATA_UnitNodeChain_VisibleUnit_First);
		Unit u = new Unit(pv);
		List<Unit> all = new ArrayList<Unit>();
		while (u.base != 0) {
			if (isEnemy(Main.playerId, u.playerID())) {
				all.add(u);
			}
			u = u.nextUnit();
		}
		return all;
	}

	/**
	 * set game speed (0 is the fastest. Tournament speed is 20), normal is 30
	 * 
	 * @param speed
	 */
	public static void setGameSpeed(int speed) {
		if (speed < 0)
			speed = 0;
		for (int i = 0; i < 7; ++i) {
			BW.writeU32(Offset.GameSpeedModifiers + i * 4, speed);
			BW.writeU32(Offset.GameSpeedModifiers + (i + 7) * 4, speed * 3);
		}

	}

}
