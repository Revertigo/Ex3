package ex4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class GuiUtil 
{
	
	private static final String icon_path = "resource\\IQC.jpg";
	
	public static String icon_path() {return icon_path;}
	
	static void create_and_add_button(JButton button, Rectangle rect, Color bg_color,
			Font font, MouseListener ml, JPanel panel)
	{
		button.setBounds(rect);
		button.setBackground(bg_color);
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);
		button.setFont(font);
		button.addMouseListener(ml);
		
		//Add the button
		panel.add(button);
	}
	
	static void create_and_add_label(JLabel label, Rectangle rect, boolean set_border, 
			Color bg_color, Font font, JPanel panel)
	{
		label.setBounds(rect);
        // draw label border
		if(set_border)
			label.setBorder(new LineBorder(Color.BLACK));
		label.setPreferredSize(new Dimension(rect.width, rect.height));
		label.setForeground(bg_color);
		label.setFont(font);
		
		//Add the label.
		panel.add(label);
	}
}
