import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

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
    public void demoJavascriptAlert(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
        String actualAlertMessage = driver.switchTo().alert().getText();
        Assert.assertEquals(actualAlertMessage,"I am a JS Alert");
        sleepSecond(1);
        driver.switchTo().alert().dismiss();
//        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'You clicked an alert successfully ')]")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You clicked an alert successfully");
    }

    @Test
    public void demoAuthenticationAlert() throws IOException {
        //cách 1
//        String url = "http://admin:admin@the-internet.herokuapp.com/basic_auth";
//        driver.get(url);

        //cách 2
        String username = "admin";
        String password = "admin";
        String chromeAuthen = "E:\\Selenium\\topic7\\autoIT\\authen_chrome.exe";
        String authenUrl = "http://the-internet.herokuapp.com/basic_auth";
        Runtime.getRuntime().exec(new String[] {chromeAuthen, username, password});

        driver.get(authenUrl);
        sleepSecond(3);
    }

    @Test
    public void tc1Button(){
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']")).click();
        Assert.assertFalse(driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).isEnabled());

        String cssRGBColorValue = driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).getCssValue("color");
        String cssHexColorValue = Color.fromString(cssRGBColorValue).asHex();
        Assert.assertEquals(cssHexColorValue,"#636363");

        driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("thaithong@gmail.com");
        driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("thaithong");
        Assert.assertTrue(driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).isEnabled());

        String cssEnableRGBColorValue = driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).getCssValue("background-color");
        String cssEnableHexColorValue = Color.fromString(cssEnableRGBColorValue).asHex().toUpperCase();
        Assert.assertEquals(cssEnableHexColorValue,"#C92127");
    }

    @Test
    public void tc2DefaultCheckboxRadiobutton(){
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
        driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).click();
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")).click();
        if (driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")).isSelected()){
            Assert.assertTrue(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")).isSelected());
        }
        else {
            driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")).click();
            Assert.assertTrue(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")).isSelected());
        }
    }

    @Test
    public void tc3DefaultChecboxRadioButton(){
        driver.get("https://material.angular.io/components/radio/examples");
        driver.findElement(By.xpath("//input[@value='Summer']")).click();
        if (driver.findElement(By.xpath("//input[@value='Summer']")).isSelected()){
            Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Summer']")).isSelected());
        }
        else {
            driver.findElement(By.xpath("//input[@value='Summer']")).click();
            Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Summer']")).isSelected());
        }
        sleepSecond(2);

        driver.get("https://material.angular.io/components/checkbox/examples");
        driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div//input")).click();
        driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div//input")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div//input")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div//input")).isSelected());
        sleepSecond(2);

        driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div//input")).click();
        driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div//input")).click();
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div//input")).isSelected());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div//input")).isSelected());
    }

    @Test
    public void tc4CustomCheckboxOrRadioButton(){
        driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//input[@id='mat-radio-3-input']")));
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='mat-radio-3-input']")).isSelected());
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
