package special.lang.chicken.instructions;

import java.util.List;

import special.lang.chicken.ChickenExecuter;
import special.lang.chicken.Instruction;

public class Fly extends Instruction{

	@Override
	public void run(ChickenExecuter exe) {
		
		List<Integer> stack = exe.stack;
		int offset = stack.get(stack.size() - 1); // get last
		int condition = stack.get(stack.size() - 2); // get second last
		
		// remove last two numbers
		stack.remove(stack.size() - 1);
		stack.remove(stack.size() - 1);
		
		if(condition == 1)
			exe.lineNumber -= offset;
	}
	
}
