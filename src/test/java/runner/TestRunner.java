package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions (
        features = "src/test/java/features",
        glue = {"utility", "stepdefinitions"},
        plugin = {"pretty", "html:target/cucumber-html-report.html", "json:cucumber.json" },
        //remember to insert "," on the end of line above if you want to use tags.

//        // users tests
//        tags = "@users"
//        tags = "@users and @negative"
//        tags = "@users and not @negative"
//
//        // notes tests
//        tags = "@notes"
        tags = "@notes and @negative"
//        tags = "@notes and not @negative"
//
//        // health tests
//        tags = "@health"
//        tags = "@health and @negative"
//        tags = "@health and not @negative"
//
//        // all tests
//        tags = "not @negative"
//        tags = "@negative"

        )

public class TestRunner {
}
