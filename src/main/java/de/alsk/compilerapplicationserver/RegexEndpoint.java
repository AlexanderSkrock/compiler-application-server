package de.alsk.compilerapplicationserver;

import de.alsk.compiler.regex.Regex;
import de.alsk.compiler.regex.RegexMatcher;
import de.alsk.compiler.regex.Regexes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("regex")
public class RegexEndpoint {
    @PostMapping("test")
    public boolean testRegex(@RequestBody RegexParameters params) throws Exception {
        Regex compiledRegex = Regexes.parse(params.regex);
        return new RegexMatcher(compiledRegex).matches(params.stringToValidate);
    }

    static class RegexParameters {
        String regex;
        String stringToValidate;

        RegexParameters(String regex, String stringToValidate) {
            this.regex = regex;
            this.stringToValidate = stringToValidate;
        }
    }
}
