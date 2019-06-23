package gui;

import java.io.File;
import javax.imageio.ImageIO;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainFrame extends Application {
	
	ImageView iv = new ImageView();
	Pane panel = new Pane(iv);
	BorderPane bp = new BorderPane();
	
	String savelocation;
	boolean freezestatus = false;
	boolean connectionstatus = false;
		
    public void start(Stage s) throws Exception {
    	// Koordianten d. ImageViews & Punktesetzung
    	iv.setLayoutX(20);
        iv.setLayoutY(50);
        iv.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        	public void handle(MouseEvent e) {     
        	}
        });
        
        // TextArea als Konsole mit Label
        TextArea ta = new TextArea();
        ta.setLayoutX(710);
        ta.setLayoutY(60);
        ta.setPrefWidth(275);
        ta.setPrefHeight(425);
        ta.setEditable(false);
        
        Text console = new Text("Konsole:");
        console.setLayoutX(710);
        console.setLayoutY(50);
        console.setFont(new Font(16));
        
        //zeigt an, ob es eine Verbindung zum USGerät gibt (provisorisch)
        Rectangle connection = new Rectangle(30,30);
        connection.setLayoutX(20);
        connection.setLayoutY(10);
        connection.setStroke(Color.BLACK);
        Text connecttxt = new Text();
        connecttxt.setLayoutX(60);
        connecttxt.setLayoutY(30);
        connecttxt.setFont(new Font(16));
        
        if(connectionstatus == false) {
        	connection.setFill(Color.RED);
        	connecttxt.setText("Keine Verbindung...");
        } else {
        	connection.setFill(Color.GREEN);
        	connecttxt.setText("Verbunden!");
        }
        
        // Startbutton zum Start d. Echtzeitdarstellung
        Button start = new Button("Start");
        start.setLayoutX(25);
        start.setLayoutY(450);
        start.setPrefWidth(80);
		start.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				ta.appendText("Echtzeitdarstellung gestartet!\n");
			} 
		});
		   
		// Stopbutton zum Beenden d. Echtzeitdarstellung
        Button stop = new Button("Stop");
        stop.setLayoutX(125);
        stop.setLayoutY(450);
        stop.setPrefWidth(80);
		stop.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				ta.appendText("Echtzeitdarstellung gestoppt!\n");
			} 
		});
		
		// Resetbutton, um gesetzte Punkte für die Abstandberechung zu resetten
        Button reset = new Button("Reset");
        reset.setLayoutX(580);
        reset.setLayoutY(120);
        reset.setPrefWidth(80);
        reset.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		ta.appendText("Gesetzte Punkte resettet!\n");
			} 
		});
		
        // Berechnung d. Abstands
        Button calc = new Button("Berechnen");
        calc.setLayoutX(580);
        calc.setLayoutY(80);
        calc.setPrefWidth(80);
        calc.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		ta.appendText("Abstand berechnet\n");
			} 
		});
        
        Text abst = new Text("Abstand:");
        abst.setLayoutX(580);
        abst.setLayoutY(50);
        abst.setFont(new Font(16));
        
        // zeigt Ergebnis d. Abstandberechnung an
        Text erg = new Text("Ergebnis:");
        erg.setLayoutX(580);
        erg.setLayoutY(190);
        erg.setFont(new Font(14));
        
        TextField tf = new TextField("");
        tf.setLayoutX(580);
        tf.setLayoutY(200);
        tf.setEditable(false);
        tf.setPrefWidth(80);
        
       
        // friert Echtzeitdarstellung ein, (um ein einzelnes USBild zu speichern)
        Button freeze = new Button("Freeze");
        freeze.setLayoutX(225);
        freeze.setLayoutY(450);
        freeze.setPrefWidth(80);
        freeze.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		if(freezestatus == false) {
        			//OtherFrames.startFreeze();
        			ta.appendText("Fenster eingefroren!\n");
        			freezestatus = true;
        			freeze.setStyle("-fx-background-color: #00ffff; ");
        			freeze.setText("Unfreeze");
        		} else {
        			ta.appendText("Fenster aufgetaut!\n");
        			freezestatus = false;
        			freeze.setStyle(null);
        			freeze.setText("Freeze");
        		}
			} 
		});
        
        MenuBar menuBar = new MenuBar();
        	Menu options = new Menu("Optionen");
        	Menu help = new Menu("Hilfe");
        	
    		// MenuItem zur Festlegung d. Speicherorts für zu speichernde USBilder
    			MenuItem saveloc = new MenuItem("Speicherort festlegen");
    			saveloc.setOnAction(new EventHandler<ActionEvent>() {
    				public void handle(ActionEvent e) {	
    				DirectoryChooser dc = new DirectoryChooser();
    				
    				File file = dc.showDialog(s);
    				if(file != null) {
    						savelocation = file.getAbsolutePath();
    						ta.appendText("Speicherort zu \"" + savelocation + "\" geändert!\n");
    					}
    				} 
    			});
       
        		// MenuItem zur Speicherung d. eingefrorenen USBildes (wenn Speicherort gesetzt)
        		MenuItem save = new MenuItem("Speichern");
                save.setOnAction(new EventHandler<ActionEvent>() {
                	public void handle(ActionEvent e) {
                		if(savelocation == null) {
                			FileChooser fc = new FileChooser();

                			FileChooser.ExtensionFilter png = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
                			FileChooser.ExtensionFilter jpg = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
                			fc.getExtensionFilters().addAll(jpg, png);
                		
                			File file = fc.showSaveDialog(s);
                			if(file != null) {                			
                				try {
                					ImageIO.write(SwingFXUtils.fromFXImage(iv.getImage(), null), "png", file);
                					ta.appendText("Bild erfolgreich auf \"" + file.getAbsolutePath() + "\" gespeichert!\n");
                				} catch (Exception ex) {
                					ta.appendText("Bild konnte nicht gespeichert werden!\n");
                				}	
                			}
                		} else {
                			File file = new File(savelocation);
                			try {
								ImageIO.write(SwingFXUtils.fromFXImage(iv.getImage(), null), "png", file);
								ta.appendText("Bild erfolgreich auf \"" + savelocation + "\" gespeichert!");
							} catch (Exception ex) {
								ta.appendText("Bild konnte nicht gespeichert werden!\n");
							}
                		}	
        			} 
        		});
        		save.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        		
        		// Einzelnes Bild auf ImageView kann entfernt werden
        		MenuItem clear = new MenuItem("Bild entfernen");
        		 clear.setOnAction(new EventHandler<ActionEvent>() {
                 	public void handle(ActionEvent e) {
                 		if(iv.getImage() != null) {
                 			iv.setImage(null);
                 			ta.appendText("Bild entfernt!\n");
                 		} else {
                 			ta.appendText("Es gibt kein Bild zum entfernen!\n");	
                 		}
                 	}
        		 });
        		 
        		// MenuItem zum Laden eines einzelnen USBildes
        		MenuItem load = new MenuItem("Laden");
                load.setOnAction(new EventHandler<ActionEvent>() {
                	public void handle(ActionEvent e) {
                		final FileChooser fc = new FileChooser();
                		
                		FileChooser.ExtensionFilter png = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
                		FileChooser.ExtensionFilter jpg = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
                		fc.getExtensionFilters().addAll(jpg, png);
                		
                		File file = fc.showOpenDialog(s);
                		if(file != null) {
                			Image i = new Image(file.toURI().toString());
                			iv.setImage(i);
                			ta.appendText("Bild erfolgreich geladen!\n");
                		} 
                	}
        		});
        		load.setAccelerator(KeyCombination.keyCombination("Ctrl+L"));
        		
        		
                //Bedienungshilfe?
        		MenuItem manual = new MenuItem("Bedienungshilfe");
        		manual.setAccelerator(KeyCombination.keyCombination("Ctrl+H"));
        		
        	options.getItems().addAll(save, new SeparatorMenuItem(), load, new SeparatorMenuItem(), saveloc, new SeparatorMenuItem(), clear);
        	help.getItems().add(manual);
        menuBar.getMenus().addAll(options, help);
         
        // Panel & Menubar auf Borderpane setzten, Elemente auf Panel setzten, Fenster anzeigen
        bp.setTop(menuBar);
        bp.setCenter(panel);
        panel.getChildren().addAll(start, stop, reset, calc, erg, freeze, ta, console, abst, tf, connection, connecttxt);
        s.setResizable(false);
        s.setTitle("USRealTimeAnalysisTool");        
        s.setScene(new Scene(bp, 1000, 600));    
        s.show();
           
    }
}