package Game;
import java.awt.Color;

public class Player {
	
	private int Size;
	private int xPos;
	private int yPos;
	
	Player(int s)
	{
		this.Size = s;
	}
	
	public int getSize()
	{
		return this.Size;
	}
	public void increaseSize(int n)
	{
		this.Size+=n;
	}
	public void decreaseSize(int n)
	{
		this.Size-=n;
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
