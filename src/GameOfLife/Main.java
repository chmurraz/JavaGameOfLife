package GameOfLife;

/*
 * Inspired by my own C++ code and ...
 * https://www.youtube.com/watch?v=lIJOuUZROo8
 * Left off at approx 36:00
 */
import java.awt.Graphics;

public class Main {

	public static void main(String[] args) 
	{
		//System.out.println(f.getWidth() + ";" + f.getHeight());
		
		Game game = new Game();
		
		//game.getFrame().CreateScreen();		//	Added this into the constructor for Frame()
		game.Run();
		
	}

}
