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
	
	
	public OpenIGTImageSource() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		frameMatrix = new Mat(480, 640, 1);
	}
	
	public OpenIGTImageSource(String ipAddress, int port) {
		ip = ipAddress;
		this.port = port;
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		frameMatrix = new Mat(480, 640, 1);
		
	}

	public boolean openConnection() {
		igtConnection = new OpenIGTConnection(ip, port);
		isConnected = true;
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
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

		frameMatrix.put(0, 0, imgData);
		return frameMatrix;
	}
	
	

	public boolean checkConnection() {
		isConnected = igtConnection.isConnected();
		return isConnected;
	}
}
