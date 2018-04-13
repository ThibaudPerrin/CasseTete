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
        //setGridLinesVisible(true);
	}
	
	public void initGrille(Partie partie) {
		for(int i = 0; i < partie.getGrille().getLongueur(); i++) {
        	for(int j = 0; j < partie.getGrille().getLargeur(); j++) {
        		
        		final int fColumn = i;
                final int fRow = j;
        		CasePane p = new CasePane(partie.getGrille().getTab()[i][j], partie.getGrille(), fColumn, fRow);
        		p.initEvents();
        		monAdd(p,i,j);
        	}
        }
	}
	
	
	public Node getNodeFromGridPane( Node node1, int col, int row) {
		Node find = null;
	    for(Node n : getChildren()) {
	    	if(getColumnIndex(n) != null && getRowIndex(n) != null && getColumnIndex(n) == col && getRowIndex(n) == row) {
	    		System.err.println(getColumnIndex(n)+" = "+col+"__ "+getRowIndex(n)+"="+row);
	    		find = n;
//	    		((CasePane) n).deletEvents();
	    		getChildren().remove(n);
	    			
	    	}
	    	
	    }
	    return find;
	}

	public void monAdd(CasePane p, int i, int j) {
        GridPane.setColumnIndex(p, i);
        GridPane.setRowIndex(p, j);
		getChildren().add(p);
		
	}
}
