package neoe.jbw.ai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import neoe.jbw.BW;
import neoe.jbw.IGame;
import neoe.jbw.Utils;
import neoe.jbw.bw.Position;
import neoe.jbw.bw.Unit;
import static neoe.jbw.data.UnitID.*;

public class AiZerg01 implements IGame {

	public interface Call {

		AiCmd call();

	}

	@Override
	public void onEnd() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFrame() {
		// all scope
		allScopeCmd();
		// each unit
		List<Unit> my = Utils.getMyUnits();
		for (Unit u : my) {
			AiCmd cmd = cmds.get(u);
			if (cmd == null) {
				cmd = getCommand(u);
				issue(u, cmd);
				cmds.put(u, cmd);
			} else {
				if (isFinish(cmd, u)) {
					cmds.remove(u);
				}
			}
		}
	}

	private void allScopeCmd() {
		List<Unit> enemy=Utils.getVisibleEnemies();
		
	}

	private void issue(Unit u, AiCmd cmd) {
		// TODO Auto-generated method stub

	}

	private boolean isFinish(AiCmd cmd, Unit u) {
		if (cmd == NoCmd)
			return false;
		//TODO
		return false;
	}

	final AiCmd NoCmd = new AiCmd(0);

	class GetCommand {

		final Call NoCall = new Call() {
			public AiCmd call() {
				return NoCmd;
			}
		};
		Map<Integer, Call> ms;

		GetCommand() {
			ms = new TreeMap<Integer, Call>();
			// @formatter:off 
			ms.put(T_Marine , new Call() {public AiCmd call() {return T_Marine ();}});
			ms.put(T_Ghost , new Call() {public AiCmd call() {return T_Ghost ();}});
			ms.put(T_Vulture , new Call() {public AiCmd call() {return T_Vulture ();}});
			ms.put(T_Goliath , new Call() {public AiCmd call() {return T_Goliath ();}});
			ms.put(T_GoliathTurret , new Call() {public AiCmd call() {return T_GoliathTurret ();}});
			ms.put(T_SiegeTankTankMode , new Call() {public AiCmd call() {return T_SiegeTankTankMode ();}});
			ms.put(T_TankTurretTankMode , new Call() {public AiCmd call() {return T_TankTurretTankMode ();}});
			ms.put(T_SCV , new Call() {public AiCmd call() {return T_SCV ();}});
			ms.put(T_Wraith , new Call() {public AiCmd call() {return T_Wraith ();}});
			ms.put(T_ScienceVessel , new Call() {public AiCmd call() {return T_ScienceVessel ();}});
			ms.put(T_Hero_GuiMontag , new Call() {public AiCmd call() {return T_Hero_GuiMontag ();}});
			ms.put(T_Dropship , new Call() {public AiCmd call() {return T_Dropship ();}});
			ms.put(T_Battlecruiser , new Call() {public AiCmd call() {return T_Battlecruiser ();}});
			ms.put(T_VultureSpiderMine , new Call() {public AiCmd call() {return T_VultureSpiderMine ();}});
			ms.put(T_NuclearMissile , new Call() {public AiCmd call() {return T_NuclearMissile ();}});
			ms.put(T_Hero_Civilian , new Call() {public AiCmd call() {return T_Hero_Civilian ();}});
			ms.put(T_Hero_SarahKerrigan , new Call() {public AiCmd call() {return T_Hero_SarahKerrigan ();}});
			ms.put(T_Hero_AlanSchezar , new Call() {public AiCmd call() {return T_Hero_AlanSchezar ();}});
			ms.put(T_Hero_AlanTurret , new Call() {public AiCmd call() {return T_Hero_AlanTurret ();}});
			ms.put(T_Hero_JimRaynorV , new Call() {public AiCmd call() {return T_Hero_JimRaynorV ();}});
			ms.put(T_Hero_JimRaynorM , new Call() {public AiCmd call() {return T_Hero_JimRaynorM ();}});
			ms.put(T_Hero_TomKazansky , new Call() {public AiCmd call() {return T_Hero_TomKazansky ();}});
			ms.put(T_Hero_Magellan , new Call() {public AiCmd call() {return T_Hero_Magellan ();}});
			ms.put(T_Hero_EdmundDukeT , new Call() {public AiCmd call() {return T_Hero_EdmundDukeT ();}});
			ms.put(T_Hero_EdmundDukeTTurret , new Call() {public AiCmd call() {return T_Hero_EdmundDukeTTurret ();}});
			ms.put(T_Hero_EdmundDukeS , new Call() {public AiCmd call() {return T_Hero_EdmundDukeS ();}});
			ms.put(T_Hero_EdmundDukeSTurret , new Call() {public AiCmd call() {return T_Hero_EdmundDukeSTurret ();}});
			ms.put(T_Hero_ArcturusMengsk , new Call() {public AiCmd call() {return T_Hero_ArcturusMengsk ();}});
			ms.put(T_Hero_Hyperion , new Call() {public AiCmd call() {return T_Hero_Hyperion ();}});
			ms.put(T_Hero_NoradII , new Call() {public AiCmd call() {return T_Hero_NoradII ();}});
			ms.put(T_SiegeTankSiegeMode , new Call() {public AiCmd call() {return T_SiegeTankSiegeMode ();}});
			ms.put(T_SiegeTankSiegeTurret , new Call() {public AiCmd call() {return T_SiegeTankSiegeTurret ();}});
			ms.put(T_Firebat , new Call() {public AiCmd call() {return T_Firebat ();}});
			ms.put(Spell_ScannerSweep , new Call() {public AiCmd call() {return Spell_ScannerSweep ();}});
			ms.put(T_Medic , new Call() {public AiCmd call() {return T_Medic ();}});
			ms.put(Z_Larva , new Call() {public AiCmd call() {return Z_Larva ();}});
			ms.put(Z_Egg , new Call() {public AiCmd call() {return Z_Egg ();}});
			ms.put(Z_Zergling , new Call() {public AiCmd call() {return Z_Zergling ();}});
			ms.put(Z_Hydralisk , new Call() {public AiCmd call() {return Z_Hydralisk ();}});
			ms.put(Z_Ultralisk , new Call() {public AiCmd call() {return Z_Ultralisk ();}});
			ms.put(Z_Broodling , new Call() {public AiCmd call() {return Z_Broodling ();}});
			ms.put(Z_Drone , new Call() {public AiCmd call() {return Z_Drone ();}});
			ms.put(Z_Overlord , new Call() {public AiCmd call() {return Z_Overlord ();}});
			ms.put(Z_Mutalisk , new Call() {public AiCmd call() {return Z_Mutalisk ();}});
			ms.put(Z_Guardian , new Call() {public AiCmd call() {return Z_Guardian ();}});
			ms.put(Z_Queen , new Call() {public AiCmd call() {return Z_Queen ();}});
			ms.put(Z_Defiler , new Call() {public AiCmd call() {return Z_Defiler ();}});
			ms.put(Z_Scourge , new Call() {public AiCmd call() {return Z_Scourge ();}});
			ms.put(Z_Hero_Torrasque , new Call() {public AiCmd call() {return Z_Hero_Torrasque ();}});
			ms.put(Z_Hero_Matriarch , new Call() {public AiCmd call() {return Z_Hero_Matriarch ();}});
			ms.put(Z_InfestedTerran , new Call() {public AiCmd call() {return Z_InfestedTerran ();}});
			ms.put(Z_Hero_InfestedKerrigan , new Call() {public AiCmd call() {return Z_Hero_InfestedKerrigan ();}});
			ms.put(Z_Hero_UncleanOne , new Call() {public AiCmd call() {return Z_Hero_UncleanOne ();}});
			ms.put(Z_Hero_HunterKiller , new Call() {public AiCmd call() {return Z_Hero_HunterKiller ();}});
			ms.put(Z_Hero_DevouringOne , new Call() {public AiCmd call() {return Z_Hero_DevouringOne ();}});
			ms.put(Z_Hero_KukulzaMutalisk , new Call() {public AiCmd call() {return Z_Hero_KukulzaMutalisk ();}});
			ms.put(Z_Hero_KukulzaGuardian , new Call() {public AiCmd call() {return Z_Hero_KukulzaGuardian ();}});
			ms.put(Z_Hero_Yggdrasill , new Call() {public AiCmd call() {return Z_Hero_Yggdrasill ();}});
			ms.put(T_Valkyrie , new Call() {public AiCmd call() {return T_Valkyrie ();}});
			ms.put(Z_Cocoon , new Call() {public AiCmd call() {return Z_Cocoon ();}});
			ms.put(P_Corsair , new Call() {public AiCmd call() {return P_Corsair ();}});
			ms.put(P_DarkTemplar , new Call() {public AiCmd call() {return P_DarkTemplar ();}});
			ms.put(Z_Devourer , new Call() {public AiCmd call() {return Z_Devourer ();}});
			ms.put(P_DarkArchon , new Call() {public AiCmd call() {return P_DarkArchon ();}});
			ms.put(P_Probe , new Call() {public AiCmd call() {return P_Probe ();}});
			ms.put(P_Zealot , new Call() {public AiCmd call() {return P_Zealot ();}});
			ms.put(P_Dragoon , new Call() {public AiCmd call() {return P_Dragoon ();}});
			ms.put(P_HighTemplar , new Call() {public AiCmd call() {return P_HighTemplar ();}});
			ms.put(P_Archon , new Call() {public AiCmd call() {return P_Archon ();}});
			ms.put(P_Shuttle , new Call() {public AiCmd call() {return P_Shuttle ();}});
			ms.put(P_Scout , new Call() {public AiCmd call() {return P_Scout ();}});
			ms.put(P_Arbiter , new Call() {public AiCmd call() {return P_Arbiter ();}});
			ms.put(P_Carrier , new Call() {public AiCmd call() {return P_Carrier ();}});
			ms.put(P_Interceptor , new Call() {public AiCmd call() {return P_Interceptor ();}});
			ms.put(P_Hero_DarkTemplar , new Call() {public AiCmd call() {return P_Hero_DarkTemplar ();}});
			ms.put(P_Hero_Zeratul , new Call() {public AiCmd call() {return P_Hero_Zeratul ();}});
			ms.put(P_Hero_TassadarZeratul , new Call() {public AiCmd call() {return P_Hero_TassadarZeratul ();}});
			ms.put(P_Hero_FenixZealot , new Call() {public AiCmd call() {return P_Hero_FenixZealot ();}});
			ms.put(P_Hero_FenixDragoon , new Call() {public AiCmd call() {return P_Hero_FenixDragoon ();}});
			ms.put(P_Hero_Tassadar , new Call() {public AiCmd call() {return P_Hero_Tassadar ();}});
			ms.put(P_Hero_Mojo , new Call() {public AiCmd call() {return P_Hero_Mojo ();}});
			ms.put(P_Hero_Warbringer , new Call() {public AiCmd call() {return P_Hero_Warbringer ();}});
			ms.put(P_Hero_Gantrithor , new Call() {public AiCmd call() {return P_Hero_Gantrithor ();}});
			ms.put(P_Reaver , new Call() {public AiCmd call() {return P_Reaver ();}});
			ms.put(P_Observer , new Call() {public AiCmd call() {return P_Observer ();}});
			ms.put(P_Scarab , new Call() {public AiCmd call() {return P_Scarab ();}});
			ms.put(P_Hero_Danimoth , new Call() {public AiCmd call() {return P_Hero_Danimoth ();}});
			ms.put(P_Hero_Aldaris , new Call() {public AiCmd call() {return P_Hero_Aldaris ();}});
			ms.put(P_Hero_Artanis , new Call() {public AiCmd call() {return P_Hero_Artanis ();}});
			ms.put(Critter_Rhynadon , new Call() {public AiCmd call() {return Critter_Rhynadon ();}});
			ms.put(Critter_Bengalaas , new Call() {public AiCmd call() {return Critter_Bengalaas ();}});
			ms.put(Unused_CargoShip , new Call() {public AiCmd call() {return Unused_CargoShip ();}});
			ms.put(Unused_MercenaryGunship , new Call() {public AiCmd call() {return Unused_MercenaryGunship ();}});
			ms.put(Critter_Scantid , new Call() {public AiCmd call() {return Critter_Scantid ();}});
			ms.put(Critter_Kakaru , new Call() {public AiCmd call() {return Critter_Kakaru ();}});
			ms.put(Critter_Ragnasaur , new Call() {public AiCmd call() {return Critter_Ragnasaur ();}});
			ms.put(Critter_Ursadon , new Call() {public AiCmd call() {return Critter_Ursadon ();}});
			ms.put(Z_LurkerEgg , new Call() {public AiCmd call() {return Z_LurkerEgg ();}});
			ms.put(P_Hero_Raszagal , new Call() {public AiCmd call() {return P_Hero_Raszagal ();}});
			ms.put(T_Hero_SamirDuran , new Call() {public AiCmd call() {return T_Hero_SamirDuran ();}});
			ms.put(T_Hero_AlexeiStukov , new Call() {public AiCmd call() {return T_Hero_AlexeiStukov ();}});
			ms.put(Map_Revealer , new Call() {public AiCmd call() {return Map_Revealer ();}});
			ms.put(T_Hero_GerardDuGalle , new Call() {public AiCmd call() {return T_Hero_GerardDuGalle ();}});
			ms.put(Z_Lurker , new Call() {public AiCmd call() {return Z_Lurker ();}});
			ms.put(Z_Hero_InfestedDuran , new Call() {public AiCmd call() {return Z_Hero_InfestedDuran ();}});
			ms.put(Spell_DisruptionWeb , new Call() {public AiCmd call() {return Spell_DisruptionWeb ();}});
			ms.put(T_CommandCenter , new Call() {public AiCmd call() {return T_CommandCenter ();}});
			ms.put(T_ComsatStation , new Call() {public AiCmd call() {return T_ComsatStation ();}});
			ms.put(T_NuclearSilo , new Call() {public AiCmd call() {return T_NuclearSilo ();}});
			ms.put(T_SupplyDepot , new Call() {public AiCmd call() {return T_SupplyDepot ();}});
			ms.put(T_Refinery , new Call() {public AiCmd call() {return T_Refinery ();}});
			ms.put(T_Barracks , new Call() {public AiCmd call() {return T_Barracks ();}});
			ms.put(T_Academy , new Call() {public AiCmd call() {return T_Academy ();}});
			ms.put(T_Factory , new Call() {public AiCmd call() {return T_Factory ();}});
			ms.put(T_Starport , new Call() {public AiCmd call() {return T_Starport ();}});
			ms.put(T_ControlTower , new Call() {public AiCmd call() {return T_ControlTower ();}});
			ms.put(T_ScienceFacility , new Call() {public AiCmd call() {return T_ScienceFacility ();}});
			ms.put(T_CovertOps , new Call() {public AiCmd call() {return T_CovertOps ();}});
			ms.put(T_PhysicsLab , new Call() {public AiCmd call() {return T_PhysicsLab ();}});
			ms.put(Unused_Starbase , new Call() {public AiCmd call() {return Unused_Starbase ();}});
			ms.put(T_MachineShop , new Call() {public AiCmd call() {return T_MachineShop ();}});
			ms.put(Unused_RepairBay , new Call() {public AiCmd call() {return Unused_RepairBay ();}});
			ms.put(T_EngineeringBay , new Call() {public AiCmd call() {return T_EngineeringBay ();}});
			ms.put(T_Armory , new Call() {public AiCmd call() {return T_Armory ();}});
			ms.put(T_MissileTurret , new Call() {public AiCmd call() {return T_MissileTurret ();}});
			ms.put(T_Bunker , new Call() {public AiCmd call() {return T_Bunker ();}});
			ms.put(Special_CrashedNoradII , new Call() {public AiCmd call() {return Special_CrashedNoradII ();}});
			ms.put(Special_IonCannon , new Call() {public AiCmd call() {return Special_IonCannon ();}});
			ms.put(Powerup_UrajCrystal , new Call() {public AiCmd call() {return Powerup_UrajCrystal ();}});
			ms.put(Powerup_KhalisCrystal , new Call() {public AiCmd call() {return Powerup_KhalisCrystal ();}});
			ms.put(Z_InfestedCommandCenter , new Call() {public AiCmd call() {return Z_InfestedCommandCenter ();}});
			ms.put(Z_Hatchery , new Call() {public AiCmd call() {return Z_Hatchery ();}});
			ms.put(Z_Lair , new Call() {public AiCmd call() {return Z_Lair ();}});
			ms.put(Z_Hive , new Call() {public AiCmd call() {return Z_Hive ();}});
			ms.put(Z_NydusCanal , new Call() {public AiCmd call() {return Z_NydusCanal ();}});
			ms.put(Z_HydraliskDen , new Call() {public AiCmd call() {return Z_HydraliskDen ();}});
			ms.put(Z_DefilerMound , new Call() {public AiCmd call() {return Z_DefilerMound ();}});
			ms.put(Z_GreaterSpire , new Call() {public AiCmd call() {return Z_GreaterSpire ();}});
			ms.put(Z_QueensNest , new Call() {public AiCmd call() {return Z_QueensNest ();}});
			ms.put(Z_EvolutionChamber , new Call() {public AiCmd call() {return Z_EvolutionChamber ();}});
			ms.put(Z_UltraliskCavern , new Call() {public AiCmd call() {return Z_UltraliskCavern ();}});
			ms.put(Z_Spire , new Call() {public AiCmd call() {return Z_Spire ();}});
			ms.put(Z_SpawningPool , new Call() {public AiCmd call() {return Z_SpawningPool ();}});
			ms.put(Z_CreepColony , new Call() {public AiCmd call() {return Z_CreepColony ();}});
			ms.put(Z_SporeColony , new Call() {public AiCmd call() {return Z_SporeColony ();}});
			ms.put(Unused_ZergBuilding1 , new Call() {public AiCmd call() {return Unused_ZergBuilding1 ();}});
			ms.put(Z_SunkenColony , new Call() {public AiCmd call() {return Z_SunkenColony ();}});
			ms.put(Special_OvermindWithShell , new Call() {public AiCmd call() {return Special_OvermindWithShell ();}});
			ms.put(Special_Overmind , new Call() {public AiCmd call() {return Special_Overmind ();}});
			ms.put(Z_Extractor , new Call() {public AiCmd call() {return Z_Extractor ();}});
			ms.put(Special_MatureChrysalis , new Call() {public AiCmd call() {return Special_MatureChrysalis ();}});
			ms.put(Special_Cerebrate , new Call() {public AiCmd call() {return Special_Cerebrate ();}});
			ms.put(Special_CerebrateDaggoth , new Call() {public AiCmd call() {return Special_CerebrateDaggoth ();}});
			ms.put(Unused_ZergBuilding2 , new Call() {public AiCmd call() {return Unused_ZergBuilding2 ();}});
			ms.put(P_Nexus , new Call() {public AiCmd call() {return P_Nexus ();}});
			ms.put(P_RoboticsFacility , new Call() {public AiCmd call() {return P_RoboticsFacility ();}});
			ms.put(P_Pylon , new Call() {public AiCmd call() {return P_Pylon ();}});
			ms.put(P_Assimilator , new Call() {public AiCmd call() {return P_Assimilator ();}});
			ms.put(Unused_ProtossBuilding1 , new Call() {public AiCmd call() {return Unused_ProtossBuilding1 ();}});
			ms.put(P_Observatory , new Call() {public AiCmd call() {return P_Observatory ();}});
			ms.put(P_Gateway , new Call() {public AiCmd call() {return P_Gateway ();}});
			ms.put(Unused_ProtossBuilding2 , new Call() {public AiCmd call() {return Unused_ProtossBuilding2 ();}});
			ms.put(P_PhotonCannon , new Call() {public AiCmd call() {return P_PhotonCannon ();}});
			ms.put(P_CitadelOfAdun , new Call() {public AiCmd call() {return P_CitadelOfAdun ();}});
			ms.put(P_CyberneticsCore , new Call() {public AiCmd call() {return P_CyberneticsCore ();}});
			ms.put(P_TemplarArchives , new Call() {public AiCmd call() {return P_TemplarArchives ();}});
			ms.put(P_Forge , new Call() {public AiCmd call() {return P_Forge ();}});
			ms.put(P_Stargate , new Call() {public AiCmd call() {return P_Stargate ();}});
			ms.put(Special_StasisCellPrison , new Call() {public AiCmd call() {return Special_StasisCellPrison ();}});
			ms.put(P_FleetBeacon , new Call() {public AiCmd call() {return P_FleetBeacon ();}});
			ms.put(P_ArbiterTribunal , new Call() {public AiCmd call() {return P_ArbiterTribunal ();}});
			ms.put(P_RoboticsSupportBay , new Call() {public AiCmd call() {return P_RoboticsSupportBay ();}});
			ms.put(P_ShieldBattery , new Call() {public AiCmd call() {return P_ShieldBattery ();}});
			ms.put(Special_KhaydarinCrystalForm , new Call() {public AiCmd call() {return Special_KhaydarinCrystalForm ();}});
			ms.put(Special_ProtossTemple , new Call() {public AiCmd call() {return Special_ProtossTemple ();}});
			ms.put(Special_XelNagaTemple , new Call() {public AiCmd call() {return Special_XelNagaTemple ();}});
			ms.put(Resource_MineralPatch1 , new Call() {public AiCmd call() {return Resource_MineralPatch1 ();}});
			ms.put(Resource_MineralPatch2 , new Call() {public AiCmd call() {return Resource_MineralPatch2 ();}});
			ms.put(Resource_MineralPatch3 , new Call() {public AiCmd call() {return Resource_MineralPatch3 ();}});
			ms.put(Unused_Cave , new Call() {public AiCmd call() {return Unused_Cave ();}});
			ms.put(Unused_CaveIn , new Call() {public AiCmd call() {return Unused_CaveIn ();}});
			ms.put(Unused_Cantina , new Call() {public AiCmd call() {return Unused_Cantina ();}});
			ms.put(Unused_MiningPlatform , new Call() {public AiCmd call() {return Unused_MiningPlatform ();}});
			ms.put(Unused_IndependantCC , new Call() {public AiCmd call() {return Unused_IndependantCC ();}});
			ms.put(Unused_IndependantStarport , new Call() {public AiCmd call() {return Unused_IndependantStarport ();}});
			ms.put(Unused_IndependantJumpGate , new Call() {public AiCmd call() {return Unused_IndependantJumpGate ();}});
			ms.put(Unused_Ruins , new Call() {public AiCmd call() {return Unused_Ruins ();}});
			ms.put(Unused_KhaydarinFormation , new Call() {public AiCmd call() {return Unused_KhaydarinFormation ();}});
			ms.put(Resource_VespeneGeyser , new Call() {public AiCmd call() {return Resource_VespeneGeyser ();}});
			ms.put(Special_WarpGate , new Call() {public AiCmd call() {return Special_WarpGate ();}});
			ms.put(Special_PsiDisrupter , new Call() {public AiCmd call() {return Special_PsiDisrupter ();}});
			ms.put(Unused_ZergMarker , new Call() {public AiCmd call() {return Unused_ZergMarker ();}});
			ms.put(Unused_TerranMarker , new Call() {public AiCmd call() {return Unused_TerranMarker ();}});
			ms.put(Unused_ProtossMarker , new Call() {public AiCmd call() {return Unused_ProtossMarker ();}});
			ms.put(Beacon_Zerg , new Call() {public AiCmd call() {return Beacon_Zerg ();}});
			ms.put(Beacon_Terran , new Call() {public AiCmd call() {return Beacon_Terran ();}});
			ms.put(Beacon_Protoss , new Call() {public AiCmd call() {return Beacon_Protoss ();}});
			ms.put(Beacon_ZergFlag , new Call() {public AiCmd call() {return Beacon_ZergFlag ();}});
			ms.put(Beacon_TerranFlag , new Call() {public AiCmd call() {return Beacon_TerranFlag ();}});
			ms.put(Beacon_ProtossFlag , new Call() {public AiCmd call() {return Beacon_ProtossFlag ();}});
			ms.put(Special_PowerGenerator , new Call() {public AiCmd call() {return Special_PowerGenerator ();}});
			ms.put(Special_OvermindCocoon , new Call() {public AiCmd call() {return Special_OvermindCocoon ();}});
			ms.put(Spell_DarkSwarm , new Call() {public AiCmd call() {return Spell_DarkSwarm ();}});
			ms.put(Doodad_FloorMissileTrap , new Call() {public AiCmd call() {return Doodad_FloorMissileTrap ();}});
			ms.put(Doodad_FloorHatch , new Call() {public AiCmd call() {return Doodad_FloorHatch ();}});
			ms.put(Doodad_LeftUpperLevelDoor , new Call() {public AiCmd call() {return Doodad_LeftUpperLevelDoor ();}});
			ms.put(Doodad_RightUpperLevelDoor , new Call() {public AiCmd call() {return Doodad_RightUpperLevelDoor ();}});
			ms.put(Doodad_LeftPitDoor , new Call() {public AiCmd call() {return Doodad_LeftPitDoor ();}});
			ms.put(Doodad_RightPitDoor , new Call() {public AiCmd call() {return Doodad_RightPitDoor ();}});
			ms.put(Doodad_FloorGunTrap , new Call() {public AiCmd call() {return Doodad_FloorGunTrap ();}});
			ms.put(Doodad_LeftWallMissileTrap , new Call() {public AiCmd call() {return Doodad_LeftWallMissileTrap ();}});
			ms.put(Doodad_LeftWallFlameTrap , new Call() {public AiCmd call() {return Doodad_LeftWallFlameTrap ();}});
			ms.put(Doodad_RightWallMissileTrap , new Call() {public AiCmd call() {return Doodad_RightWallMissileTrap ();}});
			ms.put(Doodad_RightWallFlameTrap , new Call() {public AiCmd call() {return Doodad_RightWallFlameTrap ();}});
			ms.put(Start_Location , new Call() {public AiCmd call() {return Start_Location ();}});
			ms.put(Powerup_Flag , new Call() {public AiCmd call() {return Powerup_Flag ();}});
			ms.put(Powerup_YoungChrysalis , new Call() {public AiCmd call() {return Powerup_YoungChrysalis ();}});
			ms.put(Powerup_PsiEmitter , new Call() {public AiCmd call() {return Powerup_PsiEmitter ();}});
			ms.put(Powerup_DataDisk , new Call() {public AiCmd call() {return Powerup_DataDisk ();}});
			ms.put(Powerup_KhaydarinCrystal , new Call() {public AiCmd call() {return Powerup_KhaydarinCrystal ();}});
			ms.put(Mineral_Chunk1 , new Call() {public AiCmd call() {return Mineral_Chunk1 ();}});
			ms.put(Mineral_Chunk2 , new Call() {public AiCmd call() {return Mineral_Chunk2 ();}});
			ms.put(Vespene_Orb1 , new Call() {public AiCmd call() {return Vespene_Orb1 ();}});
			ms.put(Vespene_Orb2 , new Call() {public AiCmd call() {return Vespene_Orb2 ();}});
			ms.put(Vaspene_Sac1 , new Call() {public AiCmd call() {return Vaspene_Sac1 ();}});
			ms.put(Vaspene_Sac2 , new Call() {public AiCmd call() {return Vaspene_Sac2 ();}});
			ms.put(Vespene_Tank1 , new Call() {public AiCmd call() {return Vespene_Tank1 ();}});
			ms.put(Vespene_Tank2 , new Call() {public AiCmd call() {return Vespene_Tank2 ();}});
			ms.put(None , new Call() {public AiCmd call() {return None ();}});
			// @formatter:on
		}
		AiCmd T_Marine () {return null;}
		AiCmd T_Ghost () {return null;}
		AiCmd T_Vulture () {return null;}
		AiCmd T_Goliath () {return null;}
		AiCmd T_GoliathTurret () {return null;}
		AiCmd T_SiegeTankTankMode () {return null;}
		AiCmd T_TankTurretTankMode () {return null;}
		AiCmd T_SCV () {return null;}
		AiCmd T_Wraith () {return null;}
		AiCmd T_ScienceVessel () {return null;}
		AiCmd T_Hero_GuiMontag () {return null;}
		AiCmd T_Dropship () {return null;}
		AiCmd T_Battlecruiser () {return null;}
		AiCmd T_VultureSpiderMine () {return null;}
		AiCmd T_NuclearMissile () {return null;}
		AiCmd T_Hero_Civilian () {return null;}
		AiCmd T_Hero_SarahKerrigan () {return null;}
		AiCmd T_Hero_AlanSchezar () {return null;}
		AiCmd T_Hero_AlanTurret () {return null;}
		AiCmd T_Hero_JimRaynorV () {return null;}
		AiCmd T_Hero_JimRaynorM () {return null;}
		AiCmd T_Hero_TomKazansky () {return null;}
		AiCmd T_Hero_Magellan () {return null;}
		AiCmd T_Hero_EdmundDukeT () {return null;}
		AiCmd T_Hero_EdmundDukeTTurret () {return null;}
		AiCmd T_Hero_EdmundDukeS () {return null;}
		AiCmd T_Hero_EdmundDukeSTurret () {return null;}
		AiCmd T_Hero_ArcturusMengsk () {return null;}
		AiCmd T_Hero_Hyperion () {return null;}
		AiCmd T_Hero_NoradII () {return null;}
		AiCmd T_SiegeTankSiegeMode () {return null;}
		AiCmd T_SiegeTankSiegeTurret () {return null;}
		AiCmd T_Firebat () {return null;}
		AiCmd Spell_ScannerSweep () {return null;}
		AiCmd T_Medic () {return null;}
		AiCmd Z_Larva () {return null;}
		AiCmd Z_Egg () {return null;}
		AiCmd Z_Zergling () {return null;}
		AiCmd Z_Hydralisk () {return null;}
		AiCmd Z_Ultralisk () {return null;}
		AiCmd Z_Broodling () {return null;}
		AiCmd Z_Drone () {return null;}
		AiCmd Z_Overlord () {return null;}
		AiCmd Z_Mutalisk () {return null;}
		AiCmd Z_Guardian () {return null;}
		AiCmd Z_Queen () {return null;}
		AiCmd Z_Defiler () {return null;}
		AiCmd Z_Scourge () {return null;}
		AiCmd Z_Hero_Torrasque () {return null;}
		AiCmd Z_Hero_Matriarch () {return null;}
		AiCmd Z_InfestedTerran () {return null;}
		AiCmd Z_Hero_InfestedKerrigan () {return null;}
		AiCmd Z_Hero_UncleanOne () {return null;}
		AiCmd Z_Hero_HunterKiller () {return null;}
		AiCmd Z_Hero_DevouringOne () {return null;}
		AiCmd Z_Hero_KukulzaMutalisk () {return null;}
		AiCmd Z_Hero_KukulzaGuardian () {return null;}
		AiCmd Z_Hero_Yggdrasill () {return null;}
		AiCmd T_Valkyrie () {return null;}
		AiCmd Z_Cocoon () {return null;}
		AiCmd P_Corsair () {return null;}
		AiCmd P_DarkTemplar () {return null;}
		AiCmd Z_Devourer () {return null;}
		AiCmd P_DarkArchon () {return null;}
		AiCmd P_Probe () {return null;}
		AiCmd P_Zealot () {return null;}
		AiCmd P_Dragoon () {return null;}
		AiCmd P_HighTemplar () {return null;}
		AiCmd P_Archon () {return null;}
		AiCmd P_Shuttle () {return null;}
		AiCmd P_Scout () {return null;}
		AiCmd P_Arbiter () {return null;}
		AiCmd P_Carrier () {return null;}
		AiCmd P_Interceptor () {return null;}
		AiCmd P_Hero_DarkTemplar () {return null;}
		AiCmd P_Hero_Zeratul () {return null;}
		AiCmd P_Hero_TassadarZeratul () {return null;}
		AiCmd P_Hero_FenixZealot () {return null;}
		AiCmd P_Hero_FenixDragoon () {return null;}
		AiCmd P_Hero_Tassadar () {return null;}
		AiCmd P_Hero_Mojo () {return null;}
		AiCmd P_Hero_Warbringer () {return null;}
		AiCmd P_Hero_Gantrithor () {return null;}
		AiCmd P_Reaver () {return null;}
		AiCmd P_Observer () {return null;}
		AiCmd P_Scarab () {return null;}
		AiCmd P_Hero_Danimoth () {return null;}
		AiCmd P_Hero_Aldaris () {return null;}
		AiCmd P_Hero_Artanis () {return null;}
		AiCmd Critter_Rhynadon () {return null;}
		AiCmd Critter_Bengalaas () {return null;}
		AiCmd Unused_CargoShip () {return null;}
		AiCmd Unused_MercenaryGunship () {return null;}
		AiCmd Critter_Scantid () {return null;}
		AiCmd Critter_Kakaru () {return null;}
		AiCmd Critter_Ragnasaur () {return null;}
		AiCmd Critter_Ursadon () {return null;}
		AiCmd Z_LurkerEgg () {return null;}
		AiCmd P_Hero_Raszagal () {return null;}
		AiCmd T_Hero_SamirDuran () {return null;}
		AiCmd T_Hero_AlexeiStukov () {return null;}
		AiCmd Map_Revealer () {return null;}
		AiCmd T_Hero_GerardDuGalle () {return null;}
		AiCmd Z_Lurker () {return null;}
		AiCmd Z_Hero_InfestedDuran () {return null;}
		AiCmd Spell_DisruptionWeb () {return null;}
		AiCmd T_CommandCenter () {return null;}
		AiCmd T_ComsatStation () {return null;}
		AiCmd T_NuclearSilo () {return null;}
		AiCmd T_SupplyDepot () {return null;}
		AiCmd T_Refinery () {return null;}
		AiCmd T_Barracks () {return null;}
		AiCmd T_Academy () {return null;}
		AiCmd T_Factory () {return null;}
		AiCmd T_Starport () {return null;}
		AiCmd T_ControlTower () {return null;}
		AiCmd T_ScienceFacility () {return null;}
		AiCmd T_CovertOps () {return null;}
		AiCmd T_PhysicsLab () {return null;}
		AiCmd Unused_Starbase () {return null;}
		AiCmd T_MachineShop () {return null;}
		AiCmd Unused_RepairBay () {return null;}
		AiCmd T_EngineeringBay () {return null;}
		AiCmd T_Armory () {return null;}
		AiCmd T_MissileTurret () {return null;}
		AiCmd T_Bunker () {return null;}
		AiCmd Special_CrashedNoradII () {return null;}
		AiCmd Special_IonCannon () {return null;}
		AiCmd Powerup_UrajCrystal () {return null;}
		AiCmd Powerup_KhalisCrystal () {return null;}
		AiCmd Z_InfestedCommandCenter () {return null;}
		AiCmd Z_Hatchery () {return null;}
		AiCmd Z_Lair () {return null;}
		AiCmd Z_Hive () {return null;}
		AiCmd Z_NydusCanal () {return null;}
		AiCmd Z_HydraliskDen () {return null;}
		AiCmd Z_DefilerMound () {return null;}
		AiCmd Z_GreaterSpire () {return null;}
		AiCmd Z_QueensNest () {return null;}
		AiCmd Z_EvolutionChamber () {return null;}
		AiCmd Z_UltraliskCavern () {return null;}
		AiCmd Z_Spire () {return null;}
		AiCmd Z_SpawningPool () {return null;}
		AiCmd Z_CreepColony () {return null;}
		AiCmd Z_SporeColony () {return null;}
		AiCmd Unused_ZergBuilding1 () {return null;}
		AiCmd Z_SunkenColony () {return null;}
		AiCmd Special_OvermindWithShell () {return null;}
		AiCmd Special_Overmind () {return null;}
		AiCmd Z_Extractor () {return null;}
		AiCmd Special_MatureChrysalis () {return null;}
		AiCmd Special_Cerebrate () {return null;}
		AiCmd Special_CerebrateDaggoth () {return null;}
		AiCmd Unused_ZergBuilding2 () {return null;}
		AiCmd P_Nexus () {return null;}
		AiCmd P_RoboticsFacility () {return null;}
		AiCmd P_Pylon () {return null;}
		AiCmd P_Assimilator () {return null;}
		AiCmd Unused_ProtossBuilding1 () {return null;}
		AiCmd P_Observatory () {return null;}
		AiCmd P_Gateway () {return null;}
		AiCmd Unused_ProtossBuilding2 () {return null;}
		AiCmd P_PhotonCannon () {return null;}
		AiCmd P_CitadelOfAdun () {return null;}
		AiCmd P_CyberneticsCore () {return null;}
		AiCmd P_TemplarArchives () {return null;}
		AiCmd P_Forge () {return null;}
		AiCmd P_Stargate () {return null;}
		AiCmd Special_StasisCellPrison () {return null;}
		AiCmd P_FleetBeacon () {return null;}
		AiCmd P_ArbiterTribunal () {return null;}
		AiCmd P_RoboticsSupportBay () {return null;}
		AiCmd P_ShieldBattery () {return null;}
		AiCmd Special_KhaydarinCrystalForm () {return null;}
		AiCmd Special_ProtossTemple () {return null;}
		AiCmd Special_XelNagaTemple () {return null;}
		AiCmd Resource_MineralPatch1 () {return null;}
		AiCmd Resource_MineralPatch2 () {return null;}
		AiCmd Resource_MineralPatch3 () {return null;}
		AiCmd Unused_Cave () {return null;}
		AiCmd Unused_CaveIn () {return null;}
		AiCmd Unused_Cantina () {return null;}
		AiCmd Unused_MiningPlatform () {return null;}
		AiCmd Unused_IndependantCC () {return null;}
		AiCmd Unused_IndependantStarport () {return null;}
		AiCmd Unused_IndependantJumpGate () {return null;}
		AiCmd Unused_Ruins () {return null;}
		AiCmd Unused_KhaydarinFormation () {return null;}
		AiCmd Resource_VespeneGeyser () {return null;}
		AiCmd Special_WarpGate () {return null;}
		AiCmd Special_PsiDisrupter () {return null;}
		AiCmd Unused_ZergMarker () {return null;}
		AiCmd Unused_TerranMarker () {return null;}
		AiCmd Unused_ProtossMarker () {return null;}
		AiCmd Beacon_Zerg () {return null;}
		AiCmd Beacon_Terran () {return null;}
		AiCmd Beacon_Protoss () {return null;}
		AiCmd Beacon_ZergFlag () {return null;}
		AiCmd Beacon_TerranFlag () {return null;}
		AiCmd Beacon_ProtossFlag () {return null;}
		AiCmd Special_PowerGenerator () {return null;}
		AiCmd Special_OvermindCocoon () {return null;}
		AiCmd Spell_DarkSwarm () {return null;}
		AiCmd Doodad_FloorMissileTrap () {return null;}
		AiCmd Doodad_FloorHatch () {return null;}
		AiCmd Doodad_LeftUpperLevelDoor () {return null;}
		AiCmd Doodad_RightUpperLevelDoor () {return null;}
		AiCmd Doodad_LeftPitDoor () {return null;}
		AiCmd Doodad_RightPitDoor () {return null;}
		AiCmd Doodad_FloorGunTrap () {return null;}
		AiCmd Doodad_LeftWallMissileTrap () {return null;}
		AiCmd Doodad_LeftWallFlameTrap () {return null;}
		AiCmd Doodad_RightWallMissileTrap () {return null;}
		AiCmd Doodad_RightWallFlameTrap () {return null;}
		AiCmd Start_Location () {return null;}
		AiCmd Powerup_Flag () {return null;}
		AiCmd Powerup_YoungChrysalis () {return null;}
		AiCmd Powerup_PsiEmitter () {return null;}
		AiCmd Powerup_DataDisk () {return null;}
		AiCmd Powerup_KhaydarinCrystal () {return null;}
		AiCmd Mineral_Chunk1 () {return null;}
		AiCmd Mineral_Chunk2 () {return null;}
		AiCmd Vespene_Orb1 () {return null;}
		AiCmd Vespene_Orb2 () {return null;}
		AiCmd Vaspene_Sac1 () {return null;}
		AiCmd Vaspene_Sac2 () {return null;}
		AiCmd Vespene_Tank1 () {return null;}
		AiCmd Vespene_Tank2 () {return null;}
		AiCmd None () {return null;}

		public Call get(int type) {
			Call c = ms.get(type);
			if (c == null)
				c = NoCall;
			return c;
		}
	}

	GetCommand gcp = new GetCommand();

	private AiCmd getCommand(Unit u) {
		int type = u.unitID().id;
		AiCmd cmd = gcp.get(type).call();
		if (cmd == null)
			cmd = NoCmd;
		return cmd;
	}

	@Override
	public void onNuclearLaunchDetected(Position orderTargetPos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onText(String text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUnitDeath(Unit unit) {
		cmds.remove(unit);

	}

	Map<Unit, AiCmd> cmds = new HashMap<Unit, AiCmd>();
	List<AiSpot> wars = new ArrayList<AiSpot>();
	List<AiSpot> enemySpots = new ArrayList<AiSpot>();
	List<AiSpot> exploreSpots = new ArrayList<AiSpot>();

	@Override
	public void start() {
		BW.print1(8, "AIName:" + this.getClass().getSimpleName());
	}

}
