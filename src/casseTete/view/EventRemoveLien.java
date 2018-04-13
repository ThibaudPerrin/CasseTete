package casseTete.view;

import casseTete.model.Grille;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class EventRemoveLien implements EventHandler<Event> {
	private int fColumn;
	private int fRow;
	private Grille grille;
	public EventRemoveLien(Grille grille, int fColumn, int fRow) {
		this.grille = grille;
		this.fColumn = fColumn;
		this.fRow = fRow;
	}

	public void handle(Event event) {
		this.grille.findChemin(this.fColumn, this.fRow);
		
	}
}
