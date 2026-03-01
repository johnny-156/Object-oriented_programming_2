package fakultet;

public class GViseNastavnika extends Exception {
	
	public GViseNastavnika() {
		super("Nastavnik vec postoji...");
	}
	
	public GViseNastavnika(String poruka) {
		super(poruka);
	}

}
