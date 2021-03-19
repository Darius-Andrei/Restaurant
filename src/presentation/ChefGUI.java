package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bll.Restaurant;

public class ChefGUI implements Observer
{
	private Restaurant restaurant;
	private JFrame mwJFrame;
	private JFrame mainFrame;
	private JButton backButton;
	private JPanel panou;
	
	public ChefGUI(Restaurant rest, JFrame mwJFrame2)
	{
		this.restaurant = rest;
		this.mwJFrame = mwJFrame2;
		
		restaurant.addObserver(this);
		
		mainFrame = new JFrame("Chef panel");
		mainFrame.setBounds(50, 50, 640, 480);
		mainFrame.setVisible(false);
		
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				mainFrame.setVisible(false);
				mwJFrame.setVisible(true);
			}
		});
		
		panou = new JPanel();
		panou.add(backButton);
		mainFrame.add(panou);
	}
	
	public JFrame getMainFrame()
	{
		return this.mainFrame;
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(arg.toString());
		int valid = JOptionPane.showConfirmDialog(null, "O noua comanda!");
		if(valid == 0)
		{
			System.out.println("Bucatarul e pregatit sa gateasca");
			this.getMainFrame().setVisible(false);
		}
		else
		{
			System.out.println("Bucatarul  se ocupa de alta comanda!");
			this.getMainFrame().setVisible(false);
		}
		this.getMainFrame().setVisible(true);
	}
}

