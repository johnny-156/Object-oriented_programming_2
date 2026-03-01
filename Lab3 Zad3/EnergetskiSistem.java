/* Energetski sistem je glavni prozor aplikacije sirine 500 i visine 500 piksela koji sadrzi
plac i bateriju. Stvara se sa zadatim brojem redova i kolona placa i kapacitetom baterije. U
toku stvaranja energetskog sistema naprave se plac i baterija. Nije moguce promeniti velicinu
prozora. Prozor sadrzi dugme (Button) za dodavanje novog proizvodjaca.*/

package zad2;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
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
