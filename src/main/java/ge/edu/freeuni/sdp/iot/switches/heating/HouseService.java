package ge.edu.freeuni.sdp.iot.switches.heating;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by nika on 6/23/16.
 */
@Path("/house/{house_id}")
public class HouseService {

    @GET
    public Response get(@PathParam("house_id") String houseId) {
        return Response.ok().entity("        " +
                "{\n" +
                "            \"num-floors\": 3,\n" +
                "            \"floors\": [\n" +
                "                {\n" +
                "                    \"id\": " + houseId + ",\n" +
                "                    \"status\": \"on\",\n" +
                "                    \"available\": \"yes\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 1,\n" +
                "                    \"status\": \"off\",\n" +
                "                    \"available\": \"yes\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 2,\n" +
                "                    \"status\": \"off\",\n" +
                "                    \"available\": \"no\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }").build();
    }

}
