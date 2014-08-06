package oneOone.lang.common;

import oneOone.lang.common.exception.DecompileException;

public abstract class IDecompiler {
	
	public abstract String decompile(String code) throws DecompileException;
	
	public abstract String decompileCommand(String code) throws DecompileException;
	
}
