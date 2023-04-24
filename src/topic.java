import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.lang.model.element.Element;
import java.util.List;
import java.util.Random;

public class topic {
    WebDriver driver;

    @BeforeTest
    public void start(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

//    @Test
    public void demoTopic7(){
        driver.get("https://applitools.com/automating-tests-chrome-devtools-recorder-webinar/");
        Select select = new Select(driver.findElement(By.xpath("//select[@id='Person_Role__c']")));

        select.selectByIndex(2);
        sleepSecond(3);

        select.selectByValue("Architect / Team Lead");
        sleepSecond(3);

//        select.selectByVisibleText("Other");
//        sleepSecond(3);

        List<WebElement> selectoption = select.getOptions();
        for(WebElement thanhphan: selectoption){
            if(thanhphan.getText().contains("Senior Management / Technical Executive")){
                thanhphan.click();
                break;
            }
        }
        Assert.assertEquals("ChesseBurger",select.getFirstSelectedOption().getText());
//        Assert.assertEquals(select.getFirstSelectedOption().getAttribute("value"),"Senior Management / Technical Executive");

//        //Cách truyền thống
//        driver.findElement(By.xpath("//select[@id='Person_Role__c']")).click();
//        sleepSecond(2);
//        driver.findElement(By.xpath("//select[@id='Person_Role__c']/option[@value='QA Manager / Director']")).click();
//        sleepSecond(2);
    }

    @Test
    public void xuLiHtmlDropdownList(){
        String xpathprarentDay = "//select[@name='DateOfBirthDay']";
        String ValueDayOfBirth = "1";
        String xpathprarentMon = "//select[@name='DateOfBirthMonth']";
        String ValueMonOfBirth = "May";
        String xpathprarentYear = "//select[@name='DateOfBirthYear']";
        String ValueYearOfBirth = "1980";
        String FirstName = "//input[@id='FirstName']";
        String LastName = "//input[@id='LastName']";
        String Email = "//input[@id='Email']";
        String Password = "//input[@id='Password']";
        String CPassword = "//input[@id='ConfirmPassword']";
        String ValueEmail = "thong"+getRandomNumber()+"@gmail.com";
        String ValuePassword = "123456";

        driver.get("https://demo.nopcommerce.com/register");
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();
        driver.findElement(By.xpath("//input[@id='gender-male']")).click();
        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("");

//        Select selectDOB = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
//        List<WebElement> selectDayOfBirth = selectDOB.getOptions();
//        for (WebElement thanhphan: selectDayOfBirth){
//            if (thanhphan.getText().equals("1")){
//                thanhphan.click();
//                break;
//            }
//        }
        inputToTextBox(FirstName,"Thong");
        inputToTextBox(LastName,"Tran");
        inputToTextBox(Email,ValueEmail);
        inputToTextBox(Password,ValuePassword);
        inputToTextBox(CPassword,ValuePassword);

        selectToItems(xpathprarentDay,ValueDayOfBirth);
        Assert.assertEquals(getSizeDropdownBox(xpathprarentDay),32);
        sleepSecond(1);

        selectToItems(xpathprarentMon,ValueMonOfBirth);
        Assert.assertEquals(getSizeDropdownBox(xpathprarentMon),13);
        sleepSecond(1);

        selectToItems(xpathprarentYear,ValueYearOfBirth);
        Assert.assertEquals(getSizeDropdownBox(xpathprarentYear),112);
        sleepSecond(1);

        driver.findElement(By.xpath("//button[@id='register-button']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),"Your registration completed");

        driver.findElement(By.xpath("//a[@class='ico-login']")).click();

        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(ValueEmail);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(ValuePassword);
        driver.findElement(By.xpath("//div[@class='buttons']/button[@type='submit']")).click();
        driver.findElement(By.xpath("//a[@class='ico-account']")).click();
//        Assert.assertEquals(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")).getText(),ValueDayOfBirth);
//        Assert.assertEquals(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")).getText(),ValueMonOfBirth);
//        Assert.assertEquals(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")).getText(),ValueYearOfBirth);

        Assert.assertEquals(selectDropdownItem(xpathprarentDay,ValueDayOfBirth), ValueDayOfBirth);

        //sử dụng javascrip để getText của 1 số hiden element
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        Assert.assertEquals(js.executeScript("return document.querySelector(\"select[name='DateOfBirthDay']\").value;"),"1");
    }

    @AfterTest
    public void end() throws InterruptedException {
        Thread.sleep(5000);
//        driver.quit();
    }

    public void sleepSecond(long second){
        try{
            Thread.sleep(second * 1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(99999);
    }

    public void inputToTextBox(String locator, String value){
        driver.findElement(By.xpath(locator)).sendKeys(value);
    }

    public void selectToItems(String xpathprarent, String value){
        Select select = new Select(driver.findElement(By.xpath(xpathprarent)));
        List<WebElement> selectBirth = select.getOptions();
        for (WebElement thanhphan: selectBirth){
            if (thanhphan.getText().equals(value)){
                thanhphan.click();
                break;
            }
        }
    }

    public int getSizeDropdownBox(String xpathprarent) {
        Select select = new Select(driver.findElement(By.xpath(xpathprarent)));
        List<WebElement> selectBirth = select.getOptions();

        return selectBirth.size();
    }

//    public String getFirstSelectedItems(String xpathprarent, String value){
//        Select select = new Select(driver.findElement(By.xpath(xpathprarent)));
//        List<WebElement> selectBirth = select.getOptions();
//        for (WebElement thanhphan: selectBirth){
//            if (thanhphan.getText().equals(value)){
//                thanhphan.click();
//                break;
//            }
//        }
//        return select.getFirstSelectedOption().getText();
//    }
public String selectDropdownItem(String xpathprarent, String value) {
    Select select = new Select(driver.findElement(By.xpath(xpathprarent)));
    select.selectByValue(value);
    return select.getFirstSelectedOption().getAttribute("value");
    }
}
