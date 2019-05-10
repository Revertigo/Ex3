package ex3;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Ex3A
{
	static class Task implements Callable<Boolean>
	{
		private long n;
		public Task(long n)
		{
			this.n = n;
		}
		
		@Override
		public Boolean call() throws Exception 
		{
			return Boolean.valueOf(isPrime(n));
		}
		
	}
	
	public boolean isPrime(long n, double maxTime) throws RuntimeException
	{
		ExecutorService executor = Executors.newSingleThreadExecutor();//Creating thread pool.
		Task t = new Task(n);//Create new Task for check prime number
		Future<Boolean> future = executor.submit(t);//Handle return value of the task(in the future)
		
		long time_to_long = (long)(maxTime * 1000);//Convert maxTime into milliseconds.
		Boolean result = null;
		try {
			result = future.get(time_to_long, TimeUnit.MILLISECONDS);//If we didn't get result after timeout exception thrown.
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			future.cancel(true);//Cancel the task
			e.printStackTrace();
		}

		return result;
	}
	
	 public static boolean isPrime(long n)
	 {
		 boolean ans=true;
		 if(n<2)throw new RuntimeException("ERR: the parameter to the isPrime function must be > 1 (got "+n+")!");
		 int i=2;
		 double ns=Math.sqrt(n);
		 while(i<=ns&&ans){
		 if (n%i==0) ans=false;
		 i=i+1;
		 }
		 if(Math.random()<Ex3A_tester.ENDLESS_LOOP)while(true);
		 return ans;
	 }
}
