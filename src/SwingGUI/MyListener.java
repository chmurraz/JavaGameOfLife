package SwingGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyListener implements ActionListener
{
	private final Frame frame;
	
	public MyListener(Frame frameVal)
	{
		frame = frameVal;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String command = frame.getUserPanel().getToggleButton1().getActionCommand();
		if(e.getActionCommand().equals(command))
		{
			if(frame.getUserPanel().getToggleButton1().getText() == "Start Simulation")
			{
				frame.getUserPanel().getToggleButton1().setText("Stop Simulation");
				frame.Execute();
			}
			else if(frame.getUserPanel().getToggleButton1().getText() == "Stop Simulation")
			{
				frame.getUserPanel().getToggleButton1().setText("Start Simulation");
				frame.Cancel();
			}
		}
	}

}
