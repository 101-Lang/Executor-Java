package special.lang.chicken.instructions;

import special.lang.chicken.ChickenExecuter;
import special.lang.chicken.Instruction;

public class Push extends Instruction{

	@Override
	public void run(ChickenExecuter exe) {
		exe.stack.add(exe.chickensOnCurrentLine-10);
	}
	
}
