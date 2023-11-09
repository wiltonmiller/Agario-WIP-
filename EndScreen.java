package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class EndScreen extends JFrame {
	
	JButton restartButton;
	
	EndScreen()
	{
		super("GameOver");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000,500);
		this.setBackground(Color.BLACK);
		
		this.setLayout(null);
		
		ImageIcon myImage = new ImageIcon("restart.png");
		restartButton = new JButton(myImage);
		restartButton.setVisible(true);
		restartButton.setBounds(500,250, myImage.getIconWidth(), myImage.getIconHeight());
		
		this.add(restartButton);
		
		this.setVisible(true);
	}

}
