package de.alsk.compilerapplicationserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestEndpoint {
    @GetMapping("/test")
    public String testEndpoint() {
        return "Working!";
    }
}
