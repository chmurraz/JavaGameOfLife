package GameOfLife;

/*
 * Inspired by my own C++ code and ...
 * https://www.youtube.com/watch?v=lIJOuUZROo8
 * Left off at approx 36:00
 */

public class Main {

	static int width;
	static int height;
	static float PAUSETIME = 0.05f;
	
	public static void main(String[] args) 
	{
		//System.out.println(f.getWidth() + ";" + f.getHeight());
		
		Game game = new Game();
		
		width = game.getFrame().getWidth();
		height = game.getFrame().getHeight();
		
		//game.getFrame().CreateScreen();		//	Added this into the constructor for Frame()
		game.Run();
		
	}

}
