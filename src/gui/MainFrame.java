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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainFrame extends Application {
	
	ImageView iv = new ImageView();
	Pane panel = new Pane(iv);
	BorderPane bp = new BorderPane();
	
	public String savelocation;
		
    public void start(Stage s) throws Exception {
    	// Koordianten d. ImageViews & Punktesetzung
    	iv.setLayoutX(20);
        iv.setLayoutY(50);
        iv.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        	public void handle(MouseEvent e) {     
        	}
        });
        
        // Startbutton zum Start d. Echtzeitdarstellung
        Button start = new Button("Start");
        start.setLayoutX(25);
        start.setLayoutY(450);
        start.setPrefWidth(80);
		start.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println("Echtzeitdarstellung gestartet!");
			} 
		});
		   
		// Stopbutton zum Beenden d. Echtzeitdarstellung
        Button stop = new Button("Stop");
        stop.setLayoutX(125);
        stop.setLayoutY(450);
        stop.setPrefWidth(80);
		stop.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println("Echtzeitdarstellung gestoppt!");
			} 
		});
		
		// Resetbutton, um gesetzte Punkte für die Abstandberechung zu resetten
        Button reset = new Button("Reset");
        reset.setLayoutX(600);
        reset.setLayoutY(100);
        reset.setPrefWidth(80);
        reset.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		System.out.println("Gesetzte Punkte resettet!");
			} 
		});
		
        // Berechnung d. Abstands
        Button calc = new Button("Berechnen");
        calc.setLayoutX(600);
        calc.setLayoutY(140);
        calc.setPrefWidth(80);
        calc.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		System.out.println("Abstand berechnet!");
			} 
		});
        
        // zeigt Ergebnis d. Abstandberechnung an
        Text erg = new Text("Abstand: ");
        erg.setLayoutX(600);
        erg.setLayoutY(200);
        erg.setFont(new Font(16));
       
        // friert Echtzeitdarstellung ein, (um ein einzelnes USBild zu speichern)
        Button freeze = new Button("Freeze");
        freeze.setLayoutX(225);
        freeze.setLayoutY(450);
        freeze.setPrefWidth(80);
        freeze.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		OtherFrames.freezeWindow();
        		System.out.println("Fenster eingefroren!");
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
    						System.out.println("Speicherort zu \"" + savelocation + "\" geändert!");
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
                					System.out.println("Bild erfolgreich auf \"" + file.getAbsolutePath() + "\" gespeichert!");
                				} catch (Exception ex) {
                					System.out.println("Bild konnte nicht gespeichert werden!");
                				}	
                			}
                		} else {
                			File file = new File(savelocation);
                			try {
								ImageIO.write(SwingFXUtils.fromFXImage(iv.getImage(), null), "png", file);
								System.out.println("Bild erfolgreich auf \"" + savelocation + "\" gespeichert!");
							} catch (Exception ex) {
								System.out.println("Bild konnte nicht gespeichert werden!");
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
                 			System.out.println("Bild enfernt!");
                 		} else {
                 			System.out.println("Es gibt kein Bild zum entfernen!");
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
                			//Image i = new Image(file.getAbsolutePath());
                			iv.setImage(i);
                			System.out.println("Bild erfolgreich geladen!");
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
        panel.getChildren().addAll(start, stop, reset, calc, erg, freeze);
        s.setResizable(false);
        s.setTitle("USRealTimeAnalysisTool");        
        s.setScene(new Scene(bp, 1000, 600));    
        s.show();
           
    }
}