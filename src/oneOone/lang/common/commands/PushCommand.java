package oneOone.lang.common.commands;

import oneOone.lang.common.ICommand;
import oneOone.lang.common.ICompiler;
import oneOone.lang.common.IDecompiler;
import oneOone.lang.common.IExecutor;

public class PushCommand extends ICommand {
	
	@Override
	public String getName() {
		return "push";
	}
	
	@Override
	public void execute(IExecutor exe) {
		exe.stack.add(Integer.parseInt(exe.currentCommand.substring(1), 2));
	}
	
	//we get something in decimal, and want it in binary.
	@Override
	public String compile(ICompiler compiler, String code) {  
		return "0" + Integer.toString(Integer.parseInt(code.substring(5)), 2);
	}
	
	 //we get binary ant want decimal.
	@Override
	public String decompile(IDecompiler decompiler, String binary) { 
		return getDecompiledPrefix() + " " + Integer.parseInt(binary.substring(1), 2);
	}
	
	@Override
	public String getCompiledPrefix() {
		return "0";
	}
	
	@Override
	public String getDecompiledPrefix() {
		return "push";
	}

	@Override
	public String getDescription() {
		return "Pushes a value to the end of the stack. For the compiled version: The command starts with 0 and is followed by a binarry number that is the value to add. "
				+ "For exaple if you have code 0101, the first 0 tells it is an add command, an the number 101 is the value to add. So 0101 adds 5 to the stack."
				+ "If you are working in words, the command has 1 argument with the value. This could like like 'push 5'. This will be compiled to 0101";
	}
	
}
