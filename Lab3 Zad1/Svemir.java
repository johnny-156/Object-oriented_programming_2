/* Svemir je aktivno platno (Canvas) sa crnom (BLACK) pozadinom
koje sadrzi proizvoljan broj nebeskih tela. Moguce je dodati
nebesko telo. Na svakih 100 milisekundi, svemir iscrtava sva
sadrzana nebeska tela, a potom simulira kretanje kroz svemir tako
sto svim nebeskim telima pomera y-koordinatu za 5 piksela.
Moguce je pokrenuti i trajno zavrsiti aktivnost svemira. */

package zad1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Svemir extends Canvas implements Runnable {
	
	static final Color pozadina = Color.BLACK;
	ArrayList<NebeskoTelo> lista = new ArrayList<>();
	Thread thread;
	
	public Svemir() {
		setBackground(pozadina);
		this.thread = new Thread(this);
	}

	public synchronized void dodaj(NebeskoTelo n) {
		lista.add(n);
	}

	@Override
	public void run() {
		
		try {
			while(!Thread.interrupted()) {
				Thread.sleep(100);
				
				synchronized (this) {
					
					Graphics g = getGraphics();						
					g.clearRect(0,  0, 200, 400); //brisanje prethodnog prikaza sa celog Svemira
					
					for(NebeskoTelo n: lista) {
						n.promeniY(5); //pomeranje koordinata
						n.paint(getGraphics()); //iscrtavanje
					}
				}
				
			}
		} catch (InterruptedException e) {}

	}
	
	public synchronized void pokreni() {
		thread.start();
	}
	public synchronized void zaustavi() {
		thread.interrupt();
	}
	
}