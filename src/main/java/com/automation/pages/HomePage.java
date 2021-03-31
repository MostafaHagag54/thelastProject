package com.automation.pages;
import com.automation.testBase.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class HomePage extends TestBase {


    public HomePage() throws IOException {
        PageFactory.initElements(driver,this);

    }


    @FindBy(xpath = "//span[normalize-space()='mosta mostafa']")
    WebElement userLoggedIn;

    @FindBy(xpath ="//a[@title='Women']")
    WebElement WomenButton;


    @FindBy(xpath = "//a[@title='Tops']")
    WebElement topsButton;


    public boolean IsUserDisplayed()
    {
        boolean prove=userLoggedIn.isDisplayed();
        return prove;
    }

    public CheckTopsPage clickONTops() throws IOException {

        Actions actions= new Actions(driver);

        actions.moveToElement(WomenButton).build().perform();
        topsButton.click();

        return new CheckTopsPage();
    }
}
