import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class topic10 {

    WebDriver driver;

    @BeforeTest
    public void start(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void tc1(){
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        WebElement elementTextBox = driver.findElement(By.xpath("//input[@id='age']"));
        //Tạo biến actions để sử dụng chức năng của thư viện Actions để có sử dụng các thao tác với chuột
        // biến actions được khởi tạo và truyền vào tham số driver để có thể thao tác với element do thư viện WebDriver cung cấp
        Actions actions = new Actions(driver);
        //biến actions sẽ thực thi hàm moveToElement để tao tác với element được truyền vào và sau đó kết thúc thao tác bằng chức năng perform
        actions.moveToElement(elementTextBox).perform();
        //Cần sử dụng tính năng debug của web để bắt xpath của tooltip
        //chuyển qua tab Source rồi nhấn Ctrl+\ để dùng tính năng debug, sau đó chuyển về tab element và bắt xpath
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).isDisplayed());
    }

    @Test
    public void tc4(){
        driver.get("https://automationfc.github.io/jquery-selectable/");
        WebElement elementLi1 = driver.findElement(By.xpath("//li[text()='1']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(elementLi1).clickAndHold().perform();
        WebElement elementLi4 = driver.findElement(By.xpath("//li[text()='4']"));
        actions.moveToElement(elementLi4).release().perform();
        List<WebElement> listLi = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
        Assert.assertEquals(listLi.size(),4);

//        List<WebElement> listLi = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
//        for (WebElement listtam: listLi ){
//
//        }
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
