package InputOutput;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileSystemView;

public class TestMain  {



  public static void main(String args[]) throws InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

    final Scanner scan = new Scanner(System.in);
    VideoCapturing vc = new VideoCapturing();

    JFrame frame = new JFrame("WebCam Capture - Image");
    JPanel main = new JPanel();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1000, 700);

    frame.setLayout(new BorderLayout());
    main.setLayout(new BorderLayout());
    //panel für tespanel
    TestPanel panel = new TestPanel();
    //TestPanel enthält das image 
    //frame.getContentPane().add(panel, BorderLayout.CENTER);
    main.add(panel, BorderLayout.CENTER);

    //Panel für die Buttons:
    JPanel button = new JPanel();
    JButton button1 = new JButton("start");
    JButton button2 = new JButton("lade");

    button.setLayout(new FlowLayout());
    button1.setSize(100,20);
    button2.setSize(100,20);
    button.add(button1);
    button.add(button2);

    main.add(button, BorderLayout.PAGE_END);

    //frame.add(button, BorderLayout.PAGE_END);
    frame.getContentPane().add(main);
	frame.validate();
	frame.setVisible(true);
		
		
//		 button1.addActionListener(new java.awt.event.ActionListener() {
//	            // Beim Drücken des Buttons wird actionPerformed aufgerufen
//	            public void actionPerformed(java.awt.event.ActionEvent e) {
//	            	
//	            	vc.capture();
//
//	    			// Loop for reading a BufferedImage and painting it on a panel;
//	    			while (vc.open) {
//	    				panel.setFace(vc.setBufImg());
//	    				panel.repaint();
//	              
//	               
//	            }
//	            }});
//		
//	button2.addActionListener(new java.awt.event.ActionListener() {
//        // Beim Drücken des Buttons wird actionPerformed aufgerufen
//        public void actionPerformed(java.awt.event.ActionEvent e) {
//        	
//        	final JFileChooser fc = 
//			          new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); // filechooser will open the explorer 
//			      int returnVal = fc.showOpenDialog(fc);
//			      String loadFile = null;
//			      if (returnVal == JFileChooser.APPROVE_OPTION) {
//			        loadFile = fc.getSelectedFile().getAbsolutePath();
//			        vc.capture(loadFile);
//			       
//			        while (vc.open) {
//						panel.setFace(vc.setBufImg());
//						 try {
//				              Thread.sleep(25);
//				            } catch (InterruptedException es) {
//				              // TODO Auto-generated catch block
//				              es.printStackTrace();
//				            }
//					
//						panel.repaint();
//						
//
//					}
//			
//		}
//          
//           
//        }
//        });
System.out.println("Bitte geben Sie '1' für Livestream oder '0' für Video/Bild  ein");
int checkStart = scan.nextInt();

//The datatransport starts after user interaction. Here a Scanner have replaced the subsequent Button;
if (checkStart == 1) {
	vc.capture();

	// Loop for reading a BufferedImage and painting it on a panel;
	while (vc.open) {
		panel.setFace(vc.readBufImg());
		panel.fps = vc.fps;
		panel.repaint();

	}
}else if( checkStart == 0) {
 	final JFileChooser fc = 
	          new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); // filechooser will open the explorer 
	      int returnVal = fc.showOpenDialog(fc);
	      String loadFile = null;
	      if (returnVal == JFileChooser.APPROVE_OPTION) {
	        loadFile = fc.getSelectedFile().getAbsolutePath();
	        vc.capture(loadFile);
	       
	        while (vc.open) {
				panel.setFace(vc.readBufImg());
				 try {
	              Thread.sleep(25);
		            } catch (InterruptedException es) {
		              // TODO Auto-generated catch block
		              es.printStackTrace();
		            }
				 panel.fps = vc.fps;
				 panel.repaint();
				

			}
}
}


}
}
