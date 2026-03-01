/* Kometa je nebesko telo sive boje (GRAY). Kometa se oko centra
iscrtava kao pravilni petougao sa nasumicnom orijentacijom. */

package zad1;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Kometa extends NebeskoTelo {
	
	private static final Color boja = Color.GRAY;
	private double pocetniUgao;
	

	public Kometa(int x, int y, int poluprecnik) {
		super(x, y, boja, poluprecnik);
		
		int pocetniUgao = new Random().nextInt(360); //nasumicni pocetak u stepenima
		double pocetniUgaoRadijani = Math.toRadians(pocetniUgao);
		this.pocetniUgao = pocetniUgaoRadijani;
	}

	@Override
	public void paint(Graphics g) {
		try {
			Color prevColor = g.getColor();
			
			g.setColor(boja);
			g.translate(this.x, this.y);
			double inc = 2 * Math.PI / 5;	
			
			int[] nizX = new int[6];
			int[] nizY = new int[6];
			
			int x = getX(pocetniUgao);
			int y = getY(pocetniUgao);
			int endX, endY;
			
			int br = 0;
			for(double angle = pocetniUgao; angle <= (pocetniUgao + 2 * Math.PI); angle += inc) {
			    nizX[br] = getX(angle);
				nizY[br] = getY(angle);
			
				endX = getX(angle);
				endY = getY(angle);
				g.drawLine(x, y, endX, endY);
				
				x = endX;
				y = endY;
				
				br++;
			}
			
			g.fillPolygon(nizX, nizY, 6);
			
			g.setColor(prevColor);			
		} catch( Exception e ) {}
	}
	
	public int getX(double angle) {
		return (int)(poluprecnik * Math.cos(angle));
	}
	public int getY(double angle) {
		return (int)(poluprecnik * Math.sin(angle));
	}

}