package backend.jangbogoProject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
    @GetMapping("/api/hello")
    public String test() {
        return "Hello from Backend!";
    }
}
