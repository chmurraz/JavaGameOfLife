package SwingGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import GameOfLife.Cell;
import GameOfLife.Game;
import GameOfLife.IntPoint2D;

public class Frame extends JFrame
{
	private GameScreen gameScreen;
	private UserPanel userPanel;
	private LoadCellPanel loadCellPanel;
	private GameRunner gameRunner;
	private Boolean running;
	private MyListener myListener;
	private GridBagConstraints constraints;
	private BufferedImage image;
	
	//	TESTING... changing second argument of GameRunner to Blob
	private class GameRunner extends SwingWorker<Void,Void>
	{
		private long lastFrameTime;
		private long thisFrameTime;
		private float tslf;
		private float tslu;
		private Graphics g;
		
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
			UpdateLabels();
			if(running)
			{
				Execute();
			}
		}
	}
	
	public void UpdateLabels()
	{
		int age = gameScreen.getBlob().getAge();
		int count = gameScreen.getBlob().getLiveCellCount();
		userPanel.updateAgeLabel(age);
		userPanel.updateCountLabel(count);
		userPanel.updateXRangeLabel(gameScreen.getBlob().getBoundary().getMinX(), gameScreen.getBlob().getBoundary().getMaxX());
		userPanel.updateYRangeLabel(gameScreen.getBlob().getBoundary().getMinY(), gameScreen.getBlob().getBoundary().getMaxY());
		userPanel.getAdvanceToggleButton().setSelected(false);
		userPanel.getAdvanceToggleButton().setText("Advance to Next Generation");
		userPanel.updateEscapedCellsCount(gameScreen.getBlob().getEscapedCells());
		userPanel.updateCentroid(gameScreen.getBlob().getCentroid());
		userPanel.updateAverageDistanceToCentroidLabel(gameScreen.getBlob().getAverageCentroidDistance());
		userPanel.updateVarianceDistanceToCentroidLabel(gameScreen.getBlob().getVarianceCentroidDistance());

	}
	
	public Frame(String title)
	{
		super(title);
		running = false;

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		
		myListener = new MyListener(this);
		userPanel = new UserPanel();
		userPanel.getAdvanceToggleButton().addActionListener(myListener);
		userPanel.getLoadBlobButton().addActionListener(myListener);
		userPanel.getAutoRunToggleButton().addActionListener(myListener);
		userPanel.getShowCentroid().addActionListener(myListener);
		userPanel.getTutorial().addActionListener(myListener);
		userPanel.getUpCellSizeButton().addActionListener(myListener);
		userPanel.getDownCellSizeButton().addActionListener(myListener);
		userPanel.getPanUpButton().addActionListener(myListener);
		userPanel.getPanDownButton().addActionListener(myListener);
		userPanel.getPanLeftButton().addActionListener(myListener);
		userPanel.getRecenterButton().addActionListener(myListener);
		userPanel.getPanRightButton().addActionListener(myListener);
		userPanel.getPanDownButton().addActionListener(myListener);
		
		//loadCellPanel = new LoadCellPanel();
		//loadCellPanel.getLoadGlider().addActionListener(myListener);
		//loadCellPanel.getLoadOscillator().addActionListener(myListener);
		//loadCellPanel.getLoadBlock().addActionListener(myListener);
		//loadCellPanel.getLoadRandom().addActionListener(myListener);
		//loadCellPanel.getLoadMethuselah().addActionListener(myListener);
		//loadCellPanel.getLoadAcorn().addActionListener(myListener);
		//loadCellPanel.getLoadSimple().addActionListener(myListener);
		//loadCellPanel.getLoadKokGalaxy().addActionListener(myListener);
		//loadCellPanel.getClearAll().addActionListener(myListener);
		
		gameScreen = new GameScreen();

		//	Set size of the Frame based on static parameters from Game class
		setSize(new Dimension(Game.getWidth()/2,Game.getHeight()));
		//gameScreen.setBounds(0,0,Game.getWidth()/2,Game.getHeight()/2);
		userPanel.setSize(new Dimension(Game.getWidth()/2, Game.getHeight()/2));
		gameScreen.setSize(new Dimension(Game.getWidth()/2, Game.getHeight()/2));
		
		//	Add Swing components to content pane
		//Container c = getContentPane();
		
		//c.add(gameScreen, BorderLayout.CENTER);
		//c.add(userPanel,BorderLayout.WEST);
		//c.add(loadCellPanel, BorderLayout.EAST);
		//loadCellPanel.setVisible(false);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.fill = GridBagConstraints.BOTH;
		this.getContentPane().add(userPanel,constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.weightx = 3;
		constraints.weighty = 1;
		constraints.anchor = GridBagConstraints.NORTHEAST;
		constraints.fill = GridBagConstraints.BOTH;
		this.getContentPane().add(gameScreen,constraints);
		gameScreen.setVisible(true);
		
		String message = "This is a work in progress.\nPress 'Tutorial' for basic information and detailed instructions";
		message += "\nPress 'Load Cells' to begin loading some starting patterns into the simulation and to unlock other options";
		JOptionPane.showMessageDialog(this, message, "Conway's Game of Life with Java Swing GUI", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void Repaint()
	{
		gameScreen.repaint();
	}
	
	public BufferedImage createImage(JPanel panel)
	{
		int w = panel.getWidth();
		int h = panel.getHeight();
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		panel.print(g);
		return image;
	}
	
	public void Rotate()
	{
		
	}
	
	public GameScreen getGameScreen()
	{
		return gameScreen;
	}
	
	public UserPanel getUserPanel()
	{
		return userPanel;
	}
	
	public LoadCellPanel getLoadCellPanel()
	{
		return loadCellPanel;
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
