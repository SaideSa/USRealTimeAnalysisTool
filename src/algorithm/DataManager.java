package algorithm;

import java.awt.Image;
import inputOutput.VideoCapturing;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import javax.swing.ImageIcon; 
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;

public class DataManager {
	// Klasse zur Formatierung von einer Matrix in ein Buffered Image

	public BufferedImage readBufImg() {
		BufferedImage bufImg = (BufferedImage) HighGui.toBufferedImage(readMatFrame());
		return bufImg;
	
	}
	
	
}



		/*public static Image toBufferedImage(Mat frameMatrix) {
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

// BufferedImage skalieren
/* BufferedImage ergebnis = new BufferedImage(skaliertebreite, skaliertehöhe, , BufferedImage.TYPE_INT_ARGB);
ergebnis.getGraphics().drawImage(image, 0,0, skaliertebreite, skaliertehöhe, null); */

//Aufnahme skallieren lassen
/*
 * public class Scale {  

        ImageIcon ico = new ImageIcon("/home/webs/bild.jpg"); 
        ico.setImage(ico.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)); 
    } 
}

 */



//FPS anzeigen lassen:
/*VideoCapture videoCapture = new VideoCapture();
videoCapture.open("c:/movies/mymovie.mp4");
double fps = videoCapture.get(Videoio.CAP_PROP_FPS);
System.out.println( "fps: " + fps);*/


// Set FPS
/*	
void setup() {
  frameRate(4);
}
int pos = 0;
void draw() {
  background(204);
  pos++;
  line(pos, 20, pos, 80);
  if (pos > width) {
    pos = 0;
  }
} */

/*public class BoxPlot {

	private List<Double> errors = new ArrayList();
	private double min;
	private double max;
	private double q1;
	private double median;
	private double q3;
	*/




//package InputOutput;
/*
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

	public Mat getNextMat() {

		//Umwandlung von Byte in Mat nötig später Mat als Rückgabewert;
		return null;
	}

	public boolean checkConnection() {
		isConnected = igtConnection.isConnected();
		return isConnected;
	}
}
*/
