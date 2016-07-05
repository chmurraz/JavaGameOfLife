package SwingGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LoadCellPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JButton LoadGlider;
	private JButton LoadOscillator;
	private JButton LoadBlock;
	private JButton LoadRandom;
	private JButton LoadMethuselah;
	private JButton LoadAcorn;
	private JButton LoadSimple;
	private JButton LoadKokGalaxy;

	private JButton ClearAll;
	private GridBagConstraints constraints;

	public LoadCellPanel()
	{
		Dimension size = getPreferredSize();
		size.height = 250;
		setPreferredSize(size);
		setBorder(BorderFactory.createTitledBorder("Cell Load Options"));
		setBackground(Color.WHITE);
		
		setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.gridy = 0;
		constraints.insets = new Insets(5,5,5,5);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		
		constraints.gridx = 0;
		LoadGlider = new JButton("Load Glider");
		add(LoadGlider, constraints);
		LoadGlider.setActionCommand("Load Glider");
		LoadGlider.setVisible(true);
		
		constraints.gridx = 1;
		LoadOscillator = new JButton("Load Oscillator");
		add(LoadOscillator, constraints);
		LoadOscillator.setActionCommand("Load Oscillator");
		LoadOscillator.setVisible(true);
		
		constraints.gridx = 2;
		LoadBlock = new JButton("Load Block");
		add(LoadBlock, constraints);
		LoadBlock.setActionCommand("Load Block");
		LoadBlock.setVisible(true);
		
		constraints.gridx = 3;
		LoadRandom = new JButton("Load Random");
		add(LoadRandom, constraints);
		LoadRandom.setActionCommand("Load Random");
		LoadRandom.setVisible(true);
		
		constraints.gridx = 4;
		LoadMethuselah = new JButton("Load Methuselah");
		add(LoadMethuselah, constraints);
		LoadMethuselah.setActionCommand("Load Methuselah");
		LoadMethuselah.setVisible(true);
		
		constraints.gridy = 1;
		constraints.gridx = 0;
		LoadAcorn = new JButton("Load Acorn");
		add(LoadAcorn, constraints);
		LoadAcorn.setActionCommand("Load Acorn");
		LoadAcorn.setVisible(true);
		
		constraints.gridx = 1;
		LoadSimple = new JButton("Load Simple");
		add(LoadSimple, constraints);
		LoadSimple.setActionCommand("Load Simple");
		LoadSimple.setVisible(true);
		
		constraints.gridx = 2;
		LoadKokGalaxy = new JButton("Load Kok Galaxy");
		add(LoadKokGalaxy, constraints);
		LoadKokGalaxy.setActionCommand("Load Kok Galaxy");
		LoadKokGalaxy.setVisible(true);
		
		constraints.gridx = 3;
		ClearAll = new JButton("Clear All");
		add(ClearAll, constraints);
		ClearAll.setActionCommand("Clear All");
		ClearAll.setVisible(true);
	}
	
	public JButton getLoadGlider() {
		return LoadGlider;
	}

	public JButton getLoadOscillator() {
		return LoadOscillator;
	}

	public JButton getLoadBlock() {
		return LoadBlock;
	}

	public JButton getLoadRandom() {
		return LoadRandom;
	}

	public JButton getLoadMethuselah() {
		return LoadMethuselah;
	}

	public JButton getLoadAcorn() {
		return LoadAcorn;
	}

	public JButton getLoadSimple() {
		return LoadSimple;
	}

	public JButton getLoadKokGalaxy() {
		return LoadKokGalaxy;
	}

	public JButton getClearAll() {
		return ClearAll;
	}

}
