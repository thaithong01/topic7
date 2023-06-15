import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.sql.SQLOutput;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class topic11 {
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

    @Test
    public void tc5(){
        driver.get("https://www.javacodegeeks.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Your Email']")));
        Assert.assertTrue(driver.findElement(By.xpath("//input[@placeholder='Your Email']")).isDisplayed());

    }

    @Test
    public void tc8(){
        driver.get("https://skills.kynaenglish.vn/");
        driver.switchTo().frame(0);
        Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(),"165K followers");
        driver.switchTo().defaultContent();
        driver.switchTo().frame("cs_chat_iframe");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='Chào bạn. Chúng tôi có thể giúp gì cho bạn?']/ancestor::div[@class='button_text']/following-sibling::div")));
        driver.findElement(By.xpath("//label[text()='Chào bạn. Chúng tôi có thể giúp gì cho bạn?']/ancestor::div[@class='button_text']/following-sibling::div")).click();

        driver.findElement(By.xpath("//div[@class='editing field profile_field']//input[@placeholder='Nhập tên của bạn']")).sendKeys("Darjeeling");
        driver.findElement(By.xpath("//div[@class='editing field profile_field']//input[@placeholder='Nhập số điện thoại của bạn']")).sendKeys("0123456789");
        WebElement selectItem =  driver.findElement(By.xpath("//select[@id='serviceSelect']"));
        Select select = new Select(selectItem);
        select.selectByVisibleText("TƯ VẤN TUYỂN SINH");

        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("Excel");
        driver.findElement(By.xpath("//button[@class='search-button']")).click();

        List<WebElement> list = driver.findElements(By.xpath("//div[@class='content']//h4"));
        for (WebElement listTam : list){
            Assert.assertTrue(listTam.getText().contains("Excel"));
        }
    }

    @Test
    public void tc9(){
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        driver.switchTo().frame("login_page");
        driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("thaithong97");
        driver.findElement(By.xpath("//a[@class='btn btn-primary login-btn']")).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']"))).isDisplayed());
    }

    @Test
    public void tc10(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        String parentID = driver.getWindowHandle();
        String parent = driver.getTitle();
        driver.findElement(By.xpath("//a[contains(text(),'GOOGLE')]")).click();
        //Nếu không biết title của trang, có thể mở trang đó lên vd mở google.com ==> click f12 ==> chọn tab Console ==> nhập 'document.title'
        switchToWindowsByTitle("Google");
        sleepSecond(1);
        Assert.assertEquals(driver.getTitle(),"Google");
        switchToWindowsByTitle(parent);

        driver.findElement(By.xpath("//a[contains(text(),'FACEBOOK')]")).click();
        switchToWindowsByTitle("Facebook - log in or sign up");
        sleepSecond(1);
        Assert.assertEquals(driver.getTitle(),"Facebook – log in or sign up");
        switchToWindowsByTitle(parent);

        driver.findElement(By.xpath("//a[contains(text(),'TIKI')]")).click();
        switchToWindowsByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
        sleepSecond(1);
        Assert.assertEquals(driver.getTitle(),"Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
        switchToWindowsByTitle(parent);

        closedAllWindowsExceptParent(parentID);
        switchToWindowsByTitle(parent);
        Assert.assertEquals(driver.getCurrentUrl(),"https://automationfc.github.io/basic-form/index.html");
    }

    public void switchToWindowsByTitle(String titleParent){
        Set<String> listSession = driver.getWindowHandles();
        for (String sessionTam : listSession){
            driver.switchTo().window(sessionTam);
            String title = driver.getTitle();
            if (title.equals(titleParent))
                break;
        }
    }

    public void closedAllWindowsExceptParent(String parentID){
        Set<String> listSession = driver.getWindowHandles();
        for (String sessionTam : listSession){
            if (!sessionTam.equals(parentID)){
                driver.switchTo().window(sessionTam);
                driver.close();
            }
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
