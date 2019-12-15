import javax.xml.crypto.Data;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TrafficOffice {
    public static void main(String[] args) {
        HashMap<String, TreeMap<LocalTime, LocalTime>> estops1 = new HashMap<String, TreeMap<LocalTime, LocalTime>>();
        TreeMap<LocalTime, LocalTime> StopStarts = new TreeMap<LocalTime, LocalTime>();
        StopStarts.put(LocalTime.of(7, 30, 0), LocalTime.of(7, 35, 0));
        estops1.put("Fire", StopStarts);
        Way Way1 = new Way("Германия", LocalTime.of(7, 0, 0), LocalTime.of(9, 0, 0), estops1, 20);
        DAYS[] days1 = {DAYS.Friday, DAYS.Monday};
        Travel travel1 = new Travel(Way1, 35, days1, 1);

        LinkedList<Travel> travels = new LinkedList<Travel>();

        travels.add(travel1);
        for(Object o : travels){
            Travel.printDescription((Travel) o);
            System.out.println();
        }
    }
}