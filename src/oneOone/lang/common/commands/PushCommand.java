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
	
	@Override
	public String compile(ICompiler compiler, String code) {  //we get something in decimal, and want it in binary.
		return "0" + Integer.toString(Integer.parseInt(code.substring(5)), 2);
	}
	
	@Override
	public String decompile(IDecompiler decompiler, String binary) {  //we get binary ant want decimal.
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
	
}
