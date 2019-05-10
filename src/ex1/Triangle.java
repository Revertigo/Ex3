package ex1;

public class Triangle implements Drawable
{
	private Point p1;
	private Point p2;
	private Point p3;
	
	/**
	 * Constructor
	 * @param p1 first point
	 * @param p2 second point
	 * @param p3 third point
	 */
	public Triangle(Point p1, Point p2, Point p3) 
	{
		this.p1 = new Point(p1);
		this.p2 = new Point(p2);
		this.p3 = new Point(p3);
	}
	
	/**
	 * Copy constructor.
	 * @param other triangle to copy.
	 */
	public Triangle(Triangle other){this(other.p1, other.p2, other.p3);}
	
	//Getters
	public Point p1() {return p1;}
	public Point p2() {return p2;}
	public Point p3() {return p3;}
	
	@Override
	public boolean equals(Drawable d)
	{
		if(d instanceof Triangle)
		{
			Triangle temp = (Triangle)d;
			/*
			 * Just comparing whether the three points are match.
			 * The points might not be constructed in the same order.
			 */
			return 	(p1.equals(temp.p1) || p1.equals(temp.p2) || p1.equals(p3)) && 
					(p2.equals(temp.p1) || p2.equals(temp.p2) || p2.equals(temp.p3)) && 
					(p3.equals(temp.p1) || p3.equals(temp.p2) || p3.equals(temp.p3));
		}
		
		return false;
	}

	@Override
	public boolean contains(Point p)
	{
		if(p != null)
		{
			Triangle t1 = new Triangle(p1, p2, p);
			Triangle t2 = new Triangle(p1, p, p3);
			Triangle t3 = new Triangle(p, p2, p3);
			
			//The point is inside the triangle only if the area of the three sub-triangles equals the triangle.
			return this.area() == (t1.area() + t2.area() + t3.area());
		}
		
		return false;
	}

	@Override
	public double perimeter() 
	{
		//Just sum up all three distances between p1,p2 and p3.
		return p1.distance(p2) + p1.distance(p3) + p2.distance(p3);
	}

	@Override
	public double area() 
	{
		//Calculation based on the Internet. Source: https://www.youtube.com/watch?v=H9qu9Xptf-w
		return Math.abs((p1.x() * (p2.y() - p3.y()) 
				+ p2.x() * (p3.y() - p1.y()) + 
				  p3.x() * (p1.y() - p2.y()))/2.0);
	}

	@Override
	public void translate(Point p) 
	{
		//Translate triangle using Point translate.
		p1.translate(p);
		p2.translate(p);
		p3.translate(p);	
	}
	
	@Override
	public Drawable clone() 
	{
		return new Triangle(this);
	}
}
