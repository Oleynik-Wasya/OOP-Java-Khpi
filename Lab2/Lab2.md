# Lab 2
### Алгоритмічна декомпозиція. Прості алгоритми обробки даних
**Розробник:**
+ _Олійник Василь Максимович_
+ _КІТ 101.8а_
+ _Варіант: 9_

**Мета:** Розробка простих консольних програм для платформи Java SE.

***Прикладні задачі:***
1. Підрахувати кількість цифр, що відображаються за допомогою латинських букв в шістнадцятковому запису 10-значного цілого числа.



```Java
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
```
**Вывод:**
> _<p> 0x00001B17         1 </br>
0x00001DBC         3 </br>
0x000004E1         1 </br>
0x0000110B         1 </br>
0x00000E0E         2 </br>
0x00000E29         1 </br>
0x000007E3         1 </br>
0x00000DDD         3 </br>
0x0000216D         1 </br>
0x00000531         0 </br></p>_

**Висновок:** Розробив просту консольну програму для платформи Java SE.
