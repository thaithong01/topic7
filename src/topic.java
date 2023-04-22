import org.openqa.selenium.By;
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

public class topic {
    WebDriver driver;

    @BeforeTest
    public void start(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
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
