import java.util.ArrayList;

import edu.princeton.cs.introcs.StdDraw;

public class Jeu {
	
	static ArrayList<Joueur> liste_joueurs = new ArrayList<>();
	int nb_tour;
	ArrayList<Tuile> liste_tuiles = new ArrayList<>();
	
	//attibut pierre
	static ArrayList<Integer> ordre_tour_joueur = new ArrayList<Integer>();
	
	
	
	public ArrayList<Joueur> getListe_joueurs() {
		return liste_joueurs;
	}
	public void setListe_joueurs(ArrayList<Joueur> liste_joueurs) {
		Jeu.liste_joueurs = liste_joueurs;
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
	
	public void AffichageListeJoueurs()
	{
		for (int i = 0; i < liste_joueurs.size(); i++) {
			System.out.println(liste_joueurs.get(i).getPseudo());
		}
	}
	
	public void AffichagePioche()
	{
		int posX = 1200;
		int posY = 700;
		
		for (int i = 0; i < Pioche.getTuiles_tour().size(); i++) {
			String terrain1 = Pioche.getTuiles_tour().get(i).type_tuile1;
			
			if (terrain1.equals("Champs")) {
				StdDraw.filledRectangle(posX, posY, 10, 10);
				StdDraw.setPenColor(StdDraw.YELLOW); //jaune champs
			}
			else if (terrain1.equals("Prairie")) {
				StdDraw.filledRectangle(posX, posY, 10, 10);
				StdDraw.setPenColor(82,233,89); //vert clair prairie
			}
			else if (terrain1.equals("Foret")) {
				StdDraw.filledRectangle(posX, posY, 10, 10);
				StdDraw.setPenColor(20,152,27); //vert foncé foret
			}
			else if (terrain1.equals("Mer")) {
				StdDraw.filledRectangle(posX, posY, 10, 10);
				StdDraw.setPenColor(StdDraw.BLUE); //bleu mer
			}
			else if (terrain1.equals("Montagne")) {
				StdDraw.filledRectangle(posX, posY, 10, 10);
				StdDraw.setPenColor(153,77,77); //marron montagnes
			}
			else if (terrain1.equals("Mine")) {
				StdDraw.filledRectangle(posX, posY, 10, 10);
				StdDraw.setPenColor(82,233,89); //vert clair
			}
			
			posX = posX + 30;
			
			String terrain2 = Pioche.getTuiles_tour().get(i).type_tuile2;
			
			if (terrain2.equals("Champs")) {
				StdDraw.filledRectangle(posX, posY, 10, 10);
				StdDraw.setPenColor(StdDraw.YELLOW); //jaune champs
			}
			else if (terrain2.equals("Prairie")) {
				StdDraw.filledRectangle(posX, posY, 10, 10);
				StdDraw.setPenColor(82,233,89); //vert clair prairie
			}
			else if (terrain2.equals("Foret")) {
				StdDraw.filledRectangle(posX, posY, 10, 10);
				StdDraw.setPenColor(20,152,27); //vert foncé foret
			}
			else if (terrain2.equals("Mer")) {
				StdDraw.filledRectangle(posX, posY, 10, 10);
				StdDraw.setPenColor(StdDraw.BLUE); //bleu mer
			}
			else if (terrain2.equals("Montagne")) {
				StdDraw.filledRectangle(posX, posY, 10, 10);
				StdDraw.setPenColor(153,77,77); //marron montagnes
			}
			else if (terrain2.equals("Mine")) {
				StdDraw.filledRectangle(posX, posY, 10, 10);
				StdDraw.setPenColor(82,233,89); //vert clair
			}
			
			posX = posX - 30;
			
			posY = posY + 50;
		}
		/*StdDraw.filledRectangle(1200, 700, 10, 10);
		StdDraw.setPenColor(StdDraw.RED);*/
	}

}