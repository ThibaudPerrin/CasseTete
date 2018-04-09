package casseTete.view;

import casseTete.model.Partie;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;

public class GrilleGPane extends GridPane{

	public GrilleGPane() {
		setPrefSize(800, 800);
        setAlignment(Pos.CENTER);
        setGridLinesVisible(true);
	}
	
	public void initGrille(Partie partie) {
		for(int i = 0; i < partie.getGrille().getLongueur(); i++) {
        	for(int j = 0; j < partie.getGrille().getLargeur(); j++) {
        		
        		final int fColumn = i;
                final int fRow = j;
        		CasePane p = new CasePane(partie.getGrille().getTab()[i][j]);
        		
        		p.setOnDragDetected(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {

                        Dragboard db = p.startDragAndDrop(TransferMode.ANY);
                        ClipboardContent content = new ClipboardContent();       
                        content.putString(""); // non utilisé actuellement
                        db.setContent(content);
                        event.consume();
                        partie.getGrille().startDD(fColumn, fRow);
                    }
                });

                p.setOnDragEntered(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
                        
                    	partie.getGrille().parcoursDD(fColumn, fRow);
                        event.consume();
                    }
                });
                
                p.setOnDragDone(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
                        
                        // attention, le setOnDragDone est déclenché par la source du Drag&Drop
                        
                    	partie.getGrille().stopDD(fColumn, fRow);
                        
                    }
                });
        		
        		add(p, i, j);
        	}
        }
	}
}
