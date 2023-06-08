import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class topic11 {
    WebDriver driver;

    @BeforeTest
    public void start(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void tc1(){
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.xpath("//button[@class='login_ icon-before']")).click();
        //Lưu ý: sau khi nhấn nút đăng nhập, popup có thể mất vài giây để hiện lên hoàn toàn, cần chờ vài giây cho nó hiện lên
        sleepSecond(2);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='modal-login-v1']")).isDisplayed());
        driver.findElement(By.xpath("//input[@id='account-input']")).sendKeys("automationfc");
        driver.findElement(By.xpath("//input[@id='password-input']")).sendKeys("automationfc");
        driver.findElement(By.xpath("//button[@class='btn-v1 btn-login-v1 buttonLoading']")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String getText = (String) js.executeScript("return arguments[0].innerText;",driver.findElement(By.cssSelector("div.error-login-panel")));
        Assert.assertEquals(getText,"Tài khoản không tồn tại!");
        driver.findElement(By.xpath("//button[@class='close']")).click();
        sleepSecond(2);
        Assert.assertFalse(driver.findElement(By.xpath("//div[@id='modal-login-v1']")).isDisplayed());
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
