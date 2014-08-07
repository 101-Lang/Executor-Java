package oneOone.lang.common.commands;

import oneOone.lang.common.ICommand;
import oneOone.lang.common.ICompiler;
import oneOone.lang.common.IDecompiler;
import oneOone.lang.common.IExecutor;

public class DevideCommand extends ICommand {
	
	@Override
	public String getName() {
		return "devide";
	}
	
	@Override
	public void execute(IExecutor exe) {
		int a = exe.getLastStackNumber();
		int b = exe.getSecondLastStackNumber();
		exe.removeLastStackNumber();
		exe.removeLastStackNumber();
		exe.stack.add(Math.round((float)a / (float)b));
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
		return "11011";
	}
	
	@Override
	public String getDecompiledPrefix() {
		return "Math." + getName();
	}

	@Override
	public String getDescription() {
		return "This command devides the last value on the stack from the second last value and adds the result to the stack. The two devided values are removed." 
				+ "If the result is a deciaml number, it will be rounded."
				+ "For example: "
				+ "if you have stack that looks like [1,2,3], and you do an devide command(it will do 3/2=1,5=2), it will look like [1,2]";
	}
}
