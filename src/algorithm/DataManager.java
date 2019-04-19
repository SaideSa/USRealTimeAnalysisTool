package algorithm;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import org.opencv.core.Mat;

public class DataManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

public static Image toBufferedImage(Mat m) {
    int type = BufferedImage.TYPE_BYTE_GRAY;

    if (m.channels() > 1) {
        type = BufferedImage.TYPE_3BYTE_BGR;
    }

    int bufferSize = m.channels() * m.cols() * m.rows();
    byte[] b = new byte[bufferSize];
    m.get(0, 0, b); // get all the pixels
    BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);

    final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
    System.arraycopy(b, 0, targetPixels, 0, b.length);

    return image;
}}


// FPS anzeigen lassen:
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

