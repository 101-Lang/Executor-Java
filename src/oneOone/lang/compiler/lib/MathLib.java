package oneOone.lang.compiler.lib;

import java.util.Arrays;
import java.util.Collection;

import oneOone.lang.compiler.Statement;

public class MathLib {
	
	public static Statement add = new Statement() {
		
		@Override
		public String getPrefix() {
			return "Math.add";
		}
		
		@Override
		public String compile(String line) {
			return "11000";
		}
	};
	public static Statement subtract = new Statement() {
		
		@Override
		public String getPrefix() {
			return "Math.subtract";
		}
		
		@Override
		public String compile(String line) {
			return "11001";
		}
	};
	
	public static Statement multiply = new Statement() {
		
		@Override
		public String getPrefix() {
			return "Math.multiply";
		}
		
		@Override
		public String compile(String line) {
			return "11010";
		}
	};
	
	public static Statement devide = new Statement() {
		
		@Override
		public String getPrefix() {
			return "Math.devide";
		}
		
		@Override
		public String compile(String line) {
			return "11011";
		}
	};

	public static Collection<? extends Statement> getAll() {
		return Arrays.asList(add, subtract, multiply, devide);
	}
	
}
