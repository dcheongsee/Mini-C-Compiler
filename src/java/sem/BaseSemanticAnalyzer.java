package sem;


import util.CompilerPass;

/**
 * 
 * @author dhil
 * A base class providing basic error accumulation.
 */
public abstract class BaseSemanticAnalyzer extends CompilerPass {


	protected void error(String message) {
		System.out.println("semantic error: " + message);
		incError();
	}

}
