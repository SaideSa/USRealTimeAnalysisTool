package algorithm;

import java.util.Vector;

import javax.swing.*;

public class FirstClass extends JFrame{
	
	private Vector dinge = new Vector();
	
	
	public FirstClass() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(null);
		this.setTitle("USRealTimeAnalysisTool");
		this.setResizable(false);
		this.setSize(800,500);
	    init();
		
	}
	
	public void init() {
	}
	
	public void berechnen() {
		Box tempbox1 = (Box) dinge.firstElement();
		Box tempbox2 = (Box) dinge.lastElement();
		Box tempbox3 = new Box(tempbox1.x, tempbox2.y);
		double ankathete1 = tempbox3.y - tempbox1.y; 
		double ankathete2 = tempbox3.x - tempbox2.x;
		int entfernung = (int) Math.hypot(ankathete1,ankathete2);
	}

}
