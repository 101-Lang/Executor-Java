package special.lang.chicken.instructions;

import special.lang.chicken.ChickenExecuter;
import special.lang.chicken.Instruction;

public class Chicken extends Instruction{

	@Override
	public void run(ChickenExecuter executer) {
		System.out.println("Out: " + ChickenExecuter.CHICKEN);
	}
	
}
