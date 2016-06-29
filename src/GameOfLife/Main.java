package GameOfLife;

import javax.swing.SwingUtilities;

/*
 * 	Implementation of Conway's Game of Life
 * 	using my own C++ code and Java Swing
 */

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