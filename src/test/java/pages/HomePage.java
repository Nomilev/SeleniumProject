package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage {

    public WebDriver driver;

    //constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //locators
    By homePage= By.cssSelector("[title=\"HOME\"]");
    By leftLink= By.linkText("Bedroom");
    By categoryLink= By.linkText("Dining Room");
    By bannerLink= By.cssSelector("[title=\"BABY\"]");
    By language=By.cssSelector("img[alt=\"IL\"]");
    By location= By.id("mui-component-select-country-selector-select");
    By israelLocation= By.cssSelector("li[data-ga-v2=\"Israel (₪)\"]");
    By hebrewBtn= By.cssSelector("button[data-ga-v3=\"עברית \"]");
    By shopNowBtn= By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[10]/div/div[3]/div/div[5]/button");
    By search= By.id("header-big-screen-search-box");
    By searchBtn= By.cssSelector("img[alt=\"סמל חיפוש\"]");

    //methods
    //enter to home page
    public void goToHomePage(){
       WebElement home= driver.findElement(homePage);
       Actions act= new Actions(driver);
       act.doubleClick(home).build().perform();

    }
    //enter to bedroom page from link in left
    public void clickOnLeftBedroomLink() throws InterruptedException {
        driver.findElement(leftLink).click();
    }

    //enter to dining page from link in center category
    public void clickOnCenterCategoryLink(){
        driver.findElement(categoryLink).click();
    }
    //enter to baby page from banner link
    public void ClickOnBannerLink(){
        WebElement banner= driver.findElement(bannerLink);
        Actions act= new Actions(driver);
        act.doubleClick(banner).build().perform();
    }
    //Change the language from English to Hebrew
    public void changeLanguage() throws InterruptedException {
        driver.findElement(language).click();
        driver.findElement(location).click();
        driver.findElement(israelLocation).click();
        Thread.sleep(Constants.SLEEP_2);
        driver.findElement(hebrewBtn).click();
        driver.findElement(shopNowBtn).click();
    }
    //search product
    public void searchProduct(){
        driver.findElement(search).submit();
        driver.findElement(search).sendKeys("בגדי תינוקות");
        driver.findElement(searchBtn).click();
    }

}
