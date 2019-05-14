package org.medcare.igtl.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.medcare.igtl.messages.ImageMessage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.neuronrobotics.sdk.addons.kinematics.math.TransformNR;


public class IGTImage {
	ImageMessage message;
	BufferedImage image = null;
	TransformNR transform = new TransformNR(); 
	byte[] imgdata = null;
	
	public IGTImage(ImageMessage m, byte[] data){
		message=m;
		//TODO Satya, populate all fields.
		image = null;
		transform = new TransformNR(); 
		imgdata = data;
		System.out.println("Comparison :  "  + message.getImageDataSize() + " ---- Origin: " + message.getOrigin().length + "---- Normals: " + message.getNormals().length);
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Mat mat = Imgcodecs.imdecode(new MatOfByte(data), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
	
		
		System.out.println(message.getEndian() + "++++" +  message.getScalarType() + " +++ " + message.getDeviceName());
		
//		double [][] matrix = message.getMatrix();
//		
//		mat.create( matrix.length, matrix[1].length ,1); // 8-bit single channel image
//		image = (BufferedImage) HighGui.toBufferedImage(mat);
		System.out.println("Width of Buf: " + mat.cols());
//		for (int i=0; i<matrix.length; i++)
//		{
//		    for(int j=0; j<matrix[1].length; j++)
//		    {
//		         mat.put(i, i, matrix[i][j]);
//		         
//		    }
//		}
////		
//		for(int i = 0; i < matrix.length; i++) {
//			for(int j = 0; j < matrix[i].length; j++) {
//				System.out.println("*******************" + matrix[i][j]);
//			}
//		}
	}
	
	public TransformNR getTransform(){
//		message.setMatrix(message.getOrigin(), message.getNormals());
		Mat mat = new Mat();

//		Imgproc.getPerspectiveTransform();
		
		return transform;
	}
	
	public BufferedImage getBufferedImage(){
		
		return image;
	}
}
