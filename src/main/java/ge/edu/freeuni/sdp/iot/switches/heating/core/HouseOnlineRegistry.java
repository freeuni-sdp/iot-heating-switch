package ge.edu.freeuni.sdp.iot.switches.heating.core;

import ge.edu.freeuni.sdp.iot.switches.heating.model.House;
import ge.edu.freeuni.sdp.iot.switches.heating.model.Switch;
import ge.edu.freeuni.sdp.iot.switches.heating.model.SwitchOnRequest;
import org.json.JSONObject;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nika on 6/25/16.
 */
public class HouseOnlineRegistry implements HouseRegistry {

    private String registryUrl;
    private Map<String, House> oldMap;

    HouseOnlineRegistry(String registryUrl) {
        this.registryUrl = registryUrl;
        this.oldMap = new HashMap<>();
    }

    @Override
    public synchronized House getHouse(String houseId) {
        HouseEntry entry = getHouseEntry(houseId);

        if (entry == null)
            return null;

        Response resp = getGetResponse("https://" + entry.getSwitchIp() +
                "/house/" + houseId + "/heating");

        if (resp == null)
            return null;
        String respStr = resp.readEntity(String.class);
        House house = House.fromJson(new JSONObject(respStr));
        oldMap.put(houseId, house);
        return house;
    }

    @Override
    public synchronized Switch getSwitch(String houseId, String switchId) {
        HouseEntry entry = getHouseEntry(houseId);

        if (entry == null)
            return getSavedSwitch(houseId, switchId);

        Response resp = getGetResponse("https://" + entry.getSwitchIp() +
                "/house/" + houseId + "/floor/" + switchId + "/heating");

        if (resp == null)
            return getSavedSwitch(houseId, switchId);

        String respStr = resp.readEntity(String.class);

        Switch res = Switch.fromJson(new JSONObject(respStr));

        if (oldMap.containsKey(houseId)) {
            oldMap.get(houseId).add(res);
        } else {
            House h = new House(houseId);
            h.add(res);
            oldMap.put(houseId, h);
        }

        return res;
    }

    private Switch getSavedSwitch(String houseId, String switchId) {
        if (oldMap.containsKey(houseId)) {
            Switch res = oldMap.get(houseId).get(switchId);
            if (res != null)
                res.setAvailable(false);
            return res;
        }
        return null;
    }

    @Override
    public synchronized boolean switchOn(String houseId, String switchId, SwitchOnRequest request) {
        HouseEntry entry = getHouseEntry(houseId);

        if (entry == null)
            return false;

        Client client = ClientBuilder.newClient();
        Response resp = client.target("https://" + entry.getSwitchIp() +
                "/house/" + houseId + "/floor/" + switchId + "/heating")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(request, MediaType.APPLICATION_JSON));
        return resp.getStatus() == 200;
    }

    @Override
    public synchronized boolean switchOff(String houseId, String switchId) {
        HouseEntry entry = getHouseEntry(houseId);

        if (entry == null)
            return false;

        Client client = ClientBuilder.newClient();
        Response resp = client.target("https://" + entry.getSwitchIp() +
                "/house/" + houseId + "/floor/" + switchId + "/heating")
                .request(MediaType.APPLICATION_JSON)
                .delete();
        return resp.getStatus() == 200;
    }

    private HouseEntry getHouseEntry(String houseId) {
        Response resp = getGetResponse(registryUrl + houseId);

        if (resp == null)
            return null;

        String respJson = resp.readEntity(String.class);

        return HouseEntry.fromJson(new JSONObject(respJson));
    }

    private Response getGetResponse(String url) {
        Client client = ClientBuilder.newClient();
        Response response = client.target(url)
                        .request(MediaType.APPLICATION_JSON)
                        .get();

        int responseStatus = response.getStatus();
        if (responseStatus != Response.Status.OK.getStatusCode())
            return null;

        return response;
    }

}
