/*Parcela je natpis (Label), koji se stvara sa zadatom jednoslovnom oznakom i bojom pozadine
(Color). Oznaka se ispisuje belom bojom (WHITE) i fontom Serif, podebljano, velicine 14.
Parceli moze da se promeni boja pozadine. Parcela moze da se izabere klikom misa, sto ona
prijavljuje roditeljskom kontejneru. */

package zad2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class Parcela extends Label {
	
	private char oznaka;
	private Color pozadina;
	private boolean isSelected; //flag za ako je vec do sada selektovana
	private int index; //redni broj parcele u resetki, po redosledu dodavanja, od nule
	
	public Parcela(char oznaka, Color pozadina) {
		this.oznaka = oznaka;
		this.pozadina = pozadina;
		this.isSelected = false; //jer pri stvaranju parcela nije izabrana i nije bila ranije
		
		setText("" + oznaka);
		setForeground(Color.WHITE);
		setBackground(pozadina);
		setFont(new Font("Serif", Font.BOLD, 14));
		setAlignment(Label.CENTER);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				int velicina = isSelected ? 14 : 20; //razdvajamo 2 slucaja, kada istu parcelu prvi i drugi put selektujemo
				setFont(new Font("Serif", Font.BOLD, velicina));
				isSelected = !isSelected;
				
				Plac p = (Plac) getParent();
				p.obavestiPlac((Parcela) e.getSource());
			}
		
		});
	}
	
	public void deselektuj() { //vracanje ranije selektovane na false
		this.isSelected = false;
		setFont(new Font("Serif", Font.BOLD, 14));
	}

	public void promeniPozadinu(Color boja) {
		this.pozadina = boja;
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex( int index ) {
		this.index = index;
	}
	
	
}