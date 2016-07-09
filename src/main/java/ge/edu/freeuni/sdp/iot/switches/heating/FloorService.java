package ge.edu.freeuni.sdp.iot.switches.heating;

import ge.edu.freeuni.sdp.iot.switches.heating.core.HouseRegistry;
import ge.edu.freeuni.sdp.iot.switches.heating.core.HouseRegistryFactory;
import ge.edu.freeuni.sdp.iot.switches.heating.model.Switch;
import ge.edu.freeuni.sdp.iot.switches.heating.model.SwitchOnRequest;
import org.json.JSONObject;

import javax.management.ServiceNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Created by nika on 6/23/16.
 */
@Path("/house/{house_id}/floor/{floor_id}")
public class FloorService {

    @GET
    @Produces( { MediaType.APPLICATION_JSON})
    public Switch get(@PathParam("house_id") String pHouseId,
                      @PathParam("floor_id") String pFloorId) {
        HouseRegistry reg = HouseRegistryFactory.getHouseRegistry();
        Switch res = reg.getSwitch(pHouseId, pFloorId);
        if (res == null)
            return new Switch(pFloorId, false).setAvailable(false);
        else
            return res;
    }

    private boolean setSwitchStatus(String pHouseId, String pFloorId,
                                    boolean value, Integer interval) {
        HouseRegistry reg = HouseRegistryFactory.getHouseRegistry();
        if (value)
            return reg.switchOn(pHouseId, pFloorId, new SwitchOnRequest(interval));
        else
            return reg.switchOff(pHouseId, pFloorId);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response put(@PathParam("house_id") String pHouseId,
                        @PathParam("floor_id") String pFloorId,
                        String reqStr) {
        SwitchOnRequest request = SwitchOnRequest.fromJson(new JSONObject(reqStr));
        if (setSwitchStatus(pHouseId, pFloorId, true, request.getPeriod()))
            return Response.ok().build();
        else
            throw new NotFoundException();
    }

    @DELETE
    public Response delete(@PathParam("house_id") String pHouseId,
                           @PathParam("floor_id") String pFloorId) {
        if (setSwitchStatus(pHouseId, pFloorId, false, null))
            return Response.ok().build();
        else
            throw new NotFoundException();
    }

}
