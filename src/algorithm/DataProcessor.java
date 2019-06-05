package algorithm;

import java.awt.image.BufferedImage;

import org.opencv.highgui.HighGui;

import inputOutput.FilestreamSource;
import inputOutput.AbstractImageSource;
import inputOutput.LivestreamSource;

class DataProcessor {
	
	

		 
		AbstractImageSource src;
		
		
		 void start(String filesrc) { 
			 if (filesrc == null) {
					this.src = new LivestreamSource(0);
					}
					else {
						this.src = new FilestreamSource(filesrc); 
					}
					
					this.src.openConnection(); // für hardware Exeption zb "File Not Found"
							
				}
		
		
		
		 BufferedImage getNextImage() {
			if (this.src.isConnected) {
			return (BufferedImage) HighGui.toBufferedImage(this.src.getNextMat());
			}
			else {
				return null;
			}
		}
		
		
		 void stop() {
			this.src.closeConnection();
			
		}

		
		 BufferedImage readBufImg() {
			BufferedImage bufImg = (BufferedImage) HighGui.toBufferedImage(null); //readMatFrame()
			return bufImg;
		
		}

		

		


	}




