package bll;

import java.util.ArrayList;
import java.util.Date;

public class Order implements java.io.Serializable 
{
	private int orderID;
	private Date date;
	private int tableNumber;
	private ArrayList<MenuItem> orderItems;
	private Restaurant restaurant;
	private double totalPrice;

	public Order() {
		this.orderID = 0;
		this.date = null;
		this.tableNumber = 0;
		this.orderItems = new ArrayList<MenuItem>();
		this.totalPrice = 0;
	};
	
	public Order(int orderID, Date date, int tableNumber) 
	{
		this.orderID = orderID;
		this.date = date;
		this.tableNumber = tableNumber;
		this.orderItems = new ArrayList<MenuItem>();
		this.totalPrice = 0;
	}
	public ArrayList<MenuItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(ArrayList<MenuItem> orderItems) {
		this.orderItems = orderItems;
	}

	public void addItemsToTheOrder(MenuItem mi)
	{
		if(restaurant.getMenu().contains(mi) == true)
		{
			this.orderItems.add(mi);
		}
	}
	@Override
	public int hashCode()
	{
		int hash = 11;
		hash = 13 * hash + (int)orderID;
		hash = 23 * hash + (int)date.getDay();
		hash = 17 * hash + (int)tableNumber; 
		return hash;
	}
	@Override
	public boolean equals(Object o)
	{
		if(this == o)
		{
			return true;
		}
		if(o == null)
		{
			return false;
		}
		if(this.getClass() != o.getClass())
		{
			return false;
		}
		Order order = (Order) o;
		return orderID == order.orderID && date.equals(order.getDate()) && tableNumber == order.tableNumber;
	}
	public String toStringProd()
	{
		StringBuilder sb = new StringBuilder();
		for(MenuItem mi: orderItems)
		{
			sb.append(mi.getItemName() + " ");
		}
		return sb.toString();
	}
	public ArrayList<MenuItem> getProducts()
	{
		return orderItems;
	}
	public Date getDate() 
	{
		return date;
	}
	public int getOrderID() 
	{
		return orderID;
	}
	public void setOrderID(int orderID) 
	{
		this.orderID = orderID;
	}
	public void setDate(Date date) 
	{
		this.date = date;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getTableNumber() 
	{
		return tableNumber;
	}
	public void setTableNumber(int tableNumber) 
	{
		this.tableNumber = tableNumber;
	}
	public void setProducts(ArrayList<MenuItem> products) 
	{
		this.orderItems = products;
	}
}
