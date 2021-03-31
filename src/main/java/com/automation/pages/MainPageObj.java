package com.automation.pages;

import com.automation.testBase.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class MainPageObj extends TestBase {

    public MainPageObj() throws IOException {
        PageFactory.initElements(driver,this);

    }

    @FindBy(xpath ="//a[@title='Log in to your customer account']")
    WebElement signButton;


    public String getTitle(){
      String title=  driver.getTitle();
      return title;
    }
}
