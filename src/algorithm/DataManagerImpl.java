package algorithm;

import java.awt.Image;
import java.awt.event.MouseEvent;

import InputOutput.AbstractImageSource;
import InputOutput.FilestreamSource;
import InputOutput.LivestreamSource;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.highgui.HighGui;

public class DataManagerImpl implements DataManager {
	
	private AbstractImageSource src;

	
	@Override
	public void start(String filesrc) { 
		if (filesrc == null) {
		this.src = new LivestreamSource(0);
		}
		else {
			this.src = new FilestreamSource(filesrc); 
		}
		
		this.src.openConnection(); // für hardware Exeption zb "File Not Found"
				
	}
	
	@Override
	
	public BufferedImage getNextImage() {
		if (this.src.isConnected) {
		return (BufferedImage) HighGui.toBufferedImage(this.src.getNextMat());
		}
		else {
			return null;
		}
	}
	
	@Override
	public void stop() {
		this.src.closeConnection();
		
	}

	
	@Override
	public int getDistanceBox() {
		return Calculation.getDistanceBox();
	}
	
	@Override
	public int getDistanceXY(int x1, int y1, int x2, int y2) {
		return Calculation.getDistanceXY(x1, y1, x2, y2);
	}
	
	
	private BufferedImage readBufImg() {
		BufferedImage bufImg = (BufferedImage) HighGui.toBufferedImage(null); //readMatFrame()
		return bufImg;
	
	}

	@Override
	public int getDistancePoint(Point a, Point b) {
		return Calculation.getDistancePoint(a, b);
	}

	@Override
	public void MouseListenerPressed(MouseEvent evt) {
		System.out.println("DataManagerImpl.MousePressed");
		Calculation.MouseListenerPressed(evt);
		
	}

	@Override
	public void MouseListenerReleased(MouseEvent evt) {
		// TODO Auto-generated method stub
		
	}

	


}

