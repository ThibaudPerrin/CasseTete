package casseTete.view;

import casseTete.controller.ParcoursDDEventH;
import casseTete.controller.StartDDEventH;
import casseTete.controller.StopDDEventH;
import casseTete.model.Case;
import casseTete.model.Grille;
import casseTete.model.Symbole;
import casseTete.model.Lien;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CasePane extends Pane {
	
	private Case c;
	private Grille grille;
	private int fColumn;
	private int fRow;
	
	public CasePane(Case c, Grille grille, int fColumn, int fRow) {
		this.c = c;
		this.grille = grille;
		this.fColumn = fColumn;
		this.fRow = fRow;
		
		initDesign();
	}
	
	public void initDesign() {
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
	
	public void initEvents() {
		setOnDragDetected(new StartDDEventH(this,grille, fColumn, fRow));
	    setOnDragEntered(new ParcoursDDEventH(grille, fColumn, fRow));
	    setOnDragDone(new StopDDEventH(grille, fColumn, fRow));
	}
	
}
