import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static Scanner scan;
	
	public final static int X_MAX= 1280;
	public final static int Y_MAX= 720;
	public final static float WIDTH= 1;
	
	public static Jeu MonJeu = new Jeu();
	public static Pioche MaPioche = new Pioche();
	
	public static void main(String[] args) {
		
		scan = new Scanner(System.in);
		
		//feature doublebuffering
		StdDraw.enableDoubleBuffering();
		int num_joueur = 0;

		boolean jeu = true;	
		
		boolean flagChateau = false;
		boolean flagChateauJ1 = false;
		boolean flagChateauJ2 = false;
		boolean flagChateauJ3 = false;
		boolean flagChateauJ4 = false;
		
		boolean flagTuile1 = false;
		boolean flagTuile2 = false;
		boolean flagTuile3 = false;
		boolean flagTuile4 = false;
		
		
		//int NbRoiPlace = 0;
		int CompteurOrdrejoueur = 0;
		boolean flagFinTourJeu = false;

		System.out.println("Bienvenue dans le jeu Domination !");
		
		//Création de la fenêtre de jeu
		StdDraw.setCanvasSize(X_MAX, Y_MAX);
		StdDraw.setXscale(-WIDTH, X_MAX+WIDTH);
		StdDraw.setYscale(-WIDTH, Y_MAX+WIDTH);
		StdDraw.clear(StdDraw.GRAY); 
		
		//Sélection du nombre de joueur
		num_joueur= initNbJoueur();
		
		//Paramétrage des joueurs
		CreationJoueurs(MonJeu, num_joueur);
		
		//Définition de l'ordre des joueurs de manière random
		MonJeu.initOrdreTour(num_joueur);
		
		//Initialisation de la pioche
		MaPioche.ImportationTuiles();
		MaPioche.PiocherTuilesJeu(MonJeu);
		
		//Boucle principal du jeu
		while(jeu == true){
			//Préparation affichage de tous les éléments de la fenêtre
			MonJeu.affichageGlobal(num_joueur);
			
			//raccourci fin de jeu - echap
			if(StdDraw.isKeyPressed(27)) {
				System.out.println("Racc Fin du jeu");
				jeu = false;
			}
			
			//placement des chateaux pour chacun des joueurs
			if(flagChateau == false){
				if (flagChateauJ1 == false){
					flagChateauJ1 = affichagePieceJoueur1("chateau");
					//pour debug
					//MonJeu.liste_joueurs.get(0).affichagePlateauJoueur();
				}
				
				else if(flagChateauJ2 == false){
					flagChateauJ2 = affichagePieceJoueur2("chateau");
					//pour debug
					//MonJeu.liste_joueurs.get(1).affichagePlateauJoueur();
					if(num_joueur==2 && flagChateauJ2==true)flagChateau=true;
				}
				
				else if(num_joueur >= 3 && flagChateauJ3 == false){
					flagChateauJ3 = affichagePieceJoueur3("chateau");
					//pour debug
					//MonJeu.liste_joueurs.get(2).affichagePlateauJoueur();
					if(num_joueur==3 && flagChateauJ3==true)flagChateau=true;
				}
				
				else if(num_joueur == 4 && flagChateauJ4 == false){
					flagChateauJ4 = affichagePieceJoueur4("chateau");
					//pour debug
					//MonJeu.liste_joueurs.get(3).affichagePlateauJoueur();
					if(num_joueur==4 && flagChateauJ4==true)flagChateau=true;
				}
			}
			
			else if (flagChateau == true && MonJeu.getNb_roi_place() != sommeRoi()){
				System.out.println(MaPioche.getTuiles_piochees().size());
				//affichage des tuiles du plateaux déjà placé
				if ((MaPioche.getTuiles_piochees().size() != 0 && flagFinTourJeu==true) || MonJeu.getNb_tour() == 0){
					if (MonJeu.getNb_tour() == 0) MonJeu.setNb_tour(1);
					System.out.println(MaPioche.getTuiles_piochees().size());
					MaPioche.ViderTuilesTour();
					//Reste à ranger dans l'ordre croissant les tuiles
					//penser a vider la liste des tuile du tour 
					MaPioche.PiocherTuilesTour(MonJeu);
					MaPioche.OrdonnerTuilesTour();
					flagFinTourJeu = false;
				}
				
				MaPioche.AffichageTuilesTour();	
				
				//Lorsque un joueur a placé son roi on passe au second joueur, etc...
				if(num_joueur == 2){
					//List [1, 1, 2, 2]
					if (MonJeu.getOrdre_tour_joueur().get(0) == 1 && MonJeu.getOrdre_tour_joueur().get(1) == 1 && MonJeu.getOrdre_tour_joueur().get(2) == 2 && MonJeu.getOrdre_tour_joueur().get(3) == 2) {
						if (MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour().size()==1 && CompteurOrdrejoueur==0)CompteurOrdrejoueur++;
						else if (MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour().size()==2 && CompteurOrdrejoueur==1)CompteurOrdrejoueur++;
						else if (MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour().size()==1 && CompteurOrdrejoueur==2)CompteurOrdrejoueur++;
					}
					
					//List [2, 2, 1, 1]
					if (MonJeu.getOrdre_tour_joueur().get(0) == 2 && MonJeu.getOrdre_tour_joueur().get(1) == 2 && MonJeu.getOrdre_tour_joueur().get(2) == 1 && MonJeu.getOrdre_tour_joueur().get(3) == 1) {
						if (MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour().size()==1 && CompteurOrdrejoueur==0)CompteurOrdrejoueur++;
						else if (MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour().size()==2 && CompteurOrdrejoueur==1)CompteurOrdrejoueur++;
						else if (MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour().size()==1 && CompteurOrdrejoueur==2)CompteurOrdrejoueur++;
					}
					
					//List [1, 2, 1, 2]
					if (MonJeu.getOrdre_tour_joueur().get(0) == 1 && MonJeu.getOrdre_tour_joueur().get(1) == 2 && MonJeu.getOrdre_tour_joueur().get(2) == 1 && MonJeu.getOrdre_tour_joueur().get(3) == 2) {
						if (MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour().size()==1 && CompteurOrdrejoueur==0)CompteurOrdrejoueur++;
						else if (MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour().size()==1 && CompteurOrdrejoueur==1)CompteurOrdrejoueur++;
						else if (MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour().size()==2 && CompteurOrdrejoueur==2)CompteurOrdrejoueur++;
					}
					
					//List [2, 1, 2, 1]
					if (MonJeu.getOrdre_tour_joueur().get(0) == 2 && MonJeu.getOrdre_tour_joueur().get(1) == 1 && MonJeu.getOrdre_tour_joueur().get(2) == 2 && MonJeu.getOrdre_tour_joueur().get(3) == 1) {
						if (MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour().size()==1 && CompteurOrdrejoueur==0)CompteurOrdrejoueur++;
						else if (MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour().size()==1 && CompteurOrdrejoueur==1)CompteurOrdrejoueur++;
						else if (MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour().size()==2 && CompteurOrdrejoueur==2)CompteurOrdrejoueur++;
					}
					
					//List [1, 2, 2, 1]
					if (MonJeu.getOrdre_tour_joueur().get(0) == 1 && MonJeu.getOrdre_tour_joueur().get(1) == 2 && MonJeu.getOrdre_tour_joueur().get(2) == 2 && MonJeu.getOrdre_tour_joueur().get(3) == 1) {
						if (MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour().size()==1 && CompteurOrdrejoueur==0)CompteurOrdrejoueur++;
						else if (MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour().size()==1 && CompteurOrdrejoueur==1)CompteurOrdrejoueur++;
						else if (MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour().size()==2 && CompteurOrdrejoueur==2)CompteurOrdrejoueur++;
					}
					
					//List [2, 1, 1, 2]
					if (MonJeu.getOrdre_tour_joueur().get(0) == 2 && MonJeu.getOrdre_tour_joueur().get(1) == 1 && MonJeu.getOrdre_tour_joueur().get(2) == 1 && MonJeu.getOrdre_tour_joueur().get(3) == 2) {
						if (MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour().size()==1 && CompteurOrdrejoueur==0)CompteurOrdrejoueur++;
						else if (MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour().size()==1 && CompteurOrdrejoueur==1)CompteurOrdrejoueur++;
						else if (MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour().size()==2 && CompteurOrdrejoueur==2)CompteurOrdrejoueur++;
					}
				}
				
				else if(num_joueur > 2 && MonJeu.getListe_joueurs().get(MonJeu.getOrdre_tour_joueur().get(CompteurOrdrejoueur)-1).getChoix_tuile_tour().size()!=0)CompteurOrdrejoueur++;
				
				System.out.println("CompteurOrdrejoueur : " +  CompteurOrdrejoueur);
				
				//Affectation des rois sur les tuiles
				MonJeu.getListe_joueurs().get(0).placementRoiJ1p(MonJeu, MaPioche, num_joueur, CompteurOrdrejoueur);
				MonJeu.getListe_joueurs().get(0).placementRoiJ1d(MonJeu, MaPioche, num_joueur, CompteurOrdrejoueur);
				MonJeu.getListe_joueurs().get(1).placementRoiJ2p(MonJeu, MaPioche, num_joueur, CompteurOrdrejoueur);
				MonJeu.getListe_joueurs().get(1).placementRoiJ2d(MonJeu, MaPioche, num_joueur, CompteurOrdrejoueur);
				
				try{
					MonJeu.getListe_joueurs().get(2).placementRoiJ3p(MonJeu, MaPioche, num_joueur, CompteurOrdrejoueur);
				}
				catch(java.lang.IndexOutOfBoundsException e){}
				
				try {
					MonJeu.getListe_joueurs().get(3).placementRoiJ4p(MonJeu, MaPioche, num_joueur, CompteurOrdrejoueur);
				}
				catch(java.lang.IndexOutOfBoundsException e){}
				
				for (int i=0; i<MonJeu.getListe_joueurs().size(); i++)MonJeu.getListe_joueurs().get(i).affichageRoi();
			}
			
			else if (flagChateau == true && MonJeu.getNb_roi_place() == sommeRoi() && flagFinTourJeu == false){
				//Vidage liste ordre de jeu
				for(int i=MonJeu.getOrdre_tour_joueur().size()-1; i>-1; i--)MonJeu.getOrdre_tour_joueur().remove(i);
				
				//Prochain ordre de jeu
				while(((num_joueur == 3 || num_joueur == 4) && MonJeu.getOrdre_tour_joueur().size() != num_joueur) || (num_joueur == 2 && MonJeu.getOrdre_tour_joueur().size() != 4)){
					for (int i=0; i<MonJeu.getListe_joueurs().size(); i++){
						for (int j=0; j<MonJeu.getListe_joueurs().get(i).getChoix_tuile_tour().size(); j++){
							if (MonJeu.getOrdre_tour_joueur().size() == 0 && MonJeu.getListe_joueurs().get(i).getChoix_tuile_tour().get(j) == 1) MonJeu.getOrdre_tour_joueur().add(i+1);
							else if (MonJeu.getOrdre_tour_joueur().size() == 1 && MonJeu.getListe_joueurs().get(i).getChoix_tuile_tour().get(j) == 2) MonJeu.getOrdre_tour_joueur().add(i+1);
							else if (MonJeu.getOrdre_tour_joueur().size() == 2 && MonJeu.getListe_joueurs().get(i).getChoix_tuile_tour().get(j) == 3) MonJeu.getOrdre_tour_joueur().add(i+1);
							else if (MonJeu.getOrdre_tour_joueur().size() == 3 && MonJeu.getListe_joueurs().get(i).getChoix_tuile_tour().get(j) == 4) MonJeu.getOrdre_tour_joueur().add(i+1);
						}
					}
				}
				
				
				MaPioche.AffichageTuilesTour();
				MonJeu.AffichageRoiTour();
				
				//prendre et poser la tuile
				/*if(num_joueur == 2)
				{
					if (MonJeu.liste_joueurs.get(0).choix_tuile_tour.size()==1 && CompteurOrdrejoueur==0)CompteurOrdrejoueur++;
					else if (MonJeu.liste_joueurs.get(1).choix_tuile_tour.size()==1 && CompteurOrdrejoueur==1)CompteurOrdrejoueur++;
					else if (MonJeu.liste_joueurs.get(0).choix_tuile_tour.size()==2 && CompteurOrdrejoueur==2)CompteurOrdrejoueur++;
				
				}
				else if(num_joueur > 2 && MonJeu.liste_joueurs.get(CompteurOrdrejoueur).choix_tuile_tour.size()!=0)CompteurOrdrejoueur++;*/
				
				//Placement première tuile pioche
				if (StdDraw.mouseX()>=1040-(136/4) && StdDraw.mouseX()<=1040+(136/2)+(136/4) && StdDraw.mouseY()>=540-(136/4) && StdDraw.mouseY()<=540+(136/4) && StdDraw.isMousePressed() && flagTuile1 == false){
					while(StdDraw.isMousePressed());
					while (!StdDraw.isMousePressed()){
						while(flagTuile1 == false){
							//préparation affichage de tous les éléments de la fenêtre (plateaux + pioche)
							MonJeu.affichageGlobal(num_joueur);
							MaPioche.AffichageTuilesTour();
							MonJeu.AffichageRoiTour();
							
							if(StdDraw.isKeyPressed(32)) {
								while(StdDraw.isKeyPressed(32));
								MaPioche.getTuiles_tour().get(0).rotationTuile();
								System.out.println(MaPioche.getTuiles_tour().get(0).getRotation());	
							}
							
							MaPioche.AffichageTuile(StdDraw.mouseX(), StdDraw.mouseY(),MaPioche.getTuiles_tour().get(0));
							if(MonJeu.getOrdre_tour_joueur().get(0) == 1)flagTuile1 = affichageTuileJoueur1(MaPioche.getTuiles_tour().get(0));
							else if (MonJeu.getOrdre_tour_joueur().get(0) == 2)flagTuile1 = affichageTuileJoueur2(MaPioche.getTuiles_tour().get(0));
							else if (MonJeu.getOrdre_tour_joueur().get(0) == 3)flagTuile1 = affichageTuileJoueur3(MaPioche.getTuiles_tour().get(0));
							else if (MonJeu.getOrdre_tour_joueur().get(0) == 4)flagTuile1 = affichageTuileJoueur4(MaPioche.getTuiles_tour().get(0));
							
							StdDraw.show();
							StdDraw.pause(1);
							StdDraw.clear(StdDraw.GRAY);
						}
					}
				}
				
				else if (StdDraw.mouseX()>=1040-(136/4) && StdDraw.mouseX()<=1040+(136/2)+(136/4) && StdDraw.mouseY()>=450-(136/4) && StdDraw.mouseY()<=450+(136/4) && StdDraw.isMousePressed() && flagTuile2 == false){
					while(StdDraw.isMousePressed());
					while (!StdDraw.isMousePressed()){
						while(flagTuile2 == false){
							//préparation affichage de tous les éléments de la fenêtre (plateaux + pioche)
							MonJeu.affichageGlobal(num_joueur);
							MaPioche.AffichageTuilesTour();
							MonJeu.AffichageRoiTour();
							
							if(StdDraw.isKeyPressed(32)) {
								while(StdDraw.isKeyPressed(32));
								MaPioche.getTuiles_tour().get(1).rotationTuile();
								System.out.println(MaPioche.getTuiles_tour().get(1).getRotation());	
							}
							
							MaPioche.AffichageTuile(StdDraw.mouseX(), StdDraw.mouseY(),MaPioche.getTuiles_tour().get(1));
							if(MonJeu.getOrdre_tour_joueur().get(1) == 1)flagTuile2 = affichageTuileJoueur1(MaPioche.getTuiles_tour().get(1));
							else if (MonJeu.getOrdre_tour_joueur().get(1) == 2)flagTuile2 = affichageTuileJoueur2(MaPioche.getTuiles_tour().get(1));
							else if (MonJeu.getOrdre_tour_joueur().get(1) == 3)flagTuile2 = affichageTuileJoueur3(MaPioche.getTuiles_tour().get(1));
							else if (MonJeu.getOrdre_tour_joueur().get(1) == 4)flagTuile2 = affichageTuileJoueur4(MaPioche.getTuiles_tour().get(1));
							
							StdDraw.show();
							StdDraw.pause(1);
							StdDraw.clear(StdDraw.GRAY);
						}
					}
				}
				
				else if (StdDraw.mouseX()>=1040-(136/4) && StdDraw.mouseX()<=1040+(136/2)+(136/4) && StdDraw.mouseY()>=360-(136/4) && StdDraw.mouseY()<=360+(136/4) && StdDraw.isMousePressed() && flagTuile3 == false){
					while(StdDraw.isMousePressed());
					while (!StdDraw.isMousePressed()){
						while(flagTuile3 == false){
							//préparation affichage de tous les éléments de la fenêtre (plateaux + pioche)
							MonJeu.affichageGlobal(num_joueur);
							MaPioche.AffichageTuilesTour();
							MonJeu.AffichageRoiTour();
							
							if(StdDraw.isKeyPressed(32)) {
								while(StdDraw.isKeyPressed(32));
								MaPioche.getTuiles_tour().get(2).rotationTuile();
								System.out.println(MaPioche.getTuiles_tour().get(2).getRotation());	
							}
							
							MaPioche.AffichageTuile(StdDraw.mouseX(), StdDraw.mouseY(),MaPioche.getTuiles_tour().get(2));
							if(MonJeu.getOrdre_tour_joueur().get(2) == 1)flagTuile3 = affichageTuileJoueur1(MaPioche.getTuiles_tour().get(2));
							else if (MonJeu.getOrdre_tour_joueur().get(2) == 2)flagTuile3 = affichageTuileJoueur2(MaPioche.getTuiles_tour().get(2));
							else if (MonJeu.getOrdre_tour_joueur().get(2) == 3)flagTuile3 = affichageTuileJoueur3(MaPioche.getTuiles_tour().get(2));
							else if (MonJeu.getOrdre_tour_joueur().get(2) == 4)flagTuile3 = affichageTuileJoueur4(MaPioche.getTuiles_tour().get(2));
							
							StdDraw.show();
							StdDraw.pause(1);
							StdDraw.clear(StdDraw.GRAY);
						}
					}
				}
				
				else if (StdDraw.mouseX()>=1040-(136/4) && StdDraw.mouseX()<=1040+(136/2)+(136/4) && StdDraw.mouseY()>=270-(136/4) && StdDraw.mouseY()<=270+(136/4) && StdDraw.isMousePressed() && flagTuile4 == false){
					while(StdDraw.isMousePressed());
					while (!StdDraw.isMousePressed()){
						while(flagTuile4 == false){
							//préparation affichage de tous les éléments de la fenêtre (plateaux + pioche)
							MonJeu.affichageGlobal(num_joueur);
							MaPioche.AffichageTuilesTour();
							MonJeu.AffichageRoiTour();
							
							if(StdDraw.isKeyPressed(32)) {
								while(StdDraw.isKeyPressed(32));
								MaPioche.getTuiles_tour().get(3).rotationTuile();
								System.out.println(MaPioche.getTuiles_tour().get(3).getRotation());	
							}
							
							MaPioche.AffichageTuile(StdDraw.mouseX(), StdDraw.mouseY(),MaPioche.getTuiles_tour().get(3));
							if(MonJeu.getOrdre_tour_joueur().get(3) == 1)flagTuile4 = affichageTuileJoueur1(MaPioche.getTuiles_tour().get(3));
							else if (MonJeu.getOrdre_tour_joueur().get(3) == 2)flagTuile4 = affichageTuileJoueur2(MaPioche.getTuiles_tour().get(3));
							else if (MonJeu.getOrdre_tour_joueur().get(3) == 3)flagTuile4 = affichageTuileJoueur3(MaPioche.getTuiles_tour().get(3));
							else if (MonJeu.getOrdre_tour_joueur().get(3) == 4)flagTuile4 = affichageTuileJoueur4(MaPioche.getTuiles_tour().get(3));
							
							StdDraw.show();
							StdDraw.pause(1);
							StdDraw.clear(StdDraw.GRAY);
						}
					}
				}
				
				//Fin tour
				if(num_joueur == 3 && flagTuile1 && flagTuile2 && flagTuile3) flagFinTourJeu=true;
				else if (flagTuile1 && flagTuile2 && flagTuile3 && flagTuile4) flagFinTourJeu=true;
				
				//Fin du jeu, plus de tuile dans la pioche
				if(flagFinTourJeu==true && MaPioche.getTuiles_piochees().size()==0)jeu=false;
			}
			
			else if (flagChateau == true && MonJeu.getNb_roi_place() == sommeRoi() && flagFinTourJeu == true) {
				//fonction de fin de tour (faire liste tour de jeu, tuile choisi joueur à 0, la lsite des tuile du tour )
				//Remise à 0 des variables de jeu
				MonJeu.setNb_roi_place(0);
				CompteurOrdrejoueur = 0;
				
				for(int i=MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour().size()-1; i>-1; i--)MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour().remove(i);
				for(int i=MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour().size()-1; i>-1; i--)MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour().remove(i);
				try {
					for(int i=MonJeu.getListe_joueurs().get(2).getChoix_tuile_tour().size()-1; i>-1; i--)MonJeu.getListe_joueurs().get(2).getChoix_tuile_tour().remove(i);
				}
				catch(java.lang.IndexOutOfBoundsException e) {}
				
				try {
					for(int i=MonJeu.getListe_joueurs().get(3).getChoix_tuile_tour().size()-1; i>-1; i--)MonJeu.getListe_joueurs().get(3).getChoix_tuile_tour().remove(i);
				}
				catch(java.lang.IndexOutOfBoundsException e) {}
				
				//remise a 0 des flag de tuile
				flagTuile1 = false;
				flagTuile2 = false;
				flagTuile3 = false;
				flagTuile4 = false;
				
				//Nouveau tour
				MonJeu.setNb_tour(MonJeu.getNb_tour()+1);	
			}
			
			//System.out.println("nb roi : " + NbRoiPlace);
			
			//affichage de tous les éléments de la fenêtre
			StdDraw.show();
			StdDraw.pause(1);
			StdDraw.clear(StdDraw.GRAY);
		}
		
		//Affichage résultat gagnant
		//ici
		StdDraw.show();	
		System.out.println("Vous avez fini de jouer");
	}
	
	public static int initNbJoueur() {
		int nbJoueur=0;
		
		while (nbJoueur == 0) {
			try {
				System.out.println("Sélectionner un nombre de joueur: 2, 3 ou 4");
				nbJoueur=scan.nextInt();
			}
			
			catch (java.lang.IndexOutOfBoundsException | java.util.InputMismatchException e) { 
				System.out.println("Erreur");
				nbJoueur = 0;
				scan.next();
			 	}
			
			if( nbJoueur == 1 || nbJoueur == 0 || nbJoueur > 4) nbJoueur = 0;
		}
		scan.nextLine();
		return nbJoueur;
	}
	
	
	public static void CreationJoueurs(Jeu monJeu, int nbJoueur) {
		for (int i=1; i<nbJoueur+1; i++){
			Joueur monJoueur = new Joueur();			
			System.out.println("Bonjour joueur " + i);
			System.out.println("Pseudo joueur :");
			
			//initialisation de l'ordre des joueurs
			//MonJeu.getOrdre_tour_joueur().add(i);
			monJoueur.setPseudo(scan.nextLine());
			
			//définition des couleurs des joueurs
			if (i == 1)monJoueur.setCouleur(Color.BLUE);
			else if (i == 2)monJoueur.setCouleur(Color.RED);
			else if (i == 3) monJoueur.setCouleur(Color.GREEN);	
			else if (i == 4)monJoueur.setCouleur(Color.PINK);
			
			//Définition du nombre de roi en fonction du nombre de joueur
			if(nbJoueur == 2)monJoueur.setNbRois(2);
			else monJoueur.setNbRois(1);
			
			//affichage parametres joueur
			monJoueur.infoJoueur();
			
			//Ajout du joueur au jeu
			monJeu.getListe_joueurs().add(monJoueur);
		}
	}
	
	
	public static int sommeRoi(){
		int somme =0;
		for(int i=0; i<MonJeu.getListe_joueurs().size(); i++)somme = somme + MonJeu.getListe_joueurs().get(i).getNbRois();
		return somme;
	}
	
	public static boolean affichageTuileJoueur1(Tuile maPetiteTuile){
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
							// si deborde du tableau, la tuile est détruite (position horizontal)
							if (i<4) {
								if(MonJeu.getListe_joueurs().get(0).getPlateauJoueur(j, i) == null && MonJeu.getListe_joueurs().get(0).getPlateauJoueur(j, i+1)== null) {
									MonJeu.getListe_joueurs().get(0).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
									MonJeu.getListe_joueurs().get(0).setPlateauJoueur(j,i+1,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
									return true;
								}
							}
							//mettre ici le retrun si on jeu que le joueur n'est pas plusieurs essaies 
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
							// si deborde du tableau, la tuile est détruite (position horizontal)
							if (i>0) {
								if(MonJeu.getListe_joueurs().get(0).getPlateauJoueur(j, i) == null && MonJeu.getListe_joueurs().get(0).getPlateauJoueur(j, i-1)== null) {
									MonJeu.getListe_joueurs().get(0).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
									MonJeu.getListe_joueurs().get(0).setPlateauJoueur(j,i-1,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
									return true;
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
							// si deborde du tableau, la tuile est détruite (position horizontal)
							if (j>0) {
								if(MonJeu.getListe_joueurs().get(0).getPlateauJoueur(j, i) == null && MonJeu.getListe_joueurs().get(0).getPlateauJoueur(j-1, i)== null) {
									MonJeu.getListe_joueurs().get(0).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
									MonJeu.getListe_joueurs().get(0).setPlateauJoueur(j-1,i,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
									return true;
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
							// si deborde du tableau, la tuile est détruite (position horizontal)
							if (j<4) {
								if(MonJeu.getListe_joueurs().get(0).getPlateauJoueur(j, i) == null && MonJeu.getListe_joueurs().get(0).getPlateauJoueur(j+1, i)== null) {
									MonJeu.getListe_joueurs().get(0).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
									MonJeu.getListe_joueurs().get(0).setPlateauJoueur(j+1,i,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
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
	
	public static boolean affichageTuileJoueur2(Tuile maPetiteTuile){
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
							// si deborde du tableau, la tuile est détruite (position horizontal)
							if (i<4) {
								if(MonJeu.getListe_joueurs().get(1).getPlateauJoueur(j, i) == null && MonJeu.getListe_joueurs().get(1).getPlateauJoueur(j, i+1)== null) {
									MonJeu.getListe_joueurs().get(1).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
									MonJeu.getListe_joueurs().get(1).setPlateauJoueur(j,i+1,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
									return true;
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
							// si deborde du tableau, la tuile est détruite (position horizontal)
							if (i>0) {
								if(MonJeu.getListe_joueurs().get(1).getPlateauJoueur(j, i) == null && MonJeu.getListe_joueurs().get(1).getPlateauJoueur(j, i-1)== null) {
									MonJeu.getListe_joueurs().get(1).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
									MonJeu.getListe_joueurs().get(1).setPlateauJoueur(j,i-1,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
									return true;
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
							// si deborde du tableau, la tuile est détruite (position horizontal)
							if (j>0) {
								if(MonJeu.getListe_joueurs().get(1).getPlateauJoueur(j, i) == null && MonJeu.getListe_joueurs().get(1).getPlateauJoueur(j-1, i)== null) {
									MonJeu.getListe_joueurs().get(1).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
									MonJeu.getListe_joueurs().get(1).setPlateauJoueur(j-1,i,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
									return true;
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
							// si deborde du tableau, la tuile est détruite (position horizontal)
							if (j<4) {
								if(MonJeu.getListe_joueurs().get(1).getPlateauJoueur(j, i) == null && MonJeu.getListe_joueurs().get(1).getPlateauJoueur(j+1, i)== null) {
									MonJeu.getListe_joueurs().get(1).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
									MonJeu.getListe_joueurs().get(1).setPlateauJoueur(j+1,i,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
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
	
	public static boolean affichageTuileJoueur3(Tuile maPetiteTuile){
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
							// si deborde du tableau, la tuile est détruite (position horizontal)
							if (i<4) {
								if(MonJeu.getListe_joueurs().get(2).getPlateauJoueur(j, i) == null && MonJeu.getListe_joueurs().get(2).getPlateauJoueur(j, i+1)== null) {
									MonJeu.getListe_joueurs().get(2).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
									MonJeu.getListe_joueurs().get(2).setPlateauJoueur(j,i+1,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
									return true;
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
							// si deborde du tableau, la tuile est détruite (position horizontal)
							if (i>0) {
								if(MonJeu.getListe_joueurs().get(2).getPlateauJoueur(j, i) == null && MonJeu.getListe_joueurs().get(2).getPlateauJoueur(j, i-1)== null) {
									MonJeu.getListe_joueurs().get(2).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
									MonJeu.getListe_joueurs().get(2).setPlateauJoueur(j,i-1,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
									return true;
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
							// si deborde du tableau, la tuile est détruite (position horizontal)
							if (j>0) {
								if(MonJeu.getListe_joueurs().get(2).getPlateauJoueur(j, i) == null && MonJeu.getListe_joueurs().get(2).getPlateauJoueur(j-1, i)== null) {
									MonJeu.getListe_joueurs().get(2).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
									MonJeu.getListe_joueurs().get(2).setPlateauJoueur(j-1,i,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
									return true;
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
							// si deborde du tableau, la tuile est détruite (position horizontal)
							if (j<4) {
								if(MonJeu.getListe_joueurs().get(2).getPlateauJoueur(j, i) == null && MonJeu.getListe_joueurs().get(2).getPlateauJoueur(j+1, i)== null) {
									MonJeu.getListe_joueurs().get(2).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
									MonJeu.getListe_joueurs().get(2).setPlateauJoueur(j+1,i,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
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
	public static boolean affichageTuileJoueur4(Tuile maPetiteTuile){
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
							// si deborde du tableau, la tuile est détruite (position horizontal)
							if (i<4) {
								if(MonJeu.getListe_joueurs().get(3).getPlateauJoueur(j, i) == null && MonJeu.getListe_joueurs().get(3).getPlateauJoueur(j, i+1)== null) {
								MonJeu.getListe_joueurs().get(3).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
								MonJeu.getListe_joueurs().get(3).setPlateauJoueur(j,i+1,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
								return true;
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
							// si deborde du tableau, la tuile est détruite (position horizontal)
							if (i>0) {
								if(MonJeu.getListe_joueurs().get(3).getPlateauJoueur(j, i) == null && MonJeu.getListe_joueurs().get(3).getPlateauJoueur(j, i-1)== null) {
								MonJeu.getListe_joueurs().get(3).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
								MonJeu.getListe_joueurs().get(3).setPlateauJoueur(j,i-1,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
								return true;
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
							// si deborde du tableau, la tuile est détruite (position horizontal)
							if (j>0) {
								if(MonJeu.getListe_joueurs().get(3).getPlateauJoueur(j, i) == null && MonJeu.getListe_joueurs().get(3).getPlateauJoueur(j-1, i)== null) {
								MonJeu.getListe_joueurs().get(3).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
								MonJeu.getListe_joueurs().get(3).setPlateauJoueur(j-1,i,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
								return true;
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
							// si deborde du tableau, la tuile est détruite (position horizontal)
							if (j<4) {
								if(MonJeu.getListe_joueurs().get(3).getPlateauJoueur(j, i) == null && MonJeu.getListe_joueurs().get(3).getPlateauJoueur(j+1, i)== null) {
								MonJeu.getListe_joueurs().get(3).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1() + "-" + maPetiteTuile.getNbCouronnes1());
								MonJeu.getListe_joueurs().get(3).setPlateauJoueur(j+1,i,maPetiteTuile.getType_tuile2() + "-" + maPetiteTuile.getNbCouronnes2());
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
	
	public static boolean affichagePieceJoueur1(String mot){
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		double x = StdDraw.mouseX();
		double y = StdDraw.mouseY();
		
		//test cases joueur 1 -- validé faire la même pour les autres joueurs 2,3,4
		for (int i=0; i<5; i++){
			for (int j=0; j<5; j++){
				if((x >= 5 + i*TAILLE_CASE + i*TAILLE_LIGNE && x<= 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE) && (y <= 715 - j*TAILLE_CASE - j*TAILLE_LIGNE && y >= 715 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE)){
					if (mot == "chateau") StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/chateau.png");
					if (StdDraw.isMousePressed()) {	
						MonJeu.getListe_joueurs().get(0).setPlateauJoueur(j,i,mot);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean affichagePieceJoueur2(String mot){
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		double x = StdDraw.mouseX();
		double y = StdDraw.mouseY();
		
		//test cases joueur 2
		for (int i=0; i<5; i++){
			for (int j=0; j<5; j++){
				if((x >= 365 + i*TAILLE_CASE + i*TAILLE_LIGNE && x<= 365 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE) && (y <= 355 - j*TAILLE_CASE - j*TAILLE_LIGNE && y >= 355 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE)){
					if (mot == "chateau") StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/chateau.png");
					if (StdDraw.isMousePressed()) {	
						MonJeu.getListe_joueurs().get(1).setPlateauJoueur(j,i,mot);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean affichagePieceJoueur3(String mot){
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		double x = StdDraw.mouseX();
		double y = StdDraw.mouseY();
		
		//test cases joueur 3
		for (int i=0; i<5; i++){
			for (int j=0; j<5; j++){
				if((x >= 365 + i*TAILLE_CASE + i*TAILLE_LIGNE && x<= 365 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE) && (y <= 715 - j*TAILLE_CASE - j*TAILLE_LIGNE && y >= 715 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE)){
					if (mot == "chateau") StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/chateau.png");
					if (StdDraw.isMousePressed()) {	
						MonJeu.getListe_joueurs().get(2).setPlateauJoueur(j,i,mot);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean affichagePieceJoueur4(String mot){
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		double x = StdDraw.mouseX();
		double y = StdDraw.mouseY();
		
		//test cases joueur 4
		for (int i=0; i<5; i++){
			for (int j=0; j<5; j++){
				if((x >= 5 + i*TAILLE_CASE + i*TAILLE_LIGNE && x<= 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE) && (y <= 355 - j*TAILLE_CASE - j*TAILLE_LIGNE && y >= 355 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE)){
					if (mot == "chateau") StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/chateau.png");
					if (StdDraw.isMousePressed()) {	
						MonJeu.getListe_joueurs().get(3).setPlateauJoueur(j,i,mot);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
}


// TO DO
//OK - tuile départ ? p3 pdf
// OK - ranger les tuiles dans l'ordre croissant
// OK - test tuile pas deja prise
// OK - nouvel ordre joueur prochain tour
// OK - incrémenté les tours
// OK - fixe bug spam click sur roi
// OK - ajouter les couronne sur le plateau et au deplacement de la tuile
// l 156 le faire une fois
// OK - gérer changer sens tuile 
// OK - gérer le superposage (le second tour bug tuile) 
// OK - tu sors des bords du plateau tuile detruite (le second tour bug tuile)
//victoire : pt = -> Royaume + grand
//			 pt = & royaume = -> + couronne
//			 pt = & royaume = & couronne = -> tous victoire
// OK - rotation piéce : tuile tour bouge, plateau, tableau plateau, pas de débordage
// ajouter un syster de "give up" si on peu pas poser la tuile (detruit la tuile)
//rendre a fenetre plus jolie
//test si on peut poser la tuile a coté

//fonction de fin de tour (faire liste tour de jeu, tuile choisi joueur à 0, la lsite des tuile du tour )

//rendre clean
//déplacer le fonction du main et en créer plus
// mettre des this dan sles class
//camelCase variable, methode, fonction, instances
//enlever les commentaires inutiles