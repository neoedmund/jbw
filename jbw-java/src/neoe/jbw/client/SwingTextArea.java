package neoe.jbw.client;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import neoe.jbw.cmd.Command;
import neoe.jbw.cmd.Name;

public class SwingTextArea {
	StringBuffer sb = new StringBuffer();

	public void append(String s) {
		sb.append(s);
	}

	public void show() {
		JFrame f = new JFrame("jbw");
		JTextArea jta = new JTextArea();
		jta.append(sb.toString());
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e) {
			Command.add(new Command(Name.ResumeGame,null,0), null);
		}});
		f.add(new JScrollPane(jta));
		f.setSize(600, 300);
		f.setVisible(true);
	}

}
