/*
Disk je figura koja se iscrtava plavom bojom u vidu osmougla sa centrom u vektoru polozaja. 
 */

package zad2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Disk extends Figura  {
	
	private Color boja = Color.BLUE;
	
	public Disk(Vektor polozaj, Vektor pomeraj, double r) {
		super(polozaj, pomeraj, r);
	}
	
	public Disk(Vektor polozaj, Vektor pomeraj) {
		super(polozaj, pomeraj);
	}	

	@Override
	public Color dohvatiBoju() {
		return this.boja;
	}
	
	public int getXcrtanje(double angle) {
		return (int)(this.r * Math.cos(angle));
	}
	public int getYcrtanje(double angle) {
		return (int)(this.r * Math.sin(angle));
	}
	@Override
	public void iscrtaj(Graphics g) {		
		Color prevColor = g.getColor();	 
		
		g.setColor(boja);	
		
		g.translate((int)polozaj.getX(), -(int)polozaj.getY()); 
	
		//System.out.println("isctaj: " + polozaj.getX() + " " + polozaj.getY());
		double inc = 2 * Math.PI / 8;	
		
		int[] nizX = new int[9];
		int[] nizY = new int[9];
		
		int x = getXcrtanje(0);
		int y = getYcrtanje(0);
		int endX, endY;
		
		int br = 0;
		for(double angle = 0; angle <= 2 * Math.PI; angle += inc) {
		    nizX[br] = getXcrtanje(angle);
			nizY[br] = getYcrtanje(angle);
		
			endX = getXcrtanje(angle);
			endY = getYcrtanje(angle);
			g.drawLine(x, y, endX, endY);
			
			x = endX;
			y = endY;
			
			br++;
		}
		
		g.fillPolygon(nizX, nizY, 9);
		
		g.translate(-(int)polozaj.getX(), (int)polozaj.getY()); 
		
		g.setColor(prevColor);
	}

}
