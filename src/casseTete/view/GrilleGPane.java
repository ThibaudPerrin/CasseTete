package casseTete.view;


import casseTete.model.Partie;

import javafx.geometry.Pos;
import javafx.scene.Node;

import javafx.scene.layout.GridPane;

public class GrilleGPane extends GridPane{

	public GrilleGPane() {
		super();
		setPrefSize(800, 800);
        setAlignment(Pos.CENTER);
        setGridLinesVisible(true);
	}
	
	public void initGrille(Partie partie) {
		for(int i = 0; i < partie.getGrille().getLongueur(); i++) {
        	for(int j = 0; j < partie.getGrille().getLargeur(); j++) {
        		
        		final int fColumn = i;
                final int fRow = j;
        		CasePane p = new CasePane(partie.getGrille().getTab()[i][j], partie.getGrille(), fColumn, fRow);
        		p.initEvents();
        		add(p, i, j);
        	}
        }
	}
	
	
	public Node getNodeFromGridPane( int col, int row) {
	    for (Node node : getChildren()) {
	    	System.err.println(getColumnIndex(node));
	        if (getColumnIndex(node) == col && getRowIndex(node) == row) {
	            return node;
	        }
	    }
	    return null;
	}
}
