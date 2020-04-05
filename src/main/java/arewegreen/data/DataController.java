package arewegreen.data;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
public class DataController {

    @Autowired
    DataService dataService;

    @GetMapping("/data")
    public Mono<Object> runShellScript(@RequestParam(value = "source", required = false) String source) throws IOException, InterruptedException {

        if (source == null || source.isEmpty()) {
            return Mono.just(printUsage());
        }

        return Mono.just(dataService.runCommand(source));
    }

    private ValueDto printUsage() {
        return new ValueDto("Please add a script name to be executed with ?source=<foo.sh>");
    }
}
