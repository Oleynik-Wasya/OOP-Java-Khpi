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
