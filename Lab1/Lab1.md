# Lab 1
### Структура програми мовою Java. Типи даних, літерали, операції і оператори.
**Розробник:**
+ _Олійник Василь Максимович_
+ _КІТ 101.8а_
+ _Варіант: 9_

**Мета:** ознайомлення з JDK платформи Java SE та середовищем розробки Eclipse IDE.

***Прикладні задачі:***
1. Обрати тип змінних та встановити за допомогою констант та літералів      початкові значення:
 - число, що відповідає номеру залікової книжки за допомогою шістнадцяткового літералу;
 - число, що відповідає номеру мобільного телефона (починаючи з 380…) за  допомогою десяткового літералу;
 - число, яке складається з останніх двох ненульових цифр номера мобільного телефону за допомогою двійкового літералу;
 - число, яке складається з останніх чотирьох ненульових цифр номера мобільного телефону за допомогою вісімкового літералу;
 - визначити збільшене на одиницю значення залишку від ділення на 26 зменшеного на одиницю номера студента в журналі групи;
 - символ англійського алфавіту в верхньому регістрі, номер якого відповідає знайденому раніше значенню.
2. Використовуючи десятковий запис цілочисельного значення кожної змінної знайти і підрахувати кількість парних і непарних цифр.
3. Використовуючи двійковий запис цілочисельного значення кожної змінної підрахувати кількість одиниць.

```Java
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
```
**Вывод:**
>_<p>character: I</br>
number: 9</br>
last two numbers: 19</br>
number: 9</br>
phone: 380997965819</br>
Count of even(phone): 4</br>
Count of even(last_two_numbers): 0</br>
Count of even(number): 0</br>
Count of one in phone: 26</br>
Count of one in number: 2</br>
Count of one in last_two_numbers: 3</p>_

**Висновок:** ознайомився з JDK платформи Java SE та середовищем розробки Eclipse IDE.

