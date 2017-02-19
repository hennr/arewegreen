package arewegreen.layout;

import arewegreen.config.ConfigManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class LayoutLocationController {

    private ConfigManager config;

    @Autowired
    public LayoutLocationController(ConfigManager config) {
        this.config = config;
    }

    @GetMapping("/layout")
    Object exposeLayoutJsonLocationForDashbot() throws IOException {
        return Files.readAllBytes(Paths.get(config.getLayoutJsonLocation()));
    }
}
