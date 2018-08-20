package arewegreen.data;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

import arewegreen.config.AreWeGreenProperties;
import arewegreen.config.DefaultFilesManager;

@Service
class DataService {

    private DefaultFilesManager defaultFilesManager;
    private AreWeGreenProperties properties;

    public DataService(DefaultFilesManager defaultFilesManager, AreWeGreenProperties properties) {
        this.defaultFilesManager = defaultFilesManager;
        this.properties = properties;
    }

    ValueDto runCommand(String scriptName) throws IOException, InterruptedException {
        Process process = new ProcessBuilder().command("/bin/sh", defaultFilesManager.getDataDirectoryLocation() + "/" + scriptName).start();
        if (!process.waitFor(properties.getScriptTimeoutInSeconds(), SECONDS)) {
            process.destroyForcibly();
            return new ValueDto("?");
        }
        BufferedReader programOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        ValueDto dto = new ValueDto(programOutput.readLine());
        process.destroy();
        return dto;
    }
}
