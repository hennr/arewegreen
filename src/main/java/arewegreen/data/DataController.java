package arewegreen.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class DataController {

    @Autowired
    DataService dataService;

    @RequestMapping("/data")
    public Object runShellScript(@RequestParam(value = "source", required = false) String source) throws IOException, InterruptedException {

        if (source == null || source.isEmpty()) {
            return printUsage();
        }

        return dataService.runCommand(source);
    }

    private ValueDto printUsage() {
        return new ValueDto("Please add a script name to be executed with ?source=<foo.sh>");
    }
}
