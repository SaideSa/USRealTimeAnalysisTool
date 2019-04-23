package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Test {
	
	public static BufferedImage us() throws Exception {
		
		BufferedImage bi = ImageIO.read(new File("Z:\\users\\feike\\PROJ1\\us.png"));
		
		return bi;
			
	}

}
