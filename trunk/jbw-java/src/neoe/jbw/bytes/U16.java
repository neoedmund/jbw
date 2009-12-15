package neoe.jbw.bytes;

import java.io.ByteArrayOutputStream;

public class U16 implements ToBytes {
	private int v;

	public U16(int v) {
		this.v = v;
	}

	public void write(ByteArrayOutputStream ba) {
		ba.write(v&0xff);
		ba.write((v>>8)&0xff);
	}
}
