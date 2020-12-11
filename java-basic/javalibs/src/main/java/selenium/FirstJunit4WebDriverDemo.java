package selenium;

/**
 * Created by Defias on 2017/3/17.
 */

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.junit.*;

public class FirstJunit4WebDriverDemo {
    public WebDriver driver;
    String baseurl = "http://www.sogou.com/";

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "/Users/yzh/Page/driver/chromedriver");
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void test() {
        driver.get(baseurl);
        driver.findElement(By.id("query")).sendKeys("自动化测试");
        driver.findElement(By.id("stb")).click();
    }

}
