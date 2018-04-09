package casseTete;




import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

import casseTete.controller.GrilleUpdateObserver;
import casseTete.model.Grille;
import casseTete.model.Partie;
import casseTete.model.Symbole;
import casseTete.view.CasePane;
import casseTete.view.GrilleGPane;
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
	Partie partie;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Init Model
		g = new Grille(4, 4);
		partie = new Partie();
		
		// gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();

        // permet de placer les diffrents boutons dans une grille
        GrilleGPane gPane = new GrilleGPane();
        
        //Titre
        Text affichage = new Text("Casse TETE");
        affichage.setFont(Font.font("Verdana", 30));
        affichage.setFill(Color.WHITE);
        border.setTop(affichage);
        border.setAlignment(affichage,Pos.CENTER);
        
        
        
     // la vue observe les "update" du modèle, et réalise les mises à jour graphiques
        partie.getGrille().addObserver(new GrilleUpdateObserver(partie.getGrille(), gPane));
        
        
        
        //Initialisation
        gPane.initGrille(partie);
        
        
        //Options
        border.setCenter(gPane);
        Scene scene = new Scene(border, Color.ROSYBROWN);

        //Options
        primaryStage.setTitle("Test");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
	}

}
