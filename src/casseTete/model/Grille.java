package casseTete.model;

import java.util.List;
import java.util.Observable;

public class Grille extends Observable {
	
	private List<Case> lst;
	private Chemin ch;
	private Case[][] tab;
	
	private int lastI;
	private int lastJ;
	private int vLastI;
	private int vLastJ;
	
	private int longueur, largeur;
	private boolean canStart = false; 
	
	public Grille(int longueur, int largeur) {
		setLongueur(longueur);
		setLargeur(largeur);
		this.tab = new Case[longueur][largeur];
		this.ch = new Chemin();
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
		tab[1][1].setSymb(Symbole.ETOILE);
		tab[this.longueur-2][this.largeur-2].setSymb(Symbole.ETOILE);
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
	public void chooseLien(int vLI, int vLJ, int lI, int lJ, int i, int j) {
		//Cas Depart
		if(vLI == lI && vLJ == lJ) {
			System.out.println("i:"+i+"j:"+j);
			if(i - lI != 0 ) {
				this.tab[i][j].setLien(Lien.TRAITHORIZONTAL);
			}else if(j - lJ !=0) {
				this.tab[i][j].setLien(Lien.TRAITVERTICAL);
			}
		}else if(lI-vLI == 0 && lI-i ==0 && lJ - vLJ != 0 && lJ- j !=0){
			this.tab[i][j].setLien(Lien.TRAITVERTICAL);
			this.tab[lI][lJ].setLien(Lien.TRAITVERTICAL);
		}else if(lI-vLI != 0 && lI-i != 0 && lJ - vLJ == 0 && lJ- j ==0){
			this.tab[i][j].setLien(Lien.TRAITHORIZONTAL);
			this.tab[lI][lJ].setLien(Lien.TRAITHORIZONTAL);
		}else if(lI-vLI == 1 && lI-i == 0 && lJ - vLJ == 0 && lJ- j != 0 ){
			this.tab[i][j].setLien(Lien.TRAITVERTICAL);
			this.tab[lI][lJ].setLien(Lien.ANGLEINFDROIT);
		}else if(lI-vLI == -1 && lI-i == 0 && lJ - vLJ == 0 && lJ- j != 0 ){
			this.tab[i][j].setLien(Lien.TRAITVERTICAL);
			this.tab[lI][lJ].setLien(Lien.ANGLESUPGAUCHE);
		}else if(lI-vLI == 0 && ((lI-i == -1 && lJ - vLJ == 1) || (lI-i == 1 && lJ - vLJ == -1)) && lJ- j == 0){
			this.tab[i][j].setLien(Lien.TRAITHORIZONTAL);
			this.tab[lI][lJ].setLien(Lien.ANGLESUPGAUCHE);
		}else if(lI-vLI == 0 && lI-i == 1 && lJ - vLJ == 1 && lJ- j == 0){
			this.tab[i][j].setLien(Lien.TRAITHORIZONTAL);
			this.tab[lI][lJ].setLien(Lien.ANGLESUPDROIT);
		}else if(lI-vLI == 0 && lI-i == -1 && lJ - vLJ == -1 && lJ- j == 0){
			this.tab[i][j].setLien(Lien.TRAITHORIZONTAL);
			this.tab[lI][lJ].setLien(Lien.ANGLEINFGAUCHE);
		}
		
        this.ch.getLst().add(this.tab[i][j]);
        
	}
	
	public void startDD(int i , int j ) {
		if(isSymbole(i,j)) {
			lastI = i;
	        lastJ = j;
	        vLastI = i;
	        vLastJ = j;
			System.out.println("startDD : " + i + "-" + j);
			setChanged();
	        notifyObservers();	
		}
        
	}
	
	public void parcoursDD(int i , int j){
		if(canStart && this.tab[i][j].isEmpty()) {

			chooseLien(vLastI,vLastJ, lastI, lastJ, i, j);
			
			vLastI = lastI;
	        vLastJ = lastJ;
			lastI = i;
	        lastJ = j;
	        
	        System.out.println("parcoursDD : " + i + "-" + j);
	        
	        
	        setChanged();
	        notifyObservers(""+i+"-"+j+"-"+vLastI+"-"+vLastJ);
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
