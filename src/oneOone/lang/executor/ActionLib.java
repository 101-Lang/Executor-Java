package oneOone.lang.executor;

public class ActionLib {
	
	public static Action mathAdd = new Action() {
		
		@Override
		public void run(Executer exe) {
			int a = exe.getLastStackNumber();
			int b = exe.getSecondLastStackNumber();
			exe.removeLastStackNumber();
			exe.removeLastStackNumber();
			exe.stack.add(a + b);
		}
	};
	
	public static Action mathSub = new Action() {
		
		@Override
		public void run(Executer exe) {
			int a = exe.getLastStackNumber();
			int b = exe.getSecondLastStackNumber();
			exe.removeLastStackNumber();
			exe.removeLastStackNumber();
			exe.stack.add(a - b);
		}
	};
	
	public static Action mathMul = new Action() {
		
		@Override
		public void run(Executer exe) {
			int a = exe.getLastStackNumber();
			int b = exe.getSecondLastStackNumber();
			exe.removeLastStackNumber();
			exe.removeLastStackNumber();
			exe.stack.add(a * b);
		}
	};
	
	public static Action mathDev = new Action() {
		
		@Override
		public void run(Executer exe) {
			int a = exe.getLastStackNumber();
			int b = exe.getSecondLastStackNumber();
			exe.removeLastStackNumber();
			exe.removeLastStackNumber();
			exe.stack.add(a / b);
		}
	};
	
	public static Action compareBigger = new Action() {
		
		@Override
		public void run(Executer exe) {
			int a = exe.getLastStackNumber();
			int b = exe.getSecondLastStackNumber();
			exe.stack.add(a > b ? 1 : 0);
		}
	};
	
	public static Action compareSmaller = new Action() {
		
		@Override
		public void run(Executer exe) {
			int a = exe.getLastStackNumber();
			int b = exe.getSecondLastStackNumber();
			exe.stack.add(a < b ? 1 : 0);
		}
	};
	
	public static Action compareEquals = new Action() {
		
		@Override
		public void run(Executer exe) {
			int a = exe.getLastStackNumber();
			int b = exe.getSecondLastStackNumber();
			exe.stack.add(a == b ? 1 : 0);
		}
	};
	
	public static Action compareNotEquals = new Action() {
		
		@Override
		public void run(Executer exe) {
			int a = exe.getLastStackNumber();
			int b = exe.getSecondLastStackNumber();
			exe.stack.add(a != b ? 1 : 0);
		}
	};
	
	public static Action jumpTo = new Action() {
		
		@Override
		public void run(Executer exe) {
			int addres = exe.getLastStackNumber();
			int condition = exe.getSecondLastStackNumber();
			
			if (condition == 1)
				exe.lineNumber = addres - 1;
			exe.removeLastStackNumber();
			exe.removeLastStackNumber();
		}
	};
	
	public static Action outChar = new Action() {
		
		@Override
		public void run(Executer exe) {
			System.out.println("Out: " + (char) exe.getLastStackNumber());
			exe.removeLastStackNumber();
		}
	};
	
	public static Action outInt = new Action() {
		
		@Override
		public void run(Executer exe) {
			System.out.println("Out: " + exe.getLastStackNumber());
			exe.removeLastStackNumber();
		}
	};
	
	public static Action duplicate = new Action() {
		
		@Override
		public void run(Executer exe) {
			exe.stack.add(exe.getLastStackNumber());
		}
	};
	
	public static Action remove = new Action() {
		
		@Override
		public void run(Executer exe) {
			exe.removeLastStackNumber();
		}
	};
	
}
