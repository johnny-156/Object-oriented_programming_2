/*Predmet se stvara sa zadatim nazivom i tekstualnom sifrom i sadrzi proizvoljan broj osoba koje slusaju ili drze predmet. Moguce je dohvatiti naziv i sifru predmeta. Pri
stvaranju ni jedna osoba ne slusa niti drzi predmet. Moguce je dodati osobu na predmet. Greska (GViseNastavnika) je ukoliko se pokusa dodavanje nastavnika na predmet u
slucaju kada on vec postoji. Tekstualni opis predmeta u prvoj liniji sadrzi naziv i sifru predmeta u formatu: naziv_predmeta (sifra_predmeta), a zatim u pojedinacnim
redovima i sve osobe koje slusaju ili drze predmet, po redosledu u kom su dodavane.*/

package fakultet;

import java.util.ArrayList;
import java.util.List;

public class Predmet {
	
	private String naziv;
	private String sifra;
	private List<Osoba> osobe;
	
	public Predmet(String naziv, String sifra) {
		this.naziv = naziv;
		this.sifra = sifra;
		this.osobe = new ArrayList<>();
	}
	
	public String getNaziv() {return naziv;}
	public String getSifra() {return sifra;}
	
	public void dodajOsobu(Osoba osoba) throws GViseNastavnika {
		if(!(osoba instanceof Nastavnik)) osobe.add(osoba);
		else{
			for(Osoba o:osobe) {
				if(o instanceof Nastavnik) throw new GViseNastavnika();
			}
			osobe.add(osoba);
		}
	}
	
	//u prvoj liniji sadrzi naziv i sifru predmeta u formatu: naziv_predmeta (sifra_predmeta), pa
	//u pojedinacnim redovima i sve osobe koje slusaju ili drze predmet, po redosledu u kom su dodavane
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(naziv).append(" (").append(sifra).append(")\n");
		for(Osoba o: osobe) sb.append(o).append("\n");
		return sb.toString();
	}

}
