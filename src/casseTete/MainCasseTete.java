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
import casseTete.view.GrilleMenu;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
	
	private Grille g;
	private Partie partie;
	private GrilleGPane gPane;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Init Model
		partie = new Partie();
		
		// permet de placer les diffrents boutons dans une grille
        gPane = new GrilleGPane();
        
		// Create MenuBar
        GrilleMenu menuBar = new GrilleMenu(primaryStage, partie, gPane);
        
		// gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();

        
        
        //Titre
        Text affichage = new Text("Casse TETE");
        affichage.setFont(Font.font("Verdana", 30));
        affichage.setFill(Color.WHITE);
        border.setTop(affichage);
        border.setAlignment(affichage,Pos.CENTER);
        
        
        
        // la vue observe les "update" du modèle, et réalise les mises à jour graphiques
        partie.getGrille().addObserver(new GrilleUpdateObserver(primaryStage, partie, gPane));
        
        
        
        //Initialisation
        gPane.initGrille(partie);
        
        
        //Options
        border.setTop(menuBar);
        border.setCenter(gPane);
        Scene scene = new Scene(border, Color.ROSYBROWN);

        //Options
        primaryStage.setTitle("Casse T�te");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
	}

}
