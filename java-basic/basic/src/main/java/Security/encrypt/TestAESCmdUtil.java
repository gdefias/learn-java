package Security.encrypt;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.SecureRandom;

/**
 * AES加解密工具
 *
 * 执行：
 * javac Security/Encrypt/TestAES.java
 * java Security/Encrypt/TestAES  -genkey keyfile   //生成秘钥
 * java Security/Encrypt/TestAES  -encrypt plaintext encrypted keyfile   //加密
 * java Security/Encrypt/TestAES  -decrypt encrypted decrypted keyfile  //解密
 *
 */

public class TestAESCmdUtil {
   public static void main(String[] args) {
      try {
         if (args[0].equals("-genkey")) {
             KeyGenerator keygen = KeyGenerator.getInstance("AES");   //为加密算法获取秘钥发生器
             SecureRandom random = new SecureRandom();   //产生安全随机数
             keygen.init(random);  //初始化秘钥发生器
             SecretKey key = keygen.generateKey();  //生成密钥

             ////也可以从一组固定的原始数据来生成一个秘钥
             //SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("AES");  //返回指定加密算法的SecretKeyFactory对象
             //byte[] keyData = ...; //16 byte for AES
             //SecretKeySpec KeySpec = new SecretKeySpec(keyData, "AES");  //创建一个秘钥描述规范
             //Key key = keyFactory.generateSecret(KeySpec);   //根据给定描述规范生成一个新的秘钥

             //将秘钥写入秘钥文件
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(args[1]));
            out.writeObject(key);
            out.close();
         } else {
            int mode;
            if (args[0].equals("-encrypt"))
                mode = Cipher.ENCRYPT_MODE;  //加密模式
            else
                mode = Cipher.DECRYPT_MODE;  //解密模式

             //读出秘钥到key
            ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(args[3]));
            Key key = (Key)keyIn.readObject();
            keyIn.close();

            //输入输出流
            InputStream in = new FileInputStream(args[1]);
            OutputStream out = new FileOutputStream(args[2]);

            //加密或解密
            Cipher cipher = Cipher.getInstance("AES");  //返回指定加密算法的Cipher对象
            cipher.init(mode, key);  //初始化：设置模式和密钥

            crypt(in, out, cipher);
            in.close();
            out.close();
         }
      } catch (IOException e) {
         e.printStackTrace();
      } catch (GeneralSecurityException e) {
         e.printStackTrace();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
   }


   public static void crypt(InputStream in, OutputStream out, Cipher cipher) throws IOException, GeneralSecurityException {
      int blockSize = cipher.getBlockSize();  //返回密码块的大小，如果不是分组密码则返回0。不同的加密算法数据块大小不一样
      int outputSize = cipher.getOutputSize(blockSize); //对于给定的输入大小所需要的输出缓冲区大小
      byte[] inBytes = new byte[blockSize];
      byte[] outBytes = new byte[outputSize];

      int inLength = 0;
      boolean more = true;
      while (more) {
         inLength = in.read(inBytes);
         if (inLength == blockSize) {
             //反复调用update对数据块进行加解密
             int outLength = cipher.update(inBytes, 0, blockSize, outBytes);
             out.write(outBytes, 0, outLength);
         } else {
             more = false;
         }
      }
      //doFinal方法多最后的块进行填充，常用填充方案：PKCS
      if (inLength > 0)  //还有最后一个数据块未处理
          outBytes = cipher.doFinal(inBytes, 0, inLength);
      else
          outBytes = cipher.doFinal();
      out.write(outBytes);
   }
}
