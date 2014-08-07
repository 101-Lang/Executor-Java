package oneOone.lang.common.commands;

import oneOone.lang.common.ICommand;
import oneOone.lang.common.ICompiler;
import oneOone.lang.common.IDecompiler;
import oneOone.lang.common.IExecutor;

public class LoopEndCommand extends ICommand {
	
	@Override
	public String getName() {
		return "loop end";
	}
	
	@Override
	public void execute(IExecutor executer) {
		// not executable
	}
	
	@Override
	public String compile(ICompiler compiler, String code) {
		compiler.currentCompiledLine--;
		int toJumpTo = LoopStartCommand.startedLoopReturnNumbers.get(LoopStartCommand.startedLoopReturnNumbers.size()-1);
		LoopStartCommand.startedLoopReturnNumbers.remove(LoopStartCommand.startedLoopReturnNumbers.size()-1);
		return compiler.compileCommand("push 1") + " " +
			   compiler.compileCommand("math.add") + " " +
			   compiler.compileCommand("compare.smaller ") + " " +
			   compiler.compileCommand("push " + toJumpTo) + " " + 
			   compiler.compileCommand("jump") + " " +
			   compiler.compileCommand("remove") + " " +
			   compiler.compileCommand("remove");
	}
	
	@Override
	public String decompile(IDecompiler decompiler, String binary) {
		return null;
	}
	
	@Override
	public String getCompiledPrefix() {
		return null;
	}
	
	@Override
	public String getDecompiledPrefix() {
		return "loop.end";
	}
	
	@Override
	public String getDescription() {
		return "End a loop. "
				+ "If no loop is opend by loop.start than an erro will be thrown."
				+ "This function is not a generic 101 function. It only makes coding simpler. "
				+ "If you want to see how this is compiled, look at the source code. ";
	}
	
}
