package ge.edu.freeuni.sdp.iot.switches.heating;

import ge.edu.freeuni.sdp.iot.switches.heating.core.HouseRegistry;
import ge.edu.freeuni.sdp.iot.switches.heating.core.HouseRegistryFactory;
import ge.edu.freeuni.sdp.iot.switches.heating.model.Switch;
import ge.edu.freeuni.sdp.iot.switches.heating.model.SwitchOnRequest;
import ge.edu.freeuni.sdp.iot.switches.heating.core.HouseOnlineRegistry;

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
        return reg.getSwitch(pHouseId, pFloorId);
    }

    private void setSwitchStatus(String pHouseId, String pFloorId,
                                     boolean value, Integer interval) {
        HouseRegistry reg = HouseRegistryFactory.getHouseRegistry();
        if (value)
            reg.switchOn(pHouseId, pFloorId, new SwitchOnRequest(interval));
        else
            reg.switchOff(pHouseId, pFloorId);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response put(@PathParam("house_id") String pHouseId,
                        @PathParam("floor_id") String pFloorId,
                        SwitchOnRequest request) {
        setSwitchStatus(pHouseId, pFloorId, true, request.getPeriod());
        return Response.ok().build();
    }

    @DELETE
    public Response delete(@PathParam("house_id") String pHouseId,
                           @PathParam("floor_id") String pFloorId) {
        setSwitchStatus(pHouseId, pFloorId, false, null);
        return Response.ok().build();
    }

}
