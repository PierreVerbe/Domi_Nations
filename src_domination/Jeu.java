import java.util.ArrayList;

public class Jeu {
	
	static ArrayList<Joueur> liste_joueurs = new ArrayList<>();
	int nb_tour;
	ArrayList<Tuile> liste_tuiles = new ArrayList<>();
	
	
	public ArrayList<Joueur> getListe_joueurs() {
		return liste_joueurs;
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
	
	public static int getSizeList() {
		return liste_joueurs.size();
	}

}