import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class LabFunf {

	public static void main(String[] args) {
		
		ArrayList<String> data = new ArrayList<String>(
			    Arrays.asList("Buenos Aires", "Córdoba", "La Plata", "hello", "Hi"));

		RawData rawData = new RawData(data);
		
		Iterator<String> iterator = rawData.gerIterator();
		
		System.out.println("#Print data by for each..");
		for(String str:rawData.getData()) {
			System.out.print(str + " ");
		}
		
		rawData.add("Added string");
		System.out.println();
		System.out.println("#Add new string..");
		System.out.println(rawData.toString());
		
		System.out.println("#Remove 'Hi'..");
		rawData.remove("Hi");
		System.out.println(rawData.toString());
		
		System.out.println("#Print data be while..");
		int i = 1;
		while(iterator.hasNext()) {
			System.out.println(i++ + ")" + iterator.next());
		}
		
		System.out.println("#Use size..");
		System.out.println("Size of rawData = " + rawData.size());
	}
	
}
