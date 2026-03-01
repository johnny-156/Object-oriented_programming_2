/*Objekat u svemiru se stvara sa zadatim celobrojnim koordinatama centra u dvodimenzionalnom
prostoru koje mogu da se dohvate i bojom (Color). Moguce je pojedinacno promeniti svaku od
koordinata centra za zadati pomeraj. Moguce je iscrtati objekat u svemiru nad zadatim
grafickim kontekstom (Graphics). */

package zad1;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Objekat {
	
	protected int x, y;
	protected Color boja;
	
	public Objekat(int x, int y, Color boja) {
		this.x = x;
		this.y = y;
		this.boja = boja;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void promeniX(int pomeraj) { //moze biti pozitivan i negativan u argumentu
		this.x += pomeraj;
	}
	public void promeniY(int pomeraj) {
		this.y += pomeraj;
	}
	
	public abstract void paint(Graphics g);	
	
}
