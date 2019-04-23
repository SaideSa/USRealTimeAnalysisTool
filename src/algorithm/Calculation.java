package algorithm;

import java.awt.event.*;
import java.util.*;

public class Calculation {
	
	static Vector dinge = new Vector();
	
	public static int berechnen() {
		Box tempbox1 = (Box) dinge.firstElement();
		Box tempbox2 = (Box) dinge.lastElement();
		Box tempbox3 = new Box(tempbox1.x, tempbox2.y);
		int ankathete1 = tempbox3.y - tempbox1.y; 
		int ankathete2 = tempbox3.x - tempbox2.x;
		int entfernung = (int) Math.hypot(ankathete1,ankathete2);
		return entfernung;
	}
	
	
	public static int berechnen2(int x1, int y1, int x2, int y2){
	return (int) Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2-y1), 2));
	}

	public static void MouseListenerPressed(MouseEvent evt) {
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
	
	public static void MouseListenerReleased(MouseEvent evt) {
		
		if(evt.getButton()==1 && dinge.size()==2) {
			if(dinge.size()==2) {
				int ergebnis = Calculation.berechnen();
				System.out.println("Die Entfernung beträgt " + ergebnis);
			}
		}
	}
	
}
