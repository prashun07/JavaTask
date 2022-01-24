import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class WriteJson2 {
    public static void main(String[] args)
    {
    JSONObject js1=create_object("video_played","video_liked","I1");
    JSONObject data1=new JSONObject();
    data1.put("",js1);
    //2nd
     JSONObject js2=create_object("video_played","video_liked","I2");
     JSONObject data2=new JSONObject();
     data2.put("",js2);
    //3rd
    JSONObject js3=create_object("video_played","video_liked","I3");
    JSONObject data3=new JSONObject();
    data3.put("",js3);
    //4th
    JSONObject js4=create_object("video_shared","video_liked","I4");
    JSONObject data4=new JSONObject();
    data4.put("",js4);

    JSONObject js5=new JSONObject();
    js5.put("event_name","video_liked");
    js5.put("event_section","news");
    js5.put("client_id","c1");
   JSONObject data5=new JSONObject();
   data5.put("",js5);
        JSONArray jsArray=new JSONArray();
        jsArray.add(data1);
        jsArray.add(data2);
        jsArray.add(data3);
        jsArray.add(data4);
        jsArray.add(data5);
        try(FileWriter file=new FileWriter("file2.json")){
            file.write(jsArray.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }//end main
    public static JSONObject create_object(String event_name,String event_section,String item_id)
    { JSONObject js=new JSONObject();
        js.put("event_name",event_name);
        js.put("event_section",event_section);
        js.put("item_id",item_id);
        return js;

    }
}//end writeJson2
