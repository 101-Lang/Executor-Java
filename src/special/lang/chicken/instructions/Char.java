package special.lang.chicken.instructions;

import java.util.List;

import special.lang.chicken.ChickenExecuter;
import special.lang.chicken.Instruction;

public class Char extends Instruction{

	@Override
	public void run(ChickenExecuter exe) {
		List<Integer> stack = exe.stack;
		int a = stack.get(stack.size() - 1); // get last
		
		// remove last two numbers
		stack.remove(stack.size() - 1);
		
		System.out.print((char)a);
	}
	
}
