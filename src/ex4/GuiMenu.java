package ex4;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GuiMenu extends JFrame implements Runnable
{
	private GuiMenu me;//Pointer for the class
	
	private final int X_POS = 375;
	private final int Y_POS = 200;
	private final int BUTTON_WIDTH = 200;
	private final int BUTTON_HEIGHT = 120;
	
	private JPanel panel;	
	private JButton comp_vs_student;
	private JButton student_vs_student;
	private JLabel headline;
	
	private ImageIcon icon;
	private Font default_font;
	
	/**
	 * Default constructor
	 */
	public GuiMenu()
	{
		me = this;//Just an old trick to get the class pointer
		
		this.setTitle("MiniMax");
		this.getContentPane().setBackground(Color.MAGENTA);
		this.setBounds(500, 200, 1000, 650);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		icon = new ImageIcon(GuiUtil.icon_path());
		this.setIconImage(icon.getImage());
		
		panel = new JPanel();
		panel.setBackground(Color.cyan);
		panel.setLayout(null);
		default_font = new Font("AppleGothic", Font.BOLD, 12);
		
		headline = new JLabel("Welcome To Numbers Game: Minimax");
		GuiUtil.create_and_add_label(headline, new Rectangle(115, 50, BUTTON_WIDTH * 4, BUTTON_HEIGHT/2), false,
				new Color(255, 0, 0), new Font("AppleGothic", Font.ITALIC, 44), panel);
		
		MouseListener ml = new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
			@Override
			public void mousePressed(MouseEvent e)
			{
				if(e.getSource() == student_vs_student)
				{
					Controller.start_game(GameMode.STUDENT_VS_STUDENT);
				}
				else
				{
					Controller.start_game(GameMode.COMP_VS_STUDENT);
				}
				
				//Hide the first menu
				setVisible(false);
				synchronized (me)
				{
					me.notify();
				}
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {}
		};
		student_vs_student = new JButton("Student Vs Student");
		GuiUtil.create_and_add_button(student_vs_student, new Rectangle(X_POS, Y_POS, BUTTON_WIDTH, BUTTON_HEIGHT),
				new Color(59, 89, 182), default_font, ml, panel);
		
		comp_vs_student = new JButton("Computer Vs Student");
		GuiUtil.create_and_add_button(comp_vs_student, new Rectangle(X_POS, Y_POS + 150, BUTTON_WIDTH, BUTTON_HEIGHT),
				new Color(59, 89, 182), default_font, ml, panel);
		
		add(panel);
	}

	@Override
	public void run() 
	{
		setVisible(true);
	}
}
