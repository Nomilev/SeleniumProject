package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static testCases.TestCasesNextSite.getData;

public class LoginPage {

    public WebDriver driver;
    //locators
    By myAccount= By.linkText("My Account");
    By emailAddress= By.id("EmailOrAccountNumber");
    By yourPassword= By.id("Password");
    By signInBtn= By.id("SignInNow");
    //constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //method to click on My Account
    public void clickMyAccount(){
      driver.findElement(myAccount).click();
    }
    //method to enter email address
    public void enterEmailAddress(String email) {
        driver.findElement(emailAddress).sendKeys(email);
    }
    //method to enter password
    public void enterPassword(String password){
        driver.findElement(yourPassword).sendKeys(password);
    }
    //method to click on sign in
    public void clickOnSignIn(){
        driver.findElement(signInBtn).click();
    }

}
