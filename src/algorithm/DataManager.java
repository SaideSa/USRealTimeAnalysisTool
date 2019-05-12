package algorithm;
import java.awt.image.BufferedImage;

public interface DataManager {
	void stop();
	
	// src=null f�r live
	void start(String src); 
	BufferedImage refreshImage();
	
	int berechnen(Box a, Box b);
	int berechnen();
}
