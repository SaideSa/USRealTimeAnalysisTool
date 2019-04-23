package gui;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FirstClass extends Application {
	   public void start(Stage s) throws Exception {
	    	s.setTitle("USRealTimeAnalysisTool");		
	    	Pane p = new Pane();					
	    	s.setScene(new Scene(p, 900, 500));		
	    	
	    	
	    	Button berechnen = new Button("Berechnen");
	    	p.getChildren().add(berechnen);
	    	berechnen.setText("Berechnen");
	    	
	    	berechnen.setLayoutX(500);
	    	berechnen.setLayoutY(50);
	    	
	    	Label ergebnis = new Label("Ergebnis:");
	    	p.getChildren().add(ergebnis);
	    	ergebnis.setLayoutX(500);		// X/Y Coodis angeben
	    	ergebnis.setLayoutY(120);
	    	
	    	TextField tf = new TextField();
	    	p.getChildren().add(tf);
	    	tf.setLayoutX(600);
	    	tf.setLayoutY(120);
	    	
	    	Button reset = new Button("Reset");
	    	p.getChildren().add(reset);
	    	reset.setLayoutX(500);
	    	reset.setLayoutY(80);
	    	
	    
	    	
	    	
	    	s.show();
	   }	
}
