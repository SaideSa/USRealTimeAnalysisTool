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
 * @author team3
 *
 */
public class SaveImageSource {
	
	private VideoWriter writer;
	public String path;
	private Size size;
	private int fps;
	private int count =0;
	
	/**
	 * This method needs a filename, frames-per-second and width & height of the matrix for creating an object of VideoWriter.
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
	 * This method is for saving the video by storing each Mat object that is received. It only works if saveVideo was called before for setting the parameters. 
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

	
	/** 
	 * This method is for saving one single Image given as a parameter on the computer. A destination path is also necessary here as a parameter.
	 * @param bufImg
	 * @param path
	 */
	public void saveImage(BufferedImage bufImg, String path) {
		
		File output = new File(path + ".png");
		
	
		try {
			ImageIO.write(bufImg,"png",output);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}

		
	}
	

}
