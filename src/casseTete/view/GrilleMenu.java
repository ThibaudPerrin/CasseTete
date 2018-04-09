package casseTete.view;

import casseTete.controller.GrilleUpdateObserver;
import casseTete.model.Partie;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;


public class GrilleMenu extends MenuBar{
	

	private Partie partie;
	private GrilleGPane gPane;
	
	public GrilleMenu(Partie partie, GrilleGPane gPane) {
		super();
		this.partie = partie;
		this.gPane = gPane;
		initGMenuBar();
	}
	
	public void initGMenuBar() {
		 // Create menus
        Menu fileMenu = new Menu("File");
        //Menu editMenu = new Menu("Edit");
        Menu helpMenu = new Menu("Help");
        
     // Create MenuItems
        MenuItem newGame = new MenuItem("Nouvelle Partie");
        //MenuItem openFileItem = new MenuItem("Open File");
        MenuItem exitGame = new MenuItem("Quitter");
        
        MenuItem copyItem = new MenuItem("Copy");
        MenuItem pasteItem = new MenuItem("Paste");
        
        fileMenu.getItems().addAll(newGame, exitGame);
        //editMenu.getItems().addAll(copyItem, pasteItem);
        

		//When user click on the Exit item.
		newGame.setOnAction(new EventHandler<ActionEvent>() {
		
		    @Override
		    public void handle(ActionEvent event) {
		    	partie.nouvelleParti();
		    	gPane.getChildren().clear();
		    	gPane.initGrille(partie);
		    	// la vue observe les "update" du modèle, et réalise les mises à jour graphiques
		        partie.getGrille().addObserver(new GrilleUpdateObserver(partie.getGrille(), gPane));
		    }
		    
		});
		//When user click on the Exit item.
		exitGame.setOnAction(new EventHandler<ActionEvent>() {
		
		    @Override
		    public void handle(ActionEvent event) {
		        System.exit(0);
		    }
		});

     	// Add Menus to the MenuBar
        getMenus().addAll(fileMenu, helpMenu);
	}
}
