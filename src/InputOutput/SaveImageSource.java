package InputOutput;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

/**
 * Class for saving the imported frames as Mat Objects (OpenCV). For the saving process the VideoWriter class from the OpenCV library is used.
 * @author sahin
 *
 */
public class SaveImageSource {
	
	private VideoWriter writer;
	public String path;
	private Size size;
	private int fps;
	
	/**
	 * The constructor needs a filename, frames-per-second and width & height of the matrix for creating an object of VideoWriter.
	 * @param path
	 * @param fps
	 * @param width
	 * @param height
	 */
		
	public void saveVideo(String path, int fps, int width, int height) {
		
		this.path = path;
		this.fps = fps;
		size = new Size(width, height); 
		int fourcc = VideoWriter.fourcc('M','J','P','G');
		writer = new VideoWriter(path + "avi", fourcc, 30, size);
		
	}
	
	/**
	 * This method is for storing each Mat object received passed as paramter
	 * @param frameMatrix
	 */
	public void writeMat(Mat frameMatrix) {
		writer.write(frameMatrix);

	}

	

}
