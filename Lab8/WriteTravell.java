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
