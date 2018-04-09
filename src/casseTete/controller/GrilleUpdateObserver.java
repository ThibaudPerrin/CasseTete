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
			
    		String[] parts = ((String[]) arg);

    		switch (parts[0]) {
			case "update":
				updateChemin(parts);
				break;
			case "delete":
				deleteChemin(parts);
				break;
			default:
				break;
			}
    		
    	}
		
	}
	
	public void updateChemin(String[] parts) {
		int i = Integer.parseInt(parts[1]);
		int j = Integer.parseInt(parts[2]);
		int li = Integer.parseInt(parts[3]);
		int lj = Integer.parseInt(parts[4]);
		
		CasePane p = new CasePane(g.getTab()[i][j], g, i, j);
		CasePane p2 = new CasePane(g.getTab()[li][lj], g, li, lj);
		
		
		gPane.add(p, i, j);
		gPane.add(p2, li, lj);
		System.out.println(i+":"+j+"|"+li+":"+lj);
	}
	
	public void deleteChemin(String[] parts) {
		for (int i = 1; i < parts.length; i++) {
			String[] string = parts[i].split("-");
			int col = Integer.parseInt(string[0]);
			int lin = Integer.parseInt(string[1]);
			CasePane p = new CasePane(g.getTab()[col][lin], g, col, lin);
			p.initEvents();
			gPane.add(p, col, lin);
		}
	}

}
