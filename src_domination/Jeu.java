import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;

import edu.princeton.cs.introcs.StdDraw;

public class Jeu {
	
	private ArrayList<Joueur> liste_joueurs = new ArrayList<>();
	private int nb_tour;
	private ArrayList<Tuile> liste_tuiles = new ArrayList<>();
	private ArrayList<Integer> ordre_tour_joueur = new ArrayList<Integer>(); //attribut pierre
	private int nb_roi_place = 0; // attribut pierre
	
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
	public int getNb_roi_place() {
		return nb_roi_place;
	}
	public void setNb_roi_place(int nb_roi_place) {
		this.nb_roi_place = nb_roi_place;
	}
	public void addNb_roi_place() {
		this.nb_roi_place = this.nb_roi_place+1;
	}
	
	public void AffichageListeJoueurs()
	{
		for (int i = 0; i < liste_joueurs.size(); i++) {
			System.out.println(liste_joueurs.get(i).getPseudo());
		}
	}
	
	public void initOrdreTour(int nbJoueur)
	{
		Random rand = new Random();
		int joueur;
		int occurence = 0;
		
		if (nbJoueur == 2){
			while(this.ordre_tour_joueur.size()!=4) {
				occurence = 0;
				joueur = rand.nextInt(3);
				if (joueur != 0) {
					if (this.ordre_tour_joueur.size() == 0)this.ordre_tour_joueur.add(joueur); 
					else {
						for (int i =0; i<this.ordre_tour_joueur.size(); i++) {
						if (this.ordre_tour_joueur.get(i) == joueur)occurence = occurence+1; 	
						}
						if (occurence<2)this.ordre_tour_joueur.add(joueur);  
					}
				} 
			}
			
		}
		
		if (nbJoueur == 3){
			while(this.ordre_tour_joueur.size()!=3) {
				occurence = 0;
				joueur = rand.nextInt(4);
				if (joueur != 0) {
					if (this.ordre_tour_joueur.size() == 0)this.ordre_tour_joueur.add(joueur); 
					else {
						for (int i =0; i<this.ordre_tour_joueur.size(); i++) {
						if (this.ordre_tour_joueur.get(i) == joueur)occurence =1; 	
						}
						if (occurence==0)this.ordre_tour_joueur.add(joueur);  
					}
				}
			}
		}
		
		if (nbJoueur == 4){
			while(this.ordre_tour_joueur.size()!=4) {
				occurence = 0;
				joueur = rand.nextInt(5);
				if (joueur != 0) {
					if (this.ordre_tour_joueur.size() == 0)this.ordre_tour_joueur.add(joueur); 
					else {
						for (int i =0; i<this.ordre_tour_joueur.size(); i++) {
						if (this.ordre_tour_joueur.get(i) == joueur)occurence =1; 	
						}
						if (occurence == 0)this.ordre_tour_joueur.add(joueur); 
					}	
				}
			}
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
	
	public void affichageGlobal(int nombreJoueurs){
		affichagePlateauJoueurs(nombreJoueurs); //image des plateaux du jeu
		affichageMemoireJoueur1(); //affichage des tuiles du plateaux 1
		affichageMemoireJoueur2(); //affichage des tuiles du plateaux 1
		try {
			affichageMemoireJoueur3();
		}
		
		catch(java.lang.IndexOutOfBoundsException e) {}
		
		try{
			affichageMemoireJoueur4();
		}
		
		catch(java.lang.IndexOutOfBoundsException e) {}
	}
	
	public void affichagePlateauJoueurs(int nombreJoueurs){
		Font font = new Font("Arial", Font.BOLD, 20);
		StdDraw.setFont(font);
		
		//affichage des 2 premiers plateaux
		StdDraw.picture(180, 540, "img/Domination_plateau.png");
		StdDraw.text(180, 540,"Plateau du joueur 1");
		StdDraw.picture(540, 180, "img/Domination_plateau.png");
		StdDraw.text(540, 180,"Plateau du joueur 2");
		
		//affichage des 2 derniers plateaux
		if (nombreJoueurs >= 3){
			StdDraw.picture(540, 540, "img/Domination_plateau.png");
			StdDraw.text(540, 540,"Plateau du joueur 3");
		}
		
		if (nombreJoueurs == 4){
			StdDraw.picture(180, 180, "img/Domination_plateau.png");
			StdDraw.text(180, 180,"Plateau du joueur 4");
		}
	}
	
	public void affichageMemoireJoueur1(){
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		String contenu;
		
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				contenu = this.getListe_joueurs().get(0).getPlateauJoueur(j,i);				
				if (contenu == "chateau") StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/chateau.png");
				else if (("Champs").equals(contenu)) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
				else if (("Mer").equals(contenu)) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
				else if (("Foret").equals(contenu)) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
				else if (("Prairie").equals(contenu)) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
				else if (("Mine").equals(contenu)) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
				else if (("Montagne").equals(contenu)) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
			}
		}
	}
	
	public void affichageMemoireJoueur2(){
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		String contenu;
		
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				contenu = this.getListe_joueurs().get(1).getPlateauJoueur(j,i);
				if (contenu == "chateau") StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/chateau.png");
				else if (("Champs").equals(contenu)) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
				else if (("Mer").equals(contenu)) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
				else if (("Foret").equals(contenu)) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
				else if (("Prairie").equals(contenu)) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
				else if (("Mine").equals(contenu)) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
				else if (("Montagne").equals(contenu)) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
			}
		}
	}
	
	public void affichageMemoireJoueur3(){
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		String contenu;
		
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				contenu = this.getListe_joueurs().get(2).getPlateauJoueur(j,i);
				if (contenu == "chateau") StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/chateau.png");
				else if (("Champs").equals(contenu)) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
				else if (("Mer").equals(contenu)) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
				else if (("Foret").equals(contenu)) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
				else if (("Prairie").equals(contenu)) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
				else if (("Mine").equals(contenu)) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
				else if (("Montagne").equals(contenu)) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
			}
		}
	}
	
	public void affichageMemoireJoueur4(){
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		String contenu;
		
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				contenu = this.getListe_joueurs().get(3).getPlateauJoueur(j,i);
				if (contenu == "chateau") StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/chateau.png");
				else if (("Champs").equals(contenu)) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
				else if (("Mer").equals(contenu)) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
				else if (("Foret").equals(contenu)) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
				else if (("Prairie").equals(contenu)) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
				else if (("Mine").equals(contenu)) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
				else if (("Montagne").equals(contenu)) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
			}
		}
	}
	
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