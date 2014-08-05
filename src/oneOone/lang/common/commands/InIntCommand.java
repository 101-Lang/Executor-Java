package oneOone.lang.common.commands;

import java.util.Scanner;

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
		Scanner scanner = new Scanner(System.in);
		String txt = scanner.nextLine();
		exe.stack.add(Integer.parseInt(txt));
		scanner.close();
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
	
}
