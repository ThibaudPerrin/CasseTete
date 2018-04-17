package casseTete.model;

public class Partie {
	private static int MAXFAC = 8;
	private static int MINFAC = 5;
	public int niveau = 0;

	
	private Grille grille;
	
	public Partie() {
		nouvelleParti();
	}
	
	public void nouvelleParti() {
		int Longueur = MINFAC + (int)(Math.random() * ((MAXFAC - MINFAC) + 1));
		int Largeur = MINFAC + (int)(Math.random() * ((MAXFAC - MINFAC) + 1));
		System.out.println("largeur="+Largeur+" longueur="+Longueur);
		grille = new Grille(Longueur, Largeur);
		//grille = new Grille(8, 8);
	}
	
	
	
	public void nextNiv() {
		niveau=niveau+1;
	}
	
	public void resetNiv() {
		niveau = 0;
	}

	public Grille getGrille() {
		return grille;
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}
}
