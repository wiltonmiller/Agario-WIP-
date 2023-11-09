package Game;

import java.awt.Color;

public class BadGuy {
	
	private int size;
	
	private int xPos;
	private int yPos;
	
	private int xVelocity;
	private int yVelocity;
	
	private int playerNumber;
	
	private String playerName;
	
	private Color color;
	
	private boolean followingPlayer;
	
	
	BadGuy(int pN, int s, int r, int g, int b)
	{
		this.playerNumber = pN;
		this.size = s;
		this.playerName = "Player " + this.playerNumber;
		this.color = new Color(r,g,b);
		this.followingPlayer = false; 
	}
	
	public int getSize()
	{
		return this.size;
	}
	public void setSize(int s)
	{
		this.size = s;
	}
	public void increaseSize(int s)
	{
		this.size+=s;
	}
	public void decreaseSize(int s)
	{
		this.size-=s;
	}
	public int getXPos()
	{
		return this.xPos;
	}
	public int getYPos()
	{
		return this.yPos;
	}
	public void setXPos(int x)
	{
		this.xPos = x;
	}
	public void setYPos(int y)
	{
		this.yPos = y;
	}
	public String getPlayerName()
	{
		return this.playerName;
	}
	public Color getColor()
	{
		return this.color;
	}
	public int getXVelocity()
	{
		return this.xVelocity;
	}
	public int getYVelocity()
	{
		return this.yVelocity;
	}
	public void setXVelocity(int x)
	{
		this.xVelocity = x;
	}
	public void setYVelocity(int y)
	{
		this.yVelocity = y;
	}
	public void setFollowTrue() 
	{
		this.followingPlayer = true;
	}
	public void setFollowFalse()
	{
		this.followingPlayer = false;
	}
	public boolean isFollowing()
	{
		return this.followingPlayer;
	}
}
