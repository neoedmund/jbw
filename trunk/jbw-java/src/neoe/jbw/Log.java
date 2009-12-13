package neoe.jbw;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	static FileWriter out;
	static Date d = new Date();
	static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

	public static synchronized void log(Object s) {
		try {
			if (out == null) {
				out = new FileWriter("t:/jbwjava.log");
			}
			d.setTime(System.currentTimeMillis());
			out.write("[" + sdf.format(d) + " f" + Main.frame + "]" + s
					+ "\r\n");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
