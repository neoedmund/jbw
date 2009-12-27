package neoe.jbw.client;

import java.util.ArrayList;
import java.util.List;

import neoe.jbw.Log;
import neoe.jbw.Main;
import neoe.jbw.Utils;
import neoe.jbw.bw.Supply;
import neoe.jbw.bw.Unit;
import neoe.jbw.cmd.Command;
import neoe.jbw.cmd.Name;
import neoe.jbw.data.Order;
import neoe.jbw.data.UnitID;

/**
 * help to mining. TODO: gas mining, supply build
 * 
 * @author neoe
 * 
 */
public class BuildHelp {

	private Game game;
	private ArrayList<Unit> bases;
	private List<Unit> all;
	private List<Unit> my;
	private ArrayList<Unit> mineral;

	public BuildHelp(Game game) {
		this.game = game;
	}

	public void run() {
		if (Main.frame % 10 != 1) {
			return;
		}
		{
			my = Utils.getMyUnits();
			all = Utils.getVisibleUnits();
			bases = new ArrayList<Unit>();
			for (Unit u : my) {
				if (Utils.isBase(u)) {
					bases.add(u);
				}
			}
			mineral = new ArrayList<Unit>();
			for (Unit u : all) {
				int tid = u.unitID().id;

				if ((tid == UnitID.Resource_MineralPatch1
						|| tid == UnitID.Resource_MineralPatch2 || tid == UnitID.Resource_MineralPatch3)) {

					mineral.add(u);
				}

			}
		}

		Supply supply = new Supply(Main.playerId, Utils.me().race());
		// Log.log("supply " + supply.available() + "-" + supply.used());
		int remain = supply.available() - supply.used();
		if (remain < 4)
			buildSupply();
		if (remain > 0)
			buildHelp();
		gotoWork();
	}

	private void gotoWork() {

		// List<Unit> idleWorker =new ArrayList<Unit>();
		for (Unit u : my) {
			int tid = u.unitID().id;

			if (tid == UnitID.T_SCV || tid == UnitID.Z_Drone
					|| tid == UnitID.P_Probe) {

				if (u.orderID() == Order.PlayerGuard) {
					// idleWorker.add(u);
					Unit base = getNearest(bases, u);
					if (base != null) {
						Unit m = getNearest(mineral, base);
						if (m != null) {
							Command.add(new Command(Name.RightClick, m
									.position().toPos(m), 0), Utils.toList(u));
							// Log.log("get to mining "+idleWorker.size());
						}
					}
				}

			}
		}

	}

	private void buildSupply() {
		if (bases.size() > 0) {
			for (Unit b : bases) {
				if (b.unitID().id == UnitID.Z_Larva
						&& b.orderID() != Order.Morph2
						&& b.orderID() != Order.Morph1) {
					Command.add(new Command(Name.UnitMorph, null,
							UnitID.Z_Overlord), Utils.toList(b));
				}
			}
		}
		// TODO Auto-generated method stub

	}

	double miningDistance = 500;

	private void buildHelp() {
		if (Utils.me().minerals() < 50)
			return;

		if (bases.size() == 0) {
//			Utils.print("no base found. build-helper disabled.");
//			game.buildMode = false;
			return;
		} else {
			// Log.log("find " + bases.size() + " bases");
		}

		for (Unit base : bases) {
			if (base.buildQueue().size() > 0)
				continue;
			int minerals = 0;
			int worker = 0;
			for (Unit u : mineral) {
				if (Utils.getDistance(base, u) < miningDistance) {
					minerals++;
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

			if (worker < minerals * 1.6) {
				trainWorker(base);
			}

		}
	}

	private Unit getNearest(List<Unit> mineral, Unit base) {
		double min = Double.MAX_VALUE;
		Unit v = null;
		for (Unit u : mineral) {
			double d = Utils.getDistance(u, base);
			if (d < min) {
				min = d;
				v = u;
			}
		}
		return v;
	}

	private void trainWorker(Unit u) {
		int id = u.unitID().id;
		int wid = 0;
		if (id == UnitID.T_CommandCenter)
			wid = UnitID.T_SCV;
		else if (id == UnitID.Z_Larva)
			wid = UnitID.Z_Drone;
		else if (id == UnitID.P_Nexus)
			wid = UnitID.P_Probe;
		else
			return;

		if (wid == UnitID.Z_Drone) {
			if (bases.size() > 0) {
				for (Unit b : bases) {
					if (b.unitID().id == UnitID.Z_Larva
							&& b.orderID() != Order.Morph2
							&& b.orderID() != Order.Morph1) {
						Command.add(new Command(Name.UnitMorph, null,
								UnitID.Z_Drone), Utils.toList(b));
					}
				}
			}
		} else {
			Log.log("train worker");
			Command
					.add(new Command(Name.TrainUnit, null, wid), Utils
							.toList(u));
		}
	}
}
