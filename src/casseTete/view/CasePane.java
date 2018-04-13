package casseTete.view;

import casseTete.controller.ParcoursDDEventH;
import casseTete.controller.StartDDEventH;
import casseTete.controller.StopDDEventH;
import casseTete.model.Case;
import casseTete.model.Grille;
import casseTete.model.Symbole;
import casseTete.model.Lien;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class CasePane extends Pane {
	
	private Case c;
	private Grille grille;
	private int fColumn;
	private int fRow;
	private ParcoursDDEventH parcoursdd;
	private StartDDEventH startdd;
	private StopDDEventH stopdd;
	
	public CasePane(Case c, Grille grille, int fColumn, int fRow) {
		this.c = c;
		this.grille = grille;
		this.fColumn = fColumn;
		this.fRow = fRow;
		this.startdd = new StartDDEventH(this,grille, fColumn, fRow);
		this.parcoursdd = new ParcoursDDEventH(grille, fColumn, fRow);
		this.stopdd = new StopDDEventH(grille, fColumn, fRow);
		initDesign();
		deletEvents();
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
		this.setOnMouseClicked(new EventRemoveLien(this.grille, this.fColumn, this.fRow));
//		this.setOnMouseClicked(new EventHandler<Event>() {
//
//			@Override
//			public void handle(Event event) {
//				System.out.println("click");
//				
//			}
//	    	
//		});
	}
	
	public void initEvents() {
		this.setOnDragDetected(startdd);
		this.setOnDragEntered(parcoursdd);
		this.setOnDragDone(stopdd);

	    
	}
	
	public void deletEvents() {
		this.removeEventHandler(MouseEvent.ANY, startdd);
		this.removeEventHandler(DragEvent.ANY, parcoursdd);
		this.removeEventHandler(DragEvent.ANY, stopdd);
	}
}
