package util;

public class CompilerPass {

    protected int numErrors = 0;

    public void incError() { numErrors++;}
    public int getNumErrors() { return numErrors; }

    public boolean hasErrors() { return numErrors > 0;}

}
