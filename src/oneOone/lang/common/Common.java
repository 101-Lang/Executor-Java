package oneOone.lang.common;

import java.util.ArrayList;

import oneOone.lang.common.commands.*;

public final class Common {
	
	public static ArrayList<ICommand> commands;
	
	private static boolean isInnitted = false;
	
	public static void init(){
		if(isInnitted)
			return;
		commands = new ArrayList<>();
		
		commands.add(new AddCommand());
		commands.add(new BiggerThanCommand());
		commands.add(new DevideCommand());
		commands.add(new DuplicateCommand());
		commands.add(new EqualsCommand());
		commands.add(new InCharCommand());
		commands.add(new InIntCommand());
		commands.add(new JumpCommand());
		commands.add(new MultiplyCommand());
		commands.add(new NotEqualsCommand());
		commands.add(new OutCharCommand());
		commands.add(new OutIntCommand());
		commands.add(new PushCommand());
		commands.add(new RemoveCommand());
		commands.add(new SmallerThanCommand());
		commands.add(new SubtractCommand());
				
	}
	
}
