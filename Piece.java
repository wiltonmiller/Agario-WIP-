package Game;

import java.awt.Color;

public class Piece {
	
	private int value;
	private Color color;
	private int xPos;
	private int yPos;
	
	Piece(int v, int r, int g, int b)
	{
		this.value = v; 
		this.color = new Color(r,g,b);
	}
	
	public int getValue()
	{
		return this.value;
	}
	public Color getColor()
	{
		return this.color;
	}
	public void setXPos(int x)
	{
		this.xPos = x;
	}
	public void setYPos(int y)
	{
		this.yPos = y;
	}
	public int getXPos()
	{
		return this.xPos;
	}
	public int getYPos()
	{
		return this.yPos;
	}

}
