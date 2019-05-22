package ex3;

/**
 * Represent a task for counting lines. Implements Runnable and used by threads
 * countLinesThreads.
 * @author Dekel
 *
 */
public class LineCounter implements Runnable
{
	private String file_name;
	private volatile static Integer lines_counter = 0;//We don't want the compiler to optimize it out
	
	/**
	 * Getter for lines_counter.
	 * @return
	 */
	public static int get_num_lines() {return lines_counter;} 
	
	/**Constructor
	 * 
	 * @param file_name - file name.
	 */
	public LineCounter(String file_name)
	{
		this.file_name = file_name;
	}
	
	@Override
	public void run() 
	{	
		int result = Util.calc_num_lines(file_name);
		synchronized (lines_counter) 
		{
			lines_counter += result;
		}
	}
}
