package org.shiksha.webapp;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

public class WebApiRESTServiceTest extends JerseyTest
{
    @Override
    public Application configure()
    {

        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(UserService.class);
    }

    @Test
    public void testGetAll()
    {
        Response output = target("/users").request().get();
        assertEquals("Should return status 200", 200, output.getStatus());
        assertNotNull("Should return a json object", output.getEntity());
    }
}
