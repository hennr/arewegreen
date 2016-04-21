package arewegreen.data;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class ShellScriptResource {

    @RequestMapping("/shell")
    public Object runShellScript(@RequestParam(value = "scriptName", required = false) String scriptName) {

        if (scriptName == null || scriptName.isEmpty()) {
            return printUsage();
        }

        return new ValueDtoInteger(1);
    }

    private ValueDtoString printUsage() {
        return new ValueDtoString("Please add a script name to be executed with ?scriptName=<foo.sh>");
    }
}
