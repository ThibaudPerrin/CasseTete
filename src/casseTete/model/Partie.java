package casseTete.model;

public class Partie {
	private static int MAX = 8;
	private static int MIN = 4;
	
	private Grille grille;
	
	public Partie() {
		nouvelleParti();
	}
	
	public void nouvelleParti() {
		int Longueur = MIN + (int)(Math.random() * ((MAX - MIN) + 1));
		int Largeur = MIN + (int)(Math.random() * ((MAX - MIN) + 1));
		System.out.println("largeur="+Largeur+" longueur="+Longueur);
		//grille = new Grille(Longueur, Largeur);
		grille = new Grille(8, 8);
	}

	public Grille getGrille() {
		return grille;
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}
}
