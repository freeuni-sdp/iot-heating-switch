package ge.edu.freeuni.sdp.iot.switches.heating;

import ge.edu.freeuni.sdp.iot.switches.heating.core.HouseRegistry;
import ge.edu.freeuni.sdp.iot.switches.heating.core.HouseRegistryFactory;
import ge.edu.freeuni.sdp.iot.switches.heating.model.House;
import ge.edu.freeuni.sdp.iot.switches.heating.model.Switch;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by nika on 7/9/16.
 */
public class HouseServiceTest extends JerseyTest {

    @Mock private HouseRegistry registry;
    private String houseId;
    private House house;

    @Override
    protected Application configure() {
        return new ResourceConfig(HouseService.class);
    }

    @Before
    public void setUpChild() throws Exception {
        houseId = "3c5afb74-2e82-4f10-9931-89187fe47adf";

        house = new House(houseId);
        house.add(new Switch("1", true).setAvailable(true));
        house.add(new Switch("2", false).setAvailable(true));
        house.add(new Switch("3", true).setAvailable(false));
        house.add(new Switch("4", false).setAvailable(false));

        MockitoAnnotations.initMocks(this);
        HouseRegistryFactory.setTestEntry(registry);
        HouseRegistryFactory.setTestMode(true);
    }

    @After
    public void tearDownChild() throws Exception {
        HouseRegistryFactory.setTestMode(false);
    }

    @Test
    public void get_success() throws Exception {
        when(registry.getHouse(houseId)).thenReturn(house);

        Response response = target("/house/" + houseId).request().get();

        assertEquals(200, response.getStatus());

        JSONObject respObject = new JSONObject(response.readEntity(String.class));
        House respHouse = House.fromJson(respObject);

        verify(registry).getHouse(houseId);
        assertEquals(house, respHouse);
    }

    @Test
    public void get_failure() throws Exception {
        when(registry.getHouse(houseId)).thenReturn(null);

        Response response = target("/house/" + houseId).request().get();

        verify(registry).getHouse(houseId);
        assertEquals(404, response.getStatus());
    }

}