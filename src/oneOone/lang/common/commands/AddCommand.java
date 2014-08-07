package oneOone.lang.common.commands;

import oneOone.lang.common.ICommand;
import oneOone.lang.common.ICompiler;
import oneOone.lang.common.IDecompiler;
import oneOone.lang.common.IExecutor;

public class AddCommand extends ICommand {
	
	@Override
	public String getName() {
		return "add";
	}
	
	@Override
	public void execute(IExecutor exe) {
		int a = exe.getLastStackNumber();
		int b = exe.getSecondLastStackNumber();
		exe.removeLastStackNumber();
		exe.removeLastStackNumber();
		exe.stack.add(a + b);
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
		return "11000";
	}
	
	@Override
	public String getDecompiledPrefix() {
		return "Math." + getName();
	}
	
	@Override
	public String getDescription() {
		return "This command adds the two top values of the stack and adds the result to the stack. The two added values are removed."
				+ "For example: "
				+ "if you have stack that looks like [1,2,3], and you do an add command, it will look like [1,5]";
	}
	
}
