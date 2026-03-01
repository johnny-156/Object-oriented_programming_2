/*Karta pica sadrzi proizvoljan broj pica. Stvara se prazna nakon cega je moguce pojedinacno dodavati pica. Greska (GPostoji) je ukoliko se pokusa dodavanje dva 
jednaka pica. Moguce je dohvatiti ukupan broj pica. Tekstualni opis karte pica se formira tako sto se najpre ispise tekst GAZIRANI SOKOVI, nakon cega slede opisi
svih pica oznake G u zasebnim redovima, a zatim NEGAZIRANI SOKOVI, nakon cega slede opisi svih pica oznake N u zasebnim redovima. Pica se u okviru iste vrste 
ispisuju po redosledu dodavanja.*/

package kafic;

import java.util.ArrayList;
import java.util.List;

public class KartaPica {
	
	List<Pice> pica = new ArrayList<>();
	
	public void dodajPice(Pice pice) throws GPostoji {
		for(Pice p:pica) { 
			if(p.equals(pice)) throw new GPostoji();
		}
		pica.add(pice);
	}
	
	public int getBrojPica() {
		return pica.size();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("GAZIRANI SOKOVI").append("\n");
		for(Pice p: pica) {
			if(p.getOznaka() == 'G') sb.append(p).append("\n");
		}
		
		sb.append("NEGAZIRANI SOKOVI").append("\n");
		for(Pice p: pica) {
			if(p.getOznaka() == 'N') sb.append(p).append("\n");
		}
		
		return sb.toString();
	}

}