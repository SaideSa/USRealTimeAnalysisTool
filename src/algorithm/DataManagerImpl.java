package algorithm;

import java.awt.Image;
import java.awt.event.MouseEvent;

import InputOutput.AbstractImageSource;
import InputOutput.FilestreamSource;
import InputOutput.LivestreamSource;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;

public class DataManagerImpl implements DataManager {
	
	private AbstractImageSource src;

	
	@Override
	public void start(String filesrc) { 
		if (filesrc == null) {
		this.src = new LivestreamSource(0);
		}
		else {
			this.src = new FilestreamSource(filesrc); 
		}
		
		this.src.openConnection(); // für hardware Exeption zb "File Not Found"
				
	}
	
	@Override
	
	public BufferedImage getNextImage() {
		if (this.src.isConnected) {
		return (BufferedImage) HighGui.toBufferedImage(this.src.getNextMat());
		}
		else {
			return null;
		}
	}
	
	@Override
	public void stop() {
		this.src.closeConnection();
		
	}

	
	@Override
	public int getDistanceBox() {
		return Calculation.getDistanceBox();
	}
	
	@Override
	public int getDistanceXY(int x1, int y1, int x2, int y2) {
		return Calculation.getDistanceXY(x1, y1, x2, y2);
	}
	
	
	private BufferedImage readBufImg() {
		BufferedImage bufImg = (BufferedImage) HighGui.toBufferedImage(null); //readMatFrame()
		return bufImg;
	
	}

	@Override
	public int getDistancePoint(Point a, Point b) {
		return Calculation.getDistancePoint(a, b);
	}

	@Override
	public void MouseListenerPressed(MouseEvent evt) {
		System.out.println("DataManagerImpl.MousePressed");
		Calculation.MouseListenerPressed(evt);
		
	}

	@Override
	public void MouseListenerReleased(MouseEvent evt) {
		// TODO Auto-generated method stub
		
	}

	public LatencyTest() {
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	    VideoCapture cap = new VideoCapture(0);
	    Mat camImage = new Mat();
	    JFrame frame = new JFrame();
	    JLabel label = new JLabel();
	    frame.add(label);
	    frame.setVisible(true);
	    frame.pack();
	    int frameWidth = 320;
	    int frameHeight = 240;
	    frame.setSize(frameWidth, frameHeight);
	    cap.set(Videoio.CAP_PROP_FRAME_WIDTH,frameWidth);
	    cap.set(Videoio.CAP_PROP_FRAME_HEIGHT,frameHeight);
	/**
	* initialization of a new Videocapture object
	* 
	*  @returns the defined dimension of the new object
	* 
	*/
	    if (cap.isOpened()) {
	        while (true) {
	            cap.read(camImage);
	            if (!camImage.empty()) {
	                label.setIcon(new ImageIcon(convertMatToBufferedImage(camImage)));
	            } else {
	                System.out.println("-- Frame not captured --");
	                break;
	            }
	        }
	    } else {
	        System.out.println("Couldn't open capture.");
	    }
	}          /** the connection was successful or not */

	public  BufferedImage convertMatToBufferedImage(Mat in) {
	    int width = in.width();
	    int height = in.height();
	    BufferedImage out;
	    byte[] data = new byte[width * height * (int) in.elemSize()];
	    int type;
	    in.get(0, 0, data);
	    
	    
	    if (in.channels() == 1) {
	        type = BufferedImage.TYPE_BYTE_GRAY;
	    } else {
	        type = BufferedImage.TYPE_3BYTE_BGR;
	    }

	    out = new BufferedImage(width, height, type);
	    out.getRaster().setDataElements(0, 0, width, height, data);
	/**
	* the latency is generating by out.getRaster
	*
	*@returns the connection was successful and is generating
	*
	*/
	    return out;
		}
	}
	 	
}

