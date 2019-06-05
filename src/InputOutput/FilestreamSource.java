package InputOutput;

import java.awt.image.BufferedImage;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

/**
 * provides the footage from a file with these formats: mp4, avi, mkv, mov, 3GP,
 * jpg and png
 * 
 * @author Team 3
 *
 */
public class FilestreamSource extends AbstractImageSource {

	private VideoCapture vc;
	public BufferedImage bufImg = null;
	private String path;
	private int frameTotalNumber;

	/**
	 * constructs a new FilestreamSource object with the transmitted
	 * <code>path</code>.
	 * 
	 * @param path
	 *            describes the path of the file
	 */
	public FilestreamSource(String path) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		frameMatrix = new Mat();
		this.path = path;
	}

	/**
	 * initializes a new VideoCapture object using the <code>path</code>. The video
	 * or image is opened.
	 * 
	 * @return whether the connection was successful or not
	 */
	public boolean openConnection() {
		vc = new VideoCapture(path);

		if (vc.isOpened()) {
			System.out.println("found VideoSource " + vc.toString());
			isConnected = true;
			frameTotalNumber = (int) vc.get(Videoio.CAP_PROP_FRAME_COUNT);

		} else {
			System.out.println("!!! Did not connect to camera !!!");
		}

		return isConnected;
	}

	/**
	 * Transmits the matrix of the picture
	 * 
	 * @return <code>frameMatrix</code>
	 */
	public Mat getNextMat() {
		fps = (int) vc.get(Videoio.CAP_PROP_FPS);
		vc.read(frameMatrix);

		if (frameMatrix.empty()) {
			System.out.println("!!! Nothing captured from webcam !!!");
		}

		return frameMatrix;

	}

	// public BufferedImage readBufImg() {
	//
	// bufImg = (BufferedImage) HighGui.toBufferedImage(getNextMat());
	// return bufImg;
	//
	// }

	/**
	 * sets <code>isConnected = false</code>
	 * 
	 * @return <code>isConnected = false</code>
	 */
	public boolean closeConnection() {

		vc.release();
		HighGui.destroyAllWindows();
		exit = true;
		isConnected = false;

		return exit;
	}

	public int getFrameNumber() {
		return frameTotalNumber;
	}
}
