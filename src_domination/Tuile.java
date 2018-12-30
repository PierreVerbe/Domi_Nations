
public class Tuile {
	
	int numero;
	int nbCouronnes1;
	int nbCouronnes2;
	
	String type_tuile1;
	String type_tuile2;
	
	int position_x1;
	int position_y1;
	int position_x2;
	int position_y2;
	
	
	
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
	public int getPosition_x1() {
		return position_x1;
	}
	public void setPosition_x1(int position_x1) {
		this.position_x1 = position_x1;
	}
	public int getPosition_y1() {
		return position_y1;
	}
	public void setPosition_y1(int position_y1) {
		this.position_y1 = position_y1;
	}
	public int getPosition_x2() {
		return position_x2;
	}
	public void setPosition_x2(int position_x2) {
		this.position_x2 = position_x2;
	}
	public int getPosition_y2() {
		return position_y2;
	}
	public void setPosition_y2(int position_y2) {
		this.position_y2 = position_y2;
	}
	
	public void infoTuile()
	{
		System.out.println("numéro : " + this.numero);
		System.out.println("nbCouronnes1 : " + this.nbCouronnes1);
		System.out.println("nbCouronnes2 : " + this.nbCouronnes2);
		System.out.println("type_tuile1 : " + this.type_tuile1);
		System.out.println("type_tuile2 : " + this.type_tuile2);
		System.out.println("position_x1 : " + this.position_x1);
		System.out.println("position_y1 : " + this.position_y1);
		System.out.println("position_x2 : " + this.position_x2);
		System.out.println("position_y2 : " + this.position_y2);
	}	
}