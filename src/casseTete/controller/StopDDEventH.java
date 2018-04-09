package casseTete.controller;

import casseTete.model.Grille;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;

public class StopDDEventH implements EventHandler<DragEvent> {
	private Grille grille;
	private int fColumn;
	private int fRow;
	
	public StopDDEventH(Grille grille, int fColumn, int fRow) {
		super();
		this.grille = grille;
		this.fColumn = fColumn;
		this.fRow = fRow;
	}
	 public void handle(DragEvent event) {
         
         // attention, le setOnDragDone est déclenché par la source du Drag&Drop
     	grille.stopDD(fColumn, fRow);
         
     }
}
