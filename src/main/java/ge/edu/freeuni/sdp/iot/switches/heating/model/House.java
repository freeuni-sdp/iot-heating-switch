package ge.edu.freeuni.sdp.iot.switches.heating.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nika on 6/23/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class House {

    @JsonProperty("switches")
    private Map<String, Switch> switches;

    @JsonProperty("houseId")
    private String houseId;

    public House(String houseId) {
        this.houseId = houseId;
        switches = new HashMap<>();
    }

    public String getHouseId() {
        return houseId;
    }

    public void add(Switch s) {
        switches.put(s.getId(), s);
    }

    public Switch get(String floorId) {
        return switches.get(floorId);
    }

    public static House fromJson(JSONObject obj) {
        String houseId = obj.getString("houseId");
        House res = new House(houseId);

        JSONObject switches = obj.getJSONObject("switches");

        for (String floorId : switches.keySet()) {
            res.add(Switch.fromJson(switches.getJSONObject(floorId)));
        }

        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        House house = (House) o;

        if (switches != null ? !switches.equals(house.switches) : house.switches != null) return false;
        return houseId != null ? houseId.equals(house.houseId) : house.houseId == null;

    }

    @Override
    public int hashCode() {
        int result = switches != null ? switches.hashCode() : 0;
        result = 31 * result + (houseId != null ? houseId.hashCode() : 0);
        return result;
    }
}
