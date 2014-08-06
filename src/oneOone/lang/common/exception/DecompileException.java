package oneOone.lang.common.exception;

public class DecompileException extends Exception {
	
	private static final long serialVersionUID = 4296226093134372636L;
	private int codeNumber;
	
	public DecompileException(int codeNumber, String message) {
		super(message);
		this.codeNumber = codeNumber;
	}
	
	public int getCodeNumber() {
		return codeNumber;
	}
}
