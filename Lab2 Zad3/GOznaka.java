package skijanje;

public class GOznaka extends Exception{
	
	public GOznaka() {
		super("Staza ne sadrzi nijednu deonicu...");
	}
	
	public GOznaka(String poruka) {
		super(poruka);
	}
}
