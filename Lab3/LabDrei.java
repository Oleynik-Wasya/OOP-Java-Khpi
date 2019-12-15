import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class LabDrei {
	public static void main(String[] args) {
		//String s = "Pain son rose more park way that. Do play they miss give so up. Secure shy favour length all twenty denote. So by colonel hearted ferrars. Detract yet delight written farther his general. Painful so he an comfort is manners. Uncommonly no it announcing melancholy an in. Sportsman do offending supported extremity break";
		CounterWords A = new CounterWords("Hello, world! How are you, world? hi hi hi");
		System.out.println(A.countWords().values());
		int k = 0;
		for (Entry<String, Integer> e : A.countWords().entrySet()) {
		    System.out.format("%10s%10d\n", e.getKey(), e.getValue());
		}
		System.out.println(A.str);
	}
}
