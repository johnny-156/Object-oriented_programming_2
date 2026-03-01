/* Simulator je glavni prozor aplikacije sirine 200 i visine 400
piksela koji sadrzi svemir, panel sa komandama i generator
nebeskih tela. Panel sa komandama sadrzi dugme sa natpisom
„Pokreni!“. Pritiskom na dugme „Pokreni!“ pokrecu se svemir i
generator nebeskih tela, nakon cega je dugme onemoguceno. */

package zad1;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Simulator extends Frame {
	
	Svemir svemir = new Svemir();
	Panel south = new Panel();
	Button pokreni = new Button("Pokreni!");
	AktivniGenerator generator = new AktivniGenerator(svemir);
	
	private void populateWindow() {
		
		add(svemir, BorderLayout.CENTER);
		
		pokreni.addActionListener((ae) -> {
			generator.pokreni();			
			pokreni.setEnabled(false);
		});
		
		south.add(pokreni);
		add(south, BorderLayout.SOUTH);
		
	}
	
	public Simulator() {

		setBounds(700, 200, 200, 400);
		setResizable(false);
		
		populateWindow();
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
			generator.zaustavi();
			dispose();
			}
		});
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new Simulator();
	}

}
