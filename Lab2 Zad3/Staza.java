/* Staza sadrzi proizvoljan broj deonica. Stvara se prazna, sa zadatim nazivom, nakon cega se deonice mogu dodavati na kraj jedna po jedna. Moze da se odredi 
trenutni broj deonica, ukupna realna duzina svih deonica, kao i maksimalni realni nagib od svih sadrzanih deonica. Moguce je odrediti jednoslovnu oznaku staze 
kao najzastupljeniju oznaku sadrzanih deonica, odnosno prve dodate deonice ukoliko je dve ili vise oznaka podjednako zastupljeno (greska GOznaka je ako staza 
ne sadrzi nijednu deonicu). Moze da se odredi krajnja brzina staze u m/s za zadatu pocetnu realnu brzinu, kao i vreme u sekundama potrebno da se predje cela 
staza za zadatu pocetnu realnu brzinu. Tekstualni oblik staze je naziv|broj_deonica|duzina|maks_nagib, nakon cega se u narednom redu u uglastim zagradama 
ispisuju sve deonice odvojene znakom ,.*/

package skijanje;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Staza {
	
	List<Deonica> deonice;
	private String naziv;
	
	public Staza(String naziv) {
		this.naziv = naziv;
		deonice = new ArrayList<>();
	}
	
	public void dodaj(Deonica deonica) {
		deonice.add(deonica);
	}
	
	public int broj() {
		return deonice.size();
	}
	
	public double duzina() {
		double ukupno = 0;
		for(Deonica d: deonice) {
			ukupno += d.duzina();
		}
		return ukupno;
	}
	
	public double nagib() {
		double max = 0;
		for(Deonica d: deonice) {
			if(d.nagib() > max) max = d.nagib(); //+
		}
		return max;
	}
	
	public char oznaka() throws GOznaka {
		if (deonice.size() == 0) throw new GOznaka();
		
		LinkedHashMap<Character, Integer> brojanje = new LinkedHashMap<>();
		
		for(Deonica d: deonice) {
			if (brojanje.get(d.oznaka()) != null) { //oznaka se vec nalazi
				brojanje.put(d.oznaka(), brojanje.get(d.oznaka()) + 1);
			} else {
				brojanje.put(d.oznaka(), 1);
			}			
		}
		
		char oznaka = ' ';	
		int max = 0;
		
		int i = 0;
		for(char c: brojanje.keySet()) {
			if (i > 0) {
				if (brojanje.get(c) > max) { //uzimanje najlevljeg kljuca, odnosno njegove vrednosti
					max = brojanje.get(c);
					oznaka = c;
				}
			}else { // prva deonica
				max = brojanje.get(c);
				oznaka = c;
			}
			i++;
		}
		return oznaka;
	}
	
	public double brzina(double realnaPocetnaBrzina) { 
		double krajnjaBrzina = realnaPocetnaBrzina;
		
		for(Deonica d: deonice) {
			krajnjaBrzina = d.brzina(krajnjaBrzina); //+
		}
		
		return krajnjaBrzina;
	}
	
	public double vreme(double realnaPocetnaBrzina) {
		double krajnjeVreme = 0;
		double krajnjaBrzina = realnaPocetnaBrzina;
		
		for(Deonica d: deonice) {
			krajnjeVreme += d.vreme(krajnjaBrzina);
			krajnjaBrzina = d.brzina(krajnjaBrzina); //+
		}
		
		return krajnjeVreme;
	}
	
	//naziv|broj_deonica|duzina|maks_nagib, nakon cega se u narednom redu u uglastim zagradama ispisuju sve deonice odvojene znakom ,
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(this.naziv).append('|').append(broj()).append('|').append(duzina()).append('|').append(nagib()).append('\n');
		
		sb.append('[');
		for(Deonica d: deonice) {
			if(deonice.indexOf(d) != 0) sb.append(',');
			sb.append(d);
		}
		sb.append(']');
		
		return sb.toString();
	}

}
