/* Baterija ima celobrojnu kolicinu trenutne energije i
zadati maksimalni kapacitet. Pri stvaranju baterija je
potpuno puna. Moguce je dodati zadatu celobrojnu
kolicinu energije bateriji, pri cemu se visak energije
odbacuje nakon sto se baterija potpuno napuni. Moguce
je potpuno isprazniti bateriju. Moze se proveriti da li
je baterija puna. */

package zad2;

public class Baterija {
	
	int kolicinaTrenutneEnergije, MaxKapacitet;
	
	public Baterija(int kapacitet) {
		this.kolicinaTrenutneEnergije = kapacitet;
		this.MaxKapacitet = kapacitet;
	}
	
	public synchronized void dodaj(int kolicina) {
		if(kolicina > (this.MaxKapacitet - this.kolicinaTrenutneEnergije)) {
			this.kolicinaTrenutneEnergije = this.MaxKapacitet;
		}
		else this.kolicinaTrenutneEnergije += kolicina;
	}
	
	public synchronized void isprazni() {
		this.kolicinaTrenutneEnergije = 0;
	}
	
	public synchronized boolean puna() {
		return this.kolicinaTrenutneEnergije == this.MaxKapacitet;
	}

}
