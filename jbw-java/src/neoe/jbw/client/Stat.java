package neoe.jbw.client;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import neoe.jbw.Main;
import neoe.jbw.Utils;
import neoe.jbw.bw.Player;
import neoe.jbw.bw.Unit;
import neoe.jbw.bw.UnitType;
import neoe.jbw.bw.WeaponType;
import neoe.jbw.data.UnitPrototypeFlags;

public class Stat {
	static class P {

		@Override
		public String toString() {

			return String.format("[def G %s:%s:%s A %s:%s:%s|att G %s A %s]",
					ghp, gsp, gmp, ahp, asp, amp, f(ga), f(aa));
		}

		private double f(double d) {
			return Math.round(d * 100) / 100;
		}

		public int ghp;
		public int gsp;
		public int gmp;
		public int ahp;
		public int asp;
		public int amp;
		public double ga;
		public double aa;

	}
	public void run() {
		Utils.print(run1(Utils.getVisibleUnits()));
	}
	public String run1(List<Unit> targets) {
		ByteArrayOutputStream out1 = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(out1, true);
		Map<Integer, P> m = new HashMap<Integer, P>();
		for (Unit u : targets) {
			int pid = u.playerID();
			P p = m.get(pid);
			if (p == null) {
				p = new P();
				m.put(pid, p);
			}
			UnitType ut = u.unitID();
			if (ut.flag(UnitPrototypeFlags.Flyer)) {
				p.ahp += u.healthPoints();
				p.asp += u.shieldPoints();
				p.amp += u.energy();
			} else {
				p.ghp += u.healthPoints();
				p.gsp += u.shieldPoints();
				p.gmp += u.energy();
			}

			// if (ut.flag(UnitPrototypeFlags.Attack)) {
			WeaponType gw = ut.groundWeapon();
			WeaponType aw = ut.airWeapon();
			if (gw.damageCooldown() != 0) {
				p.ga += gw.damageAmount() * 256
						/ ((double) gw.damageCooldown());
			}
			if (aw.damageCooldown() != 0) {
				p.aa += aw.damageAmount() * 256
						/ ((double) aw.damageCooldown());
			}
			// }
		}
		out.println("stat for " + m.size() + " players");
		for (Integer pid : m.keySet()) {
			Player p = Main.players[pid.intValue()];
			P i = m.get(pid);
			out.println(i.toString() + "(" + p.toStr() + ")");
		}
		return out1.toString();
	}

}
