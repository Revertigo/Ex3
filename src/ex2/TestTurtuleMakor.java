package ex2;

import Turtle.SimpleTurtle;

public class TestTurtuleMakor {

	public static void main(String[] args)
	{
//		DrunkTurtle bob = new DrunkTurtle();
//		SimpleTurtle bob = new SimpleTurtle();
		JumpyTurtle bob = new JumpyTurtle();
//		SmartTurtle bob = new SmartTurtle();
		
//		bob.hide();
//		bob.tailUp();
//		bob.moveForward(50);
//		bob.turnRight(30);
//		bob.moveForward(40);
//		bob.tailDown();
//		bob.moveForward(25);
//		bob.tailUp();
//		bob.moveForward(40);
//		bob.drawPolygon(11,70.8);
//		bob.turnLeft(90);
//		bob.moveForward(60);
//		bob.show();
		bob.tailUp();
		bob.turnRight(30);
		bob.moveForward(50);
		bob.moveBackward(70);
		bob.tailDown();
		bob.moveBackward(100);
		bob.tailUp();
		bob.turnRight(90);
		bob.moveBackward(50);
	}

}
