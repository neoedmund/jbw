package neoe.jbw.client;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import neoe.jbw.Log;
import neoe.jbw.Main;
import neoe.jbw.Utils;
import neoe.jbw.bw.Position;
import neoe.jbw.bw.Unit;
import neoe.jbw.cmd.Command;
import neoe.jbw.cmd.Name;
import neoe.jbw.data.Order;

public class Gather {

	public Position pos;
	public int order=Order.None;
	public Set<Unit> done=new HashSet<Unit>();
	public void run() {
		//if(Main.frame%10!=1)return;
		if(order==Order.None)return;
		List<Unit>army=Utils.getMyArmy();
		//Log.log("my army "+army.size());
		army=Utils.filterOrder(army, Order.PlayerGuard);
		//Log.log("my idle army "+army.size());
		army.removeAll(done);
		if (army.size()>0){
			Utils.print("gather "+Order.getName(order)+" "+army.size());
			done.addAll(army);
			if(order==Order.Move){
				Command.add(new Command(Name.RightClick,pos.toPos(null),0), army);
			}else if(order==Order.AttackMove){
				Command.add(new Command(Name.Attack,pos.toPos(null),0,Order.AttackMove), army);
			}else order=Order.None;
		}
	}
	public void set(int order, Position position) {
		this.order=order;
		this.pos=position;
		done.clear();		
	}
	public void clear() {
		order=Order.None;
		done.clear();
	}
	

}
