package dao;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import bll.Restaurant;

public class RestaurantSer {
	
		public static void serialize(Restaurant restaurant, String args)
		{
			try
			{
				FileOutputStream outFile = new FileOutputStream(args);
				ObjectOutputStream outObject = new ObjectOutputStream(outFile);
				outObject.writeObject(restaurant);
				outObject.close();
				outFile.close();
				System.out.println("Data serialized successfully");
			}
			catch(IOException i)
			{
				i.printStackTrace();
			}
		}
		
		public static Restaurant deserialize(String args)
		{
			Restaurant restaurant = null;
			try
			{
				FileInputStream inFile = new FileInputStream(args);
				ObjectInputStream inObject = new ObjectInputStream(inFile);
				restaurant = (Restaurant) inObject.readObject();
				inObject.close();
				inFile.close();
				System.out.println("Data loaded successfully");
				return restaurant;
			}
			catch(IOException i)
			{
				System.out.println(i);
				restaurant = new Restaurant();
				serialize(restaurant, args);
				return restaurant;
			}
			catch(ClassNotFoundException c)
			{
				System.out.println("Class not found");
				c.printStackTrace();
				return restaurant = new Restaurant();
			}
		}
}
