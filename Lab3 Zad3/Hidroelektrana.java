/* Hidroelektrana je proizvodjac plave boje (BLUE) i oznake H. Moze da joj se postavi broj
vodenih povrsina koje je okruzuju (pri stvaranju 0). Hidroelektrana generise u svakom
ciklusu po jednu jedinicu energije za svaku vodenu povrs koja je okruzuje. Proizvodnja energije
je uspesna ukoliko hidroelektranu okruzuje barem jedna vodena povrs. Osnovno vreme
proizvodnje je 1500 milisekundi.*/

package zad2;

import java.awt.Color;

public class Hidroelektrana extends Proizvodjac implements Runnable {
	
	private int brojVodenihPovrsina;
	//private VodenaPovrs[] niz;

	public Hidroelektrana(Baterija baterija ) { //VodenaPovrs[] niz) {
		super('H', Color.BLUE, 1500, baterija);
		//this.niz = niz;
		this.brojVodenihPovrsina = 0;
	}
	
	public synchronized void postaviVodenePovrsine(int broj) {	
		//System.out.println("pre: " + brojVodenihPovrsina);
		this.brojVodenihPovrsina = broj;		
		//System.out.println("posle: " + brojVodenihPovrsina);
	}
	
	@Override
	public synchronized boolean proveraGenerisanja() {	
		//System.out.println(brojVodenihPovrsina);
		return brojVodenihPovrsina > 0;
	}

	@Override
	public synchronized int brojJedinicaEnergije() {
		int energija = 1;
		return energija * this.brojVodenihPovrsina;
	}
	
	public synchronized int dohvatiBrojVodenihPovrsina() {
		return brojVodenihPovrsina;
	}
	
}
