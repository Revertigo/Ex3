package ex3;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Ex3B
{
	public static String[] createFiles(int n)
	{
		String [] files = new String[n];
		String input = "Hello World";
		
		Random rand = new Random(123);
		for (int i = 0; i < files.length; i++) 
		{
			int num_lines = rand.nextInt(1000);//Number of lines per file
			files[i] = "File_" + i + ".txt";
			FileWriter fw = null; 
			BufferedWriter bw = null;
			try {
				fw = new FileWriter(files[i]);
				bw = new BufferedWriter(fw);
				
				for (int j = 0; j < num_lines; j++) 
				{
					bw.write(input);
					bw.newLine();
				}
				
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return files;
	}
	
	public static void deleteFiles(String[] fileNames)
	{
		for (int i = 0; i < fileNames.length; i++) 
		{
			File file = new File(fileNames[i]);
			file.delete();
		}
	}
	
	//************á1************
	public static void countLinesThreads(int numFiles)
	{
		String [] files = createFiles(numFiles);
		LineCounter [] lcs = new LineCounter[numFiles];
		Thread [] threads = new Thread[numFiles];
		
		long start_time = System.currentTimeMillis();
		for (int i = 0; i < numFiles; i++)
		{
			lcs[i] = new LineCounter(files[i]);
			threads[i] = new Thread(lcs[i]);//Create a thread for it.
			threads[i].start();
		}
		
		//Just wait up for all threads to finish their work
		for (int i = 0; i < numFiles; i++)
		{
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long estimated_time = System.currentTimeMillis() - start_time;
		
		//Prevent printing inside time measurement(printing is an expensive operation)
		System.out.println("Run time: " + estimated_time + "ms");
		System.out.println("Total number of lines of all files: " + LineCounter.getNum_liness());
		deleteFiles(files);
	}
	
	
	private static class FileThread implements Callable<Integer>
	{
		private String file;
		
		public FileThread(String file) 
		{
			this.file = file;
		}
		
		@Override
		public Integer call() throws Exception 
		{	
			return calc_num_lines(file);
		}		
	}
	
	//************á2************
	public static void countLinesThreadPool(int num)
	{
		String [] files = createFiles(num);
		int total_lines = 0;
		
		//source: http://www.javapractices.com/topic/TopicAction.do?Id=247
		ExecutorService executor = Executors.newFixedThreadPool(5);//Creating thread pool.
		CompletionService<Integer> compService = new ExecutorCompletionService<>(executor);//Add completion service
		long start_time = System.currentTimeMillis();
		
		for (int i = 0; i < files.length; i++)
		{
			FileThread ft = new FileThread(files[i]);//Create new Task for counting lines
			compService.submit(ft);//Insert the task into the pool
		}
		
		for (int i = 0; i < files.length; i++) 
		{
			try {
				Future<Integer> future = compService.take();//get the return value
				total_lines += future.get();
			}catch (InterruptedException | ExecutionException e) {
			    e.printStackTrace();
			}	
		}
		
		long estimated_time = System.currentTimeMillis() - start_time;
		System.out.println("Run time: " + estimated_time + "ms");
		System.out.println("Total number of lines of all files: " + total_lines);
		executor.shutdown();
		deleteFiles(files);
	}
	
	//************á3************
	public static void countLinesOneProcess(int numFiles)
	{
		String [] files = createFiles(numFiles);
		int total_lines = 0;
		
		long start_time = System.currentTimeMillis();
		
		for (int i = 0; i < files.length; i++)
		{	
			total_lines += calc_num_lines(files[i]);
		}
		
		long estimated_time = System.currentTimeMillis() - start_time;
		System.out.println("Run time: " + estimated_time + "ms");
		System.out.println("Total number of lines of all files: " + total_lines);
		deleteFiles(files);
	}
	
	static int calc_num_lines(String path)
	{
		//Source: https://stackoverflow.com/questions/453018/number-of-lines-in-a-file-in-java
	    try {
			 InputStream is = new BufferedInputStream(new FileInputStream(path));
	        byte[] c = new byte[1024];
	        int count = 0;
	        int readChars = 0;
	        boolean empty = true;
	        while ((readChars = is.read(c)) != -1) 
	        {
	            empty = false;
	            for (int i = 0; i < readChars; ++i) {
	                if (c[i] == '\n')
	                    ++count;
	            }
	        }
	        is.close();
	        return (count == 0 && !empty) ? 1 : count;
	    }catch (IOException e) {
			e.printStackTrace();
	    }
	    
	    return 0;
	}
	
	public static void main(String[]args)
	{
		countLinesThreads(5);
		countLinesThreadPool(5);
		countLinesOneProcess(5);
	}
}
