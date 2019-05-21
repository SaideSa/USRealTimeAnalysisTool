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
          // Image i = SwingFXUtils.toFXImage(Test.us(), null);
           //ImageView iv = new ImageView(i);
           
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
           Pane hauptfenster = new Pane();
           //iv.setLayoutX(20);
           //iv.setLayoutY(50);
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
           Button b1 = new Button("Freeze");
       	hauptfenster.getChildren().add(b1);
       	b1.setText("Freeze");
       	String so= b1.getText();
       	b1.setLayoutX(600);
       	b1.setLayoutY(600);
       	b1.setOnAction(new EventHandler<ActionEvent>() {
             	 
       	     
               public void handle(ActionEvent event) {
               	

              	 
                   StackPane secondaryLayout = new StackPane();

    
                   Scene secondScene = new Scene(secondaryLayout, 500, 300);
    
                   // New window (Stage)
                   Stage newWindow = new Stage();
                   newWindow.setTitle("Freeze");
                   newWindow.setScene(secondScene);
    
    
                   newWindow.show();
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
          
           // Punkt A - Label 
           Label punktA = new Label ("Punkt A : ");
           hauptfenster.getChildren().add(punktA);
           punktA.setLayoutX(500);
           punktA.setLayoutY(160);
           
           // X- Label 
           Label x = new Label ("X:");
           hauptfenster.getChildren().add(x);
           x.setLayoutX(500);
           x.setLayoutY(200);
           
           //X-Textfeld
           TextField tfx = new TextField();
           hauptfenster.getChildren().add(tfx);
           tfx.setLayoutX(560);
           tfx.setLayoutY(200);

           // Y - Label 
           Label y = new Label ("Y:");
           hauptfenster.getChildren().add(y);
           y.setLayoutX(500);
           y.setLayoutY(240);
           
           //Y-Textfeld
           TextField tfy = new TextField();
           hauptfenster.getChildren().add(tfy);
           tfy.setLayoutX(560);
           tfy.setLayoutY(240);
           
           // Punkt B - Label 
           Label punktB = new Label ("Punkt B : ");
           hauptfenster.getChildren().add(punktB);
           punktB.setLayoutX(500);
           punktB.setLayoutY(300);
           
           // X- Label 
           Label xb = new Label ("X:");
           hauptfenster.getChildren().add(xb);
           xb.setLayoutX(500);
           xb.setLayoutY(380);
           
           //X-Textfeld
           TextField tfxb = new TextField();
           hauptfenster.getChildren().add(tfxb);
           tfxb.setLayoutX(560);
           tfxb.setLayoutY(380);

           // Y - Label 
           Label yb = new Label ("Y:");
           hauptfenster.getChildren().add(yb);
           yb.setLayoutX(500);
           yb.setLayoutY(420);
           
           //Y-Textfeld
           TextField tfyb = new TextField();
           hauptfenster.getChildren().add(tfyb);
           tfyb.setLayoutX(560);
           tfyb.setLayoutY(420);
        
           
           // Hauptfenster (900x500) mit Titel wird dargestellt
           s.setTitle("USRealTimeAnalysisTool");        
           s.setScene(new Scene(bp, 1200, 800));    
           s.show();
           
    }
}