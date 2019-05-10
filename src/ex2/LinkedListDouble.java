package ex2;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedListDouble <T>
{
	private NodeDouble<T> head;
	private int size;//Number of elements
	
	/**
	 * Default constructor. initialises empty list.
	 */
	public LinkedListDouble()
	{
		head = new NodeDouble<T>(null);//Dummy node(node which has no data but uses for easy list implementation)
		head.next = null;
		head.prev = null;
	}

	/**
	 * Add an item to the end of the list.
	 * @param item - item to add.
	 */
	void add(T item)//Since it's not clear where to add, I assume add to the end.
	{
		//No if in the code :)
		NodeDouble<T> obj = new NodeDouble<T>(item);
		
		NodeDouble<T> temp = head;
		while(temp.next != null)//Get the last node
			temp = temp.next;
		
		obj.prev = temp;
		obj.next = temp.next;
		temp.next = obj;
		
		size++;//Increase list size.
	}
	
	/**
	 * 
	 * @return size of the list.
	 */
	int size() {return size;}
	
	/**
	 * 
	 * @return last element in the list.
	 */
	T getLast()
	{
		NodeDouble<T> temp = head;
		while(temp.next != null)//Get the last node
			temp = temp.next;
		
		return temp.data;
	}
	
	/**
	 * 
	 * @return iterator object for that list.
	 */
	ListIterator<T> listIterator()
	{
		//All the methods inside are iterator standard functions.
		class InnerIterator implements ListIterator<T>
		{
			private NodeDouble<T> t_head = head.next;//Temporary head
			
			@Override
			public boolean hasNext() 
			{
				return t_head != null;
			}

			@Override
			public boolean hasPrevious()
			{
				return t_head.prev != head;//Head is no limb in the list(first limb is head.next)
			}

			@Override
			public T next() 
			{
				if (!hasNext())
					throw new NoSuchElementException();//According to the API
				T next = t_head.data;//Save the current(it will be the next)
				t_head = t_head.next;//Update head.
				
				return next;
			}


			@Override
			public T previous()
			{
				if (!hasPrevious())
					throw new NoSuchElementException();//According to the API
				//The user should check first if hasPrevious()
				T prev = t_head.prev.data;//Save the current previous.
				t_head = t_head.prev;
				
				return prev;
			}

			@Override
			public void add(T arg0) {throw new UnsupportedOperationException();}
			
			@Override
			public int nextIndex() {throw new UnsupportedOperationException();}
			
			@Override
			public int previousIndex() {throw new UnsupportedOperationException();}

			@Override
			public void remove() {throw new UnsupportedOperationException();}

			@Override
			public void set(T arg0) {throw new UnsupportedOperationException();}
			
		}
		
		return new InnerIterator();
	}
	
	@Override
	public String toString()
	{
		String path = "";
		NodeDouble<T> temp = head.next;
		while(temp != null)
		{
			path += temp.toString() + ",";
			temp = temp.next;
		}
		
		return path;
	}
	
	private static class NodeDouble<T>
	{
		private T data;
		NodeDouble<T> next;
		NodeDouble<T> prev;
		
		/**
		 * Default constructor.
		 * @param new_data - data to the node.
		 */
		NodeDouble(T new_data)
		{
			data = new_data;
			next = null;
			prev = null;
		}
		
		@Override
		public String toString() 
		{
			return data.toString();
		}
	}
	
	public static void main(String[] args) 
	{
		LinkedListDouble<Character> lld = new LinkedListDouble<Character>();
		lld.add('a');
		lld.add('b');
		lld.add('c');
		lld.add('d');
		lld.add('e');

		ListIterator<Character> li = lld.listIterator();
		System.out.println("Direct walk");
		while(li.hasNext())
			System.out.print(li.next() + ",");
		System.out.println("\nReverse walk");
		/*
		 * Better way to do it is to implement descending iterator(but it wasn't ordered)
		 */
		li = lld.listIterator();
		for (int i = 0; i < lld.size()-1; i++)//Go to the one before last
			li.next();
		System.out.print(lld.getLast() + ",");//Print the rest of the items
		while(li.hasPrevious())
			System.out.print(li.previous() + ",");
	}

}
