package ex2;

public class JumpyTurtle extends SmartTurtle 
{
	private final int COEFFICIENT = 10;
	
	/**
	 * Moves a jumpy turtle forward with jumping
	 * @param d - distance to move forward.
	 */
	@Override
	public void moveForward(double d)
	{
		if(!tail_up)//If tail down
		{
			for (int i = 0; i < COEFFICIENT; i++) 
			{
				if(tail_up)
					tailDown();
				else
					tailUp();
				super.moveForward(d/COEFFICIENT);
			}
		}
		else
			for (int i = 0; i < COEFFICIENT; i++) 
				super.moveForward(d/COEFFICIENT);
	}
} 
