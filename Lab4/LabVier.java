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
