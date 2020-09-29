package com.epam.testrunners;

import com.epam.driver.DriverSingleton;
import com.epam.service.TestDataReader;
import cucumber.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.util.Random;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/com/epam/features"},
        glue = {"com/epam/steps"},
        plugin = {"pretty","html:target/TestReports/html/report.html","json:target/TestReports/json/report.json","junit:target/TestReports/junit/report.xml"},
        monochrome = true)
public class GmailRegressionTestRunner extends GmailTestRunner {

    @BeforeSuite
    @Parameters({"env", "browser"})
    public void setUp(String env, String browser) throws IOException {
        TestDataReader.setEnvoronment(env);
        DriverSingleton driverSingleton = DriverSingleton.getInstance();
        driverSingleton.setBrowser(browser);
        driver = driverSingleton.getDriver();
        Random r = new Random();
        randomInt = r.nextInt();
    }

    @AfterSuite
    public void closeDriver(){
        DriverSingleton.closeDriver();
    }
}
