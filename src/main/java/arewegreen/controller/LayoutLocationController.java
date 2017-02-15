package arewegreen.controller;

import arewegreen.config.ConfigManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class LayoutLocationController {

    @Autowired
    ConfigManager config;

    @GetMapping("/layout")
    public Object exposeLayoutJsonLocationForDashbot() throws IOException {
        return new String(Files.readAllBytes(Paths.get(config.getLayoutJsonLocation())));
    }
}
