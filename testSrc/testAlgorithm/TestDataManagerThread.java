package testAlgorithm;

import java.awt.image.BufferedImage;

import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;

import InputOutput.AbstractImageSource;
import algorithm.DataManagerImpl;
import testIO.TestPanel;
import algorithm.DataManagerOLD;

public class TestDataManagerThread extends Thread {
	private DataManagerOLD datamanager;
	private TestPanel panel;
	
	public TestDataManagerThread(TestPanel tp) {
		this.datamanager = new DataManagerImpl();
		this.panel = tp;
	}
	
	public void startThread(String src) {
		this.datamanager.start(src);
		super.start();
	}
	
	public void run() {
		BufferedImage img;
		
		 do {
			 img=this.datamanager.getNextImage();
			
			panel.setFace(img);

			// panel.fps = vc.fps;
			panel.repaint();

		} while (img != null) ;
		
	}
	public DataManagerOLD getDataManager() {
		System.out.println("getDataManager");
		return this.datamanager;
	}
}



