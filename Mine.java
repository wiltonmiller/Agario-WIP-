package Game;

import java.awt.Color;

public class Mine {
	
	private int value;
	private Color color;
	private int xPos;
	private int yPos;
	
	Mine(int v)
	{
		this.value = v; 
	}
	
	public int getValue()
	{
		return this.value;
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

