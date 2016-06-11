package services;

import ge.edu.freeuni.sdp.iot.heating_switch.Ping;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

/**
 * Created by nika on 6/11/16.
 */
public class PingTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(Ping.class);
    }

    @Test
    public void testGet() throws Exception {

        Response resp = target("ping").request().get();
        assertEquals(200, resp.getStatus());

    }

}