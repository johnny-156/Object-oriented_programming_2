/* Nebesko telo je objekat u svemiru koji se stvara sa dodatno zadatim poluprecnikom opisane
kruznice u pikselima. */

package zad1;

import java.awt.Color;
import java.awt.Graphics;

public abstract class NebeskoTelo extends Objekat {
	
	protected int poluprecnik;

	public NebeskoTelo(int x, int y, Color boja, int poluprecnik) {
		super(x, y, boja);
		this.poluprecnik = poluprecnik;
	}

}
