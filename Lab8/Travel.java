import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Travel {
    private Way way;
    private int countPlaces;
    private ArrayList<String> days;
    private int number;

    public Travel(Way way, int countPlaces, ArrayList<String> days, int number) {
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

    public ArrayList<String> getDays() {
        return days;
    }

    public void setDays(ArrayList<String> days) {
        this.days = days;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        StringBuffer daysString = new StringBuffer();
        return  Travel.mapToString(this.way.getEstops()) + "\n" +
                this.way.getNameStation().toString() + "\n" +
                this.way.getCountFreePlaces() + "\n" +
                this.way.getStart() + "\n" +
                this.way.getStop() + "\n" +
                this.countPlaces + "\n" +
                this.number + "\n" +
                this.getDays() + "\n" +
                daysString;

    }
    public static <K, V> String mapToString(Map<K, V> map) {
    return map.entrySet()
        .stream()
        .map(entry -> entry.getKey() + ":" + entry.getValue())
        .collect(Collectors.joining(", ", "", ""));
    }
}