package oneOone.lang;

import java.util.Arrays;

import oneOone.lang.common.Common;
import oneOone.lang.common.ICommand;
import oneOone.lang.common.IExecutor;

public class Executer extends IExecutor {
	
	public static final String SPACE = " ";
	public static final String PATTERN = "[01]+";
	
	public Executer() {
		Common.init();
	}
	
	@Override
	public void Execute(String input) {
		String[] cmds = input.split(SPACE);
		if (cmds.length == 0)
			return;
		maxLineNumber = cmds.length - 1;
		for (lineNumber = 0; lineNumber < cmds.length; lineNumber++) {
			String cmd = cmds[lineNumber];
			currentCommand = cmd;
			handleCommand(cmd);
			System.out.println("Stack: " + Arrays.toString(stack.toArray()));
		}
	}
	
	@Override
	public void handleCommand(String in) {
		if (!in.matches(PATTERN))
			throw new IllegalArgumentException("Input must be of pattern " + PATTERN);
		in = in.trim();
		for(ICommand command : Common.commands){
			if(in.startsWith(command.getCompiledPrefix()))
				command.execute(this);
		}
	}
	
	public static void main(String[] args) {
		if (args.length == 0)
			throw new IllegalArgumentException("The first argument must be the input.");
		new Executer().Execute(args[0]);;
	}

	
	
}
