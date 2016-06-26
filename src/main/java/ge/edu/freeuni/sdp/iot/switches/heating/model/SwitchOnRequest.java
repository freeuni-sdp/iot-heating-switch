package ge.edu.freeuni.sdp.iot.switches.heating.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

/**
 * Created by nika on 6/23/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SwitchOnRequest {

    @JsonProperty("period")
    private int period;

    public SwitchOnRequest(int period) {
        this.period = period;
    }

    public int getPeriod() {
        return period;
    }

    public static SwitchOnRequest fromJson(JSONObject object) {
        return new SwitchOnRequest(object.getInt("period"));
    }

}
