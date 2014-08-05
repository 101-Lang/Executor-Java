package oneOone.lang.compiler.lib;

import java.util.Arrays;
import java.util.Collection;

import oneOone.lang.compiler.Statement;

public class IOLib {
	
	public static Statement outChar = new Statement() {
		
		@Override
		public String getPrefix() {
			return "Out.char";
		}
		
		@Override
		public String compile(String line) {
			return "10000";
		}
	};
	
	public static Statement outInt = new Statement() {
		
		@Override
		public String getPrefix() {
			return "Out.int";
		}
		
		@Override
		public String compile(String line) {
			return "10001";
		}
	};
	
	public static Statement inChar = new Statement() {
		
		@Override
		public String getPrefix() {
			return "In.char";
		}
		
		@Override
		public String compile(String line) {
			return "10010";
		}
	};
	
	public static Statement inInt = new Statement() {
		
		@Override
		public String getPrefix() {
			return "In.int";
		}
		
		@Override
		public String compile(String line) {
			return "10011";
		}
	};
	
	public static Collection<? extends Statement> getAll() {
		return Arrays.asList(outChar, outInt, inChar, inInt);
	}
}
