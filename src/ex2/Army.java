package ex2;

import java.util.Scanner;

import Turtle.SimpleTurtle;
public class Army 
{
	private final int ARMY_SIZE = 5;
	private SimpleTurtle [] army;
	
	/**
	 * Public constructor for Army
	 */
	public Army() 
	{
		army = new SimpleTurtle[ARMY_SIZE];
	}
	
	/**
	 * Get user input for building a army of turtles
	 */
	void build_army()
	{	
		Scanner reader = new Scanner(System.in); //Reading from System.in
		
		for (int i = 0; i < this.army.length; i++) 
		{
			System.out.println("Choose the type of a turtle:\r\n" + 
								"1. Simple\r\n" + 
								"2. Smart\r\n" + 
								"3. Drunk\r\n" + 
								"4. Jumpy");
			
			int choice;
			do
			{
				choice = reader.nextInt(); // Scans the next token of the input as an int
				if(choice < 1 || choice > 4)
					System.out.println("Wrong option, try again");
			}while(choice < 1 || choice > 4);
			switch(choice)
			{
				case 1:
				{
					this.army[i] = new SimpleTurtle();
					break;
				}
				
				case 2:
				{
					this.army[i] = new SmartTurtle();
					break;
				}
				
				case 3:
				{
					this.army[i] = new DrunkTurtle();
					break;
				}
				
				case 4:
				{
					this.army[i] = new JumpyTurtle();
					break;
				}
			}
		}
		
		reader.close();//Close the input stream
	}
	
	/**
	 * Move all the turtles together according to the instructions
	 */
	void move_turtules()
	{
		/* Now Let's move them all !
		 * Since we have to move them together, we have to do it in different loops.
		 * Polymorphism help us to move the different turtles forward, turn right, set their tail down
		 * and disappear as they should without knowing the type of the turtle !
		 */
		
		for (int i = 0; i < this.army.length; i++)//Tail down 
			this.army[i].tailDown();
		for (int i = 0; i < this.army.length; i++)//Move forward 100
			this.army[i].moveForward(100);
		for (int i = 0; i < this.army.length; i++)//Turn right 90 degrees
			this.army[i].turnRight(90);
		for (int i = 0; i < this.army.length; i++)//Move forward 60
			this.army[i].moveForward(60);
		for (int i = 0; i < this.army.length; i++)//Paint polygon if it smart turtle
		{
			if(this.army[i] instanceof SmartTurtle)//No choice, we have to verify it's a smart turtle
				((SmartTurtle)this.army[i]).drawPolygon(6, 70);//We have to cast since only smart turtle know to paint polygons
		}
		
		for (int i = 0; i < this.army.length; i++)//Disappear
			this.army[i].hide();
	}
	
	public static void main(String[] args) 
	{	
		Army ar = new Army();
		ar.build_army();
		ar.move_turtules();
	}
}
