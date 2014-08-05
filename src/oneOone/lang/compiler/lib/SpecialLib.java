package oneOone.lang.compiler.lib;

import java.util.Arrays;
import java.util.Collection;

import oneOone.lang.compiler.Statement;

public class SpecialLib {
	
	public static Statement jump = new Statement() {
		
		@Override
		public String getPrefix() {
			return "Jump";
		}
		
		@Override
		public String compile(String line) {
			return "11100";
		}
	};
	
	public static Statement duplicate = new Statement() {
		
		@Override
		public String getPrefix() {
			return "Duplicate";
		}
		
		@Override
		public String compile(String line) {
			return "11101";
		}
	};
	
	public static Statement remove = new Statement() {
		
		@Override
		public String getPrefix() {
			return "Remove";
		}
		
		@Override
		public String compile(String line) {
			return "11110";
		}
	};
	
	public static Statement push = new Statement() {
		
		@Override
		public String getPrefix() {
			return "push ";
		}
		
		@Override
		public String compile(String line) {
			return "0" + Integer.toString(Integer.parseInt(line.substring(5)), 2);
		}
	};
	
	
	public static Collection<? extends Statement> getAll() {
		return Arrays.asList(jump, duplicate, remove, push);
	}
}
