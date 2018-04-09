package casseTete.view;

import casseTete.model.Case;
import casseTete.model.Symbole;
import casseTete.model.Lien;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CasePane extends Pane {
	
	
	public CasePane(Case c) {
		Image image;
		if(c.getLien() == Lien.CASEVIDE) {
			image = new Image("File:image/../"+c.getSymb()+".png");
		}else if(c.getLien() != Lien.CASEVIDE && c.getSymb() != Symbole.VIDE){
			image = new Image("File:image/../"+c.getSymb()+".png");
		}else {
			image = new Image("File:image/../"+c.getLien()+".png");
		}
		
		ImageView pic = new ImageView(image);
		pic.setFitWidth(100);
		pic.setFitHeight(100);
		this.getChildren().addAll(pic);
	}
	
}
