import org.openqa.selenium.*;
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

//    @Test
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

//    @Test
    public void xuLiHTMLDropDownList6(){
        driver.get("https://applitools.com/automating-tests-chrome-devtools-recorder-webinar/");
        Select SelectPersonRole = new Select(driver.findElement(By.xpath("//select[@id='Person_Role__c']")));
        SelectPersonRole.selectByVisibleText("Student");

        Select SelectTestFramework = new Select(driver.findElement(By.xpath("//select[@id='Test_Framework__c']")));
        SelectTestFramework.selectByVisibleText("Selenium");

        Select SelectCountry = new Select(driver.findElement(By.xpath("//select[@id='Self_Report_Country__c']")));
        SelectCountry.selectByVisibleText("Canada");
    }

//    @Test
    public void customDropDown(){
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        driver.findElement(By.xpath("//span[@id='speed-button']")).click();
        sleepSecond(3);
        List<WebElement> ListSpeed = driver.findElements(By.xpath("//ul[@id='speed-menu']//li"));
        for (WebElement itemListSpeed: ListSpeed){
            if (itemListSpeed.getText().equals("Medium")){
                itemListSpeed.click();
                break;
            }
        }

//        sleepSecond(3);
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']//span[@class='ui-selectmenu-text']")).getText(),"Medium");
//        sleepSecond(2);

        driver.findElement(By.xpath("//span[@id='files-button']")).click();
        sleepSecond(3);
        List<WebElement> ListFile = driver.findElements(By.xpath("//ul[@id='files-menu']//li[@class='ui-menu-item']"));
        for (WebElement itemListFile: ListFile){
            if (itemListFile.getText().equals("ui.jQuery.js")){
                itemListFile.click();
                break;
            }
        }
//        sleepSecond(3);
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='files-button']//span[@class='ui-selectmenu-text']")).getText(),"ui.jQuery.js");
//        sleepSecond(2);
    }

//    @Test
    public void ReactJS(){
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        driver.findElement(By.xpath("//div[@role='listbox']")).click();
        sleepSecond(2);

        List<WebElement> ListPerson = driver.findElements(By.xpath("//div[@role='option']"));
        for (WebElement itemReactJS: ListPerson){
            if (itemReactJS.getText().equals("Matt")){
                itemReactJS.click();
                break;
            }
        }
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Matt");
    }

//    @Test
    public void VueJS(){
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        driver.findElement(By.xpath("//div[@class='btn-group']")).click();
        sleepSecond(2);
        List<WebElement> ListVueJS = driver.findElements(By.xpath("//ul[@class='dropdown-menu']//li//a"));
        for (WebElement itemVueJS: ListVueJS){
            if (itemVueJS.getText().equals("First Option")){
                itemVueJS.click();
                break;
            }
        }
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(),"First Option");
    }

    @Test
    public void editable(){
        driver.get("https://indrimuska.github.io/jquery-editable-select/");
        driver.findElement(By.xpath("//div[@id='default-place']//input")).click();
        sleepSecond(2);
        List<WebElement> ListEditable = driver.findElements(By.xpath("//div[@id='default-place']//li"));
        for (WebElement itemEditable: ListEditable){
            if (itemEditable.getText().equals("Mini")){
                itemEditable.click();
                break;
            }
        }
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String actualTest = js.executeScript("return document.querySelector(\"div#default-place input.form-control.es-input\").value;").toString();
        Assert.assertEquals(actualTest,"Mini");
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
