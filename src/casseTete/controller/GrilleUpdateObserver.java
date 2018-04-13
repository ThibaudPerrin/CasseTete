package casseTete.controller;

import java.util.Observable;
import java.util.Observer;

import casseTete.model.Grille;
import casseTete.model.Partie;
import casseTete.view.CasePane;
import casseTete.view.GrilleGPane;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GrilleUpdateObserver implements Observer{
	private Stage primaryStage;
	private Partie partie;
	private GrilleGPane gPane;

	public GrilleUpdateObserver(Stage primaryStage, Partie partie, GrilleGPane gPane) {
		super();
		this.primaryStage = primaryStage;
		this.partie= partie;
		this.gPane = gPane;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
		if(arg != null) {
			
    		String[] parts = ((String[]) arg);

    		switch (parts[0]) {
			case "update":
				updateChemin(parts);
				break;
			case "delete":
				deleteChemin(parts);
				break;
			case "termine":
				popUpFin();
				break;
			default:
				break;
			}
    		
    	}
		
	}
	
	public void popUpFin() {
		Button btn = new Button();
	    btn.setText("nouvelle Partie");
	    Button btn2 = new Button();
	    btn2.setText("Quitter");
	    GridPane gp = new GridPane();
	    gp.setAlignment(Pos.CENTER);
	    

	    BorderPane border = new BorderPane();
	    final Stage dialog = new Stage();
	    
	    gp.add(btn, 0, 0);
	    gp.add(btn2, 1, 0);
	    btn.setOnAction(new EventHandler<ActionEvent>() {
		
		    @Override
		    public void handle(ActionEvent event) {
		    	NouvellePartieEventH n = new NouvellePartieEventH(primaryStage, partie, gPane);
		    	n.handle(event);
		    	dialog.hide();
		    }
	    });
	    btn2.setOnAction(new EventHandler<ActionEvent>() {
		
		    @Override
		    public void handle(ActionEvent event) {
		        System.exit(0);
		    }
		});
	    
		 
         dialog.initModality(Modality.APPLICATION_MODAL);
         dialog.initOwner(primaryStage);
         
         VBox dialogVbox = new VBox(20);
         dialogVbox.getChildren().add(new Text("Partie Termine"));
         //Titre
         Text affichage = new Text("Partie Termine");
         affichage.setFont(Font.font("Verdana", 30));
         border.setTop(affichage);
         border.setAlignment(affichage,Pos.CENTER);
         
         border.setCenter(gp);
         Scene dialogScene = new Scene(border);
         dialog.setScene(dialogScene);
         dialog.show();
	}
	
	public void updateChemin(String[] parts) {
		int i = Integer.parseInt(parts[1]);
		int j = Integer.parseInt(parts[2]);
		int li = Integer.parseInt(parts[3]);
		int lj = Integer.parseInt(parts[4]);
		
		CasePane p = new CasePane(partie.getGrille().getTab()[i][j], partie.getGrille(), i, j);
		CasePane p2 = new CasePane(partie.getGrille().getTab()[li][lj], partie.getGrille(), li, lj);
//		gPane.getNodeFromGridPane(p, i, j);
//		gPane.getNodeFromGridPane(p2, li, lj);
		//p.setOnDragEntered(new ParcoursDDEventH(partie.getGrille(), i, j));
		
		gPane.monAdd(p, i, j);
		gPane.monAdd(p2, li, lj);
		System.out.println(i+":"+j+"|"+li+":"+lj);
		
	}
	
	public void deleteChemin(String[] parts) {
		for (int i = 1; i < parts.length; i++) {
			String[] string = parts[i].split("-");
			int col = Integer.parseInt(string[0]);
			int lin = Integer.parseInt(string[1]);
			CasePane p = new CasePane(partie.getGrille().getTab()[col][lin], partie.getGrille(), col, lin);
			p.initEvents();
			gPane.add(p, col, lin);
		}
	}

}
