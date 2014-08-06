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
	
}
