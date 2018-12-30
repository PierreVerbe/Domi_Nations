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
		while (num_joueur == 0) 
		{
			try {
				System.out.println("Sélectionner un nombre de joueur: 2, 3 ou 4");
				num_joueur=scan.nextInt();
			}
			
			catch (java.lang.IndexOutOfBoundsException | java.util.InputMismatchException e) { 
				System.out.println("Erreur");
				num_joueur = 0;
				scan.next();
			 	}
			
			if( num_joueur == 1 || num_joueur == 0 || num_joueur > 4) num_joueur = 0;
		}
		
		scan.nextLine();
		
		//paramétrage des joueurs
		for (int i=1; i<num_joueur+1; i++)
		{
			Joueur Monjoueur = new Joueur();			
			System.out.println("Bonjour joueur " + i);
			System.out.println("Pseudo joueur :");
			//initialisation de l'ordre des joueurs
			MonJeu.ordre_tour_joueur.add(i);
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
		MonJeu.ordre_tour_joueur.add(1);
		MonJeu.ordre_tour_joueur.add(2);
		}
		
		//ajout de la liste des joueurs dans l'instance MonJeu
		MonJeu.setListe_joueurs(allPlayers);
		//System.out.println(MonJeu.liste_joueurs.size());
		
		MaPioche.ImportationTuiles();
		MaPioche.PiocherTuilesJeu();
		
		//initialisation du premier tour de jeu
		MonJeu.nb_tour = 1;
		
		//affichage plateau avec un cercle qui suit le curseur
		while(boucleJeu == false)
		{
			//préparation affichage de tous les éléments de la fenêtre
			affichageGlobal(num_joueur);
			
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
				if ((MaPioche.tuiles_piochees.size() != 0 && flagFinTourJeu==true) || MonJeu.nb_tour == 1)
				{
					MaPioche.ViderTuilesTour();
					//Reste à ranger dans l'ordre croissant les tuiles
					//penser a vider la liste des tuile du tour 
					MaPioche.PiocherTuilesTour();
				}
				
				MaPioche.AffichageTuilesTour();
				
				System.out.println(MonJeu.ordre_tour_joueur);
				
				//Lorsque un joueur a placé son roi on passe au second joueur, etc...
				if(num_joueur == 2)
					{
					if (MonJeu.liste_joueurs.get(0).choix_tuile_tour.size()==1 && CompteurOrdrejoueur==0)CompteurOrdrejoueur++;
					else if (MonJeu.liste_joueurs.get(1).choix_tuile_tour.size()==1 && CompteurOrdrejoueur==1)CompteurOrdrejoueur++;
					else if (MonJeu.liste_joueurs.get(0).choix_tuile_tour.size()==2 && CompteurOrdrejoueur==2)CompteurOrdrejoueur++;
					//else if (MonJeu.liste_joueurs.get(1).choix_tuile_tour.size()==2 && CompteurOrdrejoueur==3)CompteurOrdrejoueur++;
					}
				else if(num_joueur > 2 && MonJeu.liste_joueurs.get(CompteurOrdrejoueur).choix_tuile_tour.size()!=0)CompteurOrdrejoueur++;
				
				
				System.out.println("CompteurOrdrejoueur : " +  CompteurOrdrejoueur);
				
				//affichage des rois
				//Sélection premier roi joueur 1
				if (StdDraw.mouseX()>=726  && StdDraw.mouseX()<=774 && StdDraw.mouseY()>=616 && StdDraw.mouseY()<=684 && StdDraw.isMousePressed() && MonJeu.ordre_tour_joueur.get(CompteurOrdrejoueur) == 1 && MonJeu.liste_joueurs.get(0).choix_tuile_tour.size()==0)
				{
					while(StdDraw.isMousePressed());
					while (!StdDraw.isMousePressed())
					{
						//préparation affichage de tous les éléments de la fenêtre (plateaux + pioche)
						affichageGlobal(num_joueur);
						MaPioche.AffichageTuilesTour();
						
						
						MonJeu.liste_joueurs.get(0).choix_tuile_tour(num_joueur, false);
						System.out.println("je prends " + MonJeu.liste_joueurs.get(0).choix_tuile_tour);
						
						
						StdDraw.picture( StdDraw.mouseX(), StdDraw.mouseY(), "img/roiBleu.png");
						StdDraw.show();
						StdDraw.pause(1);
						StdDraw.clear(StdDraw.GRAY);
					}
					NbRoiPlace++;
				}
				
				//Sélection deuxième roi joueur 1
				else if(StdDraw.mouseX()>=780-14  && StdDraw.mouseX()<=780+14 && StdDraw.mouseY()>=616 && StdDraw.mouseY()<=684 && StdDraw.isMousePressed() && MonJeu.ordre_tour_joueur.get(CompteurOrdrejoueur) == 1 && MonJeu.liste_joueurs.get(0).choix_tuile_tour.size()==1)
				{
					while(StdDraw.isMousePressed());
					while (!StdDraw.isMousePressed())
					{
						//préparation affichage de tous les éléments de la fenêtre (plateaux + pioche)
						affichageGlobal(num_joueur);
						MaPioche.AffichageTuilesTour();
						
						
						MonJeu.liste_joueurs.get(0).choix_tuile_tour(num_joueur, true);
						System.out.println("je prends " + MonJeu.liste_joueurs.get(0).choix_tuile_tour);
						
						
						StdDraw.picture( StdDraw.mouseX(), StdDraw.mouseY(), "img/roiBleu.png");
						StdDraw.show();
						StdDraw.pause(1);
						StdDraw.clear(StdDraw.GRAY);
					}
					NbRoiPlace++;
				}
				
				//Sélection premier roi joueur 2
				else if (StdDraw.mouseX()>=726  && StdDraw.mouseX()<=774 && StdDraw.mouseY()>=516 && StdDraw.mouseY()<=584 && StdDraw.isMousePressed() && MonJeu.ordre_tour_joueur.get(CompteurOrdrejoueur) == 2 && MonJeu.liste_joueurs.get(1).choix_tuile_tour.size()==0)
				{
					while(StdDraw.isMousePressed());
					while (!StdDraw.isMousePressed())
					{
						//préparation affichage de tous les éléments de la fenêtre (plateaux + pioche)
						affichageGlobal(num_joueur);
						MaPioche.AffichageTuilesTour();
						
						MonJeu.liste_joueurs.get(1).choix_tuile_tour(num_joueur, false);
						System.out.println("je prends " + MonJeu.liste_joueurs.get(1).choix_tuile_tour);
						
						
						StdDraw.picture( StdDraw.mouseX(), StdDraw.mouseY(), "img/roiRouge.png");
						StdDraw.show();
						StdDraw.pause(1);
						StdDraw.clear(StdDraw.GRAY);
					}
					NbRoiPlace++;
				}
				
				//Sélection deuxième roi joueur 2
				else if(StdDraw.mouseX()>=780-14  && StdDraw.mouseX()<=780+14 && StdDraw.mouseY()>=516 && StdDraw.mouseY()<=584 && StdDraw.isMousePressed() && MonJeu.ordre_tour_joueur.get(CompteurOrdrejoueur) == 2 && MonJeu.liste_joueurs.get(1).choix_tuile_tour.size()==1)
				{
					while(StdDraw.isMousePressed());
					while (!StdDraw.isMousePressed())
					{
						//préparation affichage de tous les éléments de la fenêtre (plateaux + pioche)
						affichageGlobal(num_joueur);
						MaPioche.AffichageTuilesTour();
						
						
						MonJeu.liste_joueurs.get(1).choix_tuile_tour(num_joueur, true);
						System.out.println("je prends " + MonJeu.liste_joueurs.get(1).choix_tuile_tour);
						
						
						StdDraw.picture( StdDraw.mouseX(), StdDraw.mouseY(), "img/roiRouge.png");
						StdDraw.show();
						StdDraw.pause(1);
						StdDraw.clear(StdDraw.GRAY);
					}
					NbRoiPlace++;
				}
				
				//Sélection roi joueur 3
				else if (StdDraw.mouseX()>=726  && StdDraw.mouseX()<=774 && StdDraw.mouseY()>=416 && StdDraw.mouseY()<=484 && StdDraw.isMousePressed() && MonJeu.ordre_tour_joueur.get(CompteurOrdrejoueur) == 3 && MonJeu.liste_joueurs.get(CompteurOrdrejoueur).choix_tuile_tour.size()==0)
				{
					while(StdDraw.isMousePressed());
					while (!StdDraw.isMousePressed())
					{
						//préparation affichage de tous les éléments de la fenêtre (plateaux + pioche)
						affichageGlobal(num_joueur);
						MaPioche.AffichageTuilesTour();
						
						MonJeu.liste_joueurs.get(2).choix_tuile_tour(num_joueur, false);
						System.out.println("je prends " + MonJeu.liste_joueurs.get(2).choix_tuile_tour);
						
						
						StdDraw.picture( StdDraw.mouseX(), StdDraw.mouseY(), "img/roiVert.png");
						StdDraw.show();
						StdDraw.pause(1);
						StdDraw.clear(StdDraw.GRAY);
					}
					NbRoiPlace++;
				}
				
				//Sélection roi joueur 4
				else if (StdDraw.mouseX()>=726  && StdDraw.mouseX()<=774 && StdDraw.mouseY()>=316 && StdDraw.mouseY()<=384 && StdDraw.isMousePressed() && MonJeu.ordre_tour_joueur.get(CompteurOrdrejoueur) == 4 && MonJeu.liste_joueurs.get(CompteurOrdrejoueur).choix_tuile_tour.size()==0)
				{
					while(StdDraw.isMousePressed());
					while (!StdDraw.isMousePressed())
					{
						//préparation affichage de tous les éléments de la fenêtre (plateaux + pioche)
						affichageGlobal(num_joueur);
						MaPioche.AffichageTuilesTour();
						
						MonJeu.liste_joueurs.get(3).choix_tuile_tour(num_joueur, false);
						System.out.println("je prends " + MonJeu.liste_joueurs.get(3).choix_tuile_tour);
						
						StdDraw.picture( StdDraw.mouseX(), StdDraw.mouseY(), "img/roiRose.png");
						StdDraw.show();
						StdDraw.pause(1);
						StdDraw.clear(StdDraw.GRAY);
					}
					NbRoiPlace++;
				}
				
				else 
				{
					for (int i=0; i<MonJeu.liste_joueurs.size(); i++)
					{
							MonJeu.liste_joueurs.get(i).affichageRoi();
					}
				}
				
				
				
				
				
				
				
				
				MonJeu.nb_tour = MonJeu.nb_tour+1;
			
			
				
				
				
				StdDraw.filledCircle(StdDraw.mouseX(), StdDraw.mouseY(), 10);
				StdDraw.setPenColor(StdDraw.RED);
			}
			
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
	
	public static void affichageGlobal(int nombreJoueurs)
	{
		//image des plateaux du jeu
		affichagePlateauJoueurs(nombreJoueurs);
		
		//affichage des tuiles du plateaux déjà placé
		affichageMemoireJoueur1();
		affichageMemoireJoueur2();
		try
		{
			affichageMemoireJoueur3();
		}
		catch(java.lang.IndexOutOfBoundsException e) {}
		
		try
		{
			affichageMemoireJoueur4();
		}
		catch(java.lang.IndexOutOfBoundsException e) {}
	}
	
	public static void affichagePlateauJoueurs(int nombreJoueurs)
	{
		Font font = new Font("Arial", Font.BOLD, 20);
		StdDraw.setFont(font);
		
		//affichage des 2 premiers plateaux
		StdDraw.picture(180, 540, "img/Domination_plateau.png");
		StdDraw.text(180, 540,"Plateau du joueur 1");
		StdDraw.picture(540, 180, "img/Domination_plateau.png");
		StdDraw.text(540, 180,"Plateau du joueur 2");
		
		//affichage des 2 derniers plateaux
		if (nombreJoueurs >= 3)
		{
			StdDraw.picture(540, 540, "img/Domination_plateau.png");
			StdDraw.text(540, 540,"Plateau du joueur 3");
		}
		if (nombreJoueurs == 4)
		{
			StdDraw.picture(180, 180, "img/Domination_plateau.png");
			StdDraw.text(180, 180,"Plateau du joueur 4");
		}
		
	}
	
	public static int sommeRoi()
	{
		int somme =0;
		for(int i=0; i<MonJeu.getListe_joueurs().size(); i++)
		{
			somme = somme + MonJeu.liste_joueurs.get(i).getNbRois();
		}
		return somme;
	}
	
	public static boolean affichagePieceJoueur1(String mot)
	{
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		double x = StdDraw.mouseX();
		double y = StdDraw.mouseY();
		
		//test cases joueur 1 -- validé faire la même pour les autres joueurs 2,3,4
		for (int i=0; i<5; i++)
		{
			for (int j=0; j<5; j++)
			{
				if((x >= 5 + i*TAILLE_CASE + i*TAILLE_LIGNE && x<= 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE) && (y <= 715 - j*TAILLE_CASE - j*TAILLE_LIGNE && y >= 715 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE))
					{
						if (mot == "chateau") StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/chateau.png");
						if (StdDraw.isMousePressed()) 
						{	
							MonJeu.liste_joueurs.get(0).setPlateauJoueur(j,i,mot);
							return true;
						}
					}
			}
		}
		return false;
	}
	
	public static void affichageMemoireJoueur1()
	{
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		
		String contenu;
		for(int i=0; i<5; i++)
		{
			for(int j=0; j<5; j++)
			{
				contenu = MonJeu.liste_joueurs.get(0).getPlateauJoueur(j,i);
				if (contenu == "chateau") StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/chateau.png");
	
			}
		}
	}
	
	//##########################################################
	
	public static boolean affichagePieceJoueur2(String mot)
	{
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		double x = StdDraw.mouseX();
		double y = StdDraw.mouseY();
		
		//test cases joueur 2
		for (int i=0; i<5; i++)
		{
			for (int j=0; j<5; j++)
			{
				if((x >= 365 + i*TAILLE_CASE + i*TAILLE_LIGNE && x<= 365 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE) && (y <= 355 - j*TAILLE_CASE - j*TAILLE_LIGNE && y >= 355 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE))
				{
					if (mot == "chateau") StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/chateau.png");
					if (StdDraw.isMousePressed()) 
					{	
						MonJeu.liste_joueurs.get(1).setPlateauJoueur(j,i,mot);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static void affichageMemoireJoueur2()
	{
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		
		String contenu;
		for(int i=0; i<5; i++)
		{
			for(int j=0; j<5; j++)
			{
				contenu = MonJeu.liste_joueurs.get(1).getPlateauJoueur(j,i);
				if (contenu == "chateau") StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/chateau.png");
	
			}
		}
	}
	
	// #############################################
	
	public static boolean affichagePieceJoueur3(String mot)
	{
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		double x = StdDraw.mouseX();
		double y = StdDraw.mouseY();
		
		//test cases joueur 3
		for (int i=0; i<5; i++)
		{
			for (int j=0; j<5; j++)
			{
				if((x >= 365 + i*TAILLE_CASE + i*TAILLE_LIGNE && x<= 365 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE) && (y <= 715 - j*TAILLE_CASE - j*TAILLE_LIGNE && y >= 715 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE))
				{
					if (mot == "chateau") StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/chateau.png");
					if (StdDraw.isMousePressed()) 
					{	
						MonJeu.liste_joueurs.get(2).setPlateauJoueur(j,i,mot);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static void affichageMemoireJoueur3()
	{
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		
		String contenu;
		for(int i=0; i<5; i++)
		{
			for(int j=0; j<5; j++)
			{
				contenu = MonJeu.liste_joueurs.get(2).getPlateauJoueur(j,i);
				if (contenu == "chateau") StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/chateau.png");
	
			}
		}
	}
	
	//#########################################################
	
	public static boolean affichagePieceJoueur4(String mot)
	{
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		double x = StdDraw.mouseX();
		double y = StdDraw.mouseY();
		
		//test cases joueur 4
		for (int i=0; i<5; i++)
		{
			for (int j=0; j<5; j++)
			{
				if((x >= 5 + i*TAILLE_CASE + i*TAILLE_LIGNE && x<= 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE) && (y <= 355 - j*TAILLE_CASE - j*TAILLE_LIGNE && y >= 355 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE))
				{
					if (mot == "chateau") StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/chateau.png");
					if (StdDraw.isMousePressed()) 
					{	
						MonJeu.liste_joueurs.get(3).setPlateauJoueur(j,i,mot);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static void affichageMemoireJoueur4()
	{
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		
		String contenu;
		for(int i=0; i<5; i++)
		{
			for(int j=0; j<5; j++)
			{
				contenu = MonJeu.liste_joueurs.get(3).getPlateauJoueur(j,i);
				if (contenu == "chateau") StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/chateau.png");
			}
		}
	}
}



// a faire ranger les tuiles dans l'ordre croissant
//test tuile pas deja prise
// nouvel ordre joueur prochain tour
//incrémenté les tours