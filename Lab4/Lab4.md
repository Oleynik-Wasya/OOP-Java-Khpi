# Lab 4
### Інтерактивні консольні програми для платформи Java SE
**Розробник:**
+ _Олійник Василь Максимович_
+ _КІТ 101.8а_
+ _Варіант: 9_

**Мета:**
+  Реалізація діалогового режиму роботи з користувачем в консольних програмах мовою Java.

***Прикладні задачі:***
1. Ввести текст. Знайти та вивести, скільки разів повторюється в тексті кожне слово. Результат вивести у вигляді таблиці.


__CounterWords.java:__
```Java
import java.util.HashMap;

public class CounterWords {
	public static StringBuffer cleanStr(String raw_data) {
		StringBuffer str = new StringBuffer(raw_data);
		while(str.indexOf("  ") != -1) {
			str.delete(str.indexOf("  "), str.indexOf("  ") + 1);
		}
		if(str.charAt(0) == ' ') {
			str.delete(0, 1);
		}
		if(str.charAt(str.length() - 1) == ' ') {
			str.setLength(str.length() - 1);
		}
		String stopwords[] = {".",",","!","?"};
		for(int i = 0; i < stopwords.length; i++) {
			while(str.indexOf(stopwords[i]) != -1)
				str.delete(str.indexOf(stopwords[i]), str.indexOf(stopwords[i]) + 1);
		}
		if(Debug.DEBUG)System.out.println("#Чистая строка: " + str);
		return str;
	}
	public static HashMap<String, Integer> countWords(String raw_data) {
		StringBuffer str = cleanStr(raw_data);
		HashMap< String, Integer > map = new HashMap< String, Integer >();
		int start = 0;
		int end = 0;
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == ' ') {
				end = i;
				if(map.get(str.substring(start, end)) != null) {
					map.put(str.substring(start, end), map.get(str.substring(start, end)) + 1);
					if(Debug.DEBUG)System.out.println("#Записую: " + str.substring(start, end));
					start = i + 1;
					continue;
				}
				map.put(str.substring(start, end), 1);
				if(Debug.DEBUG)System.out.println("#Записую: " + str.substring(start, end));
				start = i + 1;
			}
		}
		if(map.get(str.substring(start, str.length())) != null){
			map.put(str.substring(start, str.length()), map.get(str.substring(start, str.length())) + 1);
		}else {
			map.put(str.substring(start, str.length()), 1);
		}
		if(Debug.DEBUG)System.out.println("#Записую: " + str.substring(start, str.length()));
		return map;
	}
}


```
__LabVier.java__
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LabVier {
	public static void main(String[] args) {
		ArrayList<HashMap> strs = new ArrayList<HashMap>();
		if(Arrays.stream(args).anyMatch("-h"::equals) || Arrays.stream(args).anyMatch("-help"::equals)) {
			System.out.println("Автор: Олейник Василий\n"
							 + "Группа: КИТ 101.8а\n"
							 + "Задание: Ввести текст. Знайти та вивести, скільки разів повторюється в тексті кожне слово. Результат вивести у вигляді таблиці.\n"
							 + "Дата: 15.10.19\n");
		}
		if(Arrays.stream(args).anyMatch("-d"::equals) || Arrays.stream(args).anyMatch("-debug"::equals)) {
			Debug.DEBUG = true;
		}
		while(true) {
			System.out.print("1) Посчитать кол-во слов\n"
							 + "2) Результаты\n"
							 + "3) Выйти\n");
			Scanner sc = new Scanner(System.in);
			int c = Integer.parseInt(sc.nextLine());
			switch (c) {
			case 1:
				System.out.print("Введите строку: ");
				strs.add(CounterWords.countWords(sc.nextLine()));
				break;
			case 2:
				for(int i = 0; i < strs.size(); i++)
					System.out.println(strs.get(i));
				break;
			case 3:
				return;
			default:
				break;
			}
		}
	}
}
```
__Debug.java__
```java

public class Debug {
	public static boolean DEBUG = false;
}
```
**Вывод:**
> _<p>1) Посчитать кол-во слов</br>
2) Результаты</br>
3) Выйти</br>
1</br>
Введите строку:</br><p>_

**Висновок:** Реалізував діалоговий режим роботи з користувачем в консольних програмах мовою Java.
