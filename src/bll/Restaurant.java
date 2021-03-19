package bll;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;


public class Restaurant extends Observable implements IRestaurantProcessing, java.io.Serializable 
{
	
	
	private Map<Order, ArrayList<MenuItem>> ordersMap;
	private ArrayList<Order> ordersList;
	private ArrayList<MenuItem> menu;
	
	public Restaurant()
	{
		this.ordersMap = new HashMap<Order, ArrayList<MenuItem>>();
		this.ordersList = new ArrayList<Order>();
		this.menu = new ArrayList<MenuItem>();
	}	
	

	@Override
	public void createMenuItem(MenuItem createdProd) 
	{
		assert createdProd != null;
		
		int menuSize = menu.size();
		menu.add(createdProd);
		int menuSizeAfter = menu.size();
		
		assert menuSize + 1 == menuSizeAfter;
	}	

	public ArrayList<String> editlistProd(String products)
	{
		String[] str = products.split(", ");
		ArrayList<String> strList = new ArrayList<String>();
		for(int i = 0; i < str.length; i++)
		{
			strList.add(str[i]);
		}
		return strList;
	}
	@Override
	public void editMenuItem(String modProd, double price, String newName, ArrayList<MenuItem> listProd) 
	{
		assert modProd != null;
		for(MenuItem mi : menu)
		{
			if(mi.getItemName().equals(modProd))
			{
				if(price != 0)
				{
					mi.setItemPrice(price);
					for(MenuItem mi2: menu)
					{
						mi2.setItemPrice(mi2.computePrice());
					}
				}
				if(newName.equals("") == false)
				{
					mi.setItemName(newName);
					for(MenuItem mi2: menu)
					{
						for(MenuItem mi3: mi2.getProducts())
						{
							if(mi3.getItemName().equals(modProd))
							{
								mi3.setItemName(newName);
							}
						}
					}
				}
				if(listProd.size() != 0)
				{
					mi.setProducts(listProd);
					mi.setItemPrice(mi.computePrice());
				}
			}
		}
	}
	@Override
	public void deleteMenuItem(String itemName) 
	{
		assert itemName != null;
		
		int dimMenu1 = menu.size();
		Iterator<MenuItem> it = menu.iterator();
		while(it.hasNext())
		{
			if(it.next().toStringProd().contains(itemName))
			{
				it.remove();
			}
		}
		Iterator<MenuItem> it2 = menu.iterator();
		while(it2.hasNext())
		{
			if(it2.next().getItemName().equals(itemName))
			{
				it2.remove();
			}
		}
		int dimMenu2 = menu.size();
		
		assert dimMenu1 - 1 == dimMenu2;
	}	
		
	@Override
	public void createOrder(Order orderToCreate) 
	{
		assert orderToCreate != null;

		Order prevOrder = orderToCreate;
		boolean ok = false;
		ordersMap.put(orderToCreate, orderToCreate.getOrderItems());
		ordersList.add(orderToCreate);
		StringBuilder sb = new StringBuilder();
		sb.append("O noua comanda!\n");
		sb.append("Mancare de preparat: " + orderToCreate.toStringProd());
		sb.append("Pentru masa " + orderToCreate.getTableNumber());
		for(MenuItem mi: orderToCreate.getOrderItems())
		{
			if(mi instanceof CompositeProduct)
			{
				ok = true;
				break;
			}
		}
		if(ok == true)
		{
			setChanged();
			notifyObservers(sb.toString());
		}
		
		assert prevOrder.equals(orderToCreate);
	}	
	
	@Override
	public double computeOrderPrice(Order orderToCompute) 
	{
		assert orderToCompute != null;
		
		double price = 0;
		ArrayList<MenuItem> orderedItems = ordersMap.get(orderToCompute);
		for(MenuItem mi: orderedItems)
		{
			price += mi.getItemPrice();
		}
		
		assert orderToCompute.getTotalPrice() < 0;
		return price;
	}
	
	@Override
	public void generateOrderBill(Order o, String products) throws IOException 
	{
		assert o != null;
		StringBuilder sb = new StringBuilder();
		sb.append("Chitanta numarul" + o.getOrderID() + ".txt");
		FileWriter fw = new FileWriter(sb.toString());
		PrintWriter printW = new PrintWriter(fw);
		printW.print("Multumim ca ati cumparat de la noi!\n");
		printW.print("Masa cu numarul: " + o.getTableNumber() + "\n");
		printW.print("Data comenzii: " + o.getDate() + "\n");
		printW.print("Produse comandate: " + products + "\n");
		printW.print("Pret total: " + o.getTotalPrice() + "\n");
		printW.close();
	}	
	
	
	
	public Map<Order, ArrayList<MenuItem>> getOrdersMap() 
	{
		return ordersMap;
	}



	public void setOrdersMap(Map<Order, ArrayList<MenuItem>> ordersMap) 
	{
		this.ordersMap = ordersMap;
	}



	public ArrayList<Order> getOrdersList() 
	{
		return ordersList;
	}



	public void setOrdersList(ArrayList<Order> ordersList) 
	{
		this.ordersList = ordersList;
	}



	public ArrayList<MenuItem> getMenu()
	{
		return menu;
	}



	public void setMenu(ArrayList<MenuItem> menu)
	{
		this.menu = menu;
	}
	
}
