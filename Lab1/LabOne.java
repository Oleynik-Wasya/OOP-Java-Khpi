
public class LabOne {

	public static void main(String[] args) {
		int number = 0x9;
		long phone = 380997965819L;
		int last_two_numbers = 0b10011;
		char character = 'I';
		
		System.out.println(character);
		System.out.println(((number - 1) % 26) + 1);
		System.out.println(last_two_numbers);
		System.out.println(number);
		System.out.println(phone);
		System.out.println("Count of even(phone): " + count_even(phone));
		System.out.println("Count of even(last_two_numbers): " + count_even(Integer.parseInt(Integer.toString(last_two_numbers))));
		System.out.println("Count of even(number): " + count_even(Integer.parseInt(Integer.toString(number))));
		System.out.println("Count of one in phone: " + count_one(Long.toBinaryString(phone)));
		System.out.println("Count of one in number: " + count_one(Integer.toBinaryString(number)));
		System.out.println("Count of one in last_two_numbers: " + count_one(Integer.toBinaryString(last_two_numbers)));

	}
	public static int count_even(long n) {
		
		int countEven = 0;
		while(n>0) {
			if((n%10)%2 == 0) {
				countEven += 1;
			}
			n = n / 10;
		}
		
		return countEven;
	}
	public static int count_one(String str) {
		int ones = 0;
		char temp;

		for( int i = 0; i < str.length(); i++ )
		{
		    temp = str.charAt( i );

		    if(temp == '1')
		        ones++;
		}
		return ones;
	}

}
