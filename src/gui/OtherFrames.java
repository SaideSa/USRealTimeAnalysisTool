package gui;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OtherFrames {

	// Methode, um neues Fenster zu öffnen
	public static void startFreeze() {
		Pane freezepanel = new Pane();
		Stage freezeStage = new Stage();
		Scene freezescene = new Scene (freezepanel, 500, 300);
		freezeStage.setTitle("Freeze");
		freezeStage.setScene(freezescene);
		freezeStage.show();
	}
}
