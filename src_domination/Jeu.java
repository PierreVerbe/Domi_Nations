import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;

public class Jeu {
	
	private ArrayList<Joueur> liste_joueurs = new ArrayList<>();
	public int nb_tour;
	private ArrayList<Tuile> liste_tuiles = new ArrayList<>();
	private static Scanner scan;
	private static Scanner scan2;
	private Scanner scan3;
	
	
	public ArrayList<Joueur> getListe_joueurs() {
		return liste_joueurs;
	}
	
	public ArrayList<String> getNames() {
		ArrayList<String> names = new ArrayList<>();
		for(Joueur player : this.liste_joueurs) {
			names.add(player.getPseudo());
		}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
		return names;
	}
	
	public void setListe_joueurs(ArrayList<Joueur> liste_joueurs) {
		this.liste_joueurs = liste_joueurs;
	}
	public int getNb_tour() {
		return nb_tour;
	}
	public void setNb_tour(int nb_tour) {
		this.nb_tour = nb_tour;
	}
	public ArrayList<Tuile> getListe_tuiles() {
		return liste_tuiles;
	}
	public void setListe_tuiles(ArrayList<Tuile> liste_tuiles) {
		this.liste_tuiles = liste_tuiles;
	}
	
	public int getSizeList() {
		return liste_joueurs.size();
	}

	public int askNbPlayer() {
		scan = new Scanner(System.in);
		System.out.print("Saisir le nombre de joueur : ");
		int nbPlayer = 0;
		try{nbPlayer = scan.nextInt();} catch(Exception e) {nbPlayer=0;}
		
		while(nbPlayer<2 || nbPlayer>4) {
			scan3 = new Scanner(System.in);
			System.out.println("Le nombre de joueur doit �tre 2,3 ou 4");
			System.out.print("Saisir le nombre de joueur : ");
			try{nbPlayer = scan3.nextInt();} catch(Exception e2) {nbPlayer=0;}
		}
		
		return nbPlayer;
	}
	public void initGame(int nbJoueur) {
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
			this.liste_joueurs.add(allPlayers.get(i));

		}
	}
}