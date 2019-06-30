package ex4;

import java.util.Arrays;

public class BigThanMedian
{
	
	/**
	 * The function gets two sorted arrays(increasing order) and
	 * return result array with all items above median using a maximum
	 * method algorithm
	 * @param a - first array
	 * @param b - second array
	 * @return merged array
	 */
	public static int[] bigThanMedianAlgo(int []a, int []b)
	{
		int [] ans = new int[a.length];
		int num_threads = 4;
		Thread [] threads = new Thread[num_threads];
		
		long start_time = System.currentTimeMillis();
		
		for (int i = 0; i < threads.length; i++)
		{
			threads[i] = new WorkerThread(a,b,ans, (ans.length/num_threads)*i, (ans.length/num_threads)*(i+1));
			threads[i].start();
		}
		
		for (int i = 0; i < threads.length; i++)
		{
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		long estimated_time = System.currentTimeMillis() - start_time;

		System.out.println("Run time: worker threads " + estimated_time + "ms");
		
		return ans;
	}
	
	/**
	 * The function gets two sorted arrays(increasing order) and
	 * return result array with all items above median using a regular
	 * merge.
	 * @param a - first array
	 * @param b - second array
	 * @return merged array
	 */
	public static int[] bigThanMedianMerge(int[]a, int[] b)
	{
		int [] ans = new int[a.length];
		
		long start_time = System.currentTimeMillis();
		
		for(int i = a.length-1, j = b.length-1, r = 0; r < ans.length; r++)
		{
			if(a[i] > b[j])
			{
				ans[r] = a[i--];
			}
			else
			{
				ans[r] = b[j--];
			}
		}

		long estimated_time = System.currentTimeMillis() - start_time;

		System.out.println("Run time: Regular merge " + estimated_time + "ms");
		return ans;
	}
}