package ex1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class TestBet 
{
	private static TreeMap<String, Integer> tm;//TreeMap for saving all different words and their counters.
	private static int words_counter;
	private static int different_words_counter;
	private static String longest_word;
	
	/*
	 * Build up a sorted TreeMap in order to get most frequent word easily. 
	 * Source: https://stackoverflow.com/questions/2864840/treemap-sort-by-value
	 */
	private static <K,V extends Comparable<? super V>> SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
            new Comparator<Map.Entry<K,V>>() {
                @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                    int res = e1.getValue().compareTo(e2.getValue());
                    return res != 0 ? res : 1; // Special fix to preserve items with equal values
                }
            }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }
	public static void handle_line(String line)
	{
		StringTokenizer st = new StringTokenizer(line, ",:;.()-/ ");
		
		while(st.hasMoreTokens())
		{
			words_counter++;//another word
			
			String key = st.nextToken();
			Integer temp = tm.get(key);
			Integer value = Integer.valueOf(1);
			//Checking whether the words already mapped or not
			if(temp != null) 
				value = temp.intValue() + 1;//If it does, just increasing it's counter by one.
			else
				different_words_counter++;
			
			//Check whether it's the longest word so far
			if(key.length() > longest_word.length())
				longest_word = key;
			
			tm.put(key, value);
		} 
	}
	
	public static void words_in_file()
	{
		System.out.println("The Number Of Words: " + words_counter);
	}
	
	public static void different_words_in_file()
	{
		System.out.println("The Number Of Differnt Words: " + different_words_counter);
	}
	
	public static void most_frequent_word()
	{
		SortedSet<Map.Entry<String,Integer>> res = entriesSortedByValues(tm);//Create sorted TreeMap
		Entry<String, Integer> entry = res.last();//Get last entry.
		System.out.println(String.format("The Most Frequent Word: (%s), frequency: %d", entry.getKey(), entry.getValue()));
	}
	
	public static void longest_word_in_file()
	{
		System.out.println(String.format("The Longest word:\n(%s)", longest_word));
	}
	
	public static void readFile(String fileName)
	{
		//Initialise variables.
		tm = new TreeMap<String, Integer>();
		words_counter = 0;
		different_words_counter = 0;
		longest_word = "";
		
		try { // try read from the file
				FileReader fr = new FileReader(fileName);
				BufferedReader br = new BufferedReader(fr);
				String str = "";
				while(str!= null)
				{
					str = br.readLine();
					if (str != null)
					{
						handle_line(str);
					}
				}
				br.close();
			}
		catch(IOException ex) {
			 System.out.print("Error reading file\n" + ex);
			 System.exit(2);
		 }
		
		different_words_in_file();
		words_in_file();
		most_frequent_word();
		longest_word_in_file();
	}
}
