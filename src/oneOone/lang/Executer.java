package oneOone.lang;

import java.util.Arrays;

import oneOone.lang.common.Common;
import oneOone.lang.common.ICommand;
import oneOone.lang.common.IExecutor;

public class Executer extends IExecutor {
	
	public static final String SPACE = " ";
	public static final String PATTERN = "[01]+";
	private String output;
	
	public Executer() {
		Common.init();
	}
	
	@Override
	public String Execute(String input, boolean showStack) {
		String[] cmds = input.split(SPACE);
		if (cmds.length == 0)
			return null;
		output = "";
		maxLineNumber = cmds.length - 1;
		for (lineNumber = 0; lineNumber < cmds.length; lineNumber++) {
			String cmd = cmds[lineNumber];
			currentCommand = cmd;
			handleCommand(cmd);
			if(showStack)
			println("Stack: " + Arrays.toString(stack.toArray()));
		}
		return output;
	}
	
	@Override
	public void handleCommand(String in) {
		in = in.trim().replaceAll("\\r?\\n", "");

		System.out.println(in);
		if (!in.matches(PATTERN))
			throw new IllegalArgumentException("Input must be of pattern " + PATTERN);
		for(ICommand command : Common.commands){
			if(command.getCompiledPrefix() == null)
				continue;
			if(in.startsWith(command.getCompiledPrefix()))
				command.execute(this);
		}
	}
	
	public static void main(String[] args) {
		if (args.length == 0)
			throw new IllegalArgumentException("The first argument must be the input.");
		new Executer().Execute(args[0], true);
	}

	@Override
	public void println(String line){
		System.out.println(line);
		output += line + System.lineSeparator();
	}
	
}
