package ge.edu.freeuni.sdp.iot.switches.heating.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

/**
 * Created by nika on 6/23/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Switch {

    @JsonProperty("id")
    private String id;

    @JsonProperty("status")
    private boolean status;

    @JsonProperty("available")
    private boolean available;

    public Switch(String id, boolean status) {
        this.id = id;
        this.status = status;
        this.available = true;
    }

    public String getId() {
        return id;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public Switch setAvailable(boolean value) {
        this.available = value;
        return this;
    }

    public static Switch fromJson(JSONObject object) {
        return new Switch(object.getString("id"), object.getBoolean("status"));
    }
}
