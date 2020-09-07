package com.isep.domination;

import java.awt.Color;
import java.util.ArrayList;

import edu.princeton.cs.introcs.StdDraw;

public class Joueur {
	
	private String pseudo = "NONAME";
	private Color couleur = Color.BLACK;
	private Plateau plateau = new Plateau(5,5);
	private int nbRois = 0;
	private ArrayList<Integer> choix_tuile_tour = new ArrayList<Integer>(); //new attribut pierre
	private int scoreJoueur = 0;
	
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
	public String[][] getPlateauJoueur() {
		return this.plateau.getTableau_tuiles_plateau();
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
	public int getScoreJoueur() {
		return scoreJoueur;
	}
	public void setScoreJoueur(int scoreJoueur) {
		this.scoreJoueur = scoreJoueur;
	}

	public void infoJoueur(){
		System.out.println("Caract�ristiques joueurs:");
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
	
	//S�lection premier roi joueur 1
	public void placementRoiJ1p(Jeu monJeu, Pioche maPioche, int nbJoueur, int ordonnanceurJoueur){	
		if (StdDraw.mouseX()>=726  && StdDraw.mouseX()<=774 && StdDraw.mouseY()>=616 && StdDraw.mouseY()<=684 && StdDraw.isMousePressed() && monJeu.getOrdre_tour_joueur().get(ordonnanceurJoueur) == 1 && this.choix_tuile_tour.size()==0){
			while(StdDraw.isMousePressed());
			while (!StdDraw.isMousePressed()){
				//pr�paration affichage de tous les �l�ments de la fen�tre (plateaux + pioche)
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
	
	//S�lection deuxi�me roi joueur 1
	public void placementRoiJ1d(Jeu monJeu, Pioche maPioche, int nbJoueur, int ordonnanceurJoueur){	
		if(StdDraw.mouseX()>=780-14  && StdDraw.mouseX()<=780+14 && StdDraw.mouseY()>=616 && StdDraw.mouseY()<=684 && StdDraw.isMousePressed() && monJeu.getOrdre_tour_joueur().get(ordonnanceurJoueur) == 1 && this.choix_tuile_tour.size()==1){
			while(StdDraw.isMousePressed());
			while (!StdDraw.isMousePressed()){
				//pr�paration affichage de tous les �l�ments de la fen�tre (plateaux + pioche)
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
	
	//S�lection premier roi joueur 2
	public void placementRoiJ2p(Jeu monJeu, Pioche maPioche, int nbJoueur, int ordonnanceurJoueur){	
		if (StdDraw.mouseX()>=726  && StdDraw.mouseX()<=774 && StdDraw.mouseY()>=516 && StdDraw.mouseY()<=584 && StdDraw.isMousePressed() && monJeu.getOrdre_tour_joueur().get(ordonnanceurJoueur) == 2 && this.choix_tuile_tour.size()==0){
			while(StdDraw.isMousePressed());
			while (!StdDraw.isMousePressed()){
				//pr�paration affichage de tous les �l�ments de la fen�tre (plateaux + pioche)
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
	
	
	//S�lection deuxi�me roi joueur 2
	public void placementRoiJ2d(Jeu monJeu, Pioche maPioche, int nbJoueur, int ordonnanceurJoueur){	
		if(StdDraw.mouseX()>=780-14  && StdDraw.mouseX()<=780+14 && StdDraw.mouseY()>=516 && StdDraw.mouseY()<=584 && StdDraw.isMousePressed() && monJeu.getOrdre_tour_joueur().get(ordonnanceurJoueur) == 2 && this.choix_tuile_tour.size()==1){
			while(StdDraw.isMousePressed());
			while (!StdDraw.isMousePressed()){
				//pr�paration affichage de tous les �l�ments de la fen�tre (plateaux + pioche)
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
	
	//S�lection roi joueur 3
	public void placementRoiJ3p(Jeu monJeu, Pioche maPioche, int nbJoueur, int ordonnanceurJoueur){	
		if (StdDraw.mouseX()>=726  && StdDraw.mouseX()<=774 && StdDraw.mouseY()>=416 && StdDraw.mouseY()<=484 && StdDraw.isMousePressed() && monJeu.getOrdre_tour_joueur().get(ordonnanceurJoueur) == 3 && this.choix_tuile_tour.size()==0){
			while(StdDraw.isMousePressed());
			while (!StdDraw.isMousePressed()){
				//pr�paration affichage de tous les �l�ments de la fen�tre (plateaux + pioche)
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
	
	//S�lection roi joueur 4
	public void placementRoiJ4p(Jeu monJeu, Pioche maPioche, int nbJoueur, int ordonnanceurJoueur){	
		if (StdDraw.mouseX()>=726  && StdDraw.mouseX()<=774 && StdDraw.mouseY()>=316 && StdDraw.mouseY()<=384 && StdDraw.isMousePressed() && monJeu.getOrdre_tour_joueur().get(ordonnanceurJoueur) == 4 && this.choix_tuile_tour.size()==0){
			while(StdDraw.isMousePressed());
			while (!StdDraw.isMousePressed()){
				//pr�paration affichage de tous les �l�ments de la fen�tre (plateaux + pioche)
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
				//rem�dier au probl�me de remplissage de la liste
				if (flagRoi2 == false && this.choix_tuile_tour.size()==0) this.choix_tuile_tour.add(1);
				else if (flagRoi2 == true && this.choix_tuile_tour.size()==1)this.choix_tuile_tour.add(1);
				System.out.println("valid�");
				}
			
			else if (StdDraw.isMousePressed() && this.choix_tuile_tour.size()<=this.nbRois-1 && monJeu.isTuileOccupied(1) == true)System.out.println("occup�");
		}
		
		while(StdDraw.mouseX()>=1040-(L_TUILE/4) && StdDraw.mouseX()<=1040+(L_TUILE/2)+(L_TUILE/4) && StdDraw.mouseY()>=450-(L_TUILE/4) && StdDraw.mouseY()<=450+(L_TUILE/4)){
			//couleur pion sur tuile
			if (this.couleur == Color.BLUE)StdDraw.picture( 1040+(L_TUILE/4), 450, "img/roiBleu.png");
			else if (this.couleur == Color.RED)StdDraw.picture( 1040+(L_TUILE/4), 450, "img/roiRouge.png");
			else if (this.couleur == Color.GREEN)StdDraw.picture( 1040+(L_TUILE/4), 450, "img/roiVert.png");
			else if (this.couleur == Color.PINK)StdDraw.picture( 1040+(L_TUILE/4), 450, "img/roiRose.png");
			
			StdDraw.show();
			
			if (StdDraw.isMousePressed() && this.choix_tuile_tour.size()<=this.nbRois-1 && monJeu.isTuileOccupied(2) == false){
				//rem�dier au probl�me de remplissage de la liste
				if (flagRoi2 == false && this.choix_tuile_tour.size()==0) this.choix_tuile_tour.add(2);
				else if (flagRoi2 == true && this.choix_tuile_tour.size()==1)this.choix_tuile_tour.add(2);			
				System.out.println("valid�");
				}
			
			else if (StdDraw.isMousePressed() && this.choix_tuile_tour.size()<=this.nbRois-1 && monJeu.isTuileOccupied(2) == true)System.out.println("occup�");
		}
		
		while(StdDraw.mouseX()>=1040-(L_TUILE/4) && StdDraw.mouseX()<=1040+(L_TUILE/2)+(L_TUILE/4) && StdDraw.mouseY()>=360-(L_TUILE/4) && StdDraw.mouseY()<=360+(L_TUILE/4)){
			//couleur pion sur tuile
			if (this.couleur == Color.BLUE)StdDraw.picture( 1040+(L_TUILE/4), 360, "img/roiBleu.png");
			else if (this.couleur == Color.RED)StdDraw.picture( 1040+(L_TUILE/4), 360, "img/roiRouge.png");
			else if (this.couleur == Color.GREEN)StdDraw.picture( 1040+(L_TUILE/4), 360, "img/roiVert.png");
			else if (this.couleur == Color.PINK)StdDraw.picture( 1040+(L_TUILE/4), 360, "img/roiRose.png");
			
			StdDraw.show();
			
			if (StdDraw.isMousePressed() && this.choix_tuile_tour.size()<=this.nbRois-1 && monJeu.isTuileOccupied(3) == false){
				//rem�dier au probl�me de remplissage de la liste
				if (flagRoi2 == false && this.choix_tuile_tour.size()==0) this.choix_tuile_tour.add(3);
				else if (flagRoi2 == true && this.choix_tuile_tour.size()==1)this.choix_tuile_tour.add(3);
				System.out.println("valid�");
				}
			
			else if (StdDraw.isMousePressed() && this.choix_tuile_tour.size()<=this.nbRois-1 && monJeu.isTuileOccupied(3) == true)System.out.println("occup�");
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
					//rem�dier au probl�me de remplissage de la liste
					if (flagRoi2 == false && this.choix_tuile_tour.size()==0) this.choix_tuile_tour.add(4);
					else if (flagRoi2 == true && this.choix_tuile_tour.size()==1)this.choix_tuile_tour.add(4);
					System.out.println("valid�");
					}
				
				else if (StdDraw.isMousePressed() && this.choix_tuile_tour.size()<=this.nbRois-1 && monJeu.isTuileOccupied(4) == true)System.out.println("occup�");
			}
		}
	}
	
	public boolean affichageTuileJoueur1(Jeu jeu, Tuile maPetiteTuile){
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		double x = StdDraw.mouseX();
		double y = StdDraw.mouseY();
		
		for (int i=0; i<5; i++){
			for (int j=0; j<5; j++){
				if((x >= 5 + i*TAILLE_CASE + i*TAILLE_LIGNE && x<= 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE) && (y <= 715 - j*TAILLE_CASE - j*TAILLE_LIGNE && y >= 715 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE)){
					if (maPetiteTuile.getRotation().equals("horizontal-croissant")){
						if (maPetiteTuile.getType_tuile1().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile1().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile1().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile1().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 
						if (maPetiteTuile.getNbCouronnes1() == 1) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes1() == 2) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes1() == 3) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/3couronnes.png");
						
						if (maPetiteTuile.getType_tuile2().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile2().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile2().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile2().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 2
						if (maPetiteTuile.getNbCouronnes2() == 1) StdDraw.picture(TAILLE_CASE/2 + 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes2() == 2) StdDraw.picture(TAILLE_CASE/2 + 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes2() == 3) StdDraw.picture(TAILLE_CASE/2 + 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/3couronnes.png");
						
						if (StdDraw.isMousePressed()) {	
							// si deborde du tableau, la tuile est d�truite (position horizontal)
							if (i<4) {
								if(jeu.getListe_joueurs().get(0).getPlateauJoueur(j, i) == null && jeu.getListe_joueurs().get(0).getPlateauJoueur(j, i+1)== null) {
									if(this.tuileCompatible(maPetiteTuile, j, i))
									{
										jeu.getListe_joueurs().get(0).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
										jeu.getListe_joueurs().get(0).setPlateauJoueur(j,i+1,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
										return true;
									}
								}
							}
						}
					}
					
					else if (maPetiteTuile.getRotation().equals("horizontal-decroissant")){
						if (maPetiteTuile.getType_tuile1().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile1().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile1().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile1().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 
						if (maPetiteTuile.getNbCouronnes1() == 1) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes1() == 2) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes1() == 3) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/3couronnes.png");
						
						if (maPetiteTuile.getType_tuile2().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 5 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 5 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile2().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 5 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile2().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 5 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 5 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile2().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 5 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 2
						if (maPetiteTuile.getNbCouronnes2() == 1) StdDraw.picture(TAILLE_CASE/2 + 5 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes2() == 2) StdDraw.picture(TAILLE_CASE/2 + 5 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes2() == 3) StdDraw.picture(TAILLE_CASE/2 + 5 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/3couronnes.png");
						
						if (StdDraw.isMousePressed()) {	
							// si deborde du tableau, la tuile est d�truite (position horizontal)
							if (i>0) {
								if(jeu.getListe_joueurs().get(0).getPlateauJoueur(j, i) == null && jeu.getListe_joueurs().get(0).getPlateauJoueur(j, i-1)== null) {
									if(this.tuileCompatible(maPetiteTuile, j, i)) {
										jeu.getListe_joueurs().get(0).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
										jeu.getListe_joueurs().get(0).setPlateauJoueur(j,i-1,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
										return true;
									}
								}
							}
						}
					}
					
					else if (maPetiteTuile.getRotation().equals("vertical-croissant")){
						if (maPetiteTuile.getType_tuile1().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile1().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile1().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile1().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 
						if (maPetiteTuile.getNbCouronnes1() == 1) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes1() == 2) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes1() == 3) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/3couronnes.png");
						
						if (maPetiteTuile.getType_tuile2().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile2().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile2().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile2().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 2
						if (maPetiteTuile.getNbCouronnes2() == 1) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes2() == 2) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes2() == 3) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/3couronnes.png");
						
						if (StdDraw.isMousePressed()) {	
							// si deborde du tableau, la tuile est d�truite (position horizontal)
							if (j>0) {
								if(jeu.getListe_joueurs().get(0).getPlateauJoueur(j, i) == null && jeu.getListe_joueurs().get(0).getPlateauJoueur(j-1, i)== null) {
									if(this.tuileCompatible(maPetiteTuile, j, i)) {
										jeu.getListe_joueurs().get(0).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
										jeu.getListe_joueurs().get(0).setPlateauJoueur(j-1,i,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
										return true;
									}
								}
							}
						}
					}
					
					else if (maPetiteTuile.getRotation().equals("vertical-decroissant")){
						if (maPetiteTuile.getType_tuile1().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile1().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile1().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile1().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 
						if (maPetiteTuile.getNbCouronnes1() == 1) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes1() == 2) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes1() == 3) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/3couronnes.png");
						
						if (maPetiteTuile.getType_tuile2().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile2().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile2().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile2().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 2
						if (maPetiteTuile.getNbCouronnes2() == 1) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes2() == 2) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes2() == 3) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/3couronnes.png");
						
						if (StdDraw.isMousePressed()) {	
							// si deborde du tableau, la tuile est d�truite (position horizontal)
							if (j<4) {
								if(jeu.getListe_joueurs().get(0).getPlateauJoueur(j, i) == null && jeu.getListe_joueurs().get(0).getPlateauJoueur(j+1, i)== null) {
									if(this.tuileCompatible(maPetiteTuile, j, i)) {
										jeu.getListe_joueurs().get(0).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
										jeu.getListe_joueurs().get(0).setPlateauJoueur(j+1,i,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
										return true;
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public boolean affichageTuileJoueur2(Jeu jeu, Tuile maPetiteTuile){
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		double x = StdDraw.mouseX();
		double y = StdDraw.mouseY();
		
		for (int i=0; i<5; i++){
			for (int j=0; j<5; j++){
				if((x >= 365 + i*TAILLE_CASE + i*TAILLE_LIGNE && x<= 365 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE) && (y <= 355 - j*TAILLE_CASE - j*TAILLE_LIGNE && y >= 355 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE)){
					if (maPetiteTuile.getRotation().equals("horizontal-croissant")){
						if (maPetiteTuile.getType_tuile1().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile1().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile1().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile1().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 
						if (maPetiteTuile.getNbCouronnes1() == 1) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes1() == 2) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes1() == 3) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/3couronnes.png");
						
						if (maPetiteTuile.getType_tuile2().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 365 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 365 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile2().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 365 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile2().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 365 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 365 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile2().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 365 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 2
						if (maPetiteTuile.getNbCouronnes2() == 1) StdDraw.picture(TAILLE_CASE/2 + 365 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes2() == 2) StdDraw.picture(TAILLE_CASE/2 + 365 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes2() == 3) StdDraw.picture(TAILLE_CASE/2 + 365 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/3couronnes.png");
						
						if (StdDraw.isMousePressed()) {	
							// si deborde du tableau, la tuile est d�truite (position horizontal)
							if (i<4) {
								if(jeu.getListe_joueurs().get(1).getPlateauJoueur(j, i) == null && jeu.getListe_joueurs().get(1).getPlateauJoueur(j, i+1)== null) {
									if(this.tuileCompatible(maPetiteTuile, j, i)) {
										jeu.getListe_joueurs().get(1).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
										jeu.getListe_joueurs().get(1).setPlateauJoueur(j,i+1,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
										return true;
									}	
								}
							}
						}
					}
					
					else if (maPetiteTuile.getRotation().equals("horizontal-decroissant")){
						if (maPetiteTuile.getType_tuile1().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile1().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile1().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile1().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 
						if (maPetiteTuile.getNbCouronnes1() == 1) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes1() == 2) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes1() == 3) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/3couronnes.png");
						
						if (maPetiteTuile.getType_tuile2().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 365 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 365 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile2().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 365 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile2().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 365 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 365 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile2().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 365 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 2
						if (maPetiteTuile.getNbCouronnes2() == 1) StdDraw.picture(TAILLE_CASE/2 + 365 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes2() == 2) StdDraw.picture(TAILLE_CASE/2 + 365 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes2() == 3) StdDraw.picture(TAILLE_CASE/2 + 365 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/3couronnes.png");
						
						if (StdDraw.isMousePressed()) {	
							// si deborde du tableau, la tuile est d�truite (position horizontal)
							if (i>0) {
								if(jeu.getListe_joueurs().get(1).getPlateauJoueur(j, i) == null && jeu.getListe_joueurs().get(1).getPlateauJoueur(j, i-1)== null) {
									if(this.tuileCompatible(maPetiteTuile, j, i)) {
										jeu.getListe_joueurs().get(1).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
										jeu.getListe_joueurs().get(1).setPlateauJoueur(j,i-1,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
										return true;
									}
								}
							}
						}
					}
					
					else if (maPetiteTuile.getRotation().equals("vertical-croissant")){
						if (maPetiteTuile.getType_tuile1().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile1().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile1().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile1().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 
						if (maPetiteTuile.getNbCouronnes1() == 1) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes1() == 2) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes1() == 3) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/3couronnes.png");
						
						if (maPetiteTuile.getType_tuile2().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile2().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile2().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile2().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 2
						if (maPetiteTuile.getNbCouronnes2() == 1) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes2() == 2) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes2() == 3) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/3couronnes.png");
						
						if (StdDraw.isMousePressed()) {	
							// si deborde du tableau, la tuile est d�truite (position horizontal)
							if (j>0) {
								if(jeu.getListe_joueurs().get(1).getPlateauJoueur(j, i) == null && jeu.getListe_joueurs().get(1).getPlateauJoueur(j-1, i)== null) {
									if(this.tuileCompatible(maPetiteTuile, j, i)) {
										jeu.getListe_joueurs().get(1).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
										jeu.getListe_joueurs().get(1).setPlateauJoueur(j-1,i,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
										return true;
									}
								}
							}
						}
					}
					
					else if (maPetiteTuile.getRotation().equals("vertical-decroissant")){
						if (maPetiteTuile.getType_tuile1().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile1().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile1().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile1().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 
						if (maPetiteTuile.getNbCouronnes1() == 1) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes1() == 2) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes1() == 3) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/3couronnes.png");
						
						if (maPetiteTuile.getType_tuile2().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile2().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile2().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile2().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 2
						if (maPetiteTuile.getNbCouronnes2() == 1) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes2() == 2) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes2() == 3) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/3couronnes.png");
						
						if (StdDraw.isMousePressed()) {	
							// si deborde du tableau, la tuile est d�truite (position horizontal)
							if (j<4) {
								if(jeu.getListe_joueurs().get(1).getPlateauJoueur(j, i) == null && jeu.getListe_joueurs().get(1).getPlateauJoueur(j+1, i)== null) {
									if(this.tuileCompatible(maPetiteTuile, j, i)) {
										jeu.getListe_joueurs().get(1).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
										jeu.getListe_joueurs().get(1).setPlateauJoueur(j+1,i,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
										return true;
									}
								}
							}
						}
					}					
				}
			}
		}
		return false;
	}
	
	public boolean affichageTuileJoueur3(Jeu jeu, Tuile maPetiteTuile){
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		double x = StdDraw.mouseX();
		double y = StdDraw.mouseY();
		
		for (int i=0; i<5; i++){
			for (int j=0; j<5; j++){
				if((x >= 365 + i*TAILLE_CASE + i*TAILLE_LIGNE && x<= 365 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE) && (y <= 715 - j*TAILLE_CASE - j*TAILLE_LIGNE && y >= 715 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE)){
					if (maPetiteTuile.getRotation().equals("horizontal-croissant")){
						if (maPetiteTuile.getType_tuile1().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile1().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile1().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile1().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 
						if (maPetiteTuile.getNbCouronnes1() == 1) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes1() == 2) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes1() == 3) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/3couronnes.png");
						
						
						if (maPetiteTuile.getType_tuile2().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 365 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 365 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile2().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 365 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile2().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 365 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 365 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile2().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 365 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 2
						if (maPetiteTuile.getNbCouronnes2() == 1) StdDraw.picture(TAILLE_CASE/2 + 365 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes2() == 2) StdDraw.picture(TAILLE_CASE/2 + 365 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes2() == 3) StdDraw.picture(TAILLE_CASE/2 + 365 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/3couronnes.png");
						
						if (StdDraw.isMousePressed()) {	
							// si deborde du tableau, la tuile est d�truite (position horizontal)
							if (i<4) {
								if(jeu.getListe_joueurs().get(2).getPlateauJoueur(j, i) == null && jeu.getListe_joueurs().get(2).getPlateauJoueur(j, i+1)== null) {
									if(this.tuileCompatible(maPetiteTuile, j, i)) {
										jeu.getListe_joueurs().get(2).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
										jeu.getListe_joueurs().get(2).setPlateauJoueur(j,i+1,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
										return true;
									}
									
								}	
							}
						}
					}
					
					else if (maPetiteTuile.getRotation().equals("horizontal-decroissant")){
						if (maPetiteTuile.getType_tuile1().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile1().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile1().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile1().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 
						if (maPetiteTuile.getNbCouronnes1() == 1) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes1() == 2) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes1() == 3) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/3couronnes.png");
						
						
						if (maPetiteTuile.getType_tuile2().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 365 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 365 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile2().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 365 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile2().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 365 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 365 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile2().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 365 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 2
						if (maPetiteTuile.getNbCouronnes2() == 1) StdDraw.picture(TAILLE_CASE/2 + 365 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes2() == 2) StdDraw.picture(TAILLE_CASE/2 + 365 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes2() == 3) StdDraw.picture(TAILLE_CASE/2 + 365 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/3couronnes.png");
						
						if (StdDraw.isMousePressed()) {	
							// si deborde du tableau, la tuile est d�truite (position horizontal)
							if (i>0) {
								if(jeu.getListe_joueurs().get(2).getPlateauJoueur(j, i) == null && jeu.getListe_joueurs().get(2).getPlateauJoueur(j, i-1)== null) {
									if(this.tuileCompatible(maPetiteTuile, j, i)) {
										jeu.getListe_joueurs().get(2).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
										jeu.getListe_joueurs().get(2).setPlateauJoueur(j,i-1,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
										return true;
									}
									
								}	
							}
						}
					}
					
					else if (maPetiteTuile.getRotation().equals("vertical-croissant")){
						if (maPetiteTuile.getType_tuile1().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile1().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile1().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile1().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 
						if (maPetiteTuile.getNbCouronnes1() == 1) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes1() == 2) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes1() == 3) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/3couronnes.png");
						
						
						if (maPetiteTuile.getType_tuile2().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile2().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile2().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile2().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 2
						if (maPetiteTuile.getNbCouronnes2() == 1) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes2() == 2) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes2() == 3) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/3couronnes.png");
						
						if (StdDraw.isMousePressed()) {	
							// si deborde du tableau, la tuile est d�truite (position horizontal)
							if (j>0) {
								if(jeu.getListe_joueurs().get(2).getPlateauJoueur(j, i) == null && jeu.getListe_joueurs().get(2).getPlateauJoueur(j-1, i)== null) {
									if(this.tuileCompatible(maPetiteTuile, j, i)) {
										jeu.getListe_joueurs().get(2).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
										jeu.getListe_joueurs().get(2).setPlateauJoueur(j-1,i,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
										return true;
									}
									
								}	
							}
						}
					}
					
					else if (maPetiteTuile.getRotation().equals("vertical-decroissant")){
						if (maPetiteTuile.getType_tuile1().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile1().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile1().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile1().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 
						if (maPetiteTuile.getNbCouronnes1() == 1) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes1() == 2) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes1() == 3) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/3couronnes.png");
						
						
						if (maPetiteTuile.getType_tuile2().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile2().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile2().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile2().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 2
						if (maPetiteTuile.getNbCouronnes2() == 1) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes2() == 2) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes2() == 3) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/3couronnes.png");
						
						if (StdDraw.isMousePressed()) {	
							// si deborde du tableau, la tuile est d�truite (position horizontal)
							if (j<4) {
								if(jeu.getListe_joueurs().get(2).getPlateauJoueur(j, i) == null && jeu.getListe_joueurs().get(2).getPlateauJoueur(j+1, i)== null) {
									if(this.tuileCompatible(maPetiteTuile, j, i)) {
										jeu.getListe_joueurs().get(2).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
										jeu.getListe_joueurs().get(2).setPlateauJoueur(j+1,i,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
										return true;
									}
									
								}	
							}
						}	
					}
				}	
			}
		}
		return false;
	}
	
	public boolean affichageTuileJoueur4(Jeu jeu, Tuile maPetiteTuile){
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		double x = StdDraw.mouseX();
		double y = StdDraw.mouseY();
		
		for (int i=0; i<5; i++){
			for (int j=0; j<5; j++){
				if((x >= 5 + i*TAILLE_CASE + i*TAILLE_LIGNE && x<= 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE) && (y <= 355 - j*TAILLE_CASE - j*TAILLE_LIGNE && y >= 355 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE)){
					if (maPetiteTuile.getRotation().equals("horizontal-croissant")){
						if (maPetiteTuile.getType_tuile1().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile1().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile1().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile1().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 
						if (maPetiteTuile.getNbCouronnes1() == 1) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes1() == 2) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes1() == 3) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/3couronnes.png");
						
						
						if (maPetiteTuile.getType_tuile2().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile2().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile2().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile2().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 2
						if (maPetiteTuile.getNbCouronnes2() == 1) StdDraw.picture(TAILLE_CASE/2 + 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes2() == 2) StdDraw.picture(TAILLE_CASE/2 + 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes2() == 3) StdDraw.picture(TAILLE_CASE/2 + 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/3couronnes.png");
						
						if (StdDraw.isMousePressed()) {	
							// si deborde du tableau, la tuile est d�truite (position horizontal)
							if (i<4) {
								if(jeu.getListe_joueurs().get(3).getPlateauJoueur(j, i) == null && jeu.getListe_joueurs().get(3).getPlateauJoueur(j, i+1)== null) {
									if(this.tuileCompatible(maPetiteTuile, j, i)) {
										jeu.getListe_joueurs().get(3).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
										jeu.getListe_joueurs().get(3).setPlateauJoueur(j,i+1,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
										return true;
									}
								
								}
							}	
						}
					}
					
					else if (maPetiteTuile.getRotation().equals("horizontal-decroissant")){
						if (maPetiteTuile.getType_tuile1().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile1().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile1().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile1().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 
						if (maPetiteTuile.getNbCouronnes1() == 1) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes1() == 2) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes1() == 3) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/3couronnes.png");
						
						
						if (maPetiteTuile.getType_tuile2().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 5 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 5 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile2().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 5 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile2().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 5 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 5 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile2().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 5 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 2
						if (maPetiteTuile.getNbCouronnes2() == 1) StdDraw.picture(TAILLE_CASE/2 + 5 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes2() == 2) StdDraw.picture(TAILLE_CASE/2 + 5 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes2() == 3) StdDraw.picture(TAILLE_CASE/2 + 5 + (i-1)*TAILLE_CASE + (i-1)*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/3couronnes.png");
						
						if (StdDraw.isMousePressed()) {	
							// si deborde du tableau, la tuile est d�truite (position horizontal)
							if (i>0) {
								if(jeu.getListe_joueurs().get(3).getPlateauJoueur(j, i) == null && jeu.getListe_joueurs().get(3).getPlateauJoueur(j, i-1)== null) {
									if(this.tuileCompatible(maPetiteTuile, j, i)) {
										jeu.getListe_joueurs().get(3).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
										jeu.getListe_joueurs().get(3).setPlateauJoueur(j,i-1,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
										return true;
									}
								
								}
							}	
						}
					
					}
					
					else if (maPetiteTuile.getRotation().equals("vertical-croissant")){
						if (maPetiteTuile.getType_tuile1().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile1().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile1().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile1().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 
						if (maPetiteTuile.getNbCouronnes1() == 1) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes1() == 2) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes1() == 3) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/3couronnes.png");
						
						
						if (maPetiteTuile.getType_tuile2().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile2().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile2().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile2().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 2
						if (maPetiteTuile.getNbCouronnes2() == 1) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes2() == 2) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes2() == 3) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j-1)*TAILLE_CASE - (j-1)*TAILLE_LIGNE, "img/3couronnes.png");
						
						if (StdDraw.isMousePressed()) {	
							// si deborde du tableau, la tuile est d�truite (position horizontal)
							if (j>0) {
								if(jeu.getListe_joueurs().get(3).getPlateauJoueur(j, i) == null && jeu.getListe_joueurs().get(3).getPlateauJoueur(j-1, i)== null) {
									if(this.tuileCompatible(maPetiteTuile, j, i)) {
										jeu.getListe_joueurs().get(3).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
										jeu.getListe_joueurs().get(3).setPlateauJoueur(j-1,i,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
										return true;
									}
								
								}
							}	
						}
					}
					
					else if (maPetiteTuile.getRotation().equals("vertical-decroissant")){
						if (maPetiteTuile.getType_tuile1().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile1().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile1().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile1().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile1().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 
						if (maPetiteTuile.getNbCouronnes1() == 1) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes1() == 2) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes1() == 3) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/3couronnes.png");
						
						
						if (maPetiteTuile.getType_tuile2().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/champs.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/mer.png");
						else if (maPetiteTuile.getType_tuile2().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/foret.png");
						else if (maPetiteTuile.getType_tuile2().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/prairie.png");
						else if (maPetiteTuile.getType_tuile2().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/mine.png");
						else if (maPetiteTuile.getType_tuile2().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/montagne.png");
						
						//couronnes 2
						if (maPetiteTuile.getNbCouronnes2() == 1) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/1couronne.png");
						else if (maPetiteTuile.getNbCouronnes2() == 2) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/2couronnes.png");
						else if (maPetiteTuile.getNbCouronnes2() == 3) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE, "img/3couronnes.png");
						
						if (StdDraw.isMousePressed()) {	
							// si deborde du tableau, la tuile est d�truite (position horizontal)
							if (j<4) {
								if(jeu.getListe_joueurs().get(3).getPlateauJoueur(j, i) == null && jeu.getListe_joueurs().get(3).getPlateauJoueur(j+1, i)== null) {
									if(this.tuileCompatible(maPetiteTuile, j, i)) {
										jeu.getListe_joueurs().get(3).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
										jeu.getListe_joueurs().get(3).setPlateauJoueur(j+1,i,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
										return true;
									}
									jeu.getListe_joueurs().get(3).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
									jeu.getListe_joueurs().get(3).setPlateauJoueur(j+1,i,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
								return true;
								}
							}	
						}
					}
				}
			}
		}
		return false;
	}
	
public boolean tuileCompatible(Tuile tuile, int i, int j) {
		
	//test de compatibilit� sur chacune des rotations
		if(tuile.getRotation().equals("horizontal-croissant")) {
			try {
				if(tuile.getType_tuile1().equals(this.getPlateauJoueur(i, j-1).split("-")[0]) || this.getPlateauJoueur(i, j-1).split("-")[0].equals("chateau"))return true;
			}
			catch(Exception e){}
			
			try {
				if(tuile.getType_tuile1().equals(this.getPlateauJoueur(i-1, j).split("-")[0]) || this.getPlateauJoueur(i-1, j).split("-")[0].equals("chateau"))return true;
			}
			catch(Exception e){}
			
			try {
				if(tuile.getType_tuile1().equals(this.getPlateauJoueur(i+1, j).split("-")[0]) || this.getPlateauJoueur(i+1, j).split("-")[0].equals("chateau"))return true;
			}
			catch(Exception e){}
			
			try {
				if(tuile.getType_tuile2().equals(this.getPlateauJoueur(i-1, j+1).split("-")[0]) || this.getPlateauJoueur(i-1, j+1).split("-")[0].equals("chateau"))return true;
			}
			catch(Exception e){}
			
			try {
				if(tuile.getType_tuile2().equals(this.getPlateauJoueur(i, j+2).split("-")[0]) || this.getPlateauJoueur(i, j+2).split("-")[0].equals("chateau"))return true;
			}
			catch(Exception e){}
			
			try {
				if(tuile.getType_tuile2().equals(this.getPlateauJoueur(i+1, j+1).split("-")[0]) || this.getPlateauJoueur(i+1, j+1).split("-")[0].equals("chateau"))return true;	
			}
			catch(Exception e){}
			
			return false;
		}
		
		else if(tuile.getRotation().equals("horizontal-decroissant")) {
			try {
				if(tuile.getType_tuile1().equals(this.getPlateauJoueur(i-1, j).split("-")[0]) || this.getPlateauJoueur(i-1, j).split("-")[0].equals("chateau"))return true;
			}
			catch(Exception e){}
			
			try {
				if(tuile.getType_tuile1().equals(this.getPlateauJoueur(i, j+1).split("-")[0]) || this.getPlateauJoueur(i, j+1).split("-")[0].equals("chateau"))return true;
			}
			catch(Exception e){}
			
			try {
				if(tuile.getType_tuile1().equals(this.getPlateauJoueur(i+1, j).split("-")[0]) || this.getPlateauJoueur(i+1, j).split("-")[0].equals("chateau"))return true;
			}
			catch(Exception e){}
			
			try {
				if(tuile.getType_tuile2().equals(this.getPlateauJoueur(i-1, j-1).split("-")[0]) || this.getPlateauJoueur(i-1, j-1).split("-")[0].equals("chateau"))return true;
			}
			catch(Exception e){}
			
			try {
				if(tuile.getType_tuile2().equals(this.getPlateauJoueur(i, j-2).split("-")[0]) || this.getPlateauJoueur(i, j-2).split("-")[0].equals("chateau"))return true;
			}
			catch(Exception e){}
			
			try {
				if(tuile.getType_tuile2().equals(this.getPlateauJoueur(i+1, j-1).split("-")[0]) || this.getPlateauJoueur(i+1, j-1).split("-")[0].equals("chateau"))return true;
			}
			catch(Exception e){}
			
			return false;
		}
		
		else if(tuile.getRotation().equals("vertical-decroissant")) {
			try {
				if(tuile.getType_tuile1().equals(this.getPlateauJoueur(i, j-1).split("-")[0]) || this.getPlateauJoueur(i, j-1).split("-")[0].equals("chateau"))return true;
			}
			catch(Exception e){}
			
			try {
				if(tuile.getType_tuile1().equals(this.getPlateauJoueur(i-1, j).split("-")[0]) || this.getPlateauJoueur(i-1, j).split("-")[0].equals("chateau"))return true;
			}
			catch(Exception e){}
			
			try {
				if(tuile.getType_tuile1().equals(this.getPlateauJoueur(i, j+1).split("-")[0]) || this.getPlateauJoueur(i, j+1).split("-")[0].equals("chateau"))return true;	
			}
			catch(Exception e){}
			
			try {
				if(tuile.getType_tuile2().equals(this.getPlateauJoueur(i+1, j-1).split("-")[0]) || this.getPlateauJoueur(i+1, j-1).split("-")[0].equals("chateau"))return true;
			}
			catch(Exception e){}
			
			try {
				if(tuile.getType_tuile2().equals(this.getPlateauJoueur(i+2, j).split("-")[0]) || this.getPlateauJoueur(i+2, j).split("-")[0].equals("chateau"))return true;
			}
			catch(Exception e){}
			
			try {
				if(tuile.getType_tuile2().equals(this.getPlateauJoueur(i+1, j+1).split("-")[0]) || this.getPlateauJoueur(i+1, j+1).split("-")[0].equals("chateau"))return true;
			}
			catch(Exception e){}
			
			return false;	
		}
		
		else if(tuile.getRotation().equals("vertical-croissant")) {
			try {
				if(tuile.getType_tuile1().equals(this.getPlateauJoueur(i, j-1).split("-")[0]) || this.getPlateauJoueur(i, j-1).split("-")[0].equals("chateau"))return true;
			}
			catch(Exception e){}
			
			try {
				if(tuile.getType_tuile1().equals(this.getPlateauJoueur(i+1, j).split("-")[0]) || this.getPlateauJoueur(i+1, j).split("-")[0].equals("chateau"))return true;
			}
			catch(Exception e){}
			
			try {
				if(tuile.getType_tuile1().equals(this.getPlateauJoueur(i, j+1).split("-")[0]) || this.getPlateauJoueur(i, j+1).split("-")[0].equals("chateau"))return true;
			}
			catch(Exception e){}
			
			try {
				if(tuile.getType_tuile2().equals(this.getPlateauJoueur(i-1, j-1).split("-")[0]) || this.getPlateauJoueur(i-1, j-1).split("-")[0].equals("chateau"))return true;
			}
			catch(Exception e){}
			
			try {
				if(tuile.getType_tuile2().equals(this.getPlateauJoueur(i-2, j).split("-")[0]) || this.getPlateauJoueur(i-2, j).split("-")[0].equals("chateau"))return true;
			}
			catch(Exception e){}
			
			try {
				if(tuile.getType_tuile2().equals(this.getPlateauJoueur(i-1, j+1).split("-")[0]) || this.getPlateauJoueur(i-1, j+1).split("-")[0].equals("chateau"))return true;
			}
			catch(Exception e){}
			
			return false;
		}
		return false;	
	}
}