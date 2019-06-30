package ex4;

public class Controller 
{
	private static GameMode mode;
	private static MiniMax game;
	
	private static Thread gui_thread;//Thread for main GUI
	private static Thread gm_thread;//Thread for menu GUI
	
	private static GuiMenu gm;
	private static Gui gui;
	
	public Controller() 
	{
		mode = null;
		game = new MiniMax();
		
		//Create Guimenu thread
		gm = new GuiMenu();
		gm_thread = new Thread(gm);
		//Will be initialized later
		gui = null;
		gui_thread = null;
	}

	/**
	 * The function starting the game by setting the appropriate mode
	 * Chosen by the user.
	 * @param gm
	 */
	static void start_game(GameMode gm)
	{
		mode = gm;
	}
	
	/**
	 * The function handles a player's move, both computer and user.
	 * This function is calls the module to "handle" the operation.
	 * @param ind
	 */
	static void handle_player_move(int ind)
	{
		game.handle_choise(ind);
	}
	
	/**
	 * The function asks the module for the first player's sum.
	 * @return the player's sum
	 */
	static int get_player_a_sum()
	{
		return game.get_player_sum(game.PLAYER_A());
	}
	
	/**
	 * The function asks the module for the second player's sum.
	 * @return the player's sum
	 */
	static int get_player_b_sum()
	{
		return game.get_player_sum(game.PLAYER_B());
	}
	
	/**
	 * The function returns true if it is the first player's turn.
	 * @return true for first player's turn
	 */
	static boolean first_player_turn()
	{
		return game.last_turn() == game.PLAYER_A() ? true : false;
	}
	
	/**
	 * The function returns the current activated left button index
	 * @return left button index
	 */
	static int left_button()
	{
		return game.left();
	}
	
	/**
	 * The function returns the current activated right button index
	 * @return right button index
	 */
	static int right_button()
	{
		return game.right();
	}
	
	/**
	 * Get last pick(number) in the game.
	 * @return last number picked.
	 */
	static int last_pick()
	{
		return game.last_pick();
	}
	
	private static void go_to_sleep(int milliseconds)
	{
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	public static void main(String []args)
	{
		new Controller();

		gm_thread.start();//Start Game menu
		synchronized(gm) 
		{
			try {
				gm.wait();//Go to sleep
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//Create Gui thread
		gui = new Gui(game.numbers(), game.SIZE(), mode);
		gui_thread = new Thread(gui);//Make GUI as a separate thread
		gui_thread.start();
		
		if(mode == GameMode.COMP_VS_STUDENT)
		{
			for (int i = 0; i < game.SIZE()/2; i++)//Computer has half size picks 
			{
				int pick = game.pick();//Get Computer pick
				go_to_sleep(1200);//Simulate a none rapid computer pick.
				gui.handle_computer_pick(pick);
				synchronized(gui) 
				{
					try {
						gui.wait();//Go to sleep
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
