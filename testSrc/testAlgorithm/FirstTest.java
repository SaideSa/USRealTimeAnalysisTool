package testAlgorithm;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import javax.swing.filechooser.FileSystemView;
import testIO.TestPanel;

 

public class FirstTest extends JFrame implements ActionListener, MouseListener  {
	
	JPanel main = new JPanel(); //this panel will contain a panel for buttons and another panel for painting the frames
	
	JPanel buttonPanel = new JPanel();
	JButton startLive = new JButton("Start LiveStram");
	JButton startFile = new JButton("Load File");
	JButton stop = new JButton("Stop");
	JButton berechnen = new JButton("Berechnen");
	
	TestPanel videoPanel = new TestPanel();
	TestDataManagerThread thread;


	public FirstTest() {
	
		init();
	
	}
	
	public void init() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(1000, 700));

		main.setLayout(new BorderLayout());
		main.add(videoPanel, BorderLayout.CENTER);
		videoPanel.addMouseListener(this);
	
		/*Panel with four Buttons: startLive, startFile, stop, berechnen*/
		buttonPanel.setLayout(new FlowLayout());
		startLive.setSize(100, 20);
		startFile.setSize(100, 20);
		berechnen.setSize(100, 20);
		stop.setSize(100, 20);
		buttonPanel.add(startLive);
		buttonPanel.add(startFile);
		buttonPanel.add(stop);
		buttonPanel.add(berechnen);
		startLive.addActionListener(this);
		startFile.addActionListener(this);
		stop.addActionListener(this);
		berechnen.addActionListener(this);

		main.add(buttonPanel, BorderLayout.PAGE_END);
		
		this.setContentPane(main);;
		

	}

	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		// The datatransport starts after user interaction
		
		if (src == startLive) {
			thread = new TestDataManagerThread(videoPanel);
			this.thread.startThread(null);
		}
		if (src == startFile) {
			thread = new TestDataManagerThread(videoPanel);
			// filechooser will open the explorer;
			final JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			int returnVal = fc.showOpenDialog(fc);
			String loadFile = null;
			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				loadFile = fc.getSelectedFile().getAbsolutePath();
			}
			this.thread.startThread(loadFile);
		}
		if(src == stop) {
			thread.getDataManager().stop();
		}
		if(src==berechnen) {
			thread.getDataManager().getDistanceBox();
		}

	}

	
	public static void startTestMainCV() {
		FirstTest frame = new FirstTest();
		frame.validate();
		frame.setVisible(true);
		
	}

	
	public void mouseReleased(MouseEvent e) {
				
		//thread.getDataManager().MouseListenerReleased(e);
	}
	
	
	public void mousePressed(MouseEvent evt) {
		System.out.println("FirstTest.MousePressed");
		thread.getDataManager().MouseListenerPressed(evt);
		
	}
	
	
	
	public void mouseClicked(MouseEvent e) {}
	

	public void mouseEntered(MouseEvent e) {}

	
	public void mouseExited(MouseEvent e) {}

	


}
