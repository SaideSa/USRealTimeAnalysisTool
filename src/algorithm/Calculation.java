package algorithm;


import java.awt.event.*;
import java.util.*;

import org.opencv.core.Point;



public class Calculation {

	// Erstellt einen Vector, indem die Boxen gespeichert werden

	static Vector<Box> dinge = new Vector<Box>();

	// Berechnet anhand der Boxen des Vectors die Entfernung der zwei gesetzten
	// Boxen

	public static int getDistanceBox() {
		Box tempbox1 = (Box) dinge.firstElement();
		Box tempbox2 = (Box) dinge.lastElement();
		Box tempbox3 = new Box(tempbox1.x, tempbox2.y);
		int ankathete1 = tempbox3.y - tempbox1.y;
		int ankathete2 = tempbox3.x - tempbox2.x;
		int entfernung = (int) Math.hypot(ankathete1, ankathete2);
		return entfernung;
	}

	// Berechnet die Entfernung zweier Punkte durch Übergabe der Werte

	public static int getDistanceXY(int x1, int y1, int x2, int y2) {
		return (int) Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
	}
	
	
	//Berechnet die Entfernung anhand zweier Points
	
	public static int getDistancePoint(Point a, Point b){
	    int distance = 0;
	    try{
	        if(a != null && b != null){
	            int xDiff = (int) (a.x - b.x);
	            int yDiff = (int) (a.y - b.y);
	            distance = (int) Math.sqrt(Math.pow(xDiff,2) + Math.pow(yDiff, 2));
	        }
	    }catch(Exception e){
	        
	    }
	    return distance;
	}
	
	
	

	// Es können nur max 2 Punkte (Boxen) mit Linksklick gesetzt werden und mit
	// Rechtsklick entfernt werden

	public static void MouseListenerPressed(MouseEvent evt) {
		int button = evt.getButton();
		System.out.println("Calculation.MousePressed");
		if (dinge.size() < 2) {
			System.out.println(button);
		}

		if (button == 1) {
			if (dinge.size() < 2) {
				dinge.addElement(new Box(evt.getX(), evt.getY()));
				System.out.println("Box "+ dinge.size()+" wurde erstellt");
			} else {
			}
		} else if (button == 3) {
			if (dinge.size() > 0) {
				dinge.remove(dinge.lastElement());
				System.out.println("Box "+ dinge.size()+" wurde entfernt");
			} else {
			}
		}

	}

	// Gibt die Entfernung der Pixel der zwei Boxen aus, sobald der zweite gesetzt
	// wurde

	public static void MouseListenerReleased(MouseEvent evt) {

		if (evt.getButton() == 1 && dinge.size() == 2) {
			if (dinge.size() == 2) {
				int ergebnis = Calculation.getDistanceBox();
				System.out.println("Die Entfernung beträgt " + ergebnis);
			}
		}
	}

}
