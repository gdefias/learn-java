package SPI.example;

import java.util.ServiceLoader;

/**
 * Created with IntelliJ IDEA.
 * Description: SPI机制
 * User: Defias
 * Date: 2019-08
 *
 * 配置文件 java-basic/base/src/main/resources/META-INF/services/SPI.example.ITest
 */
public class SPIMain {
    public static void main(String[] args) throws Exception {
        ServiceLoader<ITest> test = ServiceLoader.load(ITest.class);
        for(ITest t: test) {
            t.test("aaaa");
            t.test0("bbbb");
        }
    }
}
