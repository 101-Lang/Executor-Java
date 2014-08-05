package oneOone.lang.common.commands;

import oneOone.lang.common.ICommand;
import oneOone.lang.common.ICompiler;
import oneOone.lang.common.IDecompiler;
import oneOone.lang.common.IExecutor;

public class SubtractCommand extends ICommand {
	
	@Override
	public String getName() {
		return "subtract";
	}

	@Override
	public void execute(IExecutor exe) {
		int a = exe.getLastStackNumber();
		int b = exe.getSecondLastStackNumber();
		exe.removeLastStackNumber();
		exe.removeLastStackNumber();
		exe.stack.add(a - b);
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
		return "11001";
	}

	@Override
	public String getDecompiledPrefix() {
		return "Math." + getName();
	}
	
}
