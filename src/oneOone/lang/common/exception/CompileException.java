package oneOone.lang.common.exception;

public class CompileException extends Exception {

	private static final long serialVersionUID = -766408612969692978L;
	
	private int lineNumber;
	
	public CompileException(int lineNumber, String message){
		super(message);
		this.lineNumber = lineNumber;
	}
	
	public int getLineNumber() {
		return lineNumber;
	}
}
