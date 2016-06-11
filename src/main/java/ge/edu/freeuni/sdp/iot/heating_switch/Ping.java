package ge.edu.freeuni.sdp.iot.heating_switch;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by nika on 6/10/16.
 */
@Path("/ping")
public class Ping {

    @GET
    public Response get() {
        return Response.ok().build();
    }

}
