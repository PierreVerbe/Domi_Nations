
public class Tuile {
	
	int numero;
	int nbCouronnes1;
	int nbCouronnes2;
	
	String type_tuile1;
	String type_tuile2;
	
	int position_x;
	int position_y;
	
	
	
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
	public int getPosition_x() {
		return position_x;
	}
	public void setPosition_x(int position_x) {
		this.position_x = position_x;
	}
	public int getPosition_y() {
		return position_y;
	}
	public void setPosition_y(int position_y) {
		this.position_y = position_y;
	}
	
	
}
