package oneOone.lang.common.commands;

import oneOone.lang.common.ICommand;
import oneOone.lang.common.ICompiler;
import oneOone.lang.common.IDecompiler;
import oneOone.lang.common.IExecutor;

public class RemoveCommand extends ICommand{

	@Override
	public String getName() {
		return "remove";
	}

	@Override
	public void execute(IExecutor exe) {
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
		return "11110";
	}

	@Override
	public String getDecompiledPrefix() {
		return getName();
	}
	
}
