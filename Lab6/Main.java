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
