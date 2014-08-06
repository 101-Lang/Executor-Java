package oneOone.lang;

import oneOone.lang.common.Common;
import oneOone.lang.common.ICommand;
import oneOone.lang.common.IDecompiler;
import oneOone.lang.common.exception.DecompileException;

public class Decompiler extends IDecompiler{

	public Decompiler(){
		Common.init();
	}
	
	@Override
	public String decompile(String in) throws DecompileException {
		String[] codes = in.replaceAll("\\s+", " ").split(" ");
		String out = "";
		int codeNr = 1;
		for(String code : codes){
			try{
				out += decompileCommand(code) + ";" + System.lineSeparator();
			}catch(Exception e){
				throw new DecompileException(codeNr, e.getMessage());
			}
			codeNr++;
		}
		return out;
	}

	@Override
	public String decompileCommand(String code) throws DecompileException {
		for(ICommand command : Common.commands){
			if(code.toLowerCase().startsWith(command.getCompiledPrefix().toLowerCase()))
				return command.decompile(this, code);
		}
		throw new IllegalArgumentException("could not decompile command: '" + code + "'");
	}
	
	public static void main(String[] args) {
		if(args.length == 0)
			throw new IllegalArgumentException("The first argument must be the input.");
		try {
			System.out.println(new Decompiler().decompile(args[0]));
		} catch (DecompileException e) {
			e.printStackTrace();
		}
	}
}
