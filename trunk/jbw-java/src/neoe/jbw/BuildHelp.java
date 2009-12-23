package neoe.jbw;

import java.util.ArrayList;
import java.util.List;

import neoe.jbw.bw.Supply;
import neoe.jbw.bw.Unit;
import neoe.jbw.cmd.Command;
import neoe.jbw.cmd.Name;
import neoe.jbw.data.UnitID;

public class BuildHelp {

	private Game game;

	public BuildHelp(Game game) {
		this.game = game;
	}

	public void run() {
		if (Main.frame % 10 != 1) {
			return;
		}
		Supply supply = new Supply(Main.playerId, Utils.me().race());
		Log.log("supply " + supply.available() + "-" + supply.used());
		int remain = supply.available() - supply.used();
		if (remain < 4)
			buildSupply();
		if (remain > 0)
			buildHelp();
	}

	private void buildSupply() {
		// TODO Auto-generated method stub

	}

	private void buildHelp() {
		if (Utils.me().minerals() < 50)
			return;
		List<Unit> my = Utils.getMyUnits();
		List<Unit> all = Utils.getVisibleUnits();
		List<Unit> bases = new ArrayList<Unit>();
		for (Unit u : my) {
			if (Utils.isBase(u)) {
				bases.add(u);
			}
		}
		if (bases.size() == 0) {
			Utils.print("no base found. build-helper disabled.");
			game.buildMode = false;
			return;
		} else {
			Log.log("find " + bases.size() + " bases");

		}
		double miningDistance = 500;
		for (Unit base : bases) {
			if (base.buildQueue().size() > 0)
				continue;
			int minerals = 0;
			int worker = 0;
			for (Unit u : all) {
				int tid = u.unitID().id;
				if (Utils.getDistance(base, u) < miningDistance) {
					if ((tid == UnitID.Resource_MineralPatch1
							|| tid == UnitID.Resource_MineralPatch2 || tid == UnitID.Resource_MineralPatch3)) {
						minerals++;

					}
				}
			}
			for (Unit u : my) {
				int tid = u.unitID().id;
				if (Utils.getDistance(base, u) < miningDistance) {
					if (tid == UnitID.T_SCV || tid == UnitID.Z_Drone
							|| tid == UnitID.P_Probe) {
						worker++;
					}
				}
			}
			Log.log("base " + base.base + " m:" + minerals + " w:" + worker);
			if (worker < minerals * 1.6) {
				trainWorker(base);
			}
		}
	}

	private void trainWorker(Unit u) {
		int id = u.unitID().id;
		int wid = 0;
		if (id == UnitID.T_CommandCenter)
			wid = UnitID.T_SCV;
		else if (id == UnitID.Z_Hatchery)
			wid = UnitID.Z_Drone;
		else if (id == UnitID.P_Nexus)
			wid = UnitID.P_Probe;
		else
			return;
		Log.log("train worker");
		Command.add(new Command(Name.TrainUnit, null, wid), Utils.toList(u));
	}
}
