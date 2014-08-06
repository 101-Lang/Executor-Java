package oneOone.lang.common.commands;

import java.util.ArrayList;

import oneOone.lang.common.ICommand;
import oneOone.lang.common.ICompiler;
import oneOone.lang.common.IDecompiler;
import oneOone.lang.common.IExecutor;

public class LoopStartCommand extends ICommand {
	
	public static ArrayList<Integer> startedLoopReturnNumbers = new ArrayList<>();
	
	@Override
	public String getName() {
		return "loop start";
	}
	
	@Override
	public void execute(IExecutor executer) {
		// not executable
	}
	
	@Override
	public String compile(ICompiler compiler, String code) {
		int nr;
		try {
			nr = Integer.parseInt(code.split(" ")[1]);
		} catch (Exception e) {
			throw new IllegalArgumentException(
					"The command 'loop.start' should be of form 'loop.start [int]'. Or the second argument is missing, or the second argument is not an valid integer.");
		}
		startedLoopReturnNumbers.add(compiler.currentCompiledLine+3);
		compiler.currentCompiledLine--;
		return compiler.compileCommand("push " + nr) + " " + compiler.compileCommand("push 0");
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
		return "loop.start";
	}
	
}
