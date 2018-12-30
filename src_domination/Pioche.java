import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.princeton.cs.introcs.StdDraw;

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
		
		if (nbJoueurs == 2 || nbJoueurs == 4) {
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
	
	public void ViderTuilesTour() 
	{
		int nb_tuile_tour = this.tuiles_tour.size();
		
		for (int i=nb_tuile_tour-1; i>-1; i--)this.tuiles_tour.remove(i);		
	}
	
	public void AffichageTuilesTour() 
	{	
		for(int i=0; i<this.tuiles_tour.size(); i++)
		{
			switch (i) {
			
			case 0:
				this.AffichageTuile(1040, 540, this.tuiles_tour.get(i).getType_tuile1(), this.tuiles_tour.get(i).getNbCouronnes1(), this.tuiles_tour.get(i).getType_tuile2(), this.tuiles_tour.get(i).getNbCouronnes2());
				break;
				
			case 1:
				this.AffichageTuile(1040, 450, this.tuiles_tour.get(i).getType_tuile1(), this.tuiles_tour.get(i).getNbCouronnes1(), this.tuiles_tour.get(i).getType_tuile2(), this.tuiles_tour.get(i).getNbCouronnes2());
				break;
				
			case 2:
				this.AffichageTuile(1040, 360, this.tuiles_tour.get(i).getType_tuile1(), this.tuiles_tour.get(i).getNbCouronnes1(), this.tuiles_tour.get(i).getType_tuile2(), this.tuiles_tour.get(i).getNbCouronnes2());
				break;
				
			case 3:
				this.AffichageTuile(1040, 270, this.tuiles_tour.get(i).getType_tuile1(), this.tuiles_tour.get(i).getNbCouronnes1(), this.tuiles_tour.get(i).getType_tuile2(), this.tuiles_tour.get(i).getNbCouronnes2());
				break;
			}
		}	
	}
	
	public void AffichageTuile(double x, double y, String terrain1, int couronne1, String terrain2, int couronne2)
	{	
		//première partie de la tuile (partie gauche)
		//terrains
		if (terrain1.equals("Champs"))StdDraw.picture(x, y, "img/champs.png");
		else if (terrain1.equals("Mer"))StdDraw.picture(x, y, "img/mer.png");
		else if (terrain1.equals("Foret"))StdDraw.picture(x, y, "img/foret.png");
		else if (terrain1.equals("Prairie"))StdDraw.picture(x, y, "img/prairie.png");
		else if (terrain1.equals("Mine"))StdDraw.picture(x, y, "img/mine.png");
		else if (terrain1.equals("Montagne"))StdDraw.picture(x, y, "img/montagne.png");
		//couronnes
		if (couronne1 == 1)StdDraw.picture(x, y, "img/1couronne.png");
		else if (couronne1 == 2)StdDraw.picture(x, y, "img/2couronnes.png");
		else if (couronne1 == 3)StdDraw.picture(x, y, "img/3couronnes.png");
		
		//deuxième partie de la tuile (partie droite)
		//terrains
		if (terrain2.equals("Champs"))StdDraw.picture(x+68, y, "img/champs.png");
		else if (terrain2.equals("Mer"))StdDraw.picture(x+68, y, "img/mer.png");
		else if (terrain2.equals("Foret"))StdDraw.picture(x+68, y, "img/foret.png");
		else if (terrain2.equals("Prairie"))StdDraw.picture(x+68, y, "img/prairie.png");
		else if (terrain2.equals("Mine"))StdDraw.picture(x+68, y, "img/mine.png");
		else if (terrain2.equals("Montagne"))StdDraw.picture(x+68, y, "img/montagne.png");	
		//couronnes
		if (couronne2 == 1)StdDraw.picture(x+68, y, "img/1couronne.png");
		else if (couronne2 == 2)StdDraw.picture(x+68, y, "img/2couronnes.png");
		else if (couronne2== 3)StdDraw.picture(x+68, y, "img/3couronnes.png");
	}
}
