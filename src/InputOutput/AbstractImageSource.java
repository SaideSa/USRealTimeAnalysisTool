package InputOutput;

import org.opencv.core.Mat;

public abstract class AbstractImageSource {
	

	public boolean isConnected = false;
	public boolean exit;
	public Mat frameMatrix;
	public int fps;


	
	public abstract Mat getNextMat();
	
	public abstract boolean openConnection();
	
	public abstract boolean closeConnection();


}
