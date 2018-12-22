import java.util.ArrayList;
import java.util.HashMap;

public class Plateau {
	
	int longueur;
	int largeur;
	ArrayList<Tuile> tuile_joueur = new ArrayList<>();
	String[][] tableau_tuiles_plateau = new String[longueur][largeur];
	
	public boolean TuilesCompatibles(Tuile tuile1, Tuile tuile2) {
		



		if ((tuile1.getType_tuile1() == tuile2.getType_tuile1()) || (tuile1.getType_tuile1() == tuile2.getType_tuile2()) || (tuile1.getType_tuile2() == tuile2.getType_tuile1()) || (tuile1.getType_tuile2() == tuile2.getType_tuile2())) {	
			return true;	
		}
		else
			return false;

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
					catch(Exception e) {
						
					}
					
					try {
						String terrainBas = this.tableau_tuiles_plateau[i+1][j].split(",")[0];
						
						if (terrain.equals(terrainBas)) {
							
							nbCasesDimension = nbCasesDimension + 1;
							
							nbCouronnesDimension = Integer.parseInt(this.tableau_tuiles_plateau[i+1][j].split(",")[1]);
							
							casesVisitees.put(i+1, j);
							
						}
					}
					catch(Exception e) {
						
					}
					
					try {
						String terrainGauche = this.tableau_tuiles_plateau[i][j-1].split(",")[0];
						
						if (terrain.equals(terrainGauche)) {
							
							nbCasesDimension = nbCasesDimension + 1;
							
							nbCouronnesDimension = Integer.parseInt(this.tableau_tuiles_plateau[i][j-1].split(",")[1]);
							
							casesVisitees.put(i, j-1);
							
						}
					}
					catch(Exception e) {
						
					}
					
					try {
						String terrainDroit = this.tableau_tuiles_plateau[i][j+1].split(",")[0];
						
						if (terrain.equals(terrainDroit)) {
							
							nbCasesDimension = nbCasesDimension + 1;
							
							nbCouronnesDimension = Integer.parseInt(this.tableau_tuiles_plateau[i][j+1].split(",")[1]);
							
							casesVisitees.put(i, j+1);
							
						}
					}
					catch(Exception e) {
						
					}
				}
				
				score = score + nbCouronnesDimension;
			}
		}
	}
	
	

}
