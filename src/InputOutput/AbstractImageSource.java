package InputOutput;

import org.opencv.core.Mat;

public abstract class AbstractImageSource {
	
	public  boolean isConnected = false;
	public  Mat frameMatrix;
	
	public abstract Mat getNextMat();
	
	public abstract boolean openConnection();
	
	public abstract boolean closeConnection();


}
