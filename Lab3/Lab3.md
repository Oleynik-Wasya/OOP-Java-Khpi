# Lab 3
### Утилітарні класи. Обробка масивів і рядків
**Розробник:**
+ _Олійник Василь Максимович_
+ _КІТ 101.8а_
+ _Варіант: 9_

**Мета:**
+  Розробка власних утилітарних класів.
+  Набуття навичок вирішення прикладних задач з використанням масивів і рядків.

***Прикладні задачі:***
1. Ввести текст. Знайти та вивести, скільки разів повторюється в тексті кожне слово. Результат вивести у вигляді таблиці.


__LabDrei.java:__
```Java
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

```
__CounterWords.java__
```java
import java.util.HashMap;

public class CounterWords {
	StringBuffer str;
	public CounterWords(String str){
		this.str = new StringBuffer(str);
	}
	public void cleanStr() {
		while(this.str.indexOf("  ") != -1) {
			this.str.delete(this.str.indexOf("  "), this.str.indexOf("  ") + 1);
		}
		if(this.str.charAt(0) == ' ') {
			this.str.delete(0, 1);
		}
		if(this.str.charAt(this.str.length() - 1) == ' ') {
			this.str.setLength(this.str.length() - 1);
		}
		String stopwords[] = {".",",","!","?"};
		for(int i = 0; i < stopwords.length; i++) {
			while(this.str.indexOf(stopwords[i]) != -1)
				this.str.delete(this.str.indexOf(stopwords[i]), this.str.indexOf(stopwords[i]) + 1);
		}
	}
	public HashMap<String, Integer> countWords() {
		cleanStr();
		HashMap< String, Integer > map = new HashMap< String, Integer >();
		int start = 0;
		int end = 0;
		for(int i = 0; i < this.str.length(); i++) {
			if(this.str.charAt(i) == ' ') {
				end = i;
				if(map.get(this.str.substring(start, end)) != null) {
					map.put(this.str.substring(start, end), map.get(this.str.substring(start, end)) + 1);
					start = i + 1;
					continue;
				}
				map.put(this.str.substring(start, end), 1);
				start = i + 1;
			}
		}
		if(map.get(this.str.substring(start, this.str.length())) != null){
			map.put(this.str.substring(start, this.str.length()), map.get(this.str.substring(start, this.str.length())) + 1);
		}else {
			map.put(this.str.substring(start, str.length()), 1);
		}
		return map;
	}
}
```
**Вывод:**
> [1, 3, 2, 1, 1, 1]</br>
       How         1</br>
        hi         3</br>
     world         2</br>
     Hello         1</br>
       are         1</br>
       you         1</br>
Hello world How are you world hi hi hi</br>

**Висновок:** Набув навичок вирішення прикладних задач з використанням масивів і рядків.
