package GameOfLife;

public class Game
{
	private long lastFrameTime;
	private long thisFrameTime;
	private float tslf;
	private float tslu;
	
	private Frame frame;
	
	public Game()
	{
		frame = new Frame();
		frame.setVisible(true);
		frame.setResizable(false);
		tslu = 0;
		tslf = System.currentTimeMillis();
		frame.getBlob().BuildRandom(0.5);
	}
	
	public void Run()
	{
		lastFrameTime = System.currentTimeMillis();
		
		while(true)
		{
			thisFrameTime = System.currentTimeMillis();
			tslf = (float)((thisFrameTime-lastFrameTime)/1000.0);
			lastFrameTime = thisFrameTime;
			
			getFrame().getBlob().UpdateBlob(tslf);
			getFrame().Repaint();
			
			try
			{
				Thread.sleep(10);
			}catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
	public Frame getFrame()
	{
		return frame;
	}
	
}
