package Game;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GameScreen extends JFrame{
	
	private int length;
	private int width;
	
	private static GamePanel gamePanel;
	
	GameScreen(int l, int w) throws InterruptedException
	{
		super("Agair.io");
		
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setResizable(false);
	    
	    this.length = l;
		this.width = w;
		
		GameScreen.gamePanel = new GamePanel(this.length, this.width, 150, 3, 10);
		
		Dimension Dimension= new Dimension(this.length,this.width);
		
		this.setSize(Dimension);
	
		this.add(GameScreen.gamePanel);
		
		System.out.println("Game panel added");
		
		this.setVisible(true);
	    
	}
	
	public static GamePanel getPanel() 
	{
		return GameScreen.gamePanel;
	}

}
