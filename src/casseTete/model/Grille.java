package casseTete.model;

import java.util.List;
import java.util.Observable;

public class Grille extends Observable {
	
	private List<Case> lst;
	private Case[][] tab;
	
	private int lastI;
	private int lastJ;
	private int longueur, largeur;
	private boolean canStart = false; 
	
	public Grille(int longueur, int largeur) {
		setLongueur(longueur);
		setLargeur(largeur);
		tab = new Case[longueur][largeur];
		init() ;
	}
	
	private void init() {
		for(int i = 0 ; i < this.longueur; i++) {
			for(int j = 0 ; j < largeur; j++) {
				tab[i][j]= new Case();
			}
		}
		placeSymbole();
	}
	private void placeSymbole() {
		tab[0][0].setSymb(Symbole.ETOILE);
		tab[this.longueur-1][this.largeur-1].setSymb(Symbole.ETOILE);
	}
	private void setLongueur(int longueur) {
		this.longueur = longueur;
	}
	public Case[][] getTab() {
		return tab;
	}

	public void setTab(Case[][] tab) {
		this.tab = tab;
	}

	private void setLargeur(int largeur) {
		this.largeur = largeur;
	}
	public int getLongueur() {
		return longueur;
	}
	public int getLargeur() {
		return largeur;
	}
	
	public boolean isSymbole(int i , int j) {
		if(this.tab[i][j].getSymb() != Symbole.VIDE) {
			canStart = true;
		}else {
			canStart = false;
		}
		return canStart;
	}
	public void startDD(int i , int j ) {
		if(isSymbole(i,j)) {
			System.out.println("startDD : " + i + "-" + j);
			setChanged();
	        notifyObservers();	
		}
        
	}
	
	public void parcoursDD(int i , int j){
		if(canStart) {
			lastI = i;
	        lastJ = j;
	        System.out.println("parcoursDD : " + i + "-" + j);
	        setChanged();
	        notifyObservers();
		}
		    
	}
	
	public void stopDD(int i , int j) {
		if(canStart) {
	        System.out.println("stopDD : " + i + "-" + j + " -> " + lastI + "-" + lastJ);
	        setChanged();
	        notifyObservers();
		}
	}
	
	
	

}
