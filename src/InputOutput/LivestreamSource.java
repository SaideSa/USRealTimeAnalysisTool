package InputOutput;

import java.awt.image.BufferedImage;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

/**
<<<<<<< HEAD
 * provides the livestream footage from a webcam, ultrasound device or any other
 * suitable devices.
=======
 * provides the livestream footage from a webcam, ultrasound device or
 * any other suitable devices.
>>>>>>> Team3
 * 
 * @author Team 3
 *
 */
public class LivestreamSource extends AbstractImageSource {

	private VideoCapture vc;
//	public BufferedImage bufImg = null;
	public int fps;
	private int deviceID = 0;

	/**
<<<<<<< HEAD
	 * constructs a new LivestreamSource object with the transmitted
	 * <code>id</code>.
=======
	 * constructs a new LivestreamSource object with the transmitted <code>id</code>.
>>>>>>> Team3
	 * 
	 * @param id describes which device is used
	 */
	public LivestreamSource(int id) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		frameMatrix = new Mat();
		deviceID = id;
	}

	/**
<<<<<<< HEAD
	 * initializes a new VideoCapture object using the <code>deviceID</code>. The
	 * webcam (or other video devices) is opened.
=======
	 * initializes a new VideoCapture object using the <code>deviceID</code>. 
	 * The webcam (or other video devices) is opened.
>>>>>>> Team3
	 * 
	 * @return whether the connection was successful or not
	 */
	public boolean openConnection() {

		System.out.println("capDev");
		vc = new VideoCapture(deviceID);

		if (vc.isOpened()) {
			System.out.println("found VideoSource " + vc.toString());
			isConnected = true;
		} else {
			System.out.println("!!! Did not connect to camera !!!");
			isConnected = false;
		}

		return isConnected;
	}

	/**
	 * Transmits the matrix of the picture
	 * 
	 * @return <code>frameMatrix</code>
	 */
	public Mat getNextMat() {
		// System.out.println((int) vc.get(Videoio.CAP_PROP_FPS));
		System.out.println((int) vc.get(Videoio.CAP_PROP_FPS));
		fps = (int) vc.get(Videoio.CAP_PROP_FPS);
		vc.read(frameMatrix);

		if (frameMatrix.empty()) {
			System.out.println("!!! Nothing captured from webcam !!!");
		}

		return frameMatrix;

	}

//	public BufferedImage readBufImg() {
//		bufImg = (BufferedImage) HighGui.toBufferedImage(getNextMat());
//		return bufImg;
//
//	}
	/**
	 * closes connection to the device and sets <code>isConnected = false</code>
	 * 
	 * @return <code>isConnected = false</code>
	 */
	public boolean closeConnection() {
		vc.release();
		isConnected = false;
		return isConnected;
	}

}
