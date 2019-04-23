package algorithm;

import java.awt.event.*;

import javax.swing.*;

public class FirstClass extends JFrame implements MouseListener{
	
	
	
	public FirstClass() {
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(null);
		this.setTitle("USRealTimeAnalysisTool");
		this.setResizable(false);
		this.setSize(800,500);
		this.addMouseListener(this);
	    init();
		
	}
	
	public void init() {
		}
	
	
	@Override
	public void mousePressed(MouseEvent evt) {
		Calculation.MouseListenerPressed(evt);
		}
	
	@Override
	public void mouseReleased(MouseEvent evt) {
		Calculation.MouseListenerReleased(evt);
	}

	@Override
	public void mouseClicked(MouseEvent evt) {}

	@Override
	public void mouseEntered(MouseEvent evt) {}

	@Override
	public void mouseExited(MouseEvent evt) {}

	
}
	
	


