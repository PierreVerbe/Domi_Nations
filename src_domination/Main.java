//import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public final static int X_MAX= 64;
	public final static int Y_MAX= 24;
	public final static float WIDTH= 0.5f;
	ArrayList<Joueur> allPlayers = new ArrayList<>();
	private static Scanner scan;
	private static Scanner scan2;

	public static void main(String[] args) {
		
		System.out.println("Bienvenue dans le jeu Domination !");
		
		initGame(askNbPlayer());
		
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
	
	private static int askNbPlayer() {
		scan = new Scanner(System.in);
		System.out.print("Saisir le nombre de joueur : ");
		int nbPlayer = scan.nextInt();
		return nbPlayer;
	}
	public static void initGame(int nbJoueur) {
		ArrayList<Joueur> allPlayers = new ArrayList<>();
		Joueur J1 = new Joueur(Color.PINK);
		Joueur J2 = new Joueur(Color.BLUE);
		Joueur J3 = new Joueur(Color.RED);
		Joueur J4 = new Joueur(Color.ORANGE);
		allPlayers.add(J1);
		allPlayers.add(J2);
		allPlayers.add(J3);
		allPlayers.add(J4);
		
		scan2 = new Scanner(System.in);
		for(int i=0; i<nbJoueur;i++)
		{
			System.out.print("Saisir votre pseudo : ");
			String pseudo = scan2.nextLine();
			
			allPlayers.get(i).setPseudo(pseudo);
			System.out.println(allPlayers.get(i).getPseudo());

		}
	}
}