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
	
	public static ArrayList<Joueur> allPlayers = new ArrayList<>();
	
	public static Jeu MonJeu = new Jeu();
	public static Pioche MaPioche = new Pioche();
	
	public static void main(String[] args) {
		
		scan = new Scanner(System.in);
		
		//feature doublebuffering
		StdDraw.enableDoubleBuffering();
		int num_joueur = 0;

		//SetUp Font texte
		Font font = new Font("Arial", Font.BOLD, 60);
		StdDraw.setFont(font);
		   
		boolean boucleJeu = false;	
		
		boolean flagChateau = false;
		boolean flagChateauJ1 = false;
		boolean flagChateauJ2 = false;
		boolean flagChateauJ3 = false;
		boolean flagChateauJ4 = false;
		
		boolean flagTuile1 = false;
		boolean flagTuile2 = false;
		boolean flagTuile3 = false;
		boolean flagTuile4 = false;
		
		
		int NbRoiPlace = 0;
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
		
		//paramétrage des joueurs
		for (int i=1; i<num_joueur+1; i++)
		{
			Joueur Monjoueur = new Joueur();			
			System.out.println("Bonjour joueur " + i);
			System.out.println("Pseudo joueur :");
			//initialisation de l'ordre des joueurs
			MonJeu.getOrdre_tour_joueur().add(i);
			Monjoueur.setPseudo(scan.nextLine());
			
			//définition des couleurs des joueurs
			System.out.print("Vous êtes un joueur de couleur ");
			if (i == 1) {
				Monjoueur.setCouleur(Color.BLUE);
				System.out.println("bleu");
				}
			else if (i == 2) {
				Monjoueur.setCouleur(Color.RED);
				System.out.println("rouge");
				}
			else if (i == 3) {
				Monjoueur.setCouleur(Color.GREEN);
				System.out.println("vert");
				}
			else if (i == 4){
				Monjoueur.setCouleur(Color.PINK);
				System.out.println("rose");
				}
			
			//Définition du nombre de roi en fonction du nombre de joueur
			if(num_joueur == 2)Monjoueur.setNbRois(2);
			else Monjoueur.setNbRois(1);
			
			//affichage parametres joueur
			Monjoueur.infoJoueur();
			
			//Ajout du joueur au jeu
			allPlayers.add(Monjoueur);
		}
		
		//fin initialisation de l'ordre des joueurs
		if(num_joueur == 2) 
		{
		MonJeu.getOrdre_tour_joueur().add(1);
		MonJeu.getOrdre_tour_joueur().add(2);
		}
		
		//ajout de la liste des joueurs dans l'instance MonJeu
		MonJeu.setListe_joueurs(allPlayers);
		//System.out.println(MonJeu.liste_joueurs.size());
		
		MaPioche.ImportationTuiles();
		MaPioche.PiocherTuilesJeu(MonJeu);
		
		//initialisation du premier tour de jeu
		//MonJeu.nb_tour = 1;
		
		//affichage plateau avec un cercle qui suit le curseur
		while(boucleJeu == false)
		{
			//préparation affichage de tous les éléments de la fenêtre
			MonJeu.affichageGlobal(num_joueur);
			
			//placement des chateaux pour chacun des joueurs
			if(flagChateau == false)
			{
				if (flagChateauJ1 == false)
				{
					flagChateauJ1 = affichagePieceJoueur1("chateau");
					//pour debug
					//MonJeu.liste_joueurs.get(0).affichagePlateauJoueur();
				}
				
				else if(flagChateauJ2 == false)
				{
					flagChateauJ2 = affichagePieceJoueur2("chateau");
					//pour debug
					//MonJeu.liste_joueurs.get(1).affichagePlateauJoueur();
					if(num_joueur==2 && flagChateauJ2==true)flagChateau=true;
				}
				
				else if(num_joueur >= 3 && flagChateauJ3 == false)
				{
					flagChateauJ3 = affichagePieceJoueur3("chateau");
					//pour debug
					//MonJeu.liste_joueurs.get(2).affichagePlateauJoueur();
					if(num_joueur==3 && flagChateauJ3==true)flagChateau=true;
				}
				
				else if(num_joueur == 4 && flagChateauJ4 == false)
				{
					flagChateauJ4 = affichagePieceJoueur4("chateau");
					//pour debug
					//MonJeu.liste_joueurs.get(3).affichagePlateauJoueur();
					if(num_joueur==4 && flagChateauJ4==true)flagChateau=true;
				}
			}
			
			else if (flagChateau == true && NbRoiPlace != sommeRoi() )
			{
				//affichage des tuiles du plateaux déjà placé
				if ((MaPioche.getTuiles_piochees().size() != 0 && flagFinTourJeu==true) || MonJeu.getNb_tour() == 0)
				{
					MonJeu.setNb_tour(MonJeu.getNb_tour()+1);
					MaPioche.ViderTuilesTour();
					//Reste à ranger dans l'ordre croissant les tuiles
					//penser a vider la liste des tuile du tour 
					MaPioche.PiocherTuilesTour(MonJeu);
				}
				
				MaPioche.AffichageTuilesTour();
				
				System.out.println(MonJeu.getOrdre_tour_joueur());
				
				//Lorsque un joueur a placé son roi on passe au second joueur, etc...
				if(num_joueur == 2)
					{
					if (MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour().size()==1 && CompteurOrdrejoueur==0)CompteurOrdrejoueur++;
					else if (MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour().size()==1 && CompteurOrdrejoueur==1)CompteurOrdrejoueur++;
					else if (MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour().size()==2 && CompteurOrdrejoueur==2)CompteurOrdrejoueur++;
					//else if (MonJeu.liste_joueurs.get(1).choix_tuile_tour.size()==2 && CompteurOrdrejoueur==3)CompteurOrdrejoueur++;
					}
				else if(num_joueur > 2 && MonJeu.getListe_joueurs().get(CompteurOrdrejoueur).getChoix_tuile_tour().size()!=0)CompteurOrdrejoueur++;
				
				System.out.println("CompteurOrdrejoueur : " +  CompteurOrdrejoueur);
				
				//affichage des rois
				//Sélection premier roi joueur 1
				if (StdDraw.mouseX()>=726  && StdDraw.mouseX()<=774 && StdDraw.mouseY()>=616 && StdDraw.mouseY()<=684 && StdDraw.isMousePressed() && MonJeu.getOrdre_tour_joueur().get(CompteurOrdrejoueur) == 1 && MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour().size()==0)
				{
					while(StdDraw.isMousePressed());
					while (!StdDraw.isMousePressed())
					{
						//préparation affichage de tous les éléments de la fenêtre (plateaux + pioche)
						MonJeu.affichageGlobal(num_joueur);
						MaPioche.AffichageTuilesTour();
						
						MonJeu.getListe_joueurs().get(0).choix_tuile_tour(MonJeu, num_joueur, false);
						System.out.println("je prends " + MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour());
						
						StdDraw.picture( StdDraw.mouseX(), StdDraw.mouseY(), "img/roiBleu.png");
						StdDraw.show();
						StdDraw.pause(1);
						StdDraw.clear(StdDraw.GRAY);
					}
					if (MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour().size() == 1)NbRoiPlace++;
				}
				
				//Sélection deuxième roi joueur 1
				else if(StdDraw.mouseX()>=780-14  && StdDraw.mouseX()<=780+14 && StdDraw.mouseY()>=616 && StdDraw.mouseY()<=684 && StdDraw.isMousePressed() && MonJeu.getOrdre_tour_joueur().get(CompteurOrdrejoueur) == 1 && MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour().size()==1)
				{
					while(StdDraw.isMousePressed());
					while (!StdDraw.isMousePressed())
					{
						//préparation affichage de tous les éléments de la fenêtre (plateaux + pioche)
						MonJeu.affichageGlobal(num_joueur);
						MaPioche.AffichageTuilesTour();
						
						MonJeu.getListe_joueurs().get(0).choix_tuile_tour(MonJeu, num_joueur, true);
						System.out.println("je prends " + MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour());
						
						StdDraw.picture( StdDraw.mouseX(), StdDraw.mouseY(), "img/roiBleu.png");
						StdDraw.show();
						StdDraw.pause(1);
						StdDraw.clear(StdDraw.GRAY);
					}
					if (MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour().size() == 2)NbRoiPlace++;
				}
				
				//Sélection premier roi joueur 2
				else if (StdDraw.mouseX()>=726  && StdDraw.mouseX()<=774 && StdDraw.mouseY()>=516 && StdDraw.mouseY()<=584 && StdDraw.isMousePressed() && MonJeu.getOrdre_tour_joueur().get(CompteurOrdrejoueur) == 2 && MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour().size()==0)
				{
					while(StdDraw.isMousePressed());
					while (!StdDraw.isMousePressed())
					{
						//préparation affichage de tous les éléments de la fenêtre (plateaux + pioche)
						MonJeu.affichageGlobal(num_joueur);
						MaPioche.AffichageTuilesTour();
						
						MonJeu.getListe_joueurs().get(1).choix_tuile_tour(MonJeu, num_joueur, false);
						System.out.println("je prends " + MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour());
						
						StdDraw.picture( StdDraw.mouseX(), StdDraw.mouseY(), "img/roiRouge.png");
						StdDraw.show();
						StdDraw.pause(1);
						StdDraw.clear(StdDraw.GRAY);
					}
					if (MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour().size() == 1)NbRoiPlace++;
				}
				
				//Sélection deuxième roi joueur 2
				else if(StdDraw.mouseX()>=780-14  && StdDraw.mouseX()<=780+14 && StdDraw.mouseY()>=516 && StdDraw.mouseY()<=584 && StdDraw.isMousePressed() && MonJeu.getOrdre_tour_joueur().get(CompteurOrdrejoueur) == 2 && MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour().size()==1)
				{
					while(StdDraw.isMousePressed());
					while (!StdDraw.isMousePressed())
					{
						//préparation affichage de tous les éléments de la fenêtre (plateaux + pioche)
						MonJeu.affichageGlobal(num_joueur);
						MaPioche.AffichageTuilesTour();
						
						MonJeu.getListe_joueurs().get(1).choix_tuile_tour(MonJeu, num_joueur, true);
						System.out.println("je prends " + MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour());
						
						StdDraw.picture( StdDraw.mouseX(), StdDraw.mouseY(), "img/roiRouge.png");
						StdDraw.show();
						StdDraw.pause(1);
						StdDraw.clear(StdDraw.GRAY);
					}
					if (MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour().size() == 2)NbRoiPlace++;
				}
				
				//Sélection roi joueur 3
				else if (StdDraw.mouseX()>=726  && StdDraw.mouseX()<=774 && StdDraw.mouseY()>=416 && StdDraw.mouseY()<=484 && StdDraw.isMousePressed() && MonJeu.getOrdre_tour_joueur().get(CompteurOrdrejoueur) == 3 && MonJeu.getListe_joueurs().get(CompteurOrdrejoueur).getChoix_tuile_tour().size()==0)
				{
					while(StdDraw.isMousePressed());
					while (!StdDraw.isMousePressed())
					{
						//préparation affichage de tous les éléments de la fenêtre (plateaux + pioche)
						MonJeu.affichageGlobal(num_joueur);
						MaPioche.AffichageTuilesTour();
						
						MonJeu.getListe_joueurs().get(2).choix_tuile_tour(MonJeu, num_joueur, false);
						System.out.println("je prends " + MonJeu.getListe_joueurs().get(2).getChoix_tuile_tour());
						
						StdDraw.picture( StdDraw.mouseX(), StdDraw.mouseY(), "img/roiVert.png");
						StdDraw.show();
						StdDraw.pause(1);
						StdDraw.clear(StdDraw.GRAY);
					}
					if (MonJeu.getListe_joueurs().get(2).getChoix_tuile_tour().size() == 1)NbRoiPlace++;
				}
				
				//Sélection roi joueur 4
				else if (StdDraw.mouseX()>=726  && StdDraw.mouseX()<=774 && StdDraw.mouseY()>=316 && StdDraw.mouseY()<=384 && StdDraw.isMousePressed() && MonJeu.getOrdre_tour_joueur().get(CompteurOrdrejoueur) == 4 && MonJeu.getListe_joueurs().get(CompteurOrdrejoueur).getChoix_tuile_tour().size()==0)
				{
					while(StdDraw.isMousePressed());
					while (!StdDraw.isMousePressed())
					{
						//préparation affichage de tous les éléments de la fenêtre (plateaux + pioche)
						MonJeu.affichageGlobal(num_joueur);
						MaPioche.AffichageTuilesTour();
						
						MonJeu.getListe_joueurs().get(3).choix_tuile_tour(MonJeu, num_joueur, false);
						System.out.println("je prends " + MonJeu.getListe_joueurs().get(3).getChoix_tuile_tour());
						
						StdDraw.picture( StdDraw.mouseX(), StdDraw.mouseY(), "img/roiRose.png");
						StdDraw.show();
						StdDraw.pause(1);
						StdDraw.clear(StdDraw.GRAY);
					}
					if (MonJeu.getListe_joueurs().get(3).getChoix_tuile_tour().size() == 1)NbRoiPlace++;
				}
				
				else 
				{
					for (int i=0; i<MonJeu.getListe_joueurs().size(); i++)
					{
							MonJeu.getListe_joueurs().get(i).affichageRoi();
					}
				}
				
				StdDraw.filledCircle(StdDraw.mouseX(), StdDraw.mouseY(), 10);
				StdDraw.setPenColor(StdDraw.RED);
			}
			
			else if (flagChateau == true && NbRoiPlace == sommeRoi() && flagFinTourJeu == false)
			{
				//pour vider la liste d'ordre de jeu des joueurs
				System.out.println("vidage liste");
				
				for(int i=MonJeu.getOrdre_tour_joueur().size()-1; i>-1; i--)
				{
					MonJeu.getOrdre_tour_joueur().remove(i);
					System.out.println(MonJeu.getOrdre_tour_joueur());
				}
				
				System.out.println("fin vidage liste");
				
				//condition de rebouclage pour 2, 3, 4 joueur pour liste ordre joueurs 
				while(((num_joueur == 3 || num_joueur == 4) && MonJeu.getOrdre_tour_joueur().size() != num_joueur) || (num_joueur == 2 && MonJeu.getOrdre_tour_joueur().size() != 4))
				{
					for (int i=0; i<MonJeu.getListe_joueurs().size(); i++)
					{
						for (int j=0; j<MonJeu.getListe_joueurs().get(i).getChoix_tuile_tour().size(); j++)
						{
							System.out.println("i : " + i + ", j : " + j);
							System.out.println(MonJeu.getListe_joueurs().get(i).getChoix_tuile_tour().size());
							if (MonJeu.getOrdre_tour_joueur().size() == 0 && MonJeu.getListe_joueurs().get(i).getChoix_tuile_tour().get(j) == 1) MonJeu.getOrdre_tour_joueur().add(i+1);
							else if (MonJeu.getOrdre_tour_joueur().size() == 1 && MonJeu.getListe_joueurs().get(i).getChoix_tuile_tour().get(j) == 2) MonJeu.getOrdre_tour_joueur().add(i+1);
							else if (MonJeu.getOrdre_tour_joueur().size() == 2 && MonJeu.getListe_joueurs().get(i).getChoix_tuile_tour().get(j) == 3) MonJeu.getOrdre_tour_joueur().add(i+1);
							else if (MonJeu.getOrdre_tour_joueur().size() == 3 && MonJeu.getListe_joueurs().get(i).getChoix_tuile_tour().get(j) == 4) MonJeu.getOrdre_tour_joueur().add(i+1);
							
							System.out.println(MonJeu.getOrdre_tour_joueur());
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
				
				//Placement première tuile
				if (StdDraw.mouseX()>=1040-(136/4) && StdDraw.mouseX()<=1040+(136/2)+(136/4) && StdDraw.mouseY()>=540-(136/4) && StdDraw.mouseY()<=540+(136/4) && StdDraw.isMousePressed())
				{
					while(StdDraw.isMousePressed());
					//while (!StdDraw.isMousePressed() && flagTuile1 == true)
					while (!StdDraw.isMousePressed() )
					{
						while(flagTuile1 == false)
						{
							System.out.println("dans boucle");
							//préparation affichage de tous les éléments de la fenêtre (plateaux + pioche)
							MonJeu.affichageGlobal(num_joueur);
							MaPioche.AffichageTuilesTour();
							MonJeu.AffichageRoiTour();
							
							MaPioche.AffichageTuile(StdDraw.mouseX(), StdDraw.mouseY(),MaPioche.getTuiles_tour().get(0).getType_tuile1(), MaPioche.getTuiles_tour().get(0).getNbCouronnes1(), MaPioche.getTuiles_tour().get(0).getType_tuile2(), MaPioche.getTuiles_tour().get(0).getNbCouronnes2());
							flagTuile1 = affichageTuileJoueur1(MaPioche.getTuiles_tour().get(0));
							
							System.out.println("flagTuile1 :" + flagTuile1);
							
							StdDraw.show();
							StdDraw.pause(1);
							StdDraw.clear(StdDraw.GRAY);
						}
						
					}
				}
				
				if (flagTuile1==true)MonJeu.getListe_joueurs().get(0).affichagePlateauJoueur();
				
				//System.out.println(MonJeu.ordre_tour_joueur);
			}
			
			//System.out.println("nb roi : " + NbRoiPlace);
			
			//affichage de tous les éléments de la fenêtre
			StdDraw.show();
			StdDraw.pause(1);
			StdDraw.clear(StdDraw.GRAY);
		}
		
		StdDraw.show();		
		
		System.out.println(MonJeu.getListe_joueurs());
		
		//System.out.println(MonJeu.getNames());
		//MonJeu.initGame(MonJeu.askNbPlayer());
		//System.out.println(MonJeu.getNames());
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
					if (maPetiteTuile.getType_tuile1().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
					else if (maPetiteTuile.getType_tuile1().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
					else if (maPetiteTuile.getType_tuile1().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
					else if (maPetiteTuile.getType_tuile1().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
					else if (maPetiteTuile.getType_tuile1().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
					else if (maPetiteTuile.getType_tuile1().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
					
					if (maPetiteTuile.getType_tuile2().equals("Champs")) StdDraw.picture(TAILLE_CASE/2 + 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
					else if (maPetiteTuile.getType_tuile2().equals("Mer"))StdDraw.picture(TAILLE_CASE/2 + 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
					else if (maPetiteTuile.getType_tuile2().equals("Foret"))StdDraw.picture(TAILLE_CASE/2 + 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
					else if (maPetiteTuile.getType_tuile2().equals("Prairie"))StdDraw.picture(TAILLE_CASE/2 + 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
					else if (maPetiteTuile.getType_tuile2().equals("Mine"))StdDraw.picture(TAILLE_CASE/2 + 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
					else if (maPetiteTuile.getType_tuile2().equals("Montagne"))StdDraw.picture(TAILLE_CASE/2 + 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
					
					if (StdDraw.isMousePressed()) {	
						MonJeu.getListe_joueurs().get(0).setPlateauJoueur(j,i,maPetiteTuile.getType_tuile1());
						MonJeu.getListe_joueurs().get(0).setPlateauJoueur(j,i+1,maPetiteTuile.getType_tuile2());
						return true;
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
// ranger les tuiles dans l'ordre croissant
// OK - test tuile pas deja prise
// nouvel ordre joueur prochain tour
//incrémenté les tours
// ~ - fixe bug spam click sur roi
//ajouter les couronne sur le plateau et au deplacement de la tuile


//fonction de fin de tour (faire liste tour de jeu, tuile choisi joueur à 0, la lsite des tuile du tour )

//rendre clean
//déplacer le fonction du main et en créer plus
// mettre des this dan sles class
//camelCase variable, methode, fonction, instances
//enlever les commentaires inutiles