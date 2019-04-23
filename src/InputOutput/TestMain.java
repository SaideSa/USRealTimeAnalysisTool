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
