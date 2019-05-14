package InputOutput;

import java.awt.image.BufferedImage;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

public class LivestreamSource extends AbstractImageSource{
	
	private VideoCapture vc;
	public BufferedImage bufImg = null;
	public int fps;
	private int deviceID = 0;
	
	public LivestreamSource(int id) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		frameMatrix = new Mat();
		deviceID = id;
	}
	
	public boolean openConnection() {
		
		System.out.println("capDev");
		vc = new VideoCapture(deviceID);
		
		if(vc.isOpened()) {
			System.out.println("found VideoSource " + vc.toString());
			isConnected = true;
		}else {
			System.out.println("!!! Did not connect to camera !!!");
			isConnected = false;
		}
		
		
		return isConnected;
	}
	
	public Mat getNextMat() { 
		System.out.println((int) vc.get(Videoio.CAP_PROP_FPS));
		fps = (int) vc.get(Videoio.CAP_PROP_FPS);
		vc.read(frameMatrix);
		
		if (frameMatrix.empty()) {
			System.out.println("!!! Nothing captured from webcam !!!");
		}

		return frameMatrix;

	}

	
	public BufferedImage readBufImg() {
		bufImg = (BufferedImage) HighGui.toBufferedImage(getNextMat());
		return bufImg;

	}

	public boolean closeConnection() {
		vc.release();
		isConnected = false;
		return isConnected;
	}

}
