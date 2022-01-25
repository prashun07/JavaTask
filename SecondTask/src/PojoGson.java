import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PojoGson {
    @SerializedName("event_name")
    @Expose
    private String eventName;
    @SerializedName("event_section")
    @Expose
    private String eventSection;
    @SerializedName("client_id")
    @Expose
    private String clientId;
    @SerializedName("item_id")
    @Expose
    private String itemId;
    public String getEventName() {
        return eventName;
    }
    public String getEventSection() {
        return eventSection;
    }
    public String getClientId() {
        return clientId;
    }
    public  String getItemId() {
        return itemId;
    }
}//end PojoGson Class
