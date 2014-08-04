package special.lang.chicken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import special.lang.chicken.instructions.Add;
import special.lang.chicken.instructions.Char;
import special.lang.chicken.instructions.Chicken;
import special.lang.chicken.instructions.Compare;
import special.lang.chicken.instructions.Exit;
import special.lang.chicken.instructions.Fly;
import special.lang.chicken.instructions.Multiply;
import special.lang.chicken.instructions.Push;
import special.lang.chicken.instructions.Store;
import special.lang.chicken.instructions.Subtract;

public class ChickenExecuter {
	
	public static final String CHICKEN = "chicken";
	public static final String SPACE = " ";
	public static final String NEW_LINE = "\\r?\\n";
	
	public static Instruction[] instructions = new Instruction[11];
	
	public List<Integer> stack = new ArrayList<>();
	
	public int chickensOnCurrentLine;
	public String input;
	public int lineNumber;
	
	public ChickenExecuter(String chickens, String input) {
		this.input = input;
		
		String[] lines = chickens.split(NEW_LINE);
		if(lines.length == 0)
			return;
		for(lineNumber = 0; lineNumber < lines.length; lineNumber++){
			String line = lines[lineNumber];
			chickensOnCurrentLine = countChickens(line);
			System.out.println(chickensOnCurrentLine);
			if(chickensOnCurrentLine < 10)
				instructions[chickensOnCurrentLine].run(this);
			else{
				instructions[10].run(this);
			}
			System.out.println("Stack: " + Arrays.toString(stack.toArray()));
		}
	}
	
	public static int countChickens(String chickens){
		chickens = chickens.trim();
		if(chickens.isEmpty())
			return 0;
		String[] split = chickens.split(SPACE);
		int counter = 0;
		for(String chick : split){
			if(chick.equals(CHICKEN))
				counter++;
		}
		return counter;
	}

	public static void init(){
		instructions[0] = new Exit();
		instructions[1] = new Chicken();
		instructions[2] = new Add();
		instructions[3] = new Subtract();
		instructions[4] = new Multiply();
		instructions[5] = new Compare();
		
		instructions[7] = new Store();
		instructions[8] = new Fly();
		instructions[9] = new Char();
		instructions[10] = new Push();
	}
	
	public static void main(String[] args) {
		if(args.length < 2)
			throw new IllegalArgumentException("The first argument must me the chickens.");
		init();
		new ChickenExecuter(args[0], args[1]);
	}

	public void exit() {
		//TODO: exit
	}
	
}
