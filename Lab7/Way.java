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

