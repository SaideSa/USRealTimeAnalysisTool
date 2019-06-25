package InputOutput;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
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
	private int count =0;
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

		int fourcc = VideoWriter.fourcc('D','V','I','X');
		writer = new VideoWriter(path + ".mpg", fourcc, 30, size);
		
	}
	
	/**
	 * This method is for storing each Mat object received passed as parameter
	 * @param frameMatrix
	 */
	public void writeMat(Mat frameMatrix) {
		writer.write(frameMatrix);



	}

	
	public void saveImage(BufferedImage bufImg, String path) {
		
		File output = new File(path + ".jpg");
		
	
		try {
			ImageIO.write(bufImg,"jpg",output);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}

		
	}
	
	

	

}
