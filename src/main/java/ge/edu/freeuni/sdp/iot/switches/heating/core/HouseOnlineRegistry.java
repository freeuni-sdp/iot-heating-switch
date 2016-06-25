package ge.edu.freeuni.sdp.iot.switches.heating.core;

import ge.edu.freeuni.sdp.iot.switches.heating.model.House;
import ge.edu.freeuni.sdp.iot.switches.heating.model.Switch;
import ge.edu.freeuni.sdp.iot.switches.heating.model.SwitchOnRequest;
import org.json.JSONObject;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by nika on 6/25/16.
 */
public class HouseOnlineRegistry implements HouseRegistry {

    private String registryUrl;

    HouseOnlineRegistry(String registryUrl) {
        this.registryUrl = registryUrl;
    }

    @Override
    public synchronized House getHouse(String houseId) {
        HouseEntry entry = getHouseEntry(houseId);

        if (entry == null)
            return null;

        Response resp = getGetResponse("https://" + entry.getSwitchIp() + "/");

        if (resp == null)
            return null;

        return resp.readEntity(House.class);
    }

    @Override
    public synchronized Switch getSwitch(String houseId, String switchId) {
        HouseEntry entry = getHouseEntry(houseId);

        if (entry == null)
            return null;

        Response resp = getGetResponse("https://" + entry.getSwitchIp() + "/floor/" + switchId);

        if (resp == null)
            return null;

        return resp.readEntity(Switch.class);
    }

    @Override
    public synchronized void switchOn(String houseId, String switchId, SwitchOnRequest request) throws NotFoundException {
        HouseEntry entry = getHouseEntry(houseId);

        if (entry == null)
            throw new NotFoundException();

        Client client = ClientBuilder.newClient();
        client.target("https://" + entry.getSwitchIp() + "/floor/" + switchId)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(request, MediaType.APPLICATION_JSON));
    }

    @Override
    public synchronized void switchOff(String houseId, String switchId) {
        HouseEntry entry = getHouseEntry(houseId);

        if (entry == null)
            throw new NotFoundException();

        Client client = ClientBuilder.newClient();
        client.target("https://" + entry.getSwitchIp() + "/floor/" + switchId)
                .request(MediaType.APPLICATION_JSON)
                .delete();
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