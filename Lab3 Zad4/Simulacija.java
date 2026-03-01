/*
Graficka simulacija je glavni prozor aplikacije (Frame) koji sadrzi scenu. Pritiskom levog tastera misa na scenu 
stvori se figura podrazumevanog poluprecnika ciji vektor polozaja odgovara koordinatama misa, dok se vektor pomeraja
generise na pseudoslucajan nacin. Moguce je pauzirati i nastaviti posao scene pritiskom na taster Space, dok se 
pritiskom na taster Esc zatvara glavni prozor i sve niti trajno zavrsavaju svoj rad. Dok scena obavlja svoj posao 
nije moguca interakcija sa scenom. Interakcija sa scenom je moguca kad je na sceni ispisan tekst PAUZA.
 */

package zad2;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Simulacija extends Frame {	
	
	private Scena scena;
	
	public Simulacija() {
		scena = new Scena(this);
		add(scena, BorderLayout.CENTER);
		setBounds(700, 200, 400, 300);
		setTitle("Simulacija");
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				scena.zaustavi();
				dispose();
			}
		});
		setVisible(true);
		
		scena.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ( !scena.pauza() ) {
					System.out.println("GRESKA, NIJE PAUZA!");
					return;
				}
				int x = e.getX();
				int y = e.getY();
				x = x - scena.getWidth()/2;
				y = scena.getHeight()/2 - y;
				
				Vektor polozaj = new Vektor(x, y);
				Vektor pomeraj = new Vektor();
				Disk d = new Disk(polozaj, pomeraj);
				scena.dodajFiguru(d);
				
				System.out.println(x + " " + y);
			}
		});
		
		scena.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char slovo = Character.toUpperCase(e.getKeyChar());
				if(slovo == KeyEvent.VK_SPACE) {					
					if ( scena.pauza() ) {
						scena.nastavi();
						System.out.println("NASTAVAK");
					} else {
						scena.pauziraj();
						System.out.println("PAUZA");
					}
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ESCAPE) {
					scena.zaustavi();
					dispose();
				}
			}
		});
	}
	
	public static void main(String[] args) {
		Simulacija simulacija =  new Simulacija();
	}

}