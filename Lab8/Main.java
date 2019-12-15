import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException, TransformerException, ParserConfigurationException {
        HashMap<String, TreeMap<LocalTime, LocalTime>> estops1 = new HashMap<String, TreeMap<LocalTime, LocalTime>>();
        TreeMap<LocalTime, LocalTime> StopStarts = new TreeMap<LocalTime, LocalTime>();
        StopStarts.put(LocalTime.of(7, 30, 0), LocalTime.of(7, 35, 0));
        estops1.put("Fire", StopStarts);
        estops1.put("Fire1", StopStarts);

        Way Way1 = new Way("Germany", LocalTime.of(7, 0, 0), LocalTime.of(9, 0, 0), estops1, 20);
        ArrayList<String> days1 = new ArrayList<String>();
        days1.add("Friday");
        days1.add("Monday");
        Travel travel1 = new Travel(Way1, 35, days1, 1);

        while(true) {
			System.out.print("1) Записать объект\n"
							 + "2) Считать объект\n"
							 + "3) Выйти\n");
			Scanner sc = new Scanner(System.in);
			int c = Integer.parseInt(sc.nextLine());
			switch (c) {
				case 1:
                    System.out.print("Введите путь: ");
                    String filePath = sc.nextLine();
                    WriteTravell.write(travel1, filePath);
					break;
				case 2:
                    System.out.print("Введите путь: ");
                    String filePath1 = sc.nextLine();
                    Travel ReadTravel = ReadTravell.read(filePath1);
                    System.out.println(ReadTravel.toString());
                    break;
				case 3:
					return;
				default:
					break;
				}
		}
    }
}
