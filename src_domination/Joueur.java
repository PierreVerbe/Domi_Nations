import java.awt.Color;
import java.util.ArrayList;

import edu.princeton.cs.introcs.StdDraw;

public class Joueur {
	
	private String pseudo = "NONAME";
	private Color couleur = Color.BLACK;
	private int nb_points = 0;
	private Plateau plateau = new Plateau(5,5);
	private int nbRois = 0;
	private ArrayList<Integer> choix_tuile_tour = new ArrayList<Integer>(); //new attribut pierre
	
	public Joueur(){}

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
	public ArrayList<Integer> getChoix_tuile_tour() {
		return choix_tuile_tour;
	}
	public void setChoix_tuile_tour(ArrayList<Integer> choix_tuile_tour) {
		this.choix_tuile_tour = choix_tuile_tour;
	}
	public void infoJoueur(){
		System.out.println("Caractéristiques joueurs:");
		System.out.println("Pseudo -> " + this.pseudo);
		if (this.couleur == Color.BLUE) System.out.println("Votre couleur -> Bleu");
		else if (this.couleur == Color.RED) System.out.println("Votre couleur -> Rouge");
		else if (this.couleur == Color.GREEN) System.out.println("Votre couleur -> Vert");
		else if (this.couleur == Color.PINK) System.out.println("Votre couleur -> Rose");
		System.out.println("Nombre de rois -> " +this.nbRois);
		System.out.println();
	}
	
	//affichage des rois, image format png en 28*68px
	public void affichageRoi(){		
		if (this.couleur == Color.BLUE){
			if (this.nbRois == 1)StdDraw.picture( 750, 650, "img/roiBleu.png");
			else{
				StdDraw.picture( 750, 650, "img/roiBleu.png");
				StdDraw.picture( 780, 650, "img/roiBleu.png");
			}
		}
		
		if (this.couleur == Color.RED){
			if (this.nbRois == 1)StdDraw.picture( 750, 550, "img/roiRouge.png");
			else{
				StdDraw.picture( 750, 550, "img/roiRouge.png");
				StdDraw.picture( 780, 550, "img/roiRouge.png");
			}
		}
		
		if (this.couleur == Color.GREEN)StdDraw.picture( 750, 450, "img/roiVert.png");
		if (this.couleur == Color.PINK)StdDraw.picture( 750, 350, "img/roiRose.png");
	}
	
	//Sélection premier roi joueur 1
	public void placementRoiJ1p(Jeu monJeu, Pioche maPioche, int nbJoueur, int ordonnanceurJoueur){	
		if (StdDraw.mouseX()>=726  && StdDraw.mouseX()<=774 && StdDraw.mouseY()>=616 && StdDraw.mouseY()<=684 && StdDraw.isMousePressed() && monJeu.getOrdre_tour_joueur().get(ordonnanceurJoueur) == 1 && this.choix_tuile_tour.size()==0){
			while(StdDraw.isMousePressed());
			while (!StdDraw.isMousePressed()){
				//préparation affichage de tous les éléments de la fenêtre (plateaux + pioche)
				monJeu.affichageGlobal(nbJoueur);
				maPioche.AffichageTuilesTour();
				
				this.choix_tuile_tour(monJeu, nbJoueur, false);
				//System.out.println("je prends " + this.getChoix_tuile_tour());
				
				StdDraw.picture( StdDraw.mouseX(), StdDraw.mouseY(), "img/roiBleu.png");
				StdDraw.show();
				StdDraw.pause(1);
				StdDraw.clear(StdDraw.GRAY);
			}
			if (this.getChoix_tuile_tour().size() == 1)monJeu.addNb_roi_place();
			}
	}
	
	//Sélection deuxième roi joueur 1
	public void placementRoiJ1d(Jeu monJeu, Pioche maPioche, int nbJoueur, int ordonnanceurJoueur){	
		if(StdDraw.mouseX()>=780-14  && StdDraw.mouseX()<=780+14 && StdDraw.mouseY()>=616 && StdDraw.mouseY()<=684 && StdDraw.isMousePressed() && monJeu.getOrdre_tour_joueur().get(ordonnanceurJoueur) == 1 && this.choix_tuile_tour.size()==1){
			while(StdDraw.isMousePressed());
			while (!StdDraw.isMousePressed()){
				//préparation affichage de tous les éléments de la fenêtre (plateaux + pioche)
				monJeu.affichageGlobal(nbJoueur);
				maPioche.AffichageTuilesTour();
				
				monJeu.getListe_joueurs().get(0).choix_tuile_tour(monJeu, nbJoueur, true);
				//System.out.println("je prends " + monJeu.getListe_joueurs().get(0).getChoix_tuile_tour());
				
				StdDraw.picture( StdDraw.mouseX(), StdDraw.mouseY(), "img/roiBleu.png");
				StdDraw.show();
				StdDraw.pause(1);
				StdDraw.clear(StdDraw.GRAY);
			}
			if (monJeu.getListe_joueurs().get(0).getChoix_tuile_tour().size() == 2)monJeu.addNb_roi_place();
		}
	}
	
	//Sélection premier roi joueur 2
	public void placementRoiJ2p(Jeu monJeu, Pioche maPioche, int nbJoueur, int ordonnanceurJoueur){	
		if (StdDraw.mouseX()>=726  && StdDraw.mouseX()<=774 && StdDraw.mouseY()>=516 && StdDraw.mouseY()<=584 && StdDraw.isMousePressed() && monJeu.getOrdre_tour_joueur().get(ordonnanceurJoueur) == 2 && this.choix_tuile_tour.size()==0){
			while(StdDraw.isMousePressed());
			while (!StdDraw.isMousePressed()){
				//préparation affichage de tous les éléments de la fenêtre (plateaux + pioche)
				monJeu.affichageGlobal(nbJoueur);
				maPioche.AffichageTuilesTour();
				
				monJeu.getListe_joueurs().get(1).choix_tuile_tour(monJeu, nbJoueur, false);
				//System.out.println("je prends " + monJeu.getListe_joueurs().get(1).getChoix_tuile_tour());
				
				StdDraw.picture( StdDraw.mouseX(), StdDraw.mouseY(), "img/roiRouge.png");
				StdDraw.show();
				StdDraw.pause(1);
				StdDraw.clear(StdDraw.GRAY);
			}
			if (monJeu.getListe_joueurs().get(1).getChoix_tuile_tour().size() == 1)monJeu.addNb_roi_place();
		 }
	}
	
	
	//Sélection deuxième roi joueur 2
	public void placementRoiJ2d(Jeu monJeu, Pioche maPioche, int nbJoueur, int ordonnanceurJoueur){	
		if(StdDraw.mouseX()>=780-14  && StdDraw.mouseX()<=780+14 && StdDraw.mouseY()>=516 && StdDraw.mouseY()<=584 && StdDraw.isMousePressed() && monJeu.getOrdre_tour_joueur().get(ordonnanceurJoueur) == 2 && this.choix_tuile_tour.size()==1){
			while(StdDraw.isMousePressed());
			while (!StdDraw.isMousePressed()){
				//préparation affichage de tous les éléments de la fenêtre (plateaux + pioche)
				monJeu.affichageGlobal(nbJoueur);
				maPioche.AffichageTuilesTour();
				
				monJeu.getListe_joueurs().get(1).choix_tuile_tour(monJeu, nbJoueur, true);
				//System.out.println("je prends " + monJeu.getListe_joueurs().get(1).getChoix_tuile_tour());
				
				StdDraw.picture( StdDraw.mouseX(), StdDraw.mouseY(), "img/roiRouge.png");
				StdDraw.show();
				StdDraw.pause(1);
				StdDraw.clear(StdDraw.GRAY);
			}
			if (monJeu.getListe_joueurs().get(1).getChoix_tuile_tour().size() == 2)monJeu.addNb_roi_place();
		}
	}
	
	//Sélection roi joueur 3
	public void placementRoiJ3p(Jeu monJeu, Pioche maPioche, int nbJoueur, int ordonnanceurJoueur){	
		if (StdDraw.mouseX()>=726  && StdDraw.mouseX()<=774 && StdDraw.mouseY()>=416 && StdDraw.mouseY()<=484 && StdDraw.isMousePressed() && monJeu.getOrdre_tour_joueur().get(ordonnanceurJoueur) == 3 && this.choix_tuile_tour.size()==0){
			while(StdDraw.isMousePressed());
			while (!StdDraw.isMousePressed()){
				//préparation affichage de tous les éléments de la fenêtre (plateaux + pioche)
				monJeu.affichageGlobal(nbJoueur);
				maPioche.AffichageTuilesTour();
				
				monJeu.getListe_joueurs().get(2).choix_tuile_tour(monJeu, nbJoueur, false);
				//System.out.println("je prends " + monJeu.getListe_joueurs().get(2).getChoix_tuile_tour());
				
				StdDraw.picture( StdDraw.mouseX(), StdDraw.mouseY(), "img/roiVert.png");
				StdDraw.show();
				StdDraw.pause(1);
				StdDraw.clear(StdDraw.GRAY);
			}
			if (monJeu.getListe_joueurs().get(2).getChoix_tuile_tour().size() == 1)monJeu.addNb_roi_place();
		}
	}
	
	//Sélection roi joueur 4
	public void placementRoiJ4p(Jeu monJeu, Pioche maPioche, int nbJoueur, int ordonnanceurJoueur){	
		if (StdDraw.mouseX()>=726  && StdDraw.mouseX()<=774 && StdDraw.mouseY()>=316 && StdDraw.mouseY()<=384 && StdDraw.isMousePressed() && monJeu.getOrdre_tour_joueur().get(ordonnanceurJoueur) == 4 && this.choix_tuile_tour.size()==0){
			while(StdDraw.isMousePressed());
			while (!StdDraw.isMousePressed()){
				//préparation affichage de tous les éléments de la fenêtre (plateaux + pioche)
				monJeu.affichageGlobal(nbJoueur);
				maPioche.AffichageTuilesTour();
				
				monJeu.getListe_joueurs().get(3).choix_tuile_tour(monJeu, nbJoueur, false);
				//System.out.println("je prends " + monJeu.getListe_joueurs().get(3).getChoix_tuile_tour());
				
				StdDraw.picture( StdDraw.mouseX(), StdDraw.mouseY(), "img/roiRose.png");
				StdDraw.show();
				StdDraw.pause(1);
				StdDraw.clear(StdDraw.GRAY);
			}
			if (monJeu.getListe_joueurs().get(3).getChoix_tuile_tour().size() == 1)monJeu.addNb_roi_place();
		}
	}
	
	public void choix_tuile_tour(Jeu monJeu, int Nbjoueur, boolean flagRoi2){
		final int L_TUILE = 136;
		
		while(StdDraw.mouseX()>=1040-(L_TUILE/4) && StdDraw.mouseX()<=1040+(L_TUILE/2)+(L_TUILE/4) && StdDraw.mouseY()>=540-(L_TUILE/4) && StdDraw.mouseY()<=540+(L_TUILE/4)){
			//couleur pion sur tuile
			if (this.couleur == Color.BLUE)StdDraw.picture( 1040+(L_TUILE/4), 540, "img/roiBleu.png");
			else if (this.couleur == Color.RED)StdDraw.picture( 1040+(L_TUILE/4), 540, "img/roiRouge.png");
			else if (this.couleur == Color.GREEN)StdDraw.picture( 1040+(L_TUILE/4), 540, "img/roiVert.png");
			else if (this.couleur == Color.PINK)StdDraw.picture( 1040+(L_TUILE/4), 540, "img/roiRose.png");
			
			StdDraw.show();
			
			if (StdDraw.isMousePressed() && this.choix_tuile_tour.size()<=this.nbRois-1 && monJeu.isTuileOccupied(1) == false){
				//remédier au problème de remplissage de la liste
				if (flagRoi2 == false && this.choix_tuile_tour.size()==0) this.choix_tuile_tour.add(1);
				else if (flagRoi2 == true && this.choix_tuile_tour.size()==1)this.choix_tuile_tour.add(1);
				System.out.println("validé");
				}
			
			else if (StdDraw.isMousePressed() && this.choix_tuile_tour.size()<=this.nbRois-1 && monJeu.isTuileOccupied(1) == true)System.out.println("occupé");
		}
		
		while(StdDraw.mouseX()>=1040-(L_TUILE/4) && StdDraw.mouseX()<=1040+(L_TUILE/2)+(L_TUILE/4) && StdDraw.mouseY()>=450-(L_TUILE/4) && StdDraw.mouseY()<=450+(L_TUILE/4)){
			//couleur pion sur tuile
			if (this.couleur == Color.BLUE)StdDraw.picture( 1040+(L_TUILE/4), 450, "img/roiBleu.png");
			else if (this.couleur == Color.RED)StdDraw.picture( 1040+(L_TUILE/4), 450, "img/roiRouge.png");
			else if (this.couleur == Color.GREEN)StdDraw.picture( 1040+(L_TUILE/4), 450, "img/roiVert.png");
			else if (this.couleur == Color.PINK)StdDraw.picture( 1040+(L_TUILE/4), 450, "img/roiRose.png");
			
			StdDraw.show();
			
			if (StdDraw.isMousePressed() && this.choix_tuile_tour.size()<=this.nbRois-1 && monJeu.isTuileOccupied(2) == false){
				//remédier au problème de remplissage de la liste
				if (flagRoi2 == false && this.choix_tuile_tour.size()==0) this.choix_tuile_tour.add(2);
				else if (flagRoi2 == true && this.choix_tuile_tour.size()==1)this.choix_tuile_tour.add(2);			
				System.out.println("validé");
				}
			
			else if (StdDraw.isMousePressed() && this.choix_tuile_tour.size()<=this.nbRois-1 && monJeu.isTuileOccupied(2) == true)System.out.println("occupé");
		}
		
		while(StdDraw.mouseX()>=1040-(L_TUILE/4) && StdDraw.mouseX()<=1040+(L_TUILE/2)+(L_TUILE/4) && StdDraw.mouseY()>=360-(L_TUILE/4) && StdDraw.mouseY()<=360+(L_TUILE/4)){
			//couleur pion sur tuile
			if (this.couleur == Color.BLUE)StdDraw.picture( 1040+(L_TUILE/4), 360, "img/roiBleu.png");
			else if (this.couleur == Color.RED)StdDraw.picture( 1040+(L_TUILE/4), 360, "img/roiRouge.png");
			else if (this.couleur == Color.GREEN)StdDraw.picture( 1040+(L_TUILE/4), 360, "img/roiVert.png");
			else if (this.couleur == Color.PINK)StdDraw.picture( 1040+(L_TUILE/4), 360, "img/roiRose.png");
			
			StdDraw.show();
			
			if (StdDraw.isMousePressed() && this.choix_tuile_tour.size()<=this.nbRois-1 && monJeu.isTuileOccupied(3) == false){
				//remédier au problème de remplissage de la liste
				if (flagRoi2 == false && this.choix_tuile_tour.size()==0) this.choix_tuile_tour.add(3);
				else if (flagRoi2 == true && this.choix_tuile_tour.size()==1)this.choix_tuile_tour.add(3);
				System.out.println("validé");
				}
			
			else if (StdDraw.isMousePressed() && this.choix_tuile_tour.size()<=this.nbRois-1 && monJeu.isTuileOccupied(3) == true)System.out.println("occupé");
		}
		
		if(Nbjoueur != 3){
			while(StdDraw.mouseX()>=1040-(L_TUILE/4) && StdDraw.mouseX()<=1040+(L_TUILE/2)+(L_TUILE/4) && StdDraw.mouseY()>=270-(L_TUILE/4) && StdDraw.mouseY()<=270+(L_TUILE/4)){
				//couleur pion sur tuile
				if (this.couleur == Color.BLUE)StdDraw.picture( 1040+(L_TUILE/4), 270, "img/roiBleu.png");
				else if (this.couleur == Color.RED)StdDraw.picture( 1040+(L_TUILE/4), 270, "img/roiRouge.png");
				else if (this.couleur == Color.GREEN)StdDraw.picture( 1040+(L_TUILE/4), 270, "img/roiVert.png");
				else if (this.couleur == Color.PINK)StdDraw.picture( 1040+(L_TUILE/4), 270, "img/roiRose.png");
				
				StdDraw.show();
				
				if (StdDraw.isMousePressed() && this.choix_tuile_tour.size()<=this.nbRois-1 && monJeu.isTuileOccupied(4) == false){
					//remédier au problème de remplissage de la liste
					if (flagRoi2 == false && this.choix_tuile_tour.size()==0) this.choix_tuile_tour.add(4);
					else if (flagRoi2 == true && this.choix_tuile_tour.size()==1)this.choix_tuile_tour.add(4);
					System.out.println("validé");
					}
				
				else if (StdDraw.isMousePressed() && this.choix_tuile_tour.size()<=this.nbRois-1 && monJeu.isTuileOccupied(4) == true)System.out.println("occupé");
			}
		}
	}
}