package arewegreen.data;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import arewegreen.config.DefaultFilesManager;

public class DataServiceTest {

    @Test
    public void stopsTheScriptAfterTwoSecondsAndReturnsUnknownResult() throws IOException, InterruptedException {
        // given
        String testDataDir = new File("src/test/resources/data").getAbsolutePath();

        DefaultFilesManager defaultFilesManager = mock(DefaultFilesManager.class);
        when(defaultFilesManager.getDataDirectoryLocation()).thenReturn(testDataDir);

        DataService dataService = new DataService(defaultFilesManager);

        // when
        ValueDto result = dataService.runCommand("timeout.sh");

        // then
        assertThat(result).isEqualTo(new ValueDto("?"));
    }

}