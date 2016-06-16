import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {

	public static void main(String[] args) {

		BrickBreaker applet = new BrickBreaker();
		applet.init();
		applet.start();
	
		JLabel lab = new JLabel();
		Container con = new Container();
		String t = new String();
		JPanel p1 = new JPanel(new BorderLayout());
		
		
		t = "HOW TO PLAY: Use the arrow keys to move your board up, down, left and right.";

		
		applet.setPreferredSize(applet.getSize());
		
		lab.setText(t);

		p1.setLayout(new BorderLayout());
		
		p1.add(applet, BorderLayout.LINE_START);
		
		p1.add(lab, BorderLayout.AFTER_LAST_LINE);
		
		JFrame window = new JFrame("Brick Breaker");
		window.setContentPane(p1);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		window.setPreferredSize(new Dimension(500,450));
		
		window.pack();
		window.setVisible(true);
	}

}