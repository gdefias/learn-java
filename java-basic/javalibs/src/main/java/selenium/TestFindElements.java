package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.*;

/**
 * Created by Defias on 2017/3/17.
 *
 * 页面元素定位方法
 *
 */

/*
定位方法	            定位方法的Java语言实现实例
----------------------------------------------------------------------------------
使用ID定位	        driver.findElement(By.id("ID值"));
使用name定位	        driver.findElement(By.name("name值"));
使用链接的全部文字定位	driver.findElement(By.linkText("链接的全部文字内容"));
使用部分链接文字定位	driver.findElement(By.partialLinkText("链接的部分文字内容"));
使用XPath方式定位 	driver.findElement(By.xpath("XPath定位表达式"));
使用CSS方式定位 	    driver.findElement(By.cssSelector("CSS定位表达式"));
使用Class名称定位 	driver.findElement(By.className("页面元素的Class属性值"));
使用标签名称定位	    driver.findElement(By.tagName("页面中的HTML标签名称"));
 */


public class TestFindElements {
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

        //ID定位
        driver.findElement(By.id("query")).sendKeys("自动化测试");
        driver.findElement(By.id("stb")).click();

        //name定位
        WebElement username=driver.findElement(By.name("username"));
        WebElement password=driver.findElement(By.name("password"));
        WebElement submit=driver.findElement(By.name("submit"));

        //全部链接文字定位
        WebElement link=driver.findElement(By.linkText("sogou搜索"));

        //部分链接文字定位
        WebElement link1 = driver.findElement(By.partialLinkText("sogou搜索"));
        List<WebElement> links=driver.findElements(By.partialLinkText("搜索"));

        //标签定位
        WebElement link2 = driver.findElement(By.tagName("a"));
        List<WebElement> linkss = driver.findElements(By.tagName("a"));

        //Class名称定位
        WebElement input=driver.findElement(By.className("tight"));

        //XPath定位
        WebElement password1 = driver.findElement(By.xpath("//*[@id='J_login_form']/dl/dt/input[@id='J_password']"));
        WebElement password2 = driver.findElement(By.xpath("//*[@id='J_login_form']/*/*/input[@id='J_password']"));

        /*
        查找页面根元素：//
        查找页面上所有的input元素：//input
        查找页面上第一个form元素内的直接子input元素(即只包括form元素的下一级input元素，使用绝对路径表示，单/号)：//form[1]/input
        查找页面上第一个form元素内的所有子input元素(只要在form元素内的input都算，不管还嵌套了多少个其他标签，使用相对路径表示，双//号)：//form[1]//input
        查找页面上第一个form元素内的最后一个input元素：//form[1]//input[last()]（注意：在xpath中没有first()这样的方法，用//form[1]//input[1]这种表示法就可以取第一个）
        查找页面上第一个form元素：//form[1]
        查找页面上id为loginForm的form元素：//form[@id='loginForm']
        查找页面上具有name属性为username的input元素：//input[@name='username']
        查找页面上id为loginForm的form元素下的第一个input元素：//form[@id='loginForm']/input[1]（注意：在xpath中没有第0元素这样的表示方法，都是从1开始）
        查找页面具有name属性为contiune并且type属性为button的input元素：//input[@name='continue'][@type='button']
        查找页面上id为loginForm的form元素下第4个input元素：//form[@id='loginForm']/input[4]
        * */

        //CSS定位
        WebElement password3 =driver.findElement(By.cssSelector("#J_login_form>dl>dt>input[id='J_password']"));
        driver.findElement(By.cssSelector("button.btn.btn_big.btn_submit"));

        /*
        定位id为flrs的div元素，可以写成：#flrs     注：相当于xpath语法的//div[@id='flrs']
        定位id为flrs下的a元素，可以写成：#flrs > a  注：相当于xpath语法的//div[@id='flrs']/a
        定位id为flrs下的href属性值为/forexample/about.html的元素，可以写成： #flrs > a[href=http://blog.csdn.net/qingchunjun/article/details/"/forexample/about.html"]
        如果需要指定多个属性值时，可以逐一加在后面，如#flrs> input[name=”username”][type=”text”]
        * */

    }

}
