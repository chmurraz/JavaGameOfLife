package GameOfLife;

///	To do list...
/*
 * fix image tearing on panels
 * fix concurrent modification of cellsInGame area
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

}
