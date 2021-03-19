package presentation;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

import bll.BaseProduct;
import bll.CompositeProduct;
import bll.IRestaurantProcessing;
import bll.MenuItem;
import bll.Order;
import bll.Restaurant;
import dao.RestaurantSer;

public class AdminGUI implements IRestaurantProcessing 
{

private Restaurant restaurant;
	
	static int contor = 0;
	private ArrayList<MenuItem> compProductItems;
	String[] columns = {"Produs","Pret","Incrediente"};
	String[][] data = new String[25][25];
	
	private JTable menu;
	private JButton exit;
	private JPanel panelCreate;
	private JFrame frameCreate;
	private JButton createItemButton;
	private JButton addItem;
	private JButton createCompositeButton;
	private JButton addCompositeItem;
	private JButton addCompositeItemMain;
	private JLabel productToAddInComp;
	private JTextField productToAddInCompTextField;
	private JLabel compProdName;
	private JTextField compProdNameTextField;
	private JFrame productCompFrame;
	private JPanel productCompPanel;
	private JLabel itemNameLabel;
	private JLabel itemPriceLabel;
	private JTextField itemName;
	private JTextField itemPrice;
	private JPanel panel;
	private JFrame frame;
	private JFrame frameDelete;
	private JButton deleteItemButton;
	private JPanel panelDelete;
	private JLabel itemToDelete;
	private JTextField itemToDeleteName;
	private JButton deleteItem;
	private JButton editMenuItem;
	private JFrame frameEdit;
	private JButton editButton;
	private JPanel panelEdit;
	private JLabel itemToEdit;
	private JLabel newPrice;
	private JTextField newPriceTextField;
	private JTextField itemToEditName;
	private JLabel newNameLabel;
	private JTextField newNameTextField;
	private JLabel editIngredientsL;
	private JTextField editIngredientsTextField;
	private JPanel panelView;
	private JFrame frameView;
	private JButton showItemsButton;
	private JFrame mwJFrame;
	public String argument;
	private JButton backButton;
	private JButton backButton1;
	private JButton backButton2;
	private JButton backButton3;
	private JButton backButton4;
	private JButton backButton5;
	
	
	
	public AdminGUI(Restaurant rest, JFrame mwJFrame2, String args)
	{
		
		this.restaurant = rest;
		this.mwJFrame = mwJFrame2;
		this.argument = args;
		
		frame = new JFrame("Administrator panel");
		frame.setBounds(50, 50, 640, 480);
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		//frame.setVisible(true);
		
		if(restaurant.getMenu().isEmpty() == false)
		{	
			for(MenuItem mi: restaurant.getMenu())
			{
				data[contor][0] = mi.getItemName();
				data[contor][1] = Double.toString(mi.getItemPrice());
				data[contor][2] = mi.toStringProd();
				contor++;
			}
		}
		
		createItemButton = new JButton("Add base product");
		createItemButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(Box.createRigidArea(new Dimension(0,25)));
		createItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
				frameCreate.setVisible(true);
			}
		});
		panel.add(createItemButton);
		
		createCompositeButton = new JButton("Add composite product");
		createCompositeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(Box.createRigidArea(new Dimension(0,25)));
		createCompositeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
				productCompFrame.setVisible(true);
			}
		});
		panel.add(createCompositeButton);
		
		
		deleteItemButton = new JButton("Delete product");
		deleteItemButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(Box.createRigidArea(new Dimension(0,25)));
		deleteItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
				frameDelete.setVisible(true);
			}
		});
		panel.add(deleteItemButton);
		
		
		editMenuItem = new JButton("Edit menu item");
		editMenuItem.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(Box.createRigidArea(new Dimension(0,25)));
		editMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
				frameEdit.setVisible(true);
			}
		});
		panel.add(editMenuItem);
			
		backButton = new JButton("Back");
		backButton.addActionListener(new backButtonListener());
		backButton1 = new JButton("Back");
		backButton1.addActionListener(new backButtonListener());
		backButton2 = new JButton("Back");
		backButton2.addActionListener(new backButtonListener());
		backButton3 = new JButton("Back");
		backButton3.addActionListener(new backButtonListener());
		backButton4 = new JButton("Back");
		backButton4.addActionListener(new backButtonListener());
		
		showItemsButton = new JButton("View menu");
		showItemsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(Box.createRigidArea(new Dimension(0,25)));
		frameView = new JFrame("Administrator panel - Check menu items");
		showItemsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				frameView = new JFrame("Administrator panel - Check menu items");
				frameView.setBounds(50, 50, 640, 480);
				panelView = new JPanel();
				panelView.setLayout(new FlowLayout());
				panelView.add(backButton2);
				data = new String[25][25];
				for(int k = 0; k < restaurant.getMenu().size(); k++)
				{
					data[k][0] = restaurant.getMenu().get(k).getItemName();
					data[k][1] = Double.toString(restaurant.getMenu().get(k).getItemPrice());
					data[k][2] = restaurant.getMenu().get(k).toStringProd();
				}
				menu = new JTable(data,columns);
				menu.setBounds(60, 60, 400, 400);
				JScrollPane sp = new JScrollPane(menu);
				panelView.add(sp);
				frameView.add(panelView);
				frame.setVisible(false);
				frameView.setVisible(true);
			}
		});
		panel.add(showItemsButton);
		
		backButton5 = new JButton("Back");
		backButton5.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(Box.createRigidArea(new Dimension(0,25)));
		backButton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				frameCreate.setVisible(false);
				frameView.setVisible(false);
				frameDelete.setVisible(false);
				frameEdit.setVisible(false);
				frame.setVisible(false);
				mwJFrame.setVisible(true);
			}
		});
		panel.add(backButton5);
		
		exit = new JButton("Exit");
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(Box.createRigidArea(new Dimension(0,25)));
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				RestaurantSer ser = new RestaurantSer();
				ser.serialize(restaurant, argument);
				System.exit(0);
			}
		});
		panel.add(exit);	
		frame.add(panel);
		frameCreate = new JFrame("Administrator panel - Add item to the menu");
		frameCreate.setBounds(50, 50, 640, 480);
		panelCreate = new JPanel();
		panelCreate.setLayout(new FlowLayout(FlowLayout.LEFT));
		itemName = new JTextField(15);
		itemPrice = new JTextField(5);
		itemNameLabel = new JLabel("Item name");
		itemPriceLabel = new JLabel("Item price");
		addItem = new JButton("ADD");
		panelCreate.add(itemNameLabel);
		panelCreate.add(itemName);
		panelCreate.add(itemPriceLabel);
		panelCreate.add(itemPrice);
		panelCreate.add(addItem);
		addItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String itName = itemName.getText();
				double pret = 0;
				if(itemPrice.getText().equals("") == false)
				{
					pret = Double.parseDouble(itemPrice.getText());
				}	
				BaseProduct bp = new BaseProduct(itName, pret);
				createMenuItem(bp);
				data[contor][0] = restaurant.getMenu().get(contor).getItemName();
				data[contor][1] = Double.toString(restaurant.getMenu().get(contor).getItemPrice());
				data[contor][2] = restaurant.getMenu().get(contor).toStringProd();
				contor++;
				JOptionPane.showMessageDialog(null, "Produs adaugat cu succes");
			}
		});
		frameCreate.add(panelCreate);
		panelCreate.add(backButton);
		frameCreate.pack();
				
		productCompFrame = new JFrame("Administrator panel - Add item to the menu");
		productCompFrame.setBounds(50, 50, 640, 480);
		productCompPanel = new JPanel();
		addCompositeItem = new JButton("ADD Base");
		productToAddInCompTextField = new JTextField(15);
		productToAddInComp = new JLabel("Base product name: ");
		compProdNameTextField = new JTextField(15);
		compProductItems = new ArrayList<MenuItem>();
		addCompositeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String prodName = productToAddInCompTextField.getText();
				for(MenuItem mi: restaurant.getMenu())
				{
					if(mi.getItemName().equals(prodName))
					{
						compProductItems.add(mi);
						if(compProductItems.contains(mi) == true)
						{
							JOptionPane.showMessageDialog(null, "Am adaugat produsul la reteta");
						}
					}
				}
			}
		});
		
		productCompPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		compProdName = new JLabel("Composite product name: ");
		productCompPanel.add(productToAddInComp);
		productCompPanel.add(productToAddInCompTextField);
		productCompPanel.add(compProdName);
		productCompPanel.add(compProdNameTextField);
		productCompPanel.add(addCompositeItem);
		addCompositeItemMain = new JButton("ADD Comp");
		productCompPanel.add(addCompositeItemMain);
		addCompositeItemMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
				String prodName = compProdNameTextField.getText();
				System.out.println(prodName);
				CompositeProduct cp = new CompositeProduct();
				cp.setItemName(prodName);
				ArrayList<MenuItem> prodCopy = new ArrayList<MenuItem>();
				for(MenuItem mi: compProductItems)
				{
					prodCopy.add(mi);
				}
				cp.setProducts(prodCopy);
				cp.setItemPrice(cp.computePrice());
				createMenuItem(cp);
				data[contor][0] = restaurant.getMenu().get(contor).getItemName();
				data[contor][1] = Double.toString(restaurant.getMenu().get(contor).getItemPrice());
				data[contor][2] = restaurant.getMenu().get(contor).toStringProd();
				contor++;
				JOptionPane.showMessageDialog(null, "Produs adaugat cu succes");
				compProductItems.clear();
			}
		});
		productCompFrame.add(productCompPanel);
		productCompPanel.add(backButton1);
		productCompFrame.pack();
		
		frameDelete = new JFrame("Administrator panel - Remove an item from the menu");
		frameDelete.setBounds(50, 50, 640, 480);
		panelDelete = new JPanel();
		panelDelete.setLayout(new FlowLayout());
		itemToDelete = new JLabel("Insert the name of the product you want to delete: ");
		itemToDeleteName = new JTextField(30);
		deleteItem = new JButton("Remove");
		deleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String itName = itemToDeleteName.getText();
				deleteMenuItem(itName);
				contor--;
				JOptionPane.showMessageDialog(null, "Produs eliminat cu succes");
			}
		});
		panelDelete.add(itemToDelete);
		panelDelete.add(itemToDeleteName);
		panelDelete.add(deleteItem);
		panelDelete.add(backButton3);
		frameDelete.add(panelDelete);
		frameDelete.pack();	

		frameEdit = new JFrame("Administrator panel - Edit an item from the menu");
		frameEdit.setBounds(50, 50, 640, 480);
		panelEdit = new JPanel();
		panelEdit.setLayout(new FlowLayout());
		itemToEdit = new JLabel("Insert the name of the product you want to edit: ");
		itemToEditName = new JTextField(10);
		newPrice = new JLabel("Insert the new price: ");
		newPriceTextField = new JTextField(5);
		editButton = new JButton("Edit item");
		newNameLabel = new JLabel("Insert the new name: ");
		newNameTextField = new JTextField(10);
		editIngredientsL = new JLabel("Edit the ingredients: ");
		editIngredientsTextField = new JTextField(30);
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String itName = itemToEditName.getText();
				double newPriceDouble = 0;
				if(newPriceTextField.getText().equals("") == false)
				{
					newPriceDouble = Double.parseDouble(newPriceTextField.getText());
				}
				String newName = newNameTextField.getText();
				ArrayList<MenuItem> ingredientsList = new ArrayList<MenuItem>();
				int contor = 0;
				ArrayList<String> strSplit = restaurant.editlistProd(editIngredientsTextField.getText());
				for(int w = 0; w < strSplit.size(); w++)
				{
					contor++;
					for(MenuItem mi: restaurant.getMenu())
					{
						if(mi.getItemName().equals(strSplit.get(w)))
						{		
							ingredientsList.add(mi);
							break;
						}
					}
				}
				if(contor == ingredientsList.size() || ingredientsList.isEmpty())
				{	
					editMenuItem(itName,newPriceDouble,newName,ingredientsList);
					JOptionPane.showMessageDialog(null, "Produs modificat cu succes");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Nu avem produsele necesare");
				}
				
			}				
		});
		
		panelEdit.add(itemToEdit);
		panelEdit.add(itemToEditName);
		panelEdit.add(newPrice);
		panelEdit.add(newPriceTextField);
		panelEdit.add(newNameLabel);
		panelEdit.add(newNameTextField);
		panelEdit.add(editIngredientsL);
		panelEdit.add(editIngredientsTextField);
		panelEdit.add(editButton);
		panelEdit.add(backButton4);
		frameEdit.add(panelEdit);
		frameEdit.pack();
	}
	
	public JFrame getMainFrame()
	{
		return frame;
	}
	
	@Override
	public void createMenuItem(MenuItem itemToCreate) 
	{
		restaurant.createMenuItem(itemToCreate);
	}

	@Override
	public void deleteMenuItem(String itemName) 
	{
		restaurant.deleteMenuItem(itemName);
	}

	@Override
	public void editMenuItem(String itemToModify, double price, String newName, ArrayList<MenuItem> ingredients) 
	{
		restaurant.editMenuItem(itemToModify, price, newName, ingredients);
	}
	@Override
	public void createOrder(Order oToCreate) {}

	@Override
	public double computeOrderPrice(Order oToCompute) 
	{
		return 0;
	}

	@Override
	public void generateOrderBill(Order order, String products) {}
	
	public class backButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			frameCreate.setVisible(false);
			frameView.setVisible(false);
			frameDelete.setVisible(false);
			frameEdit.setVisible(false);
			productCompFrame.setVisible(false);
			frame.setVisible(true);
		}
	}

}