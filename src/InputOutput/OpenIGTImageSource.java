package InputOutput;



import org.medcare.igtl.messages.ImageMessage;

import org.opencv.core.Core;
import org.opencv.core.Mat;


/**
 * 
 * @author team3
 *
 */
public class OpenIGTImageSource extends AbstractImageSource {
	private String ip = "127.0.0.1";
	private int port = 18944;
	private OpenIGTConnection igtConnection;
	private ImageMessage imgMsg;
	private byte[] imgData;
	private long[] dimensions;
	private int rows = 0;
	private int cols = 0 ;
	private int type = 1;
	
	
	
	public OpenIGTImageSource() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
<<<<<<< HEAD
		//frameMatrix = new Mat(768, 1024, 1);
		frameMatrix = new Mat(480, 640, 1);
=======

>>>>>>> team3
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
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		imgMsg = igtConnection.getImageMessage();
		rows = (int) imgMsg.getDimensions()[1];
		cols = (int) imgMsg.getDimensions()[0];
		type = (int) imgMsg.getDimensions()[2];
		
		frameMatrix = new Mat (rows, cols, type);
		
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
		
//		System.out.println(rows + " " + cols);
		imgData = igtConnection.getImageDataByte();
		
		frameMatrix.put(0, 0, imgData);
	
		return frameMatrix;
	}
	
	

	public boolean checkConnection() {
		isConnected = igtConnection.isConnected();
		
		return isConnected;
	}
	
	public long getImageScalarType() {
		return imgMsg.getScalarType();
	}
	
	public double[] getSpacing() {
		return imgMsg.getSpacing();
	}
	
	public double[] getOrigin() {
		return imgMsg.getOrigin();
	}
}
