/*
Vektor se stvara sa zadatim realnim koordinatama u dvodimenzionom prostoru (napadna tacka je u koordinatnom pocetku),
koje mogu da se postave i dohvate. Moze da mu se odredi jedinicni vektor (ort), tako sto mu se svaka koordinata 
podeli sa vrednoscu magnitude vektora. Moze se stvoriti vektor na pseudoslucajan nacin sa koordinatama u 
opsegu (-1, 1), pri cemu nije dozvoljena vrednost (0, 0).
*/

package zad2;

public class Vektor {
	
	public static double rastojanjeVrhova(Vektor v1, Vektor v2) {
		return Math.sqrt(Math.pow(v1.x-v2.x, 2) + Math.pow(v1.y-v2.y, 2));
	}
	
	public static Vektor razlikaVektora(Vektor v1, Vektor v2) {
		return new Vektor(v1.x - v2.x, v1.y - v2.y);
	}
	
	
	private double x;
	private double y;
	
	public Vektor(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vektor() {
		double x = 0, y = 0;
		boolean pronadjeno = false;
		while(!pronadjeno) {
			x = Math.random() * 2 - 1;
			y = Math.random() * 2 - 1;
			if ( x != 0 && y != 0 && x != -1 && y != -1 && x != 1 && y != 1) { //
				pronadjeno = true;
			}
		}
		this.x = x;
		this.y = y;		
	}
	
	public Vektor ort() {
		return new Vektor(magnituda() / x, magnituda() / y);
	}
	
	public void pomeriX(double x) {
		this.x += x;
	}
	
	public void pomeriY(double y) {
		this.y += y;
	}

	public double getX() {
		return this.x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return this.y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	public double magnituda() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
}