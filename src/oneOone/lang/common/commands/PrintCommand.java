package oneOone.lang.common.commands;

import oneOone.lang.common.ICommand;
import oneOone.lang.common.ICompiler;
import oneOone.lang.common.IDecompiler;
import oneOone.lang.common.IExecutor;

public class PrintCommand extends ICommand{

	@Override
	public String getName() {
		return "print";
	}

	@Override
	public void execute(IExecutor executer) {}

	@Override
	public String compile(ICompiler compiler, String code) {
		compiler.currentCompiledLine--;
		String out = "";
		String toPrint;
		try{
			toPrint = code.split(" ", 2)[1];
		} catch (Exception e) {
			throw new IllegalArgumentException(
					"The command 'print' should be of form 'print [text]'. Or the second argument is missing, or the second argument is not an valid String.");
		}
		for(char c : toPrint.toCharArray()){
			out += compiler.compileCommand("push " + (int) c) + " ";
			out += compiler.compileCommand("out.char") + " ";
		}
		return out;
	}

	@Override
	public String decompile(IDecompiler decompiler, String binary) {
		return null;
	}

	@Override
	public String getCompiledPrefix() {
		return null;
	}

	@Override
	public String getDecompiledPrefix() {
		return "print ";
	}

	@Override
	public String getDescription() {
		return "Prints out everything that is behind it. "
				+ "For example, 'print hoi', will print h, o, i to the console."
				+ "The compiler will throw an error if the argument is missing!"
				+ "This is a funciton that has no compiled version. "
				+ "Like 'print cc' will be compiled as 'push 99; out.char;push 99; out.char'. "
				+ "This is just a function to make coding simpler.";
	}
	
}
