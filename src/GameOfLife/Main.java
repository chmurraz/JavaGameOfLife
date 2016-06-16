package GameOfLife;

/*
 * Inspired by my own C++ code and ...
 * https://www.youtube.com/watch?v=lIJOuUZROo8
 * Left off at approx 36:00
 */

public class Main {

	static int width;
	static int height;
	
	public static void main(String[] args) 
	{
		Frame f = new Frame();
		//f.setUndecorated(true);
		f.setVisible(true);
		f.setResizable(false);

		
		//System.out.println(f.getWidth() + ";" + f.getHeight());
		width = f.getWidth();
		height = f.getHeight();
		
		f.CreateScreen();
		
		long lastFrame = System.currentTimeMillis();
		while(true)
		{
			long thisFrame = System.currentTimeMillis();
			float tslf = (float)((thisFrame - lastFrame)/1000.0);
			lastFrame = thisFrame;
			
			f.Update(tslf);
			f.Repaint();
			
			try
			{
				Thread.sleep(10);
			}catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
	}

}
