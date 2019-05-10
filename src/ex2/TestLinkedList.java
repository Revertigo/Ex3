package ex2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ListIterator;

import org.junit.After;
import org.junit.Before;

public class TestLinkedList {

	private final double EPSILON = 0.01;//This is the maximum different between expected value and actual value.
	
	@Before
	public void setUp()
	{
		System.out.println("Starting the test...");
	}
	
	@After
	public void tearDown()
	{
		System.out.println("Done");
	}
	
	@org.junit.Test()
	public void test()
	{
		LinkedListDouble<String> lld = new LinkedListDouble<String>();
		lld.add("a");
		lld.add("aa");
		lld.add("bb");
		assertEquals(lld.size(), 3);
		
		ListIterator<String> li = lld.listIterator();
		assertEquals(li.next().toString(), "a");
		assertEquals(li.next().toString(), "aa");
		
		assertEquals(li.previous().toString(), "aa");
		assertEquals(li.previous().toString(), "a");
		assertEquals(li.hasPrevious(), false);
		assertEquals(li.hasNext(), true);
		
		assertEquals(li.next().toString(), "a");
		assertEquals(li.next().toString(), "aa");
		assertEquals(li.hasPrevious(), true);
		
		assertEquals(li.next().toString(), "bb");
		assertEquals(li.hasNext(), false);

		assertEquals(lld.getLast().toString(), "bb");
	}
	
	@org.junit.Test()
	public void test1()
	{
		LinkedListDouble<Double> lld = new LinkedListDouble<Double>();
		double sum = 1.0;
		for (int i = 0; i < 100; i++) 
			lld.add(Double.valueOf(sum++));
		
		assertEquals(lld.size(), 100);
		ListIterator<Double> li = lld.listIterator();
		sum = 1.0;
		while(li.hasNext())
			assertEquals(Double.valueOf(sum++), li.next(), EPSILON);
	}

}
