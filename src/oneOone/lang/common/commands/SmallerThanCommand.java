package oneOone.lang.common.commands;

import oneOone.lang.common.ICommand;
import oneOone.lang.common.ICompiler;
import oneOone.lang.common.IDecompiler;
import oneOone.lang.common.IExecutor;

public class SmallerThanCommand extends ICommand {
	
	@Override
	public String getName() {
		return "smaller than";
	}
	
	@Override
	public void execute(IExecutor exe) {
		int a = exe.getLastStackNumber();
		int b = exe.getSecondLastStackNumber();
		exe.stack.add(a < b ? 1 : 0);
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
		return "10101";
	}
	
	@Override
	public String getDecompiledPrefix() {
		return "Compare.smaller";
	}
	
	@Override
	public String getDescription() {
		return "Compares the last value on the stack to the second last value on the stack. "
				+ "It works like the '<' character in Java. "
				+ "If the last value is smaller than the second last, it will add 1 to the stack, "
				+ "else it will add 0 to the stack.";
	}
	
}