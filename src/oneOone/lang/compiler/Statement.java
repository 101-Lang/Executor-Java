package oneOone.lang.compiler;

public interface Statement {
	public String getPrefix();
	public String compile(String line);
}
