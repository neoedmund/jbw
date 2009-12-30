package neoe.jbw.client;

import neoe.jbw.Log;
import neoe.jbw.Main;
import neoe.jbw.Utils;
import neoe.jbw.bw.IntArr;
import neoe.jbw.bw.Player;
import neoe.jbw.bw.Unit;
import neoe.jbw.bw.UnitType;
import neoe.jbw.cmd.Command;
import neoe.jbw.cmd.Name;
import neoe.jbw.data.UnitPrototypeFlags;

public class TrainHelp {
	int frame = 0;

	public void run() {
		if (Main.frame < frame) {
			return;
		}
		Player me = Utils.me();
		for (Unit u : Utils.getMyUnits()) {
			if (!Utils.isBase(u)
					&& u.unitID().flag(UnitPrototypeFlags.Building)) {
				IntArr arr = u.buildQueue();
				if (arr.size() == 1) {
					int utid = arr.get(0);
					UnitType ut = new UnitType(utid);
					if (me.minerals() > ut.mMineralPrices()
							&& me.gas() > ut.gasPrices()) {
						Log.log("train "+ut.getName());
						Command.add(new Command(Name.TrainUnit, null, utid),
								Utils.toList(u));
						sleep(10);
						return;
					}
				}
			}
		}
		sleep(200);
	}

	private void sleep(int i) {
		frame = Main.frame + 100;
	}

}
