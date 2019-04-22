package algorithm;

public class Calculation {
	
	public static int berechnen() {
		Box tempbox1 = (Box) FirstClass.dinge.firstElement();
		Box tempbox2 = (Box) FirstClass.dinge.lastElement();
		Box tempbox3 = new Box(tempbox1.x, tempbox2.y);
		int ankathete1 = tempbox3.y - tempbox1.y; 
		int ankathete2 = tempbox3.x - tempbox2.x;
		int entfernung = (int) Math.hypot(ankathete1,ankathete2);
		return entfernung;
	}
	
	
	public static int berechnen2(int x1, int y1, int x2, int y2){
	return (int) Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2-y1), 2));
	}

}
