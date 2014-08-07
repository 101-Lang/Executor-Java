package oneOone.lang.common;

/**
 * Every ICommand should be able to run mulaple times!
 * 
 * @author Sinius15
 * @see www.sinius15.com
 */
public abstract class ICommand {
	
	public abstract String getName();
	
	public abstract void execute(IExecutor executer);
	
	public abstract String compile(ICompiler compiler, String code);
	
	public abstract String decompile(IDecompiler decompiler, String binary);
	
	public abstract String getCompiledPrefix();
	
	public abstract String getDecompiledPrefix();
	
	public abstract String getDescription();
}
