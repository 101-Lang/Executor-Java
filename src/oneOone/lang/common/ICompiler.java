package oneOone.lang.common;

import oneOone.lang.common.exception.CompileException;

public abstract class ICompiler {
	
	/*starts with 1 and ends on the end line. This is the real line number like:
	1. push 10;
	2. duplicate;
	3. out.int
	*/
	public int currentUncompiledCommand = 0;
	
	/*
	 * This is the compiled line:
	  	1. 01010
		2. 11101
		3. 10001
	 */
	public int currentCompiledLine = 0;
	
	public abstract String compile(String command) throws CompileException;
	
	public abstract String compileCommand(String command);
	
}
