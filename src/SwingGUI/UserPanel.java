package SwingGUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import GameOfLife.Blob.Centroid;

public class UserPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JLabel ageLabel;
	private JLabel countLabel;
	private JLabel xRangeLabel;
	private JLabel yRangeLabel;
	private JLabel centroidLabel;
	private JLabel escapedCellsLabel;
	private JLabel averageDistanceToCentroidLabel;
	private JLabel varianceDistanceToCentroidLabel;
	private JButton loadBlobButton;
	private JToggleButton advanceToggleButton;
	private JToggleButton autoRunToggleButton;
	private GridBagConstraints constraints;
	
	private JPanel statPanel;
	private JPanel buttonPanel;
	
	private DecimalFormat formatter = new DecimalFormat("#.##");
	
	public UserPanel()
	{
		
		Dimension size = getPreferredSize();
		size.width = 250;
		setPreferredSize(size);
		
		setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();

		//	Adds a beveled border
		setBorder(BorderFactory.createTitledBorder("Simulation Data"));
		
		BuildStatPanel();
		
		BuildButtonPanel();
	}

	private void BuildStatPanel() 
	{
		statPanel = new JPanel();
		statPanel.setLayout(new GridBagLayout());
		Border loweredEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		statPanel.setBorder(BorderFactory.createTitledBorder(loweredEtched,"Statistics"));
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.anchor = GridBagConstraints.PAGE_START;
		add(statPanel,constraints);
		
		ageLabel = new JLabel("Blob Age: N/A");
		countLabel = new JLabel("Cell Count: N/A");
		xRangeLabel = new JLabel("N/A <= x <= N/A");
		yRangeLabel = new JLabel("N/A <= y <= N/A");
		centroidLabel = new JLabel("Centroid: N/A");
		escapedCellsLabel = new JLabel("Escaped Cells: N/A");
		averageDistanceToCentroidLabel = new JLabel("Average Distance to Centroid: N/A");
		varianceDistanceToCentroidLabel = new JLabel("Variance of Distance to Centroid: N/A");
		
		constraints.anchor = GridBagConstraints.LINE_END;
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		statPanel.add(ageLabel,constraints);
		ageLabel.setVisible(true);
		
		constraints.gridy = 1;
		statPanel.add(countLabel, constraints);
		countLabel.setVisible(true);
		
		constraints.gridy = 2;
		statPanel.add(escapedCellsLabel,constraints);
		escapedCellsLabel.setVisible(true);
		
		constraints.gridy = 3;
		statPanel.add(xRangeLabel,constraints);
		xRangeLabel.setVisible(true);
		
		constraints.gridy = 4;
		statPanel.add(yRangeLabel,constraints);
		yRangeLabel.setVisible(true);
		
		constraints.gridy = 5;
		statPanel.add(centroidLabel, constraints);
		centroidLabel.setVisible(true);
	
		constraints.gridy = 6;
		statPanel.add(averageDistanceToCentroidLabel, constraints);
		averageDistanceToCentroidLabel.setVisible(true);
		
		constraints.gridy = 7;
		statPanel.add(varianceDistanceToCentroidLabel, constraints);		
		varianceDistanceToCentroidLabel.setVisible(true);
		
	}
	
	private void BuildButtonPanel()
	{
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		buttonPanel.setBorder(BorderFactory.createTitledBorder("User Inputs"));
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.anchor = GridBagConstraints.PAGE_START;
		add(buttonPanel,constraints);
		
		loadBlobButton = new JButton("Load Cells");
		advanceToggleButton = new JToggleButton("Advance Simulation");
		autoRunToggleButton = new JToggleButton("Auto Run: Off");
		
		loadBlobButton.setActionCommand("Load Cells");
		advanceToggleButton.setActionCommand("Advance Simulation");
		autoRunToggleButton.setActionCommand("Auto Run");
		
		//	Remove annoying border on buttons
		loadBlobButton.setFocusPainted(false);
		advanceToggleButton.setFocusPainted(false);
		autoRunToggleButton.setFocusPainted(false);
		
		constraints.gridx = 0;
		constraints.gridy = 6;
		buttonPanel.add(advanceToggleButton,constraints);
		advanceToggleButton.setVisible(true);
		
		constraints.gridx = 0;
		constraints.gridy = 7;
		buttonPanel.add(loadBlobButton, constraints);	
		
		constraints.gridx = 0;
		constraints.gridy = 8;
		buttonPanel.add(autoRunToggleButton, constraints);
		autoRunToggleButton.setVisible(true);
	}
	
	public void updateAgeLabel(int ageVal)
	{
		ageLabel.setText("Blob Age: " + ageVal);
	}
	
	public void updateCentroid(Centroid point)
	{
		centroidLabel.setText("Centroid: " + "(" + formatter.format(point.getX()) + ", " + formatter.format(point.getY()) + ")");
	}
	
	public void updateCountLabel(int countVal)
	{
		countLabel.setText("Cell Count: " + countVal);
	}
	
	public void updateAverageDistanceToCentroidLabel(double averageVal)
	{
		averageDistanceToCentroidLabel.setText("Average Distance to Centroid: " + formatter.format(averageVal));
	}
	
	public void updateVarianceDistanceToCentroidLabel(double varVal)
	{
		varianceDistanceToCentroidLabel.setText("Variance of Distance to Centroid: " + formatter.format(varVal));
	}
	
	public void updateXRangeLabel(int val1, int val2)
	{
		xRangeLabel.setText(val1 + "  <= x <= " + val2);
	}
	
	public void updateYRangeLabel(int val1, int val2)
	{
		yRangeLabel.setText(val1 + "  <= y <= " + val2);
	}
	
	public void updateEscapedCellsCount(int val)
	{
		escapedCellsLabel.setText("Escaped Cells: " + val);
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
	
	public JLabel getEscapedCellsLabel()
	{
		return escapedCellsLabel;
	}
	
	public JToggleButton getAutoRunToggleButton()
	{
		return autoRunToggleButton;
	}
}
