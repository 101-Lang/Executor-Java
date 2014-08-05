package oneOone.lang.common.commands;

import oneOone.lang.common.ICommand;
import oneOone.lang.common.ICompiler;
import oneOone.lang.common.IDecompiler;
import oneOone.lang.common.IExecutor;

public class OutCharCommand extends ICommand{

	@Override
	public String getName() {
		return "Out as Char";
	}

	@Override
	public void execute(IExecutor exe) {
		System.out.println("Out: " + (char) exe.getLastStackNumber());
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
		return "10000";
	}

	@Override
	public String getDecompiledPrefix() {
		return "Out.char";
	}
	
}
