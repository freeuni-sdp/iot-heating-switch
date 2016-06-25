package ge.edu.freeuni.sdp.iot.switches.heating;

import ge.edu.freeuni.sdp.iot.switches.heating.core.HouseOnlineRegistry;
import ge.edu.freeuni.sdp.iot.switches.heating.core.HouseRegistry;
import ge.edu.freeuni.sdp.iot.switches.heating.core.HouseRegistryFactory;
import ge.edu.freeuni.sdp.iot.switches.heating.model.House;
import ge.edu.freeuni.sdp.iot.switches.heating.model.Switch;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by nika on 6/23/16.
 */
@Path("/house/{house_id}")
public class HouseService {

    @GET
    @Produces( { MediaType.APPLICATION_JSON})
    public House get(@PathParam("house_id") String houseId) {
        HouseRegistry reg = HouseRegistryFactory.getHouseRegistry();
        House res = reg.getHouse(houseId);

        if (res == null)
            throw new NotFoundException();

        return res;
    }

    private static House getDummyHouse() {
        House res = new House("3c5afb74-2e82-4f10-9931-89187fe47adf");
        res.add(new Switch(1, true));
        res.add(new Switch(2, false));
        Switch unavailable = new Switch(3, false);
        unavailable.setAvailable(false);
        res.add(unavailable);
        return res;
    }

}
