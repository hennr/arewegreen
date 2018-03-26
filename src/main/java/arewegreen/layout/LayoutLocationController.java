package arewegreen.layout;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import arewegreen.config.DefaultFilesManager;

@RestController
public class LayoutLocationController {

    private DefaultFilesManager config;

    @Autowired
    public LayoutLocationController(DefaultFilesManager config) {
        this.config = config;
    }

    @GetMapping("/layout.json")
    String exposeLayoutJsonLocationForDashbot() throws IOException {
        return new String(Files.readAllBytes(Paths.get(config.getLayoutJsonLocation())));
    }
}
