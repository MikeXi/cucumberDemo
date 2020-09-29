package com.epam.steps;

import com.epam.mail.MailBoxPage;
import com.epam.mail.MessageDialogPage;
import com.epam.testrunners.GmailTestRunner;
import com.epam.util.TestListener;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public class MailBoxSteps extends GmailTestRunner {
    MailBoxPage mailBoxPage;
    MessageDialogPage messageDialogPage;
    int draftCountBefore;
    public MailBoxSteps(){
        mailBoxPage = new MailBoxPage(driver);
    }
    @Given("^I am in mailbox page$")
    public void i_am_in_mailbox_page() {
        draftCountBefore = mailBoxPage.getDraftMailCount();
    }

    @When("^I click Add Email button$")
    public void i_click_add_email_button() {
        mailBoxPage.clickAddEmailButton();
    }

    @Then("^I can see the new Email popup dialog$")
    public void i_can_see_the_new_email_popup_dialog() {
        messageDialogPage = new MessageDialogPage(driver);
    }

    @When("^I input email ([^\"]+) and ([^\"]+) and ([^\"]+) in the new Email popup dialog$")
    public void i_input_email_info_in_the_new_email_popup_dialog(String to, String subject, String body) {
        subject +=randomInt;
        messageDialogPage.setMailContents(to, subject, body);
    }

    @And("^I close the new Eail popup dialog$")
    public void i_close_the_new_email_popup_dialog() {
        messageDialogPage.closeMessageDialog();
        mailBoxPage.sleepSeconds(2);
    }

    @Then("^I can see the Draft email is created$")
    public void i_can_see_the_draft_email_is_added() {
        int draftCountAfter = mailBoxPage.getDraftMailCount();
        Assert.assertTrue(draftCountAfter == draftCountBefore + 1);
    }

    @Given("^I open the Draft Email with ([^\"]+)$")
    public void i_open_the_draft_email_with_subject(String subject) {
        subject +=randomInt;
        mailBoxPage.openDraftEmail(subject);
        messageDialogPage = new MessageDialogPage(driver);
    }

    @When("^I click the Send button$")
    public void i_click_the_send_button() {
        boolean sent = messageDialogPage.sendEmail();
        Assert.assertTrue(sent);
    }

    @Then("^I can see the Email with ([^\"]+) in the Sent tab$")
    public void i_can_see_the_email_in_the_sent_tab(String subject) {
        subject +=randomInt;
        WebElement sent = mailBoxPage.getSentEmail(subject);
        Assert.assertNotNull(sent);
    }

    @Then("^I can see the Email ([^\"]+) and ([^\"]+) and ([^\"]+) are correct$")
    public void i_can_see_the_email_information_is_correct(String to, String subject, String body) {
        subject +=randomInt;
        String toStr = messageDialogPage.getMailContent(subject,"TO");
        Assert.assertEquals(to,toStr);
        String subjectStr = messageDialogPage.getMailContent(subject,"SUBJECT");
        Assert.assertEquals(subject,subjectStr);
        String bodyStr = messageDialogPage.getMailContent(subject,"BODY");
        Assert.assertEquals(body, bodyStr);
        messageDialogPage.closeMessageDialog();
    }

    @Given("^I am in the Sent tab$")
    public void i_am_in_the_sent_tab() {
        mailBoxPage.clickSentTab();
    }

    @When("^I input Email ([^\"]+) in Search Text box and Press ENTER$")
    public void i_input_email_subject_in_search_text_box_and_press_enter(String subject) {
        subject +=randomInt;
        mailBoxPage.searchEmail(subject);
    }

    @Then("^I can see the search result Email with ([^\"]+) in the Sent tab$")
    public void i_can_see_the_search_result_email_with_subject_in_the_sent_tab(String subject) {
        subject +=randomInt;
        WebElement searchResult = mailBoxPage.getSearchResult(subject);
        Assert.assertNotNull(searchResult);
    }

    @When("^I drag the email with ([^\"]+) and drop on Starred tab$")
    public void i_drag_the_sent_email_and_drop_on_starred_tab(String subject) {
        subject +=randomInt;
        mailBoxPage.dragSentMailToStarred(subject);
    }

    @Then("^I can see the sent Email with ([^\"]+) in Starred tab$")
    public void i_can_see_the_sent_email_in_starred_tab(String subject) {
        subject +=randomInt;
        WebElement starredEmail = mailBoxPage.getStarredEmail(subject);
        Assert.assertNotNull(starredEmail);
    }

    @Given("^I am in the Starred tab$")
    public void i_am_in_the_starred_tab() {
        mailBoxPage.clickStarredTab();
    }

    @When("^I delete email with ([^\"]+) in Context menu$")
    public void i_delete_email_in_context_menu(String subject) {
        subject +=randomInt;
        mailBoxPage.deleteEmail(subject);
    }

    @Then("^I can see the Email with ([^\"]+) disappears in Starred tab$")
    public void i_can_see_the_email_disappears_in_starred_tab(String subject) {
        subject +=randomInt;
        WebElement starredEmail = mailBoxPage.getStarredEmail(subject);
        Assert.assertNull(starredEmail);
    }
}
