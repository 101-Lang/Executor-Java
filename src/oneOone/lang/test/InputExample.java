package oneOone.lang.test;

import oneOone.lang.Compiler;
import oneOone.lang.Executer;
import oneOone.lang.common.exception.CompileException;

public class InputExample {
	
	private static String code = 
			  "in.int;"   //read integer
			+ "duplicate;"//duplicate the read value
			+ "out.int;"  //output the read integer as int
			+ "out.char;" //output the read integer as character

			+ "in.char;"  //read a character.
			+ "duplicate;"//duplicate the read value
			+ "out.int;"  //output the read char as int
			+ "out.char;";//output the read char as character
	
	/**
	 * A little program to show the input functions.
	 * First it will ask for a Integer. The integer will be outputed as an integer ánd as a Character.
	 * Next is asked for a Character. The char will be outputed as Integer ánd as a Character.
	 * @param args will be ignored.
	 */
	public static void main(String[] args) {
		
		Compiler c = new Compiler();
		String compiled;
		try {
			
			compiled = c.compile(code);
			System.out.println("compiled: " + compiled);
			Executer e = new Executer();
			e.Execute(compiled, false, System.in);
			
		} catch (CompileException e1) {
			System.err.println("line number: " + e1.getLineNumber());
			e1.printStackTrace();
		}
	}
}
