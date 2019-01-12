import java.util.HashMap;
import edu.princeton.cs.introcs.StdDraw;

public class Plateau {
	private int longueur;
	private int largeur;
	private String[][] tableau_tuiles_plateau = new String[longueur][largeur];
	
	public Plateau(){}
	
	public Plateau(int longueur, int largeur){
		this.longueur=longueur;
		this.largeur=largeur;
		this.tableau_tuiles_plateau = new String[longueur][largeur];
	}
	
	public int getLongueur() {
		return longueur;
	}
	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}
	public int getLargeur() {
		return largeur;
	}
	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}
	public String[][] getTableau_tuiles_plateau() {
		return tableau_tuiles_plateau;
	}
	public void setTableau_tuiles_plateau(String[][] tableau_tuiles_plateau) {
		this.tableau_tuiles_plateau = tableau_tuiles_plateau;
	}

	public void CompterCouronnes() {
		HashMap<Integer, Integer> casesVisitees = new HashMap<>();
		int score = 0;
		for (int i = 0; i < this.tableau_tuiles_plateau.length; i++) {
			for (int j = 0; j < this.tableau_tuiles_plateau.length; j++) {
				int nbCasesDimension = 0;
				int nbCouronnesDimension = 0;
				
				if(!casesVisitees.containsKey(i) && j != casesVisitees.get(i)) {
					casesVisitees.put(i, j);
					String terrain = this.tableau_tuiles_plateau[i][j].split("-")[0];
					
					try {
						String terrainHaut = this.tableau_tuiles_plateau[i-1][j].split("-")[0];
						
						if (terrain.equals(terrainHaut)) {
							nbCasesDimension = nbCasesDimension + 1;
							nbCouronnesDimension = Integer.parseInt(this.tableau_tuiles_plateau[i-1][j].split("-")[1]);
							casesVisitees.put(i-1, j);
						}
					}
					
					catch(Exception e) {}
						
					try {
						String terrainBas = this.tableau_tuiles_plateau[i+1][j].split("-")[0];
		
						if (terrain.equals(terrainBas)) {
							nbCasesDimension += 1;
							nbCouronnesDimension = Integer.parseInt(this.tableau_tuiles_plateau[i+1][j].split("-")[1]);
							casesVisitees.put(i+1, j);
						}
					}
					
					catch(Exception e) {}
					
					try {
						String terrainGauche = this.tableau_tuiles_plateau[i][j-1].split("-")[0];
						
						if (terrain.equals(terrainGauche)) {
							nbCasesDimension = nbCasesDimension + 1;
							nbCouronnesDimension = Integer.parseInt(this.tableau_tuiles_plateau[i][j-1].split("-")[1]);
							casesVisitees.put(i, j-1);
						}
					}
					
					catch(Exception e) {}
					
					try {
						String terrainDroit = this.tableau_tuiles_plateau[i][j+1].split("-")[0];
						
						if (terrain.equals(terrainDroit)) {
							nbCasesDimension = nbCasesDimension + 1;
							nbCouronnesDimension = Integer.parseInt(this.tableau_tuiles_plateau[i][j+1].split("-")[1]);
							casesVisitees.put(i, j+1);
						}
					}
					
					catch(Exception e) {}
				}
				score = score + (nbCasesDimension * nbCouronnesDimension);
			}
		}
	}
	
	
	public void RemplirPlateau(int i, int j, String mot) {			
		tableau_tuiles_plateau[i][j]=mot;
	}
	
	public String RecupererPlateau(int i, int j) {
		return tableau_tuiles_plateau[i][j];
	}
	
	public void affichagePlateau() {
		System.out.println("debut tab");
		for(int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				System.out.print(tableau_tuiles_plateau[i][j] + " ");
			}
			System.out.println();
		}		
	}
	
	public boolean affichagePieceJoueur1(String mot){
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		double x = StdDraw.mouseX();
		double y = StdDraw.mouseY();
		
		//test cases joueur 1 -- validé faire la même pour les autres joueurs 2,3,4
		for (int i=0; i<5; i++){
			for (int j=0; j<5; j++){
				if((x >= 5 + i*TAILLE_CASE + i*TAILLE_LIGNE && x<= 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE) && (y <= 715 - j*TAILLE_CASE - j*TAILLE_LIGNE && y >= 715 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE)){
					if (mot == "chateau") StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/chateau.png");
					if (StdDraw.isMousePressed()) {	
						this.RemplirPlateau(j,i,mot);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean affichagePieceJoueur2(String mot){
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		double x = StdDraw.mouseX();
		double y = StdDraw.mouseY();
		
		//test cases joueur 2
		for (int i=0; i<5; i++){
			for (int j=0; j<5; j++){
				if((x >= 365 + i*TAILLE_CASE + i*TAILLE_LIGNE && x<= 365 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE) && (y <= 355 - j*TAILLE_CASE - j*TAILLE_LIGNE && y >= 355 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE)){
					if (mot == "chateau") StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/chateau.png");
					if (StdDraw.isMousePressed()) {	
						this.RemplirPlateau(j,i,mot);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean affichagePieceJoueur3(String mot){
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		double x = StdDraw.mouseX();
		double y = StdDraw.mouseY();
		
		//test cases joueur 3
		for (int i=0; i<5; i++){
			for (int j=0; j<5; j++){
				if((x >= 365 + i*TAILLE_CASE + i*TAILLE_LIGNE && x<= 365 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE) && (y <= 715 - j*TAILLE_CASE - j*TAILLE_LIGNE && y >= 715 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE)){
					if (mot == "chateau") StdDraw.picture(TAILLE_CASE/2 + 365 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 647 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/chateau.png");
					if (StdDraw.isMousePressed()) {	
						this.RemplirPlateau(j,i,mot);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean affichagePieceJoueur4(String mot){
		final int TAILLE_CASE = 68;
		final int TAILLE_LIGNE = 2;
		double x = StdDraw.mouseX();
		double y = StdDraw.mouseY();
		
		//test cases joueur 4
		for (int i=0; i<5; i++){
			for (int j=0; j<5; j++){
				if((x >= 5 + i*TAILLE_CASE + i*TAILLE_LIGNE && x<= 5 + (i+1)*TAILLE_CASE + (i+1)*TAILLE_LIGNE) && (y <= 355 - j*TAILLE_CASE - j*TAILLE_LIGNE && y >= 355 - (j+1)*TAILLE_CASE - (j+1)*TAILLE_LIGNE)){
					if (mot == "chateau") StdDraw.picture(TAILLE_CASE/2 + 5 + i*TAILLE_CASE + i*TAILLE_LIGNE, TAILLE_CASE/2 + 287 - j*TAILLE_CASE - j*TAILLE_LIGNE, "img/chateau.png");
					if (StdDraw.isMousePressed()) {	
						this.RemplirPlateau(j,i,mot);
						return true;
					}
				}
			}
		}
		return false;
	}
}