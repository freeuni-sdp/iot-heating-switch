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
        House res = new House(1);
        res.add(new Switch(1, true));
        res.add(new Switch(2, false));
        Switch unav1 = new Switch(3, false);
        unav1.setAvailable(false);
        res.add(unav1);
        Switch unav2 = new Switch(3, null);
        unav2.setAvailable(false);
        res.add(unav2);
        return res;
    }

}
