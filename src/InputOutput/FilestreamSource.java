package InputOutput;

import java.awt.image.BufferedImage;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;



public class FilestreamSource extends AbstractImageSource{

	private VideoCapture vc;
	public BufferedImage bufImg = null;
	public int fps;
	private String path;
	
	public FilestreamSource(String path) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		frameMatrix = new Mat();
		this.path = path;
	}

	public boolean openConnection() {
		System.out.println("capFile");
		vc = new VideoCapture(path);
		if (vc.isOpened()) {

			System.out.println("found VideoSource " + vc.toString());
			isConnected = true;

		} else {
			System.out.println("!!! Did not connect to camera !!!");
		}
		
		return isConnected;
	}
	
	public Mat getNextMat() { // könnte auch getNextFrame heißen;
		// System.out.println("Hello");
		System.out.println((int) vc.get(Videoio.CAP_PROP_FPS));
		fps = (int) vc.get(Videoio.CAP_PROP_FPS);
		vc.read(frameMatrix);
		
		if (frameMatrix.empty()) {
			System.out.println("!!! Nothing captured from webcam !!!");
		}

		return frameMatrix;

	}

	
	public BufferedImage readBufImg() {
		// System.out.println("Hello2");
		bufImg = (BufferedImage) HighGui.toBufferedImage(getNextMat());
		return bufImg;

	}
	
	public boolean closeConnection() {
		isConnected=false;
		return isConnected;
	}
}

