package oneOone.lang.common.commands;

import oneOone.lang.common.ICommand;
import oneOone.lang.common.ICompiler;
import oneOone.lang.common.IDecompiler;
import oneOone.lang.common.IExecutor;

public class JumpCommand extends ICommand{

	@Override
	public String getName() {
		return "jump";
	}

	@Override
	public void execute(IExecutor exe) {
		int addres = exe.getLastStackNumber();
		int condition = exe.getSecondLastStackNumber();
		
		if (condition == 1)
			exe.lineNumber = addres - 1;
		exe.removeLastStackNumber();
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
		return "11100";
	}

	@Override
	public String getDecompiledPrefix() {
		return getName();
	}
	
}
