package SwingGUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.EventListenerList;

public class UserPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private EventListenerList listenerList = new EventListenerList();
	
	private JLabel ageLabel;
	private JLabel countLabel;
	private JLabel xRangeLabel;
	private JLabel yRangeLabel;
	private JButton loadBlobButton;
	private JButton refreshButton;
	private JToggleButton advanceToggleButton;
	private GridBagConstraints constraints;
	
	public UserPanel()
	{
		Dimension size = getPreferredSize();
		size.width = 250;
		setPreferredSize(size);
		
		//	Adds a beveled border
		setBorder(BorderFactory.createTitledBorder("Simulation Data"));
		
		ageLabel = new JLabel("Blob Age: ");
		countLabel = new JLabel("Cell Count: ");
		xRangeLabel = new JLabel("? <= x <= ?");
		yRangeLabel = new JLabel("? <= y <= ?");
		
		//	Parameter is the number of columns for the textfield
		//JTextField ageField = new JTextField(10);
		//JTextField countField = new JTextField(10);
		
		//startBtn = new JButton("Start Simulation");
		loadBlobButton = new JButton("Load Game");
		refreshButton = new JButton("Refresh");
		advanceToggleButton = new JToggleButton("Advance Simulation");
		
		advanceToggleButton.setActionCommand("Advance Simulation");
		advanceToggleButton.setFocusPainted(false);		//	Removes annoying border

		/*
		startBtn.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						//String age = ageField.getText();
						//String count = countField.getText();
						
						//String text = age + ": " + count + "\n";
						String text = "hey guy";
						
						//	fire event using "this" panel and the text
						fireUserEvent(new UserEvent(this,text));
					}
				});
		*/
		
		/*
		startToggleButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) 
					{
						if (startToggleButton.getText() == "Start Simulation")
						{
							startToggleButton.setText("Stop Simulation");
						}
						
						else if (startToggleButton.getText() == "Stop Simulation")
						{
							startToggleButton.setText("Start Simulation");
						}
					}
				});
		*/
			
		
		//	GridBagLayout lets you add controls in conjunction with a GridBagConstraints class
		setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		
		////	First Column (x = 0)	////
		
		constraints.anchor = GridBagConstraints.LINE_END;	//	Right justify
		constraints.weightx = 0.5;	//	Weights do not have to add up to 1.0
		constraints.weighty = 0.5;
		
		constraints.gridx = 0;	//	x increasing to right
		constraints.gridy = 0;	//	y increasing downwards
		add(ageLabel,constraints);
		ageLabel.setVisible(false);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		add(countLabel, constraints);
		countLabel.setVisible(false);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		add(xRangeLabel,constraints);
		xRangeLabel.setVisible(false);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		add(yRangeLabel,constraints);
		yRangeLabel.setVisible(false);
		
		constraints.weighty = 3;
		constraints.gridx = 0;
		constraints.gridy = 4;
		add(advanceToggleButton,constraints);
		advanceToggleButton.setVisible(false);
		
		constraints.weighty = 3;
		constraints.gridx = 0;
		constraints.gridy = 5;
		add(loadBlobButton, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 6;
		add(refreshButton, constraints);
		
		////	Second Column (x = 1)	////
		
		//gc.anchor = GridBagConstraints.LINE_START;	//	Left justify
		//gc.gridx = 1;
		//gc.gridy = 0;
		//add(ageField, gc);
		
		//gc.gridx = 1;
		//gc.gridy = 1;
		//add(countField, gc);
		
		////	Final Row	////
		
		//gc.fill = GridBagConstraints.BOTH;	//	This is a good option for some text fields
		//gc.weighty = 10;	//	Weight very high to make this "row" take up a lot of space
		//gc.anchor = GridBagConstraints.FIRST_LINE_START;	//	Top justify
		//gc.gridx = 1;
		//gc.gridy = 3;
		//add(startBtn,gc);		
	}
	
	public void fireUserEvent(UserEvent event)
	{
		Object[] listeners = listenerList.getListenerList();
		
		//	Step through the listener array two at a time because
		//	the first of every pair of items is the DetailListener.class
		//	the second 
		for(int i = 0; i<listeners.length; i+=2)
		{
			if(listeners[i]== UserListener.class)
			{
				//	Cast the second item in each pair to a DetailListener
				//	then call the detailEventOccured method
				((UserListener)listeners[i+1]).detailEventOccured(event);
			}
		}
	}
	
	
	//private JButton makeButton(String caption)
	//{
	//	JButton b = new JButton(caption);
	//	b.setActionCommand(caption);
	//	//b.addActionListener(this);
	//	add(b,constraints);
	//	return b;
	//}
	
	public void addUserListener(UserListener listener)
	{
		listenerList.add(UserListener.class, listener);
	}
	
	public void removeUserListener(UserListener listener)
	{
		listenerList.remove(UserListener.class, listener);
	}
	
	public void updateAgeLabel(int ageVal)
	{
		ageLabel.setText("Blob Age: " + ageVal);
	}
	
	public void updateCountLabel(int countVal)
	{
		countLabel.setText("Cell Count: " + countVal);
	}
	
	public void updateXRangeLabel(int val1, int val2)
	{
		xRangeLabel.setText(val1 + "  <= x <= " + val2);
	}
	
	public void updateYRangeLabel(int val1, int val2)
	{
		yRangeLabel.setText(val1 + "  <= x <= " + val2);
	}
	
	public JToggleButton getAdvanceToggleButton()
	{
		return advanceToggleButton;
	}
	
	public JButton getLoadBlobButton()
	{
		return loadBlobButton;
	}
	
	public JLabel getAgeLabel()
	{
		return ageLabel;
	}
	
	public JLabel getCountLabel()
	{
		return countLabel;
	}
	
	public JLabel getXRangeLabel()
	{
		return xRangeLabel;
	}
	
	public JLabel getYRangeLabel()
	{
		return yRangeLabel;
	}
	
	public JButton getRefreshButton()
	{
		return refreshButton;
	}

}
