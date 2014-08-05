package oneOone.lang.compiler;

import oneOone.lang.common.Common;
import oneOone.lang.common.ICommand;
import oneOone.lang.common.ICompiler;

public class Compiler extends ICompiler{
	
	public Compiler(String input){
		input = input.replaceAll("\n", "").replaceAll("\r", "").replaceAll("\\s+", " ");
		String[] lines = input.split(";");
		String output = "";
		int lineNr = 1;
		for(String line : lines){
			try{
				output += compile(line) + " ";
			}catch(Exception e){
				System.err.println("Error at command " + lineNr);
				e.printStackTrace();
				System.exit(1);
			}
			lineNr++;
		}
		System.out.println(output);
	}
	
	@Override
	public String compile(String code) {
		for(ICommand command : Common.commands){
			if(code.toLowerCase().startsWith(command.getDecompiledPrefix().toLowerCase()))
				return command.compile(this, code);
		}
		throw new IllegalArgumentException("No such function in 101-script: " + code);
	}

	public static void main(String[] args) {
		if(args.length == 0)
			throw new IllegalArgumentException("The first argument must be the input.");
		Common.init();
		new Compiler(args[0]);
	}
}
