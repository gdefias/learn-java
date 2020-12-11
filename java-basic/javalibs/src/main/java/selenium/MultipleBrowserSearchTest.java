package selenium;

/**
 * Created by Defias on 2017/3/17.
 *
 * TestNG进行并发兼容性测试
 *
 */

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.*;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.safari.*;
import java.util.*;

public class MultipleBrowserSearchTest {
    public WebDriver driver;
    String baseurl = "http://www.sogou.com/";

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String Browser) {
        if (Browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.firefox.marionette", "/Users/yzh/Page/driver/geckodriver");
            WebDriver driver = new FirefoxDriver();
        } else if (Browser.equalsIgnoreCase("safari")) {
            System.setProperty("webdriver.safari.noinstall", "true");
            WebDriver driver = new SafariDriver();
        } else {
            System.setProperty("webdriver.chrome.driver", "/Users/yzh/Page/driver/chromedriver");
            driver = new ChromeDriver();
        }

        driver.get(baseurl);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void testSearch() {
        driver.get(baseurl);
        WebElement inputbox = driver.findElement(By.id("query"));

        Assert.assertTrue(inputbox.isDisplayed());  //判断输入框是否在页面显示
        inputbox.sendKeys("自动化测试");

        driver.findElement(By.id("stb")).click();

        //等待
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //判断搜索结果的页面中是否包含期望的关键字
        Assert.assertTrue(driver.getPageSource().contains("自动化"));
    }

}
