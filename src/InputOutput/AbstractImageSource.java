package InputOutput;

import org.opencv.core.Mat;

public abstract class AbstractImageSource {
	
	public static boolean isConnected = false;
	public static Mat frameMatrix;
	
	public abstract Mat getNextMat();
	
	public abstract boolean openConnection();
	
	public abstract boolean closeConnection();


}
