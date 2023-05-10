import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class Tc01 {
    WebDriver driver;

    @BeforeTest
    public void start(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

//    @Test
    public void xuLiHTMLDropdownList1(){
        driver.get("https://demo.nopcommerce.com/register");
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();
        Select selectDay = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));

//        select.selectByValue("1");
        selectDay.selectByVisibleText("1");
        List<WebElement> selectOptionDay = selectDay.getOptions();
        Assert.assertEquals(String.valueOf(selectOptionDay.size()),"32");

        Select selectMonth = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
        selectMonth.selectByVisibleText("May");
        List<WebElement> selectOptionMonth = selectMonth.getOptions();
        Assert.assertEquals(String.valueOf(selectOptionMonth.size()),"13");

        Select selectYear = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
        selectYear.selectByVisibleText("1980");
        List<WebElement> selectOptionYear = selectYear.getOptions();
        Assert.assertEquals(String.valueOf(selectOptionYear.size()),"112");
    }

    @Test
    public void xuLiHTMLDropdownList2(){
        driver.get("https://rode.com/en/support/where-to-buy");
        Select select = new Select(driver.findElement(By.xpath("//select[@id='country']")));
        Assert.assertFalse(select.isMultiple());
        select.selectByVisibleText("Vietnam");
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"Vietnam");
        driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
        Assert.assertEquals(String.valueOf(driver.findElements(By.xpath("//div[@class='col-lg-6 p-1 overflow-hidden']")).size()),"50");

        List<WebElement> ListName = driver.findElements(By.xpath("//div[@class='col-lg-6 p-1 overflow-hidden']//h4"));
        for(WebElement thanhphan: ListName){
            System.out.println(thanhphan.getText());
        }
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
}
