package ex2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;

public class TestSquareEquation 
{
	@Before
	public void setUp()
	{
		System.out.println("Starting the test...");
	}
	
	@org.junit.Test(expected = SquareEquationException.class)
	public void test1() throws SquareEquationException  
	{
		SquareEquationException.square_equation(-2.3, 5.1, -12.62);
	}
	
	@org.junit.Test(expected = SquareEquationException.class)
	public void test2() throws SquareEquationException  
	{
		SquareEquationException.square_equation(0, 0, 0);
	}
	
	@org.junit.Test(expected = SquareEquationException.class)
	public void test3() throws SquareEquationException  
	{
		SquareEquationException.square_equation(0, 0, 82.1);
	}
	
	@org.junit.Test
	public void test4() throws SquareEquationException  
	{
		assertEquals(SquareEquationException.square_equation(-2.3, 5.1, 12.98), new SquareEquationException.Roots(-1.51, 3.73)); 
	}
	
	@org.junit.Test
	public void test5() throws SquareEquationException  
	{
		assertEquals(SquareEquationException.square_equation(1, -2, 1), new SquareEquationException.Roots(1, 1)); 
	}
	
	@org.junit.Test
	public void test6() throws SquareEquationException  
	{
		assertEquals(SquareEquationException.square_equation(0, 2, 5), new SquareEquationException.Roots(-2.5, -2.5)); 
	}
	
	@org.junit.Test
	public void test7() throws SquareEquationException  
	{
		assertEquals(SquareEquationException.square_equation(1, 0, 0), new SquareEquationException.Roots(0, 0)); 
	}
	
	@org.junit.Test
	public void test8() throws SquareEquationException  
	{
		assertEquals(SquareEquationException.square_equation(1, -5, 6), new SquareEquationException.Roots(3, 2)); 
	}
	
	@After
	public void tearDown()
	{
		System.out.println("Done");
	}

}
