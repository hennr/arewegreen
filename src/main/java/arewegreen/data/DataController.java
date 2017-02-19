package arewegreen.data;

import arewegreen.config.ConfigManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
public class DataController {

    private ConfigManager configManager;

    @Autowired
    public DataController(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @RequestMapping("/data")
    public Object runShellScript(@RequestParam(value = "source", required = false) String source) throws IOException, InterruptedException {

        if (source == null || source.isEmpty()) {
            return printUsage();
        }

        Process process = new ProcessBuilder().command("/bin/sh", configManager.getDataDirectoryLocation() + "/" + source).start();
        BufferedReader programOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        process.waitFor();
        ValueDto dto = new ValueDto(programOutput.readLine());
        process.destroy();
        return dto;
    }

    private ValueDto printUsage() {
        return new ValueDto("Please add a script name to be executed with ?source=<foo.sh>");
    }
}
