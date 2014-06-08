import java.util.ArrayList;
import java.util.Scanner;

public class Main 
{
	public static void main(String[] args) throws Exception 
	{
		HashFile hash = new HashFile(7, 2);
		Scanner scan = new Scanner(System.in);
		while(true)
		{
			System.out.println("Enter an id# and a name, type 0 to end: ");
			int id = scan.nextInt();
			if(id == 0)
				break;
			String name = scan.next();
			Record r = new Record(id, name);
			System.out.println("ID: " + id + " Name: " + name + " Stored in Bucket " + hash.store(r));
			System.out.println("");
		}
		
		while(true)
		{
			System.out.println("Enter an id# to find a name, type 0 to end: ");
			int id = scan.nextInt();
			if(id == 0) 
				break;
			Record r = hash.retrieve(id);
			if(r == null) 
				System.out.println(id + " Not Found");
			else 
				System.out.println("ID: " + r.getId() + " Name: " + r.getName());
		}
	}
}
