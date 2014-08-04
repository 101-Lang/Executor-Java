package special.lang.chicken.instructions;

import java.util.List;

import special.lang.chicken.ChickenExecuter;
import special.lang.chicken.Instruction;

public class Store extends Instruction {
	
	@Override
	public void run(ChickenExecuter exe) {
		
		List<Integer> stack = exe.stack;
		int addres = stack.get(stack.size() - 1); // get last
		int toStore = stack.get(stack.size() - 2); // get second last
		
		// remove last two numbers
		stack.remove(stack.size() - 1);
		stack.remove(stack.size() - 1);
		// add new one.
		stack.add(addres, toStore);
		
		
	}
	
}
