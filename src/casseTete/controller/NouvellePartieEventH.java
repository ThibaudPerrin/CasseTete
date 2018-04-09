package casseTete.controller;

import casseTete.model.Grille;
import casseTete.model.Partie;
import casseTete.view.GrilleGPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class NouvellePartieEventH implements EventHandler<ActionEvent>{
	
	private Stage primaryStage;
	private Partie partie;
	private GrilleGPane gPane;
	
	public NouvellePartieEventH(Stage primaryStage, Partie partie, GrilleGPane gPane) {
		super();
		this.primaryStage = primaryStage;
		this.partie = partie;
		this.gPane = gPane;
	}
	
	public void handle(ActionEvent event) {
    	partie.nouvelleParti();
    	gPane.getChildren().clear();
    	gPane.initGrille(partie);
    	// la vue observe les "update" du modèle, et réalise les mises à jour graphiques
        partie.getGrille().addObserver(new GrilleUpdateObserver(primaryStage, partie, gPane));
    }
}
