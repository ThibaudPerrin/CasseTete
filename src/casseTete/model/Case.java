package casseTete.model;

public class Case {
	
	private Symbole symb;
	private Lien lien;
	private int ligne;
	private int colonne;
	
	public Case() {
		this.symb = Symbole.VIDE;
		this.lien = Lien.CASEVIDE;
		
	}
	
	public Symbole getSymb() {
		return symb;
	}
	
	public void setSymb(Symbole symb) {
		this.symb = symb;
	}

	public Lien getLien() {
		return lien;
	}

	public void setLien(Lien lien) {
		this.lien = lien;
	}

	public int getLigne() {
		return ligne;
	}

	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	public int getColonne() {
		return colonne;
	}

	public void setColonne(int colonne) {
		this.colonne = colonne;
	}
	
	public String toString() {
		return ""+this.symb;
	}

	public boolean isEmpty() {
		return this.getSymb() == Symbole.VIDE && this.getLien() == Lien.CASEVIDE;
	}
	
	
	
}
