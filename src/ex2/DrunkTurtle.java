package ex2;

import Turtle.SimpleTurtle;

public class DrunkTurtle extends SimpleTurtle
{
	private final int DEVIATION = 30;
	
	/**
	 * Moves a drunk turtle with deviation of thirty degrees left or right.
	 * @param d - distance to move forward.
	 */
	@Override
	public void moveForward(double d) 
	{
		int dir = (int)(Math.random() * 2);//Random the direction
		int dev = (int)(Math.random() * (DEVIATION));//Random deviation
		if(dir == 1)
			super.turnLeft(dev);
		else
			super.turnRight(dev);
		double rand_distance = (Math.random() * (d*2));//Random the distance * 2
		super.moveForward(rand_distance);
	}
}

