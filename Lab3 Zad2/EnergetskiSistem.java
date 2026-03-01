/* Energetski sistem je glavni prozor aplikacije sirine 500 i visine 500 piksela koji sadrzi
plac i bateriju. Stvara se sa zadatim brojem redova i kolona placa i kapacitetom baterije. U
toku stvaranja energetskog sistema naprave se plac i baterija. Nije moguce promeniti velicinu
prozora. Prozor sadrzi dugme (Button) za dodavanje novog proizvodjaca.*/

package zad2;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EnergetskiSistem extends Frame {

	Plac plac;
	Baterija baterija;
	Button dodaj = new Button("Dodaj");
	
	private void populateWindow() {
		
		Panel north = new Panel();
		north.add(dodaj);
		add(north, BorderLayout.NORTH);
		
		dodaj.addActionListener((ae) -> {
			Hidroelektrana h = new Hidroelektrana(baterija);
			plac.dodajProizvodjaca(h);			
		});
		
		add(plac, BorderLayout.CENTER);
		
	}
	
	public EnergetskiSistem(int redovi, int kolone, int kapacitet) {
		plac = new Plac(redovi, kolone);
		baterija = new Baterija(kapacitet);
		
		setBounds(700, 200, 500, 500);
		setResizable(false);
		setTitle("Energetski sistem");
		
		populateWindow();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				plac.zaustavi();
				dispose();
			}
		});
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new EnergetskiSistem(5, 5, 100);
	}

}

/*
public class EnergetskiSistem extends Frame {

	Plac plac;
	Baterija baterija;
	Button dodaj = new Button("Dodaj");
	boolean hidro = true;
	
	private void populateWindow() {
		
		MenuItem ugasi = new MenuItem("Ugasi", new MenuShortcut(KeyEvent.VK_E));
		
		ugasi.addActionListener((ae) -> {
			plac.zaustavi();
			dispose();
		});
		
		Menu menu = new Menu("Akcije");
		menu.add(ugasi);
		
		MenuBar main = new MenuBar();
		main.add(menu);
		
		setMenuBar(main);
		
		Panel north = new Panel();
		north.add(dodaj);
		add(north, BorderLayout.NORTH);
		
		dodaj.addActionListener((ae) -> {
			boolean hidro = this.hidro;
			if (hidro) {
				Hidroelektrana h = new Hidroelektrana(baterija);
				plac.dodajObjekat(h);		
			}
			else if(!hidro) {
				Mlin m = new Mlin(baterija);
				plac.dodajObjekat(m);	
			}	
		});
		
		add(plac, BorderLayout.CENTER);
		
		Panel south = new Panel(new GridLayout(1, 0, 0, 0));
		
		Panel southWest = new Panel(new GridLayout(0, 1, 0, 0));
		southWest.setBackground(Color.BLUE);
		
		Label naslov = new Label("Izaberite: ", Label.LEFT);
		southWest.add(naslov);
		
		CheckboxGroup group = new CheckboxGroup();
		Checkbox c1 = new Checkbox("Hidroelektrana", false, group);
		Checkbox c2 = new Checkbox("Mlin", false, group);
		southWest.add(c1);
		southWest.add(c2);
		
		c1.addItemListener((ie) -> {
			if(ie.getStateChange() == ItemEvent.SELECTED) {
				this.hidro = true;
				this.plac.pocetak = true;
			}
		});
		c2.addItemListener((ie) -> {
			if(ie.getStateChange() == ItemEvent.SELECTED) {
				this.hidro = false;
				this.plac.pocetak = true;
			}
		});
		
		south.add(southWest);
		
		Panel southEast = new Panel();
		
		Label stanje = new Label("Trenutno stanje baterije je: ");
		southEast.add(stanje);
		southEast.add(this.baterija.energija);
		
		south.add(southEast);
		
		add(south, BorderLayout.SOUTH);
	
	}
*/