package InputOutput;

import org.medcare.igtl.messages.ImageMessage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;

public class OpenIGTImageSource extends AbstractImageSource {
	private String ip;
	private int port;
	private OpenIGTConnection igtConnection;
	private ImageMessage imgMsg;
	Mat mat = new Mat();

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
		byte[] body;
		byte[] imgData;
		byte[][] imgData2D = new byte [640][480];
		int c = 0;

		try {
			unpacked = imgMsg.UnpackBody();
			body = imgMsg.PackBody();
			imgData = imgMsg.getImageData();
//			Mat mat=new Mat();
			
			for(int i=0; i < 640; i++) {
				for(int j=0; j < 480; j++) {
					  mat.put(i, j, imgData);
					imgData2D[i][j] = imgData[c];
					c++;
				}
			}
		
		
//			System.out.println(imgData2D);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("Unpacked: " + unpacked + " --- body length: " + body.length + " --- data length: "
//				+ imgData.length + " --- header length: " + imgMsg.getImageHeader().length);

		// data.length = 307200 (640x480)

	}

	public Mat getNextMat() {

		//Umwandlung von Byte in Mat nötig später Mat als Rückgabewert;
		return null;
	}

	public boolean checkConnection() {
		isConnected = igtConnection.isConnected();
		return isConnected;
	}
}
