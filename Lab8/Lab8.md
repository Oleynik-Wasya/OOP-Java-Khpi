# Lab 8
### Основи введення/виведення Java SE
**Розробник:**
+ _Олійник Василь Максимович_
+ _КІТ 101.8а_
+ _Варіант: 9_

**Мета:** Оволодіння навичками управління введенням/виведенням даних з використанням класів платформи Java SE.

***Прикладні задачі:***
1. Забезпечити можливість збереження і відновлення масива об’єктів рішення завдання лабораторної роботи №7.

2. Забороняється використання стандартного протокола серіалізації.

3. Продемонструвати використання моделі Long Term Persistence.

4. Забезпечити діалог з користувачем у вигляді простого текстового меню.

5. При збереженні та відновленні даних забезпечити діалоговий режим вибору директорії з відображенням вмісту і можливістю переміщення по підкаталогах.

__Main.java__
```Java
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
```
__ReadTravel.java__
```Java
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

public class ReadTravell {
    static public Travel read(String fileName) throws IOException {
        Travel resTravel = new Travel(null, 0, null, 0);
        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse(fileName);

            // Получаем корневой элемент
            Node root = document.getDocumentElement();
            System.out.println(root.getNodeName());
            // Просматриваем все подэлементы корневого - т.е. книги
            NodeList travel = root.getChildNodes();
            String NameStation = new String();
            LocalTime start = LocalTime.parse("00:00");
            LocalTime Stop = LocalTime.parse("00:00");
            ArrayList<String> days = new ArrayList<String>();
            int FreePlaces = 0;
            int CountPlaces = 0;
            int Number = -1;
            HashMap<String, TreeMap<LocalTime, LocalTime>> estops = new HashMap<String, TreeMap<LocalTime, LocalTime>>();

            for (int i = 0; i < travel.getLength(); i++){
                if(travel.item(i).getNodeType() != Node.TEXT_NODE){
                    if(travel.item(i).getNodeName() == "Days"){
                        String str[] = travel.item(i).getTextContent().split(" ");
                        for (int m = 0; m < str.length; m++){
                            days.add(str[m]);
                        }
                    }
                    if(travel.item(i).getNodeName() == "Number"){
                        Number = Integer.valueOf(travel.item(i).getTextContent());
                    }
                    if(travel.item(i).getNodeName() == "FreePlaces"){
                        FreePlaces = Integer.valueOf(travel.item(i).getTextContent());
                    }
                    if(travel.item(i).getNodeName() == "CountPlaces"){
                        CountPlaces = Integer.valueOf(travel.item(i).getTextContent());
                    }
                    if(travel.item(i).getNodeName() == "NameStation"){
                        NameStation = travel.item(i).getTextContent();
                    }
                    if(travel.item(i).getNodeName() == "Start"){
                        start = LocalTime.parse(travel.item(i).getTextContent());
                    }
                    if(travel.item(i).getNodeName() == "Stop"){
                        Stop = LocalTime.parse(travel.item(i).getTextContent());
                    }
                    if(travel.item(i).getNodeName() == "Places"){
                        ArrayList<String> NamesEstops = new ArrayList<>();
                        ArrayList<String> StartsArray = new ArrayList<>();
                        ArrayList<String> StopsArray = new ArrayList<>();

                        NodeList places = travel.item(i).getChildNodes();
                        for (int j = 0; j < places.getLength(); j++)
                        {
                            if(places.item(j).getNodeType() != Node.TEXT_NODE){
                                NodeList estop = places.item(j).getChildNodes();
                                for(int k = 0; k < estop.getLength(); k++){
                                    if (estop.item(k).getNodeType() != Node.TEXT_NODE) {
                                        if(k == 1){
                                            NamesEstops.add(estop.item(k).getTextContent());
                                        }
                                        if(k == 3){
                                            StartsArray.add(estop.item(k).getTextContent());
                                        }
                                        if(k == 5){
                                            StopsArray.add(estop.item(k).getTextContent());
                                        }
                                    }
                                }
                            }
                        }
                        TreeMap<LocalTime, LocalTime> StopStarts = new TreeMap<LocalTime, LocalTime>();
                        for (int g = 0; g < NamesEstops.size(); g++){
                            StopStarts.put(LocalTime.parse(StartsArray.get(g)), LocalTime.parse(StopsArray.get(g)));
                            estops.put(NamesEstops.get(g), StopStarts);
                        }
                    }
                }
                Way way = new Way(NameStation, start, Stop, estops, FreePlaces);
                resTravel = new Travel(way, CountPlaces, days, Number);
            }

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        return resTravel;
    }
}
```
__Travel.java__
```Java
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
```

__Way.java__
```java
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

__WriteTravel.java__
```java
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.annotation.Target;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class WriteTravell {
    static public boolean write(Travel travel ,String fileName) throws ParserConfigurationException, TransformerException, FileNotFoundException {
          DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
          DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
          Document doc = docBuilder.newDocument();

          Element Travel = doc.createElement("Travel");
          Element NameStation = doc.createElement("NameStation");
          Element FreePlaces = doc.createElement("FreePlaces");
          Element Start = doc.createElement("Start");
          Element Stop = doc.createElement("Stop");
          Element Places = doc.createElement("Places");
          Element Number = doc.createElement("Number");
          Element CountPlaces = doc.createElement("CountPlaces");
          Element Days = doc.createElement("Days");

          doc.appendChild(Travel);
          Travel.appendChild(NameStation);
          Travel.appendChild(FreePlaces);
          Travel.appendChild(Start);
          Travel.appendChild(Stop);

          Travel.appendChild(Places);

          Travel.appendChild(Number);
          Travel.appendChild(CountPlaces);
          Travel.appendChild(Days);


          NameStation.appendChild(doc.createTextNode(travel.getWay().getNameStation()));
          FreePlaces.appendChild(doc.createTextNode(Integer.toString(travel.getWay().getCountFreePlaces())));
          Start.appendChild(doc.createTextNode(travel.getWay().getStart().toString()));
          Stop.appendChild(doc.createTextNode(travel.getWay().getStop().toString()));
          HashMap<String, TreeMap<LocalTime, LocalTime>> estops = travel.getWay().getEstops();
          Iterator<String> estopsIter = estops.keySet().iterator();
          int k = 0;
          while(estopsIter.hasNext()){
                Element Estop = doc.createElement("Estop" + k);
                Element NameEstop = doc.createElement("NameEstop");
                Element StartEstop = doc.createElement("StartEstop");
                Element StopEstop = doc.createElement("StopEstop");
                Places.appendChild(Estop);
                Estop.appendChild(NameEstop);
                Estop.appendChild(StartEstop);
                Estop.appendChild(StopEstop);
                String estopName = estopsIter.next();
                NameEstop.appendChild(doc.createTextNode(estopName));
                TreeMap<LocalTime, LocalTime> EstopTimes = estops.get(estopName);
                Iterator ETIter = EstopTimes.keySet().iterator();
                while (ETIter.hasNext()){
                      LocalTime Etime = (LocalTime) ETIter.next();
                      StartEstop.appendChild(doc.createTextNode(Etime.toString()));
                      StopEstop.appendChild(doc.createTextNode(EstopTimes.get(Etime).toString()));
                }
                k++;
          }

          Number.appendChild(doc.createTextNode(Integer.toString(travel.getNumber())));
          CountPlaces.appendChild(doc.createTextNode(Integer.toString(travel.getCountPlaces())));
          StringBuffer days = new StringBuffer();
          for (int i = 0; i < travel.getDays().size(); i++){
                days.append(travel.getDays().get(i) + " ");
          }
          Days.appendChild(doc.createTextNode(days.toString()));

          Transformer t = TransformerFactory.newInstance().newTransformer();
          t.setOutputProperty(OutputKeys.INDENT, "yes");
          t.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(fileName)));
          return true;
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<Travel>
    <NameStation>Germany</NameStation>
    <FreePlaces>20</FreePlaces>
    <Start>07:00</Start>
    <Stop>09:00</Stop>
    <Places>
        <Estop0>
            <NameEstop>Fire1</NameEstop>
            <StartEstop>07:30</StartEstop>
            <StopEstop>07:35</StopEstop>
        </Estop0>
        <Estop1>
            <NameEstop>Fire</NameEstop>
            <StartEstop>07:30</StartEstop>
            <StopEstop>07:35</StopEstop>
        </Estop1>
    </Places>
    <Number>1</Number>
    <CountPlaces>35</CountPlaces>
    <Days>Friday Monday </Days>
</Travel>
```

**Вывод:**
>_<p>1)Записать объект</br>
2)Считать объект</br>
3)Выйти</br>
2</br>
Введите путь: /home/wasya/IdeaProjects/LabAcht/temp.xml</br>
Travel</br>
Fire1:{07:30=07:35}, Fire:{07:30=07:35}</br>
Germany</br>
20</br>
07:00</br>
09:00</br>
35</br>
1</br>
[Friday, Monday]</br></p>_

**Висновок:**  Оволодів навичками управління введенням/виведенням даних з використанням класів платформи Java SE.
