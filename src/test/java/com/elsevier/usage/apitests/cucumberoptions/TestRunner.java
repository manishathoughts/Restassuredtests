package com.elsevier.usage.apitests.cucumberoptions;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/elsevier/usage/apitests/features", tags = "@integration-tests",
        glue = "com.elsevier.usage.apitests.stepdefinitions",
        plugin = {"pretty", "html:target/cucumber-html-reports", "json:target/cucumber.json", "junit:target/cukes.xml"})
public class TestRunner {

}
