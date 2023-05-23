package pages;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {

    public WebDriver driver;
     //constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    //locators
    By product=  By.xpath("//*[@id=\"platform_modernisation_product_summary_M54212\"]/div/div[1]/div[1]/div/div/div[1]/a/img");
    By color= By.linkText("קשת");
    //By color2= By.cssSelector("select#Colour-817076");
    By openColorCombobox=By.id("dk_container_Colour-817076");
    By size= By.linkText("3-6 Months (UK ) (EU 62-68cm) - 116 ₪");
    //By size2= By.id("Size-M54-212");
    By openSizeCombobox=By.id("dk_container_Size-C78-381");
    By addCartBtn= By.linkText("הוסף לסל");
    //By shoppingCart= By.cssSelector("a[title=\"שקית קניות\"]");

    By viewCart=By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[7]/div[2]/div/div/div[2]/div/div/div[3]/div[1]");
    By amount= By.id("dk_container_Qty_1");
    By checkOutBtn=By.xpath("//*[@id=\"pageWidth\"]/div[5]/div[3]/a[3]");

    
    //methods
    //select a product
    public void selectProduct(){
        driver.findElement(product).click();
    }
    //Select a product color
    public void selectColorProduct() throws InterruptedException {
        Thread.sleep(Constants.SLEEP_3);
        driver.findElement(openColorCombobox).click();
        //Select selectByVisible= new Select(driver.findElement(color2));
        //selectByVisible.selectByVisibleText("קשת");
        driver.findElement(color).click();
    }
    //Select a product size
    public void selectSizeProduct() throws InterruptedException {
        Thread.sleep(Constants.SLEEP_2);
        driver.findElement(openSizeCombobox).click();
        Thread.sleep(Constants.SLEEP_1);
        //Select selectByVisible= new Select(driver.findElement(size));
        //selectByVisible.selectByIndex(2);
        driver.findElement(size).click();
    }
    //Add the product to the shopping cart
    public void clickAddToCartBtn(){
         driver.findElement(addCartBtn).click();
    }

    //Display a shopping cart
    public void clickOnViewShoppingCart() throws InterruptedException {
        driver.findElement(viewCart).click();
    }
    //Check the amount of products in the cart
    public String amountProduct(){
       String quantity= driver.findElement(amount).getText();
       return quantity;
    }

    // click on check out button
    public void clickOnCheckOutBtn() throws InterruptedException {
        driver.findElement(checkOutBtn);
    }

}
