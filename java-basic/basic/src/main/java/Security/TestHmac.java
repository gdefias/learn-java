package Security;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by Defias on 2020/08.
 * Description: Hmac算法
 */

public class TestHmac {
    public static void main(String[] args) throws Exception {
        testHmac();
        testUnHmac();

    }

    public static void testHmac() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");
        SecretKey key = keyGen.generateKey();
        // 打印随机生成的key:
        byte[] skey = key.getEncoded();
        System.out.println(new BigInteger(1, skey).toString(16));

        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(key);

        //对Mac实例反复调用update(byte[])输入数据
        mac.update("HelloWorld".getBytes("UTF-8"));

        //调用Mac实例的doFinal()获取最终的哈希值
        byte[] result = mac.doFinal();
        System.out.println(new BigInteger(1, result).toString(16));

    }

    public static void testUnHmac() throws Exception {
        //验证：从一个byte[]数组恢复
        byte[] hkey = new byte[] { 106, 70, -110, 125, 39, -20, 52, 56, 85, 9, -19, -72, 52, -53, 52, -45, -6, 119, -63,
                30, 20, -83, -28, 77, 98, 109, -32, -76, 121, -106, 0, -74, -107, -114, -45, 104, -104, -8, 2, 121, 6,
                97, -18, -13, -63, -30, -125, -103, -80, -46, 113, -14, 68, 32, -46, 101, -116, -104, -81, -108, 122,
                89, -106, -109 };

        SecretKey key = new SecretKeySpec(hkey, "HmacMD5");
        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(key);
        mac.update("HelloWorld".getBytes("UTF-8"));
        byte[] result = mac.doFinal();
        System.out.println(Arrays.toString(result));
    }
}
