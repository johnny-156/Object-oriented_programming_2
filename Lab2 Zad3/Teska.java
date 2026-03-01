/* Teska deonica ima oznaku T i definise ubrzanje formulom a = g * sin (u), gde su g gravitaciono ubrzanje (9.81 m/s^2), u nagib u stepenima.*/

package skijanje;

public class Teska extends Deonica {
	
	private final double GravitacionoUbrzanje = 9.81;

	public Teska(double realnaDuzinaMetri, double realniNagibStepeni) {
		super(realnaDuzinaMetri, realniNagibStepeni);
	}

	@Override
	public char oznaka() {
		return 'T';
	}

	@Override
	public double ubrzanje() { //a = g * sin(u)
		return this.GravitacionoUbrzanje * Math.sin(Math.toRadians(this.nagib()));
	}

}
