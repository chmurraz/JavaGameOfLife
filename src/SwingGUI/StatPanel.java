package SwingGUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class StatPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JLabel ageLabel;
	private JLabel countLabel;
	private JLabel xRangeLabel;
	private JLabel yRangeLabel;
	private JLabel centroidLabel;
	private JButton loadBlobButton;
	private JToggleButton advanceToggleButton;
	private JToggleButton autoRunToggleButton;
	private GridBagConstraints constraints;
	
	public StatPanel()
	{
		Dimension size = getPreferredSize();
		size.width = 250;
		setPreferredSize(size);
		
		//	Adds a beveled border
		setBorder(BorderFactory.createTitledBorder("Statistics Data"));
		
		ageLabel = new JLabel("Blob Age: ");
		countLabel = new JLabel("Cell Count: ");
		xRangeLabel = new JLabel("? <= x <= ?");
		yRangeLabel = new JLabel("? <= y <= ?");
		centroidLabel = new JLabel("Centroid: ");
		
		loadBlobButton = new JButton("Load Cells");
		advanceToggleButton = new JToggleButton("Advance Simulation");
		autoRunToggleButton = new JToggleButton("Auto Run: Off");
		
		loadBlobButton.setActionCommand("Load Cells");
		advanceToggleButton.setActionCommand("Advance Simulation");
		autoRunToggleButton.setActionCommand("Auto Run");
		
		loadBlobButton.setFocusPainted(false);
		advanceToggleButton.setFocusPainted(false);		//	Removes annoying border
		autoRunToggleButton.setFocusPainted(false);
		
		/*
		 * 	Listeners are added in FRAME.JAVA !!
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
		ageLabel.setVisible(true);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		add(countLabel, constraints);
		countLabel.setVisible(true);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		add(xRangeLabel,constraints);
		xRangeLabel.setVisible(true);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		add(yRangeLabel,constraints);
		yRangeLabel.setVisible(true);
		
		constraints.weighty = 3;
		constraints.gridx = 0;
		constraints.gridy = 4;
		add(advanceToggleButton,constraints);
		advanceToggleButton.setVisible(true);
		
		constraints.weighty = 3;
		constraints.gridx = 0;
		constraints.gridy = 5;
		add(loadBlobButton, constraints);	
		
		constraints.weighty = 3;
		constraints.gridx = 0;
		constraints.gridy = 6;
		add(autoRunToggleButton, constraints);
		autoRunToggleButton.setVisible(true);
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
		yRangeLabel.setText(val1 + "  <= y <= " + val2);
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
	
	public JToggleButton getAutoRunToggleButton()
	{
		return autoRunToggleButton;
	}
}