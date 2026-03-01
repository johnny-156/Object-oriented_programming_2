/*
Figura se stvara sa zadatim vektorom polozaja, vektorom pomeraja i poluprecnikom opisane kruznice (cija je 
podrazumevana vrednost 20), koji mogu da se dohvate. Moze da  se odredi da li se zadati vektor polozaja nalazi u 
okviru opisane kruznice tekuce figure, kao i da li se opisana kruznica tekuce figure preklapa sa opisanom kruznicom 
zadate figure. Moguce je dohvatiti boju kojom se figura iscrtava, kao i iscrtati figuru.
*/

package zad2;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Figura {
	
	private static int ID = 0;
	private int id;
	protected int faktor = 25;
	protected Vektor polozaj;
	protected Vektor pomeraj;
	protected double r;
	
	public Figura(Vektor polozaj, Vektor pomeraj) {
		this(polozaj, pomeraj, 20);
	}
	
	public Figura(Vektor polozaj, Vektor pomeraj, double r) {
		this.polozaj = polozaj;
		this.pomeraj = pomeraj; 
		this.r = r;
		this.id = ++ID;
	}
	
	public boolean nalaziSe(Vektor v) {
		if ( Vektor.rastojanjeVrhova(polozaj, v) < this.r ) {
			return true;
		}
		return false;
	}
	
	public boolean preklapaSe(Figura f) {
		if ( Vektor.rastojanjeVrhova(this.polozaj, f.polozaj) < this.r + f.r ) {
			return true;
		}
		return false;
	}

	
	public void setPolozaj(Vektor polozaj) {
		this.polozaj = polozaj;
	}

	public void setPomeraj(Vektor pomeraj) {
		this.pomeraj = pomeraj;
	}
	
	public Vektor getPolozaj() {
		return this.polozaj;
	}
	public Vektor getPomeraj() {
		return this.pomeraj;
	}
	public double getR() {
		return this.r;
	}	
	public int getId() {
		return id;
	}

	public void pomeri() {
		polozaj.pomeriX(pomeraj.getX() * faktor);
		polozaj.pomeriY(pomeraj.getY() * faktor);
	}
	
	public abstract Color dohvatiBoju();
	public abstract void iscrtaj(Graphics g);

	public static Vektor posleSudara(Figura figura1, Figura figura2)  {
		// Calculate the relative position and velocity vectors
        Vektor relativePos = Vektor.razlikaVektora(figura2.getPolozaj(), figura1.getPolozaj());
        Vektor relativeVel = Vektor.razlikaVektora(figura1.getPomeraj(), figura2.getPomeraj());

         // Calculate the dot product of the relative velocity and displacement 
         double dotProduct = relativeVel.getX() * relativePos.getX() + relativeVel.getY() * relativePos.getY(); 

         // Calculate the square of the displacement magnitude 
         double displacementMagnitudeSquared = Math.pow(relativePos.getX(), 2) + Math.pow(relativePos.getY(), 2); 

         // Calculate the final velocity using the conservation of momentum 
         double finalVelocityX = relativeVel.getX() - (dotProduct * relativePos.getX()) / displacementMagnitudeSquared; 
         double finalVelocityY = relativeVel.getY() - (dotProduct * relativePos.getY()) / displacementMagnitudeSquared;
         
         finalVelocityX = dohvatiIzaZareza(finalVelocityX);
         finalVelocityY = dohvatiIzaZareza(finalVelocityY);
         
         System.out.println(finalVelocityX + " " + finalVelocityY);
		
		return new Vektor(finalVelocityX, finalVelocityY);
	}
	private static double dohvatiIzaZareza(double broj) {
		if (broj > -1 && broj < 1) {
			return broj;
		}
		int ceoDeo = (int) Math.floor(broj);
		broj = broj - ceoDeo;
		return broj;
	}
	
}