package main;

import fakultet.*;

public class Main {
	
	public static void main(String[] args) {
		Osoba[] osobe = new Osoba[] {
				new Nastavnik("Lazar", "Lazarevic", Nastavnik.Zvanje.prof),
				new Student("Petar", "Petkovic", 2016),
				new Student("Jovan", "Jovanovic", 2020),
				new Student("Maja", "Majic", 2022),
				new Nastavnik("Marko", "Markovic", Nastavnik.Zvanje.doc)
		};
		
		for (Osoba osoba: osobe) {
			System.out.println(osoba.getOznaka() + " : " + osoba);
		}
		
		Predmet predmet = new Predmet("Programiranje 1", "13S111P1");
		try {
			for (Osoba osoba: osobe) {
				predmet.dodajOsobu(osoba);
			}
		} catch (GViseNastavnika e) {}
		
		System.out.println(predmet.getNaziv() + " " + predmet.getSifra());
		System.out.println(predmet);
	}
}

/*
N : prof. dr Lazar Lazarevic
S : 2016/0000 - Petar Petkovic
S : 2020/0001 - Jovan Jovanovic
S : 2022/0002 - Maja Majic
N : doc. dr Marko Markovic
Programiranje 1 13S111P1
Programiranje 1 (13S111P1)
prof. dr Lazar Lazarevic
2016/0000 - Petar Petkovic
2020/0001 - Jovan Jovanovic
2022/0002 - Maja Majic
*/