package algorithm;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;

import inputOutput.FilestreamSource;
import inputOutput.AbstractImageSource;
import inputOutput.LivestreamSource;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class DataProcessor {
	
	


		
		 BufferedImage readBufImg() {
			BufferedImage bufImg = (BufferedImage) HighGui.toBufferedImage(null); //readMatFrame()
			return bufImg;
		
		}

		
		 
		 
		 //ab hier Utils

		
		 
		 
		 
		 public static Image mat2Image(Mat frame)
			{
				try
				{
					return SwingFXUtils.toFXImage(matToBufferedImage(frame), null);
				}
				catch (Exception e)
				{
					System.err.println("Cannot convert the Mat obejct: " + e);
					return null;
				}
			}
			
			/**
			 * Generic method for putting element running on a non-JavaFX thread on the
			 * JavaFX thread, to properly update the UI
			 * 
			 * @param property
			 *            a {@link ObjectProperty}
			 * @param value
			 *            the value to set for the given {@link ObjectProperty}
			 */
			public static <T> void onFXThread(final ObjectProperty<T> property, final T value)
			{
				
				Platform.runLater(() -> {
					property.set(value);
				});
			}
			
			/**
			 * Support for the {@link mat2image()} method
			 * 
			 * @param original
			 *            the {@link Mat} object in BGR or grayscale
			 * @return the corresponding {@link BufferedImage}
			 */
			 BufferedImage matToBufferedImage(Mat original)
			{
				// init
				BufferedImage image = null;
				int width = original.width(), height = original.height(), channels = original.channels();
				byte[] sourcePixels = new byte[width * height * channels];
				original.get(0, 0, sourcePixels);
				
				if (original.channels() > 1)
				{
					image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
				}
				else
				{
					image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
				}
				final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
				System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);
				
				return image;
			}
		 

			//Ab Hier ObjectRegognitionController
			
			// FXML camera button
			@FXML
			private Button cameraButton;
			// the FXML area for showing the current frame
			@FXML
			private ImageView originalFrame;
			
			// a timer for acquiring the video stream
			private ScheduledExecutorService timer;
			// the OpenCV object that performs the video capture
			public VideoCapture capture = new VideoCapture();
			// a flag to change the button behavior
			private boolean cameraActive;
			
			/**
			 * The action triggered by pushing the button on the GUI
			 */
			@FXML
			 void startCamera()
			{
				new SimpleObjectProperty<>();
						
				// set a fixed width for all the image to show and preserve image ratio
				this.imageViewProperties(this.originalFrame, 400);
				
				if (!this.cameraActive)
				{
					// start the video capture
					this.capture.open(0);
					
					// is the video stream available?
					if (this.capture.isOpened())
					{
						this.cameraActive = true;
						
						// grab a frame every 33 ms (30 frames/sec)
						Runnable frameGrabber = new Runnable() {
							
							@Override
							public void run()
							{
								// effectively grab and process a single frame
								Mat frame = grabFrame();
								// convert and show the frame
								Image imageToShow = DataProcessor.mat2Image(frame);
								updateImageView(originalFrame, imageToShow);
							}
						};
						
						this.timer = Executors.newSingleThreadScheduledExecutor();
						this.timer.scheduleAtFixedRate(frameGrabber, 0, 33, TimeUnit.MILLISECONDS);
						
						// update the button content
						this.cameraButton.setText("Stop Camera");
					}
					else
					{
						// log the error
						System.err.println("Failed to open the camera connection...");
					}
				}
				else
				{
					// the camera is not active at this point
					this.cameraActive = false;
					// update again the button content
					this.cameraButton.setText("Start Camera");
					
					// stop the timer
					this.stopAcquisition();
				}
			}
			
			/**
			 * Get a frame from the opened video stream (if any)
			 * 
			 * @return the {@link Image} to show
			 */
			private Mat grabFrame()
			{
				Mat frame = new Mat();
				
				// check if the capture is open
				if (this.capture.isOpened())
				{
					try
					{
						// read the current frame
						this.capture.read(frame);
						
						
						
					}
					catch (Exception e)
					{
						// log the (full) error
						System.err.print("Exception during the image elaboration...");
						e.printStackTrace();
					}
				}
				
				return frame;
			}
			
			/**
			 * Set typical {@link ImageView} properties: a fixed width and the
			 * information to preserve the original image ration
			 * 
			 * @param image
			 *            the {@link ImageView} to use
			 * @param dimension
			 *            the width of the image to set
			 */
			private void imageViewProperties(ImageView image, int dimension)
			{
				// set a fixed width for the given ImageView
				image.setFitWidth(dimension);
				// preserve the image ratio
				image.setPreserveRatio(true);
			}
			
			/**
			 * Stop the acquisition from the camera and release all the resources
			 */
			private void stopAcquisition()
			{
				if (this.timer!=null && !this.timer.isShutdown())
				{
					try
					{
						// stop the timer
						this.timer.shutdown();
						this.timer.awaitTermination(33, TimeUnit.MILLISECONDS);
					}
					catch (InterruptedException e)
					{
						// log any exception
						System.err.println("Exception in stopping the frame capture, trying to release the camera now... " + e);
					}
				}
				
				if (this.capture.isOpened())
				{
					// release the camera
					this.capture.release();
				}
			}
			
			/**
			 * Update the {@link ImageView} in the JavaFX main thread
			 * 
			 * @param view
			 *            the {@link ImageView} to update
			 * @param image
			 *            the {@link Image} to show
			 */
			private void updateImageView(ImageView view, Image image)
			{
				DataProcessor.onFXThread(view.imageProperty(), image);
			}
			
			/**
			 * On application close, stop the acquisition from the camera
			 */
			protected void setClosed()
			{
				this.stopAcquisition();
			}
			
			

	}

/* 
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


 // void stop() {
	// this.src.closeConnection();
	
// }
*/






