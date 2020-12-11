package JavaRelease.Java8;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2019-07
 *
 * Base64
 * 对Base64的支持最终成了Java 8标准库的一部分
 *
 * Base64工具类提供了一套静态方法获取下面三种BASE64编解码器：
 *      基本：输出被映射到一组字符A-Za-z0-9+/，编码不添加任何行标，输出的解码仅支持A-Za-z0-9+/
 *
 *      URL：输出映射到一组字符A-Za-z0-9+_，输出是URL和文件
 *      Base64.getUrlEncoder() / Base64.getUrlDecoder()
 *
 *      MIME：输出隐射到MIME友好格式。输出每行不超过76字符，并且使用'\r'并跟随'\n'作为分割。编码输出最后没有行分割
 *      Base64.getMimeEncoder() / Base64.getMimeDecoder()
 */




public class Test12Base64 {
    public static void main(String[] args) {
        final String text = "Base64 finally in Java 8!";

        final String encoded = Base64
                .getEncoder()
                .encodeToString( text.getBytes( StandardCharsets.UTF_8 ) );

        System.out.println( encoded );

        final String decoded = new String(
                Base64.getDecoder().decode( encoded ),
                StandardCharsets.UTF_8 );

        System.out.println( decoded );
    }
}
