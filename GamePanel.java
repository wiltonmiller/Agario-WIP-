package Game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.geom.AffineTransform;
import java.util.Random;


import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	private int length; 
	private int width;
	
	private int numberOfPieces;
	private int numberOfMines;
	private int numberOfBadGuys;
	
	private Piece [] pieceArr;
	private Mine [] mineArr; 
	private BadGuy [] bgArr;
	
	private Player player; 
	
	static int scaleFactor = 0; 
	
	static int velocityChanger = 0;
	
	static int xVelocity;
	static int yVelocity;
	
	Random random = new Random(); 
	
	GamePanel(int l, int w, int np, int nm, int bg) throws InterruptedException
	{
		
		this.length = l;
		this.width = w;
		
		this.numberOfPieces = np;
		this.numberOfMines = nm;
		this.numberOfBadGuys = bg;
		
		Dimension Dimension= new Dimension(this.length,this.width);
		
		this.setSize(Dimension);
		
		this.pieceArr = new Piece[this.numberOfPieces];
		this.mineArr = new Mine[this.numberOfMines];
		this.bgArr = new BadGuy[this.numberOfBadGuys];
		
		this.setLayout(null);
		
		this.setVisible(true);
		
		initalizePlayer();
		initalizeBadGuy();
		fillScreen();
		fillMines();
	}
	
	public void initalizeBadGuy() {
		//make a bad guy

		for(int i =  0; i < this.numberOfBadGuys; i++)
		{
			//setting the color
			int r = random.nextInt(250 - 0) + 0;
			int g = random.nextInt(250 - 0 ) + 0;
			int b = random.nextInt(250 - 0 ) + 0;

			//adding a new piece to the array while setting the size and color
			this.bgArr[i] = new BadGuy(i, 40, r, g, b);
			
			this.bgArr[i].setXVelocity((int) (Math.random() * 2) * 2 - 1);
			this.bgArr[i].setYVelocity((int) (Math.random() * 2) * 2 - 1);

			//setting a random x and y postion to the piece array
			this.bgArr[i].setXPos(random.nextInt(this.length - 0) + 0); //technically dont need the plus zero but it helps to undestand
			this.bgArr[i].setYPos(random.nextInt(this.width - 0) + 0);	
			
			
			System.out.println(this.bgArr[i].getYPos());
		}
		
	}
	
	public void initalizePlayer() {
		
		//make a player
		this.player = new Player(40);
		
		//set players x and y
		this.player.setXPos(this.width/2 - 16);
		this.player.setYPos(this.length/2 - 15);	
	}
	
	public void fillScreen() {

		for(int i =  0; i < this.numberOfPieces; i++)
		{
			//setting the color
			int r = random.nextInt(250 - 0) + 0;
			int g = random.nextInt(250 - 0 ) + 0;
			int b = random.nextInt(250 - 0 ) + 0;
			
			int pieceSize = random.nextInt(25 - 10) + 10; //the size of each piece corralates to is value
			//the size of each piece is also random
			
			//adding a new piece to the array while setting the size and color
			this.pieceArr[i] = new Piece(pieceSize, r, g, b);
			
			
			//setting a random x and y postion to the piece array
			this.pieceArr[i].setXPos(random.nextInt(this.length - 0) + 0); //technically dont need the plus zero but it helps to undestand
			this.pieceArr[i].setYPos(random.nextInt(this.width - 0) + 0);		
			
		}
	}
	
	public void fillMines() {

		for(int i =  0; i < this.numberOfMines; i++)
		{
			this.mineArr[i] = new Mine(40);

			//setting a random x and y postion to the piece array
			this.mineArr[i].setXPos(random.nextInt(this.length - 0) + 0); //technically dont need the plus zero but it helps to undestand
			this.mineArr[i].setYPos(random.nextInt(this.width - 0) + 0);	
		}
	}
	
	public void checkIfOffScreen()
	{		
		for(int i = 0; i < this.pieceArr.length; i ++)
		{
			if(this.pieceArr[i]!=null)
			{
				if(this.pieceArr[i].getXPos() < 0)
				{
					this.pieceArr[i].setXPos(this.width);
				}
				if(this.pieceArr[i].getXPos() > this.width)
				{
					this.pieceArr[i].setXPos(0);
				}
				if(this.pieceArr[i].getYPos() < 0)
				{
					this.pieceArr[i].setYPos(this.length);
				}
				if(this.pieceArr[i].getYPos() > this.length)
				{
					this.pieceArr[i].setYPos(0);
				}
			}
		}
		
		for(int i = 0; i < this.mineArr.length; i ++)
		{
			if(this.mineArr[i].getXPos() < 0)
			{
				this.mineArr[i].setXPos(this.width);
			}
			if(this.mineArr[i].getXPos() > this.width)
			{
				this.mineArr[i].setXPos(0);
			}
			if(this.mineArr[i].getYPos() < 0)
			{
				this.mineArr[i].setYPos(this.length);
			}
			if(this.mineArr[i].getYPos() > this.length)
			{
				this.mineArr[i].setYPos(0);
			}
		}
	}
	
	public void mineCollision() 
	{
		double x1 = this.player.getXPos() + this.player.getSize()/2;
		double y1 = this.player.getYPos() + this.player.getSize()/2;

		for(int i = 0; i < this.mineArr.length; i++)
		{
			if(this.mineArr[i] != null)
			{
				double x2 = this.mineArr[i].getXPos() + this.mineArr[i].getValue()/2;
				double y2 = this.mineArr[i].getYPos() + this.mineArr[i].getValue()/2;

				double r1 = this.player.getSize()/2;
				double r2 = this.mineArr[i].getValue()/2;

				double distance = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

				if (distance <= r1 + r2)
				{
					scaleFactor-=this.mineArr[i].getValue()/5;
					this.player.decreaseSize(this.mineArr[i].getValue()/5);
					repaint();
				}
			}
		}
	}
	
	public void badGuyMineCollision() 
	{

		for(int i = 0; i < this.bgArr.length; i++)
		{
			for(int j = 0; j < this.mineArr.length; j++)
			{
				if(this.mineArr[j] != null && this.bgArr[i] != null)
				{
					double x1 = this.bgArr[i].getXPos() + this.bgArr[i].getSize()/2;
					double y1 = this.bgArr[i].getYPos() + this.bgArr[i].getSize()/2;
					
					double x2 = this.mineArr[j].getXPos() + this.mineArr[j].getValue()/2;
					double y2 = this.mineArr[j].getYPos() + this.mineArr[j].getValue()/2;
					
					double r1 = this.bgArr[i].getSize()/2;
					double r2 = this.mineArr[j].getValue()/2;
					
					double distance = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
					
					if (distance <= r1 + r2)
					{
						this.bgArr[i].decreaseSize(this.mineArr[j].getValue());
						repaint();
					}
				} 
			} 	
		}
}

	public void checkForCollision()
	{
		double x1 = this.player.getXPos() + this.player.getSize()/2;
		double y1 = this.player.getYPos() + this.player.getSize()/2;

		for(int i = 0; i < this.pieceArr.length; i++)
		{
			if(this.pieceArr[i] != null)
			{
				double x2 = this.pieceArr[i].getXPos() + this.pieceArr[i].getValue()/2;
				double y2 = this.pieceArr[i].getYPos() + this.pieceArr[i].getValue()/2;

				double r1 = this.player.getSize()/2;
				double r2 = this.pieceArr[i].getValue()/2;

				double distance = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

				if (distance <= r1 + r2) {

					this.player.increaseSize(this.pieceArr[i].getValue()/5);
					scaleFactor+= this.pieceArr[i].getValue()/5;

					int r = random.nextInt(250 - 0) + 0;
					int g = random.nextInt(250 - 0 ) + 0;
					int b = random.nextInt(250 - 0 ) + 0;

					int pieceSize = random.nextInt(25 - 10) + 10; //the size of each piece corralates to is value
					//the size of each piece is also random

					//adding a new piece to the array while setting the size and color
					this.pieceArr[i] = new Piece(pieceSize, r, g, b);


					//setting a random x and y postion to the piece array
					this.pieceArr[i].setXPos(random.nextInt(this.length - 0) + 0); //technically dont need the plus zero but it helps to undestand
					this.pieceArr[i].setYPos(random.nextInt(this.width - 0) + 0);


					repaint();
				}
			} 
		}
	}

	public void badGuyCollision()
	{
		for(int i = 0; i < this.bgArr.length; i++)
		{
			for(int j = 0; j < this.pieceArr.length; j++)
			{
				if(this.bgArr[i] != null && this.pieceArr[j] !=null)
				{
					double x1 = this.bgArr[i].getXPos() + this.bgArr[i].getSize()/2;
					double y1 = this.bgArr[i].getYPos() + this.bgArr[i].getSize()/2;
					
					double x2 = this.pieceArr[j].getXPos() + this.pieceArr[j].getValue()/2;
					double y2 = this.pieceArr[j].getYPos() + this.pieceArr[j ].getValue()/2;
					
					double r1 = this.bgArr[i].getSize()/2;
					double r2 = this.pieceArr[j].getValue()/2;
					
					double distance = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
		
					if(distance <= r1 + r2)
					{
						this.bgArr[i].increaseSize(this.pieceArr[j].getValue()/5);
						
						this.pieceArr[j] = null;
						
						int r = random.nextInt(250 - 0) + 0;
						int g = random.nextInt(250 - 0 ) + 0;
						int b = random.nextInt(250 - 0 ) + 0;

						int pieceSize = random.nextInt(5 - 0) + 0; //the size of each piece corralates to is value
						//the size of each piece is also random

						//adding a new piece to the array while setting the size and color
						this.pieceArr[i] = new Piece(pieceSize, r, g, b);


						//setting a random x and y postion to the piece array
						this.pieceArr[i].setXPos(random.nextInt(this.player.getXPos() + this.width - this.player.getXPos()) + this.player.getXPos()); 
						this.pieceArr[i].setYPos(random.nextInt(this.player.getYPos() + this.length - this.player.getYPos()) + this.player.getYPos());
						//make a new piece that is between the player and the window height and width, and the players x and y postion
						//ie the high bound and the low bounds
						
						
						
						repaint();
					} 
				} 
			} 
	}
}
	public void eatBadGuy()
	{
		double x1 = this.player.getXPos() + this.player.getSize()/2;
		double y1 = this.player.getYPos() + this.player.getSize()/2;
		
		for(int i = 0; i < this.bgArr.length; i++)
		{
			if(this.bgArr[i]!= null)
			{
				double x2 = this.bgArr[i].getXPos() + this.bgArr[i].getSize()/2;
				double y2 = this.bgArr[i].getYPos() + this.bgArr[i].getSize()/2;
				
				double r1 = this.player.getSize()/2;
				double r2 = this.bgArr[i].getSize()/2;
				
				double distance = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
				
				if(distance <= r1 + r2) 
				{
					System.out.println(this.player.getSize());
					System.out.println(this.bgArr[i].getSize());
					if(this.player.getSize() > this.bgArr[i].getSize())
					{
						System.out.println("you at bad guy");
						this.player.increaseSize(this.bgArr[i].getSize()/5);
						scaleFactor += this.bgArr[i].getSize()/5;
						this.bgArr[i] = null;
						repaint();
					}
					else
					{
						scaleFactor = -1;
						GamePanel.checkIfGameOver();
						System.out.println("the game should be over rigth now");
						//repaint();
					}
				} 
		}
	}
}

	public void paint(Graphics g)
	{
		
		super.paint(g);
		
		checkIfOffScreen();
		
		eatBadGuy();
		checkForCollision();
		badGuyCollision();
		badGuyMineCollision();
		mineCollision();
		
		//getting rid of the bad guys that are "killed" by the mines
		for(int i = 0; i < this.bgArr.length; i++)
		{
			if(this.bgArr[i] != null)
			{
				if(this.bgArr[i].getSize() < 3)
				{
					this.bgArr[i] = null;
				}	
			}
		}

		for(int i = 0; i < this.pieceArr.length; i++)
		{
			if(this.pieceArr[i]!=null)
			{
				g.setColor(this.pieceArr[i].getColor());
				g.drawOval(this.pieceArr[i].getXPos(), this.pieceArr[i].getYPos(), this.pieceArr[i].getValue(), this.pieceArr[i].getValue());
				g.fillOval(this.pieceArr[i].getXPos(), this.pieceArr[i].getYPos(), this.pieceArr[i].getValue(), this.pieceArr[i].getValue());
			}
		}
		
		for(int i = 0; i < this.bgArr.length; i++)
		{
			if(this.bgArr[i]!=null)
			{
				g.setColor(this.bgArr[i].getColor());
				g.drawOval(this.bgArr[i].getXPos(), this.bgArr[i].getYPos(), this.bgArr[i].getSize(), this.bgArr[i].getSize());
				g.fillOval(this.bgArr[i].getXPos(), this.bgArr[i].getYPos(), this.bgArr[i].getSize(), this.bgArr[i].getSize());
				
				
				g.drawString(this.bgArr[i].getPlayerName(), this.bgArr[i].getXPos(), this.bgArr[i].getYPos());
			}
		}
		
		for(int i = 0; i < this.mineArr.length; i++)
		{
			g.setColor(Color.DARK_GRAY);
			g.drawOval(this.mineArr[i].getXPos(), this.mineArr[i].getYPos(), this.mineArr[i].getValue(), this.mineArr[i].getValue());
			g.fillOval(this.mineArr[i].getXPos(), this.mineArr[i].getYPos(), this.mineArr[i].getValue(), this.mineArr[i].getValue());
			
			g.setColor(Color.white);
			
			g.drawOval(this.mineArr[i].getXPos() + this.mineArr[i].getValue()/2, this.mineArr[i].getYPos() + this.mineArr[i].getValue()/2, 2, 2);
			g.fillOval(this.mineArr[i].getXPos() + this.mineArr[i].getValue()/2, this.mineArr[i].getYPos() + this.mineArr[i].getValue()/2, 2, 2);
		}
		


		//draw player
		g.setColor(Color.black);
		g.drawOval(this.player.getXPos() - scaleFactor/2, this.player.getYPos() - scaleFactor/2, this.player.getSize(), this.player.getSize());
		g.fillOval(this.player.getXPos() - scaleFactor/2, this.player.getYPos() - scaleFactor/2, this.player.getSize(), this.player.getSize());
		
		g.setColor(Color.white);
		
		g.drawOval(this.player.getXPos() - scaleFactor/2 + this.player.getSize()/2, this.player.getYPos() - scaleFactor/2 + this.player.getSize()/2, 2, 2);
		g.fillOval(this.player.getXPos() - scaleFactor/2 + this.player.getSize()/2, this.player.getYPos() - scaleFactor/2 + this.player.getSize()/2, 2, 2);

		
		try {
			Thread.sleep(5);
			moveBadGuy();
			move();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getMagnitude()
	{
		double originX = this.player.getXPos() + 15;
		double originY = this.player.getYPos() + 15; 
		
		double mouseX = MouseInfo.getPointerInfo().getLocation().getX();
		double mouseY = MouseInfo.getPointerInfo().getLocation().getY();
		
		int distance = (int) Math.sqrt((Math.pow((originX - mouseX), 2)) + (Math.pow((originY - mouseY), 2))); //distacne between the pointer and the player
	
		return distance;
	}
	
	
	public void move() throws InterruptedException
	{
	
		double mouseX = MouseInfo.getPointerInfo().getLocation().getX();
		double mouseY = MouseInfo.getPointerInfo().getLocation().getY();

		//make sure to import 
		
		int speed = (getMagnitude()/40);//just for now make it 1

		if(mouseY < this.player.getYPos())
		{
			yVelocity = 2;
			for(int i = 0; i < this.pieceArr.length; i++)
			{
				if(i < this.mineArr.length)
				{
					this.mineArr[i].setYPos(this.mineArr[i].getYPos() + yVelocity);		
				}
				if(this.pieceArr[i]!=null)
				{
					this.pieceArr[i].setYPos(this.pieceArr[i].getYPos() + yVelocity);	
				}
			}
			repaint();
		}
		if(mouseY > this.player.getYPos())
		{
			yVelocity = -2;
			for(int i = 0; i < this.pieceArr.length; i++)
			{
				if(i < this.mineArr.length)
				{
					this.mineArr[i].setYPos(this.mineArr[i].getYPos() + yVelocity);		
				}	
				if(this.pieceArr[i]!=null)
				{
					this.pieceArr[i].setYPos(this.pieceArr[i].getYPos() + yVelocity);
				}
			}
			repaint();
		}
		if(mouseX < this.player.getXPos())
		{
			xVelocity = 2;
			for(int i = 0; i < this.pieceArr.length; i++)
			{
			    if(i < this.mineArr.length)
			    {
			    	this.mineArr[i].setXPos(this.mineArr[i].getXPos() + xVelocity);
			    }
				if(this.pieceArr[i]!=null)
				{
					this.pieceArr[i].setXPos(this.pieceArr[i].getXPos() + xVelocity);
				}
			}
			repaint();
		}
		if(mouseX > this.player.getXPos())
		{
			xVelocity = -2;
			for(int i = 0; i < this.pieceArr.length; i++)
			{
				if(i < this.mineArr.length)
				{
					this.mineArr[i].setXPos(this.mineArr[i].getXPos() + xVelocity);	
				}
				if(this.pieceArr[i]!=null)
				{
					this.pieceArr[i].setXPos(this.pieceArr[i].getXPos() + xVelocity);			
				}
			}
			repaint();
		}
		
	}
	
	public void moveBadGuyRandom() throws InterruptedException
	{
		velocityChanger++;
		for(int j = 0; j < this.bgArr.length; j++)
		{	
			if(this.bgArr[j] != null && velocityChanger == 200 && this.bgArr[j].isFollowing() == false)
			{
				for(int i = 0; i < this.bgArr.length; i++)
				{
					if(this.bgArr[i] != null)
					{	this.bgArr[i].setXVelocity((int) (Math.random() * 2) * 2 - 1);
					this.bgArr[i].setYVelocity((int) (Math.random() * 2) * 2 - 1);
					}
				}
				velocityChanger = 0;
			}
		}

		for(int i = 0; i < this.bgArr.length; i++)
		{
			if(this.bgArr[i]!=null)
			{
				this.bgArr[i].setXPos(this.bgArr[i].getXPos() + this.bgArr[i].getXVelocity());
				this.bgArr[i].setYPos(this.bgArr[i].getYPos() + this.bgArr[i].getYVelocity());
			}
		}
		repaint();
	}
	
	public void moveBadGuy()
	{
		int originX = this.player.getXPos() + 15;
		int originY = this.player.getYPos() + 15; 

		for(int i = 0; i < this.bgArr.length; i++)
		{
			if(this.bgArr[i] != null)
			{
				if((int)(Math.sqrt((Math.pow((originX - this.bgArr[i].getXPos()), 2)) + (Math.pow((originY - this.bgArr[i].getYPos()), 2)))) <= 150)
				{
					try {
						follow(i, this.bgArr[i].getXPos(), originX, this.bgArr[i].getYPos(), originY);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else
			{
				try {
					moveBadGuyRandom();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static boolean checkIfGameOver()
	{
		if(scaleFactor < 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void follow(int index, int x1, int x2, int y1, int y2) throws InterruptedException {

		
		int m = 0; 
		int b = 0;
		int y = 0;
		
		//X1 AND Y1 WILL BE THE BAD GUYS COORDS
		if((x2 != 0 || x1!=0))
		{
			m = (y2 - y1)/(x2 - x1); //find the slope of the line connecting the player and the bad guy
			
			b = y1 + (m*x1); //find the y intercept of the line (technichally dont need but still need it to move the bad gut along the "path")
			
			y = m*x1 + b; //find the new y value of the bad guy along the line
		}
		
		if(x1 < x2)
		{
			this.bgArr[index].setXPos(this.bgArr[index].getXPos() + 1);
			this.bgArr[index].setYPos(y);
		}
		
		if(x1 > x2)
		{
			this.bgArr[index].setXPos(this.bgArr[index].getXPos() - 1);
			this.bgArr[index].setYPos(y);
		}
		
		repaint();
	}
}
