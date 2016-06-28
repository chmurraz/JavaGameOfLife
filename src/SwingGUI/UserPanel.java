package SwingGUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class UserPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

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
		
		loadBlobButton = new JButton("Load Game");
		refreshButton = new JButton("Refresh");
		advanceToggleButton = new JToggleButton("Advance Simulation");
		
		advanceToggleButton.setActionCommand("Advance Simulation");
		advanceToggleButton.setFocusPainted(false);		//	Removes annoying border

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
