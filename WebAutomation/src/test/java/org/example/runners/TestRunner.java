package org.example.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/org/example/features",
        glue = "org.example.stepdefinition",
        plugin = {"pretty","html:target/cucumber-reports.html","json:target/cucumber-reports.json"}
)
public class TestRunner {
}
