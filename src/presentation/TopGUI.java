package presentation;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import bll.Restaurant;
import dao.RestaurantSer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TopGUI implements java.io.Serializable 
{
private Restaurant restaurant;
	
	private AdminGUI admin;
	private WaiterGUI waiter;
	private ChefGUI chef;
	
	public static JFrame mainFrame;
	private JPanel mainPanel;
	private JButton adminPanel;
	private JButton waiterPanel;
	private JButton chefPanel;
	
	private JButton exit;
	public String args;
		
		public TopGUI(Restaurant rest, String argument)
		{
			this.restaurant = rest;
			this.args = argument;
			
			mainFrame = new JFrame("Restaurant application");
			mainFrame.setBounds(50, 50, 640, 480);
		
			
			mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
			mainFrame.add(mainPanel);
			
			admin = new AdminGUI(restaurant, mainFrame, args);
			waiter = new WaiterGUI(restaurant, mainFrame);
			chef = new ChefGUI(restaurant, mainFrame);
			
			
			adminPanel = new JButton("Administrator");
			adminPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
			mainPanel.add(Box.createRigidArea(new Dimension(0,25)));
			adminPanel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					mainFrame.setVisible(false);
					waiter.getFrame().setVisible(false);
					admin.getMainFrame().setVisible(true);
					chef.getMainFrame().setVisible(false);
				}
			});
			mainPanel.add(adminPanel);
			
			waiterPanel = new JButton("Waiter");
			waiterPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
			mainPanel.add(Box.createRigidArea(new Dimension(0,25)));
			waiterPanel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					mainFrame.setVisible(false);
					admin.getMainFrame().setVisible(false);
					waiter.getFrame().setVisible(true);
					chef.getMainFrame().setVisible(false);
				}
			});
			mainPanel.add(waiterPanel);
			
			chefPanel = new JButton("Chef");
			chefPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
			mainPanel.add(Box.createRigidArea(new Dimension(0,25)));
			chefPanel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					mainFrame.setVisible(false);
					admin.getMainFrame().setVisible(false);
					waiter.getFrame().setVisible(false);
					chef.getMainFrame().setVisible(true);
				}
			});
			mainPanel.add(chefPanel);
			
			exit = new JButton("Exit");
			exit.setAlignmentX(Component.CENTER_ALIGNMENT);
			mainPanel.add(Box.createRigidArea(new Dimension(0,25)));
			exit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					RestaurantSer ser = new RestaurantSer();
					ser.serialize(restaurant, args);
					System.exit(0);
				}
			});
			mainPanel.add(exit);	
			mainFrame.setVisible(true);
		}
		
		public JFrame getMainWindow()
		{
			return mainFrame;
		}
		
		public Restaurant getRestaurantData()
		{
			return this.restaurant;
		}
}

