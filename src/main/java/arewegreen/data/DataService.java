package arewegreen.data;

import arewegreen.config.DefaultFilesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
class DataService {

    @Autowired
    private DefaultFilesManager defaultFilesManager;

    Object runCommand(String source) throws IOException, InterruptedException {
        Process process = new ProcessBuilder().command("/bin/sh", defaultFilesManager.getDataDirectoryLocation() + "/" + source).start();
        BufferedReader programOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        process.waitFor();
        ValueDto dto = new ValueDto(programOutput.readLine());
        process.destroy();
        return dto;
    }
}
