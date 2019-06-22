package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class Test {
	
	// Methode für Beispielbild
	public static Image getImage() throws Exception {
		
		BufferedImage bi1 = ImageIO.read(new File("Z:\\users\\feike\\PROJ1\\img\\us0.png"));
		Image img1 = SwingFXUtils.toFXImage(bi1, null);
		
		return img1;	
	}
}