package ge.edu.freeuni.sdp.iot.switches.heating;

import ge.edu.freeuni.sdp.iot.switches.heating.model.House;
import ge.edu.freeuni.sdp.iot.switches.heating.model.Houses;
import ge.edu.freeuni.sdp.iot.switches.heating.model.Switch;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * Created by nika on 6/23/16.
 */
@Path("/house/{house_id}")
public class HouseService {

    @GET
    @Produces( { MediaType.APPLICATION_JSON})
    public House get(@PathParam("house_id") String houseId) {
//        Houses houses = Houses.getInstance();
//        Integer id = Integer.valueOf(houseId);
        return new House(1);
    }

}
