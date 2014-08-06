package oneOone.lang.common;

import java.util.ArrayList;
import java.util.List;

public abstract class IExecutor {
	
	public int lineNumber, maxLineNumber;
	
	public String currentCommand;
	
	public List<Integer> stack = new ArrayList<>();
	
	public abstract String Execute(String input, boolean showStack);
	
	public abstract void handleCommand(String in);
	
	public int getLastStackNumber() {
		return stack.get(stack.size() - 1);
	}
	
	public int getSecondLastStackNumber() {
		return stack.get(stack.size() - 2);
	}
	
	public void removeLastStackNumber() {
		stack.remove(stack.size() - 1);
	}

	public abstract void println(String string);
}
