package SPI.example;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-05
 *
 * 第一个实现类
 */

public class MyTest implements ITest {
    public void test(String data) throws Exception {
        System.out.println("my test:" + data);
    }

    public void test0(String data) throws Exception {
        System.out.println("test0:" + data);
    }
}
