package casseTete.controller;

import casseTete.model.Grille;
import casseTete.view.CasePane;
import javafx.event.EventHandler;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class StartDDEventH implements EventHandler<MouseEvent>{
	private CasePane p;
	private Grille grille;
	private int fColumn;
	private int fRow;
	
	public StartDDEventH(CasePane p, Grille grille, int fColumn, int fRow) {
		super();
		this.p = p;
		this.grille = grille;
		this.fColumn = fColumn;
		this.fRow = fRow;
	}

	public void handle(MouseEvent event) {
		Dragboard db = p.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();       
        content.putString(""); // non utilisé actuellement
        db.setContent(content);
        event.consume();
        grille.startDD(fColumn, fRow);
    }
}
