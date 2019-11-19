package de.alsk.compilerapplicationserver;

import de.alsk.compiler.regex.Regex;
import de.alsk.compiler.regex.RegexMatcher;
import de.alsk.compiler.regex.Regexes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("regex")
public class RegexEndpoint {
    @GetMapping("test")
    public boolean testRegex(@RequestParam("regex") String regexString, @RequestParam("string") String input) throws Exception {
        Regex compiledRegex = Regexes.parse(regexString);
        return new RegexMatcher(compiledRegex).matches(input);
    }
}
