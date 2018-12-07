//import edu.princeton.cs.introcs.StdDraw;

import java.util.ArrayList;

public class Main {
	
	public final static int X_MAX= 64;
	public final static int Y_MAX= 24;
	public final static float WIDTH= 0.5f;
	ArrayList<Joueur> allPlayers = new ArrayList<>();
	public static Jeu MonJeu = new Jeu();
	public static void main(String[] args) {

		System.out.println("Bienvenue dans le jeu Domination !");
		System.out.println(MonJeu.getListe_joueurs());
		System.out.println(MonJeu.getNames());
		MonJeu.initGame(MonJeu.askNbPlayer());
		System.out.println(MonJeu.getNames());
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
	
}