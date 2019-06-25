package InputOutput;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.medcare.igtl.messages.ImageMessage;
import org.omg.CORBA.MARSHAL;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

public class OpenIGTImageSource extends AbstractImageSource {
	private String ip = "127.0.0.1";
	private int port = 18944;
	private OpenIGTConnection igtConnection;
	private ImageMessage imgMsg;
	private int count = 0;
	private byte[] imgData;
	private MatOfByte matOfByte;
	
	
	public OpenIGTImageSource() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		frameMatrix = new Mat(480, 640, 1);
	}
	
	public OpenIGTImageSource(String ipAddress, int port) {
		ip = ipAddress;
		this.port = port;
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
	}

	public boolean openConnection() {
		igtConnection = new OpenIGTConnection(ip, port);
		isConnected = true;
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isConnected;
	}

	public boolean closeConnection() {
		igtConnection.stop();
		isConnected = false;
		exit = true;
		return exit;
	}

	public ImageMessage getImageMessage() {
		imgMsg = igtConnection.getImageMessage();
		return imgMsg;
	}

	public Mat getNextMat() {
		
		
		imgData = igtConnection.getImageDataByte();
		
//		matOfByte.p
	
//		matOfByte = new MatOfByte(640, 480, imgData);

	

		frameMatrix.put(0, 0, imgData);
//		System.out.println(frameMatrix.col(30) + " ............ ");
	
		return frameMatrix;
	}
	
	

	public boolean checkConnection() {
		isConnected = igtConnection.isConnected();
		return isConnected;
	}
}
