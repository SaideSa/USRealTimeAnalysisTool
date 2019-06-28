package algorithm;


import InputOutput.AbstractImageSource;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import org.opencv.core.Point;

import javafx.scene.image.*;
import javafx.animation.Timeline;



public class DataManager {
	DistanceMeasurement c = new DistanceMeasurement();
	DataProcessor d = new DataProcessor();
	Timeline timeline = new Timeline();
	AbstractImageSource imgSrc;
	ImageView iv = new ImageView();
	
	public int getDistanceBox() {
		return c.getDistanceBox();
	}
	
	public int getDistanceXY(int x1, int y1, int x2, int y2) {
		return c.getDistanceXY(x1, y1, x2, y2);
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
	
	 public void startUpdating(){
		d.startUpdating();
	}
	

	public void stopUpdating() {
		d.stopUpdating();
	}
	
	public void update() {
		 d.readBufImg();
	}
	
	public BufferedImage readBufImg() {
		return d.readBufImg();
	}
	
	/*public BufferedImage getNextImage() {
	return d.getNextImage();
}*/

}
	

