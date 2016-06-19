package GameOfLife;

/*
 * Inspired by my own C++ code and various tutorials on Java Swing
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
