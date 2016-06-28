package GameOfLife;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) 
	{
		Game game = new Game();
		SwingUtilities.invokeLater(new Runnable()
			{
				@Override
				public void run() 
				{
					game.CreateAndShowGUI();
				}
			});
	}
}