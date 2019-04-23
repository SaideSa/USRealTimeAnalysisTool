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
	    	s.setTitle("USRealTimeAnalysisTool");		
	    	Pane hauptfenster = new Pane();					
	    	s.setScene(new Scene(hauptfenster, 900, 500));		// Hauptfenster ist 900cm (X-Koordinate) x 500cm (Y-Koordinate) groß 
	    	
	    	
	    	Button berechnen = new Button("Berechnen"); // Button zur Berechnung der X & Y Koordinaten des US-Bildes
	    	hauptfenster.getChildren().add(berechnen);
	    	berechnen.setText("Berechnen");
	    	berechnen.setLayoutX(500); // Größe des Buttons 
	    	berechnen.setLayoutY(50);
	    	
	    	Label ergebnis = new Label("Ergebnis:"); // Erstellung des Labels "Ergebnis:" zur Ergebnisdarstellung der berechneten X und Y Koordinaten
	    	hauptfenster.getChildren().add(ergebnis);
	    	ergebnis.setLayoutX(500);		// X/Y Coodis angeben
	    	ergebnis.setLayoutY(120);
	    	
	    	TextField tf = new TextField(); // Ausgabefeld des Ergebnisses
	    	hauptfenster.getChildren().add(tf);
	    	tf.setLayoutX(600); // Größe des Ausgabefeldes
	    	tf.setLayoutY(120);
	    	
	    	Button reset = new Button("Reset"); // Reset-Button zum Zurücksetzen der Ergebnisse
	    	hauptfenster.getChildren().add(reset);
	    	reset.setLayoutX(500);
	    	reset.setLayoutY(80);
	    		    	
	    	s.show();
	   }	
}
