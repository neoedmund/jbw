package neoe.jbw.cmd;

import static neoe.jbw.cmd.Name.Attack;
import static neoe.jbw.cmd.Name.BuildingMorph;
import static neoe.jbw.cmd.Name.Burrow;
import static neoe.jbw.cmd.Name.CancelAddon;
import static neoe.jbw.cmd.Name.CancelConstruction;
import static neoe.jbw.cmd.Name.CancelNuke;
import static neoe.jbw.cmd.Name.CancelResearch;
import static neoe.jbw.cmd.Name.CancelTrain;
import static neoe.jbw.cmd.Name.CancelTrainLast;
import static neoe.jbw.cmd.Name.CancelUnitMorph;
import static neoe.jbw.cmd.Name.CancelUpgrade;
import static neoe.jbw.cmd.Name.CarrierStop;
import static neoe.jbw.cmd.Name.ChangeRace;
import static neoe.jbw.cmd.Name.ChangeSlot;
import static neoe.jbw.cmd.Name.Cloak;
import static neoe.jbw.cmd.Name.Decloak;
import static neoe.jbw.cmd.Name.HoldPosition;
import static neoe.jbw.cmd.Name.Invent;
import static neoe.jbw.cmd.Name.Land;
import static neoe.jbw.cmd.Name.LeaveGame;
import static neoe.jbw.cmd.Name.Lift;
import static neoe.jbw.cmd.Name.MakeAddon;
import static neoe.jbw.cmd.Name.MakeBuilding;
import static neoe.jbw.cmd.Name.MergeArchon;
import static neoe.jbw.cmd.Name.MergeDarkArchon;
import static neoe.jbw.cmd.Name.MinimapPing;
import static neoe.jbw.cmd.Name.PauseGame;
import static neoe.jbw.cmd.Name.ReaverStop;
import static neoe.jbw.cmd.Name.ResumeGame;
import static neoe.jbw.cmd.Name.ReturnCargo;
import static neoe.jbw.cmd.Name.RightClick;
import static neoe.jbw.cmd.Name.Select;
import static neoe.jbw.cmd.Name.SendText;
import static neoe.jbw.cmd.Name.Siege;
import static neoe.jbw.cmd.Name.StartGame;
import static neoe.jbw.cmd.Name.Stop;
import static neoe.jbw.cmd.Name.TrainFighter;
import static neoe.jbw.cmd.Name.TrainUnit;
import static neoe.jbw.cmd.Name.Unburrow;
import static neoe.jbw.cmd.Name.UnitMorph;
import static neoe.jbw.cmd.Name.UnloadAll;
import static neoe.jbw.cmd.Name.UnloadUnit;
import static neoe.jbw.cmd.Name.Unsiege;
import static neoe.jbw.cmd.Name.Upgrade;
import static neoe.jbw.cmd.Name.UseCheat;
import static neoe.jbw.cmd.Name.UseStimPack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import neoe.jbw.BW;
import neoe.jbw.Log;
import neoe.jbw.Pos;
import neoe.jbw.bw.Unit;
import neoe.jbw.bytes.ToBytes;
import neoe.jbw.bytes.U16;

public class Command {
	private static Queue<Command> cmdQueue;

	private static byte[] getBytes1(Object[] m) {
		BA ba = new BA();
		for (Object o : m) {
			if (o instanceof ToBytes)
				ba.add((ToBytes) o);
			else if (o instanceof Pos) {
				Pos p = (Pos) o;
				ba.add(new U16(p.x));
				ba.add(new U16(p.y));
				ba.add(new U16(0));
			} else
				ba.add((Integer) o);
		}
		return ba.toBA();
	}

	Name name;

	Pos pos;

	public List<Unit> units;

	public Command(Name name, List<Unit> units, Pos pos) {
		this.name = name;
		this.units = units;
		this.pos = pos;
	}

	private byte[] Attack() {
		Object[] m = { 0x15, pos, 0xe4, 0, Order.AttackMove, 0, 0, 0, 0x00 };
		return getBytes1(m);
	}

	private byte[] BuildingMorph() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] Burrow() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] CancelAddon() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] CancelConstruction() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] CancelNuke() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] CancelResearch() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] CancelTrain() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] CancelTrainLast() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] CancelUnitMorph() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] CancelUpgrade() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] CarrierStop() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] ChangeRace() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] ChangeSlot() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] Cloak() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] Decloak() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	public byte[] getBytes() {
		if (name == Select)
			return Select();
		else if (name == Attack)
			return Attack();
		else if (name == RightClick)
			return RightClick();		
		else if (name == TrainUnit)
			return TrainUnit();
		else if (name == TrainFighter)
			return TrainFighter();
		else if (name == MakeBuilding)
			return MakeBuilding();
		else if (name == Invent)
			return Invent();
		else if (name == Upgrade)
			return Upgrade();
		else if (name == MakeAddon)
			return MakeAddon();
		else if (name == ChangeSlot)
			return ChangeSlot();
		else if (name == ChangeRace)
			return ChangeRace();
		else if (name == StartGame)
			return StartGame();
		else if (name == PauseGame)
			return PauseGame();
		else if (name == ResumeGame)
			return ResumeGame();
		else if (name == LeaveGame)
			return LeaveGame();
		else if (name == MergeDarkArchon)
			return MergeDarkArchon();
		else if (name == MergeArchon)
			return MergeArchon();
		else if (name == MinimapPing)
			return MinimapPing();
		else if (name == UseStimPack)
			return UseStimPack();
		else if (name == BuildingMorph)
			return BuildingMorph();
		else if (name == CancelAddon)
			return CancelAddon();
		else if (name == CancelUpgrade)
			return CancelUpgrade();
		else if (name == CancelResearch)
			return CancelResearch();
		else if (name == CancelNuke)
			return CancelNuke();
		else if (name == Lift)
			return Lift();
		else if (name == Land)
			return Land();
		else if (name == Burrow)
			return Burrow();
		else if (name == Unburrow)
			return Unburrow();
		else if (name == HoldPosition)
			return HoldPosition();
		else if (name == UnloadUnit)
			return UnloadUnit();
		else if (name == UnloadAll)
			return UnloadAll();
		else if (name == Siege)
			return Siege();
		else if (name == Unsiege)
			return Unsiege();
		else if (name == UnitMorph)
			return UnitMorph();
		else if (name == Cloak)
			return Cloak();
		else if (name == Decloak)
			return Decloak();
		else if (name == ReturnCargo)
			return ReturnCargo();
		else if (name == Stop)
			return Stop();
		else if (name == ReaverStop)
			return ReaverStop();
		else if (name == CarrierStop)
			return CarrierStop();
		else if (name == CancelUnitMorph)
			return CancelUnitMorph();
		else if (name == CancelConstruction)
			return CancelConstruction();
		else if (name == SendText)
			return SendText();
		else if (name == UseCheat)
			return UseCheat();
		else if (name == CancelTrain)
			return CancelTrain();
		else if (name == CancelTrainLast)
			return CancelTrainLast();

		return null;
	}

	private byte[] Select() {
		BA ba = new BA().add(0x09).add(units.size());
		for (Unit u : units) {
			int fromIndex = (u.base - BW.BWDATA_UnitNodeTable)
					/ BW.UNIT_SIZE_IN_BYTES + 1;
			ba.add(new U16(fromIndex + (1 << 11)));
		}
		return ba.toBA();
	}

	private byte[] HoldPosition() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] Invent() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] Land() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] LeaveGame() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] Lift() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] MakeAddon() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] MakeBuilding() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] MergeArchon() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] MergeDarkArchon() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] MinimapPing() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] PauseGame() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] ReaverStop() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] ResumeGame() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] ReturnCargo() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] RightClick() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] SendText() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] Siege() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] StartGame() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] Stop() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] TrainFighter() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] TrainUnit() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] Unburrow() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] UnitMorph() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] UnloadAll() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] UnloadUnit() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] Unsiege() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] Upgrade() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] UseCheat() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	private byte[] UseStimPack() {
		Object[] m = {};// TODO
		return getBytes1(m);
	}

	public static boolean hasCommand() {
		return cmdQueue.size() > 0;
	}

	public static void issueFrameCommand() {
		Command cmd = Command.cmdQueue.poll();
		if (cmd == null) {
			Log.log("bug issueFrameCommand");
			return;
		}
		byte[] bs = cmd.getBytes();
		Log.log("cmd=" + Arrays.toString(bs));
		BW.command(bs, bs.length);
	}

	public static void initQueue() {
		cmdQueue = new ConcurrentLinkedQueue<Command>();
	}

	public static void add(Name cmdname, List<Unit> cmdunits, Pos cmdpos) {
		Command act = new Command(cmdname, null, cmdpos);
		while (cmdunits.size() > 12) {
			List<Unit> units = new ArrayList<Unit>(cmdunits.subList(0, 12));
			cmdunits = cmdunits.subList(12, cmdunits.size());
			cmdQueue.add(new Command(Name.Select, units, null));
			cmdQueue.add(act);
		}
		if (cmdunits.size() > 0) {
			cmdQueue.add(new Command(Name.Select,
					new ArrayList<Unit>(cmdunits), null));
			cmdQueue.add(act);
		}
	}

}
