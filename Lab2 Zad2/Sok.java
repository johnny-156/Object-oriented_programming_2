/*Sok je pice koje se dodatno stvara sa zadatom vrstom (GAZIRAN, NEGAZIRAN), koja moze da se dohvati. Jednoslovna oznaka gaziranog pica je G, a negaziranog N.*/

package kafic;

public class Sok extends Pice{
	
	public enum Vrsta{GAZIRAN, NEGAZIRAN;}
	private Vrsta vrsta;
	
	public Sok(String naziv, double realnaZapreminaULitrima, int cenaPoLitru, Vrsta vrsta) {
		super(naziv, realnaZapreminaULitrima, cenaPoLitru);
		this.vrsta = vrsta;
	}
	
	public Vrsta getVrsta() {
		return this.vrsta;
	}

	@Override
	public char getOznaka() {
		if(this.vrsta == Vrsta.GAZIRAN) return 'G';
		else return 'N';
	}
}
