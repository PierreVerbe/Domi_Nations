import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;

public class Joueur {
	
	private String pseudo = "NONAME";
	private Color couleur = Color.BLACK;
	private int nb_points = 0;
	private Plateau plateau = new Plateau(5,5);
	private int nbRois = 0;
	
	public Joueur(){}
	
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
	
	public void setPlateauJoueur(int i, int j, String mot) {
		this.plateau.RemplirPlateau(i,j,mot);
	}
	
	public String getPlateauJoueur(int i, int j) {
		return this.plateau.RecupererPlateau(i,j);
	}
	
	public void affichagePlateauJoueur() {
		this.plateau.affichagePlateau();
	}
	
	public int getNbRois() {
		return nbRois;
	}
	public void setNbRois(int nbRois) {
		this.nbRois = nbRois;
	}
	
	public void infoJoueur()
	{
		System.out.println(this.pseudo);
		System.out.println(this.couleur);
		System.out.println(this.nbRois);
	}
	
	//affichage des rois (image .png en 28*68px)
	public void affichageRoi()
	{
		if (this.couleur == Color.BLUE)
		{
			if (this.nbRois == 1)StdDraw.picture( 750, 650, "roiBleu.png");
			else
			{
				StdDraw.picture( 750, 650, "roiBleu.png");
				StdDraw.picture( 780, 650, "roiBleu.png");
			}
		}
		
		if (this.couleur == Color.RED)
		{
			if (this.nbRois == 1)StdDraw.picture( 750, 550, "roiRouge.png");
			else
			{
				StdDraw.picture( 750, 550, "roiRouge.png");
				StdDraw.picture( 780, 550, "roiRouge.png");
			}
		}
		
		if (this.couleur == Color.GREEN)StdDraw.picture( 750, 450, "roiVert.png");
		if (this.couleur == Color.PINK)StdDraw.picture( 750, 350, "roiRose.png");
	}
	
}