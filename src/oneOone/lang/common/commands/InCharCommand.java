package oneOone.lang.common.commands;

import oneOone.lang.common.ICommand;
import oneOone.lang.common.ICompiler;
import oneOone.lang.common.IDecompiler;
import oneOone.lang.common.IExecutor;

public class InCharCommand extends ICommand{

	@Override
	public String getName() {
		return "Input a Char";
	}

	@Override
	public void execute(IExecutor exe) {
		try{
			String read = exe.getInputScanner().nextLine();
			exe.stack.add((int) read.charAt(0));
		}catch(Exception e){
			exe.stack.add(-1);
			e.printStackTrace();
		}

	}

	@Override
	public String compile(ICompiler compiler, String code) {
		return getCompiledPrefix();
	}

	@Override
	public String decompile(IDecompiler decompiler, String binary) {
		return getDecompiledPrefix();
	}

	@Override
	public String getCompiledPrefix() {
		return "10010";
	}

	@Override
	public String getDecompiledPrefix() {
		return "In.char";
	}
	
	@Override
	public String getDescription() {
		return "Get input at runtime from the user. "
				+ "This one gets a Character from the user, transfoms it to an integer and aaddes it to the stack.. "
				+ "Int value of the character is added to the stack at the end."
				+ "If multaple characters are inputted, it will take the first one."
				+ "If something goes wrong (reading/converting/adding) than the value -1 will be added to the stack.";
	}
	
}
