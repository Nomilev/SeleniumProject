package pages;

import org.openqa.selenium.WebDriver;

public class Helper {

    public WebDriver driver;

    public Helper(WebDriver driver) {
        this.driver = driver;
    }

    public void navigate(){
        driver.navigate().back();
    }
}
