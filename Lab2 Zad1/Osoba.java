/*Osoba se stvara sa zadatim imenom i prezimenom. Moguce je dohvatiti jednoslovnu oznaku osobe. Tekstualni opis osobe je oblika ime prezime.*/

package fakultet;

public abstract class Osoba {
	
	protected String ime, prezime;
	
	public Osoba(String ime, String prezime) {
		this.ime = ime;
		this.prezime = prezime;
	}
	
	public abstract char getOznaka();
	
	@Override
	public String toString() { //ime prezime
		StringBuilder sb = new StringBuilder();
		sb.append(ime).append(" ").append(prezime);
		return sb.toString();
	}
}
