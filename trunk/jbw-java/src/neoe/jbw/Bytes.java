package neoe.jbw;

public class Bytes {

	public static int u16(byte[] bs) {
		return u8(bs[1]) << 8 | u8(bs[0]);
	}

	public static void main(String[] args) {
		System.out.println(u16(new byte[] { 0x12, 0x34 }));
		System.out.println(0x1234);
		System.out.println(Integer.toHexString(u32(new byte[] { -24, -16, 97, 0 })));
		System.out.println(Integer.toHexString(0x12345678));
	}

	public static int u32(byte[] bs) {
		return u8(bs[3]) << 24 | u8(bs[2]) << 16 | 
		u8(bs[1]) << 8 | u8(bs[0]);
	}

	public static int u8(byte b) {
		if (b<0)return 0x100+b;else return b;
	}
}
