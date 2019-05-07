package gui;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;



public class MainFrame extends Application {
       
    
    public void start(Stage s) throws Exception {
        
           //Methode in Testklasse liefert BufferedImage, das in JavaFX Image i konvertiert wird, ImageView erstellt
           Image i = SwingFXUtils.toFXImage(Test.us(), null);
           ImageView iv = new ImageView(i);
           
           //Menubar mit Optionen wird erstellt
           MenuBar menuBar = new MenuBar();
           Menu options = new Menu("Einstellungen");
           Menu help = new Menu("Hilfe");
            
           MenuItem save = new MenuItem("Speichern");
           save.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
           MenuItem load = new MenuItem("Laden");
           load.setAccelerator(KeyCombination.keyCombination("Ctrl+L"));
           MenuItem file = new MenuItem("Speicherort festlegen");
           MenuItem manual = new MenuItem("Bedienungshilfe");
           manual.setAccelerator(KeyCombination.keyCombination("Ctrl+H"));
         
           
           options.getItems().addAll(save, new SeparatorMenuItem(), load, new SeparatorMenuItem(), file);
           help.getItems().add(manual);
           menuBar.getMenus().addAll(options, help);
           
           // auf Panel wird UsBild gesetzt mit X,Y-Koordinaten, auf BorderPane wird menuBar & Panel gesetzt
           Pane hauptfenster = new Pane(iv);
           iv.setLayoutX(20);
           iv.setLayoutY(50);
           BorderPane bp = new BorderPane();
           bp.setTop(menuBar);
           bp.setCenter(hauptfenster);
           
         
           // Berechnen-Button mit ActionHandler erstellt & auf Panel mit X,Y-Koordinaten platziert
           Button berechnen = new Button("Berechnen");
           hauptfenster.getChildren().add(berechnen);
           berechnen.setLayoutX(500);  
           berechnen.setLayoutY(50);
           berechnen.setOnAction(new EventHandler<ActionEvent>() {
           public void handle(ActionEvent e) {
                   
               }
           });
           
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
           
           // Reset-Button mit ActionHandler erstellt & auf Panel mit X,Y-Koordinaten platziert
           Button reset = new Button("Reset");
           hauptfenster.getChildren().add(reset);
           reset.setLayoutX(500);
           reset.setLayoutY(80);
          
        
           
           // Hauptfenster (900x500) mit Titel wird dargestellt
           s.setTitle("USRealTimeAnalysisTool");        
           s.setScene(new Scene(bp, 1200, 800));    
           s.show();
           
    }
}