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
	//public static ArrayList<Plateau> allBordGame = new ArrayList<>();
	
	public static Jeu MonJeu = new Jeu();
	public static Pioche MaPioche = new Pioche();
	
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		
		//feature doublebuffering
		StdDraw.enableDoubleBuffering();
		int num_joueur = 0;
		int t =0;
		boolean flagChateau = false;
		boolean flagChateauJ1 = false;
		boolean flagChateauJ2 = false;
		boolean flagChateauJ3 = false;
		boolean flagChateauJ4 = false;

		System.out.println("Bienvenue dans le jeu Domination !");
		
		//Cr�ation de la fen�tre de jeu
		StdDraw.setCanvasSize(X_MAX, Y_MAX);
		StdDraw.setXscale(-WIDTH, X_MAX+WIDTH);
		StdDraw.setYscale(-WIDTH, Y_MAX+WIDTH);
		StdDraw.clear(StdDraw.GRAY); 
		
		//S�lection du nombre de joueur
		while (num_joueur == 0) 
		{
			try {
				System.out.println("S�lectionner un nombre de joueur: 2, 3 ou 4");
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
		
		//param�trage des joueurs
		for (int i=1; i<num_joueur+1; i++)
		{
			Joueur Monjoueur = new Joueur();
			//Plateau MonPlateau = new Plateau(5,5);
			
			System.out.println("Bonjour joueur " + i);
			
			System.out.println("Pseudo joueur :");
			
			Monjoueur.setPseudo(scan.nextLine());
			
			//d�finition des couleurs des joueurs
			System.out.print("Vous �tes un joueur de couleur ");
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
			
			//D�finition du nombre de roi en fonction du nombre de joueur
			if(num_joueur == 2)Monjoueur.setNbRois(2);
			else Monjoueur.setNbRois(1);
			
			//affichage parametres joueur
			Monjoueur.infoJoueur();
			
			//Ajout du joueur au jeu
			allPlayers.add(Monjoueur);
			
			System.out.println("Liste des joueurs");
			System.out.println(allPlayers);
			
			//Ajout du plateau
			//allBordGame.add(MonPlateau);
		}
		
		//ajout de la liste des joueurs dans l'instance MonJeu
		MonJeu.setListe_joueurs(allPlayers);
		System.out.println(MonJeu.liste_joueurs);
		
		
		MaPioche.ImportationTuiles();
		MaPioche.PiocherTuilesJeu();
		System.out.println(MaPioche.tuiles_piochees.size());
		
		//affichage plateau avec un cercle qui suit le curseur
		while(t< 10000)
		{
			
			//placement chateau
			while(flagChateau == false)
			{
				//image des plateaux du jeu
				affichageFenetre(num_joueur);
				
				//affichage des tuiles du plateaux d�j� plac�
				plateauAffichage1();
				plateauAffichage2();
				try
				{
					plateauAffichage3();
				}
				catch(java.lang.IndexOutOfBoundsException e) {}
				
				try
				{
					plateauAffichage4();
				}
				catch(java.lang.IndexOutOfBoundsException e) {}
				
				
				if (flagChateauJ1 == false)
				{
					flagChateauJ1 = plateauPlacementPiece1("chateau");
					MonJeu.liste_joueurs.get(0).affichagePlateauJoueur();
				}
				
				else if(flagChateauJ2 == false)
				{
					flagChateauJ2 = plateauPlacementPiece2("chateau");
					MonJeu.liste_joueurs.get(1).affichagePlateauJoueur();
					if(num_joueur==2 && flagChateauJ2==true)flagChateau=true;
				}
				
				else if(num_joueur >= 3 && flagChateauJ3 == false)
				{
					flagChateauJ3 = plateauPlacementPiece3("chateau");
					MonJeu.liste_joueurs.get(2).affichagePlateauJoueur();
					if(num_joueur==3 && flagChateauJ3==true)flagChateau=true;
				}
				
				else if(num_joueur == 4 && flagChateauJ4 == false)
				{
					flagChateauJ4 = plateauPlacementPiece4("chateau");
					MonJeu.liste_joueurs.get(3).affichagePlateauJoueur();
					if(num_joueur==4 && flagChateauJ4==true)flagChateau=true;
				}
				
				StdDraw.show();
				StdDraw.pause(1);
				StdDraw.clear(StdDraw.GRAY);
			}
			
			//image des plateaux du jeu
			affichageFenetre(num_joueur);
			
			//affichage des tuiles du plateaux d�j� plac�
			plateauAffichage1();
			plateauAffichage2();
			
			try
			{
				plateauAffichage3();
			}
			catch(java.lang.IndexOutOfBoundsException e) {}
			
			try
			{
				plateauAffichage4();
			}
			catch(java.lang.IndexOutOfBoundsException e) {}
			
			//.png pion 28*68
			StdDraw.picture( 750, 650, "roiBleu.png");
			StdDraw.picture( 780, 650, "roiBleu.png");
			StdDraw.picture( 750, 550, "roiRouge.png");
			StdDraw.picture( 780, 550, "roiRouge.png");
			StdDraw.picture( 750, 450, "roiVert.png");
			StdDraw.picture( 750, 350, "roiRose.png");
			
			//affichage des tuiles du plateaux d�j� plac�
			//MaPioche.PiocherTuilesTour();
			//System.out.println(MaPioche.tuiles_tour.get(0));
			
			StdDraw.filledCircle(StdDraw.mouseX(), StdDraw.mouseY(), 10);
			StdDraw.setPenColor(StdDraw.RED);
			
			StdDraw.show();
			StdDraw.pause(1);
			StdDraw.clear(StdDraw.GRAY);
			t++;
		}
		StdDraw.show();		
		
		System.out.println(MonJeu.getListe_joueurs());
		
		//System.out.println(MonJeu.getNames());
		//MonJeu.initGame(MonJeu.askNbPlayer());
		//System.out.println(MonJeu.getNames());
		System.out.println("Vous avez fini de jouer");
	}
	
	public static void affichageFenetre(int nombreJoueurs)
	{
		//affichage des 2 premiers plateaux
		StdDraw.picture(180, 540, "Domination_plateau.png");
		StdDraw.picture(540, 180, "Domination_plateau.png");
		
		//affichage des 2 derniers plateaux
		if (nombreJoueurs >= 3)StdDraw.picture(540, 540, "Domination_plateau.png");
		if (nombreJoueurs == 4)StdDraw.picture(180, 180, "Domination_plateau.png");
	}
	
	public static boolean plateauPlacementPiece1(String mot)
	{
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		double x = StdDraw.mouseX();
		double y = StdDraw.mouseY();
		
		//test cases joueur 1 -- valid� faire la m�me pour les autres joueurs 2,3,4
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
	
	public static void plateauAffichage1()
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
	
	public static boolean plateauPlacementPiece2(String mot)
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
	
	public static void plateauAffichage2()
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
	
	public static boolean plateauPlacementPiece3(String mot)
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
	
	public static void plateauAffichage3()
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
	
	public static boolean plateauPlacementPiece4(String mot)
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
	
	public static void plateauAffichage4()
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