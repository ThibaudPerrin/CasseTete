package casseTete.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class Grille extends Observable {
	
	private List<Chemin> lst;
	private Chemin ch;
	private Case[][] tab;
	
	private int startI;
	private int startJ;
	private int lastI;
	private int lastJ;
	private int vLastI;
	private int vLastJ;
	
	private int nombrePaire;
	private boolean partieTermine;
	private boolean restePlace;
	private boolean initPaire;
	
	private int longueur, largeur;
	private boolean canStart = false; 
	
	public Grille(int longueur, int largeur) {
		setLongueur(longueur);
		setLargeur(largeur);
		this.tab = new Case[longueur][largeur];
		this.lst = new ArrayList<Chemin>();
		this.nombrePaire = 0;
		this.partieTermine = false;
		this.restePlace = true;
		this.ch = new Chemin();
		init() ;
	}
	
	private void init() {
		for(int i = 0 ; i < this.longueur; i++) {
			for(int j = 0 ; j < largeur; j++) {
				tab[i][j]= new Case(i,j);
			}
		}
		initGrille();
	}
	
	private void initGrille() {
		initPaire = true;
		//System.out.println(this.nombrePaire);
		boolean continu = true;
		while(continu) {
			//System.out.println("lalaal");
			continu = placeSymbole();
			//System.out.println("lalaal2");
			if(Symbole.values().length == this.nombrePaire+1) {
				//System.out.println("lalaal3");
				continu = false;
				//System.out.println("lalaal4");
			}
		}
		addTrou() ;
		supprAllLien();
		initPaire = false;
		//System.out.println(this.nombrePaire);
	}
	
	public void addTrou() {
		
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[i].length; j++) {
				Case cases = tab[i][j];
				if(cases.getLien() == Lien.CASEVIDE && cases.getSymb() == Symbole.VIDE) {
					int rand = 0 + (int)(Math.random() * ((2 - 0)));
					
					if(rand == 1) {
						cases.setLien(Lien.TROU);
					}
				}
			}
			
		}
	}
	
	public boolean findCheminPlusCourt(Case cDepart, Case cArrive) {
		int compteur = 30;
		startDD(cDepart.getColonne(), cDepart.getLigne());
		Case cCourant = chooseCasePlusCourt(cDepart,cArrive);
		parcoursDD(cCourant.getColonne(), cCourant.getLigne());
		//System.out.println("iii"+compteur);
		while(cCourant != cArrive && cCourant!=null && compteur > 0) {
			cCourant = chooseCasePlusCourt(cCourant,cArrive);
			if(cCourant!=null) {
				parcoursDD(cCourant.getColonne(), cCourant.getLigne());
			}
			compteur --;
		}
		//System.out.println();
		stopDD(startI,startJ);
		
		if(cCourant!=null && isGoodSymbole(startI, startJ, lastI, lastJ)) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public Case chooseCasePlusCourt(Case cCourante, Case cArrive) {
		Case find = null;
		Case haut = null, bas = null, gauche = null, droite = null;
		int Min = 5000;
		
		//haut
		//System.err.println("startI:"+startI+" startJ:"+ startJ+" lastI:"+ lastI+" lastJ:"+ lastJ);
		if(cCourante.getLigne() > 0 ) {
			haut = this.tab[cCourante.getColonne()][cCourante.getLigne()-1];
			int diffHaut = diffDistance(haut, cArrive);

			//System.out.println("haut : "+haut.infoToString()+" diffHaut : "+diffHaut);
			if((Min > diffHaut && haut.getLien() == Lien.CASEVIDE)) {
				Min = diffHaut;
				find = haut;
			}
		}
		//bas
		if(cCourante.getLigne() < this.largeur-1) {
			bas = this.tab[cCourante.getColonne()][cCourante.getLigne()+1];
			int diffBas = diffDistance(bas, cArrive);
			
			//System.out.println("bas : "+bas.infoToString()+" diffBas : "+diffBas);
			
			if((Min > diffBas && bas.getLien() == Lien.CASEVIDE )) {
				Min = diffBas;
				find = bas;
			}
		}
		//gauche
		if(cCourante.getColonne() > 0) {
			gauche = this.tab[cCourante.getColonne()-1][cCourante.getLigne()];
			int diffGauche = diffDistance(gauche, cArrive);
			//System.out.println("gauche : "+gauche.infoToString()+" diffGauche : "+diffGauche);
			if((Min > diffGauche && gauche.getLien() == Lien.CASEVIDE )) {
				Min = diffGauche;
				find = gauche;
			}
		}
		//droite
		if(cCourante.getColonne() < this.longueur-1) {
			droite = this.tab[cCourante.getColonne()+1][cCourante.getLigne()];
			int diffDroite = diffDistance(droite, cArrive);

			//System.out.println("droite : "+droite.infoToString()+" diffDroite : "+diffDroite);
			if((Min > diffDroite && droite.getLien() == Lien.CASEVIDE)) {
				Min = diffDroite;
				find = droite;
			}
		}
		
//		if(Min != 5000) {
//			System.out.println("find : "+find.infoToString());
//			System.out.println(toString());
//		}
		
		return find;
	}
	
	public int diffDistance(Case cCourante, Case cArrive) {
		int diffCol = Math.abs(cCourante.getColonne()-cArrive.getColonne());
		int diffLine = Math.abs(cCourante.getLigne()-cArrive.getLigne());
		int diffTotal = Math.abs(diffCol+diffLine);
		//int[] result = {diffCol,diffLine,diffTotal};
		int result = diffTotal;
		return result;
	}
	
	
	public Case placeSymb1() {
		boolean tryPlaceSymbol = true;
		int compteurTry = 5;
		int col1=0, line1=0;
		Case findCaseSymbol = null;
		
		while(tryPlaceSymbol && findCaseSymbol == null && compteurTry > 0) {
			col1 = 0 + (int)(Math.random() * ((this.longueur - 0)));
			line1 = 0 + (int)(Math.random() * ((this.largeur)));
			
			if(this.tab[col1][line1].getSymb() == Symbole.VIDE && this.tab[col1][line1].getLien() == Lien.CASEVIDE) {
				
				this.tab[col1][line1].setSymb(Symbole.values()[this.nombrePaire+1]);
				findCaseSymbol = this.tab[col1][line1];
			}else {
				compteurTry--;
			}
			if(compteurTry <= 0) {
				tryPlaceSymbol = false;
			}
		}
		return findCaseSymbol;
	}
	
	public Case placeSymb2(Case findCaseSymbol1) {
		boolean tryPlaceSymbol = true;
		int compteurTry = 5;
		int col2=0, line2=0;
		Case findCaseSymbol = null;
		
		while(tryPlaceSymbol && findCaseSymbol == null && findCaseSymbol1 != null && compteurTry > 0) {
			col2 = 0 + (int)(Math.random() * ((this.longueur - 0)));
			line2 = 0 + (int)(Math.random() * ((this.largeur)));
			
			if(this.tab[col2][line2].getSymb() == Symbole.VIDE && this.tab[col2][line2].getLien() == Lien.CASEVIDE) {
				this.tab[col2][line2].setSymb(Symbole.values()[this.nombrePaire+1]);
				findCaseSymbol = this.tab[col2][line2];
				compteurTry = 5;
			}else {
				compteurTry--;
			}
			if(compteurTry <= 0) {
				this.tab[col2][line2].setSymb(Symbole.VIDE);
				tryPlaceSymbol = false;
			}
		}
		return findCaseSymbol;
	}
	
	public boolean placeSymbole() {

		Case symb1, symb2;
		int compteurTry = 2;
		
		
		symb1 = placeSymb1();
		symb2 = placeSymb2(symb1);
		
		
		
		if(symb1 != null  && symb2 != null) {
			boolean cheminPossible = findCheminPlusCourt(symb1,symb2);
			//System.out.println("icicics");
			while(!cheminPossible && compteurTry > 0 && symb1 != null  && symb2 != null) {
				//System.out.println("icicics2");
				if(symb1 != null) {
					symb1.setSymb(Symbole.VIDE);
				}
				if(symb2 != null) {
					symb2.setSymb(Symbole.VIDE);
				}
				//deleteChemin();
				compteurTry --;
				symb1 = placeSymb1();
				symb2 = placeSymb2(symb1);
				//System.out.println("icicics3");
				if(symb1 != null  && symb2 != null) {
					//System.out.println("icicics4");
					cheminPossible = findCheminPlusCourt(symb1,symb2);
					//System.out.println("icicics5");
				}
				
			}
			//System.out.println("icicics6");
			if(cheminPossible) {
				//System.out.println("icicics7");
				this.nombrePaire++;
				//System.out.println("icicics8");
				return true;
			}else {
				//System.out.println("icicics8");
				if(symb1 != null) {
					symb1.setSymb(Symbole.VIDE);
				}
				if(symb2 != null) {
					symb2.setSymb(Symbole.VIDE);
				}
				//deleteChemin();
				return false;
			}
			
			
		}else {
			if(symb1 != null) {
				symb1.setSymb(Symbole.VIDE);
			}
			if(symb2 != null) {
				symb2.setSymb(Symbole.VIDE);
			}
			
			
			return false;
		}
		
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
		if(i != lastI || j != lastJ) {
			if(isSymbole(lastI,lastJ)) {
				return this.tab[i][j].getSymb() == this.tab[lastI][lastJ].getSymb();
			}else {
				return false;
			}
		}else {
			return false;
		}
		
	}
	
	public boolean isParti() {
		if(this.nombrePaire == 0  && initPaire == false) {
			this.partieTermine = true;
			return true;
		}else {
			return false;
		}
	}
	public void supprAllLien() {
		this.lst = new ArrayList<Chemin>();
		this.ch = new Chemin();
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[i].length; j++) {
				Case cases = tab[i][j];
				if(cases.getLien()!= Lien.TROU) {
					cases.setLien(Lien.CASEVIDE);
				}
				
			}
			
		}
	}
	public void chooseLien(int vLI, int vLJ, int lI, int lJ, int i, int j) {
		//Cas Depart
		if(vLI == lI && vLJ == lJ) {
			
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
	public boolean findChemin(int column, int line) {
		Case findingCcase = null;
		boolean find = false;
		for (Iterator<Chemin> iter = lst.listIterator(); iter.hasNext(); ) {
			Chemin chemin = iter.next();
		    if (chemin.findCases(column, line) != null) {
		    	this.ch.getLst().addAll(chemin.getLst());
		        iter.remove();
		        find = true;
		        eraseChemin();
		        nombrePaire++;
		    }
		}
		
		
		return find;
	}
	public boolean verifCaseLibre(int column, int line) {
		if(this.tab[column][line].getLien() == Lien.TROU) {
			return false;
		}else {
			if(this.tab[column][line].getLien() != Lien.CASEVIDE) {
				return !(findChemin(column, line));
			}else {
				return true;
			}
		}
		
		
		
		
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
		
		int soustI = Math.abs(i - lastI);
		int soustJ = Math.abs(j - lastJ);
		boolean condition1;
		boolean condition2 = false;
		condition1 = (soustI == 0  && soustJ == 1 ) ;
		if(this.ch.getLst().size() < 1) {
			condition2 = (soustJ == 0  && soustI == 0  ) ;
		}else {
			condition2 = (soustJ == 0  && soustI == 1 ) ;
		}
		boolean condition3 = (condition1 || condition2);
		return condition3;
	}
	public void startDD(int i , int j ) {
		
		if(isSymbole(i,j) && !this.partieTermine) {
			startI = i;
			startJ = j;
			lastI = i;
	        lastJ = j;
	        vLastI = i;
	        vLastJ = j;
	        this.ch = new Chemin();
			//System.out.println("startDD : " + i + "-" + j);
			setChanged();
	        notifyObservers();
		}
        
	}
	
	public void eraseChemin() {
		lastI = startI;
        lastJ = startJ;
        vLastI = startI;
        vLastJ = startJ;
		String[] result = deleteChemin();
		setChanged();
        notifyObservers(result);
	}
	
	public void parcoursDD(int i , int j){
		if(canStart && !this.partieTermine) {
			if(isCasePrec(lastI, lastJ, i, j) && verifCaseLibre(i,j)) {
				if(this.tab[lastI][lastJ].getSymb() != Symbole.VIDE && this.ch.getLst().size() > 1) {
					eraseChemin();
				}else {
					chooseLien(vLastI,vLastJ, lastI, lastJ, i, j);
	
						
					vLastI = lastI;
			        vLastJ = lastJ;
			        lastI = i;
			        lastJ = j;
				        
				        
			        //System.out.println("parcoursDD : " + i + "-" + j);
			        
			        String[] result = {"update",""+i,""+j,""+vLastI,""+vLastJ};
			        setChanged();
			        notifyObservers(result);
				}
			}else {
				eraseChemin();
//				lastI = startI;
//		        lastJ = startJ;
//		        vLastI = startI;
//		        vLastJ = startJ;
//				stopDD(startI,startJ);
			}
			
		}
		    
	}
	
	public void stopDD(int i , int j) {
		
		if((i!= lastI || j != lastJ) && isGoodSymbole(i,j,lastI,lastJ)) {
			//System.out.println("stopDD : i=" + i + "-j=" + j + " -> lastI=" + lastI + "-lastJ=" + lastJ+" -> vLastJ=" + vLastI + "-vLastJ" + vLastJ);
	        //System.out.println("stopDD : " + i + "-" + j + " -> " + lastI + "-" + lastJ);
			
	        this.lst.add(ch);
	        this.ch = new Chemin();
	        if(initPaire == false) {
	        	 this.nombrePaire --;
	        }
	       
	        
	        setChanged();
	        
			if(isParti()){
				String[] result = {"termine"};
				notifyObservers(result);
			}else {
				
		        notifyObservers();
			}
	        
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
