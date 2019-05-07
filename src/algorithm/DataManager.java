package algorithm;

import java.awt.Image;
import InputOutput.VideoCapturing;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
//import javax.swing.ImageIcon; 
import org.opencv.core.Mat;

public class DataManager {

	public static Image toBufferedImage(Mat frameMatrix) {
	    int type = BufferedImage.TYPE_BYTE_GRAY;

	    if (frameMatrix.channels() > 1) {
	        type = BufferedImage.TYPE_3BYTE_BGR;
	    }

	    int bufferSize = frameMatrix.channels() * frameMatrix.cols() * frameMatrix.rows();
	    byte[] b = new byte[bufferSize];
	    frameMatrix.get(0, 0, b); // get all the pixels
	    BufferedImage image = new BufferedImage(frameMatrix.cols(), frameMatrix.rows(), type);

	    final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
	    System.arraycopy(b, 0, targetPixels, 0, b.length);

	    return image;
		}
	
	}

