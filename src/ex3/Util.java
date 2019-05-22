package ex3;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * This class contains a utility functions.
 * @author Dekel
 *
 */
public class Util
{
	/**
	 * The function gets a path to file and counting number of lines in it.
	 * @param path - path to the file.
	 * @return number of lines inside.
	 */
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
}
