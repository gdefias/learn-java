package selenium;

/**
 * Created by Defias on 2017/3/17.
 */

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.*;
import org.testng.annotations.*;


public class FirstTestNGWebDriverDemo {
    public WebDriver driver;
    String baseurl = "http://www.sogou.com/";

    @BeforeMethod
    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver", "/Users/yzh/Page/driver/chromedriver");
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @Test
    public void testSearch() {
        driver.get(baseurl);
        WebElement inputbox = driver.findElement(By.id("query"));

        Assert.assertTrue(inputbox.isDisplayed());  //判断输入框是否在页面显示
        inputbox.sendKeys("自动化测试");

        driver.findElement(By.id("stb")).click();
    }

}
