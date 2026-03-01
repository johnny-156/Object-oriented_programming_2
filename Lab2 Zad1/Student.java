/*Student je osoba koja se dodatno stvara sa godinom upisa i ima jedinstveni, automatski generisan broj indeksa. Oznaka studenta je S. Tekstualni opis studenta je oblika
godina/broj_indeksa – ime prezime, gde se broj indeksa ispisuje sa cetiri cifre. */

package fakultet;

public class Student extends Osoba{
	
	private int godinaUpisa;
	private static int poslId = 0;
	private int brojIndeksa;
	
	public Student(String ime, String prezime, int godinaUpisa) {
		super(ime, prezime);
		this.godinaUpisa = godinaUpisa;
		this.brojIndeksa = poslId++;
	}
	
	@Override
	public char getOznaka() {
		return 'S';
	}
	
	@Override
	public String toString() { //godina/broj_indeksa – ime prezime, gde se broj indeksa ispisuje sa cetiri cifre
		StringBuilder sb = new StringBuilder();
		sb.append(godinaUpisa).append("/");
		
		if(brojIndeksa < 10) sb.append("000").append(brojIndeksa);
		else if(brojIndeksa < 100) sb.append("00").append(brojIndeksa);
		else if(brojIndeksa < 1000) sb.append("0").append(brojIndeksa);
		else sb.append(brojIndeksa);
		
		sb.append(" - ").append(ime).append(" ").append(prezime);
		return sb.toString();
	}

}
