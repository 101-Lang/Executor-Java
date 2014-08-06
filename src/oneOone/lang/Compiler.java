package oneOone.lang;

import oneOone.lang.common.Common;
import oneOone.lang.common.ICommand;
import oneOone.lang.common.ICompiler;
import oneOone.lang.common.commands.LoopStartCommand;
import oneOone.lang.common.exception.CompileException;

public class Compiler extends ICompiler{
	
	public Compiler(){
		Common.init();
	}
	
	@Override
	public String compile(String input) throws CompileException{
		LoopStartCommand.startedLoopReturnNumbers.clear();
		
		input = input.replaceAll(" +", " ");
		String[] lines = input.split("\\r?\\n");
		String output = "";
		currentUncompiledCommand = 1;
		for(String line : lines){
			String[] commands = line.split(";");
			for(String command : commands){
				try{
					command = command.trim();
					output += compileCommand(command) + " ";
				}catch(Exception e){
					throw new CompileException(currentUncompiledCommand, e.getMessage());
				}	
			}
			
			currentUncompiledCommand++;
		}
		return output;
	}
	
	@Override
	public String compileCommand(String code){
		for(ICommand command : Common.commands){
			if(code.toLowerCase().startsWith(command.getDecompiledPrefix().toLowerCase())){
				String out = command.compile(this, code);
				currentCompiledLine ++;
				return out;
			}
		}
		throw new IllegalArgumentException("No such function in 101-script: '" + code + "'");
	}

	public static void main(String[] args) {
		if(args.length == 0)
			throw new IllegalArgumentException("The first argument must be the input.");
		try {
			System.out.println(new Compiler().compile(args[0]));
		} catch (CompileException e) {
			e.printStackTrace();
		}
	}
}
