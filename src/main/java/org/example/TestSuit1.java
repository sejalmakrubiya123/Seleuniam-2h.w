package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static jdk.jfr.internal.handlers.EventHandler.timestamp;

public class TestSuit1 {



    static String expectedRegistrationCompleteMsg = "Registration not working";
    static String expectedReferAFriendMessage = "Your message has not been sent";
    static String expectedVotingMessage = "User cannot vote";
    static String expectedNonRegReferAFriendMsg = "Non registered customer can use email a friend feature";
    static String expectedNonRegisteredUserVoteMsg = "Non registered customer can vote";
    static String expectedCompare2ProductMessage = "There are 2 prouducts stll after clearing List";
    static  String expectedProductInShoppingCartamaessage = "Wrong products in shopping cart";

    protected static  WebDriver driver;

    public static void clickOnElement(By by)
    {
        driver.findElement(by).click();
    }

    public static void typeText(By by , String text)
    {
        driver.findElement(by).sendKeys(text);
    }
    public static long timeStamp()
    {
        Timestamp timestamp1 =new Timestamp(System.currentTimeMillis());
        return timestamp1 .getTime();

    }
    public static String getTextFromElement(By by)
    {
        return driver.findElement(by).getText();
    }





    @BeforeMethod
    public static void openBrowser() {
        driver = new ChromeDriver();
        //open demonopcommerce url
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterMethod
    public static void closeBrowser() {
        driver.close();
    }

    @Test
    public static void verifyUserShouldBeAbleToRegisterSuccessfully() {





        //click on register button

        clickOnElement(By.className("ico-register"));

        //enter firstname
        typeText(By.id("FirstName"),"testFirstName");

        //enter lastname
        typeText(By.id("LastName"), "testLastName");

        //enter email address
        typeText(By.name("Email"), "testpatel" + timestamp() + "@gmail.com");

        //enter password
        typeText(By.id("Password"), "test1234");

        //enter confirm password
        typeText(By.id("ConfirmPassword"), "test1234");

        //click on register submit button
        clickOnElement(By.id("register-button"));

        //verify message


        String actualmessage = getTextFromElement(By.xpath("//div[@class='result']"));
        getTextFromElement((By.xpath("//div[@class=result")) );

        System.out.println(" message: " + actualmessage);
        Assert.assertEquals(actualmessage , expectedRegistrationCompleteMsg, "registration not working");



    }



    @Test
    public static void verifyRegisteredUserShouldBeAbleTovoteSuccessfully() {

        //click on register
        clickOnElement(By.className("ico-register"));

        //Enter first name
        typeText(By.id("FirstName"),"testFirstName");

        //Enter last name
        typeText(By.id("LastName"), "testLastName");

        //Enter email address
        typeText(By.name("Email"), "Test@gmail.com");

        //enter password
        typeText(By.id("password"),"Test1234");

        // confirm password
        typeText(By.id("ConfirmPassword"),"Test1234");

        //click on register button
        clickOnElement(By.id("register-button"));

        //click on login button
        clickOnElement(By.className("ico-login"));

        //enter email
        typeText(By.id("Email"), "Test@gmail.com");

        //enter password
        typeText(By.className("password"), "Test1234");

        //click on login button
        clickOnElement(By.xpath("//button[@class=\"button-1 login-button\"]"));

        //click on good text
        clickOnElement(By.xpath("//div[@class=\"poll\"]/ul/li[2]/label"));

        //click on vote button
        clickOnElement(By.xpath("//button[@class=\"button-2 vote-poll-button\"]"));


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //print message

        String actualMessage = getTextFromElement(By.xpath("//span[@class=\"poll-total-votes\"]"));
        System.out.println("Actual message:" + actualMessage);
        Assert.assertEquals(actualMessage, expectedVotingMessage, "can't vote");


    }


    @Test
    public static  void VerifyUserShouldBeAbleToCompare2Products() {


        //click on register

        clickOnElement(By.className("ico-register"));

        //enter firstname
        typeText(By.id("FirstName"),"testFirstName");
        //enter lastName
        typeText(By.id("LastName"), "testLastName");
        //enter email address
        typeText(By.name("Email"), "Test@gmail.com");
        //enter password
        typeText(By.id("Password"), "Test1234");
        //confirm password
        typeText(By.id("ConfirmPassword"), "Test1234");
        //click on register button
        clickOnElement(By.id("register-button"));
        //click on login button
        clickOnElement(By.className("ico-login"));
        //enter email
        typeText(By.id("Email"), "Test@gmail.com");
        //enter password
        typeText(By.className("password"), "Test1234");
        //click on login button
        clickOnElement(By.xpath("//button[@class=\"button-1 login-button\"]"));

        //click on Htc one m8 Android l5.0 phone compare button
        clickOnElement(By.xpath("//div[@class='item-grid']/div[3]/div[1]/div[2]/div[3]/div[2]/button[2]"));

        clickOnElement(By.xpath("//span[@class= \"close\"]"));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //click on $25 Virtual Gift card compare button
        clickOnElement(By.xpath("//div[@class='item-grid']/div[4]/div[1]/div[2]/div[3]/div[2]/button[2]"));

       driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);

       //click cross on green comparision product bar
        clickOnElement(By.xpath("//span[@class= \"close\"]"));

         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        //click on green line

       // wait.(ExpectedConditions.visibilityOfElementLocated(By .xpath("//div[@class=\"bar-notification success\"]/p/a[@href=\"/compareproducts\"]")));

        clickOnElement(By.xpath("//div[@class=\"bar-notification success\"]/p/a[@href=\"/compareproducts\"]"));

        //printout mobile name
        String actualMessage = getTextFromElement(By.xpath("//table[@class=\"compare-products-table\"]/tbody/tr[3]/td[2]/a"));

        System.out.println("message: " + actualMessage);

        //printout card name
        String actualmessage1 = getTextFromElement(By.xpath("//table[@class=\"compare-products-table\"]/tbody/tr[3]/td[3]/a"));
        System.out.println("message: + actualmessage");
        //click on clear list button
        clickOnElement(By.className("clear-list"));

        //printout msg after clicking on clear list
        String actualmessage2 = getTextFromElement(By.className("no-data"));

        System.out.println("message :" + actualmessage2);

        Assert.assertEquals(actualMessage,expectedCompare2ProductMessage,"There are 2 product still after clearing List");


    }

    @Test

      public static void VerifyNonRegisteredUserShouldNotBeAbleToEmailfriend()  {

            // click on add to cart button
        clickOnElement(By.xpath("//div[@class = \"item-grid\" ]/div[2]/div/div[2]/div[3]/div[2]/button[1]"));

            //click on email a  friend
        clickOnElement(By.xpath("//div[@class='email-a-friend']"));

        //type friends email
        typeText(By.id("FriendEmail"),"Testpatel@gmail.com");

        //type your email id
        typeText(By.id("YourEmailAddress"),"Test@gmail.com");

        //click on send email

            clickOnElement(By.name("Send email"));

            String actualMessage = driver.findElement(By.xpath("//div[@class=\"message-error validation-summary-errors\"]/ul/li")).getText();

        System.out.println("Error message" + actualMessage);
        Assert.assertEquals(actualMessage,expectedNonRegReferAFriendMsg,"\"Non registered customer can use email a friend feature\"");


    }
    @Test
    public static  void verifyUserShouldBeAbletoSeeproductsInShoppingCartSuccessfully(){

        // click on electronics
        clickOnElement(By.linkText("Electronic"));
        clickOnElement(By.linkText("Camera & photo"));

        //click on leica t mirrorless digital camera
        clickOnElement(By.linkText("Leica T Mirrorless Digital Camera"));

        //get product name

        String productname = getTextFromElement(By.xpath("//div[@class=\"product-name\"]/h1"));

        System.out.println("print product Name: " +productname);
        //click on add to cart button
        clickOnElement(By.xpath("//div[@class=\"add-to-cart\"]/div/button"));

        (driver,Duration.ofSeconds(40));

        //click on shoppiong cart on right top corner of page
        clickOnElement(By.xpath("//span[@class=\"cart-label\"]"));

        //verify & print out leica camera name text
        String productname1 = getTextFromElement(By.className("product-name"));

        System.out.println("product in cart: "+ productname1);
        //confirm product name is same in shopping cart as what we add
        Assert.assertEquals(productname1, expectedProductInShoppingCartamaessage , "Wrong product in shopping cart");


    }
    @Test

  public  static void verifyUserShouldBeAbleToReferAProductToFriendSuccessfully(){

        //click on Register

        clickOnElement(By.className("ico-register"));

        //Enter first name
        typeText(By.id("FirstName"),"testFirstName");

        //enter lastname
        typeText(By.id("LastName"),"testLastName");
        //enter email address
        typeText(By.name("Email"), "test@gmail.com");

        //enter password
        typeText(By.id("Password"),"test1234");

        //enter confirm password
        typeText(By.id("ConfirmPassword"),"test1234");

        //click on register button
        clickOnElement(By.id("register-button"));

        //click on login button
        clickOnElement(By.className("ico-login"));

        //enter email
        typeText(By.id("Email"),"test@gmail.com");

        //enter password
        typeText(By.id("password"),"test1234");

        //click on login button
        clickOnElement(By.xpath("//button[@class=\"button-1 login-button\"]"));

        //enter email
        typeText(By.id("Email"), "Test@gmail.com");
        //enter password
        typeText(By.className("Password"), "Test1234");


        //click on login button


        clickOnElement(By.xpath("//button[@class=\"button-1 login-button\"]"));

        //click on product build your own computer
        clickOnElement(By.xpath("//a[@href=\"/build-your-own-computer\"][text()='Build your own computer']"));

        //click on email friend

        clickOnElement(By.xpath("//button[@class=\"button-2 email-a-friend-button\"]"));

        //type friends email

       typeText (By.id("friendEmail"),"hellofriend" +timestamp() +"@gmail.com");

       //type your email
        //click on send email
        //type message
        //click on send email

        clickOnElement(By.xpath("//button[@class='button-1send-email-a-friend-button']"));

       // sout msg has been sent
        getTextFromElement(By.xpath("//a@class='product']"));

        //sout msg has been sent

        String actualmessage = getTextFromElement(By.xpath("//div[@class='result']"));

        getTextFromElement((By.xpath("//div[@class='result']")));
        System.out.println("message:" + actualmessage);
        Assert.assertEquals(actualmessage, expectedReferAFriendMessage, "your message has not been sent" );


    }

    @Test
    public  void verifyNonRegisteredUserShouldNotBeAbleToVote(){
        
        //click on good text option 
        clickOnElement(By.id("pollanswers-2"));
        //click on vote button
        clickOnElement(By.id("vote-poll-1"));
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
       // Object ExpectedConditions;
        Object ExpectedCondition = new Object();
        //wait.until(ExpectedCondition.visiblityOfElementLocated(By.className("poll-voe-error")));


        
        //to verify message pop up when uou click on vote button
        String actualmessage = getTextFromElement(By.className("poll-vote-error"));
        
        // to print error message
        System.out.println("my message: " + actualmessage);
        Assert.assertEquals(actualmessage,expectedNonRegisteredUserVoteMsg, "Non registered customer can vote");
        
        
    }












}