package com.automation.pages;


import com.automation.testBase.TestBase;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class LoginPage  extends TestBase {

    public LoginPage() throws IOException {
        PageFactory.initElements(driver,this);

    }
    @FindBy(xpath = "//a[@title='Log in to your customer account']")
    WebElement signButton;
    @FindBy(id="email")
    WebElement emailBox;

    @FindBy(id="passwd")
    WebElement passwrord;

    @FindBy(name = "SubmitLogin")
    WebElement loginButton;
    @FindBy(xpath = "//h1[normalize-space()='Authentication']")
    WebElement authProv;

public HomePage performLogin() throws IOException {
    signButton.click();
    emailBox.sendKeys(prop.getProperty("username"));
    passwrord.sendKeys(prop.getProperty("password"));
    loginButton.click();
return new HomePage();
}

public LoginPage loginWithinvalidData(String theUserName, String ThePassord) throws IOException {
    signButton.click();
    emailBox.sendKeys(theUserName);
    passwrord.sendKeys(ThePassord);
    loginButton.click();

    return new LoginPage();
}

public String  getUrl(){
 String URL=   driver.getCurrentUrl();
    return URL;
}

public WebElement getLoginButton(){

    return authProv;
}


}
