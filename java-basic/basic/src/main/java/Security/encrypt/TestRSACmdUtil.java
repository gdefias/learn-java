package Security.encrypt;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

/**
 *
 * RSA加解密 -- 非对称密码/公共秘钥密码
 *
 *
 * java Security/Encrypt/TestAES -genkey publickey privatekey
 * java Security/Encrypt/TestAES -encrypt plaintext encryptedr publickey
 * java Security/Encrypt/TestAES -decrypt encryptedr decryptedr privatekey
 *
 */
public class TestRSACmdUtil {
    private static final int KEYSIZE = 512;

    public static void main(String[] args) {
        try {
            if (args[0].equals("-genkey")) {  //生成RSA的密钥对
                KeyPairGenerator pairgen = KeyPairGenerator.getInstance("RSA"); //为加密算法获取秘钥发生器
                SecureRandom random = new SecureRandom();
                pairgen.initialize(KEYSIZE, random);  //初始化秘钥发生器
                KeyPair keyPair = pairgen.generateKeyPair();  //生成密钥对

                //公钥
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(args[1]));
                out.writeObject(keyPair.getPublic());
                out.close();

                 //私钥
                 out = new ObjectOutputStream(new FileOutputStream(args[2]));
                 out.writeObject(keyPair.getPrivate());
                 out.close();
            } else if (args[0].equals("-encrypt")) {   //使用RSA对AES的密钥进行加密
                 KeyGenerator keygen = KeyGenerator.getInstance("AES");
                 SecureRandom random = new SecureRandom();
                 keygen.init(random);
                 SecretKey key = keygen.generateKey();  //产生AES密钥

                 //读取公钥
                 ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(args[3]));
                 Key publicKey = (Key) keyIn.readObject();
                 keyIn.close();

                 Cipher cipher = Cipher.getInstance("RSA");
                 cipher.init(Cipher.WRAP_MODE, publicKey); //WRAP_MODE模式：用一个密钥对另一个密钥进行加密
                 byte[] wrappedKey = cipher.wrap(key); //对AES的密钥进行加密/包装，返回包装后的结果

                 //向最终结果写入包装结果长度和内容
                 DataOutputStream out = new DataOutputStream(new FileOutputStream(args[2]));
                 out.writeInt(wrappedKey.length);
                 out.write(wrappedKey);

                 //获取待加密的输入
                 InputStream in = new FileInputStream(args[1]);
                 cipher = Cipher.getInstance("AES");
                 cipher.init(Cipher.ENCRYPT_MODE, key);
                 crypt(in, out, cipher);  //使用AES加密
                 in.close();
                 out.close();
            } else {  //解密
                 //读取包装结果长度和内容
                 DataInputStream in = new DataInputStream(new FileInputStream(args[1]));
                 int length = in.readInt();
                 byte[] wrappedKey = new byte[length];
                 in.read(wrappedKey, 0, length);

                 //读取私钥
                 ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(args[3]));
                 Key privateKey = (Key) keyIn.readObject();
                 keyIn.close();

                 Cipher cipher = Cipher.getInstance("RSA");
                 cipher.init(Cipher.UNWRAP_MODE, privateKey);
                 Key key = cipher.unwrap(wrappedKey, "AES", Cipher.SECRET_KEY); //对AES的密钥包装进行解密，获得AES的密钥key

                 OutputStream out = new FileOutputStream(args[2]);
                 cipher = Cipher.getInstance("AES");
                 cipher.init(Cipher.DECRYPT_MODE, key);   //AES解密模式

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

   /**
    * Uses a cipher to transform the bytes in an input stream and sends the transformed bytes to an
    * output stream.
    * @param in the input stream
    * @param out the output stream
    * @param cipher the cipher that transforms the bytes
    */
   public static void crypt(InputStream in, OutputStream out, Cipher cipher) throws IOException, GeneralSecurityException {
       int blockSize = cipher.getBlockSize();
       int outputSize = cipher.getOutputSize(blockSize);
       byte[] inBytes = new byte[blockSize];
       byte[] outBytes = new byte[outputSize];

       int inLength = 0;
       boolean more = true;
       while (more) {
           inLength = in.read(inBytes);
           if (inLength == blockSize) {
               int outLength = cipher.update(inBytes, 0, blockSize, outBytes);
               out.write(outBytes, 0, outLength);
           } else
               more = false;
      }
      if (inLength > 0)
          outBytes = cipher.doFinal(inBytes, 0, inLength);
      else
          outBytes = cipher.doFinal();
      out.write(outBytes);
   }
}
