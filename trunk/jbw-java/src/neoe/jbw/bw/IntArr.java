package neoe.jbw.bw;

import neoe.jbw.Bytes;
import neoe.jbw.data.UnitID;

public class IntArr {

	private int start;
	private int[] arr;
	private int maxsize;

	public IntArr(byte[] bs, int start) {
		this.maxsize = bs.length / 2;
		this.arr = new int[maxsize];
		for (int i = 0; i < maxsize; i++) {
			arr[i] = (Bytes.u8(bs[1 + i * 2]) << 8 | Bytes.u8(bs[i * 2]));
		}
		this.start = start;
		//Log.log("IntArr:"+Arrays.toString(arr)+start);
	}
	public int get(int index){
		int p=index+start;
		p = p%maxsize;
		return arr[p];
	}
	public int size() {
		int p = start;
		int s = 0;
		for (int i = 0; i < maxsize; i++) {
			if (arr[p] == UnitID.None)
				break;
			s++;
			p++;
			if (p >= maxsize)
				p = 0;
		}		
		return s;
	}

}
