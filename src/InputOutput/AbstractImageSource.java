package InputOutput;

import org.opencv.core.Mat;

public abstract class AbstractImageSource {
	
	public static boolean isConnected = false;
	public static boolean exit;
	public static Mat frameMatrix;
	public int fps;
	
	public abstract Mat getNextMat();
	
	public abstract boolean openConnection();
	
	public abstract boolean closeConnection();


}
