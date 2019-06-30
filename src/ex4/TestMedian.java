package ex4;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestMedian
{
	@BeforeEach
	void setUp() throws Exception 
	{
		System.out.println("Starting tests...");
	}

	void check_result(int size)
	{
		int a[] = new int[size];
		int b[] = new int[size];
		
		for (int i = 0; i < size; i++)
		{
			a[i] = (int)(Math.random() * size);
			b[i] = (int)(Math.random() * size);
		}
		
		Arrays.sort(a);
		Arrays.sort(b);
		
		int [] ans = BigThanMedian.bigThanMedianMerge(a, b);
		for (int i = 0; i < ans.length; i++) 
		{
			//Check that every item is higher then third the size of the array.
			assertTrue(ans[i] > size/3);//Even though it's possible, very small chance
		}
		ans = BigThanMedian.bigThanMedianAlgo(a, b);
		for (int i = 0; i < ans.length; i++) 
		{
			assertTrue(ans[i] > size/3);//Even though it's possible, very small chance
		}
	}
	@Test
	void test() 
	{
		int size = 1000000;//1 million starts
		
		for (int i = 0; i < 5; i++) 
		{
			System.out.format("%d items:\n", size);
			check_result(size);
			size *= 2;
		}
	}

}
