package GameOfLife;

///	To do list...
/*
 * fix image tearing on panels
 * fix concurrent modification of cellsInGame arrrayList
 * add double buffering on panels/frames
 */


public class Main {

	public static void main(String[] args) 
	{
		//System.out.println(f.getWidth() + ";" + f.getHeight());
		Game game = new Game();
		
		//game.getFrame().CreateScreen();		//	Added this into the constructor for Frame()
		game.Run();
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
