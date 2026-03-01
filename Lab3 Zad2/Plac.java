/* Plac je resetka parcela. Stvara se sa zadatim brojem redova i kolona resetke. Prilikom
stvaranja svako polje resetke se nasumicno inicijalizuje travnatom (verovatnoca 70%) ili
vodenom povrsi (verovatnoca 30%). Moze da se izabere jedna parcela. Izborom nove parcele,
prethodno izabranoj parceli se ponistava izbor. Izabranoj parceli se velicina fonta uvecava
na 20. Moguce je dodati proizvodjaca na izabranu parcelu, nakon cega se hidroelektranama
azurira broj vodenih povrsina koje ih okruzuju. Ukoliko ni jedna parcela nije izabrana,
dodavanje proizvodjaca nije uspesno. Moguce je zaustaviti rad svih proizvodjaca.*/

package zad2;

import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.Random;

//import zad2.Plac.PlacObjekti;

public class Plac extends Panel {
	
	class PlacProizvodjaci { //koordinate placa na kom je, vezujemo za proizvodjaca
		int x;
		int y;
		Proizvodjac proizvodjac;
		public PlacProizvodjaci( int x, int y, Proizvodjac p ) {
			this.proizvodjac = p;
			this.x = x;
			this.y = y;
		}
	}
	
	int redovi, kolone;
	ArrayList<PlacProizvodjaci> proizvodjaci = new ArrayList<>();
	Parcela selektovanaParcela; //ranije selektovana parcela
			
	public Plac(int redovi, int kolone) {
		this.redovi = redovi;
		this.kolone = kolone;
		setLayout(new GridLayout(redovi, kolone, 3, 3));
		this.selektovanaParcela = null;
	
		for(int i = 0; i < (this.redovi * this.kolone); i++) {
			int broj = new Random().nextInt(10);
			Parcela parcela = (broj < 8 ? new TravnataPovrs() : new VodenaPovrs());
			parcela.setIndex(i);
			add(parcela);		
		}
		
	}
	
	public void dodajProizvodjaca(Proizvodjac proizvodjac) {
		if ( selektovanaParcela == null ) return; //ako nema nista selektovano, necemo moci da dodamo proizvodjaca

		int index = selektovanaParcela.getIndex();
		int red = index / kolone;
		int kolona = index % kolone;
		
		for(PlacProizvodjaci p: proizvodjaci) {
			if(p.x == kolona && p.y == red) { //ako se doda proizvodjac preko proizvodjaca
				p.proizvodjac.zaustavi();
				proizvodjaci.remove(p);
				break;
			}
		}
		
		PlacProizvodjaci pp = new PlacProizvodjaci(kolona, red, proizvodjac); //stvaranje i dodavanje
		proizvodjaci.add(pp);
		
		((Hidroelektrana)proizvodjac).postaviVodenePovrsine(getSuseda(red, kolona)); //postavljanje broja vodenih povrsina i pokretanje niti
		proizvodjac.pokreni();
		
		//isctavanje
		this.remove(index); 
	    this.add(proizvodjac, index);	    
	    this.selektovanaParcela = null;
	    this.revalidate();
	   
	   for(PlacProizvodjaci p: proizvodjaci) { // azuriranje broja suseda svakom proizvodjacu
	    	int brojSuseda = getSuseda(p.y, p.x);
	    	//System.out.println("red: " + p.y + ", kolona: "+ p.x + ", brojSuseda: " + brojSuseda );
	    	((Hidroelektrana)p.proizvodjac).postaviVodenePovrsine(brojSuseda);
		}
	   
	}
	
	public void zaustavi() {
		for(PlacProizvodjaci p: proizvodjaci) {
			p.proizvodjac.zaustavi();
		}
	}
	
	public void obavestiPlac(Parcela p) {
		if (this.selektovanaParcela != null) { //ako je ranije neka bila selektovana
			this.selektovanaParcela.deselektuj();
		}
		this.selektovanaParcela = p;
	}
	
	private int getSuseda(int red, int kolona) {
		
		int br = 0;
		for( int i = red - 1; i <= red + 1; i++) {
			if ( i == -1 || i == redovi) continue;
			for (int j = kolona - 1; j <= kolona + 1; j++ ) {
				if ( j == -1 || j == kolone || (i == red && j == kolona)) continue;
				
				Parcela p = (Parcela) getComponent(i*redovi + j);
				if (p instanceof VodenaPovrs) br++;
			}
		}
		return br;
	}
	 
}

/*
public class Plac extends Panel {
	
	class PlacObjekti { //koordinate placa na kom je, vezujemo za proizvodjaca
		int x;
		int y;
		Parcela objekat;
		boolean hidro;
		public PlacObjekti( int x, int y, Parcela p, boolean hidro) {
			this.objekat = p;
			this.x = x;
			this.y = y;
			this.hidro = hidro;
		}
	}
	
	int redovi, kolone;
	ArrayList<PlacObjekti> objekti = new ArrayList<>();
	Parcela selektovanaParcela; //ranije selektovana parcela
	boolean pocetak = false;
			
	public Plac(int redovi, int kolone) {
		this.redovi = redovi;
		this.kolone = kolone;
		setLayout(new GridLayout(redovi, kolone, 3, 3));
		this.selektovanaParcela = null;
	
		for(int i = 0; i < (this.redovi * this.kolone); i++) {
			int broj = new Random().nextInt(10);
			Parcela parcela = (broj < 8 ? new TravnataPovrs() : new VodenaPovrs());
			parcela.setIndex(i);
			add(parcela);		
		}
		
	}
	
	public void dodajObjekat(Parcela objekat) {
		if ( selektovanaParcela == null || this.pocetak == false ) return; //ako nema nista selektovano, necemo moci da dodamo proizvodjaca

		int index = selektovanaParcela.getIndex();
		int red = index / kolone;
		int kolona = index % kolone;
		
		for(PlacObjekti p: objekti) {
			if(p.x == kolona && p.y == red) { //ako se doda proizvodjac preko proizvodjaca
				if(p.hidro) ((Hidroelektrana)p.objekat).zaustavi();
				else if(!p.hidro) ((Mlin)p.objekat).zaustavi();
				objekti.remove(p);
				break;
			}
		}
		
		EnergetskiSistem es = (EnergetskiSistem)getParent();
		boolean h = es.hidro;
		PlacObjekti pp = new PlacObjekti(kolona, red, objekat, h); //stvaranje i dodavanje
		objekti.add(pp);
		
		//postavljanje broja vodenih povrsina i pokretanje niti
		if(h) {
			((Hidroelektrana)objekat).postaviVodenePovrsine(getSuseda(red, kolona));
			((Hidroelektrana)objekat).pokreni();
		}
		else if(!h) {
			((Mlin)objekat).postaviTravnatePovrsine(getSuseda(red, kolona));
			((Mlin)objekat).pokreni();
		}
		
		//isctavanje
		this.remove(index); 
	    this.add(objekat, index);	    
	    this.selektovanaParcela = null;
	    this.revalidate();
	   
	   for(PlacObjekti p: objekti) { // azuriranje broja suseda svakom proizvodjacu
	    	int brojSuseda = getSuseda(p.y, p.x);
	    	if(p.hidro) ((Hidroelektrana)p.objekat).postaviVodenePovrsine(brojSuseda);
			else if(!p.hidro) ((Mlin)p.objekat).postaviTravnatePovrsine(brojSuseda);
		}
	   
	}
	
	public void zaustavi() {
		for(PlacObjekti p: objekti) {
			if(p.hidro) ((Hidroelektrana)p.objekat).zaustavi();
			else if(!p.hidro) ((Mlin)p.objekat).zaustavi();
		}
	}
	
	public void obavestiPlac(Parcela p) {
		if (this.selektovanaParcela != null) { //ako je ranije neka bila selektovana
			this.selektovanaParcela.deselektuj();
		}
		this.selektovanaParcela = p;
	}
	
	private int getSuseda(int red, int kolona) {
		
		int br = 0;
		for( int i = red - 1; i <= red + 1; i++) {
			if ( i == -1 || i == redovi) continue;
			for (int j = kolona - 1; j <= kolona + 1; j++ ) {
				if ( j == -1 || j == kolone || (i == red && j == kolona)) continue;
				
				Parcela p = (Parcela) getComponent(i*redovi + j);
				if (p instanceof VodenaPovrs) br++;
			}
		}
		return br;
	}
	 
}
*/
