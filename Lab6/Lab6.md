# Lab 6
### Серіалізація/десеріалізація об’єктів. Бібліотека класів користувача.
**Розробник:**
+ _Олійник Василь Максимович_
+ _КІТ 101.8а_
+ _Варіант: 9_

**Мета:**
  +  Тривале зберігання та відновлення стану об’єктів.
  +  Ознайомлення з принципами серіалізації/десеріалізації об’єктів.
  +  Використання бібліотек класів користувача.


***Прикладні задачі:***
1. Реалізувати і продемонструвати тривале зберігання/відновлення раніше розробленого контейнера за допомогою серіалізації/десеріалізації.

2. Обмінятися відкомпільованим (без початкового коду) службовим класом (Utility Class) рішення задачі л.р. №3 з іншим студентом (визначає викладач).

3. Продемонструвати послідовну та вибіркову обробку елементів розробленого контейнера за допомогою власного і отриманого за обміном службового класу.

4. Реалізувати та продемонструвати порівняння, сортування та пошук елементів у контейнері.

5. Розробити консольну програму та забезпечити діалоговий режим роботи з користувачем для демонстрації та тестування рішення.

__Collection.java__
```Java
import java.util.Iterator;

public interface Collection {
	Iterator<String> gerIterator();
}
```
__iterator.java__
```Java

public interface iterator {
	public boolean hasNext();
	public String next();
	public void remove();
}
```
__RawData.java__
```Java
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class RawData implements Collection, Serializable {
	private ArrayList<String> data;

	public RawData(ArrayList<String> data) {
		this.data = data;
	}

	public ArrayList<String> getData() {
		return data;
	}

	public void setData(ArrayList<String> data) {
		this.data = data;
	}

	@Override
	public Iterator<String> gerIterator() {
		return new DataIterator();
	}

	private class DataIterator<String> implements Iterator{

		int index;

		@Override
		public boolean hasNext() {
			if(index < data.size()) {
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			return data.get(index++);
		}

		@Override
		public void remove() {
			data.remove(--index);
		}

	}

	public String toString() {
		StringBuffer ret = new StringBuffer();
		for(int i = 0; i < this.data.size(); i++) {
			ret.append(data.get(i));
		}
		return ret.toString();
	}

	public void add(String str) {
		this.data.add(str);
	}

	public void clear() {
		data.clear();
	}

	public boolean remove(String string) {
		DataIterator iterator = new DataIterator();
		while(iterator.hasNext()) {
			if(iterator.next().equals(string)) {
				iterator.remove();
				return true;
			}
		}
		return false;
	}

	public Object[] toArray() {
		return this.data.toArray();
	}

	public int size() {
		return this.data.size();
	}

	public boolean contains(String string) {
		DataIterator iterator = new DataIterator();
		while(iterator.hasNext()) {
			if(iterator.next().toString().equals(string)) {
				return true;
			}
		}
		return false;
	}

	public boolean containsAll(RawData container) {
		return this.getData().equals(container.getData());
	}

	public void sort(){
		Collections.sort(this.data, String.CASE_INSENSITIVE_ORDER);
	}

	public int find(String str){
		int index = 0;
		Iterator iterator = this.gerIterator();
		while (iterator.hasNext()){
			index++;
			if (iterator.next().equals(str)){
				return index;
			}
		}
		return -1;
	}
}
```
__Main.java__
```Java
import classes.CounterWords;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
		ArrayList<String> data = new ArrayList<String>(
			    Arrays.asList("Aires", "Córdoba", "Plata", "hello", "Hi"));

		RawData rawData = new RawData(data);
		while(true) {
			System.out.print("1) Добавить слово\n"
							 + "2) Содержимое\n"
							 + "3) Выйти\n" +
					           "4) Сериализовать\n" +
					           "5) Десериализовать\n" +
							   "6) Отсортировать\n" +
							   "7) Найти\n");
			Scanner sc = new Scanner(System.in);
			int c = Integer.parseInt(sc.nextLine());
			switch (c) {
				case 1:
					System.out.print("Введите строку: ");
					rawData.add(sc.nextLine());
					break;
				case 2:
					Iterator iterator = rawData.gerIterator();
					while (iterator.hasNext()){
						System.out.print(iterator.next() + " ");
					}
					System.out.println();
					break;
				case 3:
					return;
				case 4:
					FileOutputStream fileOutputStream = new FileOutputStream("tempFile");
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
					objectOutputStream.writeObject(rawData);
					objectOutputStream.close();
					break;

				case 5:
					FileInputStream fileInputStream = new FileInputStream("tempFile");
					ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
					rawData = (RawData) objectInputStream.readObject();
					break;

				case 6:
					rawData.sort();
					break;

				case 7:
					System.out.print("Введите строку: ");
					System.out.println(rawData.find(sc.nextLine()));
					break;
				default:
					break;
				}
		}
    }
}
```

**Вывод:**
> _<p>1) Добавить слово </br>
2) Содержимое </br>
3) Выйти</br>
4) Сериализовать</br>
5) Десериализовать</br>
6) Отсортировать</br>
7) Найти</br>
4</br></p>_

**Висновок:**
+ Використав тривале зберігання та відновлення стану об’єктів.
+ Ознайомився з принципами серіалізації/десеріалізації об’єктів.
+ Використав бібліотки класів користувача.
