package ex4;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Gui extends JFrame implements Runnable
{
	private Gui me;//Pointer to the class
	
	private final int BUTTON_SIZE = 75;
	private final int NUMBERS_X_POS = 100;
	private final int NUMBERS_Y_POS = 400;
	
	private int [] numbers;
	private int  amount;
	
	private JPanel panel;	
	private ImageIcon icon;
	
	private ArrayList<JButton> numbers_b;
	private MouseListener [] mls;
	private JLabel pl1;
	private JLabel pl2;
	private JLabel sum_str_pl1;
	private JLabel sum_str_pl2;
	private JLabel sum_pl1;
	private JLabel sum_pl2;
	private JLabel [] picks_pl1;
	private JLabel [] picks_pl2;
	private int pl1_ind;//current index for picks_pl1
	private int pl2_ind;//current index for picks_pl2
	private String pl1_name;//Computer or student
	private String pl2_name;
	private String victory_popup;
	
	public JPanel panel() {return panel;}
	
	/**
	 * Constructor for the Gui class.
	 * @param numbers - arrays of numbers
	 * @param amount - size of tha
	 * @param gm
	 */
	public Gui(int [] numbers, int amount, GameMode gm) 
	{
		me = this;//Just an old trick to get the class's pointer
		this.numbers = numbers;
		this.amount = amount;
		
		this.setTitle("MiniMax");
		this.getContentPane().setBackground(Color.MAGENTA);
		this.setBounds(500, 200, 1000, 650);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		icon = new ImageIcon(GuiUtil.icon_path());
		this.setIconImage(icon.getImage());
		
		panel = new JPanel();
		panel.setBackground(Color.cyan);
		panel.setLayout(null);//Disable layout management
		
		pl1_name = (gm == GameMode.COMP_VS_STUDENT) ? "Computer" : "Student1";
		pl2_name = (gm == GameMode.COMP_VS_STUDENT) ? "Student" : "Student2";
		
		numbers_b = new ArrayList<JButton>();
		mls = new MouseListener[amount];
		
		add_buttons();//Add all buttons to the panel
		add_labels();//Add all labels to the panel
		
		add(panel);
	}
	
	/**
	 * The function handles the computer pick by clicking the
	 * matching button.
	 * @param pick index of button to click.
	 */
	void handle_computer_pick(int pick)
	{
		//Triggers a click event on the appropriate button
		MouseEvent e = new MouseEvent(numbers_b.get(pick), 501, 0, 0, 0, 0, 1, true);
		mls[pick].mouseClicked(e);
	}

	@Override
	public void run() 
	{
		//Set visibility of the Jframe and add the panel into.
		setVisible(true);
	}
	
	/**
	 * The function add a popup of winner message.
	 */
	private void announce_winner()
	{
		int sum_a = Controller.get_player_a_sum();
		int sum_b = Controller.get_player_b_sum();
		String winner = ( sum_a> sum_b) ? pl1_name : pl2_name;
		int winner_sum = (sum_a > sum_b) ? sum_a : sum_b; 
		victory_popup = String.format("Winner is the %s !!!\n with sum of:%d", winner, winner_sum);
		JOptionPane.showMessageDialog(null, victory_popup);
	}
	
	/**
	 * The function creates and adds all buttons to the GUI's panel.
	 */
	private void add_buttons()
	{
		Font b_font = new Font("Tahoma", Font.BOLD, 12);
		int x_pos = NUMBERS_X_POS;
		
		for (int i = 0; i < amount; i++)
		{
			numbers_b.add(new JButton("" + numbers[i]));
			MouseListener ml = new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent arg0) {}
				
				@Override
				public void mousePressed(MouseEvent e) {}
				
				@Override
				public void mouseExited(MouseEvent arg0) {}
				
				@Override
				public void mouseEntered(MouseEvent e) 
				{
					int left_b = Controller.left_button();
					int right_b = Controller.right_button();
					
					int ind = (e.getSource() == numbers_b.get(left_b)) ? left_b : right_b;
					numbers_b.get(ind).setToolTipText("Pick " + numbers[ind]);
				}
				
				@Override
				public void mouseClicked(MouseEvent e) 
				{
					int left_b = Controller.left_button();
					int right_b = Controller.right_button();
					
					if(e.getSource() == numbers_b.get(left_b) || e.getSource() == numbers_b.get(right_b))
					{
						int ind = (e.getSource() == numbers_b.get(left_b)) ? left_b : right_b;
							
						Controller.handle_player_move(ind);
						
						//Update sum and picks
						if(Controller.first_player_turn())
						{
							sum_pl1.setText("" + Controller.get_player_a_sum());
							picks_pl1[pl1_ind++].setText(String.format("%3d,", Controller.last_pick()));//update player's choice
							pl1.setForeground(Color.BLACK);
							pl2.setForeground(Color.RED);
						}
						else
						{
							sum_pl2.setText("" + Controller.get_player_b_sum());
							picks_pl2[pl2_ind++].setText(String.format("%3d,", Controller.last_pick()));//update player's choice
							pl2.setForeground(Color.BLACK);
							pl1.setForeground(Color.RED);
						}
						
						//Disappear and disable button
						e.getComponent().setEnabled(false);
						e.getComponent().setVisible(false);
						
						if(left_b >= right_b)//Means game is done
						{
							announce_winner();
						}
						else
						{
							//Enable the adjacent button
							if(e.getSource() == numbers_b.get(left_b))
							{
								numbers_b.get(left_b + 1).setEnabled(true);
							}
							else//It was a right button
							{
								 numbers_b.get(right_b - 1).setEnabled(true);
							}
						}
					}
					
					synchronized(me)
					{
						me.notify();//Notify the controller that it's computer turn
					}
				}
			};
			GuiUtil.create_and_add_button(numbers_b.get(i), new Rectangle(x_pos, NUMBERS_Y_POS, BUTTON_SIZE, BUTTON_SIZE),
					new Color(59, 89, 182), b_font, ml, panel);
			mls[i] = ml;//Add new mouse listener
			x_pos += BUTTON_SIZE;
			numbers_b.get(i).setEnabled(false);
		}
		
		//Set only the outside numbers enabled
		numbers_b.get(0).setEnabled(true);
		numbers_b.get(amount-1).setEnabled(true);
	}
	
	/**
	 * The function creates and adds all labels to the GUI's panel.
	 */
	private void add_labels()
	{
		Font l_font = new Font("Tahoma", Font.BOLD, 30);//Labels font
		pl1 = new JLabel(pl1_name);
		GuiUtil.create_and_add_label(pl1, new Rectangle(100, 50, 200, 100), true,
				new Color(255, 0, 0), l_font, panel);
		
		pl2 = new JLabel(pl2_name);
		GuiUtil.create_and_add_label(pl2, new Rectangle(700, 50, 200, 100), true,
				new Color(0, 0, 0), l_font, panel);
		
		sum_str_pl1 = new JLabel("Sum: ");
		GuiUtil.create_and_add_label(sum_str_pl1, new Rectangle(100, 150, 150, 100), false,
				new Color(0, 0, 0), l_font, panel);
		
		sum_str_pl2 = new JLabel("Sum: ");
		GuiUtil.create_and_add_label(sum_str_pl2, new Rectangle(700, 150, 150, 100),false,
				new Color(0, 0, 0), l_font, panel);
		
		sum_pl1 = new JLabel("" + Controller.get_player_a_sum());
		GuiUtil.create_and_add_label(sum_pl1, new Rectangle(180, 150, 150, 100), false,
				new Color(0,0,0), l_font, panel);
		
		sum_pl2 = new JLabel("" + Controller.get_player_b_sum());
		GuiUtil.create_and_add_label(sum_pl2, new Rectangle(780, 150, 150, 100),false,
				new Color(0,0,0), l_font, panel);
		
		picks_pl1 = new JLabel[amount/2];
		picks_pl2 = new JLabel[amount/2];
		pl1_ind = 0;
		pl2_ind = 0;
		
		int x_pos1 = 100;
		int x_pos2 = 700;
		for (int i = 0; i < amount/2; i++) 
		{
			picks_pl1[i] = new JLabel();
			GuiUtil.create_and_add_label(picks_pl1[i], new Rectangle(x_pos1, 200, 150, 100),false,
				new Color(0,0,0), l_font, panel);
			x_pos1 += 60;
			picks_pl2[i] = new JLabel();
			GuiUtil.create_and_add_label(picks_pl2[i], new Rectangle(x_pos2, 200, 150, 100),false,
				new Color(0,0,0), l_font, panel);
			x_pos2 += 55;
		}
	}
}
