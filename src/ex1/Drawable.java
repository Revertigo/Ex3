package ex1;
public interface Drawable
{
	public boolean equals(Drawable d);
	public boolean contains(Point p);
	public double perimeter();
	public double area();
	public void translate(Point p);
	public Drawable clone();//Added function to create better code design(and preserver Polymorphism)
}
