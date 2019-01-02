import java.util.ArrayList;
import java.util.HashMap;

public class Plateau {
	
	private int longueur;
	private int largeur;
	private ArrayList<Tuile> tuile_joueur = new ArrayList<>();
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
	public ArrayList<Tuile> getTuile_joueur() {
		return tuile_joueur;
	}
	public void setTuile_joueur(ArrayList<Tuile> tuile_joueur) {
		this.tuile_joueur = tuile_joueur;
	}
	public String[][] getTableau_tuiles_plateau() {
		return tableau_tuiles_plateau;
	}
	public void setTableau_tuiles_plateau(String[][] tableau_tuiles_plateau) {
		this.tableau_tuiles_plateau = tableau_tuiles_plateau;
	}

	public boolean TuilesCompatibles(Tuile tuile1, Tuile tuile2) {
		if ((tuile1.getType_tuile1() == tuile2.getType_tuile1()) || (tuile1.getType_tuile1() == tuile2.getType_tuile2()) || (tuile1.getType_tuile2() == tuile2.getType_tuile1()) || (tuile1.getType_tuile2() == tuile2.getType_tuile2()))return true;	
		else return false;	
	}
	
	/*public void CompterCouronnes() {
		for (int i = 0; i < this.longueur; i++) {
			for (int j = 0; i <this.largeur; i++) {
				for (int k = 0; k < this.tuile_joueur.size(); k++) {
					Tuile tuile = this.tuile_joueur.get(k);
					if ((tuile.getPosition_x1() == i && tuile.getPosition_y1() == j) || (tuile.getPosition_x2() == i && tuile.getPosition_y2() == j)) {
						ArrayList<Tuile> domaine = new ArrayList<>();
					}
				}
			}
		}
	}*/
	
	public void CompterCouronnes() {
		HashMap<Integer, Integer> casesVisitees = new HashMap<>();
		int score = 0;
		for (int i = 0; i < this.tableau_tuiles_plateau.length; i++) {
			for (int j = 0; j < this.tableau_tuiles_plateau.length; j++) {
				int nbCasesDimension = 0;
				int nbCouronnesDimension = 0;
				
				if(!casesVisitees.containsKey(i) && j != casesVisitees.get(i)) {
					casesVisitees.put(i, j);
					String terrain = this.tableau_tuiles_plateau[i][j].split(",")[0];
					
					try {
						String terrainHaut = this.tableau_tuiles_plateau[i-1][j].split(",")[0];
						
						if (terrain.equals(terrainHaut)) {
							nbCasesDimension = nbCasesDimension + 1;
							nbCouronnesDimension = Integer.parseInt(this.tableau_tuiles_plateau[i-1][j].split(",")[1]);
							casesVisitees.put(i-1, j);
						}
					}
					
					catch(Exception e) {}
						
					try {
						String terrainBas = this.tableau_tuiles_plateau[i+1][j].split(",")[0];
		
						if (terrain.equals(terrainBas)) {
							nbCasesDimension = nbCasesDimension + 1;
							nbCouronnesDimension = Integer.parseInt(this.tableau_tuiles_plateau[i+1][j].split(",")[1]);
							casesVisitees.put(i+1, j);
						}
					}
					
					catch(Exception e) {}					}
					
					try {
						String terrainGauche = this.tableau_tuiles_plateau[i][j-1].split(",")[0];
						
						if (terrain.equals(terrainGauche)) {
							nbCasesDimension = nbCasesDimension + 1;
							nbCouronnesDimension = Integer.parseInt(this.tableau_tuiles_plateau[i][j-1].split(",")[1]);
							casesVisitees.put(i, j-1);
						}
					}
					
					catch(Exception e) {}
					
					try {
						String terrainDroit = this.tableau_tuiles_plateau[i][j+1].split(",")[0];
						
						if (terrain.equals(terrainDroit)) {
							nbCasesDimension = nbCasesDimension + 1;
							nbCouronnesDimension = Integer.parseInt(this.tableau_tuiles_plateau[i][j+1].split(",")[1]);
							casesVisitees.put(i, j+1);
						}
					}
					
					catch(Exception e) {}
				}
				score = score + nbCouronnesDimension;
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
}