package ge.edu.freeuni.sdp.iot.switches.heating.core;

import org.json.JSONObject;

/**
 * Created by nika on 6/25/16.
 */
public class HouseEntry {

    private String id;
    private String switchIp;

    HouseEntry(String id, String switchIp) {
        this.id = id;
        this.switchIp = switchIp;
    }

    public String getId() {
        return id;
    }

    String getSwitchIp() {
        return switchIp;
    }

    static HouseEntry fromJson(JSONObject obj) {
        return new HouseEntry(obj.getJSONObject("RowKey").getString("_"),
                obj.getJSONObject("heat_ip").getString("_"));
    }

}
