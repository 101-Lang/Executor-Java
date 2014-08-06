package oneOone.lang;

import oneOone.lang.common.Common;
import oneOone.lang.common.ICommand;
import oneOone.lang.common.ICompiler;

public class Compiler extends ICompiler{
	
	public Compiler(){
		Common.init();
	}
	
	@Override
	public String compile(String input){
		input = input.replaceAll("\n", "").replaceAll("\r", "").replaceAll("\\s+", " ");
		String[] lines = input.split(";");
		String output = "";
		int lineNr = 1;
		for(String line : lines){
			try{
				output += compileCommand(line) + " ";
			}catch(Exception e){
				System.err.println("Error at command " + lineNr);
				e.printStackTrace();
				System.exit(1);
			}
			lineNr++;
		}
		return output;
	}
	
	@Override
	public String compileCommand(String code) {
		for(ICommand command : Common.commands){
			if(code.toLowerCase().startsWith(command.getDecompiledPrefix().toLowerCase()))
				return command.compile(this, code);
		}
		throw new IllegalArgumentException("No such function in 101-script: " + code);
	}

	public static void main(String[] args) {
		if(args.length == 0)
			throw new IllegalArgumentException("The first argument must be the input.");
		System.out.println(new Compiler().compile(args[0]));
	}
}
