import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;

import edu.princeton.cs.introcs.StdDraw;

public class Jeu {
	
	private ArrayList<Joueur> liste_joueurs = new ArrayList<>();
	private int nb_tour;
	private ArrayList<Integer> ordre_tour_joueur = new ArrayList<Integer>();
	private int nb_roi_place = 0;
	
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
	
	public void CreationJoueurs(int nbJoueur) {
		for (int i=1; i<nbJoueur+1; i++){
			Joueur monJoueur = new Joueur();
			
			String pseudo = "";
			char lettreEnCours;
			boolean isNotFinish = true;

			while(pseudo.length() < 16 && isNotFinish) {
				StdDraw.text(650, 600, "Votre pseudo doit faire entre 3 et 16 caractères. Appuyez sur entrée pour valider votre pseudo.");
				StdDraw.picture(625, 545-i*75, "img/cadre"+i+".png");
				StdDraw.text(450, 543-i*75, "Joueur " + i + " :");

				if(StdDraw.hasNextKeyTyped()) {
					lettreEnCours = StdDraw.nextKeyTyped();
					if(lettreEnCours == '\b' && pseudo.length()>0) pseudo = pseudo.substring(0, pseudo.length()-1);
					else if(lettreEnCours == '\n' && pseudo.length() >= 3) isNotFinish = false;
					else if (Character.isLetter(lettreEnCours) || (lettreEnCours == ' ' && pseudo.length()>0)) pseudo = pseudo + lettreEnCours;
				}
				
				StdDraw.text(625, 542-i*75, pseudo);
				StdDraw.show();
			}
			
			monJoueur.setPseudo(pseudo);
			
			//définition des couleurs des joueurs
			if (i == 1)monJoueur.setCouleur(Color.BLUE);
			else if (i == 2)monJoueur.setCouleur(Color.RED);
			else if (i == 3) monJoueur.setCouleur(Color.GREEN);	
			else if (i == 4)monJoueur.setCouleur(Color.PINK);
			
			//Définition du nombre de roi en fonction du nombre de joueur
			if(nbJoueur == 2)monJoueur.setNbRois(2);
			else monJoueur.setNbRois(1);
			
			//affichage parametres joueur
			monJoueur.infoJoueur();
			
			//Ajout du joueur au jeu
			this.getListe_joueurs().add(monJoueur);
		}
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
	
	public void affichageGlobal(int nombreJoueurs){
		affichagePlateauJoueurs(nombreJoueurs); //image des plateaux du jeu
		affichageMemoireJoueur1(); //affichage des tuiles du plateaux 1
		affichageMemoireJoueur2(); //affichage des tuiles du plateaux 1
		interfaceControle();
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
		Font fontPlateau = new Font("Arial", Font.BOLD, 20);
		StdDraw.setFont(fontPlateau);
		
		//affichage des 2 premiers plateaux
		StdDraw.picture(180, 540, "img/Domination_plateau.png");
		StdDraw.text(180, 540,"Plateau de " + getListe_joueurs().get(0).getPseudo());
		StdDraw.picture(540, 180, "img/Domination_plateau.png");
		StdDraw.text(540, 180,"Plateau de " + getListe_joueurs().get(1).getPseudo());
		
		//affichage des 2 derniers plateaux
		if (nombreJoueurs >= 3){
			StdDraw.picture(540, 540, "img/Domination_plateau.png");
			StdDraw.text(540, 540,"Plateau de " + getListe_joueurs().get(2).getPseudo());
		}
		
		if (nombreJoueurs == 4){
			StdDraw.picture(180, 180, "img/Domination_plateau.png");
			StdDraw.text(180, 180,"Plateau de " + getListe_joueurs().get(3).getPseudo());
		}
	}
	
	public void interfaceControle(){
		Font fontFenetre = new Font("Verdana", Font.BOLD, 20);
		StdDraw.setFont(fontFenetre);
		StdDraw.text(1200, 700, "N° tour : " + this.getNb_tour());
		StdDraw.text(1100, 20, "Ordre de jeu : " + this.getOrdre_tour_joueur());
	}
	
	public int sommeRoi(){
		int somme =0;
		for(int i=0; i<this.getListe_joueurs().size(); i++)somme = somme + this.getListe_joueurs().get(i).getNbRois();
		return somme;
	}
	
	public void affichageMemoireJoueur1(){
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				try {
					String[] contenu = this.getListe_joueurs().get(0).getPlateauJoueur(j,i).split("-");	
					if (contenu[0] == "chateau") StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/chateau.png");
					else if (("Champs").equals(contenu[0])) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
					else if (("Mer").equals(contenu[0])) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
					else if (("Foret").equals(contenu[0])) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
					else if (("Prairie").equals(contenu[0])) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
					else if (("Mine").equals(contenu[0])) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
					else if (("Montagne").equals(contenu[0])) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
				
					try {
						if (("1").equals(contenu[1])) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/1couronne.png");
						else if (("2").equals(contenu[1])) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/2couronnes.png");
						else if (("3").equals(contenu[1])) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/3couronnes.png");
					}
					
					catch(java.lang.ArrayIndexOutOfBoundsException e) {}					
				}
				catch(java.lang.NullPointerException e){}			
			}
		}
	}
	
	public void affichageMemoireJoueur2(){
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				try {
					String[] contenu = this.getListe_joueurs().get(1).getPlateauJoueur(j,i).split("-");	
					if (contenu[0] == "chateau") StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/chateau.png");
					else if (("Champs").equals(contenu[0])) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
					else if (("Mer").equals(contenu[0])) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
					else if (("Foret").equals(contenu[0])) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
					else if (("Prairie").equals(contenu[0])) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
					else if (("Mine").equals(contenu[0])) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
					else if (("Montagne").equals(contenu[0])) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
					
					try {
						if (("1").equals(contenu[1])) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/1couronne.png");
						else if (("2").equals(contenu[1])) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/2couronnes.png");
						else if (("3").equals(contenu[1])) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/3couronnes.png");
					}
					
					catch(java.lang.ArrayIndexOutOfBoundsException e) {}		
				}
				catch(java.lang.NullPointerException e) {}
			}
		}
	}
	
	public void affichageMemoireJoueur3(){
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				try {
					String[] contenu = this.getListe_joueurs().get(2).getPlateauJoueur(j,i).split("-");	
					if (contenu[0] == "chateau") StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/chateau.png");
					else if (("Champs").equals(contenu[0])) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
					else if (("Mer").equals(contenu[0])) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
					else if (("Foret").equals(contenu[0])) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
					else if (("Prairie").equals(contenu[0])) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
					else if (("Mine").equals(contenu[0])) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
					else if (("Montagne").equals(contenu[0])) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
					
					try {
						if (("1").equals(contenu[1])) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/1couronne.png");
						else if (("2").equals(contenu[1])) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/2couronnes.png");
						else if (("3").equals(contenu[1])) StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/3couronnes.png");
					}
					
					catch(java.lang.ArrayIndexOutOfBoundsException e) {}	
				}
				catch(java.lang.NullPointerException e) {}
			}
		}
	}
	
	public void affichageMemoireJoueur4(){
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				try {
					String[] contenu = this.getListe_joueurs().get(3).getPlateauJoueur(j,i).split("-");	
					if (contenu[0] == "chateau") StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/chateau.png");
					else if (("Champs").equals(contenu[0])) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/champs.png");
					else if (("Mer").equals(contenu[0])) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mer.png");
					else if (("Foret").equals(contenu[0])) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/foret.png");
					else if (("Prairie").equals(contenu[0])) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/prairie.png");
					else if (("Mine").equals(contenu[0])) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/mine.png");
					else if (("Montagne").equals(contenu[0])) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/montagne.png");
					
					try {
						if (("1").equals(contenu[1])) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/1couronne.png");
						else if (("2").equals(contenu[1])) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/2couronnes.png");
						else if (("3").equals(contenu[1])) StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/3couronnes.png");
					}
					
					catch(java.lang.ArrayIndexOutOfBoundsException e) {}
				}
				catch(java.lang.NullPointerException e) {}
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