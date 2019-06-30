package ex4;

public class WorkerThread extends Thread
{
	private Algo max;//My lambda operation
	
	private int [] a;
	private int [] b;
	private int [] c;//Memory location
	private int start;
	private int end;
	
	/**
	 * Constructor for all class members
	 * @param a
	 * @param b
	 * @param c
	 * @param start
	 * @param end
	 */
	public WorkerThread(int [] a, int [] b, int [] c, int start, int end)
	{
		this.a = a;
		this.b = b;
		this.c = c;
		this.start = start;
		this.end = end;
		
		max = (int A, int B) -> Math.max(A, B);
	}
	
	/**
	 * Calculate results parallelism
	 */
	@Override
	public void run()
	{
		for (int i = start; i < end; i++) 
		{
			c[i] = max.operation(a[i], b[a.length - i - 1]);
		}
	}
	
	//Using Lambda expressions. FunctionalInterface means only one method inside
	@FunctionalInterface
	interface Algo{
		int operation(int a, int b);
	}
}
