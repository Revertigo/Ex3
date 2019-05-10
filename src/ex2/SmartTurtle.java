package ex2;

import Turtle.SimpleTurtle;

public class SmartTurtle extends SimpleTurtle
{
	protected boolean tail_up;//Whether the tail up or down
	
	/**
	 * The function makes the turtle to draw a square.
	 * @param size - size of the square.
	 */
	public void drawSquare (double size) 
	{
		//Basically a square is type of polygon with 4 equals sides.
		drawPolygon(4, size);
	}
	
	/**
	 * The function makes the turtle to draw a polygon.
	 * @param sides - how many sides the polygon has.
	 * @param size - the size of the polygon.
	 */
	public void drawPolygon (int sides, double size)
	{
		//First we have to put his tail down if it is up.
		boolean tail_state = tail_up;
		tailDown();
		for (int i = 0; i < sides; i++) 
		{
			this.moveForward(size);
			this.turnRight(360/sides);
		}
		
		if(tail_state)//We don't want to lose the original state of the tail
			tailUp();
	}
	
	/**
	 * The function sets the tail state and the tail up.
	 */
	@Override
	public void tailUp() 
	{
		tail_up = true;
		super.tailUp();
	}
	
	/**
	 * The function sets the tail state and the tail down.
	 */
	@Override
	public void tailDown() 
	{
		tail_up = false;
		super.tailDown();
	}
}
