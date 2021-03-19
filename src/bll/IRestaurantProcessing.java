package bll;

import java.io.IOException;
import java.util.ArrayList;

public interface IRestaurantProcessing 
{
	/**
	 * @pre item != null
	 *  list.size@pre == list.size@post -1
	 */
	public void createMenuItem(MenuItem prodToAdd);
	
	/**
	 * @param item
	 *	@pre item!=null
	 *  @post list.size = list.size@pre +1
	 */
	public void deleteMenuItem(String itemName);
	
	/**
	 * @pre item!=null
	 * @post item@pre.price != item@post.price
	 * @param item
	 */
	public void editMenuItem(String prodToModify, double price, String newName, ArrayList<MenuItem> ingredients);
	
	/**
	 * 
	 * @param order
	 * @param menuItem
	 * 
	 * @pre order != null
	 * @pre menuItem != null
	 */
	public void createOrder(Order orderToCreate);
	

	/**
	 * @pre order!=null
	 */
	public double computeOrderPrice(Order orderToCompute);
	
	/**
	 * 
	 * @param whatToPrint
	 * @pre whatToPrint != null
	 */
	public void generateOrderBill(Order order, String products) throws IOException ;
}
