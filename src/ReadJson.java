import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ReadJson {

public static void main(String[] args) throws IOException, ParseException {
    //Reading file1.json
    JSONParser jsonParse = new JSONParser();
    FileReader reader = new FileReader("C:\\Users\\Prashum\\IdeaProjects\\task111\\file1.json");
    Object obj= jsonParse.parse(reader);
    JSONArray array = (JSONArray) obj;
    ArrayList<ArrayList<String>>List=new ArrayList<>();
    for(Object x:array)
        {
        parseArrayObject((JSONObject) x,List);
        }
    //Reading file2.json
    JSONParser jsonParse2 = new JSONParser();
    FileReader reader2 = new FileReader("C:\\Users\\Prashum\\IdeaProjects\\task111\\file2.json");
    Object obj2 = jsonParse2.parse(reader2);
    JSONArray array2 = (JSONArray) obj2;
    HashMap<String,Set<String>>key_map=new HashMap<String,Set<String>>();
    int counter=0;
    for(Object x:array2)
        {
        compareObject((JSONObject) x,List,counter,key_map);
        counter++;
        }//end for
    for(Map.Entry<String,Set<String>> map:key_map.entrySet())//to print final result
        {
//        System.out.println("Key is:"+map.getKey()+" Values is:"+map.getValue());
        print_result(map.getKey(),map.getValue());
        }//end for

}//end main

    public static void print_result(String key, Set<String> setValue) {
     int first_count=0;
     int second_count=0;

     Iterator it=setValue.iterator();
     while(it.hasNext())
     {
         String valueInsideSet = (String) it.next();
         String[] part = valueInsideSet.split(",", 2);
         if (part[0] != null)
         {
             first_count++;
         }//end if
         if (part[1]!=null)
         {
             second_count++;
         }//end if
     }// end while
        System.out.println(key+","+first_count+","+second_count);

    }//end print_result

    /*parseArrayObject() is used to parse json object from arr and kept stored in list
    * */
    public static void parseArrayObject(JSONObject arr, ArrayList<ArrayList<String>> list)
    {   JSONObject js=(JSONObject) arr.get("");
        String event_name=(String)js.get("event_name");
        String event_section=(String)js.get("event_section");
        String client_id=(String)js.get("client_id");
        String item_id=(String)js.get("item_id");
        ArrayList<String>Plist=new ArrayList<>();
        Plist.add(event_name);
        Plist.add(event_section);
        Plist.add(client_id);
        Plist.add(item_id);
        list.add(Plist);
    }//end parseArrayObject
    public static void compareObject(JSONObject arr2, ArrayList<ArrayList<String>> list, int index, HashMap<String, Set<String>> key_map)
    {   JSONObject js=(JSONObject) arr2.get("");
        String event_name=(String)js.get("event_name");
        String event_section=(String)js.get("event_section");
        String item_id=(String)js.get("item_id");
        String name=list.get(index).get(0);
        String section=list.get(index).get(1);
        String client=list.get(index).get(2);
        Set<String>set=new HashSet<>();
        if(name.equals(event_name) && (event_section.equals(section)))
            { boolean contain_key= key_map.containsKey(event_name+","+event_section);
                if(contain_key)
                    {
                      Set<String>prev_set=key_map.get(event_name+","+event_section);
                      prev_set.add(client+","+item_id);
                      key_map.put(event_name+","+event_section,prev_set);
                    }//end if
                else{
                    set.add(client + "," + item_id);
                    key_map.put(event_name+","+event_section,set);
                    }
                //System.out.println(event_name+","+event_section+","+client+","+item_id);
            }
        else{
            Set<String>set2=new HashSet<>();
            set2.add(client+","+item_id);
            //System.out.println(event_name+","+event_section+","+client+","+item_id);
            key_map.put(event_name+","+event_section,set2);
            }
    }//end
}//end ReadJson class
