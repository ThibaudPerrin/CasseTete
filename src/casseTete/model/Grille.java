package casseTete.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Grille extends Observable {
	
	private List<Chemin> lst;
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
		this.lst = new ArrayList<Chemin>();
		init() ;
	}
	
	private void init() {
		for(int i = 0 ; i < this.longueur; i++) {
			for(int j = 0 ; j < largeur; j++) {
				tab[i][j]= new Case(i,j);
			}
		}
		placeSymbole();
	}
	private void placeSymbole() {
		tab[1][1].setSymb(Symbole.ETOILE);
		tab[this.longueur-2][this.largeur-2].setSymb(Symbole.ETOILE);
		tab[this.longueur-2][this.largeur-3].setSymb(Symbole.CARRE);
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
	public boolean isGoodSymbole(int i, int j, int lastI, int lastJ) {
		if(isSymbole(lastI,lastJ)) {
			return this.tab[i][j].getSymb() == this.tab[lastI][lastJ].getSymb();
		}else {
			return false;
		}
	}
	
	
	public void chooseLien(int vLI, int vLJ, int lI, int lJ, int i, int j) {
		//Cas Depart
		if(vLI == lI && vLJ == lJ) {
			System.out.println("i:"+i+"j:"+j);
			if(i - lI != 0 ) {
				this.tab[i][j].setLien(Lien.TRAITHORIZONTAL);
				//this.tab[lI][lJ].setLien(Lien.TRAITHORIZONTAL);
			}else if(j - lJ !=0) {
				this.tab[i][j].setLien(Lien.TRAITVERTICAL);
				//this.tab[lI][lJ].setLien(Lien.TRAITVERTICAL);
			}
		}else if(lI-vLI == 0 && lI-i ==0 && lJ - vLJ != 0 && lJ- j !=0){
			this.tab[i][j].setLien(Lien.TRAITVERTICAL);
			this.tab[lI][lJ].setLien(Lien.TRAITVERTICAL);
		}else if(lI-vLI != 0 && lI-i != 0 && lJ - vLJ == 0 && lJ- j ==0){
			this.tab[i][j].setLien(Lien.TRAITHORIZONTAL);
			this.tab[lI][lJ].setLien(Lien.TRAITHORIZONTAL);
		}else if(lI-vLI == 1 && lI-i == 0 && lJ - vLJ == 0 && lJ- j == -1 ){
			this.tab[i][j].setLien(Lien.TRAITVERTICAL);
			this.tab[lI][lJ].setLien(Lien.ANGLESUPDROIT);
		}else if(lI-vLI == -1 && lI-i == 0 && lJ - vLJ == 0 && lJ- j == 1 ){
			this.tab[i][j].setLien(Lien.TRAITVERTICAL);
			this.tab[lI][lJ].setLien(Lien.ANGLEINFGAUCHE);
		}else if(lI-vLI == -1 && lI-i == 0 && lJ - vLJ == 0 && lJ- j == -1 ){
			this.tab[i][j].setLien(Lien.TRAITVERTICAL);
			this.tab[lI][lJ].setLien(Lien.ANGLESUPGAUCHE);
		}else if(lI-vLI == 0 && lI-i == -1 && lJ - vLJ == 1 && lJ- j == 0){
			this.tab[i][j].setLien(Lien.TRAITHORIZONTAL);
			this.tab[lI][lJ].setLien(Lien.ANGLEINFGAUCHE);
		}else if(lI-vLI == 0 && lI-i == 1 && lJ - vLJ == -1 && lJ- j == 0){
			this.tab[i][j].setLien(Lien.TRAITHORIZONTAL);
			this.tab[lI][lJ].setLien(Lien.ANGLESUPDROIT);
		}else if(lI-vLI == 0 && lI-i == 1 && lJ - vLJ == 1 && lJ- j == 0){
			this.tab[i][j].setLien(Lien.TRAITHORIZONTAL);
			this.tab[lI][lJ].setLien(Lien.ANGLEINFDROIT);
		}else if(lI-vLI == 0 && lI-i == -1 && lJ - vLJ == -1 && lJ- j == 0){
			this.tab[i][j].setLien(Lien.TRAITHORIZONTAL);
			this.tab[lI][lJ].setLien(Lien.ANGLESUPGAUCHE);
		}else if(lI-vLI == 1 && lI-i == 0 && lJ - vLJ == 0 && lJ- j == 1){
			this.tab[i][j].setLien(Lien.TRAITVERTICAL);
			this.tab[lI][lJ].setLien(Lien.ANGLEINFDROIT);
		}
		
		
        this.ch.getLst().add(this.tab[i][j]);
        
	}
	public String[] deleteChemin() {
		String[] sChemins = new String[this.ch.getLst().size()+1];
		sChemins[0] = "delete";
		int i = 1;
		for (Case cs : this.ch.getLst()) {
			int caseLong = cs.getColonne();
			int caseLarg = cs.getLigne();
			this.tab[caseLong][caseLarg].setLien(Lien.CASEVIDE);
			sChemins[i] = caseLong+"-"+caseLarg;
			i++;
		}
		return sChemins;
	}
	public boolean isCasePrec(int lastI, int lastJ, int i, int j) {
		int soustI = i - lastI;
		int soustJ = j - lastJ;
		return ( soustI == 0 || soustI == 1 || soustI == -1 ) && (soustJ == 0 || soustJ == 1 || soustJ == -1);
	}
	public void startDD(int i , int j ) {
		if(isSymbole(i,j)) {
			lastI = i;
	        lastJ = j;
	        vLastI = i;
	        vLastJ = j;
	        this.ch = new Chemin();
			System.out.println("startDD : " + i + "-" + j);
			setChanged();
	        notifyObservers();
		}
        
	}
	
	public void parcoursDD(int i , int j){
		if(canStart) {
			if(isCasePrec(lastI, lastJ, i, j)) {
				chooseLien(vLastI,vLastJ, lastI, lastJ, i, j);

				vLastI = lastI;
		        vLastJ = lastJ;
				lastI = i;
		        lastJ = j;
		        
		        System.out.println("parcoursDD : " + i + "-" + j);
		        
		        String[] result = {"update",""+i,""+j,""+vLastI,""+vLastJ};
		        setChanged();
		        notifyObservers(result);
			}else {
				String[] result = deleteChemin();
				setChanged();
		        notifyObservers(result);
			}
			
		}
		    
	}
	
	public void stopDD(int i , int j) {
		if(i!= lastI && j != lastI && isGoodSymbole(i,j,lastI,lastJ)) {
			System.out.println("stopDD : i" + i + "-j" + j + " -> li" + lastI + "-lj" + lastJ+" -> vLastJ" + vLastI + "-vLastJ" + vLastJ);
	        System.out.println("stopDD : " + i + "-" + j + " -> " + lastI + "-" + lastJ);
	        this.lst.add(ch);

	        setChanged();
	        notifyObservers();
		}else {
			String[] result = deleteChemin();
			setChanged();
	        notifyObservers(result);
		}
	}
	
	public String toString() {
		String result = "";
		for (int i = 0; i < longueur; i++) {
			for (int j = 0; j < largeur; j++) {
				result = result+" "+tab[i][j].toString();
			}
			result = result+"\n";
		}
		return result;
	}
	
	
	

}
