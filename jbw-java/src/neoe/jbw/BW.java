package neoe.jbw;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import neoe.jbw.data.Offset;

public class BW {
	private static native byte[] getBytes(int offset, int size);

	public static native ByteBuffer getBB();

	public static native String getStr(int offset);

	private static native void print(int id, String s);

	public static void print1(int id, String s) {
		Log.log("[" + id + "]" + s);
		print(id, s);
	}

	public static native void command(byte[] combytes, int byteslen);

	

	// struct DatLoad
	// {
	// u32 address;
	// u32 length;
	// u32 entries;
	// };

	public static synchronized int u32(int offset) {
		position(offset);
		byte bs0 = BW.BB.get();
		byte bs1 = BW.BB.get();
		byte bs2 = BW.BB.get();
		byte bs3 = BW.BB.get();
		return Bytes.u8(bs3) << 24 | Bytes.u8(bs2) << 16 | 
		Bytes.u8(bs1) << 8 | Bytes.u8(bs0);
		//return BB.getInt();
		// int v = Bytes.u32(getBytes1(offset, 4));
		// return v;
	}

	public static synchronized int u8(int offset) {
		position(offset);
		return Bytes.u8(BW.BB.get());
		// return Bytes.u8(getBytes1(offset, 1)[0]);
	}

	public static synchronized int u16(int offset) {
		position(offset);
		byte b1 = BW.BB.get();
		byte b2 = BW.BB.get();
		return Bytes.u8(b1) | Bytes.u8(b2) << 8;
		// return BB.getChar();
		// return Bytes.u16(getBytes1(offset, 2));
	}

	private static void position(int offset) {
		try {
			BW.BB.position(offset - Offset.BASE);
		} catch (Exception e) {
			Log.log("bad position " + Integer.toHexString(offset) + " E:" + e);
			print(-1,"bad position " + Integer.toHexString(offset));
		}
	}

	public static final ByteBuffer BB = getBB().order(ByteOrder.LITTLE_ENDIAN);

	public static void writeU32(int offset, int v) {
		position(offset);
		BB.putInt(v);		
	}

}
