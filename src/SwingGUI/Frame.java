package SwingGUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

import GameOfLife.Blob;
import GameOfLife.Game;

public class Frame extends JFrame
{
	//private Screen s;
	private GameScreen gameScreen;
	private UserPanel userPanel;
	private GameRunner gameRunner;
	private MyListener myListener;
	private Boolean running;
	//private Blob blob;
	//private Simulation sim;
	
	/*
	private class Screen extends JLabel
	{
		@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			blob.Draw(g);
			blob.UpdateBlob();
		}
	}
	*/
	
	/*
	 * 
	 * FIX THIS BASED ON...
	 * http://docs.oracle.com/javase/tutorial/uiswing/examples/concurrency/FlipperProject/src/concurrency/Flipper.java
	 * 
	 */
	
	//	May possibly use this private class (does it need to be static?) if I decide to use publish in the GameRunner
	private class BlobPublishObject
	{
		private final Blob blob;
		BlobPublishObject(Blob blobVal)
		{
			blob = blobVal;
		}
		
		Blob getBlob()
		{
			return blob;
		}
	}
	
	//	TESTING... changing second argument of GameRunner to Blob
	private class GameRunner extends SwingWorker<Void,BlobPublishObject>
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
			
			/*
			while(!isCancelled())
			{
				tslf = (float) ((thisFrameTime - lastFrameTime) / 1000.0);
				lastFrameTime = thisFrameTime;
				thisFrameTime = System.currentTimeMillis();

				//	tslf = time since last frame
				tslu += tslf;
				if (tslu > PAUSETIME) 
				{
					//	Update the blob and repaint
					gameScreen.getBlob().UpdateBlob();
					Repaint();

					//	Update the user panel
					int age = gameScreen.getBlob().getAge();
					int count = gameScreen.getBlob().getLiveCellCount();
					userPanel.updateAgeLabel(age);
					userPanel.updateCountLabel(count);

					tslu = 0;
				}
			}
			*/
			
			tslf = (float) ((thisFrameTime - lastFrameTime) / 1000.0);
			lastFrameTime = thisFrameTime;
			thisFrameTime = System.currentTimeMillis();

			//	tslf = time since last frame
			tslu += tslf;
			//if (tslu > PAUSETIME) 
			//{
				//	Update the blob and repaint
				gameScreen.getBlob().UpdateBlob();
				//Repaint();

				//	Update the user panel
				//int age = gameScreen.getBlob().getAge();
				//int count = gameScreen.getBlob().getLiveCellCount();
				//userPanel.updateAgeLabel(age);
				//userPanel.updateCountLabel(count);

				tslu = 0;
			//}
			
			return null;
		
		}
		
		public void done()
		{
			int age = gameScreen.getBlob().getAge();
			int count = gameScreen.getBlob().getLiveCellCount();
			Repaint();
			userPanel.updateAgeLabel(age);
			userPanel.updateCountLabel(count);
			userPanel.getAdvanceToggleButton().setSelected(false);
			userPanel.getAdvanceToggleButton().setText("Advance Simulation");
		}
	
		
	}
	
	/*
	public void actionPerformed(ActionEvent e)
	{
		String caption;
		if ("Start Simulation" == e.getActionCommand())
		{
			caption = "Stop Simulation";
			(gameRunner = new GameRunner()).execute();
			userPanel.getToggleButton1().setText(caption);
			userPanel.getToggleButton1().setActionCommand(caption);
		}
		if("Stop Simulation" == e.getActionCommand())
		{
			caption = "Start Simulation";
			gameRunner.cancel(true);
			gameRunner = null;
			userPanel.getToggleButton1().setText(caption);
			userPanel.getToggleButton1().setActionCommand(caption);
		}	
	}
	*/
	
	public Frame(String title)
	{
		super(title);
		//blob = new Blob();
		//s = new Screen();
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
		
		userPanel.addUserListener(new UserListener ()
		{
			public void detailEventOccured(UserEvent event)
			{
				//	Get the text from the event
				//String text = event.getText();
				
				//	Append this text into the text panel
				//textArea.append(text);
			}
		});
		
		//	Add Swing components to content pane
		Container c = getContentPane();
		
		//c.add(textArea, BorderLayout.CENTER);
		c.add(gameScreen, BorderLayout.CENTER);
		c.add(userPanel,BorderLayout.WEST);
	}
	
	
	//	Is CreateScreen() necessary?
	/*
	public void CreateScreen()
	{
		s = new Screen();
		//sim = new Simulation();
		//blob = new Blob();
		s.setBounds(0,0,Main.width,Main.height);
		add(s);
	}
	*/
	
	public void Repaint()
	{
		gameScreen.repaint();
	}
	
	/*
	public Blob getBlob()
	{
		return blob;
	}
	*/
	
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
