package selenium;

/**
 * Created by Defias on 2017/3/17.
 */

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.safari.*;

public class FirstWebDriverDemo {
    public static void main(String[] args) {
        ////firefox
        //System.setProperty("webdriver.firefox.marionette", "/Users/yzh/Page/driver/geckodriver");
        //WebDriver driver = new FirefoxDriver();
        /*
        使用firefox出现启动后报错：org.openqa.selenium.firefox.NotConnectedException
        原因：与新版本的firefox不兼容，需要安装旧版本的firefox （详见：http://www.it1352.com/312222.html）
         */

        ////safari
        ////System.setProperty("webdriver.safari.driver", "/Users/yzh/Page/driver/SafariDriver.safariextz");
        //System.setProperty("webdriver.safari.noinstall", "true");  //不需要程序再自动安装SafariDriver extension了
        //WebDriver driver = new SafariDriver();
        /*
        前提：
        1、关闭mac机器的SIP机制
        2、开启Safari浏览器的自动化扩展
        3、安装Safari的webdriver驱动WebDriver.safariextz
        mac机器上的坑：http://www.jianshu.com/p/f971b9a35333
        * */

        //chrome
        System.setProperty("webdriver.chrome.driver", "/Users/yzh/Page/driver/chromedriver");
        WebDriver driver = new ChromeDriver();


        String baseurl = "http://www.sogou.com";
        driver.get(baseurl);  //打开浏览器首页
        driver.findElement(By.id("query")).sendKeys("自动化测试ABC");   //在搜索框输入：自动化测试
        driver.findElement(By.id("stb")).click();  //单击搜索按钮

        driver.quit();   //关闭打开的浏览器
        //driver.close();
    }
}
