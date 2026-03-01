/* Deonica se stvara sa zadatom realnom duzinom izrazenom u metrima i realnim nagibom izrazenim u stepenima, koji mogu da se dohvate. Mogu da se dohvate 
jednoslovna oznaka deonice i njeno realno ubrzanje. Moguce je odrediti brzinu u m/s na kraju deonice za zadatu realnu pocetnu brzinu koristeci formulu 
Vk = sqrt(2 * a * s + Vp^2), gde su Vk krajnja brzina, Vp pocetna brzina u m/s, a ubrzanje u m/s^2, s duzina u metrima, kao i vreme potrebno za prelazenje 
deonice za zadatu realnu pocetnu brzinu koristeci formulu t = (Vk - Vp) / a. Moguce je sastaviti tekstualni opis deonice u obliku oznaka(duzina,nagib).*/

package skijanje;

public abstract class Deonica {
	
	private double RealnaDuzinaMetri, RealniNagibStepeni;
	
	public Deonica(double realnaDuzinaMetri, double realniNagibStepeni) {
		this.RealnaDuzinaMetri = realnaDuzinaMetri;
		this.RealniNagibStepeni = realniNagibStepeni;
	}

	public double duzina() {return RealnaDuzinaMetri;}
	public double nagib() {return RealniNagibStepeni;}
	
	public abstract char oznaka();
	public abstract double ubrzanje();
	
	public double brzina(double realnaPocetnaBrzina){ //Vk = sqrt(2 * a * s + Vp^2)
		return Math.sqrt(2 * ubrzanje() * duzina() + Math.pow(realnaPocetnaBrzina, 2));
	}
	
	public double vreme(double realnaPocetnaBrzina) { //t = (Vk - Vp) / a
		return (this.brzina(realnaPocetnaBrzina) - realnaPocetnaBrzina) / ubrzanje();
	}
	
	@Override
	public String toString() { //oznaka(duzina,nagib)
		StringBuilder sb = new StringBuilder();
		sb.append(this.oznaka()).append("(").append(RealnaDuzinaMetri).append(",").append(RealniNagibStepeni).append(")");
		return sb.toString();
	}
}
