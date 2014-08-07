package oneOone.lang;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

import oneOone.lang.common.Common;
import oneOone.lang.common.ICommand;
import oneOone.lang.common.IExecutor;

public class Executer extends IExecutor {
	
	public static final String SPACE = " ";
	private String output;
	private Scanner scanner;
	
	public boolean showStack = false;
	public boolean showCommands = false;
	
	public Executer() {
		Common.init();
	}
	
	@Override
	public String Execute(String input, boolean showStack, InputStream inputStream) {
		this.showStack = showStack;
		String[] cmds = input.split(SPACE);
		if (cmds.length == 0)
			return null;
		this.scanner = new Scanner(inputStream);
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
		if(showCommands)
			System.out.println("now running: " + in);
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
		new Executer().Execute(args[0], true, System.in);
	}

	@Override
	public void println(String line){
		System.out.println(line);
		output += line + System.lineSeparator();
	}

	@Override
	public Scanner getInputScanner() {
		return scanner;
	}
	
}
