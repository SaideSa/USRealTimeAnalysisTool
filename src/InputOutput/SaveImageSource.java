package InputOutput;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
		int fourcc = VideoWriter.fourcc('M','J','P','G');
		writer = new VideoWriter(path + ".avi", fourcc, 30, size);
		System.out.println(path);
		
		
	}
	
	/**
	 * This method is for storing each Mat object received passed as paramter
	 * @param frameMatrix
	 */
	public void writeMat(Mat frameMatrix) {
		writer.write(frameMatrix);

	
		//ab hier nur Test;
		count++;
		
		if(count == 1) {
			
		        try {
		            File file = new File("C:\\Users\\sahin\\Desktop\\abc.txt");
		            if (!file.exists()) {
		               file.createNewFile();
		            } 
		            FileWriter fw = new FileWriter(file.getAbsoluteFile());
		            BufferedWriter bw = new BufferedWriter(fw);
		            
		            for(int i = 0; i < frameMatrix.rows(); i++) {
		            	for(int j = 0; j < frameMatrix.cols(); j++) {
		            		bw.write(frameMatrix.get(i, j) + " ");
		            	}
		            }
		            bw.close();
		            
		            System.out.println("Done");
		         } catch (IOException e) {
		            e.printStackTrace();
		         } 

			
		}

	}

	

}