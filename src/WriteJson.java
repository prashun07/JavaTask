import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JsonObject;
import java.io.FileWriter;
import java.io.IOException;

public class WriteJson {
    public static void main(String[] args)
    {
        JSONObject js=new JSONObject();
        create_object(js,"video_played","video_liked","c1");
        JSONObject data1=new JSONObject();
        data1.put("",js);

        //2nd
        JSONObject js1=new JSONObject();
        create_object(js1,"video_played","video_liked","c2");
        JSONObject data2=new JSONObject();
        data2.put("",js1);

        //3rd
        JSONObject js2=new JSONObject();
        create_object(js2,"video_played","video_liked","c3");
        JSONObject data3=new JSONObject();
        data3.put("",js2);

        //4th data
        JSONObject js3=new JSONObject();
        create_object(js3,"video_shared","video_liked","c1");
        JSONObject data4=new JSONObject();
        data4.put("",js3);

        //5th data
        JSONObject js4=new JSONObject();
        create_object(js4,"video_liked","news","c1");
        JSONObject data5=new JSONObject();
        data5.put("",js4);


        JSONArray jsArray=new JSONArray();
        jsArray.add(data1);
        jsArray.add(data2);
        jsArray.add(data3);
        jsArray.add(data4);
        jsArray.add(data5);
        try(FileWriter file=new FileWriter("file1.json")){
               file.write(jsArray.toJSONString());
               file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }//end main
    public static  void create_object(JSONObject js, String event_name,String event_section,String client_id)
    {
        js.put("event_name",event_name);
        js.put("event_section",event_section);
        js.put("client_id",client_id);
    }

}//end WriteJson
