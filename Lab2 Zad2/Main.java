package main;


import kafic.*;

public class Main {

	public static void main(String[] args) {
		Pice[] pica = new Pice[] {
				new Sok("Ananas", 0.2f, 1100, Sok.Vrsta.NEGAZIRAN),
				new Sok("Coca Cola", 0.25f, 1200, Sok.Vrsta.GAZIRAN)
		};
		
		for (Pice pice: pica) {
			System.out.println(pice.getNaziv() + " " + pice.getZapremina() + " " + pice.getCena() + " " + pice.getOznaka());
			System.out.println(pice);
		}
		
		Sok sok = new Sok("Fanta", 0.25f, 1200, Sok.Vrsta.GAZIRAN);
		System.out.println(sok.getVrsta());
		
		KartaPica kartaPica = new KartaPica();
		try {
			for (Pice pice: pica) {
				kartaPica.dodajPice(pice);
			}
			kartaPica.dodajPice(sok);
			kartaPica.dodajPice(sok);
		} catch (GPostoji e) {}
		
		System.out.println(kartaPica.getBrojPica());
		System.out.println(kartaPica);
	}

}

/*
Ananas 0.2 220.0 N
Ananas (0.20 L): 220.00 RSD
Coca Cola 0.25 300.0 G
Coca Cola (0.25 L): 300.00 RSD
GAZIRAN
3
GAZIRANI SOKOVI
Coca Cola (0.25 L): 300.00 RSD
Fanta (0.25 L): 300.00 RSD
NEGAZIRANI SOKOVI
Ananas (0.20 L): 220.00 RSD
 */
