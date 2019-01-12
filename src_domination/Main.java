import edu.princeton.cs.introcs.StdDraw;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static Scanner scan;
	
	public final static int X_MAX= 1280;
	public final static int Y_MAX= 720;
	public final static float WIDTH= 1;
	
	//Obtenir score joueur
	static Boolean[][] isCounted = new Boolean[5][5];
	static String[][] monTableauBis = new String[5][5]; 
	static ArrayList<String> dimension = new ArrayList<>();
	
	public static Jeu MonJeu = new Jeu();
	public static Pioche MaPioche = new Pioche();
	
	public static void main(String[] args) {
		
		scan = new Scanner(System.in);
		
		//feature doublebuffering
		StdDraw.enableDoubleBuffering();
		int num_joueur = 0;

		boolean jeu = true;	
		
		boolean flagChateau = false;
		boolean flagChateauJ1 = false;
		boolean flagChateauJ2 = false;
		boolean flagChateauJ3 = false;
		boolean flagChateauJ4 = false;
		
		boolean flagTuile1 = false;
		boolean flagTuile2 = false;
		boolean flagTuile3 = false;
		boolean flagTuile4 = false;
		
		int CompteurOrdrejoueur = 0;
		boolean flagFinTourJeu = false;

		System.out.println("Bienvenue dans le jeu Domination !");
		
		//Cr�ation de la fen�tre de jeu
		StdDraw.setCanvasSize(X_MAX, Y_MAX);
		StdDraw.setXscale(-WIDTH, X_MAX+WIDTH);
		StdDraw.setYscale(-WIDTH, Y_MAX+WIDTH);
		StdDraw.clear(StdDraw.WHITE);
		
		//Param�trage du la police d'�criture de la librairie
		Font fontPlateau = new Font("Arial", Font.BOLD, 20);
		StdDraw.setFont(fontPlateau);
		num_joueur = initNbJoueur();

		//Param�trage des joueurs
		MonJeu.CreationJoueurs(num_joueur);
		
		//D�finition de l'ordre des joueurs de mani�re random
		MonJeu.initOrdreTour(num_joueur);
		
		//Initialisation de la pioche
		MaPioche.ImportationTuiles();
		MaPioche.PiocherTuilesJeu(MonJeu);
		
		//Boucle principal du jeu
		while(jeu == true){
			//Pr�paration affichage de tous les �l�ments de la fen�tre
			MonJeu.affichageGlobal(num_joueur);
			
			//raccourci fin de jeu - echap
			if(StdDraw.isKeyPressed(27)) {
				System.out.println("Racc Fin du jeu");
				jeu = false;
			}
			//MonJeu.getListe_joueurs().get(3).setPlateauJoueur(j,i,mot);
			//placement des chateaux pour chacun des joueurs
			if(flagChateau == false){
				if (flagChateauJ1 == false){
					flagChateauJ1 = MonJeu.getListe_joueurs().get(0).getPlateau().affichagePieceJoueur1("chateau");
					//pour debug
					//MonJeu.liste_joueurs.get(0).affichagePlateauJoueur();
				}
				
				else if(flagChateauJ2 == false){
					flagChateauJ2 = MonJeu.getListe_joueurs().get(1).getPlateau().affichagePieceJoueur2("chateau");
					//pour debug
					//MonJeu.liste_joueurs.get(1).affichagePlateauJoueur();
					if(num_joueur==2 && flagChateauJ2==true)flagChateau=true;
				}
				
				else if(num_joueur >= 3 && flagChateauJ3 == false){
					flagChateauJ3 = MonJeu.getListe_joueurs().get(2).getPlateau().affichagePieceJoueur3("chateau");
					//pour debug
					//MonJeu.liste_joueurs.get(2).affichagePlateauJoueur();
					if(num_joueur==3 && flagChateauJ3==true)flagChateau=true;
				}
				
				else if(num_joueur == 4 && flagChateauJ4 == false){
					flagChateauJ4 = MonJeu.getListe_joueurs().get(3).getPlateau().affichagePieceJoueur4("chateau");
					//pour debug
					//MonJeu.liste_joueurs.get(3).affichagePlateauJoueur();
					if(num_joueur==4 && flagChateauJ4==true)flagChateau=true;
				}
			}
			
			else if (flagChateau == true && MonJeu.getNb_roi_place() != MonJeu.sommeRoi()){
				System.out.println(MaPioche.getTuiles_piochees().size());
				//affichage des tuiles du plateaux d�j� plac�
				if ((MaPioche.getTuiles_piochees().size() != 0 && flagFinTourJeu==true) || MonJeu.getNb_tour() == 0){
					if (MonJeu.getNb_tour() == 0) MonJeu.setNb_tour(1);
					System.out.println(MaPioche.getTuiles_piochees().size());
					MaPioche.ViderTuilesTour();
					//Reste � ranger dans l'ordre croissant les tuiles
					//penser a vider la liste des tuile du tour 
					MaPioche.PiocherTuilesTour(MonJeu);
					MaPioche.OrdonnerTuilesTour();
					flagFinTourJeu = false;
				}
				
				MaPioche.AffichageTuilesTour();	
				
				//Lorsque un joueur a plac� son roi on passe au second joueur, etc...
				if(num_joueur == 2){
					//List [1, 1, 2, 2]
					if (MonJeu.getOrdre_tour_joueur().get(0) == 1 && MonJeu.getOrdre_tour_joueur().get(1) == 1 && MonJeu.getOrdre_tour_joueur().get(2) == 2 && MonJeu.getOrdre_tour_joueur().get(3) == 2) {
						if (MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour().size()==1 && CompteurOrdrejoueur==0)CompteurOrdrejoueur++;
						else if (MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour().size()==2 && CompteurOrdrejoueur==1)CompteurOrdrejoueur++;
						else if (MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour().size()==1 && CompteurOrdrejoueur==2)CompteurOrdrejoueur++;
					}
					
					//List [2, 2, 1, 1]
					if (MonJeu.getOrdre_tour_joueur().get(0) == 2 && MonJeu.getOrdre_tour_joueur().get(1) == 2 && MonJeu.getOrdre_tour_joueur().get(2) == 1 && MonJeu.getOrdre_tour_joueur().get(3) == 1) {
						if (MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour().size()==1 && CompteurOrdrejoueur==0)CompteurOrdrejoueur++;
						else if (MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour().size()==2 && CompteurOrdrejoueur==1)CompteurOrdrejoueur++;
						else if (MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour().size()==1 && CompteurOrdrejoueur==2)CompteurOrdrejoueur++;
					}
					
					//List [1, 2, 1, 2]
					if (MonJeu.getOrdre_tour_joueur().get(0) == 1 && MonJeu.getOrdre_tour_joueur().get(1) == 2 && MonJeu.getOrdre_tour_joueur().get(2) == 1 && MonJeu.getOrdre_tour_joueur().get(3) == 2) {
						if (MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour().size()==1 && CompteurOrdrejoueur==0)CompteurOrdrejoueur++;
						else if (MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour().size()==1 && CompteurOrdrejoueur==1)CompteurOrdrejoueur++;
						else if (MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour().size()==2 && CompteurOrdrejoueur==2)CompteurOrdrejoueur++;
					}
					
					//List [2, 1, 2, 1]
					if (MonJeu.getOrdre_tour_joueur().get(0) == 2 && MonJeu.getOrdre_tour_joueur().get(1) == 1 && MonJeu.getOrdre_tour_joueur().get(2) == 2 && MonJeu.getOrdre_tour_joueur().get(3) == 1) {
						if (MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour().size()==1 && CompteurOrdrejoueur==0)CompteurOrdrejoueur++;
						else if (MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour().size()==1 && CompteurOrdrejoueur==1)CompteurOrdrejoueur++;
						else if (MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour().size()==2 && CompteurOrdrejoueur==2)CompteurOrdrejoueur++;
					}
					
					//List [1, 2, 2, 1]
					if (MonJeu.getOrdre_tour_joueur().get(0) == 1 && MonJeu.getOrdre_tour_joueur().get(1) == 2 && MonJeu.getOrdre_tour_joueur().get(2) == 2 && MonJeu.getOrdre_tour_joueur().get(3) == 1) {
						if (MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour().size()==1 && CompteurOrdrejoueur==0)CompteurOrdrejoueur++;
						else if (MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour().size()==1 && CompteurOrdrejoueur==1)CompteurOrdrejoueur++;
						else if (MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour().size()==2 && CompteurOrdrejoueur==2)CompteurOrdrejoueur++;
					}
					
					//List [2, 1, 1, 2]
					if (MonJeu.getOrdre_tour_joueur().get(0) == 2 && MonJeu.getOrdre_tour_joueur().get(1) == 1 && MonJeu.getOrdre_tour_joueur().get(2) == 1 && MonJeu.getOrdre_tour_joueur().get(3) == 2) {
						if (MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour().size()==1 && CompteurOrdrejoueur==0)CompteurOrdrejoueur++;
						else if (MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour().size()==1 && CompteurOrdrejoueur==1)CompteurOrdrejoueur++;
						else if (MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour().size()==2 && CompteurOrdrejoueur==2)CompteurOrdrejoueur++;
					}
				}
				
				else if(num_joueur > 2 && MonJeu.getListe_joueurs().get(MonJeu.getOrdre_tour_joueur().get(CompteurOrdrejoueur)-1).getChoix_tuile_tour().size()!=0)CompteurOrdrejoueur++;
				
				System.out.println("CompteurOrdrejoueur : " +  CompteurOrdrejoueur);
				
				//Affectation des rois sur les tuiles
				MonJeu.getListe_joueurs().get(0).placementRoiJ1p(MonJeu, MaPioche, num_joueur, CompteurOrdrejoueur);
				MonJeu.getListe_joueurs().get(0).placementRoiJ1d(MonJeu, MaPioche, num_joueur, CompteurOrdrejoueur);
				MonJeu.getListe_joueurs().get(1).placementRoiJ2p(MonJeu, MaPioche, num_joueur, CompteurOrdrejoueur);
				MonJeu.getListe_joueurs().get(1).placementRoiJ2d(MonJeu, MaPioche, num_joueur, CompteurOrdrejoueur);
				
				try{
					MonJeu.getListe_joueurs().get(2).placementRoiJ3p(MonJeu, MaPioche, num_joueur, CompteurOrdrejoueur);
				}
				catch(java.lang.IndexOutOfBoundsException e){}
				
				try {
					MonJeu.getListe_joueurs().get(3).placementRoiJ4p(MonJeu, MaPioche, num_joueur, CompteurOrdrejoueur);
				}
				catch(java.lang.IndexOutOfBoundsException e){}
				
				for (int i=0; i<MonJeu.getListe_joueurs().size(); i++)MonJeu.getListe_joueurs().get(i).affichageRoi();
			}
			
			else if (flagChateau == true && MonJeu.getNb_roi_place() == MonJeu.sommeRoi() && flagFinTourJeu == false){
				//Vidage liste ordre de jeu
				for(int i=MonJeu.getOrdre_tour_joueur().size()-1; i>-1; i--)MonJeu.getOrdre_tour_joueur().remove(i);
				
				//Prochain ordre de jeu
				while(((num_joueur == 3 || num_joueur == 4) && MonJeu.getOrdre_tour_joueur().size() != num_joueur) || (num_joueur == 2 && MonJeu.getOrdre_tour_joueur().size() != 4)){
					for (int i=0; i<MonJeu.getListe_joueurs().size(); i++){
						for (int j=0; j<MonJeu.getListe_joueurs().get(i).getChoix_tuile_tour().size(); j++){
							if (MonJeu.getOrdre_tour_joueur().size() == 0 && MonJeu.getListe_joueurs().get(i).getChoix_tuile_tour().get(j) == 1) MonJeu.getOrdre_tour_joueur().add(i+1);
							else if (MonJeu.getOrdre_tour_joueur().size() == 1 && MonJeu.getListe_joueurs().get(i).getChoix_tuile_tour().get(j) == 2) MonJeu.getOrdre_tour_joueur().add(i+1);
							else if (MonJeu.getOrdre_tour_joueur().size() == 2 && MonJeu.getListe_joueurs().get(i).getChoix_tuile_tour().get(j) == 3) MonJeu.getOrdre_tour_joueur().add(i+1);
							else if (MonJeu.getOrdre_tour_joueur().size() == 3 && MonJeu.getListe_joueurs().get(i).getChoix_tuile_tour().get(j) == 4) MonJeu.getOrdre_tour_joueur().add(i+1);
						}
					}
				}
				
				
				MaPioche.AffichageTuilesTour();
				MonJeu.AffichageRoiTour();
				
				//prendre et poser la tuile
				/*if(num_joueur == 2)
				{
					if (MonJeu.liste_joueurs.get(0).choix_tuile_tour.size()==1 && CompteurOrdrejoueur==0)CompteurOrdrejoueur++;
					else if (MonJeu.liste_joueurs.get(1).choix_tuile_tour.size()==1 && CompteurOrdrejoueur==1)CompteurOrdrejoueur++;
					else if (MonJeu.liste_joueurs.get(0).choix_tuile_tour.size()==2 && CompteurOrdrejoueur==2)CompteurOrdrejoueur++;
				
				}
				else if(num_joueur > 2 && MonJeu.liste_joueurs.get(CompteurOrdrejoueur).choix_tuile_tour.size()!=0)CompteurOrdrejoueur++;*/
				
				//Placement premiere tuile pioche
				if (StdDraw.mouseX()>=1040-(136/4) && StdDraw.mouseX()<=1040+(136/2)+(136/4) && StdDraw.mouseY()>=540-(136/4) && StdDraw.mouseY()<=540+(136/4) && StdDraw.isMousePressed() && flagTuile1 == false){
					while(StdDraw.isMousePressed());
					while (!StdDraw.isMousePressed()){
						while(flagTuile1 == false){
							//pr�paration affichage de tous les �l�ments de la fen�tre (plateaux + pioche)
							MonJeu.affichageGlobal(num_joueur);
							MaPioche.AffichageTuilesTour();
							MonJeu.AffichageRoiTour();
							
							//affichage information destruction tuile
							Font fontFenetre = new Font("Verdana", Font.BOLD, 20);
							StdDraw.setFont(fontFenetre);
							StdDraw.text(1070, 200, "D�truire la tuile 'a' + click");
							
							MaPioche.AffichageTuile(StdDraw.mouseX(), StdDraw.mouseY(),MaPioche.getTuiles_tour().get(0));
							if(MonJeu.getOrdre_tour_joueur().get(0) == 1)flagTuile1 = MonJeu.getListe_joueurs().get(0).affichageTuileJoueur1(MonJeu, MaPioche.getTuiles_tour().get(0));
							else if (MonJeu.getOrdre_tour_joueur().get(0) == 2)flagTuile1 = MonJeu.getListe_joueurs().get(1).affichageTuileJoueur2(MonJeu, MaPioche.getTuiles_tour().get(0));
							else if (MonJeu.getOrdre_tour_joueur().get(0) == 3)flagTuile1 = MonJeu.getListe_joueurs().get(2).affichageTuileJoueur3(MonJeu, MaPioche.getTuiles_tour().get(0));
							else if (MonJeu.getOrdre_tour_joueur().get(0) == 4)flagTuile1 = MonJeu.getListe_joueurs().get(3).affichageTuileJoueur4(MonJeu, MaPioche.getTuiles_tour().get(0));
							
							//faire une rotation de la tuile en appuyant sur 'echap'
							if(StdDraw.isKeyPressed(32)) {
								while(StdDraw.isKeyPressed(32));
								MaPioche.getTuiles_tour().get(0).rotationTuile();
								System.out.println(MaPioche.getTuiles_tour().get(0).getRotation());	
							}
							
							//detruire la tuile en appuyant sur 'a'
							if(StdDraw.isKeyPressed(65)) {
								while(StdDraw.isKeyPressed(65));
								flagTuile1 = true;
							}
							
							StdDraw.show();
							StdDraw.pause(1);
							StdDraw.clear(StdDraw.GRAY);
						}
					}
				}
				
				//Placement deuxieme tuile pioche
				else if (StdDraw.mouseX()>=1040-(136/4) && StdDraw.mouseX()<=1040+(136/2)+(136/4) && StdDraw.mouseY()>=450-(136/4) && StdDraw.mouseY()<=450+(136/4) && StdDraw.isMousePressed() && flagTuile2 == false){
					while(StdDraw.isMousePressed());
					while (!StdDraw.isMousePressed()){
						while(flagTuile2 == false){
							//pr�paration affichage de tous les �l�ments de la fen�tre (plateaux + pioche)
							MonJeu.affichageGlobal(num_joueur);
							MaPioche.AffichageTuilesTour();
							MonJeu.AffichageRoiTour();
							
							//affichage informations destruction tuile
							Font fontFenetre = new Font("Verdana", Font.BOLD, 20);
							StdDraw.setFont(fontFenetre);
							StdDraw.text(1070, 200, "D�truire la tuile 'a' + click");
							
							MaPioche.AffichageTuile(StdDraw.mouseX(), StdDraw.mouseY(),MaPioche.getTuiles_tour().get(1));
							if(MonJeu.getOrdre_tour_joueur().get(1) == 1)flagTuile2 = MonJeu.getListe_joueurs().get(0).affichageTuileJoueur1(MonJeu, MaPioche.getTuiles_tour().get(1));
							else if (MonJeu.getOrdre_tour_joueur().get(1) == 2)flagTuile2 = MonJeu.getListe_joueurs().get(1).affichageTuileJoueur2(MonJeu, MaPioche.getTuiles_tour().get(1));
							else if (MonJeu.getOrdre_tour_joueur().get(1) == 3)flagTuile2 = MonJeu.getListe_joueurs().get(2).affichageTuileJoueur3(MonJeu, MaPioche.getTuiles_tour().get(1));
							else if (MonJeu.getOrdre_tour_joueur().get(1) == 4)flagTuile2 = MonJeu.getListe_joueurs().get(3).affichageTuileJoueur4(MonJeu, MaPioche.getTuiles_tour().get(1));
							
							//faire une rotation de la tuile en appuyant sur 'echap'
							if(StdDraw.isKeyPressed(32)) {
								while(StdDraw.isKeyPressed(32));
								MaPioche.getTuiles_tour().get(1).rotationTuile();
								System.out.println(MaPioche.getTuiles_tour().get(1).getRotation());	
							}
							
							//detruire la tuile en appuyant sur 'a'
							if(StdDraw.isKeyPressed(65)) {
								while(StdDraw.isKeyPressed(65));
								flagTuile2 = true;
							}
							
							StdDraw.show();
							StdDraw.pause(1);
							StdDraw.clear(StdDraw.GRAY);
						}
					}
				}
				
				//Placement troisieme tuile pioche
				else if (StdDraw.mouseX()>=1040-(136/4) && StdDraw.mouseX()<=1040+(136/2)+(136/4) && StdDraw.mouseY()>=360-(136/4) && StdDraw.mouseY()<=360+(136/4) && StdDraw.isMousePressed() && flagTuile3 == false){
					while(StdDraw.isMousePressed());
					while (!StdDraw.isMousePressed()){
						while(flagTuile3 == false){
							//pr�paration affichage de tous les �l�ments de la fen�tre (plateaux + pioche)
							MonJeu.affichageGlobal(num_joueur);
							MaPioche.AffichageTuilesTour();
							MonJeu.AffichageRoiTour();
							
							//affichage informations destruction tuile
							Font fontFenetre = new Font("Verdana", Font.BOLD, 20);
							StdDraw.setFont(fontFenetre);
							StdDraw.text(1070, 200, "D�truire la tuile 'a' + click");
							
							MaPioche.AffichageTuile(StdDraw.mouseX(), StdDraw.mouseY(),MaPioche.getTuiles_tour().get(2));
							if(MonJeu.getOrdre_tour_joueur().get(2) == 1)flagTuile3 = MonJeu.getListe_joueurs().get(0).affichageTuileJoueur1(MonJeu, MaPioche.getTuiles_tour().get(2));
							else if (MonJeu.getOrdre_tour_joueur().get(2) == 2)flagTuile3 = MonJeu.getListe_joueurs().get(1).affichageTuileJoueur2(MonJeu, MaPioche.getTuiles_tour().get(2));
							else if (MonJeu.getOrdre_tour_joueur().get(2) == 3)flagTuile3 = MonJeu.getListe_joueurs().get(2).affichageTuileJoueur3(MonJeu, MaPioche.getTuiles_tour().get(2));
							else if (MonJeu.getOrdre_tour_joueur().get(2) == 4)flagTuile3 = MonJeu.getListe_joueurs().get(3).affichageTuileJoueur4(MonJeu, MaPioche.getTuiles_tour().get(2));
							
							//faire une rotation de la tuile en appuyant sur 'echap'
							if(StdDraw.isKeyPressed(32)) {
								while(StdDraw.isKeyPressed(32));
								MaPioche.getTuiles_tour().get(2).rotationTuile();
								System.out.println(MaPioche.getTuiles_tour().get(2).getRotation());	
							}
							
							//detruire la tuile en appuyant sur 'a'
							if(StdDraw.isKeyPressed(65)) {
								while(StdDraw.isKeyPressed(65));
								flagTuile3 = true;
							}
							
							StdDraw.show();
							StdDraw.pause(1);
							StdDraw.clear(StdDraw.GRAY);
						}
					}
				}
				
				//Placement quatrieme tuile pioche
				else if (StdDraw.mouseX()>=1040-(136/4) && StdDraw.mouseX()<=1040+(136/2)+(136/4) && StdDraw.mouseY()>=270-(136/4) && StdDraw.mouseY()<=270+(136/4) && StdDraw.isMousePressed() && flagTuile4 == false){
					while(StdDraw.isMousePressed());
					while (!StdDraw.isMousePressed()){
						while(flagTuile4 == false){
							//pr�paration affichage de tous les �l�ments de la fen�tre (plateaux + pioche)
							MonJeu.affichageGlobal(num_joueur);
							MaPioche.AffichageTuilesTour();
							MonJeu.AffichageRoiTour();
							
							//affichage informations destruction tuile
							Font fontFenetre = new Font("Verdana", Font.BOLD, 20);
							StdDraw.setFont(fontFenetre);
							StdDraw.text(1070, 200, "D�truire la tuile 'a' + click");
							
							MaPioche.AffichageTuile(StdDraw.mouseX(), StdDraw.mouseY(),MaPioche.getTuiles_tour().get(3));
							if(MonJeu.getOrdre_tour_joueur().get(3) == 1)flagTuile4 = MonJeu.getListe_joueurs().get(0).affichageTuileJoueur1(MonJeu, MaPioche.getTuiles_tour().get(3));
							else if (MonJeu.getOrdre_tour_joueur().get(3) == 2)flagTuile4 = MonJeu.getListe_joueurs().get(1).affichageTuileJoueur2(MonJeu, MaPioche.getTuiles_tour().get(3));
							else if (MonJeu.getOrdre_tour_joueur().get(3) == 3)flagTuile4 = MonJeu.getListe_joueurs().get(2).affichageTuileJoueur3(MonJeu, MaPioche.getTuiles_tour().get(3));
							else if (MonJeu.getOrdre_tour_joueur().get(3) == 4)flagTuile4 = MonJeu.getListe_joueurs().get(3).affichageTuileJoueur4(MonJeu, MaPioche.getTuiles_tour().get(3));
							
							//faire une rotation de la tuile en appuyant sur 'echap'
							if(StdDraw.isKeyPressed(32)) {
								while(StdDraw.isKeyPressed(32));
								MaPioche.getTuiles_tour().get(3).rotationTuile();
								System.out.println(MaPioche.getTuiles_tour().get(3).getRotation());	
							}
							
							//detruire la tuile en appuyant sur 'a'
							if(StdDraw.isKeyPressed(65)) {
								while(StdDraw.isKeyPressed(65));
								flagTuile4 = true;
							}
							
							StdDraw.show();
							StdDraw.pause(1);
							StdDraw.clear(StdDraw.GRAY);
						}
					}
				}
				
				//Fin tour
				if(num_joueur == 3 && flagTuile1 && flagTuile2 && flagTuile3) flagFinTourJeu=true;
				else if (flagTuile1 && flagTuile2 && flagTuile3 && flagTuile4) flagFinTourJeu=true;
				
				//Fin du jeu, plus de tuile dans la pioche
				if(flagFinTourJeu==true && MaPioche.getTuiles_piochees().size()==0)jeu=false;
			}
			
			else if (flagChateau == true && MonJeu.getNb_roi_place() == MonJeu.sommeRoi() && flagFinTourJeu == true) {
				//fonction de fin de tour (faire liste tour de jeu, tuile choisi joueur � 0, la lsite des tuile du tour )
				//Remise � 0 des variables de jeu
				MonJeu.setNb_roi_place(0);
				CompteurOrdrejoueur = 0;
				
				for(int i=MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour().size()-1; i>-1; i--)MonJeu.getListe_joueurs().get(0).getChoix_tuile_tour().remove(i);
				for(int i=MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour().size()-1; i>-1; i--)MonJeu.getListe_joueurs().get(1).getChoix_tuile_tour().remove(i);
				try {
					for(int i=MonJeu.getListe_joueurs().get(2).getChoix_tuile_tour().size()-1; i>-1; i--)MonJeu.getListe_joueurs().get(2).getChoix_tuile_tour().remove(i);
				}
				catch(java.lang.IndexOutOfBoundsException e) {}
				
				try {
					for(int i=MonJeu.getListe_joueurs().get(3).getChoix_tuile_tour().size()-1; i>-1; i--)MonJeu.getListe_joueurs().get(3).getChoix_tuile_tour().remove(i);
				}
				catch(java.lang.IndexOutOfBoundsException e) {}
				
				//remise a 0 des flag de tuile
				flagTuile1 = false;
				flagTuile2 = false;
				flagTuile3 = false;
				flagTuile4 = false;
				
				//Nouveau tour
				MonJeu.setNb_tour(MonJeu.getNb_tour()+1);	
			}	
			//affichage de tous les �l�ments de la fen�tre
			StdDraw.show();
			StdDraw.pause(1);
			StdDraw.clear(StdDraw.GRAY);
		}
		
		//Affichage r�sultat gagnant
		MonJeu.affichageGlobal(num_joueur);
		MaPioche.AffichageTuilesTour();
		MonJeu.AffichageRoiTour();
		couronneJoueur(MonJeu.getListe_joueurs().get(0), MonJeu.getListe_joueurs().get(0).getPlateauJoueur());
		couronneJoueur(MonJeu.getListe_joueurs().get(1), MonJeu.getListe_joueurs().get(1).getPlateauJoueur());
		
		try {
			couronneJoueur(MonJeu.getListe_joueurs().get(2), MonJeu.getListe_joueurs().get(2).getPlateauJoueur());
		}
		catch(Exception e) {}
		
		try {
			couronneJoueur(MonJeu.getListe_joueurs().get(3), MonJeu.getListe_joueurs().get(3).getPlateauJoueur());
		}
		catch(Exception e) {}
		StdDraw.show();	
		youWin("Patrick");
		System.out.println("Vous avez fini de jouer");
//#############################################################################################################################		
	}
	
	public static int initNbJoueur() {
		int nbJoueur=0;
		
		StdDraw.picture(640, 620, "img/askNbj.png");
		
		StdDraw.picture(256, 350, "img/2joueurs.png");
		StdDraw.picture(640, 350, "img/3joueurs.png");
		StdDraw.picture(1024, 350, "img/4joueurs.png");
		StdDraw.show(); 
		while(nbJoueur==0) {
			if(StdDraw.mouseX()>=256-(256/2) && StdDraw.mouseX()<=256+(256/2) && StdDraw.mouseY()>=350-(256/2) && StdDraw.mouseY()<=350+(256/2) && StdDraw.isMousePressed()) nbJoueur=2;
			else if(StdDraw.mouseX()>=640-(256/2) && StdDraw.mouseX()<=640+(256/2) && StdDraw.mouseY()>=350-(256/2) && StdDraw.mouseY()<=350+(256/2) && StdDraw.isMousePressed()) nbJoueur=3;
			else if(StdDraw.mouseX()>=1024-(256/2) && StdDraw.mouseX()<=1024+(256/2) && StdDraw.mouseY()>=350-(256/2) && StdDraw.mouseY()<=350+(256/2) && StdDraw.isMousePressed()) nbJoueur=4;
		}
		StdDraw.clear(StdDraw.WHITE);
		return nbJoueur;
	}
	
	public static void youWin(String gagnant) {
		while(true) {
			Font fontPlateau = new Font("Arial", Font.BOLD, 50);
			StdDraw.setFont(fontPlateau);
			StdDraw.clear(StdDraw.YELLOW);
			StdDraw.picture(Math.random()*(1200)+35, Math.random()*(650)+35, "img/chateau.png");
			StdDraw.picture(Math.random()*(1200)+35, Math.random()*(650)+35, "img/chateau.png");
			StdDraw.picture(Math.random()*(1200)+35, Math.random()*(650)+35, "img/chateau.png");
			StdDraw.picture(Math.random()*(1200)+35, Math.random()*(650)+35, "img/chateau.png");
			StdDraw.picture(Math.random()*(1200)+35, Math.random()*(650)+35, "img/chateau.png");
			StdDraw.picture(Math.random()*(1200)+35, Math.random()*(650)+35, "img/chateau.png");
			StdDraw.picture(Math.random()*(1200)+35, Math.random()*(650)+35, "img/chateau.png");
			StdDraw.picture(Math.random()*(1200)+35, Math.random()*(650)+35, "img/chateau.png");
			StdDraw.picture(Math.random()*(1200)+35, Math.random()*(650)+35, "img/chateau.png");
			StdDraw.picture(Math.random()*(1200)+35, Math.random()*(650)+35, "img/chateau.png");
			StdDraw.picture(Math.random()*(1200)+35, Math.random()*(650)+35, "img/chateau.png");
			StdDraw.picture(Math.random()*(1200)+35, Math.random()*(650)+35, "img/chateau.png");
			StdDraw.text(600, 400, gagnant.toUpperCase() + " EST LE GRAND GAGNANT !");
			StdDraw.show();
			StdDraw.pause(200);
			
			fontPlateau = new Font("Arial", Font.BOLD, 60);
			StdDraw.clear(StdDraw.PINK);
			StdDraw.setFont(fontPlateau);
			StdDraw.picture(Math.random()*(1200)+35, Math.random()*(650)+35, "img/chateau.png");
			StdDraw.picture(Math.random()*(1200)+35, Math.random()*(650)+35, "img/chateau.png");
			StdDraw.picture(Math.random()*(1200)+35, Math.random()*(650)+35, "img/chateau.png");
			StdDraw.picture(Math.random()*(1200)+35, Math.random()*(650)+35, "img/chateau.png");
			StdDraw.text(600, 400, gagnant.toUpperCase() + " EST LE GRAND GAGNANT !");

			StdDraw.show();
			StdDraw.pause(200);
			
			fontPlateau = new Font("Arial", Font.BOLD, 60);
			StdDraw.clear(StdDraw.YELLOW);
			StdDraw.picture(Math.random()*(1200)+35, Math.random()*(650)+35, "img/chateau.png");
			StdDraw.picture(Math.random()*(1200)+35, Math.random()*(650)+35, "img/chateau.png");
			StdDraw.picture(Math.random()*(1200)+35, Math.random()*(650)+35, "img/chateau.png");
			StdDraw.picture(Math.random()*(1200)+35, Math.random()*(650)+35, "img/chateau.png");
			StdDraw.picture(Math.random()*(1200)+35, Math.random()*(650)+35, "img/chateau.png");
			StdDraw.picture(Math.random()*(1200)+35, Math.random()*(650)+35, "img/chateau.png");
			StdDraw.picture(Math.random()*(1200)+35, Math.random()*(650)+35, "img/chateau.png");
			StdDraw.picture(Math.random()*(1200)+35, Math.random()*(650)+35, "img/chateau.png");
			StdDraw.text(600, 400, gagnant.toUpperCase() + " EST LE GRAND GAGNANT !");
			StdDraw.show();
			StdDraw.pause(200);
		}
	}
	
	public static void CompterCouronnesV2(String[][] tableau, int i, int j) {
		if (!(("x").equals(monTableauBis[i][j]))){
			monTableauBis[i][j] = "x";
		
			try {
				if (tableau[i][j].split("-")[0].equals(tableau[i+1][j].split("-")[0]) && !(("x").equals(monTableauBis[i+1][j]))) {
					if(isCounted[i+1][j].equals(false)) {
						dimension.add(tableau[i+1][j]);
						isCounted[i+1][j] = true;
						CompterCouronnesV2(tableau,i+1,j);
					}
				}
			}
			catch(Exception e) {}
			
			try {
				if (tableau[i][j].split("-")[0].equals(tableau[i][j+1].split("-")[0]) && !(("x").equals(monTableauBis[i][j+1]))) {
					if(isCounted[i][j+1].equals(false)) {
						dimension.add(tableau[i][j+1]);
						isCounted[i][j+1] = true;
						CompterCouronnesV2(tableau,i,j+1);
					}
				}
			}
			catch(Exception e) {}
			
			try {
				if (tableau[i][j].split("-")[0].equals(tableau[i-1][j].split("-")[0]) && !(("x").equals(monTableauBis[i-1][j]))) {
					if(isCounted[i-1][j].equals(false)) {
						dimension.add(tableau[i-1][j]);
						isCounted[i-1][j] = true;
						CompterCouronnesV2(tableau,i-1,j);
					}
				}
			}
			catch(Exception e) {}
			
			try {
				if (tableau[i][j].split("-")[0].equals(tableau[i][j-1].split("-")[0]) && !(("x").equals(monTableauBis[i][j-1]))) {
					if(isCounted[i][j-1].equals(false)) {
						dimension.add(tableau[i][j-1]);
						isCounted[i][j-1] = true;
						CompterCouronnesV2(tableau,i,j-1);
					}
				}
			}
			catch(Exception e) {}
			
			if(isCounted[i][j].equals(false)) {
				dimension.add(tableau[i][j]);
				isCounted[i][j] = true;
			}	
		}
	}
	
	public static void compterCouronne(Joueur joueur, String[][] tableau, int i, int j) {
		int nombreCouronne = 0;
		CompterCouronnesV2(tableau,i,j);
		for(int cpt=0; cpt<dimension.size(); cpt++){
			try {
				nombreCouronne += Integer.parseInt(dimension.get(cpt).split("-")[1]);
			}
			catch(Exception e) {}
			
		}
		joueur.setScoreJoueur(joueur.getScoreJoueur() + (nombreCouronne * dimension.size())); 
		System.out.println(dimension);
		dimension = new ArrayList<>();
	}
	
	public static void couronneJoueur(Joueur joueur, String[][] tableau) {
		isCounted = new Boolean[5][5];
		monTableauBis = new String[5][5];
		dimension = new ArrayList<>();
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				isCounted[i][j] = false;
			}
		}
		for (int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				compterCouronne(joueur, tableau,i,j);
			}
		}
		System.out.println("Score Joueur : " + joueur.getScoreJoueur());
	}
	
	
	/*public static String getVainqueur(){
		int maxRoyaume1 = 0;
		int nbCouronnes1 = 0;
		int maxRoyaume2 = 0;
		int nbCouronnes2 = 0;
		if (MonJeu.getListe_joueurs().size() == 2) {
			if(MonJeu.getListe_joueurs().get(0).getScoreJoueur() > MonJeu.getListe_joueurs().get(1).getScoreJoueur()) {
				return MonJeu.getListe_joueurs().get(0).getPseudo();
			}
			else if (MonJeu.getListe_joueurs().get(0).getScoreJoueur() < MonJeu.getListe_joueurs().get(1).getScoreJoueur()) {
				return MonJeu.getListe_joueurs().get(1).getPseudo();
			}
			else {
				for (int i=0; i<5; i++) {
					for(int j=0; j<5; j++) {
						if (MonJeu.getListe_joueurs().get(0).getPlateauJoueur(i, j) != null) {
							maxRoyaume1 +=1;
						}
						if (MonJeu.getListe_joueurs().get(0).getPlateauJoueur(i, j).split("-")[1].equals("1")) {
							nbCouronnes1 +=1;
						}
						else if (MonJeu.getListe_joueurs().get(0).getPlateauJoueur(i, j).split("-")[1].equals("2")) {
							nbCouronnes1 +=2;
						}
						else if (MonJeu.getListe_joueurs().get(0).getPlateauJoueur(i, j).split("-")[1].equals("3")) {
							nbCouronnes1 +=3;
						}
					}
				}
				
				for (int i=0; i<5; i++) {
					for(int j=0; j<5; j++) {
						if (MonJeu.getListe_joueurs().get(1).getPlateauJoueur(i, j) != null) {
							maxRoyaume2 +=1;
						}
						if (MonJeu.getListe_joueurs().get(1).getPlateauJoueur(i, j).split("-")[1].equals("1")) {
							nbCouronnes2 +=1;
						}
						else if (MonJeu.getListe_joueurs().get(1).getPlateauJoueur(i, j).split("-")[1].equals("2")) {
							nbCouronnes2 +=2;
						}
						else if (MonJeu.getListe_joueurs().get(1).getPlateauJoueur(i, j).split("-")[1].equals("3")) {
							nbCouronnes2 +=3;
						}
						
					}
				}
				
				if(maxRoyaume1 > maxRoyaume2) {
					return MonJeu.getListe_joueurs().get(0).getPseudo();
				}
				else if (maxRoyaume1 < maxRoyaume2) {
					return MonJeu.getListe_joueurs().get(1).getPseudo();
				}
				
				else if (maxRoyaume1 == maxRoyaume2) {
					if(nbCouronnes1 > nbCouronnes2) {
						return MonJeu.getListe_joueurs().get(0).getPseudo();
					}
					else if(nbCouronnes1 < nbCouronnes2) {
						return MonJeu.getListe_joueurs().get(1).getPseudo();
					}
					else {
						return "Egalit� Joueur 1 Joueur 2";
					}
				}
			}
		}
		
		
	}*/
	
	
}






// TO DO
//OK - tuile d�part ? p3 pdf
// OK - ranger les tuiles dans l'ordre croissant
// OK - test tuile pas deja prise
// OK - nouvel ordre joueur prochain tour
// OK - incr�ment� les tours
// OK - fixe bug spam click sur roi
// OK - ajouter les couronne sur le plateau et au deplacement de la tuile
// l 156 le faire une fois
// OK - g�rer changer sens tuile 
// OK - g�rer le superposage (le second tour bug tuile) 
// OK - tu sors des bords du plateau tuile detruite (le second tour bug tuile)
//victoire : pt = -> Royaume + grand
//			 pt = & royaume = -> + couronne
//			 pt = & royaume = & couronne = -> tous victoire
// OK - rotation pi�ce : tuile tour bouge, plateau, tableau plateau, pas de d�bordage
// ajouter un syster de "give up" si on peu pas poser la tuile (detruit la tuile)
//rendre a fenetre plus jolie
//test si on peut poser la tuile a cot�
//avant de mettre le nombre de joueur mettre un petit texte

//fonction de fin de tour (faire liste tour de jeu, tuile choisi joueur � 0, la lsite des tuile du tour )

//rendre clean
//d�placer le fonction du main et en cr�er plus
// mettre des this dan sles class
//camelCase variable, methode, fonction, instances
//enlever les commentaires inutiles