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
		int t =0;

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
			System.out.println("Liste des joueurs");
			System.out.println(allPlayers);
			
			//public void setCouleur(Color couleur);
			//Monjoueur.pseudo=scan.nextLine();
			
		}
		
		//ajout de la liste des joueurs dans l'instance MonJeu
		MonJeu.setListe_joueurs(allPlayers);
		System.out.println(MonJeu.liste_joueurs);
		
		
		MaPioche.ImportationTuiles();
		MaPioche.PiocherTuilesJeu();
		
		//affichage plateau avec un cercle qui suit le curseur
		while(t< 10000)
		{
			//image des plateaux du jeu
			affichageFenetre(num_joueur);
			
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
		
		
		
		/*
		StdDraw.pause(2000);
		StdDraw.clear(StdDraw.GRAY);
		StdDraw.show();*/
		
		
		
		System.out.println(MonJeu.getListe_joueurs());
		
		
		//System.out.println(MonJeu.getNames());
		//MonJeu.initGame(MonJeu.askNbPlayer());
		//System.out.println(MonJeu.getNames());
		System.out.println("Vous avez fini de jouer");
		
	}
	
	
//	public static void Pierre() {
//		StdDraw.setXscale(-WIDTH, X_MAX+WIDTH);
//		StdDraw.setYscale(-WIDTH, Y_MAX+WIDTH);
//		for( int y= Y_MAX; y >= 0; --y){ 
//			for(int x= 0; x <= X_MAX; ++x){ 
//				StdDraw.clear(StdDraw.GRAY);
//				StdDraw.setPenColor();
//				StdDraw.filledCircle(x, y, 10); // display and pause for 20 ms StdDraw.show(20);
//				StdDraw.pause(60);
//			}
//		}
//	}
	
	public static void affichageFenetre(int nombreJoueurs)
	{
		//affichage des 2 premiers plateaux
		StdDraw.picture(180, 540, "Domination_plateau.png");
		StdDraw.picture(540, 180, "Domination_plateau.png");
		
		//affichage des 2 derniers plateaux
		if (nombreJoueurs >= 3)StdDraw.picture(540, 540, "Domination_plateau.png");
		if (nombreJoueurs == 4)StdDraw.picture(180, 180, "Domination_plateau.png");
	}
	
}