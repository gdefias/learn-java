package selenium;

/**
 * Created by Defias on 2017/3/17.
 */

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class TestWebdriverApi {
    public static WebDriver driver;
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/yzh/Page/driver/chromedriver");
        driver = new ChromeDriver();
        String baseurl = "http://www.sogou.com";

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //隐式等待

        /*
        * 操作浏览器窗口
        * */
        Point point = new Point(150, 150);   //浏览器的位置相对于屏幕左上角(0,0)的横纵坐标
        driver.manage().window().setPosition(point);  //设定浏览器的位置

        Dimension dimension = new Dimension(500,500); //浏览器窗口的长度和宽度
        driver.manage().window().setSize(dimension);

        System.out.println(driver.manage().window().getPosition());  //获取浏览器位置
        System.out.println(driver.manage().window().getSize());  //获取浏览器窗口的长度和宽度

        driver.manage().window().maximize();   //浏览器窗口最大化

        driver.get(baseurl);  //方法1：打开浏览器首页
        //driver.navigate().to(baseurl);  //方法2：打开浏览器首页
        driver.navigate().back();   //回退操作
        driver.navigate().forward();  //前进操作
        driver.navigate().refresh();  //刷新

        System.out.println(driver.getTitle());   //获取页面Title属性
        System.out.println(driver.getPageSource());   //获取页面源代码
        System.out.println(driver.getCurrentUrl());   //获取当前页面URL

        WebElement input = driver.findElement(By.id("query"));
        input.sendKeys("自动化测试");   //在搜索框输入：自动化测试
        input.clear();   //清除输入框的文字
        input.sendKeys("测试");
        System.out.println(input.getAttribute("value"));  //获取元素的属性
        System.out.println(input.getCssValue("width"));  //获取元素的CSS属性

        /*
        * 鼠标双击某个元素
        * */
        Actions builder = new Actions(driver);
        builder.doubleClick(input).build().perform();

        ///*
        //* 显示等待
        //* */
        //WebDriverWait wait = new WebDriverWait(driver, 10);  //设定触发条件的最长等待时间为10s
        //wait.until(ExpectedConditions.titleContains("水果"));  //标题是否包含"水果"
        //
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p")));  //元素是否在页面可用（enable）和可被单击
        //
        //wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//p")));  //元素处于被选中状态
        //
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p")));  //元素在页面中存在
        //
        //wait.until(ExpectedConditions.textToBePresentInElement(By.xpath("//p"), "爱吃的水果")); //元素中包含特定的文本

        ///*
        //* 自定义显示等待
        //* 显示等待判断页面的文本输入框是否可见，如果可见则执行后续测试逻辑
        //* */
        //Boolean inputTextVisibleFlag = (new WebDriverWait(driver, 10))
        //        .until(new ExpectedCondition<Boolean>() {
        //            @Override
        //            public Boolean apply(WebDriver d) {
        //                return d.findElement(By.xpath("//p")).isDisplayed();
        //            }
        //        });

        ///*
        //* 判断页面元素是否存在
        //* */
        if (IsElementPresent(By.id("query"))) {
            WebElement input1 = driver.findElement(By.id("query"));
            if (input1.isEnabled()) {  //判断input1是否可用状态
                input1.sendKeys("输出框被成功找到");
                System.out.println("输出框被成功找到");
            }

        }


        driver.findElement(By.id("stb")).click();  //单击搜索按钮

        driver.quit();   //关闭打开的浏览器
    }

    //判断元素是否存在的函数
    public static Boolean IsElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
