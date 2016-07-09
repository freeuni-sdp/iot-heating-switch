package ge.edu.freeuni.sdp.iot.switches.heating;

import ge.edu.freeuni.sdp.iot.switches.heating.core.HouseRegistry;
import ge.edu.freeuni.sdp.iot.switches.heating.core.HouseRegistryFactory;
import ge.edu.freeuni.sdp.iot.switches.heating.model.Switch;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static org.mockito.Mockito.when;

/**
 * Created by nika on 7/9/16.
 */
public class FloorServiceTest extends JerseyTest {

    @Mock private HouseRegistry registry;

    private String houseId;

    @Override
    protected Application configure() {
        return new ResourceConfig(FloorService.class);
    }

    @Before
    public void setUpChild() throws Exception {
        houseId = "3c5afb74-2e82-4f10-9931-89187fe47adf";
        MockitoAnnotations.initMocks(this);
        HouseRegistryFactory.setTestEntry(registry);
        HouseRegistryFactory.setTestMode(true);
    }

    @After
    public void tearDownChild() throws Exception {
        HouseRegistryFactory.setTestMode(false);
    }

    private WebTarget getTarget(String switchId) {
        return target("/house/" + houseId + "/floor/" + switchId);
    }

    @Test
    public void get_available() throws Exception {
        String switchId = "1";
        Switch aswitch = new Switch(switchId, true);
        aswitch.setAvailable(true);
        when(registry.getSwitch(houseId, switchId)).thenReturn(aswitch);

        Response resp = getTarget(switchId).request().get();
        String body = resp.readEntity(String.class);
        Switch respSwitch = Switch.fromJson(new JSONObject(body));

        assertEquals(aswitch, respSwitch);
    }

    @Test
    public void get_notAvailable() throws Exception {
        String switchId = "2";
        Switch aswitch = new Switch(switchId, false);
        aswitch.setAvailable(false);
        when(registry.getSwitch(houseId, switchId)).thenReturn(null);

        Response resp = getTarget(switchId).request().get();
        String body = resp.readEntity(String.class);
        Switch respSwitch = Switch.fromJson(new JSONObject(body));

        assertEquals(aswitch, respSwitch);
    }

    @Test
    public void put() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

}