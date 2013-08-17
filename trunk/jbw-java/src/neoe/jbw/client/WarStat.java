package neoe.jbw.client;

import java.util.ArrayList;
import java.util.List;

import neoe.jbw.BW;
import neoe.jbw.Log;
import neoe.jbw.Main;
import neoe.jbw.PosUnit;
import neoe.jbw.Utils;
import neoe.jbw.bw.AttackType;
import neoe.jbw.bw.Unit;
import neoe.jbw.cmd.Command;
import neoe.jbw.cmd.Name;
import neoe.jbw.data.AttackTypeID;
import neoe.jbw.data.Offset;
import neoe.jbw.data.UnitPrototypeFlags;

public class WarStat {

	boolean inWar = false;
	private List<Unit> dead;

	public void run() {
		if (Main.frame % 20 != 1)
			return;
		int first = BW.u32(Offset.BWDATA_AttackNodeTable_FirstElement);
		int atc = 0;
		AttackType at1 = null;
		if (first != 0) {
			AttackType at = new AttackType(first);
			while (true) {
				if (at.type() == AttackTypeID.Fusion_Cutter_Hit
						|| at.type() == AttackTypeID.Particle_Beam_Hit) {
				} else {
					// Log.log(at.toStr());
					at1 = at;
					atc++;
				}
				at = at.next();
				if (at == null)
					break;
			}
		}

		if (!inWar) {
			if (atc != 0 && engadged()) {
				BW.print1(8, "war start " + Main.frame);
				inWar = true;
				dead = new ArrayList<Unit>();
				Command.add(new Command(Name.MinimapPing, new PosUnit(at1.pos_x(),
						at1.pos_y(), 0), 0), null);
			}
		} else {
			if (atc == 0 && !engadged()) {
				BW.print1(8, "war end " + Main.frame);
				inWar = false;
				Utils.print(new Stat().run1(dead));
				dead = null;
			}
		}
	}

	private boolean engadged() {
		// long t2,t1=System.currentTimeMillis();
		List<Unit> all2 = Utils.getVisibleUnits();
		List<Unit> all = new ArrayList<Unit>();
		for (Unit u : all2) {
			if (Main.players[u.playerID()].type() != 0) {
				all.add(u);
			}
		}
		// Log.log(((t2=System.currentTimeMillis())-t1)+" ms.A "+all2.size()+","+all.size());
		for (Unit u1 : all) {
			for (Unit u2 : all) {
				if (!Utils.isEnemy(u1.playerID(), u2.playerID()))
					continue;
				double dis = Utils.getDistance(u1, u2);
				if (u2.unitID().flag(UnitPrototypeFlags.Flyer)) {
					if (u1.unitID().airWeapon().damageAmount() > 0
							&& u1.unitID().airWeapon().maxRange() > dis) {
						Log.log(u1.unitID().airWeapon().toStr() + ","
								+ u1.toStr1() + "+" + u2.toStr1() + "<" + dis);
						return true;
					}
				} else {
					if (u1.unitID().groundWeapon().damageAmount() > 0
							&& u1.unitID().groundWeapon().maxRange() > dis) {
						Log.log(u1.unitID().groundWeapon().toStr() + ","
								+ u1.toStr1() + "+" + u2.toStr1() + "<" + dis);
						return true;
					}
				}
				if (u1.unitID().flag(UnitPrototypeFlags.Flyer)) {
					if (u2.unitID().airWeapon().damageAmount() > 0
							&& u2.unitID().airWeapon().maxRange() > dis) {
						Log.log(u2.unitID().airWeapon().toStr() + ","
								+ u1.toStr1() + "+" + u2.toStr1() + "<" + dis);
						return true;
					}
				} else {
					if (u2.unitID().groundWeapon().damageAmount() > 0
							&& u2.unitID().groundWeapon().maxRange() > dis) {
						Log.log(u2.unitID().groundWeapon().toStr() + ","
								+ u1.toStr1() + "+" + u2.toStr1() + "<" + dis);
						return true;
					}
				}
			}
		}
		// Log.log((System.currentTimeMillis()-t2)+" ms.B");
		return false;
	}

	public void onUnitDeath(Unit unit) {
		if (!inWar) {
			Log.log("die out war " + unit.toStr1());
		} else {
			dead.add(unit);
		}

	}

}
