package ge.edu.freeuni.sdp.iot.switches.heating;

import ge.edu.freeuni.sdp.iot.switches.heating.core.HouseRegistry;
import ge.edu.freeuni.sdp.iot.switches.heating.core.HouseRegistryFactory;
import ge.edu.freeuni.sdp.iot.switches.heating.model.Switch;
import ge.edu.freeuni.sdp.iot.switches.heating.model.SwitchOnRequest;
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

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by nika on 7/9/16.
 */
public class FloorServiceTest extends JerseyTest {

    @Mock private HouseRegistry registry;

    private String houseId;
    private String switchId;

    @Override
    protected Application configure() {
        return new ResourceConfig(FloorService.class);
    }

    @Before
    public void setUpChild() throws Exception {
        houseId = "3c5afb74-2e82-4f10-9931-89187fe47adf";
        switchId = "1";

        MockitoAnnotations.initMocks(this);
        HouseRegistryFactory.setTestEntry(registry);
        HouseRegistryFactory.setTestMode(true);
    }

    @After
    public void tearDownChild() throws Exception {
        HouseRegistryFactory.setTestMode(false);
    }

    private WebTarget getTarget(String houseId, String switchId) {
        return target("/house/" + houseId + "/floor/" + switchId);
    }

    @Test
    public void get_available() throws Exception {
        Switch aswitch = new Switch(switchId, true);
        aswitch.setAvailable(true);
        when(registry.getSwitch(houseId, switchId)).thenReturn(aswitch);

        Response resp = getTarget(houseId, switchId).request().get();
        assertEquals(200, resp.getStatus());

        String body = resp.readEntity(String.class);
        Switch respSwitch = Switch.fromJson(new JSONObject(body));

        verify(registry).getSwitch(houseId, switchId);
        assertEquals(aswitch, respSwitch);
    }

    @Test
    public void get_notAvailable() throws Exception {
        when(registry.getSwitch(houseId, switchId)).thenReturn(null);

        Response resp = getTarget(houseId, switchId).request().get();
        assertEquals(404, resp.getStatus());
    }

    @Test
    public void put_successful() throws Exception {
        SwitchOnRequest onRequest = new SwitchOnRequest(10);
        when(registry.switchOn(houseId, switchId, onRequest)).thenReturn(true);

        Response resp = getTarget(houseId, switchId).request()
                .put(Entity.entity(onRequest, MediaType.APPLICATION_JSON));

        verify(registry, times(1)).switchOn(houseId, switchId, onRequest);
        assertEquals(resp.getStatus(), 200);
    }

    @Test
    public void put_Unsuccessful() throws Exception {
        SwitchOnRequest onRequest = new SwitchOnRequest(10);
        when(registry.switchOn(houseId, switchId, onRequest)).thenReturn(false);

        Response resp = getTarget(houseId, switchId).request()
                .put(Entity.entity(onRequest, MediaType.APPLICATION_JSON));

        verify(registry).switchOn(houseId, switchId, onRequest);
        assertEquals(404, resp.getStatus());
    }

    @Test
    public void delete_successful() throws Exception {
        when(registry.switchOff(houseId, switchId)).thenReturn(true);

        Response resp = getTarget(houseId, switchId).request().delete();

        verify(registry).switchOff(houseId, switchId);
        assertEquals(200, resp.getStatus());
    }

    @Test
    public void delete_Unsuccessful() throws Exception {
        when(registry.switchOff(houseId, switchId)).thenReturn(false);

        Response resp = getTarget(houseId, switchId).request().delete();

        verify(registry).switchOff(houseId, switchId);
        assertEquals(404, resp.getStatus());
    }

}