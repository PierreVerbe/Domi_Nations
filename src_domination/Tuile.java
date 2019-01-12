public class Tuile {
	
	private int numero;
	private int nbCouronnes1;
	private int nbCouronnes2;
	private String type_tuile1;
	private String type_tuile2;
	
	private String rotation = "horizontal-croissant"; //attribut pierre -- 4 types differents : horizontal-croissant, horizontal-decroissant, vertical-croissant, vertical-decroissant 
	
	public Tuile(){}
	
	public Tuile(int numero, int nbCouronnes1, int nbCouronnes2, String type_tuile1, String type_tuile2) {
		this.numero = numero;
		this.nbCouronnes1 = nbCouronnes1;
		this.nbCouronnes2 = nbCouronnes2;
		this.type_tuile1 = type_tuile1;
		this.type_tuile2 = type_tuile2;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getNbCouronnes1() {
		return nbCouronnes1;
	}
	public void setNbCouronnes1(int nbCouronnes1) {
		this.nbCouronnes1 = nbCouronnes1;
	}
	public int getNbCouronnes2() {
		return nbCouronnes2;
	}
	public void setNbCouronnes2(int nbCouronnes2) {
		this.nbCouronnes2 = nbCouronnes2;
	}
	public String getType_tuile1() {
		return type_tuile1;
	}
	public void setType_tuile1(String type_tuile1) {
		this.type_tuile1 = type_tuile1;
	}
	public String getType_tuile2() {
		return type_tuile2;
	}
	public void setType_tuile2(String type_tuile2) {
		this.type_tuile2 = type_tuile2;
	}
	public String getRotation() {
		return rotation;
	}
	public void setRotation(String rotation) {
		this.rotation = rotation;
	}
	
	public void rotationTuile(){
		if(this.rotation == "horizontal-croissant")this.rotation = "vertical-croissant";
		else if(this.rotation == "vertical-croissant")this.rotation = "horizontal-decroissant";
		else if(this.rotation == "horizontal-decroissant")this.rotation = "vertical-decroissant";
		else if(this.rotation == "vertical-decroissant")this.rotation = "horizontal-croissant";
	}
	
	public void infoTuile(){
		System.out.println("numéro : " + this.numero);
		System.out.println("nbCouronnes1 : " + this.nbCouronnes1);
		System.out.println("nbCouronnes2 : " + this.nbCouronnes2);
		System.out.println("type_tuile1 : " + this.type_tuile1);
		System.out.println("type_tuile2 : " + this.type_tuile2);
	}	
}