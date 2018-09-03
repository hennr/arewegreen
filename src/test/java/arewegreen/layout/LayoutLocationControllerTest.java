package arewegreen.layout;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import arewegreen.AbstractTestClass;
import arewegreen.config.DefaultFilesManager;

public class LayoutLocationControllerTest extends AbstractTestClass {

    @Test
    public void servesTheDefaultLayoutJson() throws IOException {
        // given
        final String layoutJsonLocation = System.getProperty("java.io.tmpdir") + "/arewegreen/layout.json";
        String expected = new String(Files.readAllBytes(Paths.get(layoutJsonLocation)));

        DefaultFilesManager defaultFilesManager = mock(DefaultFilesManager.class);
        when(defaultFilesManager.getLayoutJsonLocation()).thenReturn(Paths.get(layoutJsonLocation));

        LayoutLocationController controller = new LayoutLocationController(defaultFilesManager);

        // when
        String result = controller.exposeLayoutJsonLocationForDashbot();

        // then
        assertEquals(expected, result);
    }

}
