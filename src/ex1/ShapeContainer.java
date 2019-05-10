package ex1;
import java.util.ArrayList;

public class ShapeContainer
{
	public static final int INIT_SIZE=10;
	public static final int RESIZE=10;
	
	private ArrayList<Drawable> al;//Dynamically allocated array list.
	private int t_size;
	private int r_size;
	/**
	 * Default constructor
	 */
	public ShapeContainer() 
	{
		t_size = 0;
		r_size = 0;
		al = new ArrayList<Drawable>(INIT_SIZE);
	}
	
	/**
	 * Copy constructor
	 * @param other - the shape to copy.
	 */
	public ShapeContainer(ShapeContainer other)
	{
		assert(other != null);
		
		this.t_size = other.t_size;
		this.r_size = other.r_size;
		al = new ArrayList<Drawable>(INIT_SIZE);
		
		//Performing a deep copy
		for (int i = 0; i < other.al.size(); i++) 
		{
			al.add(other.al.get(i).clone());
		}
	}
	
	/**
	 * 
	 * @return The number of stored shapes in the container.
	 */
	public int size()
	{
		return al.size();
	}
	
	/**
	 * 
	 * @return The number of stored triangles in the container.
	 */
	public int T_size()
	{
		return t_size;
	}
	
	/**
	 * 
	 * @return The number of stored rectangles in the container.
	 */
	public int R_size()// return the number of stored rectangles in the container
	{
		return r_size;
	}
	
	/**
	 * Add a new shape to the collection.
	 * @param d shape to add
	 */
	public void add(Drawable d) 
	{
		//First check the type
		if(d instanceof Triangle)
			t_size++;
		if(d instanceof Rectangle)
			r_size++;
		
		al.add(d);
	} 
	
	/**
	 * Remove every shape contains p.
	 * @param p shape which contains p will be removed.
	 */
	public void remove(Point p)// remove triangles & rectangles containing p
	{
		//Just traversing each shape an check if it contains p.
		int i = 0;
		while(i < al.size())
		{
			if(al.get(i).contains(p))
			{
				if(al.get(i) instanceof Triangle)
					t_size--;
				if(al.get(i) instanceof Rectangle)
					r_size--;
				al.remove(i);//Since removing shifts data to the left, i isn't incremented.
			}
			else
				i++;
		}
	}
	
	/**
	 * returns new copy of triangle number i in the container.
	 * @param i triangle i
	 * @return new copy of triangle number i in the container.
	 */
	public Triangle T_at(int i)// return a new copy of the triangle number i
	{
		return (Triangle) at_i(Triangle.class, i);
	}
	
	/**
	 * returns new copy of rectangle number i in the container.
	 * @param i rectangle i
	 * @return new copy of rectangle number i in the container.
	 */
	public Rectangle R_at(int i) // return a new copy of the rectangle number i
	{
		return (Rectangle) at_i(Rectangle.class, i);
	}
	
	/**
	 * 
	 * @return area of all shapes in the container.
	 */
	public double sumArea() // return the sum of the areas of all the triangles & rectangles
	{
		double sum = 0.0;
		
		for (int i = 0; i < al.size(); i++)
		{
			//How awesome is Polymorphism !
			if(al.get(i) != null)
				sum += al.get(i).area();
		}
		
		return sum;
	}
	
	/**
	 * The function moves all shapes in the container with offset of point p.
	 * @param p point uses as offset to move the shapes.
	 */
	public void translate(Point p) //Translates (mutator) all the shapes by a Point
	{
		for (int i = 0; i < al.size(); i++)
		{	
			if(al.get(i) != null)
				al.get(i).translate(p);//Just a simple translate for every shape.
		}
	}
	
	/**
	 * The function calculates and prints min and max perimeters of triangle and rectangles.
	 */
	public void minMaxPerimeter()//calculates and prints min and max perimeter of the shapes (triangles & rectangles)
	{
		double min_t = Double.MAX_VALUE;//Minimum triangle perimeter
		double max_t = Double.MIN_VALUE;//Maximum triangle perimeter
		double min_r = Double.MAX_VALUE;//Minimum rectangle perimeter
		double max_r = Double.MIN_VALUE;//Maximum rectangle perimeter

		for (int i = 0; i < al.size(); i++) 
		{
			if(al.get(i) instanceof Triangle)
			{
				if(al.get(i).perimeter() < min_t)
					min_t = al.get(i).perimeter();
				if(al.get(i).perimeter() > max_t)
					max_t = al.get(i).perimeter();
			}
			else//It's a Rectangle
			{
				if(al.get(i).perimeter() < min_r)
					min_r = al.get(i).perimeter();
				if(al.get(i).perimeter() > max_r)
					max_r = al.get(i).perimeter();
			}
		}
		
		System.out.println(String.format("size: %d", size()));
		System.out.println("max permiter triangle: " + max_t);
		System.out.println("min permiter triangle: " + min_t);
		System.out.println("max permiter rectangle: " + max_r);
		System.out.println("min permiter rectangle: " + min_r);
	}

	//Helper function used by T_at and R_at
	private Drawable at_i(Class<?> cls, int i)
	{

		/*
		 * Since the array contains triangles and rectangles, i have to count
		 * the number of items of the class cls in order to get the i item.
		 */
		int counter = 0;
		
		for (int j = 0; j < al.size(); j++) 
		{
			if(al.get(j) != null)
			{
				if(counter == i && al.get(j).getClass() == cls)
					return al.get(j).clone();
				
				if(al.get(j).getClass() == cls)//Check if the item is type of the class cls.
				{
					counter++;
				}
			}
		}
		
		return null;
	}
}
