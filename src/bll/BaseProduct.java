package bll;

public class BaseProduct extends MenuItem implements java.io.Serializable 
{
	public BaseProduct(String itemName, double itemPrice)
	{
		super(itemName, itemPrice);
	}

	@Override
	public double computePrice() 
	{
		return getItemPrice();
	}
}
