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
  private String path;
  private int frameTotalNumber;

  public FilestreamSource(String path) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		frameMatrix = new Mat();
		this.path = path;
	}

	public boolean openConnection() {
		System.out.println("capFile");
		vc = new VideoCapture(path, (int) Videoio.CAP_DSHOW);
		if (vc.isOpened()) {

			System.out.println("found VideoSource " + vc.toString());
			isConnected = true;
			frameTotalNumber = (int) vc.get(Videoio.CAP_PROP_FRAME_COUNT);

		} else {
			System.out.println("!!! Did not connect to camera !!!");
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
		try {
			vc.release();
			HighGui.destroyAllWindows();
			exit = true;
			isConnected = false;
		}catch(Exception ex) {
			exit = false;
		}
		
		return exit;
	}
	
	public int getFrameNumber() {
		return frameTotalNumber;
	}
}

