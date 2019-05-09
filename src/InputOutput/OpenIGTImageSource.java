package InputOutput;

import org.medcare.igtl.messages.ImageMessage;
import org.opencv.core.Core;
import org.opencv.core.Mat;

public class OpenIGTImageSource extends AbstractImageSource {
	private String ip;
	private int port;
	private OpenIGTConnection igtConnection;
	private ImageMessage imgMsg;
	private boolean isConnected;

	public OpenIGTImageSource(String ipAddress, int port) {
		ip = ipAddress;
		this.port = port;
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public boolean openConnection() {
		igtConnection = new OpenIGTConnection(ip, port);
		isConnected = true;
		return isConnected;
	}

	public boolean closeConnection() {
		igtConnection.stop();
		isConnected = false;
		return isConnected;
	}

	public ImageMessage getImageMessage() {
		imgMsg = igtConnection.getImageMessage();
		return imgMsg;
	}

	public void unpackImgMsg() {
		boolean unpacked = false;
		byte[] body = null;
		byte[] imgData = null;

		try {
			unpacked = imgMsg.UnpackBody();
			body = imgMsg.PackBody();
			imgData = imgMsg.getImageData();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Unpacked: " + unpacked + " --- body length: " + body.length + " --- data length: "
				+ imgData.length + " --- header length: " + imgMsg.getImageHeader().length);

		// data.length = 307200 (640x480)

	}

	// public Mat getNextMat() {

		//Umwandlung von Byte in Mat nötig;
	
	// }

	public boolean checkConnection() {
		isConnected = igtConnection.isConnected();
		return isConnected;
	}
}
