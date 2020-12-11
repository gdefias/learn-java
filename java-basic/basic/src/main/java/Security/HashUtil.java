package Security;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * Created by Defias on 2017/3/16.
 *
 *  HashUtil 计算Hash摘要工具
 *
 */

public class HashUtil {
    private static String algorithm = "MD5";  //摘要算法 MD5/SHA-1

    public static void main(String[] args) {
        System.out.println(HashUtil.getFormattedText(HashUtil.digestFromStr("123sakdjfkasjflka")));

        System.out.println(HashUtil.getFormattedText(HashUtil.digestFromStr("SHA-1","123sakdjfkasjflka")));
    }


    public static byte[] digestFromStr(String input) {
        if (input == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(input.getBytes());
            return messageDigest.digest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static byte[] digestFromStr(String algorithm, String input) {
        if (algorithm!=null) {
            HashUtil.algorithm = algorithm;
        }
        return digestFromStr(input);
    }


    public static byte[] digestFromByteArray(byte[] input) {
        if (input == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(input);
            return messageDigest.digest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static byte[] digestFromByteArray(String algorithm, byte[] input) {
        if (algorithm!=null) {
            HashUtil.algorithm = algorithm;
        }
        return digestFromByteArray(input);
    }



    public static byte[] digestFromFile(String filename) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            InputStream in = new FileInputStream(new File(filename));
            int ch;
            while ((ch = in.read()) != -1) {   //反复调用update方法将数据中的所有字节提供给该对象
                messageDigest.update((byte) ch);
            }
            return messageDigest.digest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] digestFromFile(String algorithm, String filename) {
        if (algorithm!=null) {
            HashUtil.algorithm = algorithm;
        }
        return digestFromFile(filename);
    }


    public static byte[] digestFromInputStream(InputStream in) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            int ch;
            while ((ch = in.read()) != -1) {   //反复调用update方法将数据中的所有字节提供给该对象
                messageDigest.update((byte) ch);
            }
            return messageDigest.digest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] digestFromInputStream(String algorithm, InputStream in) {
        if (algorithm!=null) {
            HashUtil.algorithm = algorithm;
        }
        return digestFromInputStream(in);
    }




    private static String getFormattedText(byte[] bytes) {
        char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);

        //把摘要转换成十六进制的字符串形式
        for (int j=0; j<len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }
}
