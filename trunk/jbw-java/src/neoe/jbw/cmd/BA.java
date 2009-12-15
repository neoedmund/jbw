package neoe.jbw.cmd;

import java.io.ByteArrayOutputStream;

import neoe.jbw.bytes.ToBytes;

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
