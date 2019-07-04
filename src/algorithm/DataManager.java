package algorithm;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import org.opencv.core.Point;

/**
 * builds an interface to other teams,
 * includes distance measurement and 
 * conversion from matrix in bufferedimage
 * @author Team2
 *
 */

public class DataManager {
	DistanceMeasurement c = new DistanceMeasurement();
	DataProcessor d = new DataProcessor();
		
	public int getDistanceBox() {
		return c.getDistanceBox();
	}
	
	/**
	 * 
	 * @param x1 x-coordinate first point
	 * @param y1 y-coordinate first point
	 * @param x2 x-coordinate second point
	 * @param y2 y-coordinate second point
	 * @param umrechnungX 
	 * @param umrechnungY
	 * @return
	 */
	public int getDistanceXY(int x1, int y1, int x2, int y2, double umrechnungX, double umrechnungY) {
		return c.getDistanceXY(x1, y1, x2, y2, umrechnungX, umrechnungY);
	}

	public int getDistancePoint(Point a, Point b) {
		return c.getDistancePoint(a, b);
	}
	
	public void MouseListenerPressed(MouseEvent evt) {
		c.MouseListenerPressed(evt);
	}
	
	public void MouseListenerReleased(MouseEvent evt) {
		c.MouseListenerReleased(evt);
	}
	
	public void openConnection (int x){
		d.openConnection(x);
	}
	
	public void closeConnection(){
		d.closeConnection();
	}
	
	public BufferedImage readBufImg() {
		return d.readBufImg();
	}
}
	

