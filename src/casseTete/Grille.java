package casseTete;

import java.util.List;
import java.util.Observable;

public class Grille extends Observable {
	
	private List<Case> lst;
	private Case[][] tab;
	
	int lastI;
	int lastJ;
	int Longueur, largeur;
	
	public Grille(int longueur, int largeur) {
		setLongueur(longueur);
		setLargeur(largeur);
		tab = new Case[longueur][largeur];
	}
	
	private void setLongueur(int longueur) {
		this.Longueur = longueur;
	}
	private void setLargeur(int largeur) {
		this.largeur = largeur;
	}
	public void startDD(int i , int j ) {
		System.out.println("startDD : " + i + "-" + j);
        setChanged();
        notifyObservers();
	}
	
	public void parcoursDD(int i , int j){
		    lastI = i;
	        lastJ = j;
	        System.out.println("parcoursDD : " + i + "-" + j);
	        setChanged();
	        notifyObservers();
	}
	
	public void stopDD(int i , int j) {
        System.out.println("stopDD : " + i + "-" + j + " -> " + lastI + "-" + lastJ);
        setChanged();
        notifyObservers();
	}
	

}
