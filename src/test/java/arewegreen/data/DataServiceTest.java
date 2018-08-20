package arewegreen.data;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import arewegreen.config.AreWeGreenProperties;
import arewegreen.config.DefaultFilesManager;

public class DataServiceTest {
    private DefaultFilesManager defaultFilesManager = mock(DefaultFilesManager.class);
    private AreWeGreenProperties properties = new AreWeGreenProperties();
    private DataService dataService = new DataService(defaultFilesManager, properties);


    @Test
    public void stopsTheScriptAfterTwoSecondsAndReturnsUnknownResult() throws IOException, InterruptedException {
        // given
        String testDataDir = new File("src/test/resources/data").getAbsolutePath();
        when(defaultFilesManager.getDataDirectoryLocation()).thenReturn(testDataDir);

        // when
        ValueDto result = dataService.runCommand("timeout.sh");

        // then
        assertThat(result).isEqualTo(new ValueDto("?"));
    }

    @Test
    public void startsTheScriptInTheTimeSet() throws IOException, InterruptedException {
        //given
        String testDataDir = new File("src/test/resources/data").getAbsolutePath();
        when(defaultFilesManager.getDataDirectoryLocation()).thenReturn(testDataDir);

        //when
        ValueDto result = dataService.runCommand("justInTime.sh");

        //then
        assertThat(result).isEqualTo(new ValueDto("success"));
    }

}
