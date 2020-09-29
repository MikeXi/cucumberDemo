package com.epam.steps;

import com.epam.mail.LoginPage;
import com.epam.mail.iservice.LoginIService;

import com.epam.testrunners.GmailTestRunner;
import com.epam.util.TestListener;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public class LoginSteps extends GmailTestRunner {
    LoginIService loginIService;

    public LoginSteps() {
        loginIService = new LoginPage(driver);
    }

    @Given("^I open Gmail page in browser$")
    public void i_open_gmail_page_in_browser() {
        if(!isLoggedIn) {
            loginIService.openGmailURL();
        }
    }

    @When("^I input Email address and Password$")
    public void i_input_email_address_and_password() {
        if(!isLoggedIn) {
            loginIService.inputGmailUserAndPassword();
        }
    }

    @Then("^I can see Gmail is logged in with my account$")
    public void i_can_see_gmail_is_logged_in_with_my_account() {
        if(!isLoggedIn) {
            isLoggedIn = loginIService.isLoggedIn();
            Assert.assertTrue(isLoggedIn);
        }
    }
}
