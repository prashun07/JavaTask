import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonStreamParser;
import java.io.*;
import java.util.*;

public class readJson{
    public static void main(String[] args) throws IOException {
        HashMap<String, Set<String>>Client_map=new HashMap<>();
        HashMap<String,Set<String>>Item_map=new HashMap<>();
        readFile("file1",Client_map,Item_map);
        readFile("file2",Client_map,Item_map);
        /*
        UnComment to Print Both map
        System.out.println(Client_map);
        System.out.println(Item_map);
        */

        //Below code  print the final result
        System.out.println("Printing Final Result...");
        System.out.println("Event Name,Event Section,Count(Distinct_ClientID),Count(Distinct_ItemId)");
        for(Map.Entry<String,Set<String>>map:Client_map.entrySet())
        { String key= map.getKey();
            Set<String>set1=map.getValue();
            int clientCount;
            int itemCount=0;
            if(Item_map.containsKey(key))
            { Set<String> set2=Item_map.get(key);
                itemCount=set2.size();
            }
            clientCount=set1.size();
            System.out.println(key+","+clientCount+","+itemCount);
        }
    }//end main

    public static void readFile(String FileName, HashMap<String, Set<String>> client_map, HashMap<String, Set<String>> item_map) throws IOException{

        FileInputStream file=new FileInputStream("C:\\Users\\Prashum\\IdeaProjects\\JsonTask\\src\\"+FileName);
        Reader r = new InputStreamReader(file, "UTF-8");
        Gson gson = new GsonBuilder().create();
        JsonStreamParser p = new JsonStreamParser(r);

        while (p.hasNext()) {
            /*
            This while loop read each line from the given file
            And finally map client_id and item_id in corresponding Hashmap
             */
            JsonElement e = p.next();
            if (e.isJsonObject()) {
                PojoGson pj = gson.fromJson(e, PojoGson.class); //PojoGson is a class defined in separate file
                String item = pj.getItemId();
                if (item != null) {
                    boolean keyContain = item_map.containsKey(pj.getEventName() + "," + pj.getEventSection());
                    if (keyContain) {
                        Set<String> Prev_set = item_map.get(pj.getEventName() + "," + pj.getEventSection());
                        Prev_set.add(item);
                        item_map.put(pj.getEventName() + "," + pj.getEventSection(), Prev_set);
                    } else {
                        Set<String> set = new HashSet<>();
                        set.add(item);
                        item_map.put(pj.getEventName() + "," + pj.getEventSection(), set);
                    }
                }//end item
                String Client = pj.getClientId();
                if (Client != null) {
                    boolean keyContain = client_map.containsKey(pj.getEventName() + "," + pj.getEventSection());
                    if (keyContain) {
                        Set<String> Prev_set = client_map.get(pj.getEventName() + "," + pj.getEventSection());
                        Prev_set.add(Client);
                        client_map.put(pj.getEventName() + "," + pj.getEventSection(), Prev_set);
                    } else {
                        Set<String> set = new HashSet<>();
                        set.add(Client);
                        client_map.put(pj.getEventName() + "," + pj.getEventSection(), set);
                    }
                }//end Client
            }//end if
        }//end while loop
        r.close();
    }//end readFile()
}