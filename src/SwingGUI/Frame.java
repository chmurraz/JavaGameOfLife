package SwingGUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import GameOfLife.Game;

public class Frame extends JFrame
{
	private GameScreen gameScreen;
	private UserPanel userPanel;
	private GameRunner gameRunner;
	private Boolean running;
	private MyListener myListener;
	
	//	TESTING... changing second argument of GameRunner to Blob
	private class GameRunner extends SwingWorker<Void,Void>
	{
		
		private long lastFrameTime;
		private long thisFrameTime;
		private float tslf;
		private float tslu;
		
		private float PAUSETIME = 0.05f;
		
		@Override
		protected Void doInBackground() throws Exception 
		{
			lastFrameTime = System.currentTimeMillis();
			tslu = PAUSETIME * 2;			
			tslf = (float) ((thisFrameTime - lastFrameTime) / 1000.0);
			lastFrameTime = thisFrameTime;
			thisFrameTime = System.currentTimeMillis();

			//	tslf = time since last frame
			tslu += tslf;
			gameScreen.getBlob().UpdateBlob();
			tslu = 0;
			
			SwingUtilities.invokeLater(new Runnable()
			{
				public void run()
				{
					Repaint();
				}
			});
			
			return null;
		}
		
		@Override
		protected void done()
		{
			int age = gameScreen.getBlob().getAge();
			int count = gameScreen.getBlob().getLiveCellCount();
			userPanel.updateAgeLabel(age);
			userPanel.updateCountLabel(count);
			userPanel.updateXRangeLabel(gameScreen.getBlob().getBoundary().getMinX(), gameScreen.getBlob().getBoundary().getMaxX());
			userPanel.updateYRangeLabel(gameScreen.getBlob().getBoundary().getMinY(), gameScreen.getBlob().getBoundary().getMaxY());
			userPanel.getAdvanceToggleButton().setSelected(false);
			userPanel.getAdvanceToggleButton().setText("Advance Simulation");
			if(running)
			{
				Execute();
			}
		}
	}
	
	public Frame(String title)
	{
		super(title);
		gameScreen = new GameScreen();
		running = false;

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		//	Set size of "this" Frame
		setSize(new Dimension(Game.getWidth()/2,Game.getHeight()));
		
		//	Set size of the gameScreen
		gameScreen.setBounds(0,0,Game.getWidth()/2,Game.getHeight()/2);
		//gameScreen.getBlob().BuildRandom(0.5);
		add(gameScreen);
		
		userPanel = new UserPanel();
		myListener = new MyListener(this);
		userPanel.getAdvanceToggleButton().addActionListener(myListener);
		userPanel.getLoadBlobButton().addActionListener(myListener);
		userPanel.getAutoRunToggleButton().addActionListener(myListener);
				
		//	Add Swing components to content pane
		Container c = getContentPane();
		
		//c.add(textArea, BorderLayout.CENTER);
		c.add(gameScreen, BorderLayout.CENTER);
		c.add(userPanel,BorderLayout.WEST);
	}
	
	public void Repaint()
	{
		gameScreen.repaint();
	}
	
	public GameScreen getGameScreen()
	{
		return gameScreen;
	}
	
	public UserPanel getUserPanel()
	{
		return userPanel;
	}
	
	public GameRunner getGameRunner()
	{
		return gameRunner;
	}
	
	public void Execute()
	{
		(gameRunner = new GameRunner()).execute();
	}
	
	public void Cancel()
	{
		gameRunner.cancel(true);
		gameRunner = null;
	}
	
	public Boolean getRunning()
	{
		return running;
	}
	
	public void setRunning(Boolean runVal)
	{
		running = runVal;
	}
	
}
