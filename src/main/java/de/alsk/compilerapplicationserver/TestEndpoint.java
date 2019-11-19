package de.alsk.compilerapplicationserver;

import de.alsk.compiler.Scanner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TestEndpoint {
    private final RegexEndpoint regexEndpoint;
    private final ScannerEndpoint scannerEndpoint;

    public TestEndpoint(RegexEndpoint regexEndpoint, ScannerEndpoint scannerEndpoint) {
        this.regexEndpoint = regexEndpoint;
        this.scannerEndpoint = scannerEndpoint;
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "Working!";
    }

    @GetMapping("test/regex")
    public boolean testRegex(@RequestParam(name = "string", defaultValue = "123Hallo456") String string) throws Exception {
        String regexString = "0|([1-9][0-9]*)|[a-zA-Z]+";
        return regexEndpoint.testRegex(regexString, string);
    }

    @GetMapping("test/scan")
    public List<Scanner.Token> testScan(@RequestParam(name = "string", defaultValue = "123Hallo456") String string) {
        Map<String, String> tokenDefinitions = Map.of(
                "0|([1-9][0-9]*)", "Integer",
                "[a-zA-Z]+", "Word"
        );
        return scannerEndpoint.scan(new ScannerEndpoint.ScanParameters(tokenDefinitions, string));
    }
}
