package ex2;

import java.util.Scanner;

public class SquareEquationException extends Exception
{
	public SquareEquationException(String message)
	{
		super(message);
	}
	
	static class Roots
	
	{
		private final double EPSILON = 0.01;//This is the maximum different between expected value and actual value.
		
		double x1;
		double x2;
		
		public Roots(double x1, double x2)
		{
			this.x1 = x1;
			this.x2 = x2;
		}
		
		@Override
		public boolean equals(Object arg0) 
		{
			Roots other = (Roots)arg0;
			return (Math.abs(this.x1 - other.x1) <= EPSILON && Math.abs(this.x2 - other.x2) <= EPSILON);
		}
	}
	
	/**
	 * The functions gets a coefficient of square equation calculates and returns the results.
	 * @param a - X^2 coefficient
	 * @param b - x coefficient
	 * @param c - free number coefficient
	 * @return the results or throws exception.
	 * @throws SquareEquationException
	 */
	static Roots square_equation(double a, double b, double c) throws SquareEquationException
	{
		if(a == 0 && b == 0 && c == 0)
			throw new SquareEquationException("x can be any number - trivial!");
		else if(a == 0 && b == 0 && c != 0)
			throw new SquareEquationException("Error, no answer!!");
		else if(a==0)//Linear equation
		{
			System.out.format("x1:%.2f\n",c/-b);//b isn't equals zero
			return new Roots(c/-b, c/-b);
		}
		else
		{
			//Src: https://www.programiz.com/java-programming/examples/quadratic-roots-equation
			double determinant = b * b -4 * a * c;	
			if(determinant > 0)
			{
				double root1 = (-b + Math.sqrt(determinant)) / (2 * a);
	            double root2 = (-b - Math.sqrt(determinant)) / (2 * a);
	            
	            System.out.format("x1:%.2f, x2:%.2f\n", root1 , root2);
	            return(new Roots(root1, root2));
			}
			else if(determinant == 0)
			{
				double root1 = -b / (2 * a);
				System.out.format("x1 = x2: %.2f\n", root1);
				return(new Roots(root1, root1));
			}
			else
				throw new SquareEquationException("Error: No real roots!");
		}
	}
	
	/**
	 * The program frame. Get a user inputs and calculate the results.
	 */
	static void program_frame()
	{
		Scanner reader = new Scanner(System.in); //Reading from System.in
		System.out.println("Enter 0 or any number to Exit or 1 to solve aX^2+bX+c=0:");
		int choice = reader.nextInt(); 
		
		while(choice == 1)
		{
			System.out.println("aX^2+bX+c=0: Enter a,b,c:");
			System.out.print("Enter a: ");
			double a = reader.nextDouble();
			System.out.print("Enter b: ");
			double b = reader.nextDouble();
			System.out.print("Enter c: ");
			double c = reader.nextDouble();
			System.out.format("%.1fX^2 + %.1fX + %.1f = 0:\n", a, b ,c);

			try {
				square_equation(a, b, c);
			} catch (SquareEquationException e) {
				e.printStackTrace();
			}
			
			System.out.println("Enter 0 or any number to Exit or 1 to solve aX^2+bX+c=0:");
			choice = reader.nextInt(); 
		}
		System.out.println("Bye-bye!");
	}
	
	public static void main(String[] args)
	{
		program_frame();
	}
}
