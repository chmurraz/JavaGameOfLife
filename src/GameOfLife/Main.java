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
		//System.out.println(f.getWidth() + ";" + f.getHeight());
		
		Blob myBlob = new Blob();
		myBlob.BuildRandom(0.5);
		width = myBlob.getFrame().getWidth();
		height = myBlob.getFrame().getHeight();
		
		myBlob.getFrame().CreateScreen();
		
		long lastFrame = System.currentTimeMillis();
		while(true)
		{
			long thisFrame = System.currentTimeMillis();
			float tslf = (float)((thisFrame - lastFrame)/1000.0);
			lastFrame = thisFrame;
			
			myBlob.getFrame().Update(tslf);
			myBlob.getFrame().Repaint();
			
			try
			{
				Thread.sleep(10);
			}catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
				
		/*
		 * CHRIS MAIN CODE BELOW
		 * REMOVE THE COMMENT BLOCK
		 	
		 
	 		Blob myblob = new Blob();
			myblob.BuildRandom(0.05);
	
			for (int i = 0; i <= 500; i++)
			{
				myblob.UpdateBlob();
				Thread.sleep(500);
			}
			
		 */
		
	}

}
