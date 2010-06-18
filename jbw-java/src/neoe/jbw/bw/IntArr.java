package neoe.jbw.bw;

import neoe.jbw.BW;
import neoe.jbw.Bytes;
import neoe.jbw.data.UnitID;

public class IntArr {

	private int start;
	//private int[] arr;
	private int maxsize;
	private int base;

	public IntArr(int base, int size, int start) {
		this.base=base;
		this.maxsize = size;
		this.start = start;
	}

	public int get(int index){
		int p=index+start;
		p = p%maxsize;
		return arr(p);
	}
	public int size() {
		int p = start;
		int s = 0;
		for (int i = 0; i < maxsize; i++) {
			if (arr(p) == UnitID.None)
				break;
			s++;
			p++;
			if (p >= maxsize)
				p = 0;
		}		
		return s;
	}

	private int arr(int p) {
		return BW.u16(base+p*2);
	}

}
