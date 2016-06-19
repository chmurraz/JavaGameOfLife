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
import javax.swing.event.EventListenerList;

public class UserPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private EventListenerList listenerList = new EventListenerList();
	
	public UserPanel()
	{
		Dimension size = getPreferredSize();
		size.width = 250;
		setPreferredSize(size);
		
		//	Adds a beveled border
		setBorder(BorderFactory.createTitledBorder("Personal Details"));
		
		JLabel nameLabel = new JLabel("Name: ");
		JLabel occupationLabel = new JLabel("Occupation: ");
		
		//	Parameter is the number of columns for the textfield
		JTextField nameField = new JTextField(10);
		JTextField occupationField = new JTextField(10);
		
		JButton addBtn = new JButton("Add");
		
		addBtn.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						String name = nameField.getText();
						String occupation = occupationField.getText();
						
						String text = name + ": " + occupation + "\n";
						
						//	fire event using "this" panel and the text
						fireUserEvent(new UserEvent(this,text));
					}
			
				});
		
		//	GridBagLayout lets you add controls in conjunction with a GridBagConstraints class
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		////	First Column (x = 0)	////
		
		gc.anchor = GridBagConstraints.LINE_END;	//	Right justify
		gc.weightx = 0.5;	//	Weights do not have to add up to 1.0
		gc.weighty = 0.5;
		
		gc.gridx = 0;	//	x increasing to right
		gc.gridy = 0;	//	y increasing downwards
		add(nameLabel,gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		add(occupationLabel, gc);
		
		////	Second Column (x = 1)	////
		
		gc.anchor = GridBagConstraints.LINE_START;	//	Left justify
		gc.gridx = 1;
		gc.gridy = 0;
		add(nameField, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		add(occupationField, gc);
		
		////	Final Row	////
		
		//gc.fill = GridBagConstraints.BOTH;	//	This is a good option for some text fields
		gc.weighty = 10;	//	Weight very high to make this "row" take up a lot of space
		gc.anchor = GridBagConstraints.FIRST_LINE_START;	//	Top justify
		gc.gridx = 1;
		gc.gridy = 2;
		add(addBtn,gc);		
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
	
	public void addUserListener(UserListener listener)
	{
		listenerList.add(UserListener.class, listener);
	}
	
	public void removeUserListener(UserListener listener)
	{
		listenerList.remove(UserListener.class, listener);
	}

}
