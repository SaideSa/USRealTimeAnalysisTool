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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import algorithm.*;

public class MainFrame extends Application {
	
	ImageView iv = new ImageView();
	Pane panel = new Pane(iv);
	BorderPane bp = new BorderPane();
	
	String savelocation;
	boolean freezestatus = false;
	boolean connectionstatus = false;
	boolean running = false;
	
	Circle a = new Circle();
	Circle b = new Circle();
	Line l = new Line();
		
    public void start(Stage s) throws Exception {
        // TextArea als Konsole mit Label
        TextArea ta = new TextArea();
        ta.setLayoutX(710);
        ta.setLayoutY(60);
        ta.setPrefWidth(275);
        ta.setPrefHeight(425);
        ta.setEditable(false);
        
    	// Koordianten d. ImageViews & Punktesetzung
    	iv.setLayoutX(20);
        iv.setLayoutY(50);
        iv.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        	public void handle(MouseEvent e) {
        		if(e.getButton().equals(MouseButton.PRIMARY)) {
				   panel.getChildren().remove(a);
				   if(a.getRadius() == 3) {
					  //DistanceMeasurement.dinge.remove(0);
				   }
				   
				   int x1 = (int) e.getX()+20;
				   int y1 = (int) e.getY()+50;
				   a.setCenterX(x1);
				   a.setCenterY(y1);
				   a.setRadius(3);
				   a.setFill(Color.RED);
				   panel.getChildren().add(a);
				   ta.appendText("Punkt 1 gesetzt!\n");
				  
				   //DistanceMeasurement.dinge.add(0, new Box(x1,y1));
				   
			   } else {
				   panel.getChildren().remove(b);
				   if(b.getRadius() == 3) {
					  //DistanceMeasurement.dinge.remove(1); 
				   }
				   
				   int x2 = (int) e.getX()+20;
				   int y2 = (int) e.getY()+50;
				   b.setCenterX(x2);
				   b.setCenterY(y2);
				   b.setRadius(3);
				   b.setFill(Color.BLUE);
				   panel.getChildren().add(b);
				   ta.appendText("Punkt 2 gesetzt!\n");
				   //DistanceMeasurement.dinge.add(1, new Box(x2, y2));
			   }
			   
			  if((a.getRadius() == 3)&&(b.getRadius() == 3)) {  
				  panel.getChildren().remove(l);
				  l.setStartX(a.getCenterX());
				  l.setStartY(a.getCenterY());
				  l.setEndX(b.getCenterX());
				  l.setEndY(b.getCenterY());
				  l.setStroke(Color.WHITE);
				  panel.getChildren().add(l);
			  }
        	}
        });
        

        Text output = new Text("Ausgaben:");
        output.setLayoutX(710);
        output.setLayoutY(50);
        output.setFont(new Font(16));
        
        //zeigt an, ob es eine Verbindung zum USGer�t gibt (provisorisch)
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
        
        // Labels f�r die fps/Latenzanzeigen
        Text fps = new Text("fps: ");
        fps.setLayoutX(25);
        fps.setLayoutY(65);
        fps.setFont(new Font(14));
        fps.setFill(Color.WHITE);
        fps.setVisible(false);
        Text latency = new Text(" / latenz: " + "m/s");
        latency.setLayoutX(80);
        latency.setLayoutY(65);
        latency.setFont(new Font(14));
        latency.setFill(Color.WHITE);
        latency.setVisible(false);
        
        // Start/Stoppbutton zum Start/Stopp d. Echtzeitdarstellung
        Button startstop = new Button("Start");
        startstop.setLayoutX(25);
        startstop.setLayoutY(450);
        startstop.setPrefWidth(80);
		startstop.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if(running == false) {
					startstop.setText("Stop");
					ta.appendText("Echtzeitdarstellung gestartet!\n");
					running = true;
				} else {
					startstop.setText("Start");
					ta.appendText("Echtzeitdarstellung gestoppt!\n");
					running = false;
				}
			} 
		});
		   
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
        
		// Resetbutton, um gesetzte Punkte f�r die Abstandberechung zu resetten
        Button reset = new Button("Reset");
        reset.setLayoutX(580);
        reset.setLayoutY(120);
        reset.setPrefWidth(80);
        reset.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		ta.appendText("Gesetzte Punkte resettet!\n");
        		panel.getChildren().remove(a);
        		panel.getChildren().remove(b);
        		panel.getChildren().remove(l);
 			   	a.setRadius(0);
 			   	b.setRadius(0);
 			   	//DistanceMeasurement.dinge.clear();
 			   	ta.setText("");
			} 
		});
		
        // Berechnung d. Abstands
        Button calc = new Button("Berechnen");
        calc.setLayoutX(580);
        calc.setLayoutY(80);
        calc.setPrefWidth(80);
        calc.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		ta.appendText("Abstand berechnet!\n");
            	if((a.getRadius() == 3)&&(b.getRadius() == 3)) { 
            		//int ergs = DistanceMeasurement.getDistanceBox();
            		
            		int tst = 222;
            		erg.setText(Integer.toString(tst));
            		
            	} 
			} 
		});
        
        Text abst = new Text("Abstand:");
        abst.setLayoutX(580);
        abst.setLayoutY(50);
        abst.setFont(new Font(16));
        

        
        // friert Echtzeitdarstellung ein, (um ein einzelnes USBild zu speichern)
        Button freeze = new Button("Freeze");
        freeze.setLayoutX(125);
        freeze.setLayoutY(450);
        freeze.setPrefWidth(80);
        freeze.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		if(freezestatus == false) {
        			//OtherFrames.startFreeze();
        			ta.appendText("Fenster eingefroren!\n");
        			freezestatus = true;
        			freeze.setText("Unfreeze");
        		} else {
        			ta.appendText("Fenster aufgetaut!\n");
        			freezestatus = false;
        			freeze.setText("Freeze");
        		}
			} 
		});
        
        MenuBar menuBar = new MenuBar();
        	Menu options = new Menu("Optionen");
        	Menu help = new Menu("Hilfe");
        	
    		// MenuItem zur Festlegung d. Speicherorts f�r zu speichernde USBilder
    			MenuItem saveloc = new MenuItem("Speicherort festlegen");
    			saveloc.setOnAction(new EventHandler<ActionEvent>() {
    				public void handle(ActionEvent e) {	
    				DirectoryChooser dc = new DirectoryChooser();
    				
    				File file = dc.showDialog(s);
    				if(file != null) {
    						savelocation = file.getAbsolutePath();
    						ta.appendText("Speicherort zu \"" + savelocation + "\" ge�ndert!\n");
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
        panel.getChildren().addAll(startstop, reset, calc, erg, freeze, ta, output, abst, tf, connection, connecttxt, fps, latency);
        s.setResizable(false);
        s.setTitle("USRealTimeAnalysisTool");        
        s.setScene(new Scene(bp, 1000, 600));    
        s.show();
           
    }
}