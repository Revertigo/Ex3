package ex3;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Ex3B
{
	/**
	 * The function creates n files with each file has a random number of
	 * lines contains "Hello World".
	 * @param n - number of files to create.
	 * @return arrays which contains all the files created by the function.
	 */
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
	
	/**
	 * The function delete files given as input.
	 * @param fileNames - files's path to delete.
	 */
	public static void deleteFiles(String[] fileNames)
	{
		for (int i = 0; i < fileNames.length; i++) 
		{
			File file = new File(fileNames[i]);
			file.delete();
		}
	}
	
	//************á1************
	/**
	 * The function creates files, thread for every file, counting number of lines in and print it.
	 * @param numFiles - number of files to create.
	 */
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
		System.out.println("Total number of lines of all files: " + LineCounter.get_num_lines());
		deleteFiles(files);
	}
	
	//************á2************
	/**
	 * The function creates file, counting each file the number of lines and prints 
	 * results. Done via Thread pool technique.
	 * @param num - number of file to create.
	 */
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
	/**
	 * The function creates files, counting the number of line in each one of them
	 * and prints results. Done without threads using only main thread.
	 * @param numFiles - number of files to create.
	 */
	public static void countLinesOneProcess(int numFiles)
	{
		String [] files = createFiles(numFiles);
		int total_lines = 0;
		
		long start_time = System.currentTimeMillis();
		
		for (int i = 0; i < files.length; i++)
		{	
			total_lines += Util.calc_num_lines(files[i]);
		}
		
		long estimated_time = System.currentTimeMillis() - start_time;
		System.out.println("Run time: " + estimated_time + "ms");
		System.out.println("Total number of lines of all files: " + total_lines);
		deleteFiles(files);
	}
	
	public static void main(String[]args)
	{
		countLinesThreads(1000);
		countLinesThreadPool(1000);
		countLinesOneProcess(1000);
	}
}
