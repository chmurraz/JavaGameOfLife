package GameOfLife;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class BlobJFrame extends JFrame
{
	public BlobJFrame()
	{
		super("Title of window");
		setSize(600,600);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setLayout( new FlowLayout());
		//JButton button = new JButton("Click Me");
		//add(button);
	}
	
}
