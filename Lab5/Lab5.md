# Lab 5
### Розробка власних контейнерів. Ітератори
**Розробник:**
+ _Олійник Василь Максимович_
+ _КІТ 101.8а_
+ _Варіант: 9_

**Мета:**
+ Набуття навичок розробки власних контейнерів.
+ Використання ітераторів.


***Прикладні задачі:***
1. Розробити клас-контейнер, що ітерується для збереження початкових даних завдання л.р. №3 у вигляді масиву рядків з можливістю додавання, видалення і зміни елементів.
2. В контейнері реалізувати та продемонструвати наступні методи:
  + String toString() повертає вміст контейнера у вигляді рядка;
  + void add(String string) додає вказаний елемент до кінця контейнеру;
  + void clear() видаляє всі елементи з контейнеру;
  + boolean remove(String string) видаляє перший випадок вказаного елемента з контейнера;
  + Object[] toArray() повертає масив, що містить всі елементи у контейнері;
  + int size() повертає кількість елементів у контейнері;
  + boolean contains(String string) повертає true, якщо контейнер містить вказаний елемент;
  + boolean containsAll(Container container) повертає true, якщо контейнер містить всі елементи з зазначеного у параметрах;
  + public Iterator<String> iterator() повертає ітератор відповідно до Interface Iterable.
3. В класі ітератора відповідно до Interface Iterator реалізувати методи:
  + public boolean hasNext();
  + public String next();
  + public void remove().

4. Продемонструвати роботу ітератора за допомогою циклів while и for each.
5. Забороняється використання контейнерів (колекцій) і алгоритмів з Java Collections Framework.

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
__LabFunf.java__
```Java
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
```
__RawData.java__
```Java
import java.util.ArrayList;
import java.util.Iterator;

public class RawData implements Collection{
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

}
```

**Вывод:**
>_#Print data by for each..</br>
Buenos Aires Córdoba La Plata hello Hi </br>
#Add new string..</br>
Buenos AiresCórdobaLa PlatahelloHiAdded string</br>
#Remove 'Hi'..</br>
Buenos AiresCórdobaLa PlatahelloAdded string</br>
#Print data be while..</br>
1)Buenos Aires</br>
2)Córdoba</br>
3)La Plata</br>
4)hello</br>
5)Added string</br>
#Use size..</br>
Size of rawData = 5</br>

**Висновок:** Набув навичок розробки власних контейнерів. Використав ітератори.
