package oneOone.lang.executor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Executer {
	
	public static final String I = "1";
	public static final String O = "0";
	public static final String SPACE = " ";
	public static final String PATTERN = "[01]+";
	
	public static HashMap<String, Action> actions = new HashMap<>();
	
	public List<Integer> stack = new ArrayList<>();
	
	public int lineNumber, maxLineNumber;
	
	public Executer(String chickens) {

		String[] cmds = chickens.split(SPACE);
		if(cmds.length == 0)
			return;
		maxLineNumber = cmds.length-1;
		for(lineNumber = 0; lineNumber < cmds.length; lineNumber++){
			String cmd = cmds[lineNumber];
			handleCommand(cmd);
			System.out.println("Stack: " + Arrays.toString(stack.toArray()));
		}
	}
	
	public void handleCommand(String in){
		if(!in.matches(PATTERN))
			throw new IllegalArgumentException("Input must be of pattern " + PATTERN);
		if(in.startsWith(O)){//push command
			stack.add( Integer.parseInt(in.substring(1), 2));
		}
		if(in.startsWith(I)){//real command
			try{
			actions.get(in.substring(1)).run(this);
			}catch(Exception e){
				
			}
		}
	}

	public static void init(){
		actions.put("1000", ActionLib.mathAdd);
		actions.put("1001", ActionLib.mathSub);
		actions.put("1010", ActionLib.mathMul);
		actions.put("1011", ActionLib.mathDev);
		
		actions.put("0100", ActionLib.compareBigger);
		actions.put("0101", ActionLib.compareSmaller);
		actions.put("0110", ActionLib.compareEquals);
		actions.put("0111", ActionLib.compareNotEquals);
		
		actions.put("1100", ActionLib.jumpTo);
		actions.put("1101", ActionLib.duplicate);
		actions.put("1110", ActionLib.remove);
		actions.put("1111", null);
		
		actions.put("0000", ActionLib.outChar);
		actions.put("0001", ActionLib.outInt);
		actions.put("0010", null);
		actions.put("0011", null);
	}
	
	public int getLastStackNumber(){
		return stack.get(stack.size()-1);
	}
	public int getSecondLastStackNumber(){
		return stack.get(stack.size()-2);
	}
	public void removeLastStackNumber(){
		stack.remove(stack.size()-1);
	}
	
	public static void main(String[] args) {
		if(args.length == 0)
			throw new IllegalArgumentException("The first argument must be the input.");
		init();
		new Executer(args[0]);
	}
	
}
