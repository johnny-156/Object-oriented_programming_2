/*Pice se stvara sa zadatim nazivom, realnom zapreminom u litrima i celobrojnom cenom po litru. Moguce je dohvatiti naziv, zapreminu kao i ukupnu cenu pica.
Moguce je dohvatiti jednoslovnu oznaku pica. Tekstualni opis pica je oblika naziv (zapremina L):ukupna_cena RSD, gde se zapremina i ukupna cena ispisuju na dve
decimale. Dva pica su jednaka ukoliko imaju isti naziv i zapreminu.*/

package kafic;

public abstract class Pice {
	
	protected String naziv;
	protected double realnaZapreminaULitrima;
	protected int cenaPoLitru;
	
	public Pice(String naziv, double realnaZapreminaULitrima, int cenaPoLitru) {
		this.naziv = naziv;
		this.realnaZapreminaULitrima = getDouble2Decimale(realnaZapreminaULitrima);
		this.cenaPoLitru = cenaPoLitru;
		
	}

	public String getNaziv() {return this.naziv;}
	public double getZapremina() {return realnaZapreminaULitrima;}
	public double getCena() {return this.cenaPoLitru * this.realnaZapreminaULitrima;}
	
	public abstract char getOznaka();
	
	@Override
	public String toString() { //naziv (zapremina L):ukupna_cena RSD, zapremina i ukupna cena ispisuju na dve decimale
		StringBuilder sb = new StringBuilder();
		sb.append(naziv).append(" (").append(String.format("%.2f", getZapremina())).append(" L):").append(String.format("%.2f", getCena())).append(" RSD");
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) { //jednaka ukoliko imaju isti naziv i zapreminu
		if(obj == this) return true;
		if(obj == null) return false;
		if(!(obj instanceof Pice)) return false;
		
		Pice p = (Pice)obj;
		return this.naziv == p.naziv && this.realnaZapreminaULitrima == p.realnaZapreminaULitrima;
	}
	
	private double getDouble2Decimale(double broj) {
		String brojString = String.format("%.2f", broj);
		return Double.parseDouble(brojString);
	}
}
