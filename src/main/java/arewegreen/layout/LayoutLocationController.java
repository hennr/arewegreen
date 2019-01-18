package arewegreen.layout;

import arewegreen.config.DefaultFilesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;

@RestController
public class LayoutLocationController {

    private DefaultFilesManager config;

    @Autowired
    public LayoutLocationController(DefaultFilesManager config) {
        this.config = config;
    }

    @GetMapping("/layout.json")
    Mono<String> exposeLayoutJsonLocationForDashbot() throws IOException {
        return Mono.just(new String(Files.readAllBytes(config.getLayoutJsonLocation())));
    }
}
