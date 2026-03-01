package kafic;

public class GPostoji extends Exception{
	
	public GPostoji() {
		super("Dodata su 2 ista pica...");
	}
	
	public GPostoji(String poruka) {
		super(poruka);
	}

}
