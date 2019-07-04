package gui;

import java.awt.image.BufferedImage;
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
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import algorithm.DataManager;

public class MainFrame extends Application {
	
	int geraet;
	
	ImageView iv = new ImageView();
	Pane panel = new Pane(iv);
	BorderPane bp = new BorderPane();
	
	String savelocation;
	boolean freezestatus = false;
	boolean connectionstatus = false;
	boolean running = false;
	
	Timeline timeline = new Timeline();
	DataManager dMngr = new DataManager();
	
	Circle a = new Circle();
	Circle b = new Circle();
	Line l = new Line();
	
	int x1;
	int y1;
	int x2;
	int y2;
	double umrechnungX;
	double umrechnungY;

    public void start(Stage s) throws Exception {
    	
        // TextArea als Ausgabenfenster mit Label
        TextArea ta_output = new TextArea();
        ta_output .setLayoutX(810);
        ta_output .setLayoutY(80);
        ta_output .setPrefWidth(275);
        ta_output .setPrefHeight(450);
        ta_output .setEditable(false);
        
        Text label_output = new Text("Ausgaben:");
        label_output.setLayoutX(810);
        label_output.setLayoutY(65);
        label_output.setFont(new Font(16));
        
        // zeigt Ergebnis d. Abstandberechnung in Textfeld an mit Label
        TextField tf_result = new TextField("");
        tf_result.setLayoutX(680);
        tf_result.setLayoutY(220);
        tf_result.setEditable(false);
        tf_result.setPrefWidth(80);
        
        Text label_result = new Text("Ergebnis: (in mm)");
        label_result.setLayoutX(680);
        label_result.setLayoutY(210);
        label_result.setFont(new Font(14));
        
        //Liste mit Geräten
        ChoiceBox<Object> cb_device = new ChoiceBox<Object>();
        cb_device.getItems().addAll("Webcam", new Separator(), "externes Gerät");
        cb_device.getSelectionModel().selectFirst();
        cb_device.setLayoutX(230);
        cb_device.setLayoutY(540);
        cb_device.setPrefWidth(105);
        
        Text label_device = new Text("Gerät auswählen:");
        label_device.setLayoutX(230);
        label_device.setLayoutY(530);
        label_device.setFont(new Font(14));
        
        //Liste mit Ultraschallköpfen
        ChoiceBox<Object> cb_kopf = new ChoiceBox<Object>();
        cb_kopf.getItems().addAll("3S", new Separator(), "11L");
        cb_kopf.getSelectionModel().selectFirst();
        cb_kopf.setLayoutX(340);
        cb_kopf.setLayoutY(540);
        cb_kopf.setPrefWidth(105);
        
        Text label_kopf = new Text("Kopf auswählen:");
        label_kopf.setLayoutX(340);
        label_kopf.setLayoutY(530);
        label_kopf.setFont(new Font(14));
        
    	// ImageView mit Punktesetzung
    	iv.setLayoutX(20);
        iv.setLayoutY(50);
        iv.setFitWidth(640);
        iv.setFitHeight(500);
        iv.setPreserveRatio(true);
        iv.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        	public void handle(MouseEvent e) {
            	if(e.getButton().equals(MouseButton.PRIMARY)) {
     			   if(a.getRadius() == 3) {
     				  panel.getChildren().remove(a);
     			   }
     			   x1 = (int) e.getX()+20;
     			   y1 = (int) e.getY()+50;
     			   a.setCenterX(x1);
     			   a.setCenterY(y1);
     			   a.setRadius(3);
     			   a.setFill(Color.RED);
     			   a.setMouseTransparent(true);
     			   panel.getChildren().add(a);
     			   ta_output.appendText("Punkt 1 gesetzt!\n");
     			   
     		   } else { 
     			   if(b.getRadius() == 3) {
     				   	panel.getChildren().remove(b);
     			   }
     			   x2 = (int) e.getX()+20;
     			   y2 = (int) e.getY()+50;
     			   b.setCenterX(x2);
     			   b.setCenterY(y2);
     			   b.setRadius(3);
     			   b.setFill(Color.BLUE);
     			   b.setMouseTransparent(true);
     			   panel.getChildren().add(b);
     			   ta_output.appendText("Punkt 2 gesetzt!\n");
     		   }
     		   
     		  if((a.getRadius() == 3)&&(b.getRadius() == 3)) {  
     			  panel.getChildren().remove(l);
     			  l.setStartX(a.getCenterX());
     			  l.setStartY(a.getCenterY());
     			  l.setEndX(b.getCenterX());
     			  l.setEndY(b.getCenterY());
     			  l.setStroke(Color.WHITE);
     			  l.setMouseTransparent(true);
     			  panel.getChildren().add(l);
     		  }
        	}
        });
        
		// Resetbutton, um gesetzte Punkte zu resetten
        Button reset = new Button("Reset");
        reset.setLayoutX(680);
        reset.setLayoutY(140);
        reset.setPrefWidth(80);
        reset.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		if((a.getRadius() == 3)||(b.getRadius() == 3)) {
        			ta_output.appendText("Gesetzte Punkte resettet!\n");
        			panel.getChildren().remove(a);
        			panel.getChildren().remove(b);
        			panel.getChildren().remove(l);
        			a.setRadius(0);
        			b.setRadius(0);
        			tf_result.setText("");
        		} else {
        			ta_output.appendText("Die Punkte können nicht resettet werden,\nweil keine Punkte gesetzt wurden!\n");
        		}
			} 
		});
        
        // Button friert Echtzeitdarstellung ein
        Button freeze = new Button("Freeze");
        freeze.setLayoutX(125);
        freeze.setLayoutY(540);
        freeze.setPrefWidth(80);
        freeze.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		if(running == true) {
        			if(freezestatus == false) {
        				ta_output.appendText("Fenster eingefroren!\n");
        				freezestatus = true;
        				freeze.setText("Unfreeze");
        				stopUpdating();
        				
        			} else {
        				ta_output.appendText("Fenster aufgetaut!\n");
        				freezestatus = false;
        				freeze.setText("Freeze");
        				startUpdating(geraet);
        			}
        			
        		} else {
        			ta_output.appendText("Das Bild kann nicht eingefroren werden,\nweil die Echtzeitdarstellung nicht gestartet ist!\n");
        		}
			} 
		});
        
        // Start/Stoppbutton zum Start/Stopp d. Echtzeitdarstellung
        Button startstop = new Button("Start");
        startstop.setLayoutX(20);
        startstop.setLayoutY(540);
        startstop.setPrefWidth(80);
		startstop.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if(running == false) {
					if(cb_device.getValue()=="Webcam") {
						setGeraet(0);
					} else if (cb_device.getValue()=="externes Gerät") {
						setGeraet(1);
					}
					startstop.setText("Stop");
					ta_output.appendText("Echtzeitdarstellung gestartet!\n");
					running = true;
					startUpdating(geraet);
					
				} else {
					startstop.setText("Start");
					ta_output.appendText("Echtzeitdarstellung gestoppt!\n");
					running = false;
					stopUpdating();
					iv.setImage(null);
        			panel.getChildren().remove(a);
        			panel.getChildren().remove(b);
        			panel.getChildren().remove(l);
        			a.setRadius(0);
        			b.setRadius(0);
        			tf_result.setText("");
					if (freezestatus == true) {
						freezestatus = false;
	        			freeze.setText("Freeze");
					}
				}
			} 
		});
 
        // Berechnung d. Abstands mit Label
        Button calc = new Button("Berechnen");
        calc.setLayoutX(680);
        calc.setLayoutY(100);
        calc.setPrefWidth(80);
        calc.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
            	if((a.getRadius() == 3)&&(b.getRadius() == 3)) {
            		if(cb_kopf.getValue()=="3S") {
            			setKopf(0.256, 0.274);
            		} else if(cb_kopf.getValue()=="11L") {
            			setKopf(0.080, 0.080);
            		}
            		int erg = dMngr.getDistanceXY(x1, y1, x2, y2, umrechnungX, umrechnungY);
            		tf_result.setText(Integer.toString(erg));
            		ta_output.appendText("Abstand berechnet!\n");
            	} else {
            		ta_output.appendText("Abstand konnte nicht berechnet werden,\nweil nicht beide Punkte gesetzt wurden!\n");
            	}
			} 
		});
        
        Text label_distance = new Text("Abstand:");
        label_distance.setLayoutX(680);
        label_distance.setLayoutY(65);
        label_distance.setFont(new Font(16));
        
        // MenuBar 
        MenuBar menuBar = new MenuBar();
        	Menu options = new Menu("Optionen");
        	Menu help = new Menu("Hilfe");
        	
    		// MenuItem zur Festlegung d. Speicherorts für zu speichernde USBilder
    			MenuItem saveloc = new MenuItem("Speicherort festlegen");
    			/*saveloc.setOnAction(new EventHandler<ActionEvent>() {
    				public void handle(ActionEvent e) {	
    				DirectoryChooser dc = new DirectoryChooser();
    				File file = dc.showDialog(s);
    				if(file != null) {
    						savelocation = file.getAbsolutePath();
    						ta_output.appendText("Speicherort zu \"" + savelocation + "\" geändert!\n");
    					}
    				} 
    			});
    			*/
       
        		// MenuItem zur Speicherung d. eingefrorenen USBildes (wenn Speicherort gesetzt)
        		MenuItem save = new MenuItem("Speichern");
                save.setOnAction(new EventHandler<ActionEvent>() {
                	public void handle(ActionEvent e) {
                		if(savelocation == null) {
                			FileChooser fc = new FileChooser();
                			FileChooser.ExtensionFilter png = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
                			FileChooser.ExtensionFilter jpg = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
                			fc.getExtensionFilters().addAll(png, jpg);
                			File file = fc.showSaveDialog(s);
                			if(file != null) {                			
                				try {
                					ImageIO.write(SwingFXUtils.fromFXImage(iv.getImage(), null), "png", file);
                					ta_output.appendText("Bild erfolgreich auf \"" + file.getAbsolutePath() + "\" gespeichert!\n");
                				} catch (Exception ex) {
                					ta_output.appendText("Bild konnte nicht gespeichert werden!\n");
                				}	
                			}
                		} else {
                			File file = new File(savelocation);
                			try {
								ImageIO.write(SwingFXUtils.fromFXImage(iv.getImage(), null), "png", file);
								ta_output.appendText("Bild erfolgreich auf \"" + savelocation + "\" gespeichert!");
							} catch (Exception ex) {
								ta_output.appendText("Bild konnte nicht gespeichert werden!\n");
							}
                		}	
        			} 
        		});
        		save.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        		 
        		// MenuItem zum Laden eines einzelnen USBildes
        		MenuItem load = new MenuItem("Laden");
                load.setOnAction(new EventHandler<ActionEvent>() {
                	public void handle(ActionEvent e) {
                		final FileChooser fc = new FileChooser();
                		FileChooser.ExtensionFilter png = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
                		FileChooser.ExtensionFilter jpg = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
                		fc.getExtensionFilters().addAll(png, jpg);
                		File file = fc.showOpenDialog(s);
                		if(file != null) {
                			Image i = new Image(file.toURI().toString());
                			iv.setImage(i);
                			ta_output.appendText("Bild erfolgreich geladen!\n");
                		} 
                	}
        		});
        		load.setAccelerator(KeyCombination.keyCombination("Ctrl+L"));
        		
                //Bedienungshilfe
        		MenuItem manual = new MenuItem("Bedienungshilfe");
        		manual.setAccelerator(KeyCombination.keyCombination("Ctrl+H"));
        		
        	options.getItems().addAll(save, new SeparatorMenuItem(), load, new SeparatorMenuItem(), saveloc);
        	help.getItems().add(manual);
        menuBar.getMenus().addAll(options, help);
        
        // Verbindungsanzeige mit Label
        Rectangle connection = new Rectangle(30,30);
        connection.setLayoutX(20);
        connection.setLayoutY(10);
        connection.setStroke(Color.BLACK);
        
        Text label_connection = new Text();
        label_connection.setLayoutX(60);
        label_connection.setLayoutY(30);
        label_connection.setFont(new Font(16));
        
        if(connectionstatus == false) {
        	connection.setFill(Color.RED);
        	label_connection.setText("Keine Verbindung...");
        } else {
        	connection.setFill(Color.GREEN);
        	label_connection.setText("Verbunden!");
        }
        
        /* unbenutzer code
         * Labels für die fps/Latenzanzeigen
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
        */	

        // Panel & Menubar auf Borderpane setzten, Elemente auf Panel setzten, Fenster anzeigen
        bp.setTop(menuBar);
        bp.setCenter(panel);
        panel.getChildren().addAll(ta_output, label_output, tf_result, label_result, cb_device, label_device, cb_kopf, label_kopf, reset, freeze, startstop, calc, label_distance, connection, label_connection);
        s.setResizable(false);
        s.setTitle("USRealTimeAnalysisTool");        
        s.setScene(new Scene(bp, 1120, 600));    
        s.show(); 
    }
    
    private void setGeraet(int x) {
    	geraet = x;
    	
    }
    
    private void setKopf(double x, double y) {
    	umrechnungX = x;
    	umrechnungY = y;
    	
   }
    
    private void startUpdating(int x){
    	dMngr.openConnection(x);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(100),
                        event -> {
                            update();
                        })
        );
        timeline.play();
    }
    
    private void stopUpdating(){
    	dMngr.closeConnection();
    	timeline.stop();
    }
    
    private void update() {
        System.out.println("Update");
        BufferedImage bufImg = dMngr.readBufImg();
        Image image = SwingFXUtils.toFXImage(bufImg, null);
        iv.setImage(image);
    }
}