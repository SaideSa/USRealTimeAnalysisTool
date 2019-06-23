package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OtherFrames {
	
	// Methode, um neues Fenster zu öffnen
	public static void freezeWindow() {
		Pane freezepanel = new Pane();
		Scene freezescene = new Scene (freezepanel, 500, 300);
			
		Stage freezeStage = new Stage();
		freezeStage.setTitle("Freeze");
		freezeStage.setScene(freezescene);
		freezeStage.show();
	}
}
