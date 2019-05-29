package algorithm;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import org.opencv.core.Point;

public interface DataManagerI {
	void stop();
	
	// src=null für live
	void start(String src); 
	BufferedImage getNextImage();
	
	int getDistanceBox();
	int getDistanceXY(int x1, int y1, int x2, int y2);
	int getDistancePoint(Point a, Point b);
	
	void MouseListenerPressed(MouseEvent evt);
	void MouseListenerReleased(MouseEvent evt);
}
