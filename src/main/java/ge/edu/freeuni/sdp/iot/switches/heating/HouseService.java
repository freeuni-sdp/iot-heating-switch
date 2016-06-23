package ge.edu.freeuni.sdp.iot.switches.heating;

import ge.edu.freeuni.sdp.iot.switches.heating.model.House;
import ge.edu.freeuni.sdp.iot.switches.heating.model.Houses;
import ge.edu.freeuni.sdp.iot.switches.heating.model.Switch;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * Created by nika on 6/23/16.
 */
@Path("/house/{house_id}")
public class HouseService {

    @GET
    public Response get(@PathParam("house_id") String houseId) {
        Houses houses = Houses.getInstance();

        try {
            Integer id = Integer.valueOf(houseId);
            House h = houses.get(id);
            StringBuilder builder = new StringBuilder();
            builder.append("{");

            Collection<Switch> values = h.getValues();

            builder.append("\"num-floors: \"").append(values.size()).append(", ");

            boolean first = true;
            builder.append("\"floors\": [");
            for (Switch s : values) {

                if (first)
                    first = false;
                else
                    builder.append(", ");

                builder.append("{");

                builder.append("\"id\": ").append(s.getFloorId()).append(", ");

                Boolean status = s.getStatus();

                builder.append("\"status\": ");
                if (status == null || !status)
                    builder.append("\"off\"");
                else
                    builder.append("\"on\"");
                builder.append(", ");

                builder.append("\"available\": ");
                if (status != null)
                    builder.append("\"yes\"");
                else
                   builder.append("\"no\"");
                builder.append(", ");

                builder.append("}");

            }
            builder.append("]");

            builder.append("}");

            return Response.ok().entity(builder.toString()).build();
        } catch (NumberFormatException|NullPointerException e) {
            return Response.status(404).build();
        }
    }

}
