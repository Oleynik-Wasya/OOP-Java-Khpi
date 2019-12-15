import java.util.Random;

public class LabTwo {

	public static void main(String[] args) {
		
		Random rnd = new Random(System.currentTimeMillis());
		
		System.out.println("Hello worrld!");
		for(int i = 0; i < 10; i++) {
			int x = 1000 + rnd.nextInt(10000 - 1000 + 1);
			System.out.format("%10s%10d\n", String.format("0x%08X", x), count_symbol(x));
		}
	}
	public static int count_symbol(int number) {
		int n = number;
		int count = 0;
		while(n > 0) {
			if(n%16 > 9) {
				count++;
			}
			n /= 16;
		}
		return count;
	}
	
}
