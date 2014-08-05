package oneOone.lang.compiler.lib;

import java.util.Arrays;
import java.util.Collection;

import oneOone.lang.compiler.Statement;

public class CompareLib {
	
	public static Statement smaller = new Statement() {
		
		@Override
		public String getPrefix() {
			return "Compare.smaller";
		}
		
		@Override
		public String compile(String line) {
			return "10101";
		}
	};
	
	public static Statement bigger = new Statement() {
		
		@Override
		public String getPrefix() {
			return "Compare.bigger";
		}
		
		@Override
		public String compile(String line) {
			return "10100";
		}
	};
	
	public static Statement equals = new Statement() {
		
		@Override
		public String getPrefix() {
			return "Compare.equals";
		}
		
		@Override
		public String compile(String line) {
			return "10110";
		}
	};
	
	public static Statement notEquals = new Statement() {
		
		@Override
		public String getPrefix() {
			return "Compare.notEquals";
		}
		
		@Override
		public String compile(String line) {
			return "10111";
		}
	};
	
	public static Collection<? extends Statement> getAll() {
		return Arrays.asList(smaller, bigger, equals, notEquals);
	}
	
}
