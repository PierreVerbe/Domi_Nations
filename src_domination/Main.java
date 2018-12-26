import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
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
		
		boolean boucleJeu = false;
		boolean flagChateau = false;
		boolean flagChateauJ1 = false;
		boolean flagChateauJ2 = false;
		boolean flagChateauJ3 = false;
		boolean flagChateauJ4 = false;

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
		
		System.out.println("Fin choix joueur");
		scan.nextLine();
		
		//paramétrage des joueurs
		for (int i=1; i<num_joueur+1; i++)
		{
			Joueur Monjoueur = new Joueur();			
			System.out.println("Bonjour joueur " + i);
			System.out.println("Pseudo joueur :");
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
			
			//System.out.println("Liste des joueurs");
			//System.out.println(allPlayers);
		}
		
		//ajout de la liste des joueurs dans l'instance MonJeu
		MonJeu.setListe_joueurs(allPlayers);
		//System.out.println(MonJeu.liste_joueurs.size());
		
		MaPioche.ImportationTuiles();
		MaPioche.PiocherTuilesJeu();
		
		//affichage plateau avec un cercle qui suit le curseur
		while(boucleJeu == false)
		{
			//placement des chateaux pour chacun des joueurs
			while(flagChateau == false)
			{
				//préparation affichage de tous les éléments de la fenêtre
				affichageGlobal(num_joueur);
				
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
				
				//affichage de tous les éléments de la fenêtre
				StdDraw.show();
				StdDraw.pause(1);
				StdDraw.clear(StdDraw.GRAY);
			}
			
			//préparation affichage de tous les éléments de la fenêtre
			affichageGlobal(num_joueur);
			
			//affichage des rois
			for (int i=0; i<4; i++)
			{
				try
				{
					MonJeu.liste_joueurs.get(i).affichageRoi();
				}
				catch(java.lang.IndexOutOfBoundsException e) {}
			}
			
			//affichage des tuiles du plateaux déjà placé
			MaPioche.PiocherTuilesTour();
			MaPioche.AffichageTuilesTour();
			//System.out.println(MaPioche.tuiles_tour.get(0));
			
			StdDraw.filledCircle(StdDraw.mouseX(), StdDraw.mouseY(), 10);
			StdDraw.setPenColor(StdDraw.RED);
			
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
		//affichage des 2 premiers plateaux
		StdDraw.picture(180, 540, "Domination_plateau.png");
		StdDraw.picture(540, 180, "Domination_plateau.png");
		
		//affichage des 2 derniers plateaux
		if (nombreJoueurs >= 3)StdDraw.picture(540, 540, "Domination_plateau.png");
		if (nombreJoueurs == 4)StdDraw.picture(180, 180, "Domination_plateau.png");
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
						if (mot == "chateau") StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "chateau.png");
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
				if (contenu == "chateau") StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "chateau.png");
	
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
					if (mot == "chateau") StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "chateau.png");
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
				if (contenu == "chateau") StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "chateau.png");
	
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
					if (mot == "chateau") StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "chateau.png");
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
				if (contenu == "chateau") StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "chateau.png");
	
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
					if (mot == "chateau") StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "chateau.png");
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
				if (contenu == "chateau") StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "chateau.png");
			}
		}
	}
}