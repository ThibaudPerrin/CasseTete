package casseTete;




import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

import casseTete.model.Grille;
import casseTete.model.Symbole;
import casseTete.view.CasePane;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainCasseTete extends Application {
	
	Grille g;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Init Model
		g = new Grille(3, 3);
		
		// gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();

        // permet de placer les diffrents boutons dans une grille
        GridPane gPane = new GridPane();
        gPane.setPrefSize(500, 500);
        gPane.setAlignment(Pos.CENTER);
        
        //Titre
        Text affichage = new Text("Casse TETE");
        affichage.setFont(Font.font("Verdana", 30));
        affichage.setFill(Color.WHITE);
        border.setTop(affichage);
        border.setAlignment(affichage,Pos.CENTER);
        
        
        
     // la vue observe les "update" du modèle, et réalise les mises à jour graphiques
        g.addObserver(new Observer() {

            @Override
            public void update(Observable o, Object arg) {
                // TODO
            }
        });
        
        
        
        //Initialisation
        for(int i = 0; i < g.getLongueur(); i++) {
        	for(int j = 0; j < g.getLargeur(); j++) {
        		
        		final int fColumn = i;
                final int fRow = j;
        		CasePane p = new CasePane(g.getTab()[i][j]);
        		
        		p.setOnDragDetected(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {

                        Dragboard db = p.startDragAndDrop(TransferMode.ANY);
                        ClipboardContent content = new ClipboardContent();       
                        content.putString(""); // non utilisé actuellement
                        db.setContent(content);
                        event.consume();
                        g.startDD(fColumn, fRow);
                    }
                });

                p.setOnDragEntered(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
                        
                        g.parcoursDD(fColumn, fRow);
                        event.consume();
                    }
                });
                
                p.setOnDragDone(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
                        
                        // attention, le setOnDragDone est déclenché par la source du Drag&Drop
                        
                        g.stopDD(fColumn, fRow);
                        
                    }
                });
        		
        		
        		
        		
        		
        		
        		gPane.add(p, i, j);
        	}
        }
        
        
        
        //Options
        gPane.setGridLinesVisible(true);
        border.setCenter(gPane);
        Scene scene = new Scene(border, Color.ROSYBROWN);

        //Options
        primaryStage.setTitle("Test");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
	}

}
