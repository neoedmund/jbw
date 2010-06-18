package neoe.jbw;

import neoe.jbw.bw.Position;
import neoe.jbw.bw.Unit;

public interface IGame {

	void onUnitDeath(Unit unit);

	void onText(String text);

	void onEnd();

	void onFrame();

	void onNuclearLaunchDetected(Position orderTargetPos);

	void start();

}
