package ui;

import java.io.OutputStream;
import java.io.PrintWriter;

public class PrintStreamIntercepte extends PrintWriter {
	
	Log log;
	
	public PrintStreamIntercepte(OutputStream out,Log log)
    {
        super(out, true);
        this.log = log;
        
    }
	
	@Override
    public void print(String s)
    {
    	if(Log.niveauRequis >= log.getNiveau() && log.getActif())
        super.print(s);
    }
}
