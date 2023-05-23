package testCases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import pages.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@FixMethodOrder (MethodSorters.NAME_ASCENDING)

public class TestCasesNextSite {

    private static WebDriver driver;
    LoginPage loginPage= new LoginPage(driver);
    HomePage homePage=new HomePage(driver);
    ProductPage productPage = new ProductPage(driver);

    PaymentPage paymentPage=new PaymentPage(driver);
    Helper helper=new Helper(driver);
    // Report settings
    private static ExtentSparkReporter spark=new ExtentSparkReporter("spark.html");
    private static ExtentReports report= new ExtentReports();

    @BeforeClass
    public static void beforeClass() throws ParserConfigurationException, IOException, SAXException {
        //Setting system properties of EdgeDriver
        System.setProperty("webdriver.edge.driver",getData("DRIVER"));

        //Edge option to avoid falling on the WebSocket$Listener
        EdgeOptions option = new EdgeOptions();
        //option.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        option.addArguments("--remote-allow-origins=*");

        //Creating an object of EdgeDriver
        driver=new EdgeDriver(option);
        driver.manage().window().maximize();

        //Deleting all the cookies
        driver.manage().deleteAllCookies();

        //launching the specified URL
        driver.get(getData("URL"));

        //Specifiying pageLoadTimeout and Implicit wait
        //driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //define report
        spark.config().setReportName("My Report");
        report.attachReporter(spark);

    }
    @Test
    public void A_loginToNextSite() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        ExtentTest loginTest= report.createTest("LoginTest");
        loginTest.info(Constants.START_TEST);
        loginPage.clickMyAccount();
        loginTest.info("Entering the login page");
        //check login page title
        loginTest.info("Check entry to the login page(by title)");
        String actualLoginPageTitle= driver.getTitle();
        if(Constants.LOGIN_PAGE_TITLE.equals(actualLoginPageTitle)){
            loginTest.pass("Entered the login page (title is correct)");
        }else loginTest.fail("Did not enter the login page (title is incorrect)" , MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\screenshots\\"+ "nomi")).build());
        //enter username and password
        loginTest.info("Enter username and password");
        loginPage.enterEmailAddress(getData("EMAIL"));
        loginPage.enterPassword(getData("PASSWORD"));
        loginPage.clickOnSignIn();
        Thread.sleep(Constants.SLEEP_4);
        //check my account page title
        loginTest.info("Check entry to the my account page(by title)");
        String actualMyAccountPageTitle = driver.getTitle();
        try {
           Assert.assertEquals(Constants.MY_ACCOUNT_PAGE_TITLE,actualMyAccountPageTitle);
           loginTest.pass("Entered the my account page (title is correct)");
        }catch (AssertionError error)
        {loginTest.fail("Did not enter the my account(title is incorrect)" , MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\screenshots\\"+ "nomi")).build());}
        loginTest.info(Constants.END_TEST);

    }

    @Test
    public void B_homePage() throws IOException, InterruptedException {
        ExtentTest homeTest= report.createTest("HomeTest");
        homeTest.info(Constants.START_TEST);

        //go to home page
        homeTest.info("Entering to home page");
        homePage.goToHomePage();
        //check the home page title
        homeTest.info("Check entry to the home page(by title)");
        String actualHomePageTitle = driver.getTitle();
        if (Constants.HOME_PAGE_TITLE.equals(actualHomePageTitle))
        {
            homeTest.pass("Entered the home page (title is correct)");
        }else
        {homeTest.fail("Did not enter the home page (title is incorrect)" , MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\screenshots\\"+ "nomi")).build());}

        //click on category link in the side left
        homeTest.info("Enter to the link on the left");
        homePage.clickOnLeftBedroomLink();
        //check the bedroom page title
        homeTest.info("Check entry to the bedroom page(by title)");
        String actualBedroomPageTitle = driver.getTitle();
        if(Constants.BEDROOM_PAGE_TITLE.equals(actualBedroomPageTitle)){
            homeTest.pass("Entered the bedroom page (title is correct)");
        }else {
            homeTest.fail("Did not enter the bedroom page (title is incorrect)" , MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\screenshots\\"+ "nomi")).build());
        }
        helper.navigate();

        //click on dining room category link in the center
        homeTest.info("Entry to the category in the center");
        homePage.clickOnCenterCategoryLink();
        //check the dining page title
        homeTest.info("Check entry to the dining page(by title)");
        String actualDiningPageTitle = driver.getTitle();
        if(Constants.DINING_ROOM_PAGE_TITLE.equals(actualDiningPageTitle)){
            homeTest.pass("Entered the dining page (title is correct)");
        }else {
            homeTest.fail("Did not the dining room page (title is incorrect)" , MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\screenshots\\"+ "nomi")).build());
        }
        helper.navigate();

        //click on banner link
        homeTest.info("Entry to the banner link");
        homePage.ClickOnBannerLink();
        //check the banner baby page title
        homeTest.info("Check entry to the baby page(by title)");
        String actualBabyPageTitle = driver.getTitle();
        if(Constants.BANNER_BABY_PAGE_TITLE.equals(actualBabyPageTitle)){
            homeTest.pass("Entered the baby page (title is correct)");
        }else {
            homeTest.fail("Did not the baby page (title is incorrect)" , MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\screenshots\\"+ "nomi")).build());
        }
        helper.navigate();

        //change language
        homeTest.info("change language from english to hebrew");
        homePage.changeLanguage();
        //check the site in hebrew
        String actualHebrewSiteURL= driver.getCurrentUrl();
        if(Constants.HEBREW_SITE_URL.equals(actualHebrewSiteURL)){
            homeTest.pass("The language changed to Hebrew");
        }else {
            homeTest.fail("The language did not change to Hebrew" , MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\screenshots\\"+ "nomi")).build());
        }
        Thread.sleep(Constants.SLEEP_2);
        //search product
        homeTest.info("search product");
        homePage.searchProduct();

        homeTest.info("Check entry to the products page(by title)");
        String actualProductsPageTitle = driver.getTitle();
        try {
            Assert.assertEquals(Constants.PRODUCT_PAGE_TITLE,actualProductsPageTitle);
            homeTest.pass("Entered the products page (title is correct)");
        }catch (AssertionError error)
        {homeTest.fail("Did not enter the products page (title is incorrect)" , MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\screenshots\\"+ "nomi")).build());}
        homeTest.info(Constants.END_TEST);
    }
    @Test
    public void C_productPage() throws InterruptedException, IOException {
        ExtentTest productTest= report.createTest("ProductTest");
        productTest.info(Constants.START_TEST);
        //select product
        productTest.info("select product");
        productPage.selectProduct();
        //check the product select page title
        productTest.info("Check entry to the product page(by title)");
        String actualProductPageTitle = driver.getTitle();
        if(Constants.SELECT_PRODUCT_PAGE_TITLE.equals(actualProductPageTitle)){
            productTest.pass("Entered the product page (title is correct)");
        }else {
            productTest.fail("Did not enter the product page (title is incorrect)" , MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\screenshots\\"+ "nomi")).build());
        }
        Thread.sleep(Constants.SLEEP_4);
        //select color product
        productTest.info("select color product");
        productPage.selectColorProduct();
        //select size product
        productTest.info("select size product");
        productPage.selectSizeProduct();
        //Add the product twice
        productTest.info("Add the product twice");
        productPage.clickAddToCartBtn();
        productPage.clickAddToCartBtn();
        //display shopping cart
        productPage.clickOnViewShoppingCart();
        //check the URL of the sopping cart
        productTest.info("Check the URL of the shopping cart");
        String actualShoppingCartURL= driver.getCurrentUrl();
        if(Constants.SOPPING_CART_URL.equals(actualShoppingCartURL)){
            productTest.pass("Entered the shopping cart (URL is correct)");
          }else{
            productTest.fail("Did not enter the shopping cart (URL is incorrect)" , MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\screenshots\\"+ "nomi")).build());
          }
        //Check that the product quantity is correct
        String quantity= productPage.amountProduct();
        if(quantity.equals("2")){
            productTest.pass("The quantity matches");
        }else{
        productTest.fail("The quantity does not match" , MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\screenshots\\"+ "nomi")).build());
         }
        //click on check out button
        productTest.info("click on check out button");
        productPage.clickOnCheckOutBtn();
        //check the payment page title
        productTest.info("Check entry to the payment page(by title)");
        String paymentPageTitle = driver.getTitle();
        try {
            Assert.assertEquals(Constants.PAYMENT_PAGE_TITLE,paymentPageTitle);
        productTest.pass("Enter the selected product page (title is correct)");
        }catch (AssertionError error)
        {productTest.fail("Did not enter the selected product page (title is correct)" , MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\screenshots\\"+ "nomi")).build());}
        productTest.info(Constants.END_TEST);
    }
    @Ignore
    @Test
    public void D_paymentPage(){
        ExtentTest paymentTest= report.createTest("PaymentTest");
        paymentTest.info(Constants.START_TEST);
        paymentTest.info("Select the credit card payment method");
        paymentPage.SelectCreditCardPaymentMethod();
        paymentTest.info("click on new credit");
        paymentPage.clickOnNewCredit();
        paymentTest.info("Enter the card number");
        paymentPage.EnterCardNumber();
        paymentTest.info("Enter the expiry month");
        paymentPage.EnterExpiryMonth();
        paymentTest.info("Enter the expiry year");
        paymentPage.EnterExpiryYear();
        paymentTest.info("Enter the security code");
        paymentPage.EnterSecurityCode();
        paymentTest.info("check that received error with card number");
        paymentPage.CheckReceivedErrorHint();
        paymentTest.info("Make a payment");
        paymentPage.clickOnPayNow();
        paymentTest.info(Constants.END_TEST);
    }

    @AfterClass
    public static void afterClass(){
        System.out.println("afterClass");
        driver.quit();
        report.flush();
    }
    //get data method
    public static String getData (String keyName) throws ParserConfigurationException, IOException, SAXException {
        File configXmlFile = new File("C:\\AutomationSelenium\\SeleniumProject\\config.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;

        dBuilder = dbFactory.newDocumentBuilder();

        Document doc = null;

        assert dBuilder != null;
        doc = dBuilder.parse(configXmlFile);

        if (doc != null) {
            doc.getDocumentElement().normalize();
        }
        assert doc != null;
        return doc.getElementsByTagName(keyName).item(0).getTextContent();
    }
    private static String takeScreenShot(String ImagesPath) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath+".png");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath+".png";
    }

}
