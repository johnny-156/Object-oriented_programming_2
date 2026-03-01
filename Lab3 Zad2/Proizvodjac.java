/* Proizvodjac je parcela koja aktivno proizvodi energiju u
ciklusima. Stvara se sa dodatno zadatim celobrojnim
osnovnim vremenom i baterijom. Ukupno vreme
proizvodnje se racuna kao zbir osnovnog vremena i slucajno generisanog celog broja u opsegu [0,
300]. Proizvodjac periodicno saceka ukupno vreme, zatim proizvede izvesan broj jedinica
energije kojima puni bateriju (uz moguci neuspeh proizvodnje) i dodatno saceka 300
milisekundi. Ukoliko je proizvodjac uspesno proizveo energiju, njegov natpis se ispisuje
crvenom bojom (RED) u toku navedenog intervala od 300 milisekundi, nakon tekuce proizvodnje,
a pre novog ciklusa proizvodnje. Moguce je zaustaviti proizvodjaca.*/

package zad2;

import java.awt.Color;
import java.util.Random;

public abstract class Proizvodjac extends Parcela implements Runnable{
	
	private int osnovnoVreme;
	Baterija baterija;
	Thread thread;
	
	public Proizvodjac(char oznaka, Color pozadina, int vreme, Baterija baterija) {
		super(oznaka, pozadina);
		this.osnovnoVreme = vreme;
		this.baterija = baterija;
		thread = new Thread(this);
	}
	
	public int ukupnoVreme() {
		int vreme = new Random().nextInt(300);
		return (this.osnovnoVreme + vreme);
	}	

	@Override
	public void run() {
		try {
			while( !Thread.interrupted() ) {
				Thread.sleep(ukupnoVreme());					
			
				if (proveraGenerisanja()) {	
					
					if (!this.baterija.puna()) {
						this.baterija.dodaj(brojJedinicaEnergije());
						//System.out.println("Proizvedeno " + brojJedinicaEnergije());
						this.setForeground(Color.RED);
					}
				}					
				
				Thread.sleep(300);
				setForeground(Color.WHITE);				
			}
		} catch (InterruptedException e) {}
	}
	
	public void pokreni() {
		this.thread.start();
	}
	public void zaustavi() {		
		this.thread.interrupt();		
	}
	
	public abstract boolean proveraGenerisanja();
	public abstract int brojJedinicaEnergije();	
	
/*
	 if (!this.baterija.puna()) {
						this.baterija.dodaj(brojJedinicaEnergije());
						this.baterija.energija.setText("" + this.baterija.kolicinaTrenutneEnergije);
						this.setForeground(Color.RED);
					} else if (this.baterija.puna()){
						synchronized (this) {
							notify();
						}
					}
*/

}