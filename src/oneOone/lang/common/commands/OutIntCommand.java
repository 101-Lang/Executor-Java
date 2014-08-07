package oneOone.lang.common.commands;

import oneOone.lang.common.ICommand;
import oneOone.lang.common.ICompiler;
import oneOone.lang.common.IDecompiler;
import oneOone.lang.common.IExecutor;

public class OutIntCommand extends ICommand{

	@Override
	public String getName() {
		return "Out as Integer";
	}

	@Override
	public void execute(IExecutor exe) {
		exe.println("Out: " + exe.getLastStackNumber());
		exe.removeLastStackNumber();
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
		return "10001";
	}

	@Override
	public String getDecompiledPrefix() {
		return "Out.int";
	}
	
	@Override
	public String getDescription() {
		return "Outputs the last value on the stack. "
				+ "For example, if the stack looks like [99], "
				+ "and the out.int command is executed, "
				+ "the output would be '99' and the stack would be [] (empty)";
	}
}
