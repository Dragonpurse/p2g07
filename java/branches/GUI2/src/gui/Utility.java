package gui;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public abstract class Utility {
	
	public static void addSeparator(String text, JPanel panel)
	{
		JLabel l = new JLabel(text);
		l.setForeground(new Color(35,100,240));

		panel.add(l, "gapbottom 1, span, split 2, aligny center");
		panel.add((new JSeparator()), "gapleft rel, growx");
	}	

}
