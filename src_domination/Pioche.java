import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pioche {
	
	static ArrayList<Tuile> tuiles_piochees = new ArrayList<>();
	static ArrayList<Tuile> liste_tuiles = new ArrayList<>();
	static ArrayList<Tuile> tuiles_tour = new ArrayList<>();
	
	
	
	public static ArrayList<Tuile> getTuiles_piochees() {
		return tuiles_piochees;
	}

	public static void setTuiles_piochees(ArrayList<Tuile> tuiles_piochees) {
		Pioche.tuiles_piochees = tuiles_piochees;
	}

	public static ArrayList<Tuile> getListe_tuiles() {
		return liste_tuiles;
	}

	public static void setListe_tuiles(ArrayList<Tuile> liste_tuiles) {
		Pioche.liste_tuiles = liste_tuiles;
	}

	public static ArrayList<Tuile> getTuiles_tour() {
		return tuiles_tour;
	}

	public static void setTuiles_tour(ArrayList<Tuile> tuiles_tour) {
		Pioche.tuiles_tour = tuiles_tour;
	}

	public void ImportationTuiles() {
		
		
		Path orderPath = Paths.get("dominos.csv");
		List<String> lines = null;
		
		try {
			lines = Files.readAllLines(orderPath);
		}
		catch (IOException e){
			System.out.println("Impossible d'importer le fichier");
		}
		
		
		for (int i = 1; i < lines.size(); i++) {
            String[] split = lines.get(i).split(",");
            
            int nbCouronnes1 = Integer.valueOf(split[0]);
            String type1 = split[1];
            int nbCouronnes2 = Integer.valueOf(split[2]);
            String type2 = split[3];
            int numeroDomino = Integer.valueOf(split[4]);
            
            Tuile t = new Tuile(numeroDomino, nbCouronnes1, nbCouronnes2, type1, type2);
            
            liste_tuiles.add(t);
            
            
		}
		
		//System.out.println(liste_tuiles);
	}
	
	public void PiocherTuilesJeu() {
		
		int nbJoueurs = Jeu.getSizeList();
		Random rand = new Random();
		
		if (nbJoueurs == 2) {
			for (int i = 0; i < 24; i++) {
				int indice = rand.nextInt(liste_tuiles.size());
				tuiles_piochees.add(liste_tuiles.get(indice));
				liste_tuiles.remove(indice);
			}
		} else if (nbJoueurs == 3) {
			for (int i = 0; i < 36; i++) {
				int indice = rand.nextInt(liste_tuiles.size());
				tuiles_piochees.add(liste_tuiles.get(indice));
				liste_tuiles.remove(indice);
			}
		} else if (nbJoueurs == 4) {
			tuiles_piochees = liste_tuiles;
			
			
			
		}
	}
	
	public void PiocherTuilesTour() {
		
		int nbJoueurs = Jeu.getSizeList();
		Random rand = new Random();
		
		if (nbJoueurs == 2 && nbJoueurs == 4) {
			for (int i = 0; i < 4; i++) {
				int indice = rand.nextInt(tuiles_piochees.size());
				Pioche.tuiles_tour.add(tuiles_piochees.get(indice));
				Pioche.tuiles_piochees.remove(indice);
			}
		} else if (nbJoueurs == 3) {
			for (int i = 0; i < 3; i++) {
				int indice = rand.nextInt(tuiles_piochees.size());
				Pioche.tuiles_tour.add(tuiles_piochees.get(indice));
				Pioche.tuiles_piochees.remove(indice);
			}
		}
	}
}
