package ex3;

public class LineCounter implements Runnable
{
	private String file_name;
	private volatile static Integer line_counters = 0;//We don't want the compiler to optimize it out
	
	public static int getNum_liness() {return line_counters;} 
	
	public LineCounter(String file_name)
	{
		this.file_name = file_name;
	}
	
	@Override
	public void run() 
	{	
		int result = Ex3B.calc_num_lines(file_name);
		synchronized (line_counters) {
			line_counters += result;
		}
	}
}
