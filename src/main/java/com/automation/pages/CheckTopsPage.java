package com.automation.pages;

import com.automation.testBase.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;


public class CheckTopsPage extends TestBase {

    public CheckTopsPage() throws IOException {

        PageFactory.initElements(driver,this);
    }


    @FindBy(xpath = "//p[@class='subcategory-heading']")
    WebElement proveTops;
public boolean isTopsDisplayed(){
 boolean prove=   proveTops.isDisplayed();
 return prove;
}

}
