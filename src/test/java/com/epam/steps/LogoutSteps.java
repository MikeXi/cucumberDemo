package com.epam.steps;

import com.epam.mail.LogoutPage;
import com.epam.testrunners.GmailTestRunner;
import com.epam.util.TestListener;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public class LogoutSteps extends GmailTestRunner {
    LogoutPage logoutPage;
    boolean isLoggedOut;
    @Given("^I am logged in Gmail$")
    public void i_am_logged_in_gmail() {
        logoutPage = new LogoutPage(driver);
    }

    @When("^I click Logout button$")
    public void i_click_logout_button() {
        isLoggedOut = logoutPage.logOut();
    }

    @Then("^I am logged out$")
    public void i_am_logged_out() {
        Assert.assertTrue(isLoggedOut);
    }
}
