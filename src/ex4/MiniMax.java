package ex4;

public class MiniMax 
{
	private final int TOP_LIMIT = 100;
	private final int SIZE = 10;//Must be even number
	private final int NUM_OF_PLAYERS = 2;//Number of players
	private final int PLAYER_A = 0;
	private final int PLAYER_B = 1;
	
	private Player [] players;//In computer mode, player 0 is always the computer.
	private int current_turn;
	private int [] numbers;
	private int left;//Left index of number's array
	private int right;//Right index of number's array
	private int last_pick;
	
	//Getters
	public int SIZE() {return SIZE;}
	public int[] numbers() {return numbers;}
	public int last_turn() {return 1-current_turn;}
	public int PLAYER_A() {return PLAYER_A;}
	public int PLAYER_B() {return PLAYER_B;}
	public int left() {return left;}
	public int right() {return right;}
	public int last_pick() {return last_pick;}
	
	/**
	 * default constructor.
	 */
	public MiniMax() 
	{
		players = new Player[NUM_OF_PLAYERS];
		//In computer mode, player 0 is always the computer.
		players[0] = new Player(SIZE/2);
		players[1] = new Player(SIZE/2);
		numbers = new int[SIZE];
		
		current_turn = 0;
		left = 0;
		right = numbers.length-1;
		last_pick = -1;
		
		random_numbers();//Fill the array with number
	}
	
	/**
	 * The function operates a pick out of the number's array
	 * after a calculations of sums(improved strategy)
	 * @return index in the array of the computer pick's.
	 */
	public int pick()
	{
		int even_sum = calc_even_odd(numbers, left, right);
		int odd_sum = calc_even_odd(numbers, left + 1, right);
		
		//extract index
		int ind = even_sum > odd_sum ? left : right;
		//Handling the pick will be later
		return ind;
	}
	
	/**
	 * The function handles a computer/user pick of number. 
	 * Updates everything related.
	 * @param ind - index of the number in the array.
	 */
	public void handle_choise(int ind)
	{
		assert (ind == left || ind == right);
		
		players[current_turn].add_sum(numbers[ind]);
		players[current_turn].add_pick(numbers[ind]);
		current_turn = 1 - current_turn;
		last_pick = numbers[ind];

		//Update indices after user pick
		if(ind == left)
		{
			left++;
		}
		else//Must be equals to right
		{
			right--;
		}
	}
	
	/**
	 * Get's a player's current sum.
	 * @param ind
	 * @return
	 */
	public int get_player_sum(int ind)
	{
		return players[ind].player_sum();
	}
	
	/**
	 * The function random numbers.
	 */
	private void random_numbers()
	{
		for (int i = 0; i < numbers.length; i++) 
		{
			numbers[i] = (int)(Math.random() * TOP_LIMIT) + 1;
		}
	}
	
	/**
	 * The function calculates the sum of even indices or odd indices
	 * and returns it's sum.
	 * @param arr - array of numbers.
	 * @param start_ind - where to start.
	 * @param end_ind - where to end.
	 * @return
	 */
	private static int calc_even_odd(int [] arr, int start_ind, int end_ind)
	{
		int sum = 0;
		
		for(int i = start_ind; i <= end_ind; i+=2)
		{
			sum += arr[i];
		}
		
		return sum;
	}
}
