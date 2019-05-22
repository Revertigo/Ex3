package ex3;

import java.util.concurrent.Callable;

/**
 * This class represent a task(using Callable) which counts number of lines in file.
 * Used by countLinesThreadPool.
 * @author Dekel
 *
 */
public class FileThread implements Callable<Integer>
{
	private String file;
	
	public FileThread(String file) 
	{
		this.file = file;
	}
	
	@Override
	public Integer call() throws Exception 
	{	
		return Util.calc_num_lines(file);
	}		
}
