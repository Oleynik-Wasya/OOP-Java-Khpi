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
