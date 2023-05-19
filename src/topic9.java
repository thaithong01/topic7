import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class topic9 {

    WebDriver driver;

    @BeforeTest
    public void start(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void demoDefaultCheckbox(){
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        driver.findElement(By.xpath("//input[@id='eq3']")).click();
        sleepSecond(1);
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='eq3']")).isSelected());
    }


    @Test
    public void demoCustomCheckbox(){
        //cách bình thường, ko khuyến khích
//        driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
//        driver.findElement(By.xpath("//input[@id='mat-radio-3-input']/preceding-sibling::div[@class='mat-radio-inner-circle']")).click();
//        sleepSecond(1);
//        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='mat-radio-3-input']")).isSelected());

        //Cách này hay hơn
        driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");

        //Tạo 1 biến javascrip js (js là tên tự đặt, ko cố định)
        //Do chúng ta cần thực thi hàm executeScript để click 1 element bằng câu lệnh javascript
        //Nhưng kiểu dữ liệu của biến driver chúng ta đang dùng là kiểu Webdriver của selenium, ko giống kiểu dữ liệu của biến js là JavascriptExecutor
        //Nên chúng ta cần ép kiểu dữ liệu của biến driver (đang là Webdriver) sang JavascriptExecutor để có thể thực thi các hàm của javascript
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //dưới này là cú pháp mặc định rồi, muốn biết chi tiết thì GG
        js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//input[@id='mat-radio-3-input']")));
        sleepSecond(1);
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='mat-radio-3-input']")).isSelected());
    }

    @Test
    public void demoJavascruptAlert(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
        String actualAlertMessage = driver.switchTo().alert().getText();
        Assert.assertEquals(actualAlertMessage,"I am a JS Alert");
        sleepSecond(1);
        driver.switchTo().alert().dismiss();
//        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'You clicked an alert successfully ')]")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You clicked an alert successfully");
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
