package com.epam.mail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class LoginPage extends LoginArrow {

    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    public void openGmailURL(){
        super.openGmailURL();
    }

    public void inputGmailUserAndPassword(){
        super.inputGmailUserAndPassword();
    }
    public boolean isLoggedIn(){
        return super.isLoggedIn();
    }
}
