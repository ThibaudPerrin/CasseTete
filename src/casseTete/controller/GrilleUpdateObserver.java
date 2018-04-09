package casseTete.controller;

import java.util.Observable;
import java.util.Observer;

import casseTete.model.Grille;
import casseTete.view.CasePane;
import casseTete.view.GrilleGPane;

public class GrilleUpdateObserver implements Observer{
	private Grille g;
	private GrilleGPane gPane;

	public GrilleUpdateObserver(Grille g, GrilleGPane gPane) {
		super();
		this.g= g;
		this.gPane = gPane;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg != null) {
    		String[] parts = ((String) arg).split("-");
    		int i = Integer.parseInt(parts[0]);
    		int j = Integer.parseInt(parts[1]);
    		int li = Integer.parseInt(parts[2]);
    		int lj = Integer.parseInt(parts[3]);
    		
    		
    		CasePane p = new CasePane(g.getTab()[i][j], g, i, j);
    		CasePane p2 = new CasePane(g.getTab()[li][lj], g, li, lj);
    		
    		p.initEvents();
    		
    		
    		gPane.add(p, i, j);
    		gPane.add(p2, li, lj);
    		System.out.println(i+":"+j+"|"+li+":"+lj);
    	}
		
	}

}
