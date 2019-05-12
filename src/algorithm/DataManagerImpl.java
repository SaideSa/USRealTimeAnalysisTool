package algorithm;

import java.awt.Image;

import InputOutput.AbstractImageSource;
import InputOutput.FilestreamSource;
import InputOutput.LivestreamSource;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import org.opencv.core.Mat;
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
	public BufferedImage refreshImage() {
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
	public int berechnen(Box a, Box b) {
		return Calculation.berechnen2(a.x, a.y, b.x, b.y);
	}
	
	@Override
	public int berechnen() {
		return Calculation.berechnen();
	}
	
	
	private BufferedImage readBufImg() {
		BufferedImage bufImg = (BufferedImage) HighGui.toBufferedImage(null); //readMatFrame()
		return bufImg;
	
	}




}

