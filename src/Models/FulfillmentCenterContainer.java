package Models;
import java.util.*;

public class FulfillmentCenterContainer {
    private Map<String,FulfillmentCenter> fulfillmentCenterMap;

    public FulfillmentCenterContainer(){
        fulfillmentCenterMap = new TreeMap<String, FulfillmentCenter>();
    }

    //  i. addCenter(String, double) – dodającą nowy magazyn o podanej nazwie i zadanej
    //  pojemności do spisu magazynów
    public void AddCenter(String name, double maxStorage){
        fulfillmentCenterMap.put(name,new FulfillmentCenter(name,maxStorage));
    }

    //  ii. removeCenter(String) – usuwający magazyn o podanej nazwie
    public FulfillmentCenter RemoveCenter(String name){
        return fulfillmentCenterMap.remove(name);
    }

    //  iii. findEmpty() – zwracający listę pustych magazynów
    public List<FulfillmentCenter> FindEmpty(){
        List<FulfillmentCenter> result = new ArrayList<FulfillmentCenter>();
        Set keys = fulfillmentCenterMap.keySet();
        for(Iterator i = keys.iterator(); i.hasNext();) {
            String key = (String)i.next();
            if(fulfillmentCenterMap.get(key).ProductList.isEmpty()){
                result.add(fulfillmentCenterMap.get(key));
            }
        }
        return result;
    }

    //  iv. summary() – wypisujący na standardowe wyjście informacje zawierające: nazwę magazynu
    //    i procentowe zapełnienie
    public void Summary(){
        Set keys = fulfillmentCenterMap.keySet();
        for (Object key : keys) {
            String loopKey = (String) key;
            System.out.print(fulfillmentCenterMap.get(loopKey).Name + ": ");
            System.out.println(fulfillmentCenterMap.get(loopKey).FillingStatus() + "% used");
        }
    }
}