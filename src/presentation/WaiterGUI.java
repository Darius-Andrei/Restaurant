package presentation;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import bll.IRestaurantProcessing;
import bll.MenuItem;
import bll.Order;
import bll.Restaurant;


public class WaiterGUI implements IRestaurantProcessing
{
	private Restaurant restaurant;
	static int orderID = 1;
	static int contor = 0;

	private JFrame waiterFrame;
	private JFrame addOrderFrame;
	private JFrame viewOrdersFrame;
	private JFrame generateOrdersFrame;
	private JPanel waiterPanel;
	private JPanel addOrderPanel;
	private JPanel viewOrdersPanel;
	private JPanel generateOrdersPanel;
	private JButton addOrder;
	private JButton addOrderButton;
	private JLabel tableNumber;
	private JTextField tableNumberTextField;
	private JButton computeOrder;
	private JButton viewOrders;
	private JButton backButton1;
	private JButton backButton2;
	private JButton backButton3;
	private JButton backButton4;
	
	private JFrame mwJFrame;
	private JLabel addItemToOrder;
	private JLabel generateOrdersLabel;
	private JButton addItemToOrderButton;
	private JTextField generateOrdersTextField;
	private JTextField addItemTextField;
	
	private ArrayList<MenuItem> orderedItems;
	

	String[] columns = {"Id","Data","Nr. Masa","Produse comandate","Pret total"};
	String[][] data = new String[25][25];
	private JTable orders;
	
	public WaiterGUI(Restaurant rest, JFrame mwJFrame2)
	{
		
		this.restaurant = rest;
		this.mwJFrame = mwJFrame2;
		
		waiterFrame = new JFrame("Waiter panel");
		waiterFrame.setBounds(50, 50, 640, 480);
		waiterPanel = new JPanel();
		waiterPanel.setLayout(new BoxLayout(waiterPanel,BoxLayout.Y_AXIS));
		waiterFrame.add(waiterPanel);
		
		addOrder = new JButton("New order");
		addOrder.setAlignmentX(Component.CENTER_ALIGNMENT);
		waiterPanel.add(Box.createRigidArea(new Dimension(0,25)));
		addOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				waiterFrame.setVisible(false);
				addOrderFrame.setVisible(true);
			}
		});
		waiterPanel.add(addOrder);
		
		viewOrders = new JButton("View active orders");
		viewOrders.setAlignmentX(Component.CENTER_ALIGNMENT);
		waiterPanel.add(Box.createRigidArea(new Dimension(0,25)));
		viewOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				waiterFrame.setVisible(false);
				viewOrdersFrame.setVisible(true);
			}
		});
		waiterPanel.add(viewOrders);
		
		addOrderFrame = new JFrame("Waiter panel - Create new order");
		addOrderFrame.setBounds(50, 50, 640, 480);
		addOrderPanel = new JPanel();
		addOrderPanel.setLayout(new FlowLayout());
		
		tableNumber = new JLabel("Select the table");
		addOrderPanel.add(tableNumber);
		
		tableNumberTextField = new JTextField(5);
		addOrderPanel.add(tableNumberTextField);
		
		addItemToOrder = new JLabel("Insert product: ");
		addOrderPanel.add(addItemToOrder);
		
		addItemTextField = new JTextField(15);
		addOrderPanel.add(addItemTextField);
		
		orderedItems = new ArrayList<MenuItem>();
		addItemToOrderButton = new JButton("Add product");
		addItemToOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String prodName = addItemTextField.getText();
				for(MenuItem mi: restaurant.getMenu())
				{
					if(mi.getItemName().equals(prodName))
					{
						orderedItems.add(mi);
						if(orderedItems.contains(mi) == true)
						{
							JOptionPane.showMessageDialog(null, "Am adaugat produsul la comanda");
						}
					}
				}
			}
		});
		addOrderPanel.add(addItemToOrderButton);
		
		if(restaurant.getOrdersList().isEmpty() == false)
		{
			for(Order o: restaurant.getOrdersList())
			{
				orderID++;
				o.setTotalPrice(computeOrderPrice(o));
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
				String strDate = df.format(o.getDate());
				data[contor][0] = Integer.toString(o.getOrderID());
				data[contor][1] = strDate;
				data[contor][2] = Integer.toString(o.getTableNumber());
				data[contor][3] = o.toStringProd();
				data[contor][4] = Double.toString(o.getTotalPrice());
				contor++;
			}
		}
			
		
		addOrderButton = new JButton("Add order");
		addOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Date orderDate = new Date();
				Order orderCopyAdd = new Order(orderID,orderDate,Integer.parseInt(tableNumberTextField.getText()));
				ArrayList<MenuItem> orderItemsCopy = new ArrayList<MenuItem>();
				for(MenuItem mi: orderedItems)
				{
					orderItemsCopy.add(mi);
				}
				orderCopyAdd.setProducts(orderItemsCopy);
				createOrder(orderCopyAdd);
				orderID++;
				
				if(restaurant.getOrdersList().contains(orderCopyAdd) == true)
				{
					orderCopyAdd.setTotalPrice(computeOrderPrice(orderCopyAdd));
					DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
					String strDate = df.format(orderCopyAdd.getDate());
					data[contor][0] = Integer.toString(orderCopyAdd.getOrderID());
					data[contor][1] = strDate;
					data[contor][2] = Integer.toString(orderCopyAdd.getTableNumber());
					data[contor][3] = orderCopyAdd.toStringProd();
					data[contor][4] = Double.toString(orderCopyAdd.getTotalPrice());
					contor++;
					JOptionPane.showMessageDialog(null, "Comanda adaugata cu succes");
				}
				orderedItems.clear();
			}
		});
		addOrderPanel.add(addOrderButton);
		
		backButton1 = new JButton("Back");
		backButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				addOrderFrame.setVisible(false);
				waiterFrame.setVisible(true);
			}
		});
		addOrderPanel.add(backButton1);
		
		addOrderFrame.add(addOrderPanel);
		addOrderFrame.pack();
		
		viewOrdersFrame = new JFrame("Waiter panel - Reviewing active orders");
		viewOrdersFrame.setBounds(50, 50, 1280, 720);
		viewOrdersPanel = new JPanel();
		viewOrdersPanel.setLayout(new FlowLayout());
		orders = new JTable(data, columns);
		orders.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		orders.getColumnModel().getColumn(1).setPreferredWidth(100);
		orders.getColumnModel().getColumn(2).setPreferredWidth(25);
		orders.getColumnModel().getColumn(3).setPreferredWidth(100);
		backButton4 = new JButton("Back");
		backButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				generateOrdersFrame.setVisible(false);
				viewOrdersFrame.setVisible(true);	
			}
		});
		computeOrder= new JButton("ComputeOrder");
		JButton button = new JButton("Generate Bill");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
					generateOrdersFrame=new JFrame();
					generateOrdersPanel=new JPanel();
					generateOrdersLabel=new JLabel("Order ID de computat:");
					generateOrdersTextField=new JTextField(5);
					generateOrdersPanel.add(generateOrdersLabel);
					generateOrdersPanel.add(generateOrdersTextField);
					generateOrdersPanel.add(computeOrder);
					generateOrdersPanel.add(backButton4);
					generateOrdersFrame.add(generateOrdersPanel);
					generateOrdersFrame.setVisible(true);
					generateOrdersFrame.pack();
					viewOrdersFrame.setVisible(false);
					
			}
		});
	computeOrder.addActionListener(new ActionListener() 
	{
		public void actionPerformed(ActionEvent e)
		{
			int w=0;
			Order orderCopyDelete=new Order();
			for(Order o: restaurant.getOrdersList())
			{
				if(o.getOrderID() == Integer.parseInt(generateOrdersTextField.getText()))
				{
					w=o.getOrderID()-1;
					orderCopyDelete=o;
					break;
				}
			}	
			try {
				generateOrderBill(orderCopyDelete, orderCopyDelete.toStringProd());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			for(Order o: restaurant.getOrdersList())
			{
				if(o.getOrderID() == orderCopyDelete.getOrderID())
				{
					restaurant.getOrdersMap().remove(o);
				}
			}	
			Iterator<Order> it = restaurant.getOrdersList().iterator();
			while(it.hasNext())
			{
				if(it.next().getOrderID() == orderCopyDelete.getOrderID())
				{
					it.remove();
				}
			}	
			for(int k = w; k < contor-1; k++)
			{
				data[k][0] = Integer.toString(Integer.parseInt(data[k + 1][0])-1);
				data[k][1] = data[k + 1][1];
				data[k][2] = data[k + 1][2];
				data[k][3] = data[k + 1][3];
				data[k][4] = data[k + 1][4];
			}
			data[contor-1][0]="";
			data[contor-1][1]="";
			data[contor-1][2]="";
			data[contor-1][3]="";
			data[contor-1][4]="";
			contor--;
			if(restaurant.getOrdersList().contains(orderCopyDelete) == false)
			{
				JOptionPane.showMessageDialog(null, "Comanda eliminata cu succes");
			}
		}
	});
		
		JScrollPane sp = new JScrollPane(orders);
		sp.setBounds(50, 50, 800, 800);
		viewOrdersPanel.add(sp, BorderLayout.CENTER);
		
		backButton2 = new JButton("Back");
		backButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				viewOrdersFrame.setVisible(false);
				waiterFrame.setVisible(true);
			}
		});

		viewOrdersPanel.add(button);
		viewOrdersPanel.add(backButton2);
		
		viewOrdersFrame.add(viewOrdersPanel);
		
		backButton3 = new JButton("Back");
		backButton3.setAlignmentX(Component.CENTER_ALIGNMENT);
		waiterPanel.add(Box.createRigidArea(new Dimension(0,25)));
		backButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				waiterFrame.setVisible(false);
				mwJFrame.setVisible(true);
			}
		});
		waiterPanel.add(backButton3);
	}
	
	public JFrame getFrame()
	{
		return waiterFrame;
	}
	
	@Override
	public void createOrder(Order orderCopyCreate) 
	{
		restaurant.createOrder(orderCopyCreate);
	}

	@Override
	public double computeOrderPrice(Order orderCopyCompute)
	{
		return restaurant.computeOrderPrice(orderCopyCompute);
	}

	@Override
	public void generateOrderBill(Order order, String products) throws IOException 
	{
		restaurant.generateOrderBill(order, products);
	}

	@Override
	public void createMenuItem(MenuItem itemToAdd) {}

	@Override
	public void deleteMenuItem(String itemName) {}

	@Override
	public void editMenuItem(String itemToModify, double price, String newName, ArrayList<MenuItem> ingredients) {}
}
