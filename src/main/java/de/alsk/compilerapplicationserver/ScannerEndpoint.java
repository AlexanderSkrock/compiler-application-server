package de.alsk.compilerapplicationserver;

import de.alsk.compiler.Scanner;
import de.alsk.compiler.ScannerGenerator;
import de.alsk.compiler.SimpleScanner;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("scanner")
public class ScannerEndpoint {
    private static final Set<Character> DEFAULT_ALPHABET;
    static {
        DEFAULT_ALPHABET = new HashSet<>();
        for(char c = 0; c < Short.MAX_VALUE; c++) {
            DEFAULT_ALPHABET.add(c);
        }
    }

    @PostMapping("scan")
    public List<Scanner.Token> scan(@RequestBody ScanParameters params) {
        ScannerGenerator<String> gen = new ScannerGenerator<>();
        params.tokenDefinitions.forEach(gen::addToken);
        SimpleScanner<String> scanner = gen.generate(DEFAULT_ALPHABET);
        scanner.setStringToScan(params.inputString);
        List<Scanner.Token> tokens = new LinkedList<>();
        while(scanner.hasNext()) {
            tokens.add(scanner.next());
        }
        return tokens;
    }

    static class ScanParameters {
        Map<String, String> tokenDefinitions;
        String inputString;

        ScanParameters(Map<String, String> tokenDefinitions, String inputString) {
            this.tokenDefinitions = tokenDefinitions;
            this.inputString = inputString;
        }
    }
}
