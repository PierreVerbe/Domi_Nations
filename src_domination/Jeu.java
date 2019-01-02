import java.util.ArrayList;

import edu.princeton.cs.introcs.StdDraw;

public class Jeu {
	
	private ArrayList<Joueur> liste_joueurs = new ArrayList<>();
	private int nb_tour;
	private ArrayList<Tuile> liste_tuiles = new ArrayList<>();
	private ArrayList<Integer> ordre_tour_joueur = new ArrayList<Integer>(); //attribut pierre
	
	public Jeu(){}
	
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
	public int getSizeList() {
		return this.liste_joueurs.size();
	}	
	public ArrayList<Integer> getOrdre_tour_joueur() {
		return ordre_tour_joueur;
	}
	public void setOrdre_tour_joueur(ArrayList<Integer> ordre_tour_joueur) {
		this.ordre_tour_joueur = ordre_tour_joueur;
	}
	
	public void AffichageListeJoueurs()
	{
		for (int i = 0; i < liste_joueurs.size(); i++) {
			System.out.println(liste_joueurs.get(i).getPseudo());
		}
	}
	
	/* Pas utilisé 
	public void AffichagePioche()
	{
		int posX = 1200;
		int posY = 700;
		
		for (int i = 0; i < Pioche.getTuiles_tour().size(); i++) {
			String terrain1 = Pioche.getTuiles_tour().get(i).getType_tuile1();
			
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
			
			String terrain2 = Pioche.getTuiles_tour().get(i).getType_tuile2();
			
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
		StdDraw.setPenColor(StdDraw.RED);
	} */
	
	public void AffichageRoiTour()
	{
		final int L_TUILE = 136;
		
		for (int i=0; i<this.getOrdre_tour_joueur().size(); i++)
		{	
			if(this.ordre_tour_joueur.get(i) == 1)StdDraw.picture( 1040+(L_TUILE/4), 540 - i*90, "img/roiBleu.png");
			else if(this.ordre_tour_joueur.get(i) == 2)StdDraw.picture( 1040+(L_TUILE/4), 540 - i*90, "img/roiRouge.png");
			else if(this.ordre_tour_joueur.get(i) == 3)StdDraw.picture( 1040+(L_TUILE/4), 540 - i*90, "img/roiVert.png");
			else if(this.ordre_tour_joueur.get(i) == 4)StdDraw.picture( 1040+(L_TUILE/4), 540 - i*90, "img/roiRose.png");	
		}
	}
	
	public boolean isTuileOccupied(int numTuile){
		for (int i=0; i<this.getListe_joueurs().get(0).getChoix_tuile_tour().size(); i++)if (this.getListe_joueurs().get(0).getChoix_tuile_tour().get(i) == numTuile) return true;
		for (int i=0; i<this.getListe_joueurs().get(1).getChoix_tuile_tour().size(); i++)if (this.getListe_joueurs().get(1).getChoix_tuile_tour().get(i) == numTuile) return true;
		
		try {
			for (int i=0; i<this.getListe_joueurs().get(2).getChoix_tuile_tour().size(); i++){
				if (this.getListe_joueurs().get(2).getChoix_tuile_tour().get(i) == numTuile) return true;
			}
		}
		catch (java.lang.IndexOutOfBoundsException e) {}
		
		try {
			for (int i=0; i<this.getListe_joueurs().get(3).getChoix_tuile_tour().size(); i++)if (this.getListe_joueurs().get(3).getChoix_tuile_tour().get(i) == numTuile) return true;
		}
		catch (java.lang.IndexOutOfBoundsException e) {}
		return false;			
	}
}