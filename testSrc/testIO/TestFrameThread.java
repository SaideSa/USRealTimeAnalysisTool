package testIO;

import java.awt.image.BufferedImage;

import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;

import InputOutput.AbstractImageSource;

/*This class is a Thread which starts after the user press a start button. It takes the frames continuously from the device or a file through an abstract class and then set them on the TestPanel*/
public class TestFrameThread extends Thread {
	Mat mat;
	AbstractImageSource imgSrc;
	BufferedImage bufImg;
	TestPanel panel;
	
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

				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// panel.fps = vc.fps;
				panel.repaint();

			}
		}
	}

}
