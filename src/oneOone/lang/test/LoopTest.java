package oneOone.lang.test;

import oneOone.lang.Compiler;
import oneOone.lang.Executer;
import oneOone.lang.common.exception.CompileException;

public class LoopTest {
	
	private static String code = "pUsH 10;"
			+ "push 1;"
			+ "duplicate;"
			+ "out.int;"
			+ "push 1;"
			+ "math.add;"
			+ "compare.smaller;"
			+ "push 2;"
			+ "jump;";
	
	public static void main(String[] args) {
		
		Compiler c = new Compiler();
		String compiled;
		try {
			
			compiled = c.compile(code);
			System.out.println(compiled);
			Executer e = new Executer();
			e.Execute(compiled, true);
			
		} catch (CompileException e1) {
			System.err.println("line number: " + e1.getLineNumber());
			e1.printStackTrace();
		}
		
		
		
	}
	
}
