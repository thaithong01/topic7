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

    @Test
    public void tc5(){
        driver.get("https://automationfc.github.io/jquery-selectable/");
        Actions actions = new Actions(driver);
        //Move chuột đến nút 1 và nhấn giữ Ctrl
        actions.moveToElement(driver.findElement(By.xpath("//li[text()=1]"))).click().keyDown(Keys.CONTROL).perform();
        sleepSecond(1);
        actions.moveToElement(driver.findElement(By.xpath("//li[text()=3]"))).click().perform();
        sleepSecond(1);
        actions.moveToElement(driver.findElement(By.xpath("//li[text()=6]"))).click().perform();
        sleepSecond(1);
        //sau khi di chuyển chuột đến nút 11 và click vào nó thì dùng thêm hành động release để dừng hành động nhấn giữ Ctrl ở trên
        actions.moveToElement(driver.findElement(By.xpath("//li[text()=11]"))).click().release().perform();
        sleepSecond(1);

        List<WebElement> listLi = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
        Assert.assertEquals(listLi.size(),4);
//        List<WebElement> listTc5 = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
//        for (WebElement tam: listTc5){
//            Assert.assertTrue(tam.isSelected());
//        }
    }

    @Test
    public void tc6(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//button[@ondblclick]"))).doubleClick().perform();
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).isDisplayed());
    }

    @Test
    public void tc7(){
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"))).contextClick().perform();
        actions.moveToElement(driver.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-quit']"))).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-quit context-menu-hover context-menu-visible']")).isDisplayed());
        actions.click(driver.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-quit context-menu-hover context-menu-visible']"))).perform();
        driver.switchTo().alert().accept();
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-quit']")).isDisplayed());
    }

    @Test
    public void tc8(){
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        Actions actions = new Actions(driver);
        WebElement sourceElement = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement targetElement = driver.findElement(By.xpath("//div[@id='droptarget']"));
        actions.moveToElement(sourceElement).dragAndDrop(sourceElement, targetElement).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='You did great!']")).isDisplayed());
        WebElement colorBackground = driver.findElement(By.xpath("//div[text()='You did great!']"));
        String rgbColorBackground = colorBackground.getCssValue("background-color");
        String hexColorBackground = Color.fromString(rgbColorBackground).asHex();
        Assert.assertEquals(hexColorBackground,"#03a9f4");
    }

    @Test
    public void tc9(){
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
//        WebElement columnA = (WebElement) js.executeScript("return document.querySelector(argument[0];",
//                driver.findElement(By.cssSelector("div#column-a")));
//        WebElement columnB = (WebElement) js.executeScript("return document.querySelector(argument[0];",
//                driver.findElement(By.cssSelector("div#column-b")));

        WebElement A = driver.findElement(By.xpath("//div[@id='column-a']"));
        WebElement B = driver.findElement(By.xpath("//div[@id='column-b']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(A).dragAndDrop(A, B).build().perform();
        sleepSecond(2);
        actions.moveToElement(A).dragAndDrop(A, B).build().perform();
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
