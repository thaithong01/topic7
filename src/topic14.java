import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class topic14 {
    WebDriver driver;
    public WebDriverWait wait;

    @BeforeTest
    public void start(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Khởi tạo WebDriverWait với thời gian chờ là 10 giây
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void tc1(){
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        WebElement uploadFile = driver.findElement(By.xpath("//input[@name='files[]']"));
        String filePath = "\"C:\\Users\\Admin\\Downloads\\20230612014956.png\"";
        uploadFile.sendKeys(filePath);

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@title='246482650_1231995290647073_506250896130028103_n.png']")).isDisplayed());
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
