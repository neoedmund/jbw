package neoe.jbw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import neoe.jbw.bw.Unit;
import neoe.jbw.cmd.Command;

public class Utils {

	public static List<Unit> getVisibleUnits() {// not work
		// int addr = BW.u32(BW.BWDATA_UnitNodeChain_VisibleUnit_First);
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

	public static List<Unit> getMyUnits() {// not my unit?
		int pv = BW.u32(BW.BWDATA_UnitNodeChain_PlayerFirstUnit);
		Unit u = new Unit(pv);
		List<Unit> all = new ArrayList<Unit>();
		while (u.base != 0) {
			if (u.playerID() == Main.playId) {
				all.add(u);
			}
			u = u.nextUnit();
		}
		Log.log("getMyUnits count=" + all.size());
		return all;
	}

	/** has problem */
	public static List<Unit> getAllUnits() {
		//
		int cnt = BW.u32(BW.BWDATA_UnitNodeTable_UsedNodeCount);
		Log.log("unit cnt " + cnt);
		List<Unit> all = new ArrayList<Unit>();
		int p = BW.BWDATA_UnitNodeTable;
		// int pv=BW.u32(p);
		Unit u = new Unit(p);
		for (int i = 0; i < cnt; i++) {
			Log.log(u.healthPoints());
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

	/**add command to the buffer*/
	public static void cmd(Command cmd) {
		Command.cmdList.add(cmd);		
	}

	static void issueFrameCommand() {
		Command cmd = Command.cmdList.get(0);	
		
		if (cmd.units.size() > 12) {		
			List<Unit> units = new ArrayList<Unit>(cmd.units.subList(0, 12));
			cmd.units=cmd.units.subList(12,cmd.units.size());
			byte[] bs = Command.select(units);
			Log.log("cmd1="+Arrays.toString(bs));
			BW.command(bs, bs.length);
			bs = cmd.getBytes();
			Log.log("cmd2="+Arrays.toString(bs));
			BW.command(bs, bs.length);
		} else {
			byte[] bs = Command.select(cmd.units);
			Log.log("cmd3="+Arrays.toString(bs));
			BW.command(bs, bs.length);
			bs = cmd.getBytes();
			Log.log("cmd4="+Arrays.toString(bs));
			BW.command(bs, bs.length);
			Command.cmdList.remove(0);
		}		
	}

}
/*
 * for (int i = 0; i < BW::PLAYABLE_PLAYER_COUNT; i++) if
 * (strcmp(BW::BWDATA_CurrentPlayer, this->players[i]->getName().c_str()) == 0)
 * this->BWAPIPlayer = this->players[i];
 */