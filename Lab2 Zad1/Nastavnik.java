/*Nastavnik je osoba koja se dodatno stvara sa nastavnickim zvanjem (doc, prof). Oznaka nastavnika je N. Tekstualni opis nastavnika je oblika zvanje. dr ime prezime.*/

package fakultet;

public class Nastavnik extends Osoba{
	
	public enum Zvanje{doc, prof;}
	private Zvanje zvanje;

	public Nastavnik(String ime, String prezime, Zvanje zvanje) {
		super(ime, prezime);
		this.zvanje = zvanje;
	}

	@Override
	public char getOznaka() {
		return 'N';
	}
	
	@Override
	public String toString() { //zvanje. dr ime prezime
		StringBuilder sb = new StringBuilder();
		sb.append(zvanje).append(". dr ").append(ime).append(" ").append(prezime);
		return sb.toString();
	}

}
