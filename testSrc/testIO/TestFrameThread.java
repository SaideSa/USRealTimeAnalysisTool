package testIO;

import java.awt.image.BufferedImage;

import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;

import InputOutput.AbstractImageSource;
import InputOutput.SaveImageSource;

/*This class is a Thread which starts after the user press a start button. It takes the frames continuously from the device or a file through an abstract class and then set them on the TestPanel*/
public class TestFrameThread extends Thread {
	Mat mat;
	AbstractImageSource imgSrc;
	BufferedImage bufImg;
	TestPanel panel;
	boolean saveVideoOn;
	SaveImageSource save;
	
	public TestFrameThread(TestPanel tp, AbstractImageSource src) {
		imgSrc = src;
		panel = tp;
		start();
	}
	public void run() {
		if (imgSrc.openConnection()) {
			while (imgSrc.isConnected) {
				mat = imgSrc.getNextMat();
				bufImg = (BufferedImage) HighGui.toBufferedImage(mat);
				panel.setFace(bufImg);
				panel.fps = imgSrc.fps;
				
				if(saveVideoOn == true) {
					save.saveVideo(mat);
				}


				panel.repaint();

			}
		}
	}
	
	public void saveVideoStart(String path) {
		save = new SaveImageSource(path, imgSrc.fps, mat.width(), mat.height());
		saveVideoOn = true;
		
	}
	
	public void stopSave() {
		saveVideoOn = false;
		
	}

}
