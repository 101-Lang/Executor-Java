package oneOone.lang.common.commands;

import java.util.Scanner;

import oneOone.lang.common.ICommand;
import oneOone.lang.common.ICompiler;
import oneOone.lang.common.IDecompiler;
import oneOone.lang.common.IExecutor;

public class InCharCommand extends ICommand{

	@Override
	public String getName() {
		return "Input a Char";
	}

	@Override
	public void execute(IExecutor exe) {
		Scanner scanner = new Scanner(System.in);
		String txt = scanner.nextLine();
		exe.stack.add(Character.getNumericValue(txt.charAt(0)));
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
		return "10010";
	}

	@Override
	public String getDecompiledPrefix() {
		return "In.char";
	}
	
}
