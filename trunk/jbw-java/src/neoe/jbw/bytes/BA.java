package neoe.jbw.bytes;

import java.io.ByteArrayOutputStream;


public class BA {
	private ByteArrayOutputStream ba;

	public BA() {
		ba = new ByteArrayOutputStream();
	}

	public BA add(int b) {
		ba.write(b);
		return this;
	}
	public BA add(ToBytes u) {
		u.write(ba);
		return this;
	}

	public byte[] toBA() {
		return ba.toByteArray();
	}
}
