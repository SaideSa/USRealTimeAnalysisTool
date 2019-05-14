package testIO;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileSystemView;

import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;

import InputOutput.*;

/*This class needs TestPanel and TestFrameThread to work properly*/
public class TestFrame extends JFrame implements ActionListener {
	
	JPanel main = new JPanel(); //this panel will contain a panel for buttons and another panel for painting the frames
	
	JPanel buttonPanel = new JPanel();
	JButton startLive = new JButton("Start LiveStram");
	JButton startFile = new JButton("Load File");
	JButton stop = new JButton("Stop");
	
	TestPanel videoPanel = new TestPanel();
	AbstractImageSource imgSrc;
	Mat mat;
	BufferedImage bufImg;
	TestFrameThread thread;


	public TestFrame() {
	
		init();
	
	}
	
	public void init() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(1000, 700));

		main.setLayout(new BorderLayout());
		main.add(videoPanel, BorderLayout.CENTER);
	
		/*Panel with three Buttons: startLive, startFile, stop*/
		buttonPanel.setLayout(new FlowLayout());
		startLive.setSize(100, 20);
		startFile.setSize(100, 20);
		stop.setSize(100, 20);
		buttonPanel.add(startLive);
		buttonPanel.add(startFile);
		buttonPanel.add(stop);
		startLive.addActionListener(this);
		startFile.addActionListener(this);
		stop.addActionListener(this);

		main.add(buttonPanel, BorderLayout.PAGE_END);
		
		this.setContentPane(main);;
		

	}

	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		// The datatransport starts after user interaction
		if (src == startLive) {
			LivestreamSource liveStream = new LivestreamSource(0);
			imgSrc = liveStream;
			thread = new TestFrameThread(videoPanel, imgSrc);

		}
		if (src == startFile) {
			// filechooser will open the explorer;
			final JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			int returnVal = fc.showOpenDialog(fc);
			String loadFile = null;
			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				loadFile = fc.getSelectedFile().getAbsolutePath();

				FilestreamSource fileStream = new FilestreamSource(loadFile);
				imgSrc = fileStream;
				thread = new TestFrameThread(videoPanel, imgSrc);

			}
		}
		if(src == stop) {
			if(imgSrc.closeConnection()) {
				System.out.println("Connection stopped!");
			}
		}

	}

	
	public static void startTestMainCV() {
		TestFrame frame = new TestFrame();
		frame.validate();
		frame.setVisible(true);
		
	}


}