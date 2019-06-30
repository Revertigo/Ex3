package ex4;

public class Player 
{
	private int player_sum;
	private int [] player_picks;
	private int arr_tracker;
	
	//Getters
	public int player_sum() {return player_sum;}
	
	public Player(int num_of_picks) 
	{
		player_sum = 0;
		player_picks = new int[num_of_picks];
		arr_tracker = 0;
	}
	
	void add_sum(int player_sum) {this.player_sum += player_sum;}
	void add_pick(int to_add) {this.player_picks[arr_tracker++] = to_add;}
}
