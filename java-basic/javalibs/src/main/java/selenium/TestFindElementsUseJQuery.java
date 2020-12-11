package selenium;

/**
 * Created by Defias on 2017/3/17.
 *
 * jQuery方式定位
 *
 */

/*
jQuery方式定位：调用jQuery库的查找功能，主要用于不能良好支持css定位方式的浏览器
js.executeScript("return jQuery.find('jQuery定位表达式')");
 */

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;


public class TestFindElementsUseJQuery {
    public WebDriver driver;
    JavascriptExecutor js;
    String baseurl = "http://www.sogou.com/";

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "/Users/yzh/Page/driver/chromedriver");
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() throws Exception {
        driver.close();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test() throws InterruptedException {
        driver.get(baseurl);
        js = (JavascriptExecutor)driver;
        injectJQueryIfNeeded();

        //调用jQuery的find方法查找页面中所有a标签的页面元素（即链接元素）
        List<WebElement> elements = (List<WebElement>)js.executeScript("return jQuery.find('a')");
        assertEquals(115, elements.size());  //验证数量
        for(int i=0; i<elements.size(); i++) {  //输出
            System.out.println(elements.get(i).getText() + ", ");
        }
    }

    //根据是否需要加载jQuery
    public void injectJQueryIfNeeded() {
        if (!jQueryLoaded())
            injectJQuery();
    }

    //判断是否已加载jQuery
    public Boolean jQueryLoaded() {
        Boolean loaded;
        try {
            loaded = (Boolean)js.executeScript("return jQuery()!=null");
        } catch (WebDriverException e) {
            loaded = false;
        }
        return loaded;
    }

    //加载jQuery
    public void injectJQuery() {
        System.out.println("Start Load jquery...");
        js.executeScript(" var headID = document.getElementsByTagName(\"head\")[0];"
                + "var newScript = document.createElement('script');"
                + "newScript.type = 'text/javascript';"
                + "newScript.src = 'http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js';"
                + "headID.appendChild(newScript);"
        );
    }
}
