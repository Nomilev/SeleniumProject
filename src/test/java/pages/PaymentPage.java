package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage {
    public WebDriver driver;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
    }

    //Locator
    By credit= By.cssSelector(".paymentoption paymentoptions-card");
    By newCredit= By.id("card_option");
    By cardNumber= By.id("cardNumber");
    By expiryMonth= By.id("expiryMonth");
    By expiryYear= By.id("expiryYear");
    By securityCode= By.id("securityCode");
    By errorHint= By.id("cardNumber-hint");
    By payNow= By.id("submitButton");
    By makingPayment= By.linkText("ביצוע תשלום");

    //methods

    //select the credit card payment method
    public void SelectCreditCardPaymentMethod(){
        driver.findElement(credit).click();
    }
    //click on new credit
    public void clickOnNewCredit(){
        driver.findElement(newCredit).click();
    }
    //enter the card number
    public void EnterCardNumber(){
        driver.findElement(cardNumber).submit();
        driver.findElement(cardNumber).sendKeys("1234566789877665544");
    }
    //enter the expiry month
    public void EnterExpiryMonth(){
        driver.findElement(expiryMonth).click();
        driver.findElement(expiryMonth).sendKeys("01");
    }
    //enter the expiry year
    public void EnterExpiryYear(){
        driver.findElement(expiryYear).click();
        driver.findElement(expiryYear).sendKeys("27");
    }
    //enter security mode
    public void EnterSecurityCode(){
        driver.findElement(securityCode).sendKeys("123");
    }
    // check the error message of card number
    public Boolean CheckReceivedErrorHint(){
      boolean error= driver.findElement(errorHint).isDisplayed();
        if (error){
            return error;
        }else {
           return false;
        }
    }
    public void clickOnPayNow(){
        driver.findElement(payNow).click();
    }
}
