package casseTete.view;

import casseTete.controller.GrilleUpdateObserver;
import casseTete.controller.NouvellePartieEventH;
import casseTete.model.Partie;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class GrilleMenu extends MenuBar{
	

	private Stage primaryStage;
	private Partie partie;
	private GrilleGPane gPane;
	
	public GrilleMenu(Stage primaryStage, Partie partie, GrilleGPane gPane) {
		super();
		this.primaryStage = primaryStage;
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
        
        MenuItem reglesJeu = new MenuItem("Regles du jeu");
        
        fileMenu.getItems().addAll(newGame, exitGame);
        //editMenu.getItems().addAll(copyItem, pasteItem);
        
        helpMenu.getItems().addAll(reglesJeu);
        

		//When user click on the Exit item.
		newGame.setOnAction(new NouvellePartieEventH(primaryStage, partie, gPane));
		//When user click on the Exit item.
		exitGame.setOnAction(new EventHandler<ActionEvent>() {
		
		    @Override
		    public void handle(ActionEvent event) {
		        System.exit(0);
		    }
		});
		
		reglesJeu.setOnAction(new EventHandler<ActionEvent>(){
			 @Override
			    public void handle(ActionEvent e) {
			        popUpAide();
			        System.out.println("pas beau");
			    }
		});
			

     	// Add Menus to the MenuBar
        getMenus().addAll(fileMenu, helpMenu);
        
      
        
        
	}
	
	  public void popUpAide() {
			Button btn = new Button();
		    btn.setText("Fermer");
		    GridPane gp = new GridPane();
		    gp.setAlignment(Pos.CENTER);
		    

		    BorderPane border = new BorderPane();
		    final Stage dialog = new Stage();
		    
		    gp.add(btn, 0, 0);
		    btn.setOnAction(new EventHandler<ActionEvent>() {
			
			    @Override
			    public void handle(ActionEvent event) {
			    	dialog.hide();
			    }
		    });
		    
		    
			 
	         dialog.initModality(Modality.APPLICATION_MODAL);
	         dialog.initOwner(primaryStage);
	         
	         VBox dialogVbox = new VBox(20);
	         dialogVbox.getChildren().add(new Text("Regles du jeu"));
	         //Titre
	         Text affichage = new Text("Regles du jeu\n");
	         affichage.setFont(Font.font("Verdana", 30));
	         border.setTop(affichage);
	         border.setAlignment(affichage,Pos.CENTER);
	         Text regles = new Text("Pour terminer la partie , vous devrez relier chaque paire de Ruby entre eux \nLe but étant de relier toutes les paires de Ruby entre elles sans croiser les chemins déjà realisés"
	         		+ "\n\n\nPour relier les Ruby entre eux vous devrez faire glisser votre souris d'un Ruby à l'autre\n"
	         		+ "Vous pouvez aussi supprimer un chemin simplement en cliquant dessus\n\n");
	         regles.setFont(Font.font("Verdana", 12));
	         border.setCenter(regles);
	         BorderPane.setAlignment(regles,Pos.CENTER);
	         border.setBottom(gp);
	         Scene dialogScene = new Scene(border);
	         dialog.setScene(dialogScene);
	         dialog.show();
		}
}

