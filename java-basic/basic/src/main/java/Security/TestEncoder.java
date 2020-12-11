package Security;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Base64;

/**
 * Created by Defias on 2020/08.
 * Description: 编码算法


 */
public class TestEncoder {
    public static void main(String[] args) throws Exception{
        //testURL();
        testBase64();
       // testBase64URL();
    }

    public static void testURL() throws UnsupportedEncodingException {
        //编码
        String encoded = URLEncoder.encode("中文!", "UTF-8");
        System.out.println(encoded);

        //解码
        String decoded = URLDecoder.decode("%E4%B8%AD%E6%96%87%21","UTF-8");
        System.out.println(decoded);
    }


    public static void testBase64() {
        //byte[] input = new byte[] { (byte) 0xe4, (byte) 0xb8, (byte) 0xad, 0x21 };
        //byte[] input = new byte[] { 0x01, 0x02, 0x7f, 0x00 };

        byte[] input = "yzh87117835@163.com:123456".getBytes();

        //编码
        String b64encoded = Base64.getEncoder().encodeToString(input);
        System.out.println(b64encoded);

        //解码
        byte[] output = Base64.getDecoder().decode(b64encoded);
        System.out.println(Arrays.toString(output));

        //编码
        String b64encoded2 = Base64.getEncoder().withoutPadding().encodeToString(input);
        System.out.println(b64encoded2);

        //解码
        byte[] output2 = Base64.getDecoder().decode(b64encoded2);
        System.out.println(Arrays.toString(output2));
    }

    //因为标准的Base64编码会出现+、/和=，所以不适合把Base64编码后的字符串放到URL中
    //一种针对URL的Base64编码可以在URL中使用的Base64编码，它仅仅是把+变成-，/变成_
    public static void testBase64URL() {
        byte[] input = new byte[] { 0x01, 0x02, 0x7f, 0x00 };
        String b64encoded = Base64.getUrlEncoder().encodeToString(input);
        System.out.println(b64encoded);

        byte[] output = Base64.getUrlDecoder().decode(b64encoded);
        System.out.println(Arrays.toString(output));
    }
}
