import java.awt.Color;

public class Joueur {
	
	private String pseudo = "NONAME";
	private Color couleur = Color.BLACK;
	private int nb_points = 0;
	private Plateau plateau = new Plateau();
	private int nbRois = 0;
	
	public Joueur(Color couleur) {
		this.pseudo = "NONAME";
		this.couleur = couleur;
		this.nb_points = 0;
		this.nbRois = 0;
	}

	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public Color getCouleur() {
		return couleur;
	}
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
	public int getNb_points() {
		return nb_points;
	}
	public void setNb_points(int nb_points) {
		this.nb_points = nb_points;
	}
	public Plateau getPlateau() {
		return plateau;
	}
	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	public int getNbRois() {
		return nbRois;
	}

	public void setNbRois(int nbRois) {
		this.nbRois = nbRois;
	}
	
}