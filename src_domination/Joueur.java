import java.awt.Color;
import java.util.ArrayList;

import edu.princeton.cs.introcs.StdDraw;

public class Joueur {
	
	private String pseudo = "NONAME";
	private Color couleur = Color.BLACK;
	private int nb_points = 0;
	private Plateau plateau = new Plateau(5,5);
	private int nbRois = 0;
	
	//attibut pierre
	ArrayList<Integer> choix_tuile_tour = new ArrayList<Integer>();
	
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
			if (this.nbRois == 1)StdDraw.picture( 750, 650, "img/roiBleu.png");
			else
			{
				StdDraw.picture( 750, 650, "img/roiBleu.png");
				StdDraw.picture( 780, 650, "img/roiBleu.png");
			}
		}
		
		if (this.couleur == Color.RED)
		{
			if (this.nbRois == 1)StdDraw.picture( 750, 550, "img/roiRouge.png");
			else
			{
				StdDraw.picture( 750, 550, "img/roiRouge.png");
				StdDraw.picture( 780, 550, "img/roiRouge.png");
			}
		}
		
		if (this.couleur == Color.GREEN)StdDraw.picture( 750, 450, "img/roiVert.png");
		if (this.couleur == Color.PINK)StdDraw.picture( 750, 350, "img/roiRose.png");
	}
	
	public void choix_tuile_tour(int Nbjoueur, boolean flagRoi2)
	{
		final int L_TUILE = 136;
		//&& !StdDraw.isMousePressed()
		while(StdDraw.mouseX()>=1040-(L_TUILE/4) && StdDraw.mouseX()<=1040+(L_TUILE/2)+(L_TUILE/4) && StdDraw.mouseY()>=540-(L_TUILE/4) && StdDraw.mouseY()<=540+(L_TUILE/4))
		{
			
			//couleur pion sur tuile
			if (this.couleur == Color.BLUE)StdDraw.picture( 1040+(L_TUILE/4), 540, "img/roiBleu.png");
			else if (this.couleur == Color.RED)StdDraw.picture( 1040+(L_TUILE/4), 540, "img/roiRouge.png");
			else if (this.couleur == Color.GREEN)StdDraw.picture( 1040+(L_TUILE/4), 540, "img/roiVert.png");
			else if (this.couleur == Color.PINK)StdDraw.picture( 1040+(L_TUILE/4), 540, "img/roiRose.png");
			
			StdDraw.show();
			if (StdDraw.isMousePressed() && this.choix_tuile_tour.size()<=this.nbRois-1)
				{
				
				//remédier au problème de remplissage de la liste
				if (flagRoi2 == false && this.choix_tuile_tour.size()==0) this.choix_tuile_tour.add(1);
				else if (flagRoi2 == true && this.choix_tuile_tour.size()==1)this.choix_tuile_tour.add(1);
				
				System.out.println("validé");
				}
		}
		
		while(StdDraw.mouseX()>=1040-(L_TUILE/4) && StdDraw.mouseX()<=1040+(L_TUILE/2)+(L_TUILE/4) && StdDraw.mouseY()>=450-(L_TUILE/4) && StdDraw.mouseY()<=450+(L_TUILE/4))
		{
			
			//couleur pion sur tuile
			if (this.couleur == Color.BLUE)StdDraw.picture( 1040+(L_TUILE/4), 450, "img/roiBleu.png");
			else if (this.couleur == Color.RED)StdDraw.picture( 1040+(L_TUILE/4), 450, "img/roiRouge.png");
			else if (this.couleur == Color.GREEN)StdDraw.picture( 1040+(L_TUILE/4), 450, "img/roiVert.png");
			else if (this.couleur == Color.PINK)StdDraw.picture( 1040+(L_TUILE/4), 450, "img/roiRose.png");
			
			StdDraw.show();
			if (StdDraw.isMousePressed() && this.choix_tuile_tour.size()<=this.nbRois-1)
				{
				
				//remédier au problème de remplissage de la liste
				if (flagRoi2 == false && this.choix_tuile_tour.size()==0) this.choix_tuile_tour.add(2);
				else if (flagRoi2 == true && this.choix_tuile_tour.size()==1)this.choix_tuile_tour.add(2);
								
				System.out.println("validé");
				}
		}
		
		while(StdDraw.mouseX()>=1040-(L_TUILE/4) && StdDraw.mouseX()<=1040+(L_TUILE/2)+(L_TUILE/4) && StdDraw.mouseY()>=360-(L_TUILE/4) && StdDraw.mouseY()<=360+(L_TUILE/4))
		{
			
			//couleur pion sur tuile
			if (this.couleur == Color.BLUE)StdDraw.picture( 1040+(L_TUILE/4), 360, "img/roiBleu.png");
			else if (this.couleur == Color.RED)StdDraw.picture( 1040+(L_TUILE/4), 360, "img/roiRouge.png");
			else if (this.couleur == Color.GREEN)StdDraw.picture( 1040+(L_TUILE/4), 360, "img/roiVert.png");
			else if (this.couleur == Color.PINK)StdDraw.picture( 1040+(L_TUILE/4), 360, "img/roiRose.png");
			
			StdDraw.show();
			if (StdDraw.isMousePressed() && this.choix_tuile_tour.size()<=this.nbRois-1)
				{
				
				//remédier au problème de remplissage de la liste
				if (flagRoi2 == false && this.choix_tuile_tour.size()==0) this.choix_tuile_tour.add(3);
				else if (flagRoi2 == true && this.choix_tuile_tour.size()==1)this.choix_tuile_tour.add(3);
				
				System.out.println("validé");
				}
		}
		
		if(Nbjoueur != 3)
		{
			while(StdDraw.mouseX()>=1040-(L_TUILE/4) && StdDraw.mouseX()<=1040+(L_TUILE/2)+(L_TUILE/4) && StdDraw.mouseY()>=270-(L_TUILE/4) && StdDraw.mouseY()<=270+(L_TUILE/4))
			{
				
				//couleur pion sur tuile
				if (this.couleur == Color.BLUE)StdDraw.picture( 1040+(L_TUILE/4), 270, "img/roiBleu.png");
				else if (this.couleur == Color.RED)StdDraw.picture( 1040+(L_TUILE/4), 270, "img/roiRouge.png");
				else if (this.couleur == Color.GREEN)StdDraw.picture( 1040+(L_TUILE/4), 270, "img/roiVert.png");
				else if (this.couleur == Color.PINK)StdDraw.picture( 1040+(L_TUILE/4), 270, "img/roiRose.png");
				
				StdDraw.show();
				if (StdDraw.isMousePressed() && this.choix_tuile_tour.size()<=this.nbRois-1)
					{
					
					//remédier au problème de remplissage de la liste
					if (flagRoi2 == false && this.choix_tuile_tour.size()==0) this.choix_tuile_tour.add(4);
					else if (flagRoi2 == true && this.choix_tuile_tour.size()==1)this.choix_tuile_tour.add(4);
					
					System.out.println("validé");
					}
			}
		}
	}
}