package algorithm;

import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class FirstClass extends JFrame implements MouseListener{
	
	static Vector dinge = new Vector();
	

	
	
	
	
	public FirstClass() {
		
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(null);
		this.setTitle("USRealTimeAnalysisTool");
		this.setResizable(false);
		this.setSize(800,500);
		this.addMouseListener(this);
	    init();
		
	}
	
	public void init() {
			
		
	}
	
	
	
		
			
		
	
	
	@Override
	public void mousePressed(MouseEvent evt) {

		int button = evt.getButton();
		
		if(dinge.size()<2) {
			System.out.println(button);
		}
		
			if (button == 1) {
				if (dinge.size() < 2) {
					dinge.addElement(new Box(evt.getX(), evt.getY()));
				} else {
				}
			} else if (button == 3) {
				if (dinge.size() > 0) {
					dinge.remove(dinge.lastElement());
				} else {
				}
			}
		}
	

	@Override
	public void mouseClicked(MouseEvent evt) {}

	@Override
	public void mouseEntered(MouseEvent evt) {}

	@Override
	public void mouseExited(MouseEvent evt) {}

	@Override
	public void mouseReleased(MouseEvent evt) {
		if(evt.getButton()==1 && dinge.size()==2) {
			if(dinge.size()==2) {
				int ergebnis = Calculation.berechnen();
				System.out.println("Die Entfernung beträgt " + ergebnis);
			}
		}
	}
}
	
	


