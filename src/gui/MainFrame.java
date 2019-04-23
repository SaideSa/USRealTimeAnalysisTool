package gui;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainFrame extends Application {
	
	   public void start(Stage s) throws Exception {
		   
		    // Hauptfenster (900x500) mit Titel wird erstellt, Panel drauf platziert
	    	s.setTitle("USRealTimeAnalysisTool");		
	    	Pane hauptfenster = new Pane();					
	    	s.setScene(new Scene(hauptfenster, 900, 500));	
	    	
	    	// Berechnen-Button erstellt & auf Panel mit X,Y-Koordinaten platziert
	    	Button berechnen = new Button("Berechnen"); 
	    	hauptfenster.getChildren().add(berechnen);
	    	berechnen.setLayoutX(500);  
	    	berechnen.setLayoutY(50);
	    	
	    	// Ergebnis-Label erstellt & auf Panel mit X,Y-Koordinaten platziert
	    	Label ergebnis = new Label("Ergebnis:"); 
	    	hauptfenster.getChildren().add(ergebnis);
	    	ergebnis.setLayoutX(500);		
	    	ergebnis.setLayoutY(120);
	    	
	    	// Ergebnis-Textfeld erstellt & auf Panel mit X,Y-Koordinaten platziert
	    	TextField tf = new TextField(); 
	    	hauptfenster.getChildren().add(tf);
	    	tf.setLayoutX(560); 
	    	tf.setLayoutY(120);
	    	
	    	// Reset-Button erstellt & auf Panel mit X,Y-Koordinaten platziert
	    	Button reset = new Button("Reset"); 
	    	hauptfenster.getChildren().add(reset);
	    	reset.setLayoutX(500);
	    	reset.setLayoutY(80);
	    	
	    	// Hauptfenster wird angezeigt
	    	s.show();
	   }	
}
