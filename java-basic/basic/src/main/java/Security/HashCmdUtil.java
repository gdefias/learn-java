package Security;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-05
 *
 * HashCmdUtil 计算Hash摘要命令行工具
 *
 * 执行：
 * javac Security/Signature/Digest.java
 * java Security/Signature/Digest input.txt
 * java Security/Signature/Digest input.txt MD5
 *
 */

public class HashCmdUtil {

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        String algname = args.length >= 2 ? args[1] : "SHA-1";   //默认使用SHA-1
        MessageDigest alg = MessageDigest.getInstance(algname);  //获取一个能计算指定算法的指纹的对象，SHA-1或MD5

        //读取待计算摘要的数据
        byte[] input = Files.readAllBytes(Paths.get(args[0]));

        //完成散列计算，返回计算所得的摘要，并复位算法对象
        byte[] hash = alg.digest(input);

        //输出
        String d = "";
        for (int i=0; i<hash.length; i++) {
            int v = hash[i] & 0xFF;
            if (v < 16)
                d += "0";
            d += Integer.toString(v, 16).toUpperCase() + " ";
        }
        System.out.println(d);
    }
}
