package GameOfLife;

import javax.swing.SwingUtilities;

///	To do list...
/*
 * fix image tearing on panels
 * fix concurrent modification of cellsInGame arrrayList
 * add double buffering on panels/frames
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

		//game.getFrame().CreateScreen();		//	Added this into the constructor for Frame()
		//game.Run();
	}
	
	/*
	 * Program work flow...
	 * declare Game game in main and game.Run()
	 * 
	 * Game.Run()
	 * 		Frame..... blob.updateBlob()
	 * 				ResetBlobStats()
	 * 				BuildDeadCells()
	 * 				CountNeighbors()
	 * 				BirthDeath()
	 * 				age++
	 * 		Frame.repaint()
	 * 				gameScreen.repaint();
	 * 				
	 * 				
	 * 
	 * 
	 * 		Frame..... userPanel.updateAgeLabel()
	 * 		Frame..... userPanel.updateCountLabel();
	 * 
	 * 
	 */
	
	//	Double buffering work flow
	/*
	 * declare Image image and Graphics bufferGraphics object
	 * image = createImage(dim.width, dim.height)
	 * bufferGraphics = image.getGraphics()
	 * bufferGraphics.clear
	 */

}
