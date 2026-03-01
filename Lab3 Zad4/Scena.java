/*
Aktivna scena je platno (Canvas) sive boje koja se stvara sa zadatim glavnim prozorom (videti nize). Scena se 
aktivira prilikom stvaranja, ali ne zapocinje odmah svoj posao. Posao scene se sastoji od pomeranja njenih figura za 
njihov jedinicni vektor pomeraja pomnozen odgovarajucom vrednoscu pomeraja u pikselima (podrazumevano iznosi
 3) i njihovog iscrtavanja na svakih 100ms. Moguce je pokrenuti scenu i dohvatiti informaciju da li scena trenutno 
radi, kao i dodati figuru na scenu. Ukoliko se figura koja se dodaje na scenu preklapa sa nekom od postojecih figura 
na sceni ili figura ne staje cela na scenu, operacija dodavanja je bez efekta. Ukoliko se figura sudari sa
nekom od ivica scene ili sa nekom drugom figurom, ona se od nje odbija pod odbojnim uglom identicnim upadnom uglu 
(elasticni sudar). Posao scene moze da se privremeno pauzira, nastavi i trajno zavrsi. Prilikom pauziranja posla 
scene, na sredini scene ispisuje se tekst PAUZA.
 */

package zad2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.util.ArrayList;

public class Scena extends Canvas implements Runnable {

	public static int WIDTH;
	public static int HEIGHT;
	
	private Frame owner;
	private ArrayList<Figura> figure;
	private volatile boolean radi;
	private Thread nit;
	
	public Scena(Frame owner) {
		this.owner = owner;
		(this.nit = new Thread(this)).start();
		this.figure = new ArrayList<>();
		this.setBackground(Color.GRAY);
	}
	
	@Override
	public void run() {
		try {			
			while(!nit.isInterrupted()) {
				synchronized(this) {
					if (!radi) {
						wait();
					}
				}
				Thread.sleep(100);
				crtaj();
			}
		} catch(InterruptedException e) {}
		
	}

	private void crtaj() {
		// Proci kroz sve figure i pomeriti ih za vektor pomeraja
		for(Figura figura1 : figure) {
			figura1.pomeri();
			
			if(figura1.polozaj.getX() + figura1.r > this.getWidth() / 2) figura1.pomeraj.setX(-figura1.pomeraj.getX());
			if(figura1.polozaj.getX() - figura1.r < -this.getWidth() / 2) figura1.pomeraj.setX(-figura1.pomeraj.getX());
			if(figura1.polozaj.getY() + figura1.r > this.getHeight() / 2) figura1.pomeraj.setY(-figura1.pomeraj.getY());
			if(figura1.polozaj.getY() - figura1.r < -this.getHeight() / 2) figura1.pomeraj.setY(-figura1.pomeraj.getY());
		}
		
		Figura[] niz = (Figura[]) figure.toArray(new Figura[figure.size()]);
		for(int i=0; i<niz.length - 1; i++) {
			for(int j = i+1; j<niz.length; j++) {
				Figura figura1 = niz[i];
				Figura figura2 = niz[j];
				
				if (figura1.preklapaSe(figura2)) {
					System.out.println("SUDAR! " + figura1.getId() + " " + figura2.getId());
					
					figura1.setPomeraj(Figura.posleSudara(figura1, figura2));
					figura2.setPomeraj(Figura.posleSudara(figura2, figura1));	                
				}				
			}
		}
	               
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		WIDTH = getWidth() / 2;
		HEIGHT = getHeight() / 2;
		
		g.clearRect(0,  0, 400, 300);
		g.translate(Scena.WIDTH, Scena.HEIGHT);
		
		for(Figura figura : figure) {			
			figura.iscrtaj(g);
		}
		
		synchronized (this) {
			if (!radi) {			
				//System.out.println("ISPISI PAUZA NA EKRANU");
				g.setColor(Color.BLACK);
				g.setFont(new Font("Comic sans ms", Font.BOLD, 25));
				g.drawString("PAUZA", -50, 0);							
			}
		}	
		
	}
	
	public synchronized void pauziraj() {
		radi = false;
		repaint();
	}
	
	public synchronized void nastavi() {
		radi = true;
		notify();
	}
	
	public synchronized void zaustavi() {
		nit.interrupt();
	}
	
	public synchronized boolean pauza() {
		return !radi;
	}
	
	public void dodajFiguru(Figura f) {
		boolean sveOk = true;
		for(Figura figura : figure) { //dodati sta ako se figura cela ne iscrtava
			if (figura.preklapaSe(f)) {
				sveOk = false;
				break;
			}
		}
		if(f.polozaj.getX() + f.r > this.getWidth() / 2 || f.polozaj.getX() - f.r < -(this.getWidth() / 2)) {//ako se figura ne vidi cela preskociti
			sveOk = false;
		}
		if(f.polozaj.getY() + f.r > this.getHeight() / 2 || f.polozaj.getY() - f.r < -(this.getHeight() / 2)) {
			sveOk = false;
		}
		if ( !sveOk ) {
			return;
		}
		
		figure.add(f);		
		repaint();
	}

	
}