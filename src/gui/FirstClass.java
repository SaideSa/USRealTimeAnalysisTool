package gui;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FirstClass extends Application {
	   public void start(Stage s) throws Exception {
	    	s.setTitle("USRealTimeAnalysisTool");		
	    	Pane p = new Pane();					
	    	s.setScene(new Scene(p, 500, 300));		
	    	
	    	s.show();
	   }	
}
