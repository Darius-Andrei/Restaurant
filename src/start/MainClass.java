package start;

import bll.Restaurant;
import dao.RestaurantSer;
import presentation.TopGUI;

public class MainClass
{
	public static void main(String[] args)
	{
		RestaurantSer rSer = new RestaurantSer();
		//Restaurant rest = new Restaurant();
		Restaurant restaurant = rSer.deserialize(args[0]);
		TopGUI start = new TopGUI(restaurant,args[0]);
	}
}

