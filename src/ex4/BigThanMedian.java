package ex4;

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
			final int inner_i = i;//Since i can't be used inside anonymous inner class. 
			//Using lambda expressions :)
			Runnable r = ()-> {
				for (int j = (ans.length/num_threads)*inner_i; j < (ans.length/num_threads)*(inner_i+1); j++) 
				{
					ans[j] = Math.max(a[j], b[a.length - j - 1]);
				}
			};
			threads[i] = new Thread(r);
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