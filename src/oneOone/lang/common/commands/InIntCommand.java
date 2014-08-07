package oneOone.lang.common.commands;

import oneOone.lang.common.ICommand;
import oneOone.lang.common.ICompiler;
import oneOone.lang.common.IDecompiler;
import oneOone.lang.common.IExecutor;

public class InIntCommand  extends ICommand{

	@Override
	public String getName() {
		return "Input a Integer";
	}

	@Override
	public void execute(IExecutor exe) {
		try{
			exe.stack.add(Integer.parseInt(exe.getInputScanner().nextLine()));
		}catch(Exception e){
			exe.stack.add(-1);
			e.printStackTrace();
		}

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
		return "10011";
	}

	@Override
	public String getDecompiledPrefix() {
		return "In.int";
	}

	@Override
	public String getDescription() {
		return "Get input at runtime from the user. "
				+ "This one gets a Integer from the user. "
				+ "The read integer is added to the stack at the end."
				+ "If something goes wrong (reading/converting/adding) than the value -1 will be added to the stack.";
	}
	
}
