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
			exe.lineNumber = addres -2;
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

	@Override
	public String getDescription() {
		return "Jumps to an other spot in the compiled code. "
				+ "A jump command looks at two values in the stack. "
				+ "The last value on the stack represents the addres to jump to. THIS IS THE ADDRES IN COMPILED CODE!"
				+ "The second last value represents the condition whether or not to jump. "
				+ "If the confition is 1 it will jump, else it will not jump."
				+ "So if the stack looks like [1, 3] and a 'jump' command is executed, "
				+ "the next statement that will be executed is the third statement in the compiled code."
				+ "To understand this command better, look at the the JumpExample.java file.";
	}
	
}
