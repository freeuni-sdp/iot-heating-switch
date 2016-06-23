package ge.edu.freeuni.sdp.iot.switches.heating;

import ge.edu.freeuni.sdp.iot.switches.heating.model.House;
import ge.edu.freeuni.sdp.iot.switches.heating.model.Houses;
import ge.edu.freeuni.sdp.iot.switches.heating.model.Switch;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by nika on 6/23/16.
 */
@Path("/house/{house_id}/floor/{floor_id}")
public class FloorService {

    @GET
    public Response get(@PathParam("house_id") String pHouseId,
                        @PathParam("floor_id") String pFloorId) {
        try {
            Integer houseId = Integer.valueOf(pHouseId);
            Integer floorId = Integer.valueOf(pFloorId);

            Houses houses = Houses.getInstance();
            House house = houses.get(houseId);
            Switch floorSwitch = house.get(floorId);

            StringBuilder b = new StringBuilder();
            b.append("{");

            b.append("\"status\": ");
            if (floorSwitch.getStatus())
                b.append("\"on\"");
            else
                b.append("\"off\"");

            b.append("}");

            return Response.ok().entity(b.toString()).build();
        } catch (NumberFormatException|NullPointerException e) {
            return Response.status(404).build();
        }
    }

}
