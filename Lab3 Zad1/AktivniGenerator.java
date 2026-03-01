/* Aktivni generator nebeskih tela se stvara sa zadatim svemirom. Na
svakih 900 milisekundi, generator pravi kometu cija je x koordinata centra nasumicna vrednost izmedju 0 i 200, y-koordinata
centra 0, a poluprecnik opisane kruznice nasumicna vrednost
izmedju 10 i 30, a potom je dodaje u svemir. Moguce je pokrenuti i
trajno zavrsiti aktivnost generatora. */

package zad1;

import java.util.Random;

public class AktivniGenerator extends Thread {
	
	private Kometa trenutnaKometa; //poslednja ubacena kometa
	protected Svemir svemir;
	
	public AktivniGenerator(Svemir svemir) {
		this.svemir = svemir;
	}
	
	@Override
	public void run() {
		try {
			while(!isInterrupted()) {
				sleep(900);
				int koordinataX = new Random().nextInt(200);
				int koordinataY = 0;
				int poluprecnik = 10 + new Random().nextInt(30);
				
				if ( trenutnaKometa != null ) //deo koda koji omogucava da se komete ne preklapaju
					while(trenutnaKometa.y - koordinataY < trenutnaKometa.poluprecnik + poluprecnik || 
							Math.abs(trenutnaKometa.x - koordinataX) < trenutnaKometa.poluprecnik + poluprecnik) {
						koordinataX = new Random().nextInt(200);
					}
				
				Kometa kometa = new Kometa(koordinataX, koordinataY, poluprecnik);
				svemir.dodaj(kometa);
				
				trenutnaKometa = kometa;
				
			}
		} catch (InterruptedException e) {}
		
	}
	//pokretanje translacije ide tek kada ima generisanih kometa koje bi se translirale, zato sam sve odavde radio
	public synchronized void pokreni() {
		this.start();
		svemir.pokreni();
	}
	public synchronized void zaustavi() {
		this.interrupt();
		svemir.zaustavi();
	}
	

}
