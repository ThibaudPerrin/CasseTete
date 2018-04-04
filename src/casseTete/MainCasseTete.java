package casseTete;




import java.util.Properties;

import casseTete.model.Grille;
import casseTete.model.Symbole;
import casseTete.view.CasePane;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
        
        //Initialisation
        for(int i = 0; i < g.getLongueur(); i++) {
        	for(int j = 0; j < g.getLargeur(); j++) {
        		CasePane p = new CasePane(g.getTab()[i][j]);
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
