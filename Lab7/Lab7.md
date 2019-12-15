# Lab 7
### Об’єктно-орієнтована декомпозиція
**Розробник:**
+ _Олійник Василь Максимович_
+ _КІТ 101.8а_
+ _Варіант: 9_

**Мета:** Використання об’єктно-орієнтованого підходу для розробки об’єкта предметної (прикладної) галузі.

***Прикладні задачі:***
1. Квиткова каса. Дані про маршрут: маршрут - необмежений набір значень у вигляді “назва станції, час прибуття (для проміжних і кінцевої), час відправлення (для початкової та проміжних), кількість вільних місць”; загальна кількість місць; дні тижня; номер рейсу.

__TrafficOffice.java__
```Java
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
```
__Way.java__
```Java
import java.time.LocalTime;
import java.util.HashMap;
import java.util.TreeMap;

public class Way {
    private String nameStation;
    private HashMap<String, TreeMap<LocalTime, LocalTime>> estops;
    private int countFreePlaces;
    private LocalTime start;
    private LocalTime stop;

    public Way(String nameStation, LocalTime start, LocalTime stop, HashMap<String, TreeMap<LocalTime, LocalTime>> estops, int countFreePlaces) {
        this.nameStation = nameStation;
        this.estops = estops;
        this.countFreePlaces = countFreePlaces;
        this.start = start;
        this.stop = stop;
    }

    public String getNameStation() {
        return nameStation;
    }

    public void setNameStation(String nameStation) {
        this.nameStation = nameStation;
    }

    public HashMap<String, TreeMap<LocalTime, LocalTime>> getEstops() {
        return estops;
    }

    public void setEstops(HashMap<String, TreeMap<LocalTime, LocalTime>> estops) {
        this.estops = estops;
    }

    public int getCountFreePlaces() {
        return countFreePlaces;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getStop() {
        return stop;
    }

    public void setStop(LocalTime stop) {
        this.stop = stop;
    }

    public void setCountFreePlaces(int countFreePlaces) {
        this.countFreePlaces = countFreePlaces;
    }
}
```
__Travel.java__
```Java
import java.time.LocalTime;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Travel {
    private Way way;
    private int countPlaces;
    private DAYS[] days;
    private int number;

    public Travel(Way way, int countPlaces, DAYS[] days, int number) {
        this.way = way;
        this.countPlaces = countPlaces;
        this.days = days;
        this.number = number;
    }

    public Way getWay() {
        return way;
    }

    public void setWay(Way way) {
        this.way = way;
    }

    public int getCountPlaces() {
        return countPlaces;
    }

    public void setCountPlaces(int countPlaces) {
        this.countPlaces = countPlaces;
    }

    public DAYS[] getDays() {
        return days;
    }

    public void setDays(DAYS[] days) {
        this.days = days;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public static void printDescription(Travel travel){
        System.out.println("Station Name: " + travel.way.getNameStation());
        System.out.println("Station Number: " + travel.number);
        System.out.println("Count of places: " + travel.countPlaces);
        System.out.print("Works on: ");
        for(DAYS day : travel.days){
            System.out.print(day + " ");
        }
        System.out.println();
        System.out.println("Count of Free Places: " + travel.way.getCountFreePlaces());
        Iterator it = travel.way.getEstops().entrySet().iterator();
        System.out.println("Estops:");
        while (it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            System.out.print(pair.getKey() + " ");
            System.out.println(pair.getValue());
        }
        System.out.println("Start: " + travel.way.getStart());
        System.out.println("Stop: " + travel.way.getStop());
    }
}

enum DAYS {
    Monday,
    Tuesday,
    Wednesday,
    Thursday,
    Friday,
    Saturday,
    Sunday;
}
```

**Вывод:**
> _<p>Station Name: Германия</br>
Station Number: 1</br>
Count of places: 35</br>
Works on: Friday Monday </br>
Count of Free Places: 20</br>
Estops:</br>
Fire {07:30=07:35}</br>
Start: 07:00</br>
Stop: 09:00</br></p>_

**Висновок:**  Використав об’єктно-орієнтованний підхід для розробки об’єкта предметної (прикладної) галузі.
