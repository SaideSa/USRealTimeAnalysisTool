package algorithm;

import java.awt.image.BufferedImage;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import InputOutput.AbstractImageSource;
import InputOutput.LivestreamSource;


class DataProcessor {
	
	DistanceMeasurement c = new DistanceMeasurement();
	//DataProcessor d = new DataProcessor();
	AbstractImageSource imgSrc;
		
	BufferedImage readBufImg() {
		Mat mat = imgSrc.getNextMat();
		BufferedImage bufImg = (BufferedImage) HighGui.toBufferedImage(mat);
		return bufImg;
	}
				  
	public void openConnection(){
		imgSrc = new LivestreamSource(0);
		imgSrc.openConnection();	
	}
 
	public void closeConnection(){
		imgSrc.closeConnection();
	}

	public void setGeometry() {
		
		
	}
 
} 
