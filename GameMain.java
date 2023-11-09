package Game;

public class GameMain {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		GameScreen gameScreen = new GameScreen(700,700);
		
		int gameOver = 1; 
		
		while(gameOver!=0)
		{
			//System.out.println("loop running"); //FOR SOME REASON IF U GET RID OF THIS LINE IT DOES NOT EXIT THE PROGRAM LIKE WTF
			if(gameScreen.getPanel().checkIfGameOver())
			{
				gameOver = 0;
			}
			else
			{
				gameOver = 1;
			}
		}
		System.out.println("exited loop");
		gameScreen.dispose();
		
		EndScreen endScreen = new EndScreen();
	} 
}