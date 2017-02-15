package arewegreen.layout;

import arewegreen.AbstractRestTestClass;
import arewegreen.config.ConfigManager;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LayoutLocationControllerTest extends AbstractRestTestClass {

    @Test
    public void servesTheDefaultLayoutJson() throws IOException {
        // given
        final String layoutJsonLocation = "/tmp/arewegreen/layout.json";
        String expected = new String(Files.readAllBytes(Paths.get(layoutJsonLocation)));

        ConfigManager configManager = mock(ConfigManager.class);
        when(configManager.getLayoutJsonLocation()).thenReturn(layoutJsonLocation);

        LayoutLocationController controller = new LayoutLocationController(configManager);

        // when
        Object result = controller.exposeLayoutJsonLocationForDashbot();

        // then
        assertEquals(result, expected);
    }

}
