package casseTete.controller;

import casseTete.model.Grille;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;

public class ParcoursDDEventH implements EventHandler<DragEvent>{
	
	private Grille grille;
	private int fColumn;
	private int fRow;
	
	public ParcoursDDEventH(Grille grille, int fColumn, int fRow) {
		super();
		this.grille = grille;
		this.fColumn = fColumn;
		this.fRow = fRow;
	}

	public void handle(DragEvent event) {
        
    	grille.parcoursDD(fColumn, fRow);
        event.consume();
    }
}
