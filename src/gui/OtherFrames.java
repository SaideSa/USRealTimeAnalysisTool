package gui;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
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
