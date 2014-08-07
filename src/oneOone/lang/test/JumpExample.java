package oneOone.lang.test;

import oneOone.lang.Compiler;
import oneOone.lang.Executer;
import oneOone.lang.common.exception.CompileException;

public class JumpExample {
	
	private static String code = 
			  "PusH 5;"  //pushes 5 to the stack
			+ "out.int;" //shows the 5 on the screen.
			+ "push 1;"  //this is the condition: true
			+ "push 1;"  //this is the addres. This jumps to the beginning of the program.
			+ "jump;";   //jumps to 'push 5' command.
	
	/**
	 * Runs a example program. This is never ending loop that will output forever '5'.
	 * Look at the source to see the code.
	 * @param args are ignored
	 */
	public static void main(String[] args) {
		
		Compiler c = new Compiler();
		String compiled;
		try {
			
			compiled = c.compile(code);
			System.out.println(compiled);
			Executer e = new Executer();
			e.Execute(compiled, true, System.in);
			
		} catch (CompileException e1) {
			System.err.println("line number: " + e1.getLineNumber());
			e1.printStackTrace();
		}
		
		
		
	}
	
}
